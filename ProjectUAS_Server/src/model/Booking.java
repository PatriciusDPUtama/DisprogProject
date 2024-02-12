/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Booking extends MyModel {

    private int id;
    private String customer;
    private String restaurant;
    private int num_people;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getNum_people() {
        return num_people;
    }

    public void setNum_people(int num_people) {
        this.num_people = num_people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Booking(int id, String customer, String restaurant, int num_people, String date) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.num_people = num_people;
        this.date = date;
        // _getConnection();
    }

    public Booking(String customer, String restaurant, int num_people, String date) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.num_people = num_people;
        this.date = date;
        // _getConnection();
    }

    public Booking(String customer) {
        this.customer = customer;
        //_getConnection();
    }

    public Booking() {

    }

    @Override
    public void insertData() {
        try {
            if (!conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) conn.prepareStatement("insert into booking(customer, restaurant, num_people, date) values (?,?,?,?)");
                sql.setString(1, getCustomer());
                sql.setString(2, getRestaurant());
                sql.setString(3, Integer.toString(getNum_people()));
                sql.setString(4, getDate());
                sql.executeUpdate();
            } else {
                System.out.println("Koneksi Hilang");
            }
        } catch (Exception ex) {
            System.out.println("Error di insert : " + ex);
        }
    }

    @Override
    public void updateData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "DELETE FROM booking WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Object> viewListData() {
        ArrayList<Object> listOfObject = new ArrayList<Object>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking");
                this.result = sql.executeQuery();
                while (result.next()) {
                    Booking temp = new Booking(result.getString("customer"), result.getString("restaurant"), result.getInt("num_people"), result.getString("date"));
                    listOfObject.add(temp);
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }
    
    public ArrayList<Object> viewListData(String restoName) {
        ArrayList<Object> listOfObject = new ArrayList<Object>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking where restaurant='" + restoName + "'");
                this.result = sql.executeQuery();
                while (result.next()) {
                    Booking temp = new Booking(result.getString("customer"), result.getString("restaurant"), result.getInt("num_people"), result.getString("date"));
                    listOfObject.add(temp);
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }
public ArrayList<Object> viewListCust(String custName) {
        ArrayList<Object> listOfObject = new ArrayList<Object>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking where customer='" + custName + "'");
                this.result = sql.executeQuery();
                while (result.next()) {
                    Booking temp = new Booking(result.getString("customer"), result.getString("restaurant"), result.getInt("num_people"), result.getString("date"));
                    listOfObject.add(temp);
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }
    public ArrayList<String> viewNames(String custName) {
        ArrayList<String> listOfObject = new ArrayList<String>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking where customer='" + custName + "'");
                this.result = sql.executeQuery();
                while (result.next()) {
                    listOfObject.add(result.getString("name"));
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }

    public ArrayList<String> viewNames() {
        ArrayList<String> listOfObject = new ArrayList<String>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking");
                this.result = sql.executeQuery();
                while (result.next()) {
                    listOfObject.add(result.getString("name"));
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }

    public int getSeats(String dateValue) {
        int totalSeats = 0;
        try {
            System.out.println("OPEN");
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking where date = '" + dateValue + "'");
                this.result = sql.executeQuery();
                System.out.println(totalSeats);
                while (result.next()) { 
                    totalSeats += result.getInt("num_people");
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(totalSeats);
        return totalSeats;
    }
    
    public int getTakenSeats(String dateValue, String restaurantName) {
        int totalSeats = 0;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from booking where date = '" + dateValue + "' AND restaurant='" + restaurantName+"'");
                this.result = sql.executeQuery();

                while (result.next()) {
                    totalSeats += result.getInt("num_people");
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return totalSeats;
    }
}
