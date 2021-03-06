package com.damien.flashcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.graphics.Typeface;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public int countClick            = 0;
    public int questionIndex         = 1;
    public int totalNumberOfQuestion = 2;
    public int totalGoodAnswers      = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the initial index of the question
        this.setTitle("AnimalCard - 1/2");

        // -------- Map Questions, Responses -------
        HashMap<String, String> questionAnswerMap = new HashMap<String, String>();
        questionAnswerMap.put("Quel est le nom de cet animal ?", "okapi;pudu;cerf");
        questionAnswerMap.put("Quel est le pays d'origine de cet animal ?", "Argentine et Chili;Inde;Canada");
        questionAnswerMap.put("Nombre de petits par portée ?", "1;2;4");

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
        questionImageMap.put("Quel est le pays d'origine de cet animal ?", "@drawable/difficult_pudu");
        questionImageMap.put("Nombre de petits par portée ?", "@drawable/easy_giraffe");

        // Get a random entry from the questionImageMap.
        Object[] crunchifyKeysImage = questionAnswerMap.keySet().toArray();
        // System.out.println("************ Random Value ************ \n" + key + " :: " + questionAnswerMap.get(key));
        System.out.println("************ Random Image ************ \n" + questionImageMap.get(key));

        // Set Random Question
        TextView question = findViewById(R.id.QuestionTextView);
        question.setText(key.toString());

        // Set Random ImageView from @drawable
        String uri        = questionImageMap.get(key);
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

    public void GetRandomQuestion(View  view){


    }

    //Validate the answer response
    public void onSubmitAnswerClicked(View  view) {

        Button submitButton = findViewById(R.id.SubmitButton);
        System.out.println("value =" + submitButton.getText());

        if(this.questionIndex <= this.totalNumberOfQuestion )  {
            this.setTitle("AnimalCard - " + this.questionIndex + "/2");

            // Change the text of submit button dynamically
            if (submitButton.getText().equals("Question suivante")){
                submitButton.setText("Valider la réponse");


            // RECREATE THE ACTIVITY
            } else {
                this.questionIndex++;
                submitButton.setText("Question suivante");



            }


            // Good answers by question Map
            // -------- Map Questions, Responses -------
            HashMap<String, String> questionAnswerMap = new HashMap<String, String>();
            questionAnswerMap.put("Quel est le nom de cet animal ?", "okapi");
            questionAnswerMap.put("Quel est le pays d'origine de cet animal ?", "Argentine et Chili");
            questionAnswerMap.put("Nombre de petits par portée ?", "1");

            // get the  current question
            TextView currentQuestion = findViewById(R.id.QuestionTextView);
            String currentQuestionValue = currentQuestion.getText().toString();

            // get the correct answer associated to the question
            String goodAnswer = questionAnswerMap.get(currentQuestionValue);
            Log.i("goodAnswer =", goodAnswer);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

            // get the current radio button checked when we clicked onSubmit Button
            // get selected radio button from radioGroup
            int selectedId = radioGroup.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            RadioButton radioButtonChecked = (RadioButton) findViewById(selectedId);
            Log.i("radioButtonChecked", radioButtonChecked.getText().toString());

            // Bad or Wrong
            TextView goodOrBad = findViewById(R.id.GoodBadAnswerTextView);
            TextView answerWas = findViewById(R.id.TheAnswerWasTextView);

            System.out.println((goodAnswer.equals(radioButtonChecked.getText())));
            // Log.i("length",""+ ( goodAnswer.length() ));
            // Log.i("length",""+ ( radioButtonChecked.length() ));
            // Log.i("test"  ,""+ ( goodAnswer.getClass()));
            // Log.i("test"  ,""+ ( radioButtonChecked.getText().getClass()) );

            if (goodAnswer.equals(radioButtonChecked.getText().toString())
            ) {
                goodOrBad.setText("Bonne réponse");
                goodOrBad.setTypeface(null, Typeface.BOLD);
                goodOrBad.setTextColor(Color.GREEN);
                answerWas.setText("La bonne réponse était : " + goodAnswer);
                this.totalGoodAnswers++;

            }
            else {
                goodOrBad.setText("Mauvaise réponse");
                goodOrBad.setTypeface(null, Typeface.BOLD);
                goodOrBad.setTextColor(Color.RED);
                answerWas.setText("La bonne réponse était : " + goodAnswer);
            }

        }

        if(this.questionIndex > this.totalNumberOfQuestion )  {
            System.out.println("End of quiz");
            // Good answers
            int ScorePercentage = this.totalGoodAnswers*100;

            Log.i("SCORE",""+ ScorePercentage + "%");
            // Change the text of submit button dynamically
            submitButton.setText("Voir les Résultats");
        }

    }

    public void onImageClicked(View  view) {

        this.countClick++;

        // ----- Set the imageView size on click -----
        if (countClick  == 1){
            ImageView imageView2    = (ImageView)findViewById(R.id.imageView);
            System.out.println("width  =" + imageView2.getWidth() );
            System.out.println("height =" + imageView2.getHeight() );

            int newHeight = imageView2.getHeight() + 100; // New height in pixels
            int newWidth  = imageView2.getWidth() + 180; // New width in pixels

            imageView2.requestLayout();
            /* getLayoutParams() Get the LayoutParams associated with this view.*/

            // Apply the new height for ImageView programmatically
            imageView2.getLayoutParams().height = newHeight;

            // Apply the new width for ImageView programmatically
            imageView2.getLayoutParams().width = newWidth;

            // Set the scale type for ImageView image scaling
            imageView2.setScaleType(ImageView.ScaleType.FIT_XY);

            System.out.println("The number is even.");
            System.out.println("countClick =" + this.countClick);

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
