/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectuas_server;

/**
 *
 * @author ASUS1
 */
public class ProjectUAS_Server {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        ServerTCP serverTCP = new ServerTCP();
        ServerUDP serverUDP = new ServerUDP();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
