package com.uwimona.group25.calculuscal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.Collections;


public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart lineChart = (LineChart)findViewById(R.id.chart);
        lineChart.setNoDataText("");

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Float> xValues = new ArrayList<>();
        ArrayList<Float> yValues = new ArrayList<>();

//        yValues.add(4);
//        yValues.add(12);
//        xValues.add(0);
//        xValues.add(6);

//        for(int i=0; i < 2; i++){
//            entries.add(new Entry(xValues.get(i), yValues.get(i)));
//        }

        double x = 0;
        int numDataPoints = 500;
        for(int i=0; i <numDataPoints; i++){
            float function = Float.parseFloat(String.valueOf(Math.cos(x)));
            xValues.add((float)i);
            yValues.add(function);
            x = x + 0.1;
            entries.add(new Entry(i, function));
        }

        float xOffsetMin, xOffsetMax, yOffsetMin, yOffsetMax;
        final float MAX = 4;
        final float MIN = -4;

        xOffsetMax = Math.max(Collections.max(xValues) + 1, MAX);
        xOffsetMin = Math.min(Collections.min(xValues) - 1, MIN);
        yOffsetMax = Math.max(Collections.max(yValues) + 1, MAX);
        yOffsetMin = Math.min(Collections.min(yValues) - 1, MIN);

        ArrayList<ILineDataSet> lineDataSet = new ArrayList<>();
        LineDataSet data = new LineDataSet(entries, "");
        data.setDrawCircles(false);
        data.setDrawValues(false);
        data.setColor(Color.BLUE);
        data.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMinimum(xOffsetMin);
        xAxis.setAxisMaximum(xOffsetMax);
        xAxis.setDrawGridLines(true);

        lineChart.getAxisRight().setEnabled(false);
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(yOffsetMin);
        yAxis.setAxisMaximum(yOffsetMax);
        yAxis.setDrawGridLines(true);
        yAxis.setDrawZeroLine(true);
        yAxis.setZeroLineWidth(2f);

        lineDataSet.add(data);

        lineChart.setData(new LineData(lineDataSet));
        lineChart.setVisibleXRangeMaximum(65f);
        lineChart.setScaleEnabled(false);
        lineChart.getDescription().setEnabled(false);


    }

}
