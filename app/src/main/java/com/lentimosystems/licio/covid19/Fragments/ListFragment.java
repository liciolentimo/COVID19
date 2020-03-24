package com.lentimosystems.licio.covid19.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.lentimosystems.licio.covid19.Adapter.QuizListAdapter;
import com.lentimosystems.licio.covid19.Adapter.QuizListViewModel;
import com.lentimosystems.licio.covid19.Model.QuizListModel;
import com.lentimosystems.licio.covid19.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private RecyclerView listView;
    private QuizListViewModel quizListViewModel;
    private QuizListAdapter adapter;
    private ProgressBar listProgress;
    private Animation fadeInAnim;
    private Animation fadeOutAnim;


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.list_view);
        adapter = new QuizListAdapter();
        listProgress = view.findViewById(R.id.list_progress);

        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.setHasFixedSize(true);
        listView.setAdapter(adapter);

        fadeInAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        fadeOutAnim = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        quizListViewModel = new ViewModelProvider(getActivity()).get(QuizListViewModel.class);
        quizListViewModel.getQuizListModelData().observe(getViewLifecycleOwner(), new Observer<List<QuizListModel>>() {
            @Override
            public void onChanged(List<QuizListModel> quizListModels) {
                //Load RecyclerView
                listView.startAnimation(fadeInAnim);
                listProgress.startAnimation(fadeOutAnim);
                adapter.setQuizListModels(quizListModels);
                adapter.notifyDataSetChanged();
            }
        });
    }

}
