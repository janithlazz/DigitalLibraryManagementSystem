import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library;
    private static Scanner scanner;
    private static LibraryStaff currentStaff;
    private static Person currentUser;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        // Initialize the library with sample data
        initializeLibrary();

        System.out.println("Welcome to the " + library.getName() + " Library Management System!");

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                login();
            } else {
                if (currentUser instanceof LibraryStaff) {
                    adminMenu();
                } else {
                    displayMainMenu();
                }
            }
        }

        scanner.close();
    }

    private static void initializeLibrary() {
        // Create the library
        library = new Library("Central City", "123 Main Street");

        // Create some staff members
        LibraryStaff staff1 = new LibraryStaff("S001", "John Smith", "john@library.com", "STAFF001", "Librarian");
        LibraryStaff staff2 = new LibraryStaff("S002", "Jane Doe", "jane@library.com", "STAFF002", "Assistant Librarian");

        // Register staff
        library.registerPerson(staff1);
        library.registerPerson(staff2);

        // Set current staff for administrative functions
        currentStaff = staff1;

        // Create some patrons
        Patron patron1 = new Patron("P001", "Alice Johnson", "alice@email.com");
        Patron patron2 = new Patron("P002", "Bob Wilson", "bob@email.com");
        Patron patron3 = new Patron("P003", "Carol Adams", "carol@email.com");

        // Register patrons
        library.registerPerson(patron1);
        library.registerPerson(patron2);
        library.registerPerson(patron3);

        // Create some books
        Book book1 = new Book("B001", "The Great Gatsby", 0.25, "F. Scott Fitzgerald", "978-0743273565", 1925, "Fiction", 180);
        Book book2 = new Book("B002", "To Kill a Mockingbird", 0.25, "Harper Lee", "978-0061120084", 1960, "Fiction", 281);
        Book book3 = new Book("B003", "1984", 0.25, "George Orwell", "978-0451524935", 1949, "Fiction", 328);
        Book book4 = new Book("B004", "The Hobbit", 0.25, "J.R.R. Tolkien", "978-0547928227", 1937, "Fantasy", 366);
        Book book5 = new Book("B005", "Pride and Prejudice", 0.25, "Jane Austen", "978-0141439518", 1813, "Classic", 432);

        // Create some magazines
        Magazine mag1 = new Magazine("M001", "National Geographic", 0.50, "National Geographic Society", LocalDate.of(2023, 1, 15), 1, "Science");
        Magazine mag2 = new Magazine("M002", "Time", 0.50, "Time USA, LLC", LocalDate.of(2023, 2, 20), 5, "News");
        Magazine mag3 = new Magazine("M003", "Scientific American", 0.50, "Springer Nature", LocalDate.of(2023, 3, 10), 3, "Science");

        // Create some DVDs
        DVD dvd1 = new DVD("D001", "The Shawshank Redemption", 0.75, "Frank Darabont", 142, "R", 1994);
        DVD dvd2 = new DVD("D002", "The Godfather", 0.75, "Francis Ford Coppola", 175, "R", 1972);
        DVD dvd3 = new DVD("D003", "The Dark Knight", 0.75, "Christopher Nolan", 152, "PG-13", 2008);

        // Add items to the library
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(book3);
        library.addItem(book4);
        library.addItem(book5);
        library.addItem(mag1);
        library.addItem(mag2);
        library.addItem(mag3);
        library.addItem(dvd1);
        library.addItem(dvd2);
        library.addItem(dvd3);

        // Check out some items
        book1.checkOut(patron1);
        mag1.checkOut(patron1);
        dvd1.checkOut(patron2);
    }

    private static void login() {
        System.out.println("\n===== LOGIN =====");
        System.out.println("1. Login as Patron");
        System.out.println("2. Login as Staff");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                loginAsPatron();
                break;
            case 2:
                loginAsStaff();
                break;
            case 3:
                System.out.println("Thank you for using the Library Management System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void loginAsPatron() {
        System.out.print("Enter your patron ID: ");
        String id = scanner.nextLine();

        Person person = library.findPerson(id);

        if (person != null && person instanceof Patron) {
            currentUser = person;
            System.out.println("Welcome, " + currentUser.getName() + "!");
        } else {
            System.out.println("Invalid patron ID. Please try again.");
        }
    }

    private static void loginAsStaff() {
        System.out.print("Enter your staff ID: ");
        String id = scanner.nextLine();

        Person person = library.findPerson(id);

        if (person != null && person instanceof LibraryStaff) {
            currentUser = person;
            currentStaff = (LibraryStaff) currentUser;
            System.out.println("Welcome, " + currentUser.getName() + "!");
        } else {
            System.out.println("Invalid staff ID. Please try again.");
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Search for Items");
        System.out.println("2. Check Out Item");
        System.out.println("3. Return Item");
        System.out.println("4. View My Borrowed Items");
        System.out.println("5. View My Account");
        System.out.println("6. Pay Fines");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchItems();
                break;
            case 2:
                checkoutItem();
                break;
            case 3:
                returnItem();
                break;
            case 4:
                viewBorrowedItems();
                break;
            case 5:
                viewAccount();
                break;
            case 6:
                payFines();
                break;
            case 7:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void adminMenu() {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Search for Items");
        System.out.println("2. Add New Item");
        System.out.println("3. Remove Item");
        System.out.println("4. Register New Patron");
        System.out.println("5. Search for Patron");
        System.out.println("6. Process Fine Payment");
        System.out.println("7. Generate Overdue Items Report");
        System.out.println("8. View Library Statistics");
        System.out.println("9. Logout");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchItems();
                break;
            case 2:
                addNewItem();
                break;
            case 3:
                removeItem();
                break;
            case 4:
                registerNewPatron();
                break;
            case 5:
                searchForPatron();
                break;
            case 6:
                processFinePayment();
                break;
            case 7:
                currentStaff.generateOverdueItemsReport(library);
                break;
            case 8:
                library.displayLibraryStats();
                break;
            case 9:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchItems() {
        System.out.println("\n===== SEARCH ITEMS =====");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Type");
        System.out.println("3. Search by Creator (Author/Director)");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                searchByTitle();
                break;
            case 2:
                searchByType();
                break;
            case 3:
                searchByCreator();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchByTitle() {
        System.out.print("Enter title to search for: ");
        String title = scanner.nextLine();

        List<LibraryItem> results = library.searchItemsByTitle(title);
        displaySearchResults(results);
    }

    private static void searchByType() {
        System.out.println("Select item type:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();
        String type;

        switch (choice) {
            case 1:
                type = "Book";
                break;
            case 2:
                type = "Magazine";
                break;
            case 3:
                type = "DVD";
                break;
            default:
                System.out.println("Invalid choice. Returning to search menu.");
                return;
        }

        List<LibraryItem> results = library.searchItemsByType(type);
        displaySearchResults(results);
    }

    private static void searchByCreator() {
        System.out.print("Enter author or director name: ");
        String creator = scanner.nextLine();

        List<LibraryItem> results = library.searchItemsByCreator(creator);
        displaySearchResults(results);
    }

    private static void displaySearchResults(List<LibraryItem> results) {
        if (results.isEmpty()) {
            System.out.println("No items found matching your search criteria.");
            return;
        }

        System.out.println("\nSearch Results:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s %-10s\n", "ID", "Title", "Type", "Status");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < results.size(); i++) {
            LibraryItem item = results.get(i);
            System.out.printf("%-10s %-30s %-10s %-10s\n",
                    item.getId(),
                    (item.getTitle().length() > 27 ? item.getTitle().substring(0, 27) + "..." : item.getTitle()),
                    item.getItemType(),
                    (item.isCheckedOut() ? "Checked Out" : "Available"));
        }
        System.out.println("------------------------------------------------------------");
    }

    private static void checkoutItem() {
        if (!(currentUser instanceof Patron)) {
            System.out.println("Only patrons can check out items.");
            return;
        }

        Patron patron = (Patron) currentUser;

        System.out.print("Enter the ID of the item you want to check out: ");
        String itemId = scanner.nextLine();

        LibraryItem item = library.findItem(itemId);

        if (item == null) {
            System.out.println("Item not found with ID: " + itemId);
            return;
        }

        if (item.isCheckedOut()) {
            System.out.println("This item is already checked out.");
            return;
        }

        boolean success = item.checkOut(patron);

        if (success) {
            System.out.println("Item checked out successfully!");
        } else {
            System.out.println("Failed to check out item.");
        }
    }

    private static void returnItem() {
        if (!(currentUser instanceof Patron)) {
            System.out.println("Only patrons can return items.");
            return;
        }

        Patron patron = (Patron) currentUser;
        List<LibraryItem> borrowedItems = patron.getBorrowedItems();

        if (borrowedItems.isEmpty()) {
            System.out.println("You don't have any items checked out.");
            return;
        }

        System.out.println("\nYour Borrowed Items:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s %-12s\n", "ID", "Title", "Type", "Due Date");
        System.out.println("------------------------------------------------------------");

        for (LibraryItem item : borrowedItems) {
            System.out.printf("%-10s %-30s %-10s %-12s\n",
                    item.getId(),
                    (item.getTitle().length() > 27 ? item.getTitle().substring(0, 27) + "..." : item.getTitle()),
                    item.getItemType(),
                    item.getDueDate());
        }
        System.out.println("------------------------------------------------------------");

        System.out.print("Enter the ID of the item you want to return: ");
        String itemId = scanner.nextLine();

        LibraryItem itemToReturn = null;
        for (LibraryItem item : borrowedItems) {
            if (item.getId().equals(itemId)) {
                itemToReturn = item;
                break;
            }
        }

        if (itemToReturn == null) {
            System.out.println("You don't have an item with ID: " + itemId);
            return;
        }

        boolean success = itemToReturn.returnItem();

        if (success) {
            System.out.println("Item returned successfully!");

            double fine = itemToReturn.calculateFine();
            if (fine > 0) {
                System.out.println("Fine for late return: $" + fine);
                System.out.println("Your total outstanding fines: $" + patron.getOutstandingFines());
            }
        } else {
            System.out.println("Failed to return item.");
        }
    }

    private static void viewBorrowedItems() {
        if (!(currentUser instanceof Patron)) {
            System.out.println("Only patrons can view borrowed items.");
            return;
        }

        Patron patron = (Patron) currentUser;
        patron.displayBorrowedItems();
    }

    private static void viewAccount() {
        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println(currentUser.toString());

        if (currentUser instanceof Patron) {
            Patron patron = (Patron) currentUser;
            System.out.println("\nOutstanding Fines: $" + patron.getOutstandingFines());
            System.out.println("Items Borrowed: " + patron.getItemCount() + " / " + patron.getMaxBorrowItems());
        }
    }

    private static void payFines() {
        if (!(currentUser instanceof Patron)) {
            System.out.println("Only patrons can pay fines.");
            return;
        }

        Patron patron = (Patron) currentUser;
        double fines = patron.getOutstandingFines();

        if (fines <= 0) {
            System.out.println("You don't have any outstanding fines.");
            return;
        }

        System.out.println("Your outstanding fines: $" + fines);
        System.out.print("Enter amount to pay: $");

        double amount = getDoubleInput();

        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }

        boolean success = patron.payFine(amount);

        if (success) {
            System.out.println("Payment processed successfully!");
            System.out.println("Remaining fines: $" + patron.getOutstandingFines());
        }
    }

    private static void addNewItem() {
        System.out.println("\n===== ADD NEW ITEM =====");
        System.out.println("Select item type:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");
        System.out.println("4. Cancel");
        System.out.print("Enter your choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                addNewMagazine();
                break;
            case 3:
                addNewDVD();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addNewBook() {
        System.out.println("\nEnter Book Details:");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (library.findItem(id) != null) {
            System.out.println("An item with this ID already exists.");
            return;
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Year Published: ");
        int year = getIntInput();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Page Count: ");
        int pages = getIntInput();

        System.out.print("Daily Fine Rate: $");
        double fineRate = getDoubleInput();

        Book book = new Book(id, title, fineRate, author, isbn, year, genre, pages);
        boolean success = currentStaff.addItemToLibrary(book, library);

        if (success) {
            System.out.println("Book added successfully!");
        }
    }

    private static void addNewMagazine() {
        System.out.println("\nEnter Magazine Details:");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (library.findItem(id) != null) {
            System.out.println("An item with this ID already exists.");
            return;
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Publisher: ");
        String publisher = scanner.nextLine();

        System.out.print("Issue Number: ");
        int issueNumber = getIntInput();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Issue Date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate issueDate;
        try {
            issueDate = LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using today's date.");
            issueDate = LocalDate.now();
        }

        System.out.print("Daily Fine Rate: $");
        double fineRate = getDoubleInput();

        Magazine magazine = new Magazine(id, title, fineRate, publisher, issueDate, issueNumber, category);
        boolean success = currentStaff.addItemToLibrary(magazine, library);

        if (success) {
            System.out.println("Magazine added successfully!");
        }
    }

    private static void addNewDVD() {
        System.out.println("\nEnter DVD Details:");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (library.findItem(id) != null) {
            System.out.println("An item with this ID already exists.");
            return;
        }

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Director: ");
        String director = scanner.nextLine();

        System.out.print("Runtime (minutes): ");
        int runtime = getIntInput();

        System.out.print("Rating: ");
        String rating = scanner.nextLine();

        System.out.print("Release Year: ");
        int year = getIntInput();

        System.out.print("Daily Fine Rate: $");
        double fineRate = getDoubleInput();

        DVD dvd = new DVD(id, title, fineRate, director, runtime, rating, year);
        boolean success = currentStaff.addItemToLibrary(dvd, library);

        if (success) {
            System.out.println("DVD added successfully!");
        }
    }

    private static void removeItem() {
        System.out.print("Enter the ID of the item you want to remove: ");
        String itemId = scanner.nextLine();

        LibraryItem item = library.findItem(itemId);

        if (item == null) {
            System.out.println("Item not found with ID: " + itemId);
            return;
        }

        System.out.println("Item Details:");
        System.out.println(item);

        System.out.print("Are you sure you want to remove this item? (y/n): ");
        String confirm = scanner.nextLine().toLowerCase();

        if (confirm.equals("y") || confirm.equals("yes")) {
            boolean success = currentStaff.removeItemFromLibrary(itemId, library);

            if (success) {
                System.out.println("Item removed successfully!");
            }
        } else {
            System.out.println("Item removal cancelled.");
        }
    }

    private static void registerNewPatron() {
        System.out.println("\nEnter Patron Details:");

        System.out.print("ID: ");
        String id = scanner.nextLine();

        if (library.findPerson(id) != null) {
            System.out.println("A person with this ID already exists.");
            return;
        }

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Contact Info: ");
        String contactInfo = scanner.nextLine();

        System.out.print("Maximum Borrowing Limit: ");
        int maxItems = getIntInput();
        if (maxItems <= 0) {
            maxItems = 5; // Default value
        }

        Patron patron = new Patron(id, name, contactInfo, maxItems);
        boolean success = currentStaff.registerPatron(patron, library);

        if (success) {
            System.out.println("Patron registered successfully!");
        }
    }

    private static void searchForPatron() {
        System.out.print("Enter patron name to search for: ");
        String name = scanner.nextLine();

        Patron patron = currentStaff.findPatronByName(name, library);

        if (patron != null) {
            System.out.println("\nPatron Found:");
            System.out.println(patron);

            System.out.println("\nBorrowed Items:");
            patron.displayBorrowedItems();
        }
    }

    private static void processFinePayment() {
        System.out.print("Enter patron ID: ");
        String patronId = scanner.nextLine();

        Person person = library.findPerson(patronId);

        if (person == null || !(person instanceof Patron)) {
            System.out.println("Patron not found with ID: " + patronId);
            return;
        }

        Patron patron = (Patron) person;
        double fines = patron.getOutstandingFines();

        if (fines <= 0) {
            System.out.println("This patron doesn't have any outstanding fines.");
            return;
        }

        System.out.println("Patron: " + patron.getName());
        System.out.println("Outstanding Fines: $" + fines);

        System.out.print("Enter amount to process: $");
        double amount = getDoubleInput();

        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return;
        }

        boolean success = currentStaff.processFinePayment(patron, amount);

        if (success) {
            System.out.println("Payment processed successfully!");
            System.out.println("Patron's remaining fines: $" + patron.getOutstandingFines());
        }
    }

    private static void logout() {
        currentUser = null;
        currentStaff = null;
        System.out.println("You have been logged out successfully.");
    }

    private static int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return 0;
        }
    }

    private static double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return 0.0;
        }
    }
}