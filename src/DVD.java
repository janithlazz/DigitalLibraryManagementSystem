import java.time.LocalDate;

public class DVD extends LibraryItem{
    private String director;
    private int runTimeMinutes;
    private String rating;
    private int releaseYear;

    public DVD(String id, String title, double dailyFine, String director, int runTimeMinutes, String rating, int releaseYear) {
        // Call the parent constructor with basic properties
        super(id, title, dailyFine);

        // Initialize DVD-specific properties
        this.director = director;
        this.runTimeMinutes = runTimeMinutes;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    @Override
    public String getItemType() {
        return null;
    }

    public String getDirector() {
        return director;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public String getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRunTimeMinutes(int runTimeMinutes) {
        this.runTimeMinutes = runTimeMinutes;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
