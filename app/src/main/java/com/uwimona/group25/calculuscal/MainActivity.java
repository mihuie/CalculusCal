package com.uwimona.group25.calculuscal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
            return true;
        }
        else if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class ButtonClickHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView textViewOutputScreen = (TextView)findViewById(R.id.textView_problem);
//            textViewOutputScreen.setText(textViewOutputScreen.getText().toString() + ((Button) v).getText());
            int id = v.getId();
            switch (id) {
                case R.id.btn_0:
                    textViewOutputScreen.setText(textViewOutputScreen.getText().toString() + ((Button) v).getText());
                    break;
                case R.id.btn_1:
                    textViewOutputScreen.setText(textViewOutputScreen.getText().toString() + ((Button) v).getText());
                default:
                    break;
            }

        }
    }


}
