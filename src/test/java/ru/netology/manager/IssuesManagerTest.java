package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issues;
import ru.netology.repository.IssuesRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssuesManagerTest {

    private IssuesRepository repository = new IssuesRepository();
    private IssuesManager manager = new IssuesManager(repository);

    private Issues first = new Issues(1, "First", true, "Ivanov", Set.of("bug"), Set.of("No one"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues second = new Issues(2, "Second", false, "Ivanov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues third = new Issues(3, "Third", true, "Ivanov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues fourth = new Issues(4, "Fourth", false, "Petrov", Set.of("bug"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues fifth = new Issues(5, "Fifth", true, "Petrov", Set.of("bug"), Set.of("Sidorov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));


    @Test
    void shouldSearchClose() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{first, third, fifth};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpen() {
        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{second, fourth};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthor() {
        String author = "Ivanov";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{first, second, third};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);


    }

    //@Test
    //void searchByAssignee() {
     //   Set<String> assignee= Collections.singleton("Petrov");

      //  repository.addAll(List.of(first, second, third, fourth, fifth));

      //  Issues[] expected = new Issues[]{second, third, fourth};
      //  Issues[] actual = manager.searchByAssignee(assignee);

      //  assertArrayEquals(expected, actual);
   // }

    @Test
    void closeById() {
        int idNecessaryToClose = 2;
        repository.addAll(List.of(first, second, third, fourth, fifth));
        manager.closeById(idNecessaryToClose);

        Issues[] expected = new Issues[]{first, second, third, fifth};
        Issues[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openById() {
    }

    @Test
    void searchByLabelIssue() {
        String labelIssue = "bug";

    }
}