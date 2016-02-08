package com.company;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.omg.CORBA.Current;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.swing.JOptionPane;


/**
 * Created by zhesun3 on 2/8/2016.
 */
public class Chat_Server {
    //Globals
    public static ArrayList<Socket> ConnectionArray = new ArrayList<Socket>();
    public static ArrayList<String> CurrentUsers = new ArrayList<String>();

    public static void main(String[] args) {

        try {
            final int PORT = 444;
            ServerSocket SERVER = new ServerSocket(PORT);
            System.out.println("Waiting for clients...");


            while(true) {
                Socket SOCK = SERVER.accept();
                ConnectionArray.add(SOCK);

                System.out.println("Client connected from: "+SOCK.getLocalAddress().getHostName());
                AddUserName(SOCK);

                Chat_Server_Return CHAT = new Chat_Server_Return(SOCK);
                Thread X = new Thread(CHAT);
                X.start();

            }
        } catch (Exception X) {
            System.out.println(X);
        }
    }

    public static void AddUserName(Socket X) throws IOException {
        Scanner INPUT = new Scanner(X.getInputStream());
        String UserName = INPUT.nextLine();
        CurrentUsers.add(UserName);

        for(int i=1;i<=Chat_Server.ConnectionArray.size();i++){
            Socket TEMP_SOCK = (Socket)Chat_Server.ConnectionArray.get(i-1);
            PrintWriter OUT = new PrintWriter(TEMP_SOCK.getOutputStream());
            OUT.println("#?!"+CurrentUsers);
            OUT.flush();
        }

    }
}
