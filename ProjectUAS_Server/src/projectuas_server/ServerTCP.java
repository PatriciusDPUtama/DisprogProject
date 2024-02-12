/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas_server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import model.Restaurant;

/**
 *
 * @author ASUS1
 */
public class ServerTCP implements Runnable {

    Socket s;
    ServerSocket ss;
    Thread t;
    ArrayList<HandleSocket> clients = new ArrayList<HandleSocket>();
    private String[] messages = null;
    private Restaurant restaurant;

    @Override
    public void run() {
        while (true) {
            try {
                s = ss.accept();
                HandleSocket newS = new HandleSocket(this, s);
                newS.start();
                
                clients.add(newS);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void ShowChat(String text) {
        try {
            System.out.println(text + "\n");
            /*String msgSplit[] = text.split(":");
            String username = msgSplit[0];
            String content1 = msgSplit[1];

            System.out.println(username);
            System.out.println(content1);*/
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void Broadcast(String text) {
        try {
            for (HandleSocket hs : clients) {
                hs.SendChat(text);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public ServerTCP() {
        try {
            this.ss = new ServerSocket(4321);
            if (t == null) {
                t = new Thread(this, "Server");
                t.start();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /*public void checkLogin(HandleSocket _hsclient, String tmp) {
        messages = tmp.split(";-");
        String checker = null;
        String username = messages[0];
        String password = messages[1];
        restaurant = new Restaurant(username, password);
        int hasil = restaurant.CheckLogin();
        //System.out.println(hasil);
        if (hasil == 1) {
            checker = "CORRECT";
        } else {
            checker = "INCORRECT";
        }
        for (HandleSocket hs : clients) {
            if (hs == _hsclient) {
                hs.SendChat(checker);
            }
        }
    }

    public void checkRegister(HandleSocket _hsclient, String tmp) {
        messages = tmp.split(";-");
        String checker = null;
        String restoName = messages[0];
        String restoOwner = messages[1];
        int numSeats = Integer.parseInt(messages[2]);
        String username = messages[3];
        String password = messages[4];

        restaurant = new Restaurant(restoName, restoOwner, numSeats, username, password);
        int hasil = restaurant.RegisterCheck();
        if (hasil == 1) {
            checker = "USED";
        } else {
            checker = "NOTUSED";
            restaurant.insertData();
        }
        for (HandleSocket hs : clients) {
            if (hs == _hsclient) {
                hs.SendChat(checker);
            }
        }
    }*/
}
