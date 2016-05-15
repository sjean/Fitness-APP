package com.example.hp.health.Fragment;

import android.graphics.Color;
import android.os.AsyncTask;
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

import com.example.hp.health.Db.Netconnect;
import com.example.hp.health.Db.URL;
import com.example.hp.health.Model.Utility;
import com.example.hp.health.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProgressReport extends Fragment {
    View _view;
    private Button weekly;
    private Button confirm;
    private EditText date;
    private FragmentManager manager;
    private LinearLayout healthy;
    private FragmentTransaction transaction;
    private BarChart mBarchat;
    private BarData mBardata;
    private LineChart mLinechart;
    private String oneday;
    private String username;
    private String Steps;
    private String Goal;
    private Float _goal = Float.parseFloat("0");
    private Float _steps = Float.parseFloat("0");
    private Float _burned = Float.parseFloat("0");
    private Float _consumed = Float.parseFloat("0");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        manager = getFragmentManager();
        _view = inflater.inflate(R.layout.fragment_progress_report, container, false);
        weekly = (Button)_view.findViewById(R.id.week);
        healthy = (LinearLayout)_view.findViewById(R.id.healthy);
        mBarchat = (BarChart)_view.findViewById(R.id.barChart);
        mLinechart = (LineChart)_view.findViewById(R.id.lineChart);
        date = (EditText)_view.findViewById(R.id.reportdate);
        confirm = (Button)_view.findViewById(R.id.confirmDaily);

        date.setText(Utility.Today());
        username = getActivity().getIntent().getStringExtra("username");

        oneday = date.getText().toString();
        String url = URL.URL_GetGoal+ oneday+"/"+username;
        new getGoal().execute(url);
        String url1 = URL.URL_GetTotalSteps+oneday+"/"+username;
        new getTotalSteps().execute(url1);
        String urlBurned = URL.URL_StepsBurned+username+"/"+oneday;
        new getStepsBurned().execute(urlBurned);
        String urlConsumed = URL.URL_Consumed+username+"/"+oneday;
        new getCalorieConsumed().execute(urlConsumed);

        weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healthy.setVisibility(View.GONE);
                ProgressReportWeekly progressReportWeekly = new ProgressReportWeekly();
                transaction = manager.beginTransaction()
                        .replace(R.id.report1,progressReportWeekly);
                transaction.commit();


            }
        });
       confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               oneday = date.getText().toString();
               System.out.println(oneday);

               String url = URL.URL_GetGoal+ oneday+"/"+username;
               new getGoal().execute(url);

           }


       });

        return _view;
    }

    private void drawCharts() {
        mBardata = getBarData(4,100);
        showBarChart(mBarchat,mBardata);

        System.out.println(_steps);

        LineData mLineData = getLineData(4, 100);
        showChart(mLinechart, mLineData, Color.rgb(255,250,250));
    }


    protected class getGoal extends AsyncTask<String,Void,String > {

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }

        protected  void onPostExecute(String s){
            if (s.equals("[]")){
                _goal = Float.parseFloat("0");
                System.out.println(_goal);
                String url1 = URL.URL_GetTotalSteps+oneday+"/"+username;
                new getTotalSteps().execute(url1);
            }else {

                try {
                    JSONObject result =  new JSONArray(s).getJSONObject(0);
                    Goal = result.getString("calorieSetGoal");
                    _goal = Float.parseFloat(Goal);
                    System.out.println(_goal);
                    String url1 = URL.URL_GetTotalSteps+oneday+"/"+username;
                    new getTotalSteps().execute(url1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    protected class getTotalSteps extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected void onPostExecute(String s){
            if (s.equals("[]")){
                _steps = Float.parseFloat("0");
                String urlBurned = URL.URL_StepsBurned+username+"/"+oneday;
                new getStepsBurned().execute(urlBurned);
            }else {
                try {
                    JSONObject result = new JSONArray(s).getJSONObject(0);
                    Steps = result.getString("totalSteps");
                    _steps = Float.parseFloat(Steps);
                    String urlBurned = URL.URL_StepsBurned+username+"/"+oneday;
                    new getStepsBurned().execute(urlBurned);
                    System.out.println(_steps);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected class getStepsBurned extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected  void onPostExecute(String s){
           _burned = Float.parseFloat(s);
            System.out.println(_burned);
            String urlConsumed = URL.URL_Consumed+username+"/"+oneday;
            new getCalorieConsumed().execute(urlConsumed);
        }
    }

    protected class getCalorieConsumed extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return Netconnect.getContent(params[0]);
        }
        protected  void onPostExecute(String s){
            _consumed = Float.parseFloat(s);
            System.out.println(_consumed);
            drawCharts();
        }
    }

    private void showBarChart(BarChart barChart, BarData barData) {
        barChart.setDrawBorders(false);  ////是否在折线图上添加边框

        barChart.setDescription("");// 数据描述

        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("You need to provide data for the chart.");

        barChart.setDrawGridBackground(false); // 是否显示表格颜色
        barChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        barChart.setTouchEnabled(true); // 设置是否可以触摸

        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(true);// 是否可以缩放

        barChart.setPinchZoom(false);//

//      barChart.setBackgroundColor();// 设置背景

        barChart.setDrawBarShadow(true);

        barChart.setData(barData); // 设置数据

        Legend mLegend = barChart.getLegend(); // 设置比例图标示

        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色
//      X轴设定
//      XAxis xAxis = barChart.getXAxis();
//      xAxis.setPosition(XAxisPosition.BOTTOM);

        barChart.animateX(2500); // 立即执行的动画,x轴
    }

    private BarData getBarData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
//        for (int i = 0; i < count; i++) {
//            xValues.add("第" + (i + 1) + "季度");
//        }
        xValues.add("Steps");
        xValues.add("Goal");
        xValues.add("Consumed");
        xValues.add("Burned");



        System.out.println(_steps);
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
        yValues.add(new BarEntry(_steps,0));
        yValues.add(new BarEntry(_goal,1));
        yValues.add(new BarEntry(_consumed,2));
        yValues.add(new BarEntry(_burned,3));
//
//        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();
//
//        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * range/*100以内的随机数*/) + 3;
//            yValues.add(new BarEntry(value, i));
//        }

        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "BarChart");

        barDataSet.setColor(Color.rgb(114, 188, 223));

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet); // add the datasets

        BarData barData = new BarData(xValues,barDataSet);
        return barData;
    }


/**********************************************Line  Chart****************************/


    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
//        for (int i = 0; i < count; i++) {
//            // x轴显示的数据，这里默认使用数字下标显示
//            xValues.add("" + i);
//        }
        xValues.add("Steps");
        xValues.add("Goal");
        xValues.add("Consumed");
        xValues.add("Burned");
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
//        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * range) + 3;
//            yValues.add(new Entry(value, i));
//        }
        yValues.add(new Entry(_steps,0));
        yValues.add(new Entry(_goal,1));
        yValues.add(new Entry(_consumed,2));
        yValues.add(new Entry(_burned,3));
        LineDataSet lineDataSet = new LineDataSet(yValues, "LineChart" /*显示在比例图上*/);

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

    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框

        // no description text
        lineChart.setDescription("");// 数据描述
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(true);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放

        lineChart.setPinchZoom(false);//

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色

        lineChart.animateX(2500); // 立即执行的动画,x轴
    }

}
