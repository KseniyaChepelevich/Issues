package ru.netology.repository;

import ru.netology.domain.Issues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssuesRepository {
    private List<Issues> items = new ArrayList<>();

    public List<Issues> getAll() {
        return items;
    }

    public  Issues getById(int id) {
        for (Issues item : items) {
            if (item.getId() == id) {
                return  item;
            }

        }
        return null;

    }

    public  boolean add(Issues item) {
        return  items.add(item);
    }



}
