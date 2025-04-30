import java.time.LocalDate;

public class Book extends LibraryItem{

    private String author;
    private String isbn;
    private int yearPublished;
    private String genre;
    private int pageCount;

    public Book(String id, String title, double dailyFine, String author, String isbn, int yearPublished, String genre, int pageCount) {
        // Call the parent constructor with just the basic properties
        super(id, title, dailyFine);

        // Initialize Book-specific properties
        this.author = author;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    @Override
    public String getItemType() {
        return null;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public String getGenre() {
        return genre;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
