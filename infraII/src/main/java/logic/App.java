package logic;

public class App {
    public static void main(String[] args) throws Exception {

        FileIO herramienta = new FileIO();
        Database database = new Database();
        //database.isDatabase("Hola");
        database.getDatabases();
    }
}