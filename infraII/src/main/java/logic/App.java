package logic;

public class App {
    public static void main(String[] args) throws Exception {

        FileIO herramienta = new FileIO();
        Database database = new Database();
        //String sin = herramienta.readFile("Users", "Databases", false);
        //herramienta.writeFile(sin, "Users", "Databases", true);
        //database.isDatabase("Hola");
        //database.getDatabases();
        //database.getTables("eCommmerce");
        database.insertUser("ale", "natsudark13");
        //boolean as = database.deleteUser("ale");
        database.insertDatabase("ale", "BikeStore");
        System.out.println();
    }
}