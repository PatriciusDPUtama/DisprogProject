/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author ASUS1
 */
@WebService(serviceName = "UAS_WebServices")
public class UAS_WebServices {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAvailableSeats")
    public int getAvailableSeats(@WebParam(name = "seats") int seats, @WebParam(name = "restaurant_seats") int restaurant_seats) {
        //TODO write your implementation code here:
        return restaurant_seats - seats;
    }

    /**
     * Web service operation
     */
    
}
