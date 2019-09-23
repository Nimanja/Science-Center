package com.example.sciencecenter;

public class JavaView {

    private String mName, mPhone, mCommnt;

    public JavaView() {

    }

    public JavaView(String mName, String mPhone, String mCommnt) {
        this.mName = mName;
        this.mPhone = mPhone;
        this.mCommnt = mCommnt;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmCommnt() {
        return mCommnt;
    }

    public void setmCommnt(String mCommnt) {
        this.mCommnt = mCommnt;
    }
}
