package converter;
import java.math.BigInteger;

public class Worker {
    public static String convert(String input, int sourceBase, int targetBase) {
        try {
            BigInteger number = new BigInteger(input, sourceBase);
            if (number.intValue() < 0) {
                throw new IllegalArgumentException("[...1 - входное число отрицательно...]");
            }
            String result = number.toString(targetBase);
            if (result.length() > 256) {
                throw new IllegalArgumentException("[...4 - в результате число с более чем 256 символов...]");
            }
            return result;       
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[...3 - число неправильно записано...]");
        }
    }
}
