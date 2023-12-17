import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {
    private JFrame mainFrame;
    private AddMovieForm addMovieForm;
    private SearchMovieForm searchMovieForm;
    private WatchListForm watchListForm;
    private ExportMovieForm exportMovieForm;
    private FilterByGenreForm filterByGenreForm;
    private MovieTable movieTable;

    private JPanel mainPanel;

    MovieLibrary movieLibrary = new MovieLibrary();
    String[] columnNames = {"Title", "Genre", "Rating", "Year", "Director", "Watchlist"};
    JTable mainPageTable;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MainApplication();
        });
    }

    public MainApplication() {
        mainFrame = new JFrame("Movie Library Manager");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 500);

       
        // Create main panel
        mainPanel = new JPanel();
        mainFrame.add(mainPanel);
       
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Forms
        addMovieForm = new AddMovieForm(this);
        watchListForm = new WatchListForm(this);
        exportMovieForm = new ExportMovieForm(this);
        filterByGenreForm = new FilterByGenreForm(this, movieLibrary.getCommonGenres());

        // Create buttons
        addButton("Add a Movie", (ActionEvent e) -> openChildWindow(addMovieForm), mainPanel);
        addButton("Filter by Genre", (ActionEvent e) -> openChildWindow(filterByGenreForm), mainPanel);
        addButton("View Watchlist", (ActionEvent e) -> openChildWindow(watchListForm), mainPanel);
        addButton("Export Movies", (ActionEvent e) -> openChildWindow(exportMovieForm), mainPanel);

        // Search bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 24));
        mainPanel.add(searchBar, BorderLayout.NORTH);
        JButton searchButton = new JButton("Search");        
        searchButton.addActionListener(e -> {
            String query = searchBar.getText();
            openSearchWindow(query);
        });
        mainPanel.add(searchButton, BorderLayout.NORTH);

        // Get data for the table
        Object[][] data = movieLibrary.getMoviesTabled(movieLibrary.getAllMovies());
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // This causes all cells to be not editable
            }
        };
        
        showMovieTable();
        mainFrame.setVisible(true);

    }

    public void showMovieTable(){

        movieTable = new MovieTable(this, movieLibrary.getMoviesTabled(movieLibrary.getAllMovies()));
        mainPanel.add(movieTable.getTablePanel(), BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void handleAddMovie(String csv){
        movieLibrary.addMovie(csv, true);
        addMovieForm.dispose();
        refreshTable();
    }
    public void handleExportMovie(String filename, String format){
        movieLibrary.exportMovieLibrary(filename, format);
        exportMovieForm.dispose();
    }
    public void handleDeleteMovie(String id){
        movieLibrary.deleteMovie(id);
        refreshTable();
    }
    public void handleUpdateMovie(String id, String title, String genre, String rating, String year, String director){
        movieLibrary.updateMovie(id, title, genre, rating, year, director);
        refreshTable();
    }
    public void handleUpdateMovieWatchlist(String id, boolean watchlist){
        movieLibrary.updateMovieWatchlist(id, watchlist);
        refreshTable();
    }
    public Object[][] getFilteredMovies(String genre){
        return movieLibrary.getMoviesTabled(movieLibrary.searchMoviesExact(genre, "genre"));
    }

    public Object[][] getSearchResults(String query){
        return movieLibrary.getMoviesTabled(movieLibrary.searchMovies(query, "title"));
    }

    public Object[][] getWatchList(){
        return movieLibrary.getMoviesTabled(movieLibrary.getWatchList());
    }
    public Object[][] getAllMovies(){
        return movieLibrary.getMoviesTabled(movieLibrary.getAllMovies());
    }


    private void addButton(String label, ActionListener actionListener, JPanel panel) {
        JButton button = new JButton(label);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void openChildWindow(JFrame childFrame) {
        if (childFrame != null) {
            childFrame.setVisible(true);
        }
    }
    private void openSearchWindow(String query){
        SearchMovieForm searchMovieForm = new SearchMovieForm(this, query);
        if (searchMovieForm != null){
            searchMovieForm.setVisible(true);

        }
    }
    private void refreshTable() {
        // Get the new data
        mainPanel.remove(movieTable.getTablePanel());
        movieTable = new MovieTable(this, movieLibrary.getMoviesTabled(movieLibrary.getAllMovies()));
        mainPanel.add(movieTable.getTablePanel(), BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
