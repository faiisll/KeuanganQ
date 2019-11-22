package com.example.keuanganq;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {
    private TextView showDate;
    private Button pickDate;
    private FragmentActivity myfragment;

    Integer nominalPeng[] = {3000, 1000, 2500};
    String kategoriPeng[] = {"Pangan", "Pendidikan", "Gadget"};
    Integer nominalPem[] = {4000, 3000, 1500};
    String kategoriPem[] = {"Gaji", "Projek", "Jualan"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentManager fragman = myfragment.getSupportFragmentManager();
        fragman.beginTransaction().replace(R.id.detailSect,
                new DetailPengeluaranFrag()).commit();

        return inflater.inflate(R.layout.fragment_chart, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Button btnPengeluaran = view.findViewById(R.id.btnPengeluaran);
        final Button btnPemasukan = view.findViewById(R.id.btnPemasukan);
        setupPieChart(nominalPeng, kategoriPeng);

        btnPengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragman = myfragment.getSupportFragmentManager();
                fragman.beginTransaction().replace(R.id.detailSect,
                        new DetailPengeluaranFrag()).commit();
                setupPieChart(nominalPeng, kategoriPeng);

                btnPemasukan.setTextColor(Color.parseColor("#6F6F6F"));
                btnPemasukan.setBackgroundResource(R.drawable.btn_unselected);
                btnPengeluaran.setTextColor(Color.parseColor("#9C1EFF"));
                btnPengeluaran.setBackgroundResource(R.drawable.btn_selected);


            }
        });

        btnPemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragman = myfragment.getSupportFragmentManager();
                fragman.beginTransaction().replace(R.id.detailSect,
                        new DetailPemasukanFrag()).commit();

                setupPieChart(nominalPem, kategoriPem);

                btnPemasukan.setTextColor(Color.parseColor("#9C1EFF"));
                btnPemasukan.setBackgroundResource(R.drawable.btn_selected);
                btnPengeluaran.setTextColor(Color.parseColor("#6F6F6F"));
                btnPengeluaran.setBackgroundResource(R.drawable.btn_unselected);


            }
        });




    }

    @Override
    public void onAttach(Activity activity) {
        myfragment = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    private void setupPieChart(Integer nilai[], String[] label) {
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; i <nilai.length;i++){
            pieEntries.add(new PieEntry(nilai[i], label[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) getView().findViewById(R.id.pieChart);
        chart.setData(data);
        Description desc = chart.getDescription();
        desc.setEnabled(false);
        chart.animateY(700);
        chart.setUsePercentValues(true);
        chart.setHoleRadius(60);
        chart.invalidate();
    }


}
