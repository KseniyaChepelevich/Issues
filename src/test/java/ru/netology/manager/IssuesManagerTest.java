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
    private Issues fourth = new Issues(4, "Fourth", false, "Petrov", Set.of("documentation"), Set.of("Petrov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues fifth = new Issues(5, "Fifth", true, "Petrov", Set.of("bug"), Set.of("Sidorov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));
    private Issues sixth = new Issues(6, "Sixth", true, "Sidorov", Set.of("bug"), Set.of("Ivanov"), Set.of("No one"), Set.of("No one"), Set.of("No one"));


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


    @Test
    void searchByAuthorIfOne() {
        String author = "Sidorov";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issues[] expected = new Issues[]{sixth};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAuthorIfNoOne() {
        String author = "Olga";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }


    @Test
    void searchByAssignee() {
        String assignee = "Petrov";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{second, third, fourth};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAssigneeIfOne() {
        String assignee = "Ivanov";

        repository.addAll(List.of(first, second, third, fourth, fifth,sixth));

        Issues[] expected = new Issues[]{sixth};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAssigneeIfNoOne() {
        String assignee = "Olga";

        repository.addAll(List.of(first, second, third, fourth, fifth,sixth));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }



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
    void closeByIdIfAlreadyClosed() {
        int idNecessaryToClose = 1;
        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        manager.closeById(idNecessaryToClose);

        Issues[] expected = new Issues[]{first, third, fifth, sixth};
        Issues[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openById() {
        int idNecessaryToOpen = 3;
        repository.addAll(List.of(first, second, third, fourth, fifth));
        manager.openById(idNecessaryToOpen);

        Issues[] expected = new Issues[]{second, third, fourth};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdIfAlreadyOpen() {
        int idNecessaryToOpen = 4;
        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        manager.openById(idNecessaryToOpen);

        Issues[] expected = new Issues[]{second, fourth};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssue() {
        String labelIssue = "bug";

        repository.addAll(List.of(first, second, third, fourth, fifth));

        Issues[] expected = new Issues[]{first, second, third, fifth};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByLabelIssueIfOne() {
        String labelIssue = "documentation";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issues[] expected = new Issues[]{fourth};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchByLabelIssueIfNoOne() {
        String labelIssue = "question";

        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveById() {


        repository.addAll(List.of(first, second, third, fourth, fifth, sixth));
        repository.remove(third);

        Issues[] expected = new Issues[]{first, second, fourth, fifth, sixth};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdWhichIsNot() {


        repository.addAll(List.of(first, second, fourth, fifth, sixth));
        repository.remove(third);

        Issues[] expected = new Issues[]{first, second, fourth, fifth, sixth};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);

    }


    @Test
    void shouldSearchCloseIfOneIn() {
        repository.addAll(List.of(first));

        Issues[] expected = new Issues[]{first};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenIfOneIn() {
        repository.addAll(List.of(second));

        Issues[] expected = new Issues[]{second};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorIOneIn() {
        String author = "Ivanov";

        repository.addAll(List.of(second));

        Issues[] expected = new Issues[]{second};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeIfOneIn() {
        String assignee = "Petrov";

        repository.addAll(List.of(second));

        Issues[] expected = new Issues[]{second};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdIfOneIn() {
        int idNecessaryToClose = 1;

        repository.addAll(List.of(first));

        manager.closeById(idNecessaryToClose);

        Issues[] expected = new Issues[]{first};
        Issues[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdIfOneIn() {
        int idNecessaryToOpen = 2;

        repository.addAll(List.of(second));

        manager.openById(idNecessaryToOpen);

        Issues[] expected = new Issues[]{second};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueIfOneIn() {
        String labelIssue = "bug";

        repository.addAll(List.of(first));

        Issues[] expected = new Issues[]{first};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdIfOneIn() {

        repository.addAll(List.of(first));

        repository.remove(first);

        Issues[] expected = new Issues[]{};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldSearchCloseWhichNotIfOneIn() {
        repository.addAll(List.of(second));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenWhichNotIfOneIn() {
        repository.addAll(List.of(first));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorWhichNotIfOneIn() {
        String author = "Petrov";

        repository.addAll(List.of(second));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeWhichNotIfOneIn() {
        String assignee = "Petrov";

        repository.addAll(List.of(first));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdWhichNotIfOneIn() {
        int idNecessaryToClose = 2;

        repository.addAll(List.of(first));

        manager.closeById(idNecessaryToClose);

        Issues[] expected = new Issues[]{first};
        Issues[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdWhichNotIfOneIn() {
        int idNecessaryToOpen = 1;

        repository.addAll(List.of(second));

        manager.openById(idNecessaryToOpen);

        Issues[] expected = new Issues[]{second};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueWhichNotIfOneIn() {
        String labelIssue = "bug";

        repository.addAll(List.of(fourth));

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdWhichNotIfOneIn() {

        repository.addAll(List.of(first));

        repository.remove(second);

        Issues[] expected = new Issues[]{first};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);

    }



    @Test
    void shouldSearchCloseInEmpty() {

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchClose();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchOpenInEmpty() {

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchOpen();

        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByAuthorInEmpty() {
        String author = "Ivanov";


        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAuthor(author);

        assertArrayEquals(expected, actual);

    }

    @Test
    void searchByAssigneeInEmpty() {
        String assignee = "Petrov";


        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByAssignee(assignee);

        assertArrayEquals(expected, actual);
    }

    @Test
    void closeByIdInEmpty() {
        int idNecessaryToClose = 2;

        manager.closeById(idNecessaryToClose);

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchClose();
        assertArrayEquals(expected, actual);
    }

    @Test
    void openByIdInEmpty() {
        int idNecessaryToOpen = 3;

        manager.openById(idNecessaryToOpen);

        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchOpen();
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByLabelIssueInEmpty() {
        String labelIssue = "bug";


        Issues[] expected = new Issues[]{};
        Issues[] actual = manager.searchByLabelIssue(labelIssue);

        assertArrayEquals(expected, actual);

    }

    @Test
    void shouldRemoveByIdInEmpty() {


        repository.remove(third);

        Issues[] expected = new Issues[]{};
        Issues[] actual = repository.getAll().toArray(new Issues[0]);

        assertArrayEquals(expected, actual);

    }

}