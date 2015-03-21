package com.niranjanbajgai.geoquiz;


import android.app.Activity;
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
    //private static final String TAG= "QuizActivity";
    private static final String INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

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
                if(mQuestionBank[mCurrentIndex].isTrueQuestion()){
                    makeCorrectToast();
                }
                else {
                    makeIncorrectToast();
                }

            }
        });

//----------------------False Button-------------------------------
        mFalseButton=(Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mQuestionBank[mCurrentIndex].isTrueQuestion()){
                    makeCorrectToast();
                }
                else{
                    makeIncorrectToast();
                }
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
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INDEX, mCurrentIndex);
    }

    private void makeCorrectToast(){
        Toast.makeText(QuizActivity.this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
    }
    private void makeIncorrectToast(){
        Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

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
