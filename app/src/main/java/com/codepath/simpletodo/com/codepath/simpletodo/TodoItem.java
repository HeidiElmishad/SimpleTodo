package com.codepath.simpletodo.com.codepath.simpletodo;

import java.util.Calendar;

/**
 * Created by snapfish on 1/25/16.
 */
public class TodoItem {

    private String mTaskName;
    private String mTaskNotes;
    private String mDueDate;
    private String mPriority;
    private String mStatus;
    private Long milliTime;

    public TodoItem(String mDueDate, String mPriority, String mStatus, String mTaskName, String mTaskNotes, long milliTime) {
        this.mDueDate = mDueDate;
        this.mPriority = mPriority;
        this.mStatus = mStatus;
        this.mTaskName = mTaskName;
        this.mTaskNotes = mTaskNotes;
        this.milliTime = milliTime;
    }

    public String getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }

    public String getmPriority() {
        return mPriority;
    }

    public void setmPriority(String mPriority) {
        this.mPriority = mPriority;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmTaskName() {
        return mTaskName;
    }

    public void setmTaskName(String mTaskName) {
        this.mTaskName = mTaskName;
    }

    public String getmTaskNotes() {
        return mTaskNotes;
    }

    public void setmTaskNotes(String mTaskNotes) {
        this.mTaskNotes = mTaskNotes;
    }

    public Long getMilliTime() {
        return milliTime;
    }

    public void setMilliTime(Long milliTime) {
        this.milliTime = milliTime;
    }
}
