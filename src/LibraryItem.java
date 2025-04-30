import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isCheckedOut;
    private LocalDate dueDate;
    private double dailyFine;
    private Person borrower;

    public LibraryItem(String id, String title, boolean isCheckedOut, LocalDate dueDate, double dailyFine, Person borrower) {
        this.id = id;
        this.title = title;
        this.isCheckedOut = isCheckedOut;
        this.dueDate = dueDate;
        this.dailyFine = dailyFine;
        this.borrower = borrower;
    }

    public LibraryItem(String id, String title, double dailyFine) {
    }

    public abstract String getItemType();
    public boolean checkOut(Person patron){
        if (isCheckedOut) {
            System.out.println("Item is already checked out.");
            return false;
        }
        if (!(patron instanceof Patron)) {
            System.out.println("Only patrons can check out items.");
            return false;
        }
        Patron borrowerPatron = (Patron) patron;
        if (borrowerPatron.getItemCount() >= borrowerPatron.getMaxBorrowItems()) {
            System.out.println("Patron has reached maximum borrowing limit.");
            return false;
        }
        // Set checkout status
        this.isCheckedOut = true;
        this.borrower = patron;

        // Set due date to 14 days from today
        this.dueDate = LocalDate.now().plusDays(14);
        borrowerPatron.borrowItem(this);
        System.out.println("Item checked out successfully. Due date: " + dueDate);
        return true;
    }
    public boolean returnItem(){
        if (!isCheckedOut) {
            System.out.println("Item is not checked out.");
            return false;
        }
        // Calculate fine if item is overdue
        double fine = calculateFine();
        if (fine > 0) {
            System.out.println("Item is overdue. Fine: $" + fine);
            if (borrower instanceof Patron) {
                ((Patron) borrower).addFine(fine);
            }
        }
        if (borrower instanceof Patron) {
            ((Patron) borrower).returnItem(this);
        }

        // Reset checkout status
        this.isCheckedOut = false;
        this.dueDate = null;
        this.borrower = null;

        System.out.println("Item returned successfully.");
        return true;
    }
    public double calculateFine(){
        if (!isCheckedOut || dueDate == null) {
            return 0.0;
        }

        LocalDate today = LocalDate.now();
        if (today.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, today);
            return daysLate * dailyFine;
        }

        return 0.0;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Title: " + title +
                ", Type: " + getItemType() +
                ", Status: " + (isCheckedOut ? "Checked Out" : "Available") +
                (isCheckedOut ? ", Due Date: " + dueDate : "");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public double getDailyFine() {
        return dailyFine;
    }

    public Person getBorrower() {
        return borrower;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDailyFine(double dailyFine) {
        this.dailyFine = dailyFine;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }
}
