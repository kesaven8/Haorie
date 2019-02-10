package com.example.kesavenvulliamay.healthapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private LineChart linechart;
    private Legend legend;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        linechart = findViewById(R.id.chart);
        legend=linechart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormSize(10);

        Formart_Days_Chart(linechart);


        // adding values with respect to x-axis and y-axix

        ArrayList<Entry> data = new ArrayList<>();
        data.add(new Entry(0,0));
        data.add(new Entry(1,1));
        data.add(new Entry(6,2));

        // Represent one line
        LineDataSet dataset = new LineDataSet(data,"Calories");



        LineData lineData = new LineData(dataset);


        linechart.setData(lineData);
        linechart.invalidate(); // refresh



    }

    // Function that format the number of days to fit in the chart
    public void Formart_Days_Chart(LineChart lineChart){

        final String[] quarters = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday","Friday","Saturday","Sunday" };


        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

        };



        XAxis xAxis = linechart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);



        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setTextSize(10);

    }


}
