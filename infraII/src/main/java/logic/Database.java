package logic;

import java.io.File;
import java.util.ArrayList;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
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
        JsonElement elementFile = parser.parse(databasesFile);
        JsonObject objectFile = elementFile.getAsJsonObject();
        JsonArray databasesArray = objectFile.getAsJsonArray("databases");
        for (int i=0; i < databasesArray.size();i++){
            JsonElement elementDatabase = databasesArray.get(i).getAsJsonObject().get("database");
            JsonElement detailsDatabase = elementDatabase.getAsJsonObject().get("name");
            String nameDatabase = detailsDatabase.getAsString();
            databases.add(nameDatabase);
        }
        return databases;
    }

    public ArrayList<String> getTables(String database){
        ArrayList<String> tables = new ArrayList<>();
        String databases = reader.readFile("Databases", "Databases");
        JsonElement elementFile = parser.parse(databases);
        JsonObject objectFile = elementFile.getAsJsonObject();
        JsonArray databasesArray = objectFile.getAsJsonArray("databases");
        String table;
        for (int i=0; i < databasesArray.size();i++){
            JsonElement elementDatabase = databasesArray.get(i).getAsJsonObject().get("database");
            JsonElement detailsDatabase = elementDatabase.getAsJsonObject().get("tables");
            String databaseName = elementDatabase.getAsJsonObject().get("name").getAsString();
            JsonArray tablesDatabase = detailsDatabase.getAsJsonArray();
            if (database.equals(databaseName)){
                for (int j=0; j<tablesDatabase.size();j++){
                    table = tablesDatabase.get(j).getAsString();
                    tables.add(table);
                }
            }
        }
        return tables;
    }
}