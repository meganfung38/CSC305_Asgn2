package Asgn2;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// grid of squares visualizing size and complexity summary of .java files in GH URL
// each square represents one .java file

public class CenterPanel extends JPanel {

    // components
    private List<AnalyzedFile> files;
    private Rectangle[] squares;
    private int clickedSquare = -1;
    private final BottomPanel bottomPanel;

    public CenterPanel(BottomPanel bottomPanel) {

        // config
        setBackground(Color.white);
        setToolTipText("");

        // initialize components
        this.bottomPanel = bottomPanel;

        // enable mouse clicking events
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {  // trigger for square selection
                squareClicked(e.getPoint());
            }

            @Override
            public void mouseMoved (MouseEvent e) {  // trigger for square hover
                squareHover(e.getPoint());
            }

        };

        // add to panel
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
    }

    public void clearGrid() {

        // clear all visualized data on grid and stored metrics
        files = null;
        squares = null;
        clickedSquare = -1;
        repaint();
        bottomPanel.setSelectedFileGrid("");

    }

    public void displayError(String message) {

        // clear all visualized data on grid and stored metrics
        clearGrid();

        // render error message
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

    }

    public void updateFiles(List<AnalyzedFile> analyzedFiles) {

        clearGrid();  // clear all visualized data on grid and stored metrics
        this.files = analyzedFiles;  // store analyzed files
        this.squares = (files == null) ? null : new Rectangle[files.size()];  // initialize squares

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);  // clear old drawings

        // no .java files
        if (files == null || files.isEmpty()) { return; }

        // config
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int totalFiles = files.size();  // number of files in GH URL

        // get square dimensions
        int cols = (int) Math.ceil(Math.sqrt(totalFiles));
        int rows = (int) Math.ceil(totalFiles / (double) cols);

        // config for grid of squares
        int padding = 12;
        int w = getWidth() - padding * 2;
        int h = getHeight() - padding * 2;
        int gridWidth = Math.max(24, w / cols);
        int gridHeight = Math.max(24, h / rows);
        int size = Math.min(gridWidth, gridHeight);

        // create squares for grid
        if (squares == null || squares.length != totalFiles) {
            squares = new Rectangle[totalFiles];  // square per file
        }

        // determine alpha value (max number of lines across all files)
        int alpha = files.stream().mapToInt(AnalyzedFile::size).max().orElse(1);

        // drawing config
        int x = padding;  // current position in grid
        int y = padding;  // current position in grid

        for (int i = 0; i < totalFiles; i++) {

            // get current file
            AnalyzedFile file = files.get(i);

            // calculate square transparency and color
            float transparency = Math.min(1f, (float) file.size() / alpha);  // relative size compared to the largest file
            Color color = colorForComplexity(file.complexity());  // color in respect to file complexity
            Color fill = getFill(color, transparency);  // final square fill config

            // apply square fill
            g2d.setColor(fill);
            g2d.fillRect(x, y, size, size);

            // border config for clicked square
            g2d.setColor(i == clickedSquare ? Color.black : Color.gray);  // black border if current square is selected (clicked)
            g2d.setStroke(new BasicStroke(i == clickedSquare ? 3f : 1f));  // boldness of border based off selection
            g2d.drawRect(x, y, size, size);

            // save square to stored squares
            squares[i] = new Rectangle(x, y, size, size);

            // go to next square (horizontal then wrap to next row)
            x += size;
            if ((i + 1) % cols == 0) {
                x = padding;
                y += size;
            }

        }

        g2d.dispose();

    }

    private Color colorForComplexity(int complexity) {

        if (complexity > 10) {
            return Color.red;
        } else if (complexity > 5) {
            return Color.yellow;
        } else {
            return Color.green;
        }

    }

    private Color getFill(Color color, float transparency) {

        // calculate transparency value
        int transparencyVal = Math.max(0, Math.min(255, (int) (transparency * 255)));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), transparencyVal);

    }

    @Override
    public String getToolTipText(MouseEvent e) {

        // no .java files
        if (files == null || files.isEmpty() || squares == null) { return null; }

        // iterate through squares in grid
        for (int i = 0; i < squares.length; i++) {

            // current square
            Rectangle square = squares[i];

            if (square != null && square.contains(e.getPoint())) {

                AnalyzedFile file = files.get(i);  // current file

                // render file name, size, and complexity in tooltip
                return "<html><b>" + file.name() + "</b><br/>lines: " + file.size() +
                        "<br/>complexity: " + file.complexity() + "</html>";

            }

        }

        return null;

    }

    private void squareHover(Point point) {

        // trigger tooltip
        setToolTipText(getToolTipText(new MouseEvent(this, 0, 0, 0, point.x, point.y, 0, false)));

    }

    private void squareClicked(Point point) {

        // no .java files
        if (files == null || squares == null) { return; }

        // iterate through squares in grid
        for (int i = 0; i < squares.length; i++) {

            if (squares[i] != null && squares[i].contains(point)) {

                clickedSquare = i;  // update square selection
                bottomPanel.setSelectedFileGrid(files.get(i).name());  // update bottom panel
                repaint();
                break;

            }
        }

    }

}
