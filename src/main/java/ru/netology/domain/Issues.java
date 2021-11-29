package ru.netology.domain;

import java.util.Collections;
import java.util.Set;

public class Issues {
    private int id;
    private boolean close;
    private String author;
    private Set labelIssue;
    private Set assignee;
    private String projects;
    private String milestones;
    private Set tags;

    public Issues(int id, boolean close, String author, Set labelIssue, Set assignee, String projects, String milestones, Set tags) {
        this.id = id;
        this.close = close;
        this.author = author;
        this.labelIssue = labelIssue;
        this.assignee = assignee;
        this.projects = projects;
        this.milestones = milestones;
        this.tags = tags;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set getLabelIssue() {
        return labelIssue;
    }

    public void setLabelIssue(Set labelIssue) {
        this.labelIssue = labelIssue;
    }

    public Set getAssignee() {
        return assignee;
    }

    public void setAssignee(Set assignee) {
        this.assignee = assignee;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getMilestones() {
        return milestones;
    }

    public void setMilestones(String milestones) {
        this.milestones = milestones;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }
}
