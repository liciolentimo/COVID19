package com.lentimosystems.licio.covid19.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lentimosystems.licio.covid19.R;

public class CoronaViewHolder extends RecyclerView.ViewHolder {
    TextView country,cases,todayCases,deaths,todayDeaths,recovered,critical,casesPerOneMillion;

    public CoronaViewHolder(@NonNull View itemView) {
        super(itemView);

        country = itemView.findViewById(R.id.txtCountry);
        cases = itemView.findViewById(R.id.txtCases);
        todayCases = itemView.findViewById(R.id.txtTodayCases);
        deaths = itemView.findViewById(R.id.txtDeaths);
        todayDeaths = itemView.findViewById(R.id.txtTodayDeaths);
        recovered = itemView.findViewById(R.id.txtRecovered);
        critical = itemView.findViewById(R.id.txtCritical);
        casesPerOneMillion = itemView.findViewById(R.id.txtCasesPerOneMil);
    }
}
