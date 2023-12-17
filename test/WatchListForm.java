import javax.swing.*;
import java.awt.*;

public class WatchListForm extends JFrame {

    public WatchListForm(MainApplication mainApplication) {
        super("Watchlist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 500);

        MovieTable movieTable = new MovieTable(mainApplication, mainApplication.getWatchList(), "watchlist");
        JPanel mainPanel = new JPanel();
        mainPanel.add(movieTable.getTablePanel());
        add(mainPanel);
    }
}