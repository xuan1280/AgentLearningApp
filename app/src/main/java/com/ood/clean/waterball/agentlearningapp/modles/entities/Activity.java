package com.ood.clean.waterball.agentlearningapp.modles.entities;

import java.util.List;

public class Activity extends Entitiy{
    private List<ActivityTags> ActivityTags;
    private String title;
    private String startDate;
    private String endDate;
    private String content;
    private String source;
    private String link;
    private String address;
    private String contact;

    public Activity(int id, List<ActivityTags> ActivityTags, String title, String startDate, String endDate, String content, String source, String link, String address, String contact) {
        this.id = id;
        this.ActivityTags = ActivityTags;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.source = source;
        this.link = link;
        this.address = address;
        this.contact = contact;
    }

    public List<ActivityTags> getActivityTags() {
        return ActivityTags;
    }

    public void setTags (List<ActivityTags> ActivityTags) {
        this.ActivityTags = ActivityTags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
