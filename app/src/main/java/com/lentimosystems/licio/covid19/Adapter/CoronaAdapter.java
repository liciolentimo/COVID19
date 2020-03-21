package com.lentimosystems.licio.covid19.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lentimosystems.licio.covid19.MainActivity;
import com.lentimosystems.licio.covid19.Model.CovidModel;
import com.lentimosystems.licio.covid19.R;

import java.util.List;

import www.sanju.motiontoast.MotionToast;

public class CoronaAdapter extends RecyclerView.Adapter<CoronaViewHolder> {
    Context context;
    List<CovidModel> model;

    public CoronaAdapter(Context context, List<CovidModel> model) {
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public CoronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_card,parent,false);
        return new CoronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaViewHolder holder, int position) {
        holder.country.setText(String.valueOf(model.get(position).country));
        holder.cases.setText("Total Cases: " + String.valueOf(model.get(position).cases));
        holder.todayCases.setText("Cases Today: " + String.valueOf(model.get(position).todayCases));
        holder.deaths.setText("Total Deaths: " + String.valueOf(model.get(position).deaths));
        holder.todayDeaths.setText("Deaths Today: " + String.valueOf(model.get(position).todayDeaths));
        holder.recovered.setText("Recovered: " + String.valueOf(model.get(position).recovered));
        holder.critical.setText("Critical: " + String.valueOf(model.get(position).critical));
        holder.casesPerOneMillion.setText("Cases Per One Million: " + String.valueOf(model.get(position).casesPerOneMillion));
    }
    @Override
    public int getItemCount() {
        return model.size();
    }

}
