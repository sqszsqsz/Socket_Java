package com.company;
import java.io.*;
import java.net.*;

/**
 * Created by SweetSoul on 2/8/2016.
 */
public class Test_Server {

    public static void main(String[] args) throws Exception {
        Test_Server server1 = new Test_Server();
        server1.run();
    }

    public void run() throws Exception {
        ServerSocket SRVSOCK = new ServerSocket(444);
        Socket Sock = SRVSOCK.accept();
        InputStreamReader IR = new InputStreamReader(Sock.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String Message = BR.readLine();
        System.out.println(Message);

        if (Message != null) {
            PrintStream PS = new PrintStream(Sock.getOutputStream());
            PS.println("Message Received");
        }

    }
}
