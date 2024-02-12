/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.MyModel.conn;

/**
 *
 * @author HP
 */
public class Restaurant extends MyModel {

    int id;
    String restaurantName;
    String restaurantOwner;
    int numberSeats;
    String username;
    String password;

    public Restaurant() {
        this.restaurantName = null;
        this.restaurantOwner = null;
        this.numberSeats = 0;
        this.username = null;
        this.password = null;
    }

    public Restaurant(int _id, String _restoName, String _restoOwner, int _numSeats, String _username, String _pass) {
        this.id = _id;
        this.restaurantName = _restoName;
        this.restaurantOwner = _restoOwner;
        this.numberSeats = _numSeats;
        this.username = _username;
        this.password = _pass;
    }

    public Restaurant(String _restoName, String _restoOwner, int _numSeats, String _username, String _pass) {
        this.restaurantName = _restoName;
        this.restaurantOwner = _restoOwner;
        this.numberSeats = _numSeats;
        this.username = _username;
        this.password = _pass;
    }

    public Restaurant(String _username, String _pass) {
        this.username = _username;
        this.password = _pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int CheckLogin() {
        int hasil = 0;
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT EXISTS (SELECT * FROM restaurant WHERE username = (?) AND password = (?)) AS hasil");
                sql.setString(1, username);
                sql.setString(2, password);
                ResultSet rs = sql.executeQuery();
                while (rs.next()) {
                    hasil = rs.getInt("hasil");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return hasil;
    }

    public int RegisterCheck() {
        int result = 0;
        try {
            if (!MyModel.conn.isClosed()) {
                //hampir sama seperti CheckLogin, tetapi query ini hanya mengecek 
                //apakah username sudah pernah dipakai atau belum
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "SELECT EXISTS (SELECT * FROM restaurant WHERE username = (?)) AS hasil");
                sql.setString(1, username);
                ResultSet rs = sql.executeQuery();
                while (rs.next()) {
                    result = rs.getInt("hasil");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void insertData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "INSERT INTO restaurant (restaurant_name, restaurant_owner, number_seats, username, password) VALUES (?,?,?,?,?)");
                sql.setString(1, this.restaurantName);
                sql.setString(2, this.restaurantOwner);
                sql.setInt(3, this.numberSeats);
                sql.setString(4, this.username);
                sql.setString(5, this.password);
                sql.executeUpdate();
                sql.close();
            } else {
                System.out.println("Koneksi Hilang");
            }
        } catch (Exception ex) {
            System.out.println("Error di insert : " + ex);
        }
    }
    
    @Override
    public void updateData(){
        
    }
    
    public void updateData(int idResto, String restName, String restOwner, int number_seats, String username, String password ) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "UPDATE restaurant SET restaurant_name=?, restaurant_owner=?, number_seats=?, username=?, password=? WHERE id=?");
                sql.setString(1, restName);
                sql.setString(2, restOwner);
                sql.setInt(3, number_seats);
                sql.setString(4, username);
                sql.setString(5, password);
                sql.setInt(6, idResto);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Update");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void deleteData() {

    }
    public void deleteData(int id) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "DELETE FROM restaurant WHERE id = ?");
                sql.setInt(1, id);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Delete");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery(""
                    + "SELECT * FROM restaurant "
                    + "WHERE username = '" + this.username + "' AND password = '" + this.password + "'");

            while (this.result.next()) {
                Restaurant r = new Restaurant(
                        this.result.getString("username"),
                        this.result.getString("password")
                );
                collections.add(r);
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return collections;
    }

    public ArrayList<String> viewNames() {
        ArrayList<String> listOfObject = new ArrayList<String>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from restaurant");
                this.result = sql.executeQuery();
                while (result.next()) {
                    listOfObject.add(result.getString("restaurant_name"));
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }
    
    public ArrayList<Object> viewAll() {
        ArrayList<Object> collections = new ArrayList<>();
        try {
            this.statement = (Statement) MyModel.conn.createStatement();
            this.result = this.statement.executeQuery(""
                    + "SELECT * FROM restaurant");

            while (this.result.next()) {
                Restaurant r = new Restaurant(
                        this.result.getInt("id"),
                        this.result.getString("restaurant_name"),
                        this.result.getString("restaurant_owner"),
                        this.result.getInt("number_seats"),
                        this.result.getString("username"),
                        this.result.getString("password")
                );
                collections.add(r);
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return collections;
    }
    
    public String sendName(String username) {
        String status = "";
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select restaurant_name from restaurant where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getString("restaurant_name");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }
    public int sendId(String username) {
        int status = 0;
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select id from restaurant where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getInt("id");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }
    
    public String sendInfo(String username){
        String status = "";
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select * from restaurant where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getString("id") + " " + this.result.getString("restaurant_name") + " " + this.result.getString("restaurant_owner") +" " + this.result.getString("number_seats") +" " + this.result.getString("username");
                //System.out.println(status);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }
     
     public int sendSeats(String restaurantName) {
        int status = 0;
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select number_seats from restaurant where restaurant_name=?");
            sql.setString(1, restaurantName);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getInt("number_seats");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }
}
