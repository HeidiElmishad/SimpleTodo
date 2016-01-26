package com.codepath.simpletodo.com.codepath.simpletodo;

import java.util.Date;

/**
 * Created by snapfish on 1/25/16.
 */
public class TodoItem {

    private String mTaskName;
    private String mTaskNotes;
    private Date mDueDate;
    private String mPriority;
    private String mStatus;

    public TodoItem(Date mDueDate, String mPriority, String mStatus, String mTaskName, String mTaskNotes) {
        this.mDueDate = mDueDate;
        this.mPriority = mPriority;
        this.mStatus = mStatus;
        this.mTaskName = mTaskName;
        this.mTaskNotes = mTaskNotes;
    }

    public Date getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(Date mDueDate) {
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
}
