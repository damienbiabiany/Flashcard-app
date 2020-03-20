package com.damien.flashcard;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuestionAnswer implements Parcelable {


    protected QuestionAnswer(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionAnswer> CREATOR = new Creator<QuestionAnswer>() {
        @Override
        public QuestionAnswer createFromParcel(Parcel in) {
            return new QuestionAnswer(in);
        }

        @Override
        public QuestionAnswer[] newArray(int size) {
            return new QuestionAnswer[size];
        }
    };


}

