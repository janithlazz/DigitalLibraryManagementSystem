import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron extends Person{

    private List<LibraryItem> borrowedItems;
    private double outstandingFines;
    private int maxBorrowItems;

    // Constructor
    public Patron(String id, String name, String contactInfo) {
        super(id, name, contactInfo, LocalDate.now());
        this.borrowedItems = new ArrayList<>();
        this.outstandingFines = 0.0;
        this.maxBorrowItems = 5; // Default limit
    }

    // Constructor with custom max items
    public Patron(String id, String name, String contactInfo, int maxBorrowItems) {
        super(id, name, contactInfo, LocalDate.now());
        this.borrowedItems = new ArrayList<>();
        this.outstandingFines = 0.0;
        this.maxBorrowItems = maxBorrowItems;
    }
    @Override
    public String getRole() {
        return null;
    }
    public boolean borrowItem(LibraryItem item){
        if(borrowedItems.size() >= maxBorrowItems){
            System.out.println("Cannot borrow more items. Maximum limit reached.");
            return false;
        }
        if (outstandingFines > 0) {
            System.out.println("Cannot borrow items until outstanding fines are paid.");
            return false;
        }
        borrowedItems.add(item);
        return true;
    }
    public boolean returnItem(LibraryItem item){
        if (!borrowedItems.contains(item)) {
            System.out.println("This item was not borrowed by this patron.");
            return false;
        }
        borrowedItems.remove(item);
        return true;
    }
    // Calculate total fines from all borrowed items
    public double calculateTotalFines() {
        double totalFine = outstandingFines;

        for (LibraryItem item : borrowedItems) {
            totalFine += item.calculateFine();
        }

        return totalFine;
    }
    // Pay an amount towards outstanding fines
    public boolean payFine(double amount) {
        if (amount <= 0) {
            System.out.println("Payment amount must be positive.");
            return false;
        }

        if (outstandingFines == 0) {
            System.out.println("No outstanding fines to pay.");
            return false;
        }

        if (amount > outstandingFines) {
            System.out.println("Payment amount exceeds outstanding fines. Processing payment of $" + outstandingFines);
            amount = outstandingFines;
        }

        outstandingFines -= amount;
        System.out.println("Payment of $" + amount + " processed. Remaining fines: $" + outstandingFines);
        return true;
    }
// Get the number of items currently borrowed
    public int getItemCount() {
        return borrowedItems.size();
    }
    public void addFine(double amount) {
        if (amount > 0) {
            this.outstandingFines += amount;
            System.out.println("Fine of $" + amount + " added. Total outstanding fines: $" + this.outstandingFines);
        }
    }
    public void displayBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("No items currently borrowed.");
            return;
        }

        System.out.println("Currently borrowed items:");
        for (LibraryItem item : borrowedItems) {
            System.out.println("- " + item.getTitle() + " (Due: " + item.getDueDate() + ")");
        }
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nRole: " + getRole() +
                "\nBorrowed Items: " + getItemCount() +
                "\nOutstanding Fines: $" + outstandingFines;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public double getOutstandingFines() {
        return outstandingFines;
    }

    public int getMaxBorrowItems() {
        return maxBorrowItems;
    }

    public void setBorrowedItems(List<LibraryItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public void setOutstandingFines(double outstandingFines) {
        this.outstandingFines = outstandingFines;
    }

    public void setMaxBorrowItems(int maxBorrowItems) {
        this.maxBorrowItems = maxBorrowItems;
    }
}
