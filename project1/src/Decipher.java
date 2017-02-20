/**
 * Created by adley on 2/11/2017.
 */
import java.io.*;
import java.util.*;

public class Decipher {
    public Map<Character, Double> CharacterFrequency=new HashMap<Character, Double>();
    public void initializeCharacterFrequency(){
        CharacterFrequency.put('A', .082);
        CharacterFrequency.put('B', .015);
        CharacterFrequency.put('C', .028);
        CharacterFrequency.put('D', .043);
        CharacterFrequency.put('E', .127);
        CharacterFrequency.put('F', .022);
        CharacterFrequency.put('G', .020);
        CharacterFrequency.put('H', .061);
        CharacterFrequency.put('I', .070);
        CharacterFrequency.put('J', .002);
        CharacterFrequency.put('K', .008);
        CharacterFrequency.put('L', .040);
        CharacterFrequency.put('M', .024);
        CharacterFrequency.put('N', .067);
        CharacterFrequency.put('O', .075);
        CharacterFrequency.put('P', .019);
        CharacterFrequency.put('Q', .001);
        CharacterFrequency.put('R', .060);
        CharacterFrequency.put('S', .063);
        CharacterFrequency.put('T', .091);
        CharacterFrequency.put('U', .028);
        CharacterFrequency.put('V', .010);
        CharacterFrequency.put('W', .023);
        CharacterFrequency.put('X', .001);
        CharacterFrequency.put('Y', .020);
        CharacterFrequency.put('Z', .001);
    }
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
    public Map<Character, Integer> countLetters(String letters){
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
        //System.out.println("Total Length: " + len);
        //System.out.println(numChars);
        return numChars;
    }
    public Map<String, Integer> countDigrams(String cipherText){
        Map<String, Integer> digrams=new HashMap<String, Integer>();
        
        for(int ii=0; ii<cipherText.length();ii++){
            String digram=cipherText.substring(ii,Math.min(ii+2, cipherText.length()));
            if(!digrams.containsKey(digram)){
                digrams.put(digram, 1);
            }
            else {
                digrams.put(digram, digrams.get(digram) + 1);
            }
        }
        System.out.println(digrams);
        return digrams;
    }

    public void decryptShiftCipher(String cipherText){
        int len=cipherText.length();
        int ShiftLength=3;
        StringBuilder decrypted= new StringBuilder();
        for (ShiftLength=0; ShiftLength<26; ShiftLength++) {
            for (int i = 0; i < len; i++) {
                int c = cipherText.charAt(i);
                if (Character.isUpperCase(c)) {
                    c = c - (ShiftLength % 26);
                    if (c < 'A') {
                        c = c + 26;
                    }
                } else if (Character.isLowerCase(c)) {
                    c = c - (ShiftLength % 26);
                    if (c < 'a') {
                        c = c + 26;
                    }
                }
                char ch=(char) c;
                decrypted = decrypted.append(ch);
            }
            System.out.println(ShiftLength + ": " + decrypted);
            decrypted.setLength(0);
        }
    }

    public String decryptShiftCipher(String cipherText, int ShiftLength){
        int len=cipherText.length();
        StringBuilder decrypted= new StringBuilder();

        for (int i = 0; i < len; i++) {
            int c = cipherText.charAt(i);
            if (Character.isUpperCase(c)) {
                c = c - (ShiftLength % 26);
                if (c < 'A') {
                    c = c + 26;
                }
            } else if (Character.isLowerCase(c)) {
                c = c - (ShiftLength % 26);
                if (c < 'a') {
                    c = c + 26;
                }
            }
            char ch=(char) c;
            decrypted = decrypted.append(ch);
        }
            //System.out.println(ShiftLength + ": " + decrypted);
            //decrypted.setLength(0);
        return decrypted.toString();
    }

    public void decryptPermutationCipher(String cipherText){
        //int key=2;
        StringBuilder decipheredString= new StringBuilder();
        for(int key=2; key<100; key++) {
            for (int ii = 0; ii < key; ii++) {
                for (int jj = ii; jj < cipherText.length(); jj += key) {
                    decipheredString.append(cipherText.charAt(jj));
                }
            }
            System.out.println("Columns " + key + ": " + decipheredString);
            decipheredString.setLength(0);
        }
    }
    public double calculateIC(Map<Character, Integer> numChars){
        double ic=0;
        int TotalCharacters=0;

        for(int letterFrequency:numChars.values()){
            TotalCharacters+=letterFrequency;
        }
        for(int letterFrequency:numChars.values()){
            double top=letterFrequency * (letterFrequency-1);
            double bottom=TotalCharacters*(TotalCharacters-1);
            ic+=top/bottom;
        }
        return ic;
    }
    public double calculateIC(String cipherText){
        double ic=0;
        int TotalCharacters=0;
        Map<Character, Integer> numChars=countLetters(cipherText);
        for(int letterFrequency:numChars.values()){
            TotalCharacters+=letterFrequency;
        }
        for(int letterFrequency:numChars.values()){
            double top=letterFrequency * (letterFrequency-1);
            double bottom=TotalCharacters*(TotalCharacters-1);
            ic+=top/bottom;
        }
        return ic;
    }
    public Map<Integer, Double> calculateICPeriods(String cipherText){
        Map<Integer, Double> ICPeriods= new HashMap<Integer, Double>();
        StringBuilder stringSubset=new StringBuilder();
        double avgIC=0;
        for(int period=2; period<16; period++) {                            //Calculate Shifted IC for different key lengths (Periods)
            for(int ii=0; ii<period; ii++) {
                for (int jj = ii; jj < cipherText.length(); jj += period) {
                    stringSubset.append(cipherText.charAt(jj));
                }
                avgIC+=calculateIC(stringSubset.toString());                       //Calculate IC for sub string
                stringSubset.setLength(0);
            }
            avgIC=avgIC/period;             //Calculate average IC for the period
            ICPeriods.put(period, avgIC);   //Put average IC into map with corresponding period (key length)
        }
        return ICPeriods;
    }

    public double[] estimateKeyLength(Map<Integer, Double> periods){
        double[] result=new double[]{0,0,0};
        for(Integer key:periods.keySet()){
            if(periods.get(key)>result[1]){
                result[1]=key;
            }
            else if(periods.get(key)>result[2]){
                result[2]=key;
            }
            else if(periods.get(key)>result[3]){
                result[3]=key;
            }
        }
        return result;
    }

    public double calculateChiSquared(Map<Character, Integer> numChars){
        double ChiSquared=0;
        double expectedCount=0;
        int TotalCharacters=0;
        Map<Character, Double> ChiSquaredMap=new HashMap<Character, Double>();
        for(int letterFrequency:numChars.values()){
            TotalCharacters+=letterFrequency;
        }
        for(char key:numChars.keySet()){
            expectedCount=TotalCharacters*CharacterFrequency.get(key);
            ChiSquared+=(numChars.get(key)-expectedCount)*(numChars.get(key)-expectedCount)/expectedCount;
        }
        return ChiSquared;
    }
    public double calculateChiSquared(String cipherText){
        double ChiSquared=0;
        double expectedCount=0;
        int TotalCharacters=0;
        Map<Character, Double> ChiSquaredMap=new HashMap<Character, Double>();
        Map<Character, Integer> numChars=countLetters(cipherText);
        for(int letterFrequency:numChars.values()){
            TotalCharacters+=letterFrequency;
        }
        for(char key:numChars.keySet()){
            expectedCount=TotalCharacters*CharacterFrequency.get(key);
            ChiSquared+=(numChars.get(key)-expectedCount)*(numChars.get(key)-expectedCount)/expectedCount;
        }
        return ChiSquared;
    }

    public ArrayList<Integer> determineKey(String cipherText, int keyLength){
        String decrypted="";
        double ic=10000;
        double tempIC=0;
        int key=0;
        StringBuilder subString=new StringBuilder();
        ArrayList<Integer> result= new ArrayList<Integer>();

        for(int k=0; k<keyLength; k++) {
            for(int ii=k; ii<cipherText.length(); ii+=keyLength){
                subString.append(cipherText.charAt(ii));
            }
            for (int ii = 0; ii < 26; ii++) {
                decrypted = decryptShiftCipher(subString.toString(), ii);
                tempIC = calculateChiSquared(decrypted);
                if (tempIC < ic) {
                    ic = tempIC;
                    key = ii;
                }
            }
            ic=10000;
            subString.setLength(0);
            result.add(key);
        }
        return result;

    }

}
