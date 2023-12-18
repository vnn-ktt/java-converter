package converter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkerTest {

   // Тесты для двоичной системы счисления
   @Test
   public void testConvertFromBinaryToOctal() {
       assertEquals("12", Worker.convert("1010", 2, 8));
   }
   @Test
   public void testConvertFromBinaryToDecimal() {
       assertEquals("10", Worker.convert("1010", 2, 10));
   }
   @Test
   void testConvertFromBinaryToHexadecimal() {
       assertEquals("a", Worker.convert("1010", 2, 16));
   }

   // Тесты для восьмиричной системы счисления
   @Test
   public void testConvertFromOctalToBinary() {
       assertEquals("1010", Worker.convert("12", 8, 2));
   }
   @Test
   public void testConvertFromOctalToDecimal() {
       assertEquals("10", Worker.convert("12", 8, 10));
   }
   @Test
   void testConvertFromOctalToHexadecimal() {
       assertEquals("a", Worker.convert("12", 8, 16));
   }

   // Тесты для десятичной системы счисления
   @Test
   public void testConvertFromDecimalToBinary() {
      assertEquals("1010", Worker.convert("10", 10, 2));
   }
   @Test
   public void testConvertFromDecimalToOctal() {
      assertEquals("12", Worker.convert("10", 10, 8));
   }
   @Test
   public void testConvertFromDecimalToHexadecimal() {
      assertEquals("a", Worker.convert("10", 10, 16));
   }

   // Тесты для шестнадцатиричной системы счисления
   @Test
   public void testConvertFromHexadecimalToBinary() {
       assertEquals("1010", Worker.convert("a", 16, 2));
   }
   @Test
   public void testConvertFromHexadecimalToOctal() {
       assertEquals("12", Worker.convert("a", 16, 8));
   }
   @Test
   void testConvertFromHexadecimalToDecimal() {
       assertEquals("10", Worker.convert("a", 16, 10));
   }

   //Тесты исключений
    @Test
    public void testConvertIllegalArgumentExceptionNegative() {
        String input = "-12345";
        int sourceBase = 10;
        int targetBase = 2;
        assertThrows(IllegalArgumentException.class, () -> Worker.convert(input, sourceBase, targetBase));
    }
    @Test
    public void testConvertIllegalArgumentExceptionFloat() {
        String input = "123.45";
        int sourceBase = 10;
        int targetBase = 2;
        assertThrows(IllegalArgumentException.class, () -> Worker.convert(input, sourceBase, targetBase));
    }
    @Test
    public void testConvertIllegalArgumentExceptionEntry() {
        String input = "Число 12";
        int sourceBase = 16;
        int targetBase = 8;
        assertThrows(IllegalArgumentException.class, () -> Worker.convert(input, sourceBase, targetBase));
    }
    @Test
    public void testConvertIllegalArgumentExceptionLength() {
        String input = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
        int sourceBase = 10;
        int targetBase = 2;
        assertThrows(IllegalArgumentException.class, () -> Worker.convert(input, sourceBase, targetBase));
    }
}
