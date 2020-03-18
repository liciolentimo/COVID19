package com.lentimosystems.licio.covid19.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lentimosystems.licio.covid19.Model.CovidModel;
import com.lentimosystems.licio.covid19.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.RecyclerViewHolder> {
    private ArrayList<CovidModel> covidModel = new ArrayList<>();

    public CovidAdapter(ArrayList<CovidModel> covidModel){
        this.covidModel = covidModel;
    }



    @NonNull
    @Override
    public CovidAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidAdapter.RecyclerViewHolder holder, int position) {
        String country = covidModel.get(position).getCountry();
        holder.txtCountry.setText(country);
    }

    @Override
    public int getItemCount() {
        return covidModel.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.txtCountry)
        TextView txtCountry;
        RecyclerViewHolder(@NonNull View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
