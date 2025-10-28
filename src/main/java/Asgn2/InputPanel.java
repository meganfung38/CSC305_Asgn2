package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {

    // components
    private final JTextField urlInput;
    private final JButton okButton;

    public InputPanel(ActionListener actionListener) {

        // config
        setLayout(new BorderLayout(5, 5));

        // initialize components
        urlInput = new JTextField("https://github.com/CSC3100/Tool-Maven/tree/main/src/main/java");
        okButton = new JButton("Ok");
        okButton.addActionListener(actionListener);

        // add to panel
        add(urlInput, BorderLayout.CENTER);
        add(okButton, BorderLayout.EAST);

    }

    // get method
    public String getUrl() {
        return urlInput.getText().trim();
    }

}
