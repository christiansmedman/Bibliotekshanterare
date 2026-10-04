package se.iths.christian.Bibliotekshanterare;

import java.util.Arrays;
public class Library {

    private Book[] books;
    private Member[] members;
    private int bookCount = 0;
    private int memberCount = 0;
    private int nextBookId = 1;
    private int nextMemberId = 1;

    public Library(int maxBooks, int maxMembers) {
        this.books = new Book[maxBooks];
        this.members = new Member[maxMembers];
    }

    public int getBookCount() {
        return bookCount;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public Book addBook(String title, String author) throws LibraryException {
        if (title == null || title.isBlank() || author == null || author.isBlank()) {
            throw new LibraryException("Titel och författare får inte vara tomma.");
        }

        if (bookCount == books.length) {
            books = Arrays.copyOf(books, Math.max(1, books.length * 2));
        }
        //Hantera vid full array.
        Book book = new Book(nextBookId++, title.trim(), author.trim());
        books[bookCount++] = book;
        return book;
    }

    public Member addMember(String name) throws LibraryException {
        if (memberCount == members.length) {
            members = Arrays.copyOf(members, Math.max(1, members.length * 2));
            throw new LibraryException("Medlemsregistret är fullt (max " + members.length + " medlemmar).");
        }
        try {
            Member member = new Member(nextMemberId, name);
            nextMemberId++;
            members[memberCount++] = member;
            return member;
        } catch (IllegalArgumentException e) {
            throw new LibraryException(e.getMessage());
        }
    }
    public Book findBookById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId() == id) {
                return books[i];
            }
        }
        return null;
    }

    public Member findMemberById(int id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].id() == id) {
                return members[i];
            }
        }
        return null;
    }
    public Book borrowBook(int bookId, int memberId) throws LibraryException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new LibraryException("Ingen bok med id " + bookId + " hittades.");
        }
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new LibraryException("Ingen medlem med id " + memberId + " hittades.");
        }
        if (!book.isAvailable()) {
            throw new LibraryException("Boken \"" + book.getTitle() + "\" är redan utlånad till "
                    + book.getBorrower().name() + " .");
        }
        book.setBorrower(member);
        return book;
    }
    public Book returnBook(int bookId) throws LibraryException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new LibraryException("Ingen bok med id " + bookId + " hittades.");
        }
        if (book.isAvailable()) {
            throw new LibraryException("Boken \"" + book.getTitle() + "\" är inte utlånad.");
        }
        book.setBorrower(null);
        return book;
    }

    public Book[] searchBooks(String search) {
        String s = search.trim().toLowerCase();
        Book[] temp = new Book[bookCount];
        int found = 0;
        for (int i = 0; i < bookCount; i++) {
            String title = books[i].getTitle().toLowerCase();
            String author = books[i].getAuthor().toLowerCase();
            if (title.contains(s) || author.contains(s)) {
                temp[found++] = books[i];
            }
        }
        Book[] result = new Book[found];
        for (int i = 0; i < found; i++) {
            result[i] = temp[i];
        }
        return result;
    }
    public Book[] getAllBooks() {
        Book[] result = new Book[bookCount];
        for (int i = 0; i < bookCount; i++) {
            result[i] = books[i];
        }
        return result;
    }

}