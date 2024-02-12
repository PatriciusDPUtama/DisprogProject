/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import model.Customer;
import model.Restaurant;
import model.Booking;
import java.time.*;

/**
 *
 * @author ASUS1
 */
public class ServerUDP {

    public ServerUDP() throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(5320);

        byte[] receiveData;
        byte[] sendData;

        DatagramPacket incomingPacket, repliedPacket;
        while (true) //server always run
        {
            //set miximum packet 2 KB
            receiveData=new byte[2048];
            sendData= new byte[2048];
            incomingPacket= new DatagramPacket(receiveData,receiveData.length);
            // use receive method to get packet from client
            serverSocket.receive(incomingPacket);
            // change packet from byte to string
            String received= new String(incomingPacket.getData(),incomingPacket.getOffset(), incomingPacket.getLength());
            System.out.println("UDP Packet from admin: "+received);
            // change packet from string to bytes
            String resultText[] = received.split(";-");
            // get IP and port address from client
            String amountData = received;
            System.out.println(resultText[0]);
            if(resultText[0].contains("user"))
            {
                if(resultText[1].contains("view"))
                {
                    System.out.println("USER VIEW IN");
                    Customer customer = new Customer();
                    ArrayList<Object> list = customer.viewListData();
                    System.out.println("data Get");
                    amountData = String.valueOf(list.size());
                    
                    for(Object obj : list)
                    {
                        System.out.println("in for");
                        Customer temp = (Customer)obj;
                        System.out.println("convert");
                        amountData +=" " + temp.getUsername() + "-" + "********" + "-" + temp.getName();
                    }
                }
            }
            else if(resultText[0].contains("restaurant"))
            {
               if(resultText[1].contains("view"))
                {
                    Restaurant restaurant = new Restaurant();
                    ArrayList<Object> list = restaurant.viewAll();
                    amountData = String.valueOf(list.size());
                    for(Object obj : list)
                    {
                        Restaurant temp = (Restaurant)obj;
                        amountData += " "+ temp.getRestaurantName() + "-" + temp.getRestaurantOwner() + "-" + temp.getNumberSeats() + "-" + temp.getUsername() + "-" + "hidden";
                    }
                }
            }
               
            else if(resultText[0].contains("b"))
            {
                System.out.println("in book");
                if(resultText[1].contains("view"))
                {
                    System.out.println("in view");
                    Booking booking = new Booking();
                    ArrayList<Object> list = booking.viewListData();
                    amountData = String.valueOf(list.size());
                    for(Object obj : list)
                    {
                        Booking temp = (Booking)obj;
                        amountData +="&" + temp.getCustomer() + ";" + temp.getRestaurant() + ";" + temp.getNum_people() + ";" + temp.getDate();
                    }
                }
                else if(resultText[1].contains("seats"))
               {
                   Booking booking = new Booking();
                   int seats = booking.getSeats(resultText[2]);
                   amountData = String.valueOf(seats);
                   System.out.println(amountData);
               }
            }
            
            sendData = amountData.getBytes();
            InetAddress ipClient= incomingPacket.getAddress();
            int portClient= incomingPacket.getPort();
            repliedPacket= new DatagramPacket(sendData, sendData.length, ipClient,portClient );
            
            //send the packet
            serverSocket.send(repliedPacket);
        }
    }
}
