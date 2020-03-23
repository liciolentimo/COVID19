package com.lentimosystems.licio.covid19;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.functions.Consumer;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.lentimosystems.licio.covid19.Adapter.CoronaAdapter;


import com.lentimosystems.licio.covid19.Api.CovidApi;
import com.lentimosystems.licio.covid19.Api.CovidClient;
import com.lentimosystems.licio.covid19.Model.CovidModel;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import www.sanju.motiontoast.MotionToast;


public class MainActivity extends AppCompatActivity {
    CovidApi myAPI;
    RecyclerView recycler;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ChipNavigationBar chipNavigationBar;

    SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.swipeRefresh);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshLayout.setRefreshing(false);
            }
        });

        chipNavigationBar = findViewById(R.id.bottom_nav);
        chipNavigationBar.setItemSelected(R.id.home,true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.discover:
                        Intent intent = new Intent(MainActivity.this,DiscoverActivity.class);
                        startActivity(intent);
                        break;
//                        MotionToast.Companion.createToast(MainActivity.this,"Coming Soon!",
//                                MotionToast.Companion.getTOAST_INFO(),
//                                MotionToast.Companion.getGRAVITY_CENTER(),
//                                MotionToast.Companion.getLONG_DURATION(),
//                                ResourcesCompat.getFont(MainActivity.this,R.font.helvetica_regular));
                    case R.id.trivia:
                        Intent triviaIntent = new Intent(MainActivity.this,TriviaActivity.class);
                        startActivity(triviaIntent);
//                        MotionToast.Companion.createToast(MainActivity.this,"Coming Soon!",
//                                MotionToast.Companion.getTOAST_INFO(),
//                                MotionToast.Companion.getGRAVITY_CENTER(),
//                                MotionToast.Companion.getSHORT_DURATION(),
//                                ResourcesCompat.getFont(MainActivity.this,R.font.helvetica_regular));

                }

            }
        });


        Retrofit retrofit = CovidClient.getInstance();
        myAPI = retrofit.create(CovidApi.class);

        recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        int resId = R.anim.layout_animation_fall_down;

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(MainActivity.this,resId);
        recycler.setLayoutAnimation(animationController);


        fetchData();
        //runLayoutAnimation(recycler);

    }


    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
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
