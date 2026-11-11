import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    // Тот самый тест из задания (добавление разных задач)
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение Нетобайка", "Во вторник после обеда");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    // --- ТЕСТЫ ДЛЯ ПОИСКА ---

    @Test
    public void shouldSearchSimpleTaskByTitle() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить хлеб");
        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpicBySubtask() {
        Todos todos = new Todos();
        Epic epic = new Epic(2, new String[]{"Молоко", "Хлеб", "Яйца"});
        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("Хлеб");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeetingByTopicAndProject() {
        Todos todos = new Todos();
        Meeting meeting = new Meeting(3, "Обсуждение бюджета", "Проект X", "Завтра");
        todos.add(meeting);

        // Ищем по теме
        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("бюджет"));
        // Ищем по проекту
        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("Проект X"));
    }

    @Test
    public void shouldReturnEmptyArrayWhenNothingFound() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить хлеб"));
        todos.add(new Meeting(2, "Тема", "Проект", "Старт"));

        Task[] actual = todos.search("Космос");
        Assertions.assertEquals(0, actual.length);
    }

    @Test
    public void shouldFindMultipleTasksByQuery() {
        Todos todos = new Todos();
        SimpleTask simple = new SimpleTask(1, "Позвонить в банк");
        Meeting meeting = new Meeting(2, "Обсуждение банка", "Проект", "Завтра");
        Epic epic = new Epic(3, new String[]{"Оплатить счета"});

        todos.add(simple);
        todos.add(meeting);
        todos.add(epic);

        // Ищем слово "банк" (есть в simple и meeting)
        Task[] expected = {simple, meeting};
        Task[] actual = todos.search("банк");
        Assertions.assertArrayEquals(expected, actual);
    }
}