import javax.swing.*;
import java.awt.*;

public class SearchMovieForm extends JFrame {

    public SearchMovieForm(MainApplication mainApplication, String query) {
        super("Search for a Movie");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            // String csv = searchMovie();
            // mainApplication.handleSearchMovie(csv);
        });
        buttonPanel.add(searchButton);

        // Add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // Create table
        String[] columnNames = {"Title", "Genre", "Rating", "Year", "Director", "Watchlist"};
        Object[][] data = mainApplication.getSearchResults(query);
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add scroll pane to main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

}