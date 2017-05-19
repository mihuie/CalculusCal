package com.uwimona.group25.calculuscal;

/**
 * Created by michael on 5/17/17.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.uwimona.group25.calculuscal.semantics.Environment;
import com.uwimona.group25.calculuscal.semantics.Evaluator;
import com.uwimona.group25.calculuscal.syntax.SmplLexer;
import com.uwimona.group25.calculuscal.syntax.SmplParser;
import com.uwimona.group25.calculuscal.syntax.SmplProgram;
import com.uwimona.group25.calculuscal.sys.SmplException;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ScriptActivity extends AppCompatActivity {

    private EditText script_input;
    private TextView script_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_script);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.button_go);
        fab.setVisibility(View.VISIBLE);

        setupViews();
    }

    private void setupViews(){
        script_input = (EditText) findViewById(R.id.script_input);
        script_result = (TextView) findViewById(R.id.script_result);

        script_input.setVisibility(View.VISIBLE);
    }

    public void parseEvalShow(View v){

        String input = script_input.getText().toString();

        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes(Charset.defaultCharset()));
        InputStreamReader reader = new InputStreamReader(bais);

        SmplParser parser;
        SmplProgram program = null;
        Evaluator interp = new Evaluator();


        try {
            parser = new SmplParser(new SmplLexer(reader));
            program = (SmplProgram) parser.parse().value;
        } catch (Exception e) {
            script_result.setText("Parse Error: " + e.getMessage());
        }

        if(program != null)
            try {
                Object result = program.visit(interp, new Environment());
                script_result.setText(result.toString());
            } catch (SmplException e){
                script_result.setText(e.getMessage());
            }
    }
}
