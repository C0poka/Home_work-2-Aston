import java.util.List;

class Student {
    String name;
    List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    @Override
    public String toString() {
        return "Student " +
                "name= '" + name + ", books=" + books ;
    }
}

