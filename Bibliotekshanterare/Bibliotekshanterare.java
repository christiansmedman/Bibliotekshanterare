package se.iths.christian.Bibliotekshanterare;



    public class Bibliotekshanterare {

        private static final int MAX_BOOKS = 100;
        private static final int MAX_MEMBERS = 50;

        private static final Library library = new Library(MAX_BOOKS, MAX_MEMBERS);

        static void main() {
            boolean running = true;
            while (running) {
                printMenu();

                try {
                    int choice = Integer.parseInt(IO.readln("Välj (0-6): "));
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMember();
                    case 3 -> borrowBook();
                    case 4 -> returnBook();
                    case 5 -> searchBooks();
                    case 6 -> showAllBooks();
                    case 0 -> running = false;
                    default -> IO.println("Ogiltigt menyval. Ange en siffra mellan 0 och 6.");
                    }
                } catch (NumberFormatException e) {
                    IO.println("Du måste ange en siffra mellan 0-6!");
                    }
            }
            IO.println("Hej då!");
        }

        private static void printMenu() {

            IO.println("""
                
        ===== BIBLIOTEK =====
        1. Lägg till bok
        2. Registrera medlem
        3. Låna bok
        4. Lämna tillbaka bok
        5. Sök bok (titel/författare)
        6. Visa alla böcker och status
        0. Avsluta
        """);
        }

    private static void addBook() {
        String title = IO.readln("Titel: ");
        String author = IO.readln("Författare: ");
        try {
            Book book = library.addBook(title, author);
            IO.println("Boken lades till med id " + book.getId() + ".");
        } catch (LibraryException e) {
            IO.println("Fel: " + e.getMessage());
        }
    }

        private static void addMember() {
            String name = IO.readln("Namn: ");
            try {
                Member member = library.addMember(name);
                IO.println("Medlem registrerad med id " + member.id() + ".");
            } catch (LibraryException e) {
                IO.println("Fel: " + e.getMessage());
            }
        }
        private static void borrowBook() {
            int bookId = Integer.parseInt(IO.readln("Bok-ID: "));
            int memberId = Integer.parseInt(IO.readln("Member-ID: "));
            try {
                Book book = library.borrowBook(bookId, memberId);
                IO.println(book.getTitle() + " är nu utlånad till: " + book.getBorrower().name() + ".");
            } catch (LibraryException e){
                IO.println("Fel: " + e.getMessage());
        }
    }

        private static void returnBook() {
            int bookId = Integer.parseInt(IO.readln("Bok-ID: "));
            try {
                Book book = library.returnBook(bookId);
                IO.println("\"" + book.getTitle() + "\" har lämnats tillbaka.");
            } catch (LibraryException e) {
                IO.println("Fel: " + e.getMessage());
            }
        }

        private static void searchBooks() {
            String search = IO.readln("Sök efter titel eller författare: ");
            Book[] result = library.searchBooks(search);
            if (result.length == 0) {
                IO.println("Inga böcker matchade \"" + search + "\".");
                return;
            }
            IO.println(result.length + " träff(ar):");
            for (Book book : result) {
                IO.println("  " + book);
            }
        }
        private static void showAllBooks() {
            Book[] allBooks = library.getAllBooks();
            if (allBooks.length == 0) {
                IO.println("Inga böcker registrerade ännu.");
                return;
            }
            IO.println("Alla böcker (" + allBooks.length + "/" + MAX_BOOKS + "):");
            for (Book book : allBooks) {
                IO.println("  " + book);
            }
        }
}