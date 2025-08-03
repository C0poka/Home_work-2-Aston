import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        // Создание нескольких экземпляров студентов с наборами книг
        students.add(new Student(
                "Иван",
                Arrays.asList(
                        new Book("Война и мир", 1225, 1869),
                        new Book("Преступление и наказание", 600, 1866),
                        new Book("Анна Каренина", 864, 1877),
                        new Book("Двенадцать стульев", 350, 1928),
                        new Book("Гарри Поттер и философский камень", 223, 1997))
        ));

        students.add(new Student(
                "Марья",
                Arrays.asList(
                        new Book("Мастер и Маргарита", 480, 1967),
                        new Book("Три товарища", 384, 1936),
                        new Book("Фауст", 250, 1808),
                        new Book("Маленький принц", 96, 1943),
                        new Book("Властелин колец", 1178, 1954))
        ));

        students.add(new Student(
                "Алексей",
                Arrays.asList(
                        new Book("Граф Монте-Кристо", 1400, 1844),
                        new Book("Игра престолов", 835, 1996),
                        new Book("Код да Винчи", 454, 2003),
                        new Book("Американские боги", 636, 2001),
                        new Book("Коллекционер", 288, 1963))
        ));

        students.stream()
                .peek(System.out::println)                      // выводит каждого студента
                .flatMap(student -> student.books.stream())    // получает книги всех студентов
                .sorted(Comparator.comparingInt(book -> book.pagesCount)) // сортирует книги по числу страниц
                .distinct()                                     // оставляет только уникальные книги
                .filter(book -> book.yearPublished > 2000)      // фильтрует книги изданные после 2000 г.
                .limit(3)                                       // ограничивает три книги
                .map(book -> book.yearPublished)                // извлекает год выпуска книги
                .findFirst()                                    // ищет первую подходящую книгу
                .ifPresentOrElse(year -> System.out.println("Год выпуска первой подходящей книги: " + year),
                        () -> System.out.println("Нет книг, удовлетворяющих условиям"));


    }
}
