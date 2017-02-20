/**
 * Created by adley on 2/11/2017.
 */
import java.util.*;

public class Test {
    public static void main(String [] args) {
        Decipher decipher = new Decipher();
        Map<Character, Integer> numChars;
        decipher.initializeCharacterFrequency();
        ArrayList<Integer> key=new ArrayList<Integer>();
        String text="";
        text=decipher.readFromTextFile("cipher4.txt");
        text=text.toUpperCase();
        System.out.println(text);
        numChars=decipher.countLetters(text);
        System.out.println(numChars);
        double ic= decipher.calculateIC(numChars);
        System.out.println("IC for this text:" + ic);


        decipher.determineKeyLength(text);
        decipher.determineKey(text,9);

        decipher.decryptVigenereCipher(text, "WORCESTER");
        //decipher.countDigrams(text);
        //decipher.decryptShiftCipher(text);
        //decipher.decryptPermutationCipher(text);

    }
}
