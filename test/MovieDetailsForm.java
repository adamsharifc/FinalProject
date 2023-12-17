import javax.swing.*;
import java.awt.*;

public class MovieDetailsForm extends JFrame {
    private MainApplication mainApplication;
    private JTextField titleField, genreField, ratingField, yearField, directorField;
    private String id;
    private MovieTable movieTable;

    public MovieDetailsForm(MainApplication mainApplication, Object[] data) {
        super(data[0].toString());
        id = data[6].toString();
        this.mainApplication = mainApplication;
        setSize(400, 400);

        // Create panel for form fields
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 2, 2, 2); // Add space between components

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

        titleField = new JTextField(data[0].toString(), 20);
        formPanel.add(titleField, gbc);
        gbc.gridy++;
        genreField = new JTextField(data[1].toString(), 10);
        formPanel.add(genreField, gbc);
        gbc.gridy++;
        ratingField = new JTextField(data[2].toString(), 10);
        formPanel.add(ratingField, gbc);
        gbc.gridy++;
        yearField = new JTextField(data[3].toString(), 10);
        formPanel.add(yearField, gbc);
        gbc.gridy++;
        directorField = new JTextField(data[4].toString(), 10);
        formPanel.add(directorField, gbc);

        // Create "Save" and "Delete" buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Make the buttons span two columns
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {

            // Update the movie properties
            System.out.println("Save button clicked");
            mainApplication.handleUpdateMovie(
                id, 
                titleField.getText(),
                genreField.getText(), 
                ratingField.getText(), 
                yearField.getText(), 
                directorField.getText()
            );
            dispose();
        });
        formPanel.add(saveButton, gbc);

        gbc.gridy++;
        JButton deleteButton = new JButton("Delete Movie");
        deleteButton.addActionListener(e -> {
            // Delete the movie
            // mainApplication.handleDeleteMovie(movie.getID());
        });
        formPanel.add(deleteButton, gbc);
        
        gbc.gridy++;
        final boolean[] watchlistStatus = new boolean[1];
        watchlistStatus[0] = Boolean.parseBoolean(data[5].toString());
        
        JButton toggleWatchlistButton = new JButton(watchlistStatus[0] ? "Remove from Watchlist" : "Add to Watchlist");
        toggleWatchlistButton.addActionListener(e -> {

            toggleWatchlistButton.setText(watchlistStatus[0] ? "Remove from Watchlist" : "Add to Watchlist");
            watchlistStatus[0] = !watchlistStatus[0];
            // mainApplication.handleSaveMovie(data[6].toString());
        });
        formPanel.add(toggleWatchlistButton, gbc);

        // Reset gridwidth for future components
        gbc.gridwidth = 1;

        add(formPanel);
    }
}