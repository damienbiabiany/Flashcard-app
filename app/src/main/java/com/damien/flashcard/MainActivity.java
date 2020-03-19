package com.damien.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.graphics.Typeface;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -------- Map Questions, Responses -------
        HashMap<String, String> questionAnswerMap = new HashMap<String, String>();
        questionAnswerMap.put("Quel est le nom de cet animal ?", "South;San;Jose");
        questionAnswerMap.put("Quel l'origine de cet animal ?", "North;Bla;Jose");
        questionAnswerMap.put("Combien d'enfants ?", "Mountain;View;dsddssd");

        // Get a random entry from the questionImageMap.
        Object[] crunchifyKeys = questionAnswerMap.keySet().toArray();
        Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
        // System.out.println("************ Random Value ************ \n" + key + " :: " + questionAnswerMap.get(key));
        System.out.println("************ Random Question ************ \n" + key);
        System.out.println("************ Random Response ************ \n" + questionAnswerMap.get(key));
        //List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(questionAnswerMap.entrySet());

        // -------- Map Questions, Images ------
        HashMap<String, String> questionImageMap = new HashMap<String, String>();
        questionImageMap.put("Quel est le nom de cet animal ?", "@drawable/difficult_okapi");
        questionImageMap.put("Quel l'origine de cet animal ?", "@drawable/difficult_pudu");
        questionImageMap.put("Combien d'enfants ?", "@drawable/easy_giraffe");

        // Get a random entry from the questionImageMap.
        Object[] crunchifyKeysImage = questionAnswerMap.keySet().toArray();
        // System.out.println("************ Random Value ************ \n" + key + " :: " + questionAnswerMap.get(key));
        System.out.println("************ Random Image ************ \n" + questionImageMap.get(key));


        // Set Random Question
        TextView question = findViewById(R.id.QuestionTextView);
        question.setText(key.toString());

        // Set Random ImageView from @drawable
        // String uri = "@drawable/difficult_okapi";  // where myresource (without the extension) is the file
        String uri = questionImageMap.get(key);
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        ImageView imageView    = (ImageView)findViewById(R.id.imageView);
        Drawable res           = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

        // Random response for radiobutton text
        List<String> myList= new ArrayList<String>();

        // Convert string to Array
        String str[] = questionAnswerMap.get(key).split(";");
        List<String> mylist = new ArrayList<String>();

        mylist = Arrays.asList(str);
        System.out.println("************  al ************ \n" + mylist);

        Collections.shuffle(mylist);


        // SET text to each radiobutton in radiogroup
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int count = radioGroup.getChildCount();
        for (int i=0;i<count;i++) {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) {
                // Set the text of radioButton
                ((RadioButton)radioGroup.getChildAt(i)).setText("" + mylist.get(i));
            }
        }


    }

    //validate response
    public void onSubmitAnswerClicked(View  view) {
        // Good answers by question Map

        // retrieve the radio button checked

        // Bad or Wrong
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
