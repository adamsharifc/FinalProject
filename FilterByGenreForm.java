import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FilterByGenreForm extends JFrame {

    public FilterByGenreForm(MainApplication mainApplication, ArrayList<String> commonGenres) {
        super("Filter by Genre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,  600);

        MovieTable movieTable = new MovieTable(mainApplication, mainApplication.getAllMovies());

        JPanel mainPanel = new JPanel();
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        for (int i = 0; i < Math.min(5, commonGenres.size()); i++) {
            JButton genreButton = new JButton(commonGenres.get(i));
            genreButton.addActionListener(e -> {
                // Handle button click
                Object[][] data = mainApplication.getFilteredMovies(genreButton.getText());
                System.out.println(data[0][0]);
                // add code here that will update the table
                System.out.println("Updating table");
                movieTable.updateTable(data);
                
            });
            topBar.add(genreButton);
        }
        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(movieTable.getTablePanel());

        JTextField genreSearchField = new JTextField(20);
        genreSearchField.addActionListener(e -> {
            // Handle enter key press
            Object[][] data = mainApplication.getFilteredMovies(genreSearchField.getText());
            System.out.println(data[0][0]);
        });
        topBar.add(genreSearchField);

        JButton filterButton = new JButton("Filter");
        filterButton.addActionListener(e -> {
            // Handle button click
            Object[][] data = mainApplication.getFilteredMovies(genreSearchField.getText());
            System.out.println(data[0][0]);
            genreSearchField.setText("");
            // add code here that will update the table
            System.out.println("Updating table");
            movieTable.updateTable(data);            

        });
        topBar.add(filterButton);
        add(mainPanel);

    }
}