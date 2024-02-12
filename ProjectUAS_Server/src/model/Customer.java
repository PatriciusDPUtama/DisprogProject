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
 * @author ASUS1
 */
public class Customer extends MyModel {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Customer(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    int id;
    String username;
    String password;
    String name;

    public Customer() {
        this.username = "username";
        this.password = "password";
        this.name = "name";
    }

    public Customer(String username) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    @Override
    public void insertData() {
        System.out.println("Start Create override");
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "insert into customer(username,password,name) values(?,?,?)");
                sql.setString(1, this.username);
                sql.setString(2, this.password);
                sql.setString(3, this.name);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Create");
            } else {
                System.out.println("Koneksi Hilang insert data");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertData(String username, String name, String password) {
        System.out.println("Start Create");
        try {
            if (!conn.isClosed()) {
                System.out.println(username + "" + name + "" + password);
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "insert into customer(username,name,password) values(?,?,?)");
                sql.setString(1, username);
                sql.setString(2, name);
                sql.setString(3, password);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Create");
            } else {
                System.out.println("Koneksi Hilang insert data");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean checkUsername(String username) {
        boolean status = false;
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select * from customer where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }

    public boolean checkLogin(String user, String pass) {
        boolean status = false;
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select * from customer where username=? and password=?");
            sql.setString(1, user);
            sql.setString(2, pass);
            result = sql.executeQuery();
            if (result.next()) {
                status = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }

    @Override
    public void updateData() {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "UPDATE customer SET username=?, name=?, password=? WHERE id=?");
                sql.setString(1, this.username);
                sql.setString(2, this.name);
                sql.setString(3, this.password);
                sql.setString(4, this.username);

                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull update");
            } else {
                System.out.println("Koneksi Hilang insert data");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void updateData(int id, String username, String name, String password) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "UPDATE customer SET username=?, name=?, password=? WHERE id=?");
                sql.setString(1, username);
                sql.setString(2, name);
                sql.setString(3, password);
                sql.setInt(4, id);
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
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "DELETE FROM customer WHERE id = ?");
                sql.setInt(1, this.id);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Delete");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteData(int id) {
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "DELETE FROM customer WHERE id = ?");
                sql.setInt(1, id);
                sql.executeUpdate();
                sql.close();
                System.out.println("Successfull Delete");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<String> viewNames() {
        ArrayList<String> listOfObject = new ArrayList<String>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from customer");
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

    public String sendName(String username) {
        String status = "";
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select name from customer where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getString("name");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }

    @Override
    public ArrayList<Object> viewListData() {
        ArrayList<Object> listOfObject = new ArrayList<Object>();
        try {
            if (!MyModel.conn.isClosed()) {
                PreparedStatement sql = (PreparedStatement) MyModel.conn.prepareStatement(
                        "select * from customer");
                this.result = sql.executeQuery();
                while (result.next()) {
                    Customer temp = new Customer(result.getString("username"), result.getString("password"), result.getString("name"));
                    listOfObject.add(temp);
                }
                System.out.println("Successfull Create");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return listOfObject;
    }

    public String sendAll(String username) {
        String status = "";
        try {
            PreparedStatement sql;
            sql = (PreparedStatement) conn.prepareStatement("select * from customer where username=?");
            sql.setString(1, username);
            result = sql.executeQuery();
            if (result.next()) {
                status = this.result.getString("id") + " " + this.result.getString("name") + " " + this.result.getString("username");
                //System.out.println(status);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }

}
