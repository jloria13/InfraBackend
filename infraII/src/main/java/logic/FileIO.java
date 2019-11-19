package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

/**
 * FileIO
 */
public class FileIO {

    String directory = System.getProperty("user.dir");
    String separator = System.getProperty("file.separator");
    String path = directory + separator+"Databases"+separator;

    void writeFile (String text,String name){
        // write the content in file
        path += name + ".db";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
        // exception handling
        }
    }

    void writeFile (String name){
        // write the content in file
        path += name + ".db";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.close();
        } catch (IOException e) {
        // exception handling
        }
    }
    
    String readFile(String name) {
        name += ".db";
        String file = "";
        // read the content from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                file = line+"\n";
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
        } catch (IOException e) {
            // exception handling
        }
        return file;
    }
    
}