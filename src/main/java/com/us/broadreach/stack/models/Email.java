package com.us.broadreach.stack.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Email {

    private String emailFrom;
    private String subject;
    private String body;

    @java.lang.Override
    public java.lang.String toString() {
        return "Email{" +
                "emailFrom='" + emailFrom + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public Email(String emailFrom, String subject, String body) {
        this.emailFrom = emailFrom;
        this.subject = subject;
        this.body = body;
    }

    public Email(){}

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

