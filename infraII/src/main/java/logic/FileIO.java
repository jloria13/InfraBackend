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
    String path;


    void writeFile (String text,String file,String directory, boolean encrypted){
        // write the content in file
        file += ".json";
        setPath(directory,file);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            if (encrypted) text = Encryption.encrypt(text);
            bufferedWriter.write(text);
            bufferedWriter.close();
        } catch (IOException e) {
        // exception handling
        }
    }

    void writeFile (String file){
        // write the content in file
        path += file + ".json";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.close();
        } catch (IOException e) {
        // exception handling
        }
    }
    
    String readFile(String fileName,String directory) {
        fileName += ".json";
        String file = "";
        setPath(directory, fileName);
        // read the content from file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                file += line;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // exception handling
            System.err.println("File not found");
        } catch (IOException e) {
            // exception handling
        }
        file = file.substring(1, file.length()-1);
        return file;
    }

    private void setPath(String directory,String file){
        path = this.directory + separator+directory+separator+file;
    }
    
}