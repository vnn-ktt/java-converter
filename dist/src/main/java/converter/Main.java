package converter;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            JFrame frame = new JFrame("Radix Converter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new ConverterPanel());
            frame.setPreferredSize(new Dimension(900, 180));
            frame.pack();
            frame.setVisible(true);
        } else {
            String inputFilePath = args[0];
            String outputFilePath = args[1];
            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));   
                String line = reader.readLine();      
                if(line == null){
                    writer.write("[...5 - файл \"input\" пустой...]");
                    reader.close();
                    writer.close(); 
                    System.out.println("[...5 - файл \"input\" пустой...]");
                    System.exit(5);
                }    
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    writer.write("[...3 - число неправильно записано...]");
                    reader.close();
                    writer.close(); 
                    System.out.println("[...3 - число неправильно записано...]");
                    System.exit(3);
                }
                try {
                    String number = parts[0];
                    int sourceBase = Integer.parseInt(parts[1]);
                    int targetBase = Integer.parseInt(parts[2]);
                    String result = Worker.convert(number, sourceBase, targetBase);
                    writer.write(result);
                } catch (NumberFormatException ex) {
                    writer.write(ex.getMessage());
                    System.out.println(ex.getMessage());
                }
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ConverterPanel extends JPanel {
    private JTextField inputField;
    private JComboBox<String> sourceBaseComboBox;
    private JComboBox<String> targetBaseComboBox;
    private JButton replaceButton, convertButton, clearButton;
    private JTextArea resultTextArea;

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