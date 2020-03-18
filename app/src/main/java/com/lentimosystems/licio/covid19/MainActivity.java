package com.lentimosystems.licio.covid19;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.functions.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lentimosystems.licio.covid19.Adapter.CoronaAdapter;


import com.lentimosystems.licio.covid19.Api.CovidApi;
import com.lentimosystems.licio.covid19.Api.CovidClient;
import com.lentimosystems.licio.covid19.Model.CovidModel;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    CovidApi myAPI;
    RecyclerView recycler;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = CovidClient.getInstance();
        myAPI = retrofit.create(CovidApi.class);

        recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

    }

    private void fetchData() {
        compositeDisposable.add(myAPI.getCovidModel()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CovidModel>>() {
                    @Override
                    public void accept(List<CovidModel> posts) throws Exception {
                        displayData(posts);
                    }
                }));
    }

    private void displayData(List<CovidModel> models) {
        CoronaAdapter adapter = new CoronaAdapter(this,models);
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
