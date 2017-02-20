/**
 * Created by adley on 2/11/2017.
 */
import java.util.*;

public class Test {
    public static void main(String [] args) {
        Decipher decipher = new Decipher();
        Map<Character, Integer> numChars;
        decipher.initializeCharacterFrequency();
        String text="";
        text=decipher.readFromTextFile("test.txt");
        text=text.toUpperCase();
        System.out.println(text);
        numChars=decipher.countLetters(text);
        System.out.println(numChars);
        decipher.decryptShiftCipher(text);
        //decipher.decryptPermutationCipher(text);
        double ChiSquared=decipher.calculateChiSquared(numChars);
        System.out.println(ChiSquared);
        Map<Integer, Double> periods=decipher.calculateICPeriods(text);
        System.out.println(periods);
    }
}
