package converter;
import java.math.BigInteger;

public class Worker {
    public static String convert(String input, int sourceBase, int targetBase) {
        try {
            BigInteger number = new BigInteger(input, sourceBase);
            if (number.compareTo(BigInteger.ZERO) < 0) {
                throw new IllegalArgumentException("Входное число должно быть неотрицательно...");
            }else if (sourceBase == targetBase) {
               throw new IllegalArgumentException("Указаны одинаковые системы счисления...");
           }
            String result = number.toString(targetBase);
            if (result.length() > 256) {
                throw new IllegalArgumentException("Результат превышает 256 символов...");
            }
            return result;       
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверные входные данные...");
        }
    }
}
