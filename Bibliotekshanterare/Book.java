package se.iths.christian.Bibliotekshanterare;

public class Book {

    private int id;
    private String title;
    private String author;
    private Member borrower;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.borrower = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Member getBorrower() {
        return borrower;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    public boolean isAvailable() {
        return borrower == null;
    }

    @Override
    public String toString() {
        String status = isAvailable()
                ? "Tillgänglig"
                : "Utlånad till " + borrower.name() + " (Medlem #" + borrower.id() + ")";
        return String.format("#%d  \"%s\" av %s  [%s]", id, title, author, status);
    }
}
