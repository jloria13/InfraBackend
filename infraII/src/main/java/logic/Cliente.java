/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable{
    static String ip = "192.168.100.189";//"192.168.100.218";
    static int PUERTO = Integer.parseInt("49153");
    static private String status;
    static private String res;
    Socket socket;
    
    //---------------------------------------------------Métodos------------------------------------------//
    
    //Creación del Socket.
    public void createSocket() throws IOException{
        socket = new Socket(ip,PUERTO);
        System.out.println("Intentando conectar al servidor");
    }
    //Escribir la petición.
    public void writePetition(String petition, OutputStreamWriter osw) throws IOException, InterruptedException{
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(petition);
        bw.newLine();
        bw.flush();
        System.out.println("Se inicia la pregunta de sesión");
        Thread.sleep(10000);
    }
    //Envio de parámetros tipo array.
    public void sendParameters(OutputStream os,ArrayList<String> array) throws IOException, InterruptedException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(os);
        objectOutput.writeObject(array);
        System.out.println("Se envia el array: " + array);
        Thread.sleep(10000);
    }
    //Envio de parámetros tipo String.
    public void sendParametersString(OutputStream os, String user) throws IOException, InterruptedException{
        ObjectOutputStream objectOutput = new ObjectOutputStream(os);
        objectOutput.writeObject(user);
        System.out.println("Se envia el string" + user);
        Thread.sleep(10000);        
    }
    //Recibir respuesta booleana.
    public boolean getResponseBoolean(InputStream is) throws IOException, ClassNotFoundException{
        System.out.println("Se recibe respuesta");
        ObjectInputStream ois = new ObjectInputStream(is);
        boolean respuesta = (boolean) ois.readObject();
        System.out.println("respuesta en cliente: " + respuesta);
        return respuesta;
    }
    //Recibir respuesta String.
    public String getResponseString(InputStream is) throws IOException, ClassNotFoundException{
        System.out.println("Se recibe respuesta");
        ObjectInputStream ois = new ObjectInputStream(is);
        String respuesta = (String) ois.readObject();
        System.out.println("respuesta en cliente: " + respuesta);
        return respuesta;
    }
    //Recibir respuesta Array.
    public ArrayList<String> getResponseArray(InputStream is) throws IOException, ClassNotFoundException{
        System.out.println("Se recibe respuesta");
        ObjectInputStream ois = new ObjectInputStream(is);
        ArrayList<String> respuesta = (ArrayList<String>) ois.readObject();
        System.out.println("respuesta en cliente: " + respuesta);      
        return respuesta;       
    }
    
    //-------------------------------------------REQUERIMIENTOS------------------------------------------------------//
    
    //REQUERIMIENTO 1.
    public boolean askLogIn(ArrayList<String> array) throws IOException, InterruptedException, ClassNotFoundException{                     
        createSocket();
        InputStream is = socket.getInputStream(); 
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("Inicio", osw);
        
        sendParameters(os, array);
        boolean respuesta = getResponseBoolean(is);
        return respuesta;
    }    
    //REQUERIMIENTO 2.
    public String insertUser(ArrayList<String> array) throws IOException, InterruptedException, ClassNotFoundException{
        
        createSocket();
        InputStream is = socket.getInputStream();
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("InsertarUsuario",osw);
        
        sendParameters(os,array);
       
        String respuesta = getResponseString(is);
        return respuesta;     
    }
    //REQUERIMIENTO 3.1
    public ArrayList<String> getUsers() throws IOException, InterruptedException, ClassNotFoundException{
        createSocket();
        InputStream is = socket.getInputStream();
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("getUsuarios", osw);
        
        ArrayList<String> respuesta = getResponseArray(is);
        return respuesta;                 
    }    
    //REQUERIMIENTO 3.2
    public String deleteUser(String user) throws IOException, InterruptedException, ClassNotFoundException{
        createSocket();
        InputStream is = socket.getInputStream();
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("EliminarUsuario",osw);
        
        sendParametersString(os,user);        
     
        String respuesta = getResponseString(is);      
        return respuesta;     
               
    }
    //REQUERIMIENTO 4.
    public String creatDB(ArrayList<String> array) throws IOException, InterruptedException, ClassNotFoundException{
        createSocket();
        InputStream is = socket.getInputStream();
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("CrearBaseDatos",osw);
        
        sendParameters(os, array);
        
        String respuesta = getResponseString(is);
        return respuesta;         
    }
    
    //REQUERIMIENTO VER BASE DATOS.
    public ArrayList<String> getUserDataBases(String user) throws IOException, InterruptedException, ClassNotFoundException{
        createSocket();
        InputStream is = socket.getInputStream();
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        writePetition("VerBaseDatosUser",osw);
        
        sendParametersString(os,user);
       
        ArrayList<String> respuesta = getResponseArray(is);
        return respuesta;  
    }
   
    
    public static void main(String[] args) throws ClassNotFoundException {
               
	new Thread(new Cliente()).start();
        
	}

    @Override
    public void run() {
        
        try{
			
		socket = new Socket(ip,PUERTO);
                System.out.println("Intentando conectarse al servidor");
                
		InputStream is = socket.getInputStream();

                ObjectInputStream ois = new ObjectInputStream(is);
       
                System.out.println("Se entrego mensaje");

                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                
                System.out.println("Se construyo");
                
                for(int x=0; x<array.size(); x++){
                    System.out.println("This: "+array.get(x));
                }

	        }catch (SocketException e) {
                e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
 
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
 
                    e.printStackTrace();
                }
            }
    
    }
    
	}

