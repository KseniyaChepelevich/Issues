package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

public class IssuesManager {
    public IssuesRepository repository = new IssuesRepository();
    public Issues[] items = new Issues[0];

    public IssuesManager(IssuesRepository repository) {
        this.repository = repository;
    }


    public void save(Issues issues) {repository.add(issues); }



    public Issues[] searchClose() {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.isClose() == true) {
                Issues[] tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }

        }
        return result;
    }

    public Issues[] searchOpen() {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.isClose() == false) {
                Issues[]tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issues[] searchByAuthor(String author) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.getAuthor() == author) {
                Issues[]tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public Issues[] searchByAssignee(String assignee) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.getAssignee().contains(assignee)) {
                Issues[]tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public void closeById(int id) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {

            if (issues.getId() == id && !issues.isClose()) {
                issues.setClose(true);
            }

        }
    }

    public void openById(int id) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {

            if (issues.getId() == id && issues.isClose()) {
                issues.setClose(false);
            }

        }
    }

    public Issues[] searchByLabelIssue(String labelIssue) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.getLabelIssue().contains(labelIssue)) {
                Issues[]tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }

    public void removeById(int id) {

        Issues[] result = new Issues[0];
        for (Issues issues : repository.getAll()) {
            if (issues.getId() == id) {


                repository.remove(issues);
            }


        }
    }

}
