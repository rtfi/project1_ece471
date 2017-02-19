/**
 * Created by adley on 2/11/2017.
 */

public class Test {
    public static void main(String [] args) {
        Decipher decipher = new Decipher();
        String text="";
        text=decipher.readFromTextFile("cipher1.txt");
        System.out.println(text);
        decipher.countLetters(text);
        decipher.decryptShiftCipher(text);
        //decipher.decryptPermutationCipher(text);
    }
}
