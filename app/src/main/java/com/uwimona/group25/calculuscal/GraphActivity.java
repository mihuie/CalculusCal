package com.uwimona.group25.calculuscal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        String plotPointsString = bundle.getString("message");
//        String plotPointsString = "[(3 . 363), (4 . 752), (5 . 1325)]";

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart lineChart = (LineChart)findViewById(R.id.chart);
        lineChart.setNoDataText("");

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Float> xValues = new ArrayList<>();
        ArrayList<Float> yValues = new ArrayList<>();


        plotPointsString = plotPointsString.replace("[", "");
        plotPointsString = plotPointsString.replace("]", "");
        plotPointsString = plotPointsString.replace(" ", "");
        String[] points = plotPointsString.split(",");

//        Log.d("points", Arrays.toString(points));

        for (int i=0; i < points.length; i++){
            String str;
            str = points[i].replace("(", "");
            str = str.replace(")", "");
//            Log.d("mystring", str);

            str = str.replace(".", ",");

            String[] xy = str.split(",");
//            Log.d("xy", Arrays.toString(xy));

            float x = Float.parseFloat(xy[0]);
            float y = Float.parseFloat(xy[1]);
            xValues.add(x);
            yValues.add(y);
        }


        for(int i=0; i < xValues.size(); i++){
            entries.add(new Entry(xValues.get(i), yValues.get(i)));
        }

//===============
//        xValues.add(3f);
//        yValues.add(363f);
//        xValues.add(4f);
//        yValues.add(752f);
//        xValues.add(5f);
//        yValues.add(1325f);
//
//        for(int i=0; i < xValues.size(); i++){
//            entries.add(new Entry(xValues.get(i), yValues.get(i)));
//        }
// =============

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
