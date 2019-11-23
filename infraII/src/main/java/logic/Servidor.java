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

        
        public boolean logIn(ArrayList<String> array){
            Database database = new Database();
            boolean respuesta = database.validateUser(array.get(1),array.get(0));           
            return respuesta;           
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
                                System.out.println("Se entró a inicio");
                                ObjectInputStream ois = new ObjectInputStream(is);
                                ArrayList<String> array = (ArrayList<String>) ois.readObject();
                                System.out.println("ESTO ES EL CONTENIDO DEL ARRAY EN INCIO"+ array.get(1) +
                                        " "+ array.get(1));
                                boolean resp = logIn(array);
                                System.out.println("respuesta servidor: "+ resp);
                                OutputStream os = socket.getOutputStream();
                                ObjectOutputStream objectOutput = new ObjectOutputStream(os);
                                objectOutput.writeObject(resp);
                                Thread.sleep(10000);
                                System.out.println("Se envió el booleano");
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
