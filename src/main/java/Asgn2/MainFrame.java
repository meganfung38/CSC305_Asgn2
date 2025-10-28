package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class MainFrame extends JFrame {

    // components
    private final GHOperations gh_operations;
    private final InputPanel inputPanel;
    private final FilePanel filePanel;

    public MainFrame() {

        // config
        setTitle("Title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setVisible(true);

        // initialize components
        EnvLoader.loadEnv(".env");
        String token = System.getProperty("GH_TOKEN");
        System.out.println("Token loaded: " + (token != null && !token.isBlank()));
        gh_operations = new GHOperations(token);
        inputPanel = new InputPanel(this::onOkClicked);
        filePanel = new FilePanel();

        // add to frame
        add(inputPanel, BorderLayout.NORTH);
        add(filePanel, BorderLayout.CENTER);

    }

    public void onOkClicked(ActionEvent actionEvent) {

        String url = inputPanel.getUrl();  // get url

        try {
            List<String> files = gh_operations.listFilesRecursive(url);  // get files
            filePanel.show_files(files);  // display files
        } catch (IOException e) {
            filePanel.displayError(e.getMessage());
        }

    }

}
