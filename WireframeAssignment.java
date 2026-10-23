import javax.swing.*;
import java.awt.*;

/**
 * PROG209 - Basic Interactive GUI (wireframe version)
 * WireframeAssignment extends JFrame (inheritance) and follows the wireframe:
 * app icon and welcome text at the top, a content panel whose background
 * changes, and interactive controls at the bottom.
 */
public class WireframeAssignment extends JFrame {

    private static final Color[] BACKGROUNDS = {
            new Color(235, 245, 255),   // light blue
            new Color(235, 255, 240),   // light green
            new Color(255, 248, 230),   // light yellow
            new Color(250, 235, 255)    // light purple
    };

    private final JPanel contentPanel;
    private final JLabel updatedLabel;
    private final JTextField newTextField;
    private final JButton changeBackgroundButton;
    private final JButton updateTextButton;
    private int colorIndex = 0;

    // Constructor: calls the parent JFrame constructor first
    public WireframeAssignment() {
        super("Java Swing Assignment");

        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);   // closes when the close button is clicked
        setLocationRelativeTo(null);

        // ImageIcon: the application icon, shown in the title bar and the header
        ImageIcon appIcon = loadIcon("icon.png");
        if (appIcon != null) {
            setIconImage(appIcon.getImage());
        }

        // Root panel: holds the header, the content panel and the controls
        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ---------- Header: app icon and welcome text ----------
        JPanel header = new JPanel(new BorderLayout(15, 0));

        JLabel iconLabel = new JLabel("APP ICON", SwingConstants.CENTER);
        iconLabel.setPreferredSize(new Dimension(90, 90));
        iconLabel.setBorder(BorderFactory.createLineBorder(new Color(60, 70, 90), 2));
        if (appIcon != null) {
            iconLabel.setText("");
            iconLabel.setIcon(scaledIcon(appIcon, 80));
        }

        JLabel welcomeLabel = new JLabel(
                "<html><div style='font-size:16px'>"
                        + "<b>Welcome to Java Swing First</b><br>"
                        + "Individual Assignment by Mohamed Salieu Barrie"
                        + "</div></html>");
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        header.add(iconLabel, BorderLayout.WEST);
        header.add(welcomeLabel, BorderLayout.CENTER);

        // ---------- Content panel: JPanel whose background changes ----------
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUNDS[colorIndex]);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(40, 50, 70), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel contentTitle = new JLabel("Content Panel", SwingConstants.CENTER);
        contentTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        contentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // JLabel that shows the text the user typed
        updatedLabel = new JLabel("Your updated text will appear here", SwingConstants.CENTER);
        updatedLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        updatedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(contentTitle);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(updatedLabel);
        contentPanel.add(Box.createVerticalGlue());

        // ---------- Interactive controls ----------
        JPanel controls = new JPanel(new GridLayout(3, 1, 0, 12));

        JLabel controlsTitle = new JLabel("Interactive Controls", SwingConstants.CENTER);
        controlsTitle.setFont(new Font("SansSerif", Font.BOLD, 16));

        // Left: text field with its prompt above it
        JPanel fieldBlock = new JPanel(new BorderLayout(0, 4));
        fieldBlock.add(new JLabel("Enter new label text"), BorderLayout.NORTH);
        newTextField = new JTextField();
        fieldBlock.add(newTextField, BorderLayout.CENTER);

        // Right: background button, with a blank line above so it lines up with the field
        JPanel backgroundBlock = new JPanel(new BorderLayout(0, 4));
        backgroundBlock.add(new JLabel(" "), BorderLayout.NORTH);
        changeBackgroundButton = new JButton("Change panel background");
        backgroundBlock.add(changeBackgroundButton, BorderLayout.CENTER);

        JPanel row1 = new JPanel(new GridLayout(1, 2, 15, 0));
        row1.add(fieldBlock);
        row1.add(backgroundBlock);

        // Centred: the Update Text button
        updateTextButton = new JButton("Update Text");
        updateTextButton.setPreferredSize(new Dimension(220, 36));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        row2.add(updateTextButton);

        controls.add(controlsTitle);
        controls.add(row1);
        controls.add(row2);

        // ---------- Button actions ----------
        // Change panel background: moves to the next colour
        changeBackgroundButton.addActionListener(e -> {
            colorIndex = (colorIndex + 1) % BACKGROUNDS.length;
            contentPanel.setBackground(BACKGROUNDS[colorIndex]);
            contentPanel.repaint();
        });

        // Update Text: copies the typed text into the updated label
        updateTextButton.addActionListener(e -> {
            String text = newTextField.getText().trim();
            if (!text.isEmpty()) {
                updatedLabel.setText(text);
            }
        });

        // ---------- Put it all together ----------
        root.add(header, BorderLayout.NORTH);
        root.add(contentPanel, BorderLayout.CENTER);
        root.add(controls, BorderLayout.SOUTH);
        add(root);
    }

    // Loads the icon file, or returns null and prints a message if it is missing
    private ImageIcon loadIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
            return icon;
        }
        System.out.println("Icon not found: " + path);
        return null;
    }

    // Returns a copy of the icon scaled to a square size
    private ImageIcon scaledIcon(ImageIcon icon, int size) {
        return new ImageIcon(icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WireframeAssignment app = new WireframeAssignment();
            app.setVisible(true);
        });
    }
}