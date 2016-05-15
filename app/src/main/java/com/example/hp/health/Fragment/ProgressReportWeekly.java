package com.example.hp.health.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.hp.health.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class ProgressReportWeekly extends Fragment {
    private View _view;
    private Button confirm;
    private Button daily;
    private EditText startDate;
    private EditText endDate;
    private FragmentManager manager;
    private LinearLayout reportweekly;
    private LineChart mLinechart1;
    private LineChart mLinechart2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view =  inflater.inflate(R.layout.fragment_progress_report_weekly, container, false);
        daily =(Button)_view.findViewById(R.id.day);
        reportweekly = (LinearLayout)_view.findViewById(R.id.reportweekly);
        confirm = (Button)_view.findViewById(R.id.confirmWeekly);
        mLinechart1 = (LineChart)_view.findViewById(R.id.lineChart1);
        mLinechart2 = (LineChart)_view.findViewById(R.id.lineChart2);
        manager = getFragmentManager();

        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportweekly.setVisibility(View.GONE);
                ProgressReport progressReport = new ProgressReport();
                manager.beginTransaction().replace(R.id.weekly12,progressReport).commit();
            }
        });


        LineData mLineData = getLineData(7, 100);
        showChart(mLinechart1, mLineData, Color.rgb(255,250,250));

        LineData mLineData1 = getLineDataBurned(7, 100);
        showChart(mLinechart2, mLineData1, Color.rgb(255,250,250));




        return _view;
    }

    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 23; i < 30; i++) {
            xValues.add("" + i);
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        yValues.add(new Entry(5000,0));
        yValues.add(new Entry(4500,1));
        yValues.add(new Entry(5670,2));
        yValues.add(new Entry(3400,3));
        yValues.add(new Entry(2580,4));
        yValues.add(new Entry(5600,5));
        yValues.add(new Entry(7590,6));
        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "Total Steps Everyday" /*显示在比例图上*/);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
//        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setColor(Color.rgb(114, 188, 223));// 显示颜色
        lineDataSet.setCircleColor(Color.rgb(114, 188, 223));// 圆形的颜色
        lineDataSet.setHighLightColor(Color.rgb(114, 188, 223)); // 高亮的线的颜色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues,lineDataSet);

        return lineData;
    }
    private LineData getLineDataBurned(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 23; i < 30; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }

        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        yValues.add(new Entry(1500,0));
        yValues.add(new Entry(560,1));
        yValues.add(new Entry(1350,2));
        yValues.add(new Entry(1400,3));
        yValues.add(new Entry(450,4));
        yValues.add(new Entry(560,5));
        yValues.add(new Entry(759,6));
        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "Calorie Burned" /*显示在比例图上*/);

        //用y轴的集合来设置参数
        lineDataSet.setLineWidth(1.75f); // 线宽
//        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setColor(Color.rgb(255, 0, 0));// 显示颜色
        lineDataSet.setCircleColor(Color.rgb(255, 0, 0));// 圆形的颜色
        lineDataSet.setHighLightColor(Color.rgb(255, 0, 0)); // 高亮的线的颜色

        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet); // add the datasets

        // create a data object with the datasets
        LineData lineData = new LineData(xValues,lineDataSet);

        return lineData;
    }

    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色

        lineChart.animateX(2500); // 立即执行的动画,x轴
    }


}
