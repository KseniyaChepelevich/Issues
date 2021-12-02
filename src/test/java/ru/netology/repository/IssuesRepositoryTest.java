package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssuesRepositoryTest {
    private IssuesRepository repository = new IssuesRepository();

    private Issues first = new Issues(1, "First", true, "Ivanov", Set.of("bug"), Set.of("No one"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues second = new Issues(2, "Second", false, "Ivanov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues third = new Issues(3, "Third", true, "Ivanov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues fourth = new Issues(4, "Fourth", false, "Petrov", Set.of("documentation"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues fifth = new Issues(5, "Fifth", true, "Petrov", Set.of("bug"), Set.of("Sidorov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues sixth = new Issues(6, "Sixth", true, "Sidorov", Set.of("bug"), Set.of("Ivanov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));

    @Test
    void getAll() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{first, second, third, fourth, fifth};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);
    }



    @Test
    void add() {
        repository.add(first);
        repository.add(second);

        Issues[] expected = new Issues[]{first, second};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);
    }

    @Test
    void remove() {
        repository.addAll(List.of(first, second, third, fourth, fifth));
        repository.remove(second);

        Issues[] expected = new Issues[]{first, third, fourth, fifth};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);
    }
}