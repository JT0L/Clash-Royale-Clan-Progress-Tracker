package pl.clashroyale.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class FileOperations {

    public static String readFile(String filePath){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filePath));

            String fileContent = "";
            String line;
            while ((line = br.readLine()) != null) {
                fileContent = fileContent + line + ";";
            }
            br.close();

            return fileContent;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static String readFullFile(String filePath){
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filePath));

            String fileContent = "";
            String line;
            while ((line = br.readLine()) != null) {
                fileContent = fileContent + line + "\n";
            }
            br.close();

            return fileContent;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public static void writeToFile(String fileContent, String filePath){
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(filePath));
            bw.write(fileContent);
            bw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void appendToFile(String fileContent, String filePath){
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(filePath, true));
            bw.write(fileContent);
            bw.close();
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

