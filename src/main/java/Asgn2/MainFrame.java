package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

// class for combining all components

public class MainFrame extends JFrame {

    // components
    private final GHOperations ghOperations;
    private final TopBar topBar;
    private final CenterPanel centerPanel;

    public MainFrame() {

        // config
        setTitle("Assignment 02");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setVisible(true);

        // initialize environment
        EnvLoader.loadEnv(".env");
        String token = System.getProperty("GH_TOKEN");
        ghOperations = new GHOperations(token);

        // initialize components
        topBar = new TopBar(this::onOkClicked);
        BottomPanel bottomPanel = new BottomPanel();
        centerPanel = new CenterPanel(bottomPanel);
        setJMenuBar(new MenuBar(this));

        // add to frame
        add(topBar, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

    }

    public void onOkClicked(ActionEvent actionEvent) {

        String url = topBar.getUrl().trim();  // get url

        // validate URL
        if (!url.startsWith("https://github.com/") || !url.contains("/tree/")) {

            // update center panel
            centerPanel.displayError("Not a valid GH URL.");
            return;
        }

        try {

            // analyze files in GH URL
            FileAnalyzer fileAnalyzer = new FileAnalyzer(ghOperations);
            List<AnalyzedFile> analyzedFiles = fileAnalyzer.analyzeFiles(url);

            if (analyzedFiles == null || analyzedFiles.isEmpty()) {  // no .java files or unsuccessful analysis

                // update center panel
                centerPanel.displayError("No .java files found in GH URL.");
                return;

            }

            centerPanel.updateFiles(analyzedFiles);  // render square grid

        } catch (IllegalArgumentException | IOException e)  {

            centerPanel.displayError(e.getMessage());

        }

    }

    public void clearGrid() {

        centerPanel.clearGrid(); // refresh center panel for new GH URL
        topBar.resetUrl();  // reset top bar for new GH URL

    }

}
