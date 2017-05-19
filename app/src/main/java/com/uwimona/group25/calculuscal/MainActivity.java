package com.uwimona.group25.calculuscal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.github.mikephil.charting.charts.LineChart;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.uwimona.group25.calculuscal.semantics.Environment;
import com.uwimona.group25.calculuscal.semantics.Evaluator;
import com.uwimona.group25.calculuscal.syntax.SmplLexer;
import com.uwimona.group25.calculuscal.syntax.SmplParser;
import com.uwimona.group25.calculuscal.syntax.SmplProgram;
import com.uwimona.group25.calculuscal.sys.SmplException;

public class MainActivity extends AppCompatActivity {
    private String mCurrentText; // stores text format for display
    private String parserString = ""; // stores text format for parser
    private final String SPACE = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_go);
        fab.setVisibility(View.GONE);

        EditText script_input = (EditText) findViewById(R.id.script_input);
        script_input.setVisibility(View.GONE);


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
        else if (id == R.id.action_script) {
            startActivity(new Intent(getApplicationContext(), ScriptActivity.class));
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
                + "<style type='text/css'> html {font-size: 120%;} </style>"
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

    private void updateWebViewResult(String text){
//        Log.v("result", text);
        WebView webView = (WebView)findViewById(R.id.webViewResult);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        final String path="file:///android_asset/";
        String js = "<html><head>"
                + "<link rel='stylesheet' href='"+path+"jqmath-0.4.3.css'>"
                + "<style type='text/css'> html {font-size: 120%;} </style>"
                + "<script src='" +path+"jquery-1.4.3.min.js'></script>"
                + "<script src='"+path+"jqmath-etc-0.4.6.min.js'></script>"
                + "</head><body>"
                + "<script>var s = '$$" + text + "$$';M.parseMath(s);document.write(s);</script></body>";
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
        } else {
            mCurrentText = str;
        }
    }

    private void updateParserString(String str){
           parserString = str;
    }

    private String getCurrentText(){
        return mCurrentText;
    }

    private String getParserString(){
        return parserString;
    }

    private void clearAll(){
        updateWebView(getString(R.string.text_0));
        updateWebViewResult("");
        updateCurrentText(getString(R.string.text_0));
        updateParserString("");
    }

    private void backspace() {
//        String str = getCurrentText();
//
//        if (str != null && str.length() > 0) {
//            for(String operand : Utils.getOperands()){
//                if(str.endsWith(operand)) {
//                    str = str.substring(0, str.length() - operand.length());
//                    updateCurrentText(str);
//                    updateWebView(getCurrentText());
//                    return;
//                }
//            }
//            str = str.substring(0, str.length()-1);
//            updateCurrentText(str);
//            updateWebView(getCurrentText());
//        }
        clearAll();
    }

    private void handleSPKey(){
        int state = 0;

        if ((getCurrentText().endsWith(getString(R.string.text_0)) && (getCurrentText().length() == 1)) ||
                getCurrentText().endsWith(Utils.ROOT)) {
            state = 1;
        } else if (getCurrentText().startsWith(Utils.LIMIT) &&
                !(getCurrentText().contains("}"))){
            addOperation("}", "]");
            state = 1;
        } else {
            for (int i = getCurrentText().length() - 1; i >= 0; --i) { //&& !getCurrentText().endsWith("}")
                if ((getCurrentText().charAt(i) == '{') ) {
                    addOperation("}", "]");
                    state = 1;
                    break;
                } else if (getCurrentText().charAt(i) == '/') {
                    addOperation(Utils.SPACE, SPACE);
                    state = 1;
                    break;
                } else if (getCurrentText().charAt(i) == '^') {
                    addOperation(Utils.SPACE, SPACE);
                    state = 1;
                    break;
                }
            }
        }

        if ((state == 0) && (!getCurrentText().endsWith(Utils.SPACE))) addOperation(Utils.SPACE, SPACE);
    }

    private void addOperation(String str, String strP) {
        String text = " ";
        String parserText;

        if ("0".equals(getCurrentText())) {
            switch (str) {
                case " ":
                    break;
                case Utils.DECIMAL:
                    text = getCurrentText() + str;
                case Utils.POWER:
                    break;
                case Utils.ADD:
                    break;
                case Utils.MULTIPLY:
                    break;
                case Utils.DIVIDE:
                    break;
                case Utils.TOWARDS:
                    break;
                default:
                    text = str;
                    break;
            }
        } else if (getCurrentText().contains(Utils.EQUAL) && (str.equals(Utils.EQUAL))) {
            text = getCurrentText();
        } else if (getCurrentText().endsWith(Utils.LIMIT) && ((Utils.getOperands().contains(str)))) {
            text = getCurrentText();
        } else if (getCurrentText().endsWith(Utils.DECIMAL) && (Utils.getBasicOperators().contains(str))) {
            text = str.equals(Utils.EQUAL) ? getCurrentText() + "0" + str : getCurrentText();
        } else if (getCurrentText().endsWith(Utils.LEFTBRACKET) && (Utils.getBasicOperators().contains(str))) {
            text = str.equals(Utils.MINUS) ? getCurrentText() + str : getCurrentText();
        } else if (Utils.isBasicOperator(str)) {
            for (String operator : Utils.getBasicOperators()) {
                if (getCurrentText().endsWith(operator) && !str.equals(Utils.MINUS)) {
                    text = getCurrentText();
                    break;
                } else {
                    text = getCurrentText().endsWith(operator + Utils.MINUS) ? getCurrentText() : getCurrentText() + str;
                }
            }
        } else if (Utils.isAdvancedOperator(str)) {
            for (String operator : Utils.getAdvancedOperators()) {
                if (getCurrentText().endsWith(operator)) {
                    text = getCurrentText();
                    break;
                } else {
                    text = getCurrentText() + str;
                }
            }
        } else if (Utils.getNoDuplicates().contains(str)){
            for (String i : Utils.getNoDuplicates()) {
                if (getCurrentText().endsWith(i) && str.equals(i)) {
                    text = getCurrentText();
                    break;
                } else {
                    text = getCurrentText() + str;
                }
            }
        } else {
            text = getCurrentText() + str;
        }

        if (strP.equals("x") && Character.isDigit(getParserString().charAt(getParserString().length()-1)))
            strP = SPACE + "*" + SPACE + "x";

        parserText = ((text.length() >= getCurrentText().length()) || !getCurrentText().equals("0")) ? getParserString() + strP : getParserString();

        updateWebView(text);
        updateCurrentText(text);
        updateParserString(parserText);
    }

    private void parserEvaluateString(){

        String input = getParserString();
        String parserStringCondition;

        if (getParserString().startsWith("diff(") || getParserString().startsWith("INT(")) {
            input =  input + " : x);";
        } else if (getParserString().startsWith("TAN(") || getParserString().startsWith("COS(") ||
                getParserString().startsWith("SIN(") || getParserString().startsWith("LN(")){
            input =  input + " );";
        } else if (getParserString().startsWith("limit([")) {
            String str = getParserString();
            try {
                parserStringCondition = str.substring(str.indexOf("[") + 1, str.indexOf("]"));
                input = "limit("+str.substring(str.indexOf("]") + 1, str.length())+SPACE
                        +"as"+SPACE+parserStringCondition+");";
            } catch (Exception e) {
                updateWebViewResult(e.getMessage());
            }
        } else if (getParserString().startsWith("sqRoot(")) {
            String str = getParserString();
            try {
                input = str.substring(str.indexOf("("), str.length()) + ") ^ (1/2);";
            } catch (Exception e) {
                updateWebViewResult(e.getMessage());
            }
        } else {
            input = input + ";";
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes(Charset.defaultCharset()));
        InputStreamReader reader = new InputStreamReader(bais);

        SmplParser parser;
        SmplProgram program = null;
        Evaluator interp = new Evaluator();

//        updateWebView(input);

        try {
            parser = new SmplParser(new SmplLexer(reader));
            program = (SmplProgram) parser.parse().value;
        } catch (Exception e) {
            updateWebViewResult("E" + e.getMessage());
        }

        String testResult = "";
        if(program != null)
            try {
                Object result = program.visit(interp, new Environment());
//                Log.v("result", result.toString());
                if (result.toString().contains(" * x")){
                    testResult = result.toString().replace(" * x", "x");
                } else {
                    testResult = result.toString();
                }

                updateWebViewResult(testResult);
            } catch (SmplException e){
                updateWebViewResult("E");
            }

        Toast.makeText(getApplicationContext(), testResult, Toast.LENGTH_SHORT).show();

        updateParserString("");
        updateCurrentText("");

    }

    private class ButtonLongClickHandler implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v){
            int id = v.getId();
            switch (id) {
                case R.id.btn_clear:
                    clearAll();
                    break;
                case R.id.btn_f:
//                    calFunctions("mFunctionF");
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
                    addOperation("0", "0");
                    break;
                case R.id.btn_1:
                    addOperation("1", "1");
                    break;
                case R.id.btn_2:
                    addOperation("2", "2");
                    break;
                case R.id.btn_3:
                    addOperation("3", "3");
                    break;
                case R.id.btn_4:
                    addOperation("4", "4");
                    break;
                case R.id.btn_5:
                    addOperation("5", "5");
                    break;
                case R.id.btn_6:
                    addOperation("6", "6");
                    break;
                case R.id.btn_7:
                    addOperation("7", "7");
                    break;
                case R.id.btn_8:
                    addOperation("8", "8");
                    break;
                case R.id.btn_9:
                    addOperation("9", "9");
                    break;
                case R.id.btn_minus:
                    addOperation(Utils.MINUS, SPACE+"-"+SPACE+"");
                    break;
                case R.id.btn_plus:
                    addOperation(Utils.ADD, SPACE+"+"+SPACE+"");
                    break;
                case R.id.btn_multiply:
                    addOperation(Utils.MULTIPLY, SPACE+"*"+SPACE+"");
                    break;
                case R.id.btn_divide:
                    addOperation(Utils.DIVIDE, SPACE+"/"+SPACE+"");
                    break;
                case R.id.btn_limit:
                    addOperation(Utils.LIMIT, "limit([");
                    break;
                case R.id.btn_cos:
                    addOperation(Utils.COS, "COS(");
                    break;
                case R.id.btn_sin:
                    addOperation(Utils.SIN, "SIN(");
                    break;
                case R.id.btn_tan:
                    addOperation(Utils.TAN, "TAN(");
                    break;
                case R.id.btn_ln:
                    addOperation(Utils.LN, "LN(");
                    break;
                case R.id.btn_integral:
                    addOperation(Utils.INTEGRAL, "INT(");
                    break;
                case R.id.btn_log:
                    addOperation(Utils.LOG, "LOG(");
                    break;
                case R.id.btn_pi:
                    addOperation(Utils.PI, "PI");
                    break;
                case R.id.btn_decimal:
                    addOperation(Utils.DECIMAL, ".");
                    break;
                case R.id.btn_exponent:
                    addOperation(Utils.EXPONENT, "");
                    break;
                case R.id.btn_leftBracket:
                    addOperation(Utils.LEFTBRACKET, ")");
                    break;
                case R.id.btn_rightBracket:
                    addOperation(Utils.RIGHTBRACKET, "(");
                    break;
                case R.id.btn_power:
                    addOperation(Utils.POWER, SPACE+"^"+SPACE+"");
                    break;
                case R.id.btn_root:
                    addOperation(Utils.ROOT, "sqRoot(");
                    break;
                case R.id.btn_towards:
                    addOperation(Utils.TOWARDS, SPACE+"->"+SPACE+"");
                    break;
                case R.id.btn_equals:
                    addOperation(Utils.EQUAL, "=");
                    break;
                case R.id.btn_f:
                    addOperation(Utils.FUNCTIONF, "x");
                    break;
                case R.id.btn_x:
                    addOperation(Utils.VARIABLEX, "x");
                    break;
                case R.id.btn_y:
                    addOperation(Utils.VARIABLEY, "y");
                    break;
                case R.id.btn_factorial:
                    addOperation(Utils.FACTORIAL, "!");
                    break;
                case R.id.btn_diff:
                    addOperation(Utils.DIFF, "diff(");
                    break;
                case R.id.btn_infinity:
                    addOperation(Utils.INFINITY, "infinity");
                    break;
                case R.id.btn_evaluate:
                    parserEvaluateString();
                    break;
                case R.id.btn_space:
                    handleSPKey();
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
