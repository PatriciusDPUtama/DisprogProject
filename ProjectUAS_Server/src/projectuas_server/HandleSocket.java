/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectuas_server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Customer;
import model.Restaurant;
import model.Booking;

/**
 *
 * @author ASUS1
 */
public class HandleSocket extends Thread {

    ServerTCP parent;
    Socket s;
    BufferedReader in;
    DataOutputStream out;
    Customer cust;
    Restaurant resto;
    Booking book;

    public HandleSocket(ServerTCP _parent, Socket _s) {
        this.parent = _parent;
        this.s = _s;
        try {
            out = new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void SendChat(String text) {
        try {
            out.writeBytes(text + "\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void run() {
        while (true) {
            try {
                String s = in.readLine();
                System.out.println(s);
                String[] array = null;
                array = s.split(" ");
                String awal = array[0];
                if (awal.equals("Welcome")) {
                    String nama = array[1];
                    String pass = array[2];
                    cust = new Customer();
                    if (cust.checkLogin(nama, pass) == false) {
                        sendChat("0");
                    } else {
                        sendChat("1");
                    }
                } else if (awal.equals("Login")) {
                    String nama = array[1];
                    String pass = array[2];
                    resto = new Restaurant(nama, pass);
                    if (resto.CheckLogin() == 1) {
                        sendChat("1");
                    } else {
                        sendChat("0");
                    }
                } else if (awal.equals("CheckUser")) {
                    String user = array[1];
                    String name = array[2];
                    String pass = array[3];
                    System.out.println(user + " " + pass + " " + name + " line 84");
                    cust = new Customer();
                    if (cust.checkUsername(user) == false) {
                        System.out.println("Checkuser gaada line 87");
                        cust.insertData(user, name, pass);
                        //cust.insertData();
                        sendChat("0");
                    } else {
                        System.out.println("Checkuser ada line 92");
                        sendChat("1");
                    }
                } else if (awal.equals("CheckResto")) {
                    String restoName = array[1];
                    String restoOwner = array[2];
                    int numSeats = Integer.parseInt(array[3]);
                    String username = array[4];
                    String password = array[5];
                    resto = new Restaurant(restoName, restoOwner, numSeats, username, password);
                    if (resto.RegisterCheck() == 0) {
                        resto.insertData();
                        sendChat("NOTUSED");
                    } else {
                        sendChat("USED");
                    }
                } else {
                    parent.ShowChat(s);
                }

                //System.out.println(s);
                if (awal.equals("Ezbooking")) {
                    String nama = array[1];
                    Customer cust = new Customer();
                    String namaAsli = cust.sendName(nama);
                    System.out.println(namaAsli);
                    sendChat(namaAsli);
                }
                if (awal.equals("List")) {
                    // resto
                    Restaurant resto = new Restaurant();
                    ArrayList<String> list = resto.viewNames();
                    String amountData = "";
                    for (String name : list) {
                        amountData += " " + name;

                        System.out.println(name);
                    }
                    sendChat(amountData);

                }
                if (awal.equals("BookNow")) {
                    String custName = array[1];
                    String restName = array[2];
                    int numOfPeople = Integer.parseInt(array[3]);
                    String reservation = array[4] + " " + array[5];
                    System.out.println(custName + restName + numOfPeople + reservation);
                    //String jam= array[5];
                    //System.out.println(jam);
                    
                    Booking temp = new Booking(custName, restName, numOfPeople, reservation);
                    //get available seats (max cap - taken seats)
                    System.out.println("SUCCESS");
                    Restaurant temp2 = new Restaurant();
                    int getBookedSeats = temp.getTakenSeats(reservation, restName);
                    int availableSeats = temp2.sendSeats(restName);//blm tau max cap brp, nanti max cap - taken seats
                    System.out.println("available seats" + availableSeats);
                    int checkSeats = getAvailableSeats(getBookedSeats,availableSeats);
                    System.out.println("Check seats " + checkSeats);
                    if (checkSeats> numOfPeople) {
                        temp.insertData();
                        parent.Broadcast("available" + ";-" + temp.getCustomer() + ";-" + temp.getDate() + ";-" + temp.getNum_people());
                    } else {
                        temp.deleteData();
                        sendChat("penuh");
                    }
                }

                if (awal.equals("RestoName")) {
                    String nama = array[1];
                    Restaurant resto = new Restaurant();
                    String namaAsli = resto.sendName(nama);
                    System.out.println(namaAsli);
                    sendChat(namaAsli);
                }
                if (awal.equals("RestoBooking")) {
                    String restName = array[1];
                    Booking booking = new Booking();
                    ArrayList<Object> list = booking.viewListData(restName);
                    String amountData = String.valueOf(list.size());
                    for (Object obj : list) {
                        Booking temp = (Booking) obj;
                        amountData += "&" + temp.getCustomer() + ";" + temp.getRestaurant() + ";" + temp.getNum_people() + ";" + temp.getDate();
                    }
                    System.out.println(amountData);
                    sendChat(amountData);
                }
                if (awal.equals("CekEdit")) {
                    String username = array[1];
                    Customer cust = new Customer();
                    String allInfo = cust.sendAll(username);
                    System.out.println("handle " + allInfo);
                    sendChat(allInfo);
                }
                if (awal.equals("Edit")) {
                    System.out.println("masuk edit");
                    String id = array[1];
                    int idEdit = Integer.parseInt(id);
                    String usernameBaru = array[2];
                    String namaBaru = array[3];
                    String passwordBaru = array[4];
                    cust = new Customer();
                    System.out.println("newcust");
                    cust.updateData(idEdit, usernameBaru, namaBaru, passwordBaru);
                    sendChat("0");

                }
                if (awal.equals("DeleteUser")) {
                    System.out.println("masuk delete ");
                    String id = array[1];
                    int idDelete = Integer.parseInt(id);
                    cust = new Customer();
                    cust.deleteData(idDelete);
                    sendChat("DoneDelete");

                }
                if(awal.equals("GetTable")){
                    String custName = array[1];
                    Booking booking = new Booking();
                    ArrayList<Object> list = booking.viewListCust(custName);
                    String amountData = String.valueOf(list.size());
                    for (Object obj : list) {
                        Booking temp = (Booking) obj;
                        amountData += "&" + temp.getCustomer() + ";" + temp.getRestaurant() + ";" + temp.getNum_people() + ";" + temp.getDate();
                    }
                    System.out.println(amountData);
                    sendChat(amountData);
                }
                if (awal.equals("RestoInfo")) {
                    System.out.println(array[1]);
                    String nama = array[1];
                    Restaurant res = new Restaurant();
                    String resto = res.sendInfo(nama);
                    System.out.println(resto);
                    sendChat(String.valueOf(resto));
                }
                if (awal.equals("EditResto")) {
                    System.out.println("masuk edit resto");
                    String id = array[1];
                    int idEdit = Integer.parseInt(id);
                    String restNameBaru = array[2];
                    String restOwnerBaru = array[3];
                    int numSeatBaru = (int)Math.round(Double.parseDouble(array[4]));
                    String usernameBaru = array[5];
                    String passwordBaru = array[6];
                    resto = new Restaurant();
                    System.out.println("newresto");
                    resto.updateData(idEdit, restNameBaru, restOwnerBaru, numSeatBaru, usernameBaru, passwordBaru);
                    sendChat("0");

                }
                if (awal.equals("DeleteResto")) {
                    System.out.println("masuk delete ");
                    String id = array[1];
                    int idDelete = Integer.parseInt(id);
                    resto = new Restaurant();
                    resto.deleteData(idDelete);
                    sendChat("DoneDelete");

                }
                

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void sendChat(String tmp) {
        try {
            out.writeBytes(tmp + "\n");
        } catch (IOException ex) {
            Logger.getLogger(HandleSocket.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int getAvailableSeats(int seats, int restaurantSeats) {
        com.project.UASWebServices_Service service = new com.project.UASWebServices_Service();
        com.project.UASWebServices port = service.getUASWebServicesPort();
        return port.getAvailableSeats(seats, restaurantSeats);
    }

}
