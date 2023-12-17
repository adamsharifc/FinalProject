import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class MovieTable {
    private JTable mainPageTable;
    private MainApplication mainApplication;
    private JPanel tablePanel;
    private Object[][] data;
    String[] columnNames = {"Title", "Genre", "Rating", "Year", "Director", "Watchlist"};
    public MovieTable(MainApplication mainApplication, Object[][] data) {
        this.mainApplication = mainApplication;
        this.data = data;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(1000, 500));

        // Get data for the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // This causes all cells to be not editable
            }
        };

        // Create the table
        mainPageTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(mainPageTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Add double-click listener to the table
        mainPageTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Check for double click
                    int selectedRow = mainPageTable.getSelectedRow();
                    if (selectedRow != -1) { // Check if a row is selected
                        Object[] rowData = data[selectedRow];

                        // Open a new window with the row data
                        MovieDetailsForm movieDetailsForm = new MovieDetailsForm(mainApplication, rowData);
                        movieDetailsForm.setVisible(true);
                    }
                }
            }
        });
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public void updateTable(Object[][] data) {
        this.data = data;
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // This causes all cells to be not editable
            }
        };
        mainPageTable.setModel(model);
        tablePanel.revalidate();
        tablePanel.repaint();

    }
}