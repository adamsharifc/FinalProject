public class Movie {
    
    // instance variables
    private String id;
    private String title;
    private String genre;
    private float rating;
    private Integer year;
    private String director;
    private boolean isWatchList;

    // constructors
    public Movie(String id, String title, String genre, float rating, Integer year, String director, boolean isWatchList) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.director = director;
        this.isWatchList = isWatchList;
    }
    public Movie(String record){
        String[] fields = record.split(",");
        this.id = fields[0];
        this.title = fields[1];
        this.genre = fields[2];
        this.rating = Float.parseFloat(fields[3]);
        this.year = Integer.parseInt(fields[4]);
        this.director = fields[5];
        this.isWatchList = Boolean.parseBoolean(fields[6]);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", isWatchList=" + isWatchList +
                '}';
    }


    // instance methods
    public String getID() {
        return id;
    }   
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public float getRating() {
        return rating;
    }
    public Integer getYear() {
        return year;
    }
    public String getDirector() {
        return director;
    }
    public boolean getIsWatchList() {
        return isWatchList;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setIsWatchList(boolean isWatchList) {
        this.isWatchList = isWatchList;
    }
}