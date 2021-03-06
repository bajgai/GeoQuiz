package com.niranjanbajgai.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;


public class QuizActivity extends Activity {
    private static final String INDEX = "index";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.niranjanbajgai.geoquiz.answer_is_true";


    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private Button mCheatButton;


    private TextView mQuestionTextView;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_america, true),
            new TrueFalse(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(INDEX, 0);
        }

//-----------Questions TextView--------------------------------------------
        mQuestionTextView= (TextView)findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(1);

            }
        });

//----------------TruButton---------------------------------------
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }
        });

//----------------------False Button-------------------------------
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }

        });

        mCheatButton = (Button)findViewById(R.id.cheatButton);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                i.putExtra(EXTRA_ANSWER_IS_TRUE, mQuestionBank[mCurrentIndex].isTrueQuestion());
                startActivityForResult(i, 0);

            }
        });

//------------------------ Next Button ----------------------------
        mNextButton = (ImageButton)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(1);

            }
        });

//----------------------- Previous Button-----------------------------
        mPrevButton = (ImageButton)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null) return;
        mQuestionBank[mCurrentIndex].setCheater(data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
    }

    private void checkAnswer(boolean ansSelected){
        if(mQuestionBank[mCurrentIndex].isCheater()){
            Toast.makeText(QuizActivity.this, "Cheating is wrong", Toast.LENGTH_SHORT).show();

        }
        else{
            if(ansSelected == mQuestionBank[mCurrentIndex].isTrueQuestion()){
                Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();

            }
            else{
                Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX, mCurrentIndex);

    }



    private void updateQuestion(int direction){

        switch (direction){
            case 1:
                mCurrentIndex = (mCurrentIndex+1)% mQuestionBank.length;

                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
                break;
            case 0:
                if(mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                }
                else{
                    mCurrentIndex = mQuestionBank.length -1;
                }

                mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
                break;
            default:
                throw new InvalidParameterException("given parameter..." + direction + "...Use 1 for next and 0 for previous");
        }
    }
}
