import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PasswordGeneratorGUI extends JFrame implements ActionListener {
    private JTextField lengthField;
    private JCheckBox upperCaseBox, lowerCaseBox, numbersBox, symbolsBox, excludeSimilarBox, pronounceableBox;
    private JButton generateButton;
    private JTextArea resultArea;
    private JCheckBox showPasswordBox;
    private ArrayList<String> passwordHistory;

    public PasswordGeneratorGUI() {
        setTitle("Creative Password Generator 🔐");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        passwordHistory = new ArrayList<>();

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Password Settings"));

        lengthField = new JTextField("7");
        upperCaseBox = new JCheckBox("Include Uppercase (A-Z)", true);
        lowerCaseBox = new JCheckBox("Include Lowercase (a-z)", true);
        numbersBox = new JCheckBox("Include Numbers (0-9)", true);
        symbolsBox = new JCheckBox("Include Symbols (!@#$)", true);
        excludeSimilarBox = new JCheckBox("Exclude Similar Characters (l,1,I,O,0)");
        pronounceableBox = new JCheckBox("Pronounceable Mode (Easy to Say)");

        topPanel.add(new JLabel("Password Length:"));
        topPanel.add(lengthField);
        topPanel.add(upperCaseBox);
        topPanel.add(lowerCaseBox);
        topPanel.add(numbersBox);
        topPanel.add(symbolsBox);

        JPanel middlePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        middlePanel.add(excludeSimilarBox);
        middlePanel.add(pronounceableBox);

        showPasswordBox = new JCheckBox("Show Password");
        middlePanel.add(showPasswordBox);

        generateButton = new JButton("✨ Generate Password ✨");
        generateButton.setBackground(Color.BLACK);
        generateButton.setForeground(Color.WHITE);
        generateButton.setFont(new Font("Arial", Font.BOLD, 14));
        generateButton.addActionListener(this);
        middlePanel.add(generateButton);

        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 18));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createTitledBorder("Generated Password"));

        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        getContentPane().setBackground(new Color(240, 248, 255)); // Light background
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int length = Integer.parseInt(lengthField.getText());
        boolean upper = upperCaseBox.isSelected();
        boolean lower = lowerCaseBox.isSelected();
        boolean number = numbersBox.isSelected();
        boolean symbol = symbolsBox.isSelected();
        boolean excludeSimilar = excludeSimilarBox.isSelected();
        boolean pronounceable = pronounceableBox.isSelected();

        String password = pronounceable
            ? PasswordGenerator.generatePronounceable(length)
            : PasswordGenerator.generate(length, upper, lower, number, symbol, excludeSimilar);

        resultArea.setText(password);
        passwordHistory.add(password);
        savePasswordToFile(password);
    }

    private void savePasswordToFile(String password) {
        try {
            FileWriter writer = new FileWriter("password_history.txt", true);
            writer.write(LocalDateTime.now() + " - " + password + "\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving password to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordGeneratorGUI());
    }
}

