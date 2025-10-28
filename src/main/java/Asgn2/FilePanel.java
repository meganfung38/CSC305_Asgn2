package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilePanel extends JPanel {

    // components
    private final JTextArea gh_files;

    public FilePanel() {

        // config
        setLayout(new BorderLayout());

        // initialize component
        gh_files = new JTextArea();
        gh_files.setEditable(false);
        gh_files.setBackground(Color.yellow);
        JScrollPane scrollPane = new JScrollPane(gh_files);

        // add to panel
        add(scrollPane, BorderLayout.CENTER);

    }

    public void show_files(List<String> files) {

        gh_files.setText(""); // clear
        if (files.isEmpty()) {
            gh_files.append("No gh_files found\n");
        } else {
            for (String file : files) {
                gh_files.append(file + "\n");
            }
        }
    }

    public void displayError(String message) {

        gh_files.setText("Error: " + message);

    }

}
