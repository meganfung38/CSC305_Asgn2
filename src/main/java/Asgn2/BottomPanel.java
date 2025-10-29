package Asgn2;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;

// immutable text area
// "Selected File Name: <current filename of last clicked square>"

public class BottomPanel extends JPanel {

    // components
    private final JTextField selectedFileGrid;

    public  BottomPanel() {

        // config
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Selected File Name: ");

        // initialize components
        selectedFileGrid = new JTextField();
        selectedFileGrid.setEditable(false); // static (non editable)

        // add to panel
        add(label, BorderLayout.WEST);
        add(selectedFileGrid, BorderLayout.CENTER);

    }

    public void setSelectedFileGrid(String file) {

        // write to text field
        selectedFileGrid.setText(file);

    }

}
