package com.company;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * Created by zhesun3 on 2/8/2016.
 */
public class Chat_Client implements Runnable {

    Socket SOCK;
    Scanner INPUT;
    Scanner SEND = new Scanner(System.in);
    PrintWriter OUT;

    public Chat_Client(Socket X)
    {
        this.SOCK = X;
    }

    public void run()
    {
        try
        {
            try
            {
                INPUT = new Scanner(SOCK.getInputStream());
                OUT = new PrintWriter(SOCK.getOutputStream());
                OUT.flush();
                CheckStream();
            }
            finally
            {
                SOCK.close();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void DISCONNECT() throws IOException
    {
        OUT.println(Chat_Client_GUI.UserName + " has disconnected.");
        OUT.flush();
        SOCK.close();
        JOptionPane.showMessageDialog(null, "You disconnected");
        System.exit(0);
    }

    public void CheckStream()
    {
        while(true)
        {
            RECEIVE();
        }
    }

    public void RECEIVE()
    {
        if(INPUT.hasNext())
        {
            String MESSAGE = INPUT.nextLine();

            if(MESSAGE.contains("#?!"))
            {
                String TEMP1 = MESSAGE.substring(3);
                TEMP1 = TEMP1.replace("[", "");
                TEMP1 = TEMP1.replace("]", "");

                String[] CurrentUsers = TEMP1.split(", ");

                Chat_Client_GUI.JL_ONLINE.setListData(CurrentUsers);
            }
            else
            {
                Chat_Client_GUI.TA_CONVERSATION.append(MESSAGE + "\n");
            }
        }
    }

    public void SEND(String x)
    {
        OUT.println(Chat_Client_GUI.UserName + ": " + x);
        OUT.flush();
        Chat_Client_GUI.TF_Message.setText("");
    }

}
