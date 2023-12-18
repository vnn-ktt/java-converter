package converter;
import javax.swing.*;
import java.awt.*;
import java.io.*;

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
                } catch (IllegalArgumentException ex) {
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