package logic;

import java.io.File;
import java.util.ArrayList;
import com.google.gson.JsonParser;

/**
 * Database
 */
public class Database {

    String separator = System.getProperty("file.separator");
    String path = System.getProperty("user.dir")+separator+"Databases"+separator;
    JsonParser parser = new JsonParser();

    public Database (){

    }

    public boolean create(String name,ArrayList<String> columnas){
        if (isDatabase(name)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isDatabase(String name){
        ArrayList<String> files = getDatabases();
        if (files.contains(name)){
            return true;
        }
        return false;
    }

    public ArrayList<String> getDatabases (){
        ArrayList<String> databases = new ArrayList<>();
        File[] files = new File (path).listFiles();
        for (File file:files){
            if (file.isFile()){
                databases.add(file.getName());
                System.out.println(file.getName());
            }
        }
        return databases;  
    }
}