import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class MovieLibrary {
    
    // static variables
    private static Integer serialMovieCount = 0;
    private static Integer numMovies = 0;
    private static Integer numWatchList = 0;
    private static ArrayList<Movie> movies = new ArrayList<Movie>();

    public MovieLibrary(){
        serialMovieCount = 0;
        numMovies = 0;
        numWatchList = 0;
        readMovies();  
    }
    // static methods
    public static String toCSV(){
        String csv = serialMovieCount.toString() + '\n';
        for (Movie movie : movies) {
            csv += movie.getID() + ',' + movie.getTitle() + ',' + movie.getGenre() + ',' + movie.getRating() + ',' + movie.getYear() + ',' + movie.getDirector() + ',' + movie.getIsWatchList() + '\n';
        }
        return csv;
    }
    public static String toTXT(){
        String format = "%-10s%-30s%-15s%-10s%-10s%-30s%-10s%n";
        String txt = String.format(format, "ID", "Title", "Genre", "Rating", "Year", "Director", "Watchlist");
        for (Movie movie : movies) {
            txt += String.format(format, movie.getID(), movie.getTitle(), movie.getGenre(), movie.getRating(), movie.getYear(), movie.getDirector(), movie.getIsWatchList());
        }
        return txt;
    }

    public static int getNumMovies() {
        return numMovies;
    }
    public static int getNumWatchList() {
        return numWatchList;
    }
    public static ArrayList<Movie> getMovies() {
        return movies;
    }
    public static void incrementNumMovies() {
        numMovies++;
    }
    public static void incrementNumWatchList() {
        numWatchList++;
    }
    public static void setSerialMovieCount(int newNumMovies) {
        serialMovieCount = newNumMovies;
    }
    public static void setNumWatchList(int newNumWatchList) {
        numWatchList = newNumWatchList;
    }
    public static void addMovie(String csv, Boolean isNew){
        csv = serialMovieCount.toString() + ',' + csv;
        Movie movie = new Movie(csv);
        movies.add(movie);
        if (isNew){
            serialMovieCount++;
        }
        numMovies++;
        if (movie.getIsWatchList()){
            numWatchList++;
        }
        dumpMovies();
    }
    public static void deleteMovie(String id){
        int index = 0;
        boolean none_found = true;

        for (Movie movie : movies) {
            if (movie.getID().equals(id)) {
                none_found = false;
                break;
            }
            index++;
        }
        if (none_found) {
            System.out.println("No movie found with that ID.");
        } else {
            movies.remove(index);
            numMovies--;
            dumpMovies();
            System.out.println("Movie deleted.");
        }
    }
    public static void updateMovie(String id, String title, String genre, String rating, String year, String director){
        boolean none_found = true;
        for (Movie movie : movies){
            if (movie.getID().equals(id)){
                if (movie != null){
                    none_found = false;
                    movie.setTitle(title);
                    movie.setGenre(genre);
                    movie.setRating(Float.parseFloat(rating));
                    movie.setYear(Integer.parseInt(year));
                    movie.setDirector(director);
                    dumpMovies();
                }
            }
        }
        if (none_found){
            System.out.println("No movie found with that ID.");
        }
    }
    
    public static void updateMovieWatchlist(String id, boolean watchlist){
        boolean none_found = true;
        for (Movie movie : movies){
            if (movie.getID().equals(id)){
                if (movie != null){
                    none_found = false;
                    movie.setIsWatchList(watchlist);
                    dumpMovies();
                }
            }
        }
        if (none_found){
            System.out.println("No movie found with that ID.");
        }
    }
    public static ArrayList<Movie> searchMovies(String query, String field){
        boolean none_found = true;
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movies){
            if (field.equals("title")){
                if (movie.getTitle().toLowerCase().contains(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("genre")){
                if (movie.getGenre().toLowerCase().contains(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("rating")){
                if (Float.toString(movie.getRating()).contains(query)){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("year")){
                if (Integer.toString(movie.getYear()).contains(query)){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("director")){
                if (movie.getDirector().toLowerCase().contains(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("watchlist")){
                if (Boolean.toString(movie.getIsWatchList()).contains(query)){
                    results.add(movie);
                    none_found = false;
                }
            }
        }
        if (none_found){
            System.out.println("No movies found.");
        }
        return results;
    }
    public static ArrayList<Movie> searchMoviesExact(String query, String field){
        boolean none_found = true;
        ArrayList<Movie> results = new ArrayList<Movie>();
        for (Movie movie : movies){
            if (field.equals("title")){
                if (movie.getTitle().toLowerCase().equals(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("genre")){
                if (movie.getGenre().toLowerCase().equals(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("rating")){
                if (Float.toString(movie.getRating()).equals(query)){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("year")){
                if (Integer.toString(movie.getYear()).equals(query)){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("director")){
                if (movie.getDirector().toLowerCase().equals(query.toLowerCase())){
                    results.add(movie);
                    none_found = false;
                }
            } else if (field.equals("watchlist")){
                if (Boolean.toString(movie.getIsWatchList()).equals(query)){
                    results.add(movie);
                    none_found = false;
                }
            }
        }
        if (none_found){
            System.out.println("No movies found.");
        }
        return results;
    }

    public static ArrayList<Movie> getAllMovies(){
        return movies;
    }
    public static void dumpMovies(){
        File moviesFile = new File("data.bin");

        try (FileOutputStream fos = new FileOutputStream(moviesFile)){
            fos.write(toCSV().getBytes());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void readMovies(){
        File file = new File("data.bin");
        if (file.length() == 0) {
            System.out.println("Error: Empty file.");
            return;
        }
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            setSerialMovieCount(Integer.parseInt(line));

            while (line != null){
                line = reader.readLine();
                if (line != null){
                    Movie movie = new Movie(line);
                    movies.add(movie);
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static ArrayList<Movie> getWatchList(){
        ArrayList<Movie> watchList = new ArrayList<Movie>();
        for (Movie movie : movies){
            if (movie.getIsWatchList()){
                watchList.add(movie);
            }
        }
        return watchList;
    }
    public static ArrayList<Movie> sortMovies(ArrayList<Movie> mList, String field, String order){
        
        if (field.equals("title")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> m2.getTitle().compareTo(m1.getTitle()));
            }
        } else if (field.equals("genre")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> m1.getGenre().compareTo(m2.getGenre()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> m2.getGenre().compareTo(m1.getGenre()));
            }
        } else if (field.equals("rating")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> Float.compare(m1.getRating(), m2.getRating()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> Float.compare(m2.getRating(), m1.getRating()));
            }
        } else if (field.equals("year")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> Integer.compare(m1.getYear(), m2.getYear()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> Integer.compare(m2.getYear(), m1.getYear()));
            }
        } else if (field.equals("director")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> m1.getDirector().compareTo(m2.getDirector()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> m2.getDirector().compareTo(m1.getDirector()));
            }
        } else if (field.equals("watchlist")){
            if (order.equals("ascending")){
                mList.sort((Movie m1, Movie m2) -> Boolean.compare(m1.getIsWatchList(), m2.getIsWatchList()));
            } else if (order.equals("descending")){
                mList.sort((Movie m1, Movie m2) -> Boolean.compare(m2.getIsWatchList(), m1.getIsWatchList()));
            }
        }
        return mList;
    }  
    public static void updateMovieByField(String id, String field, String value){
        boolean none_found = true;
        for (Movie movie : movies){
            if (movie.getID().equals(id)){
                none_found = false;
                if (field.equals("title")){
                    movie.setTitle(value);
                } else if (field.equals("genre")){
                    movie.setGenre(value);
                } else if (field.equals("rating")){
                    movie.setRating(Float.parseFloat(value));
                } else if (field.equals("year")){
                    movie.setYear(Integer.parseInt(value));
                } else if (field.equals("director")){
                    movie.setDirector(value);
                } else if (field.equals("watchlist")){
                    movie.setIsWatchList(Boolean.parseBoolean(value));
                }
                dumpMovies();
            }
        }
        if (none_found){
            System.out.println("No movie found with that ID.");
        }
    }
    public static void printMovies(ArrayList<Movie> mList){
        for (Movie movie : mList){
            System.out.println(movie.toString());
        }
    }

    public static Object[][] getMoviesTabled(ArrayList<Movie> mList){
        Object[][] data = new Object[mList.size()][7];
        int i = 0;
        for (Movie movie : mList){
            data[i][0] = movie.getTitle();
            data[i][1] = movie.getGenre();
            data[i][2] = movie.getRating();
            data[i][3] = movie.getYear();
            data[i][4] = movie.getDirector();
            data[i][5] = movie.getIsWatchList();
            data[i][6] = movie.getID();
            i++;
        }
        return data;
    }
    public static void exportMovieLibrary(String filename, String format){
        File moviesFile = new File(filename + format);
        try (FileOutputStream fos = new FileOutputStream(moviesFile)){
            if (format.equals(".txt")){
                fos.write(toTXT().getBytes());
            } else{
                fos.write(toCSV().getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static ArrayList<String> getCommonGenres(){
        ArrayList<String> genres = new ArrayList<String>();
        for (Movie movie : movies){
            if (!genres.contains(movie.getGenre())){
                genres.add(movie.getGenre());
            }
        }
        return genres;
    }
}