package com.lentimosystems.licio.covid19.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.lentimosystems.licio.covid19.Model.QuestionsModel;
import com.lentimosystems.licio.covid19.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "QUIZ_FRAGMENT_LOG";
    private FirebaseFirestore firebaseFirestore;

    private String quizId;

    //UI Elements
    private TextView quizTitle;
    private Button optionOneBtn;
    private Button optionTwoBtn;
    private Button optionThreeBtn;
    private Button nextBtn;
    private ImageButton closeBtn;
    private TextView questionFeedback;
    private TextView questionText;
    private TextView questionTime;
    private ProgressBar questionProgress;
    private TextView questionNumber;

    private List<QuestionsModel> allQuestionsList = new ArrayList<>();
    private List<QuestionsModel> questionsToAnswer = new ArrayList<>();
    private Long totalQuestionsToAnswer = 0L;
    private CountDownTimer countDownTimer;

    private boolean canAnswer = false;
    private int currentQuestion = 0;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseFirestore = FirebaseFirestore.getInstance();

        //UI Initialize
        quizTitle = view.findViewById(R.id.quiz_title);
        optionOneBtn = view.findViewById(R.id.quiz_option_one);
        optionTwoBtn = view.findViewById(R.id.quiz_option_two);
        optionThreeBtn = view.findViewById(R.id.quiz_option_three);
        nextBtn = view.findViewById(R.id.quiz_next_btn);
        questionFeedback = view.findViewById(R.id.quiz_question_feedback);
        questionText = view.findViewById(R.id.quiz_question);
        questionTime = view.findViewById(R.id.quiz_question_time);
        questionProgress = view.findViewById(R.id.quiz_question_progress);
        questionNumber = view.findViewById(R.id.quiz_question_number);

        quizId = QuizFragmentArgs.fromBundle(getArguments()).getQuizId();
        totalQuestionsToAnswer = QuizFragmentArgs.fromBundle(getArguments()).getTotalQuestions();

        //Query Firestore Data
        firebaseFirestore.collection("QuizList")
                .document(quizId).collection("Questions")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    allQuestionsList = task.getResult().toObjects(QuestionsModel.class);
                    pickQuestions();
                    loadUI();
                } else {
                    quizTitle.setText("Error : " + task.getException().getMessage());
                }
            }
        });

        //Set Button Click Listeners
        optionOneBtn.setOnClickListener(this);
        optionTwoBtn.setOnClickListener(this);
        optionThreeBtn.setOnClickListener(this);
    }

    private void loadUI() {
        //Quiz Data Loaded, Load the UI
        quizTitle.setText("Quiz Data Loaded");
        questionText.setText("Load First Question");

        //Enable Options
        enableOptions();

        //Load First Question
        loadQuestion(1);
    }

    private void loadQuestion(int questNum) {
        //Set Question Number
        questionNumber.setText(questNum + "");

        //Load Question Text
        questionText.setText(questionsToAnswer.get(questNum).getQuestion());

        //Load Options
        optionOneBtn.setText(questionsToAnswer.get(questNum).getOption_a());
        optionTwoBtn.setText(questionsToAnswer.get(questNum).getOption_b());
        optionThreeBtn.setText(questionsToAnswer.get(questNum).getOption_c());

        //Question Loaded, Set Can Answer
        canAnswer = true;
        currentQuestion = questNum;

        //Start Question Timer
        startTimer(questNum);
    }

    private void startTimer(int questionNumber) {

        //Set Timer Text
        final Long timeToAnswer = questionsToAnswer.get(questionNumber).getTimer();
        questionTime.setText(timeToAnswer.toString());

        //Show Timer ProgressBar
        questionProgress.setVisibility(View.VISIBLE);

        //Start CountDown
        countDownTimer = new CountDownTimer(timeToAnswer*1000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                //Update Time
                questionTime.setText(millisUntilFinished/1000 + "");

                //Progress in percent
                Long percent = millisUntilFinished/(timeToAnswer*10);
                questionProgress.setProgress(percent.intValue());
            }

            @Override
            public void onFinish() {
                //Time Up, Cannot Answer Question Anymore
                canAnswer = false;
            }
        };

        countDownTimer.start();
    }

    private void enableOptions() {
        //Show All Option Buttons
        optionOneBtn.setVisibility(View.VISIBLE);
        optionTwoBtn.setVisibility(View.VISIBLE);
        optionThreeBtn.setVisibility(View.VISIBLE);

        //Enable Option Buttons
        optionOneBtn.setEnabled(true);
        optionTwoBtn.setEnabled(true);
        optionThreeBtn.setEnabled(true);

        //Hide Feedback and next Button
        questionFeedback.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        nextBtn.setEnabled(false);
    }

    private void pickQuestions() {
        for(int i=0; i < totalQuestionsToAnswer; i++) {
            int randomNumber = getRandomInt(0, allQuestionsList.size());
            questionsToAnswer.add(allQuestionsList.get(randomNumber));
            allQuestionsList.remove(randomNumber);
            Log.d("QUESTIONS LOG", "Question " + i + " : " + questionsToAnswer.get(i).getQuestion());
        }
    }

    private int getRandomInt(int min, int max) {
        return ((int) (Math.random()*(max-min))) + min;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quiz_option_one:
                answerSelected(optionOneBtn.getText());
                break;
            case R.id.quiz_option_two:
                answerSelected(optionTwoBtn.getText());
                break;
            case R.id.quiz_option_three:
                answerSelected(optionThreeBtn.getText());
                break;
        }
    }

    private void answerSelected(CharSequence selectedAnswer) {
        //Check Answer
        if(canAnswer){
            if(questionsToAnswer.get(currentQuestion).getAnswer().equals(selectedAnswer)){
                //Correct Answer
                Log.d(TAG, "Correct Answer");
            } else {
                //Wrong Answer
                Log.d(TAG, "Wrong Answer");
            }
            //Set Can answer to false
            canAnswer = false;
        }
    }
}