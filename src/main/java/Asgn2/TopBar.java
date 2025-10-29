package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// text field input
// labelled GitHub Folder URL (for users to paste GH URLs)

public class TopBar extends JPanel {

    // components
    private final JTextField urlInput;

    public TopBar(ActionListener actionListener) {

        // config
        setLayout(new BorderLayout(5, 5));

        // initialize components
        urlInput = new JTextField("Insert GitHub Folder URL");
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(actionListener);

        // add to panel
        add(urlInput, BorderLayout.CENTER);
        add(okButton, BorderLayout.EAST);

    }

    // get method
    public String getUrl() {
        return urlInput.getText().trim();
    }

    // set method
    public  void resetUrl() {
        urlInput.setText("Insert GitHub Folder URL");
    }

}
