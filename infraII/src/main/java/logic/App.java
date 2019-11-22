package logic;

public class App {
    public static void main(String[] args) throws Exception {

        FileIO herramienta = new FileIO();
        Database database = new Database();
        //database.isDatabase("Hola");
        //database.getDatabases();
        //database.getTables("eCommmerce");
        boolean es = database.validateUser("mj", "mauEsElMejor");
        System.out.println(es);
    }
}