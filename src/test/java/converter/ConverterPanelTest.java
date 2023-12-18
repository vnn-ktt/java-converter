package converter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ConverterPanelTest {
   
   //Проверка работоспособности интерфейса (кнопки "Очистить")
   @Test
   public void testClearButtonFunctionality() {
      ConverterPanel converterPanel = new ConverterPanel();
      converterPanel.inputField.setText("100");
      converterPanel.sourceBaseComboBox.setSelectedItem("2");
      converterPanel.targetBaseComboBox.setSelectedItem("10");
      converterPanel.convertButton.doClick();
      converterPanel.clearButton.doClick();
      assertEquals("2", converterPanel.sourceBaseComboBox.getSelectedItem());
      assertEquals("10", converterPanel.targetBaseComboBox.getSelectedItem());
      assertEquals("", converterPanel.resultTextArea.getText());
      assertFalse(converterPanel.convertButton.isEnabled());
      assertFalse(converterPanel.clearButton.isEnabled());
   }
}
