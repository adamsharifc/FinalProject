import views.MovieForm;

import java.lang.reflect.Array;

import javax.swing.JFrame;
import java.util.ArrayList;


public class main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MovieLibrary movieLibrary = new MovieLibrary();
        String csv = "The Godfather,Crime,9.2,1972,Francis Ford Coppola,true";
        // movieLibrary.addMovie(csv, true);
        // movieLibrary.deleteMovie("0");
        // movieLibrary.updateMovie("2", "rating", "8.5");
        // movieLibrary.printMovies(movieLibrary.getAllMovies());
    }
}