package com.lentimosystems.licio.covid19.Adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lentimosystems.licio.covid19.Model.QuizListModel;

import java.util.List;


public class QuizListViewModel extends ViewModel implements FirebaseRepository.OnFirestoreTaskComplete {
    private MutableLiveData<List<QuizListModel>> quizListModelData = new MutableLiveData<>();
    public LiveData<List<QuizListModel>> getQuizListModelData() {
        return quizListModelData;
    }
    private FirebaseRepository firebaseRepository = new FirebaseRepository(this);

    public QuizListViewModel() {
        firebaseRepository.getQuizData();
    }

    @Override
    public void quizListDataAdded(List<QuizListModel> quizListModelsList) {
        quizListModelData.setValue(quizListModelsList);
    }

    @Override
    public void onError(Exception e) {

    }
}
