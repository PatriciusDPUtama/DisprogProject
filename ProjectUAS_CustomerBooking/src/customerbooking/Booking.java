/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package customerbooking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Booking extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form Booking
     */
    Socket clientSocket;
    Thread t;
    BufferedReader chatFromServer;
    DataOutputStream sendToServer;
    String name;
    String chatServer;

    public Booking(Socket socketClient, String nama, String chatServer) {
        try {
            initComponents();

            //cbChat.setSelectedItem(null);
            clientSocket = socketClient;
            chatFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendToServer = new DataOutputStream(clientSocket.getOutputStream());

            name = nama;
            sendToServer.writeBytes("Ezbooking " + nama + "\n");
            System.out.println("sent to server " + nama);
            String namaCust = chatFromServer.readLine();
            lblWelcome.setText("WELCOME, " + nama);
            customerTxt.setText(nama);

            sendToServer.writeBytes("List" + "\n");
            System.out.println("new");
            String[] array = null;
            chatServer = chatFromServer.readLine();
            array = chatServer.split(" ");
            System.out.println(array[0]);
            for (String name : array) {
                cboRestaurant.addItem(name);
            }
            cboTime.addItem("10:00");
            cboTime.addItem("11:00");
            cboTime.addItem("12:00");
            cboTime.addItem("13:00");
            cboTime.addItem("14:00");
            cboTime.addItem("15:00");
            cboTime.addItem("16:00");
            cboTime.addItem("17:00");
            cboTime.addItem("18:00");
            cboTime.addItem("19:00");
            cboTime.addItem("20:00");
            cboTime.addItem("21:00");
            //refreshTable();
            //this.start();
            getTable();
        } catch (IOException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        if (t == null) {
            t = new Thread(this, "client");
            t.setPriority(5);
            t.start();
        }
    }

    public void getMessage() throws IOException {
        System.out.println("Welcome to EzBooking");

        String chatServer;

        chatServer = chatFromServer.readLine();
//        txtChat.append("Server: " + chatServer + "\n");
        //txtChat.append(chatServer + "\n");
    }


    @Override
    public void run() {
        while (true) {
            try {
                
                getMessage();
                
            } catch (IOException ex) {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBooking = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        customerTxt = new javax.swing.JTextField();
        cboTime = new javax.swing.JComboBox<>();
        bookDate = new com.toedter.calendar.JDateChooser();
        cboRestaurant = new javax.swing.JComboBox<>();
        numOfPeople = new javax.swing.JSpinner();
        btnEdit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcome.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("WELCOME, ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Restaurant Name:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Number of People:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Reservation Date:");

        btnBooking.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBooking.setText("BOOK");
        btnBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookingActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Customer Name:");

        customerTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        customerTxt.setEnabled(false);

        cboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimeActionPerformed(evt);
            }
        });

        bookDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookDateMouseClicked(evt);
            }
        });

        cboRestaurant.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboRestaurant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRestaurantActionPerformed(evt);
            }
        });

        numOfPeople.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        numOfPeople.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        btnEdit.setText("Edit Info");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(btnBooking)
                    .addComponent(btnEdit))
                .addGap(20, 211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bookDate, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cboRestaurant, javax.swing.GroupLayout.Alignment.LEADING, 0, 170, Short.MAX_VALUE)
                        .addComponent(customerTxt, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(numOfPeople, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(187, 187, 187))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblWelcome)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(customerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboRestaurant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numOfPeople, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(bookDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnEdit)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void getTable() {
        try {
            sendToServer.writeBytes("GetTable " + name + "\n");
            chatServer = chatFromServer.readLine();
            
            String[] columnNames = {"Id", "Customer", "Restaurant", "Number of People", "Data and Time"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            Object[] data = new Object[5];
            tableData.setModel(model);

            String messageSplit[] = chatServer.split("&");
            int numberOfItems = messageSplit.length;
            String numberOfUser = messageSplit[0];
            String dataNumber[];

            for (int i = 1; i < numberOfItems; i++) {
                String dataText = "";
                dataNumber = messageSplit[i].split(";");
                data[0] = i;
                for (int x = 0; x < dataNumber.length; x++) {
                    dataText += dataNumber[x] + " ";
                    data[x + 1] = dataNumber[x];
                }
                System.out.println(dataText);
                model.addRow(data);
            }
        } catch (Exception e) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void btnBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookingActionPerformed
        // TODO add your handling code here:
        try {
            //1
            String custName = customerTxt.getText();
            System.out.println(custName);

            //2
            String restaurantName = String.valueOf(cboRestaurant.getSelectedItem());
            System.out.println(restaurantName);

            //3
            int tempNumOfPeople = (int) numOfPeople.getValue();
            if (tempNumOfPeople < 1) {
                JOptionPane.showMessageDialog(this, "Jumlah orang kurang dari 1", "Error", JOptionPane.ERROR_MESSAGE);
            }

            //4
            Date date = bookDate.getDate();
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String jam = cboTime.getSelectedItem() + ":00";
            String tanggalPesan = dateformat.format(date) + jam;
            System.out.println(tanggalPesan);
            if (customerTxt.getText() != "") {
                if (tempNumOfPeople >= 1) {
                    if (bookDate != null) {
                        if (cboRestaurant.getSelectedIndex() > 0) {
                            if ((cboRestaurant.getSelectedIndex() > 0) && (customerTxt.getText() != "") && (tempNumOfPeople >= 1) && (bookDate != null)) {

                                sendToServer.writeBytes("BookNow " + custName + " " + restaurantName + " " + tempNumOfPeople + " " + dateformat.format(date) + " " + jam + "\n");
                                chatServer = chatFromServer.readLine();

                                if (chatServer.contains("available")) {
                                    JOptionPane.showMessageDialog(this, "Thankyou for booking");
                                } else if(chatServer.contains("penuh")){
                                    JOptionPane.showMessageDialog(this, "Full, please change the date");
                                }
                            }
                        } else {
                            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, "CHOOSE A RESTAURANT");
                        }
                    } else {
                        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, "PICK A BOOK DATE AND TIME");
                    }
                } else {
                    Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, "INSERT YOUR NUMBER OF PEOPLE PROPERLY");
                }
            } else {
                Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, "INSERT YOUR NAME");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }//GEN-LAST:event_btnBookingActionPerformed

    private void cboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboTimeActionPerformed

    private void cboRestaurantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRestaurantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRestaurantActionPerformed

    private void bookDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookDateMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_bookDateMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        new Edit(clientSocket, customerTxt.getText(), chatServer).setVisible(true);
        this.setVisible(false);
        System.out.println("dari booking " +customerTxt.getText());
    }//GEN-LAST:event_btnEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Booking.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Booking.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Booking.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Booking.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser bookDate;
    private javax.swing.JButton btnBooking;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> cboRestaurant;
    private javax.swing.JComboBox<String> cboTime;
    private javax.swing.JTextField customerTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JSpinner numOfPeople;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}
