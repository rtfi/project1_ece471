/**
 * Created by adley on 2/11/2017.
 */
import java.io.*;
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
}
