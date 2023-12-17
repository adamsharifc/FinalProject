import javax.swing.*;
import java.awt.*;

public class AddMovieForm extends JFrame {
    private MainApplication mainApplication;
    private JTextField titleField;
    private JTextField genreField;
    private JTextField ratingField;
    private JTextField yearField;
    private JTextField directorField;
    private JTextField isWatchListField;


    public AddMovieForm(MainApplication mainApplication) {
        super("Add Movie");
        this.mainApplication = mainApplication;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);

        // Create panel for form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add space between components

        // Create form fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Genre:"), gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Rating:"), gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Year:"), gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Director:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        titleField = new JTextField(10);
        formPanel.add(titleField, gbc);
        gbc.gridy++;
        genreField = new JTextField(10);
        formPanel.add(genreField, gbc);
        gbc.gridy++;
        ratingField = new JTextField(10);
        formPanel.add(ratingField, gbc);
        gbc.gridy++;
        yearField = new JTextField(10);
        formPanel.add(yearField, gbc);
        gbc.gridy++;
        directorField = new JTextField(10);
        formPanel.add(directorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Make the button span two columns
        JButton addButton = new JButton("Add Movie");
        formPanel.add(addButton, gbc);

        // Reset gridwidth for future components
        gbc.gridwidth = 1;

        add(formPanel);
    }

    private String addMovie() {
        // Implement the logic to add a movie
        String title = titleField.getText();
        String genre = genreField.getText();
        String rating = ratingField.getText();
        String year = yearField.getText();
        String director = directorField.getText();
        String isWatchList = isWatchListField.getText();

    
        // Create a CSV string with the form data
        String csv = title + "," + genre + "," + rating + "," + year + "," + director + "," + isWatchList;
    
        // Clear the form fields
        titleField.setText("");
        genreField.setText("");
        ratingField.setText("");
        yearField.setText("");
        directorField.setText("");
        isWatchListField.setText("");

        return csv;
    }

}