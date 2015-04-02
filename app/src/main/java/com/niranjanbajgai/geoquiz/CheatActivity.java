package com.niranjanbajgai.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by niranjanbajgai on 15-03-22.
 */
public class CheatActivity extends Activity {

    private static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_SHOWN = "com.niranjanbajgai.geoquiz.answer_shown";
    private static final String CHEATED = "cheated";


    TextView mAnswerTextView;
    Button mShowButton;
    private boolean mAnswerIsTrue;
    private boolean mCheated;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            setAnswerShownResult(savedInstanceState.getBoolean(CHEATED));
            mCheated = savedInstanceState.getBoolean(CHEATED);
        }
        else{
            setAnswerShownResult(false);
            mCheated = false;
        }

        Log.d(TAG, "onCreated started");

        setContentView(R.layout.activity_cheat);


        mAnswerIsTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_IS_TRUE, false );

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowButton = (Button)findViewById(R.id.showAnswerButton);
        mShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.button_true);

                }
                else{
                    mAnswerTextView.setText(R.string.button_false);
                }
                setAnswerShownResult(true);
                mCheated = true;
            }

        });


    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(CHEATED, mCheated );
    }
}
