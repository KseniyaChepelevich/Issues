package ru.netology.manager;

import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

public class IssuesManager {
    public IssuesRepository repository = new IssuesRepository();
    public Issues[] items = new Issues[0];

    public void save(Issues issues) {repository.add(issues); }

    public Issues[] findAll() {
        return (Issues[]) repository.getAll().toArray();
    }

    public Issues[] searchClose(boolean close) {
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

    public Issues[] searchOpen(boolean open) {
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

    public Issues[] searchByAssignee(Object[] assignee) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.getAssignee().toArray() == assignee) {
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
            if (issues.isClose() == true) {
                return;
            }
        }
    }

    public void openById(int id) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {

            if (issues.getId() == id && issues.isClose()) {
                issues.setClose(false);
            }
            if (!issues.isClose()) {
                return;
            }
        }
    }

    public Issues[] searchByLabelIssue(Object[] labelIssue) {
        Issues[] result = new Issues[0];
        for(Issues issues : repository.getAll()) {
            if(issues.getLabelIssue().toArray() == labelIssue) {
                Issues[]tmp = new Issues[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = issues;
                result = tmp;
            }
        }
        return result;
    }


}
