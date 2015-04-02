package com.niranjanbajgai.geoquiz;

/**
 * Created by niranjanbajgai on 15-03-08.
 */
public class TrueFalse {
    private int mQuestion;
    private boolean mTrueQuestion;
    private boolean mCheater;

    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        mCheater = false;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.mTrueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        this.mQuestion = question;
    }

    public void setCheater(boolean cheater){
        this.mCheater = cheater;
    }
    public boolean isCheater(){
        return mCheater;
    }
}
