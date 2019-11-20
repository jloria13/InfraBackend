package logic;

import java.io.File;
import java.util.ArrayList;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Database
 */
public class Database {

    FileIO reader = new FileIO();
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
        ArrayList<String> databases = getDatabases();
        if (databases.contains(name)){
            return true;
        }
        return false;
    }

    public ArrayList<String> getDatabases(){
        ArrayList<String> databases = new ArrayList<>();
        String databasesFile = reader.readFile("Databases", "Databases");
        JsonElement element = parser.parse(databasesFile.substring(1, databasesFile.length()-1));
        JsonObject object = element.getAsJsonObject();
        String objecto = object.toString();
        System.out.println(objecto);
        return databases;
    }

    public ArrayList<String> getTables(String database){
        ArrayList<String> tables = new ArrayList<>();
        String databases = reader.readFile("Databases", "Databases");
        JsonElement element = parser.parse(databases);
        if (element.isJsonObject()){
            JsonObject object = element.getAsJsonObject();
            System.out.println(object.toString());
        }else{
            System.out.println("Not object");
            System.err.println("Not object");
        }

        return tables;
    }
}