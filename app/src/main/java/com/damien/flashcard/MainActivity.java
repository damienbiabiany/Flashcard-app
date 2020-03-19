package com.damien.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.graphics.Typeface;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // responses list
        /*
        ArrayList<String>  mylist = new ArrayList<String>();
        mylist.add("code");
        mylist.add("quiz");
        mylist.add("geeksforgeeks");

        Collections.shuffle(mylist);

        // SET QUESTION
        // random question
        TextView question = findViewById(R.id.QuestionTextView);
        question.setText("Test");


        */


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        int count = radioGroup.getChildCount();
        ArrayList<RadioButton> listOfRadioButtons = new ArrayList<RadioButton>();
        for (int i=0;i<count;i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                Log.i("child", "child"+ o);

                listOfRadioButtons.add((RadioButton)o);

                ((RadioButton)radioGroup.getChildAt(i)).setText("TEST" + i);
            }
        }

    }


    public void onRadioButtonClicked(View  view) {

        //require to import the RadioButton class
        RadioButton rb1 = findViewById(R.id.first);
        RadioButton rb2 = findViewById(R.id.second);
        RadioButton rb3 = findViewById(R.id.third);


        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.first:
                if (checked)
                    // Set font
                    rb1.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb2.setTypeface(null, Typeface.NORMAL);
                    rb3.setTypeface(null, Typeface.NORMAL);

                    String answer1 = rb1.getText().toString();
                    Log.i("answer1", answer1);
                break;
            case R.id.second:
                if (checked)
                    Log.i("second","second");
                    // Set font
                    rb1.setTypeface(null, Typeface.NORMAL);
                    rb2.setTypeface(null, Typeface.BOLD_ITALIC);
                    rb3.setTypeface(null, Typeface.NORMAL);

                    String answer2 = rb2.getText().toString();
                    Log.i("answer2", answer2);
                break;
            case R.id.third:
                if (checked)
                    Log.i("third","third");
                // Set font
                rb1.setTypeface(null, Typeface.NORMAL);
                rb2.setTypeface(null, Typeface.NORMAL);
                rb3.setTypeface(null, Typeface.BOLD_ITALIC);

                String answer3 = rb3.getText().toString();
                Log.i("answer3", answer3);
                break;

        }
    }
}
