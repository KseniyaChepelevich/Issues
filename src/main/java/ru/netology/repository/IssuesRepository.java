package ru.netology.repository;

import ru.netology.domain.Issues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssuesRepository {
    private List<Issues> items = new ArrayList<>();

    public Collection<Issues> getAll() {
        return this.items;
    }

    public boolean addAll(Collection<? extends  Issues> items) {
        return  this.items.addAll(items);
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

    public boolean remove(Issues item) {return items.remove(item);}

    public boolean removeAll(Collection<? extends Issues> items) {
        return this.items.removeAll(items);
    }





}
