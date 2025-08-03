class Book implements Comparable<Book> {
    String title;
    int pagesCount;
    int yearPublished;

    public Book(String title, int pagesCount, int yearPublished) {
        this.title = title;
        this.pagesCount = pagesCount;
        this.yearPublished = yearPublished;
    }

    @Override
    public String toString() {
        return
                "\nBook " +
                "title= '" + title + '\'' +
                ", pages Count= " + pagesCount +
                ", year Published= " + yearPublished ;
    }

    @Override
    public int compareTo(Book book) {
        return Integer.compare(this.pagesCount, book.pagesCount);
    }
}