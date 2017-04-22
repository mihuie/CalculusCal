package com.uwimona.group25.calculuscal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import com.github.mikephil.charting.charts.LineChart;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String mCurrentText; // stores text format for display
//    private String mFunctionF = ""; // holds function assigned to g
//    private String mFunctionG = ""; // holds function assigned to g

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LineChart lineChart = (LineChart)findViewById(R.id.chart);
        lineChart.setNoDataText("");

        List<Integer> myList = new ArrayList<>();
        myList.add(R.id.linLayout_1);
        myList.add(R.id.linLayout_2);
        myList.add(R.id.linLayout_3);
        myList.add(R.id.linLayout_4);
        myList.add(R.id.linLayout_5);
        myList.add(R.id.linLayout_6);
        myList.add(R.id.linLayout_7);
        myList.add(R.id.linLayout_8);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler();
        for(Integer i : myList){
            LinearLayout myLinearLayout = (LinearLayout) findViewById(i);
            int count = myLinearLayout.getChildCount();
            for(int j=0; j < count; j++){
                View v = myLinearLayout.getChildAt(j);
                if (v instanceof Button){
                    v.setOnClickListener(buttonClickHandler);
                }
            }
        }

        ButtonLongClickHandler buttonLongClickHandler = new ButtonLongClickHandler();
        final Button buttonC = (Button) findViewById(R.id.btn_clear);
        buttonC.setOnLongClickListener(buttonLongClickHandler);

        final Button buttonXY = (Button) findViewById(R.id.btn_plot);
        buttonXY.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), GraphActivity.class));
            }
        });

//        final Button buttonF = (Button) findViewById(R.id.btn_f);
//        buttonF.setOnLongClickListener(buttonLongClickHandler);
//
//        final Button buttonG = (Button) findViewById(R.id.btn_g);
//        buttonG.setOnLongClickListener(buttonLongClickHandler);

        updateCurrentText(getString(R.string.text_0));
        updateWebView(getCurrentText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            return true;
        }
        else if (id == R.id.action_help) {
            startActivity(new Intent(getApplicationContext(), HelpActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateWebView(String text){
        WebView webView = (WebView)findViewById(R.id.webView1);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final String path="file:///android_asset/";
        String js = "<html><head>"
                + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
                + "<style type='text/css'> fmath.ma-block { display: block; text-align: right; "
                + "text-indent: 0; padding-top: 0.5cm; page-break-inside: avoid } </style>"
                + "<style type='text/css'> html {font-size: 130%;} </style>"
                + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
                + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
                + "</head><body>"
                + "<script>var s = '$$"+text+"$$';M.parseMath(s);document.write(s);</script></body>";
        webView.loadDataWithBaseURL(path, js,  "text/html",  "UTF-8", null);

        // disabling longClick
        webView.setLongClickable(true);
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        //zoom
//        webView.setInitialScale(120);
    }

    private void updateWebViewResult(String text){
        WebView webView = (WebView)findViewById(R.id.webViewResult);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final String path="file:///android_asset/";
        String js = "<html><head>"
                + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
                + "<style type='text/css'> html {font-size: 130%;} </style>"
                + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
                + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
                + "</head><body>"
                + "<script>var s = '$$"+text+"$$';M.parseMath(s);document.write(s);</script></body>";
        webView.loadDataWithBaseURL(path, js,  "text/html",  "UTF-8", null);

        // disabling longClick
        webView.setLongClickable(true);
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    private void updateCurrentText(String str){
        if (str.equals(" ") || str.equals("") || str.isEmpty()){
            mCurrentText = getString(R.string.text_0);
        } else { mCurrentText = str; }
    }

    private String getCurrentText(){
        return mCurrentText;
    }

    private void evaluateInput(){
        updateWebViewResult(getCurrentText());
        updateWebView(getString(R.string.text_0));
    }

    private void addOperation(String str){
        String text = " ";
        if ("0".equals(getCurrentText())){
            switch (str) {
                case " ":
                    break;
                default:
                    text = str;
                    break;
            }
        }
        else{
            text = mCurrentText + str;
        }
        updateWebView(text);
        updateCurrentText(text);
    }

    private void clearWebView(){
        updateWebView(getString(R.string.text_0));
        updateWebViewResult("");
        updateCurrentText(getString(R.string.text_0));
    }

    private void backspace() {
        String str = getCurrentText();

        if (str != null && str.length() > 0) {
            for(String operand : Utils.getOperands()){
                if(str.endsWith(operand)) {
                    str = str.substring(0, str.length() - operand.length());
                    updateCurrentText(str);
                    updateWebView(getCurrentText());
                    return;
                }
            }
            str = str.substring(0, str.length()-1);
            updateCurrentText(str);
            updateWebView(getCurrentText());
        }
    }

    private void handleSpace(){
        int state = 0;
        for (int i = getCurrentText().length() - 1; i >= 0; --i) {
            if (getCurrentText().charAt(i) == '{') {
                addOperation("}");
                state = 1;
                break;
            } else if (getCurrentText().charAt(i) == '/') {
                addOperation(" ");
                state = 1;
                break;
            } else if (getCurrentText().charAt(i) == '^') {
                addOperation(" ");
                state = 1;
                break;
            }
//            } else if (mCurrentText.charAt(i) == '0'){
//                state = 1;
//                break;
//            }
        }

        if (state == 0){
            addOperation(" ");
        }
    }

//    private void calFunctions(String func){
//
//        if(func.equals("mFunctionF")){
//            if (func.isEmpty()){
//                mFunctionF = mCurrentText;
//                updateWebView(mCurrentText);
//            } else {
//                updateWebView(mFunctionF);
//            }
//        } else if (func.equals("mFunctionG")){
//            if (func.isEmpty()){
//                mFunctionG = mCurrentText;
//                updateWebView(mCurrentText);
//            } else {
//                updateWebView(mFunctionG);
//            }
//        }
//    }

    private class ButtonLongClickHandler implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v){
            int id = v.getId();
            switch (id) {
                case R.id.btn_clear:
                    clearWebView();
                    break;
                case R.id.btn_f:
//                    calFunctions("mFunctionF");
                    break;
                case R.id.btn_g:
//                    calFunctions("mFunctionG");
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private class ButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_0:
                    addOperation("0");
                    break;
                case R.id.btn_1:
                    addOperation("1");
                    break;
                case R.id.btn_2:
                    addOperation("2");
                    break;
                case R.id.btn_3:
                    addOperation("3");
                    break;
                case R.id.btn_4:
                    addOperation("4");
                    break;
                case R.id.btn_5:
                    addOperation("5");
                    break;
                case R.id.btn_6:
                    addOperation("6");
                    break;
                case R.id.btn_7:
                    addOperation("7");
                    break;
                case R.id.btn_8:
                    addOperation("8");
                    break;
                case R.id.btn_9:
                    addOperation("9");
                    break;
                case R.id.btn_limit:
                    addOperation(Utils.LIMIT);
                    break;
                case R.id.btn_cos:
                    addOperation(Utils.COS);
                    break;
                case R.id.btn_sin:
                    addOperation(Utils.SIN);
                    break;
                case R.id.btn_tan:
                    addOperation(Utils.TAN);
                    break;
                case R.id.btn_decimal:
                    addOperation(Utils.DECIMAL);
                    break;
                case R.id.btn_divide:
                    addOperation(Utils.DIVIDE);
                    break;
                case R.id.btn_exponent:
                    addOperation(Utils.EXPONENT);
                    break;
                case R.id.btn_leftBracket:
                    addOperation(Utils.LEFTBRACKET);
                    break;
                case R.id.btn_rightBracket:
                    addOperation(Utils.RIGHTBRACKET);
                    break;
                case R.id.btn_ln:
                    addOperation(Utils.LN);
                    break;
                case R.id.btn_integral:
                    addOperation(Utils.INTEGRAL);
                    break;
                case R.id.btn_log:
                    addOperation(Utils.LOG);
                    break;
                case R.id.btn_minus:
                    addOperation(Utils.MINUS);
                    break;
                case R.id.btn_plus:
                    addOperation(Utils.ADD);
                    break;
                case R.id.btn_multiply:
                    addOperation(Utils.MULTIPLY);
                    break;
                case R.id.btn_pi:
                    addOperation(Utils.PI);
                    break;
                case R.id.btn_power:
                    addOperation(Utils.POWER);
                    break;
                case R.id.btn_root:
                    addOperation(Utils.ROOT);
                    break;
                case R.id.btn_towards:
                    addOperation(Utils.TOWARDS);
                    break;
                case R.id.btn_equals:
                    addOperation(Utils.EQUAL);
                    break;
                case R.id.btn_f:
                    addOperation(Utils.FUNCTIONF);
                    break;
                case R.id.btn_x:
                    addOperation(Utils.VARIABLEX);
                    break;
                case R.id.btn_y:
                    addOperation(Utils.VARIABLEY);
                    break;
                case R.id.btn_g:
                    addOperation(Utils.FUNCTIONG);
                    break;
                case R.id.btn_diff:
                    addOperation(Utils.DIFF);
                    break;
                case R.id.btn_infinity:
                    addOperation(Utils.INFINITY);
                    break;
                case R.id.btn_evaluate:
                    evaluateInput();
                    break;
                case R.id.btn_space:
                    handleSpace();
                    break;
                case R.id.btn_clear:
                    backspace();
                    break;
                default:
                    break;
            }
        }
    }
}
