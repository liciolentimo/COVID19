package com.lentimosystems.licio.covid19.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lentimosystems.licio.covid19.MainActivity;
import com.lentimosystems.licio.covid19.R;

import www.sanju.motiontoast.MotionToast;

public class CoronaViewHolder extends RecyclerView.ViewHolder  {
    TextView country,cases,todayCases,deaths,todayDeaths,recovered,critical,tests;

    private Context context;

    public CoronaViewHolder(@NonNull View itemView) {
        super(itemView);

        country = itemView.findViewById(R.id.txtCountry);
        cases = itemView.findViewById(R.id.txtCases);
        todayCases = itemView.findViewById(R.id.txtTodayCases);
        deaths = itemView.findViewById(R.id.txtDeaths);
        todayDeaths = itemView.findViewById(R.id.txtTodayDeaths);
        recovered = itemView.findViewById(R.id.txtRecovered);
        critical = itemView.findViewById(R.id.txtCritical);
        tests = itemView.findViewById(R.id.txtCasesPerOneMil);

     
    }

}
