/**
 * Created by adley on 2/11/2017.
 */
import java.io.*;
import java.util.*;

public class Decipher {
    public String readFromTextFile(String fileName){
        //String fileName = "cipher1.txt";
        String line = null;
        String result="";
        try {
            java.io.FileReader fileReader = new java.io.FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                result=line;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file");
        } catch (IOException ex) {
            System.out.println("Error Reading File");
        }
        return result;
    }
    public void countLetters(String letters){
        int len=letters.length();
        Map<Character, Integer> numChars = new HashMap<Character, Integer>(Math.min(len, 26));
        for(int i=0; i<len; i++){
            char charAt = letters.charAt(i);
            if(!numChars.containsKey(charAt)){
                numChars.put(charAt, 1);
            }
            else{
                numChars.put(charAt, numChars.get(charAt)+1);
            }
        }
        System.out.println("Total Length: " + len);
        System.out.println(numChars);
    }

    public void decryptShiftCipher(String cipherText){
        int len=cipherText.length();
        int ShiftLength=3;
        String decrypted="";
        for(int i=0; i<len; i++){
            int c=cipherText.charAt(i);
            if(Character.isUpperCase(c)){
                c=c-(ShiftLength%26);
                if(c<'A'){
                    c=c+26;
                }
            }
            else if(Character.isLowerCase(c)){
                c=c-(ShiftLength%26);
                if(c<'a'){
                    c=c+26;
                }
            }
            decrypted=decrypted + (char) c;
        }
        System.out.println(decrypted);
    }

    public void decryptPermutationCipher(String cipherText){
        //int key=2;
        StringBuilder decipheredString= new StringBuilder();
        for(int key=2; key<20; key++) {
            for (int ii = 0; ii < key; ii++) {
                for (int jj = ii; jj < cipherText.length(); jj += key) {
                    decipheredString.append(cipherText.charAt(jj));
                }
            }
            System.out.println(decipheredString);
            decipheredString.setLength(0);
        }

    }
}
