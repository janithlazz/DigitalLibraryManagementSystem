import java.time.LocalDate;

public class Magazine extends LibraryItem{
    private String publisher;
    private LocalDate issueDate;
    private int issueNumber;
    private String category;

    public Magazine(String id, String title, double dailyFine, String publisher, LocalDate issueDate, int issueNumber, String category) {
        // Call the parent constructor with basic properties
        super(id, title, dailyFine);

        // Initialize Magazine-specific properties
        this.publisher = publisher;
        this.issueDate = issueDate;
        this.issueNumber = issueNumber;
        this.category = category;
    }

    @Override
    public String getItemType() {
        return null;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
