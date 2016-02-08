package com.company;

import java.io.*;
import java.net.*;

/**
 * Created by SweetSoul on 2/8/2016.
 */
public class Test_Client {

    public static void main(String[] args) throws Exception {
        Test_Client Client = new Test_Client();
        Client.run();
    }

    public void run() throws Exception {
        Socket Sock = new Socket("localhost",444);
        PrintStream PS = new PrintStream(Sock.getOutputStream());
        PS.println("Hello to SERVER from CLIENT");

        InputStreamReader IR = new InputStreamReader(Sock.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String Message = BR.readLine();
        System.out.println(Message);
    }

}
