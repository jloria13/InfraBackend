/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Servidor implements Runnable{
	private static int intentos = 0;
	static String ip = "192.168.100.189";//"192.168.100.218";
        static int PUERTO = Integer.parseInt("49153");
       
        //--------------------------------------------Métodos------------------------------------------------------//
        
        // Enviar respuesta tipo Array a la petición correspondiente.
        public void sendResponseArray(boolean resp, Socket socket) throws IOException, InterruptedException{
            //boolean resp = logIn(array);
            //System.out.println("respuesta servidor: "+ resp);
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream objectOutput = new ObjectOutputStream(os);
            objectOutput.writeObject(resp);
            Thread.sleep(10000);
            System.out.println("Se envió booleano: " + resp);
        }
        //Enviar respuesta tipo Array a la petición correspondiente.
        public void sendResponseArray2(ArrayList<String> resp, Socket socket) throws IOException, InterruptedException{
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream objectOutput = new ObjectOutputStream(os);
            objectOutput.writeObject(resp);
            Thread.sleep(10000);
            System.out.println("Se envió la array: " + resp);
        }
        //Enviar respuesta tipo String a la petición correspondiente.
        public void sendResponseString(String resp, Socket socket) throws IOException, InterruptedException{
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream objectOutput = new ObjectOutputStream(os);
            objectOutput.writeObject(resp);
            Thread.sleep(10000);
            System.out.println("Se envió el string: " + resp);
        }
        
        //--------------------------------------------REQUERIMIENTOS-----------------------------------------//
        //REQUERIMIENTO 1.
        public boolean logIn(ArrayList<String> array){
            Database database = new Database();
            boolean respuesta = database.validateUser(array.get(0),array.get(1));           
            return respuesta;           
        }     
        //REQUERIMIENTO 2.
        public String insertUser(ArrayList<String> array){
            Database database = new Database();
            database.insertUser(array.get(0), array.get(1));
            return "True: se ha insertado el usuario.";
        }       
        //REQUERIMIENTO 3.1.
        public ArrayList<String> getUsers(){
            Database database = new Database();
            ArrayList<String> array = new ArrayList<>();
            array = database.getUsers();
            return array;
        }
        //REQUERIMIENTO 3.2
        public String deleteUser(String user){
            Database database = new Database();
            database.deleteUser(user);
            return "True: Se ha eliminado el usuario";
        }        
        //REQUERIMIENTO 4.
        public String createDataBase(ArrayList<String> array){
            Database database = new Database();
            database.insertDatabase(array.get(0), array.get(1));
            return "True: se ha insertado una base de datos dentro del user.";
        }
        //REQUERIMIENTO VER BASE DATOS
        public ArrayList<String> getUsersDatabases(String user){
            Database database = new Database();
            ArrayList<String> array = new ArrayList<>();
            array = database.getUserDatabases(user);
            return array;
        }
        
	public static void main(String[] args) {
         new Thread(new Servidor()).start();
	}
        
       
    @Override
    public void run() {
    ServerSocket socketServer = null;
		Socket socket = null;

		try {
			
			socketServer = new ServerSocket (PUERTO);
                        while(true){
                            System.out.println("Servidor esperando a que el cliente se conecte");

                            socket = socketServer.accept();

                            System.out.println("Cliente se ha conectado.");

                            InputStream is = socket.getInputStream();

                            InputStreamReader isw = new InputStreamReader(is);
                            BufferedReader br = new BufferedReader(isw);
                            String respuesta = br.readLine();
                            
                            if(respuesta.equals("Inicio")){
                                System.out.println("Se entró a Peticion: Inicio");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL ARRAY EN INICIO "+ array.get(0) +
                                        " "+ array.get(1));
                                boolean resp = logIn(array);
                                System.out.println("respuesta servidor: "+ resp);
                                sendResponseArray(resp,socket);
                                                           
                            }
                            else if(respuesta.equals("InsertarUsuario")){
                                System.out.println("Se entró a Peticion: InsertarUsuario");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL ARRAY EN INSERT USER: "+ array.get(0) +
                                        " "+ array.get(1));
                                String resp = insertUser(array);
                                System.out.println("respuesta servidor: "+ resp);
                                sendResponseString(resp,socket);
                                
                            }
                            else if(respuesta.equals("getUsuarios")){
                                System.out.println("Se entró a Peticion: getUsuarios");
                                //ObjectInputStream ois = new ObjectInputStream(is);
                                ArrayList<String> resp = getUsers();
                                System.out.println("respuesta servidor: " + resp);
                                sendResponseArray2(resp,socket);                                 
                            }
                            else if(respuesta.equals("EliminarUsuario")){
                                System.out.println("Se entró a Peticion: EliminarUsuario");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                String user = (String) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL USER: "+ user);
                                String resp = deleteUser(user);
                                System.out.println("respuesta servidor: "  + resp);
                                sendResponseString(resp,socket);                             
                            }
                            else if(respuesta.equals("CrearBaseDatos")){
                                System.out.println("Se entró a Peticion: CrearBaseDatos");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL ARRAY EN INSERT DATABASE: "+ array.get(0) +
                                        " "+ array.get(1));
                                String resp = createDataBase(array);
                                System.out.println("respuesta servidor: "+ resp);
                                sendResponseString(resp,socket);                              
                            }
                            else if(respuesta.equals("VerBaseDatosUser")){
                                System.out.println("Se entró a Peticion: VerDaseDatosUser");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                String user = (String) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL USER: "+ user);
                                ArrayList<String> resp = getUsersDatabases(user);
                                System.out.println("respuesta servidor: "  + resp);
                                sendResponseArray2(resp,socket);
                            }
                        }

		} catch (UnknownHostException e) {
	          System.err.println("I can't find " + e  );
	        }
	        catch (IOException e) {
	          e.printStackTrace();
	        } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
