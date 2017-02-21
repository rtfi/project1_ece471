/**
 * Created by adley on 2/11/2017.
 */
import java.util.*;

public class Test {
    public static void main(String [] args) {
        Decipher decipher = new Decipher();     //create Decipher object, which contains all functions to decrypt the ciphertext
        Map<Character, Integer> numChars;       //map which contains letter frequencies
        decipher.initializeCharacterFrequency();    //initialize English character frequency
        String text="";
        text=decipher.readFromTextFile("cipher4.txt");  //Read ciphertext from file
        text=text.toUpperCase();                //convert ciphertext to upper case if not
        System.out.println(text);               //print out ciphertext
        numChars=decipher.countLetters(text);   //print out ciphertext letter frequencies
        System.out.println(numChars);
        double ic= decipher.calculateIC(numChars);  //Calculate and print the IC for the entire ciphertext.
        System.out.println("IC for this text:" + ic);


        decipher.determineKeyLength(text);      //determine possible key length of vignere cipher key. The Key length with the highest IC is a possible key length.
        decipher.determineKey(text,9);  //determine a key for the vignere cipher given the ciphertext and a key length.
        decipher.decryptVigenereCipher(text, "WORCESTER");  //function to decrypt the vigenere cipher given the ciphertext and the key.


        //decipher.decryptShiftCipher(text);    //function to decrypt the shift cipher. Tries all 26 possibilities.
        //decipher.decryptPermutationCipher(text);  //function to decrypt the permutation column transposition cipher.

        //decipher.countDigrams(text);      //function to count the digrams in the ciphertext.

    }
}
