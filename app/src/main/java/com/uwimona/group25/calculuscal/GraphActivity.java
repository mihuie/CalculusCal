package com.uwimona.group25.calculuscal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;



public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        LineChart lineChart = (LineChart)findViewById(R.id.chart);
        lineChart.setNoDataText("");

        ArrayList<Entry> entries = new ArrayList<>();

        double x = 0;
        int numDataPoints = 500;
        for(int i=0; i <numDataPoints; i++){
            float function = Float.parseFloat(String.valueOf(Math.sin(x)));
            x = x + 0.1;
            entries.add(new Entry(i, function));
        }

        ArrayList<ILineDataSet> lineDataSet = new ArrayList<>();
        LineDataSet data = new LineDataSet(entries, "sin");
        data.setDrawCircles(false);
        data.setDrawValues(false);
        data.setColor(Color.BLUE);
        data.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(true);

        lineDataSet.add(data);

        lineChart.setData(new LineData(lineDataSet));
        lineChart.setVisibleXRangeMaximum(65f);
        lineChart.setScaleEnabled(false);


    }

}
