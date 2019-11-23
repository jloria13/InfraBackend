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
	static String ip = "192.168.100.218";//"192.168.100.218";
        static int PUERTO = Integer.parseInt("49153");
    
    
	public static void main(String[] args) {
         new Thread(new Servidor()).start();
	}

    @Override
    public void run() {
    ServerSocket socketServer = null;
		Socket socket = null;

		try {
			
			socketServer = new ServerSocket (PUERTO);

			System.out.println("Servidor esperando a que el cliente se conecte");

    			socket = socketServer.accept();

			System.out.println("Cliente se ha conectado.");

			InputStream is = socket.getInputStream();
                        
			InputStreamReader isw = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isw);

			OutputStream os = socket.getOutputStream();
                        ObjectOutputStream objectOutput = new ObjectOutputStream(os);
                        
                        ArrayList<String> array = new ArrayList<String>();
                        array.add("a");
                        array.add("B");
                        
                        System.out.println("A punto de funcionar");
                        objectOutput.writeObject(array);
                        Thread.sleep(10000);



		} catch (UnknownHostException e) {
	          System.err.println("I can't find " + e  );
	        }
	        catch (IOException e) {
	          e.printStackTrace();
	        } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
