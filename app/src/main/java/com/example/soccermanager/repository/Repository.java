package com.example.soccermanager.repository;

import com.example.soccermanager.model.SoccerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T extends SoccerEntity> {

    private final List<T> items = new ArrayList<>();

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public void add(T item) {
        if (item == null) throw new IllegalArgumentException("Item must not be null");
        items.add(item);
    }

    public List<T> filter(Predicate<T> predicate) {
        if (predicate == null) throw new IllegalArgumentException("Predicate must not be null");
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
