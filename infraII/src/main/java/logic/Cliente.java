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
    
    
    public boolean askLogIn(ArrayList<String> array) throws IOException, InterruptedException, ClassNotFoundException{       
        socket = new Socket(ip,PUERTO);
        System.out.println("Intentando conectar al servidor");
        InputStream is = socket.getInputStream();

        //InputStreamReader isw = new InputStreamReader(is);
        //InputStreamReader isr = new InputStreamReader(is);
        //BufferedReader br = new BufferedReader(isw);
        
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write("Inicio");
        bw.newLine();
        bw.flush();
        System.out.println("Se inicia la pregunta de sesión");
        Thread.sleep(10000);
        
        ObjectOutputStream objectOutput = new ObjectOutputStream(os);
        objectOutput.writeObject(array);
        System.out.println("Se envia el array");
        Thread.sleep(10000);
        
        
        System.out.println("Se recibe respuesta");
        ObjectInputStream ois = new ObjectInputStream(is);
        boolean respuesta = (boolean) ois.readObject();
        System.out.println("respuesta en cliente: " + respuesta);

        
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

