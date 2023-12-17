import javax.swing.*;
import java.awt.*;

public class ExportMovieForm extends JFrame {

    public ExportMovieForm(MainApplication mainApplication) {
        super("Export Movie Library");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0); // Add space between buttons

        JButton exportBin = new JButton("Export to .bin");
        exportBin.setPreferredSize(new Dimension(150, 50)); // Make button larger
        exportBin.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this, "Enter filename:");
            mainApplication.handleExportMovie(filename, ".bin");
        });
        buttonPanel.add(exportBin, gbc);

        JButton exportCsv = new JButton("Export to .csv");
        exportCsv.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this, "Enter filename:");
            mainApplication.handleExportMovie(filename, ".csv");

        });
        exportCsv.setPreferredSize(new Dimension(150, 50)); // Make button larger
        buttonPanel.add(exportCsv, gbc);

        JButton exportTxt = new JButton("Export to .txt");
        exportTxt.setPreferredSize(new Dimension(150, 50)); // Make button larger
        exportTxt.addActionListener(e -> {
            String filename = JOptionPane.showInputDialog(this, "Enter filename:");
            mainApplication.handleExportMovie(filename, ".txt");
        });
        buttonPanel.add(exportTxt, gbc);

        // Add button panel to main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}