package views;
import javax.swing.*;
import java.awt.event.*;

public class MovieForm extends JPanel {
    private JTextField titleField;
    private JTextField genreField;
    private JTextField ratingField;
    private JTextField yearField;

    public MovieForm() {
        titleField = new JTextField(20);
        genreField = new JTextField(20);
        ratingField = new JTextField(20);
        yearField = new JTextField(20);

        JButton addButton = new JButton("Add Movie");
        addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String genre = genreField.getText();
            double rating = 0;
            int year = 0;

            try {
                rating = Double.parseDouble(ratingField.getText());
            } catch (NumberFormatException ex) {
                // Handle error, e.g., show an error message to the user
                System.out.println("Invalid rating");
                return;
            }

            try {
                year = Integer.parseInt(yearField.getText());
            } catch (NumberFormatException ex) {
                // Handle error, e.g., show an error message to the user
                System.out.println("Invalid year");
                return;
            }

            // Movie movie = new Movie(title, genre, rating, year);
            // main.addMovie(movie.toString());

            titleField.setText("");
            genreField.setText("");
            ratingField.setText("");
            yearField.setText("");
        }
    });

        add(addButton);
    }
}