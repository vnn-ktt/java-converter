package converter;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterPanel extends JPanel {
    JTextField inputField;
    JComboBox<String> sourceBaseComboBox;
    JComboBox<String> targetBaseComboBox;
    JButton replaceButton, convertButton, clearButton;
    JTextArea resultTextArea;

    public ConverterPanel() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
    
        inputField = new JTextField(30);
        sourceBaseComboBox = new JComboBox<>(new String[]{"2", "8", "10", "16"});
        targetBaseComboBox = new JComboBox<>(new String[]{"2", "8", "10", "16"});
        replaceButton = new JButton("\u2190 Поменять местами \u2192");
        convertButton = new JButton("Конвертировать");
        clearButton = new JButton("Очистить");

        inputField.setToolTipText("Введите число...");
        sourceBaseComboBox.setSelectedItem("2");
        targetBaseComboBox.setSelectedItem("10");
        convertButton.setEnabled(false);
        clearButton.setEnabled(false);

        inputPanel.add(inputField);
        inputPanel.add(sourceBaseComboBox);
        inputPanel.add(replaceButton);
        inputPanel.add(targetBaseComboBox);
        inputPanel.add(convertButton);
        inputPanel.add(clearButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel resultPanel = new JPanel(new BorderLayout()); 

        resultTextArea = new JTextArea(); 

        resultTextArea.setEditable(false);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setLineWrap(true);
        resultTextArea.setBorder(new EmptyBorder(15, 15, 15, 15));
        resultTextArea.setFont(new Font("Tahoma", Font.BOLD,16));
        resultPanel.add(resultTextArea, BorderLayout.CENTER);

        add(resultPanel, BorderLayout.CENTER); 

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (inputField.getText().isEmpty()) {
                    convertButton.setEnabled(false);
                    clearButton.setEnabled(false);
                } else {
                    convertButton.setEnabled(true);
                    clearButton.setEnabled(true);
                }
            }
        });
        replaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object temp = sourceBaseComboBox.getSelectedItem();
                sourceBaseComboBox.setSelectedItem(targetBaseComboBox.getSelectedItem());
                targetBaseComboBox.setSelectedItem(temp);
            }
        });
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                int sourceBase = Integer.parseInt(sourceBaseComboBox.getSelectedItem().toString());
                int targetBase = Integer.parseInt(targetBaseComboBox.getSelectedItem().toString());
                try {
                    String result = Worker.convert(input, sourceBase, targetBase);
                    resultTextArea.setText("Результат: " + result);
                } catch (IllegalArgumentException ex) {
                    resultTextArea.setText("Ошибка: " + ex.getMessage());
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
                sourceBaseComboBox.setSelectedItem("2");
                targetBaseComboBox.setSelectedItem("10");
                resultTextArea.setText("");
                convertButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });
    }
}