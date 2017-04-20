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
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String mCurrentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler();
        List<Integer> myList = new ArrayList<>();
        myList.add(R.id.linLayout_1);
        myList.add(R.id.linLayout_2);
        myList.add(R.id.linLayout_3);
        myList.add(R.id.linLayout_4);
        myList.add(R.id.linLayout_5);
        myList.add(R.id.linLayout_6);
        myList.add(R.id.linLayout_7);
        myList.add(R.id.linLayout_8);

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

        updateWebView("0");
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

    private String getTextViewProblem() {
        final TextView textViewOutputScreen = (TextView)findViewById(R.id.textView_problem);
        return (String)textViewOutputScreen.getText();
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
                + "<script src='"+path+"jquery-1.4.3.min.js'></script>"
                + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
                + "</head><body>"
                + "<script>var s = '$$"+text+"$$';M.parseMath(s);document.write(s);</script></body>";
        webView.loadDataWithBaseURL(path, js,  "text/html",  "UTF-8", null);
    }

    private void addDigit(int number){
        final TextView textViewOutputScreen = (TextView)findViewById(R.id.textView_problem);
        final String current = getTextViewProblem();
        String text;
        if ("0".equals(current)){
            text = "" + number;
        }
        else{
            text = getTextViewProblem() + number;
        }
        textViewOutputScreen.setText(text);
        mCurrentText = text;
        updateWebView(text);
    }

    private void addOperation(String string){
        final TextView textViewOutputScreen = (TextView)findViewById(R.id.textView_problem);
        final String current = getTextViewProblem();
        String text;
        if ("0".equals(current)){
            text = string;
        }
        else{
            text = getTextViewProblem() + string;
        }

        textViewOutputScreen.setText(text);
        updateWebView(text);
        mCurrentText = text;
    }

    private void clearWebView(){
        updateWebView("0");
        final TextView textViewOutputScreen = (TextView)findViewById(R.id.textView_problem);
        textViewOutputScreen.setText("0");
        mCurrentText = "0";
    }

    private void handleSpace(){
        int state = 0;
        for (int i = mCurrentText.length() - 1; i >= 0; --i) {
            if (mCurrentText.charAt(i) == '{') {
                addOperation("}");
                state = 1;
                break;
            } else if (mCurrentText.charAt(i) == '/') {
                addOperation(" ");
                state = 1;
                break;
            } else if (mCurrentText.charAt(i) == '^'){
                addOperation(" ");
                state = 1;
                break;
            }
        }

        if (state == 0){
            addOperation(" ");
        }
    }

    private class ButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.btn_0:
                    addDigit(0);
                    break;
                case R.id.btn_1:
                    addDigit(1);
                    break;
                case R.id.btn_2:
                    addDigit(2);
                    break;
                case R.id.btn_3:
                    addDigit(3);
                    break;
                case R.id.btn_4:
                    addDigit(4);
                    break;
                case R.id.btn_5:
                    addDigit(5);
                    break;
                case R.id.btn_6:
                    addDigit(6);
                    break;
                case R.id.btn_7:
                    addDigit(7);
                    break;
                case R.id.btn_8:
                    addDigit(8);
                    break;
                case R.id.btn_9:
                    addDigit(9);
                    break;
                case R.id.btn_limit:
                    addOperation(" `lim\u2199{");
                    break;
                case R.id.btn_cos:
                    addOperation("`cos ");
                    break;
                case R.id.btn_sin:
                    addOperation("`sin ");
                    break;
                case R.id.btn_tan:
                    addOperation("`tan ");
                    break;
                case R.id.btn_decimal:
                    addOperation(".");
                    break;
                case R.id.btn_divide:
                    addOperation("/");
                    break;
                case R.id.btn_exponent:
                    addOperation("e");
                    break;
                case R.id.btn_leftBracket:
                    addOperation("(");
                    break;
                case R.id.btn_rightBracket:
                    addOperation(")");
                    break;
                case R.id.btn_ln:
                    addOperation("`ln");
                    break;
                case R.id.btn_integral:
                    addOperation("\u222B ");
                    break;
                case R.id.btn_log:
                    addOperation("`log_");
                    break;
                case R.id.btn_minus:
                    addOperation("-");
                    break;
                case R.id.btn_plus:
                    addOperation("+");
                    break;
                case R.id.btn_multiply:
                    addOperation("*");
                    break;
                case R.id.btn_pi:
                    addOperation("\u03C0 ");
                    break;
                case R.id.btn_power:
                    addOperation("^");
                    break;
                case R.id.btn_root:
                    addOperation("\u221A{");
                    break;
                case R.id.btn_towards:
                    addOperation("\u2192");
                    break;
                case R.id.btn_equals:
                    addOperation("=");
                    break;
                case R.id.btn_f:
                    addOperation("f");
                    break;
                case R.id.btn_x:
                    addOperation("x");
                    break;
                case R.id.btn_y:
                    addOperation("y");
                    break;
                case R.id.btn_g:
                    addOperation("g");
                    break;
                case R.id.btn_diff:
                    addOperation("\u2202");
                    break;
                case R.id.btn_infinity:
                    addOperation("\u221E");
                    break;
                case R.id.btn_space:
                    handleSpace();
                    break;
                case R.id.btn_clear:
                    clearWebView();
                    break;
                default:
                    break;
            }
        }
    }
}
