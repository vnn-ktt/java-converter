package converter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainTest {

  //Проверка работы интеграционного режима (и взаимодействия Main с Worker)
  @Test
  public void testFileInputProcessing() {
        String inputFilePath = "testInput.txt";
        String outputFilePath = "testOutput.txt";
        String input = "5;10;2";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFilePath));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String[] args = {inputFilePath, outputFilePath};
        Main.main(args); 
       
        try {
            BufferedReader reader = new BufferedReader(new FileReader(outputFilePath));
            String result = reader.readLine();
            reader.close();
            assertEquals("101", result); 
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File(inputFilePath).delete();
        new File(outputFilePath).delete();
    }
}
