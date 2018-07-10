
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainForm extends javax.swing.JFrame {

    public static String bindingString = "rmi://localhost:1234/Supervisor";
    public static String dataServerBinder;
    public static String supervisorIP, dataServerIP;
    private static MainForm frm;

    public MainForm() throws IOException {
        initComponents();
        frm = this;

        tbSensors.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (tbSensors.getSelectedRow() >= 0) {
                    int index = Integer.parseInt(tbSensors.getValueAt(tbSensors.getSelectedRow(), 1).toString());
                    System.out.println(index);
                    try {
                        ShowImage(index);
                    } catch (Exception ex) {
                        System.out.println("Fatal error: " + ex.toString());
                    }
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SupervisorInterface SRI = (SupervisorInterface) Naming.lookup("rmi://" + supervisorIP + ":1234/Supervisor");
                    while (true) {
                        Thread.sleep(15000);
                        SRI.balanceKids();
                        refreshTable();
                    }
                } catch (Exception ex) {
                    System.out.println("Fatal error: " + ex.toString());
                }
            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbSensors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtsensorx1 = new javax.swing.JTextField();
        txtsensory1 = new javax.swing.JTextField();
        txtsensorx2 = new javax.swing.JTextField();
        txtsensory2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregionx1 = new javax.swing.JTextField();
        txtregiony1 = new javax.swing.JTextField();
        txtregionx2 = new javax.swing.JTextField();
        txtregiony2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        comboParent = new javax.swing.JComboBox<>();
        imageregion = new javax.swing.JLabel();
        imagelabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        x1Mod = new javax.swing.JTextField();
        y1Mod = new javax.swing.JTextField();
        x2Mod = new javax.swing.JTextField();
        y2Mod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        comboMod = new javax.swing.JComboBox<>();
        buttonMod = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnRestart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnRefreshTable = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboImage = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbSensors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sensor", "Id", "X1", "Y1", "X2", "Y2", "Status", "Parent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSensors.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tbSensors);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 89, 600, 210));

        jLabel1.setText("Sensors");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 68, -1, -1));

        jButton2.setText("Save Sensor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 40, -1, -1));
        getContentPane().add(txtsensorx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 42, -1));
        txtsensorx1.getAccessibleContext().setAccessibleName("");

        getContentPane().add(txtsensory1, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 41, 42, -1));
        getContentPane().add(txtsensorx2, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 41, 42, -1));
        getContentPane().add(txtsensory2, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 41, 42, -1));

        jLabel2.setText("X1");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, 16, -1));

        jLabel3.setText("Y1");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 15, 16, -1));

        jLabel4.setText("X2");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 15, -1, -1));

        jLabel5.setText("Y2");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 15, -1, -1));
        getContentPane().add(txtregionx1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 382, 42, -1));
        getContentPane().add(txtregiony1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 382, 42, -1));
        getContentPane().add(txtregionx2, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 382, 42, -1));
        getContentPane().add(txtregiony2, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 382, 42, -1));

        jLabel6.setText("Y2");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 360, -1, -1));

        jLabel7.setText("X2");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 360, -1, -1));

        jLabel8.setText("Y1");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 360, 16, -1));

        jLabel9.setText("X1");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 16, -1));

        jButton3.setText("Get Region");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(206, 381, -1, -1));

        comboParent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NaN" }));
        comboParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParentActionPerformed(evt);
            }
        });
        getContentPane().add(comboParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 41, 127, -1));

        imageregion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageregion.setText(".");
        imageregion.setToolTipText("");
        imageregion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        imageregion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        imageregion.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        imageregion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(imageregion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 730, 260));

        imagelabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagelabel1.setText(".");
        imagelabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(imagelabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 280, 173));

        jLabel10.setText("Parent");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(242, 15, 70, -1));

        x1Mod.setEnabled(false);
        getContentPane().add(x1Mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 42, -1));

        y1Mod.setEnabled(false);
        getContentPane().add(y1Mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 320, 42, -1));

        x2Mod.setEnabled(false);
        getContentPane().add(x2Mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 320, 42, -1));

        y2Mod.setEnabled(false);
        getContentPane().add(y2Mod, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 320, 42, -1));

        jLabel11.setText("X1");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 305, -1, -1));

        jLabel12.setText("Y1");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 305, -1, -1));

        jLabel13.setText("X2");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 305, -1, -1));

        jLabel14.setText("Y2");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 305, -1, -1));

        jLabel15.setText("Status");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 300, 50, 20));

        comboMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wake", "StandBy" }));
        comboMod.setEnabled(false);
        getContentPane().add(comboMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 320, 89, -1));

        buttonMod.setText("Modify");
        buttonMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModActionPerformed(evt);
            }
        });
        getContentPane().add(buttonMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 319, 85, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1209, 873, 14, 198));

        btnRestart.setText("Restart");
        btnRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestartActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        getContentPane().add(btnStop, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        btnRefreshTable.setText("Refresh");
        btnRefreshTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshTableActionPerformed(evt);
            }
        });
        getContentPane().add(btnRefreshTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        jLabel17.setText("Requested region");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, -1, -1));

        jLabel18.setText("Sensor region");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, -1));

        jLabel16.setText("Available photos");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        comboImage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bird.jpg", "Cat.jpg", "LU.jpg" }));
        comboImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboImageActionPerformed(evt);
            }
        });
        getContentPane().add(comboImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changeControllerView() {
        if (tbSensors.getSelectedRowCount() > 0) {
            int r = tbSensors.getSelectedRow();
            x1Mod.setText(tbSensors.getValueAt(r, 2).toString());
            y1Mod.setText(tbSensors.getValueAt(r, 3).toString());
            x2Mod.setText(tbSensors.getValueAt(r, 4).toString());
            y2Mod.setText(tbSensors.getValueAt(r, 5).toString());
            String state = tbSensors.getValueAt(r, 6).toString();
            if (state.equalsIgnoreCase("wake")) {
                comboMod.setSelectedIndex(0);
            } else if (state.equalsIgnoreCase("standby")) {
                comboMod.setSelectedIndex(1);
            }
        }
    }

    private void ShowImage(int index) throws IOException, RemoteException, NotBoundException, Exception {
        SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
        ArrayList<Integer> waitingSensors = SRI.getWaitingSensors();
        if (waitingSensors.contains(index)
                || (tbSensors.getSelectedRowCount() > 0 && !tbSensors.getValueAt(tbSensors.getSelectedRow(), 6).toString().equals("Wake"))) {
            imagelabel1.setIcon(null);
            return;
        }
        String bindWithSensor = "rmi://" + SRI.getSensorIP(index) + ":1236/Sensor" + index;
        SensorInterface sn = (SensorInterface) Naming.lookup(bindWithSensor);
        SensorData sd = sn.getSensorData();
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(sd.image));
        imagelabel1.setIcon(new Icon() {
            @Override
            public void paintIcon(Component cmpnt, Graphics grphcs, int i, int i1) {
                grphcs.drawImage(image, 0, 0, frm); // see javadoc for more info on the parameters          
            }

            @Override
            public int getIconWidth() {
                return 400;
            }

            @Override
            public int getIconHeight() {
                return 400;
            }
        });
        changeControllerView();
    }

    private void modify(int rowNumber, int x1, int y1, int x2, int y2, String state) {
        tbSensors.setValueAt(x1, rowNumber, 2);
        tbSensors.setValueAt(y1, rowNumber, 3);
        tbSensors.setValueAt(x2, rowNumber, 4);
        tbSensors.setValueAt(y2, rowNumber, 5);
        tbSensors.setValueAt(state, rowNumber, 6);

    }

    private void ShowImageRegion(BufferedImage image) throws IOException, RemoteException, NotBoundException, Exception {

        imageregion.setIcon(new Icon() {
            @Override
            public void paintIcon(Component cmpnt, Graphics grphcs, int i, int i1) {
                grphcs.drawImage(image, 0, 0, frm); // see javadoc for more info on the parameters          
            }

            @Override
            public int getIconWidth() {
                return 200;
            }

            @Override
            public int getIconHeight() {
                return 200;
            }
        });
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            int x1 = Integer.parseInt(txtsensorx1.getText());
            int y1 = Integer.parseInt(txtsensory1.getText());
            int x2 = Integer.parseInt(txtsensorx2.getText());
            int y2 = Integer.parseInt(txtsensory2.getText());
            if (x1 >= x2 || y1 >= y2) {
                JOptionPane.showMessageDialog(null, "Please ensure : x1<x2 and y1<y2.");
            } else {
                String parentIndexString = String.valueOf(comboParent.getSelectedItem());
                int parentIndex;
                if (!parentIndexString.equals("NaN")) {
                    parentIndex = Integer.parseInt(parentIndexString);
                } else {
                    parentIndex = -1;
                }
                Sensor s = new Sensor(x1, y1, x2, y2, parentIndex, supervisorIP, dataServerIP);
                try {
                    ShowImage(s.index);
                } catch (Exception ex) {
                    System.out.println("Fatal error: " + ex.toString());
                }
                refreshTable();
                txtsensorx1.setText("");
                txtsensorx2.setText("");
                txtsensory1.setText("");
                txtsensory2.setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error while creating a sensor.\n" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SupervisorInterface SRI;
        try {
            ShowImageRegion(null);
            SRI = (SupervisorInterface) Naming.lookup(bindingString);
            ArrayList<SensorInterface> allSensors = SRI.getSensors();
            ArrayList<Integer> waitingSensors = SRI.getWaitingSensors();
            ArrayList<SensorInterface> lst = new ArrayList<>();
            for (SensorInterface s : allSensors) {
                if (!waitingSensors.contains(s.getSensorData().index)) {
                    lst.add(s);
                }
            }
            int x1 = Integer.parseInt(txtregionx1.getText());
            int y1 = Integer.parseInt(txtregiony1.getText());
            int x2 = Integer.parseInt(txtregionx2.getText());
            int y2 = Integer.parseInt(txtregiony2.getText());
            if (x1 >= x2 || y1 >= y2) {
                JOptionPane.showMessageDialog(null, "Please ensure : x1<x2 and y1<y2.");
            } else {
                BufferedImage dst = new BufferedImage(x2 - x1, y2 - y1, BufferedImage.TYPE_INT_ARGB);
                int index = 0;
                for (SensorInterface s : lst) {
                    index++;
                    SensorData sd = s.getSensorData();
                    Rectangle rect1 = new Rectangle(x1, y1, x2 - x1, y2 - y1);
                    Rectangle rect2 = new Rectangle(sd.x1.x, sd.x1.y, sd.x2.x - sd.x1.x, sd.x2.y - sd.x1.y);
                    Rectangle i = rect1.intersection(rect2);
                    int newx1 = (int) i.getX();
                    int newy1 = (int) i.getY();
                    int newx2 = (int) i.getX() + (int) i.getWidth();
                    int newy2 = (int) i.getY() + (int) i.getHeight();
                    //coordinates of intersection with respect to sensor image
                    int cropx1 = Math.abs(newx1 - sd.x1.x);
                    int cropy1 = Math.abs(newy1 - sd.x1.y);
                    int cropx2 = Math.abs(newx2 - sd.x1.x);
                    int cropy2 = Math.abs(newy2 - sd.x1.y);
                    //coordinates of intersection with respect to new region
                    int newrectx1 = Math.abs(newx1 - x1);
                    int newrecty1 = Math.abs(newy1 - y1);
                    int newrectx2 = Math.abs(newx2 - x1);
                    int newrecty2 = Math.abs(newy2 - y1);
                    if (!i.isEmpty()) {
                        {
                            BufferedImage src = ImageIO.read(new ByteArrayInputStream(sd.image));
                            ImageIO.write(src, "png", new File("sensor_" + index + ".png"));
                            dst.getGraphics().drawImage(src, newrectx1, newrecty1, newrectx2, newrecty2, cropx1, cropy1, cropx2, cropy2, null);
                            ImageIO.write(dst, "png", new File("sensor_cropped_" + index + "_width_" + (int) i.getWidth() + "_height_" + (int) i.getHeight() + "_.png"));
                        }
                    }
                    ImageIO.write(dst, "png", new File("sensor_final.png"));
                    ShowImageRegion(dst);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to preview region." + "\n" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void controllerSwitch(boolean val) {
        x1Mod.setEnabled(val);
        x2Mod.setEnabled(val);
        y1Mod.setEnabled(val);
        y2Mod.setEnabled(val);
        comboMod.setEnabled(val);
    }

    private void comboParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboParentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboParentActionPerformed

    private void buttonModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModActionPerformed
        if (tbSensors.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "No row is selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tbSensors.getValueAt(tbSensors.getSelectedRow(), 6).toString().equalsIgnoreCase("stopped")) {
            JOptionPane.showMessageDialog(null, "Sensor is no more usable.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        controllerSwitch(true);
        int sensorId;
        if (buttonMod.getText().equalsIgnoreCase("modify")) {
            buttonMod.setText("Accept");
            return;
        }
        try {
            sensorId = (int) tbSensors.getValueAt(tbSensors.getSelectedRow(), 1);
            int newX1 = Integer.parseInt(x1Mod.getText());
            int newY1 = Integer.parseInt(y1Mod.getText());
            int newX2 = Integer.parseInt(x2Mod.getText());
            int newY2 = Integer.parseInt(y2Mod.getText());
            int newState = comboMod.getSelectedIndex();
            if (newX1 >= newX2 || newY1 >= newY2) {
                JOptionPane.showMessageDialog(null, "Please ensure : x1<x2 and y1<y2.");
                return;
            }
            SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
            ArrayList<SensorInterface> arr = SRI.getSensors();
            for (SensorInterface s : arr) {
                if (s.getSensorData().index == sensorId) {
                    s.configureRegion(new Point(newX1, newY1), new Point(newX2, newY2));
                    if (newState == 0) {
                        SRI.removeFromWaiting(s.getSensorData().index);
                        synchronized (s) {
                            s.notify();
                            s.changeState(1);
                        }
                    } else if (newState == 1) {
                        if (!SRI.getWaitingSensors().contains(s.getSensorData().index)) {
                            SRI.addToWaiting(s.getSensorData().index);
                            s.changeState(2);
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                synchronized (s) {
                                    try {
                                        s.wait();
                                    } catch (InterruptedException ex) {
                                        System.out.println("Fatal error: " + ex.toString());
                                    }
                                }
                            }
                        }).start();
                    }
                    break;
                }
            }
            modify(tbSensors.getSelectedRows()[0], newX1, newY1, newX2, newY2, (newState == 0) ? "Wake" : "StandBy");
            controllerSwitch(false);
            buttonMod.setText("Modify");
            ShowImage(sensorId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occured while trying to modify.\n" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonModActionPerformed

    private void btnRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartActionPerformed
        if (tbSensors.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "No row is selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (tbSensors.getValueAt(tbSensors.getSelectedRow(), 6).toString().equalsIgnoreCase("stopped")) {
            JOptionPane.showMessageDialog(null, "Sensor is no more usable.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tbSensors.getValueAt(tbSensors.getSelectedRow(), 6).toString().equalsIgnoreCase("standby")) {
            JOptionPane.showMessageDialog(null, "Status must be wake to restart.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tbSensors.getRowCount() > 0) {
            try {
                int index = (int) tbSensors.getValueAt(tbSensors.getSelectedRow(), 1);
                SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);

                SensorInterface s = (SensorInterface) Naming.lookup("rmi://" + SRI.getSensorIP(index) + ":1236/Sensor" + index);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tbSensors.setValueAt("Restarting...", tbSensors.getSelectedRow(), 6);
                            s.changeState(4);
                            Thread.sleep(1000);
                            tbSensors.setValueAt("Wake", tbSensors.getSelectedRow(), 6);
                        } catch (InterruptedException | RemoteException ex) {
                            System.out.println("Fatal error: " + ex.toString());
                        }
                    }
                }).start();
                synchronized (this) {
                    this.wait(1000);
                }
            } catch (MalformedURLException | NotBoundException | RemoteException | InterruptedException ex) {
                System.out.println("Fatal error: " + ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error while trying to restart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRestartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if (tbSensors.getSelectedRowCount() < 1) {
            JOptionPane.showMessageDialog(null, "No row is selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = (int) tbSensors.getValueAt(tbSensors.getSelectedRow(), 1);
            SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
            SRI.removeSensor(index, SRI.getSensorIP(index));
            refreshTable();
            updateComboParents();
            ShowImage(index);
        } catch (Exception ex) {
            System.out.println("Fatal error: " + ex.toString());
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnRefreshTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshTableActionPerformed
        try {
            refreshTable();
            updateComboParents();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error while refreshing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRefreshTableActionPerformed

    private void comboImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboImageActionPerformed
        try {
            DataServerHost ds = (DataServerHost) Naming.lookup(dataServerBinder);
            ds.setImagePath(comboImage.getSelectedItem().toString());
        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            System.out.println("Fatal error: "+ex.toString());
        }
    }//GEN-LAST:event_comboImageActionPerformed

    private void updateComboParents() {
        try {
            SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
            comboParent.removeAllItems();
            comboParent.addItem("NaN");
            for (int parent : SRI.getSecondTier().keySet()) {
                comboParent.addItem(Integer.toString(parent));
            }
        } catch (Exception ex) {
           System.out.println("Fatal error: "+ex.toString());
        }

    }

    private void refreshTable() {
        try {
            SupervisorInterface SRI;
            try {
                SRI = (SupervisorInterface) Naming.lookup(bindingString);
                ArrayList<SensorInterface> lst = SRI.getSensors();
                HashMap<Integer, SensorInterface> secondTier = SRI.getSecondTier();
                DefaultTableModel model = (DefaultTableModel) tbSensors.getModel();
                model.setRowCount(0);
                int index = 0;
                String parent;
                for (SensorInterface s : lst) {
                    index++;
                    SensorData sd = s.getSensorData();
                    parent = (sd.parentIndex == -1) ? "NaN" : Integer.toString(sd.parentIndex);
                    model.addRow(new Object[]{"Sensor " + index, sd.index,
                        sd.x1.x, sd.x1.y, sd.x2.x, sd.x2.y, sd.State, parent});
                }
                comboParent.removeAllItems();
                comboParent.addItem("NaN");
                for (SensorInterface s : secondTier.values()) {
                    SensorData sd = s.getSensorData();
                    comboParent.addItem(Integer.toString(sd.index));

                }

            } catch (Exception ex) {
               System.out.println("Fatal error: "+ex.toString());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //}
    }

    public static void main(String args[]) throws InterruptedException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (Exception ex) {
            System.out.println("Fatal error: " + ex.toString());
        }
        try {
            SupervisorInterface SRI;
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter ip of supervisor: ");
            supervisorIP = sc.next();
            System.out.print("Enter ip of data server: ");
            dataServerIP = sc.next();
            System.out.println("Trying to connect to supevisor...");
            Naming.lookup("rmi://" + supervisorIP + ":1234/Supervisor");
            System.out.println("Trying to connect to dataserver...");
            Naming.lookup("rmi://" + dataServerIP + ":1235/DataServer");
            bindingString = "rmi://" + supervisorIP + ":1234/Supervisor";
            dataServerBinder = "rmi://" + dataServerIP + ":1235/DataServer";

        } catch (MalformedURLException | NotBoundException | RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Error while trying to connect to servers.\n" + ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainForm().setVisible(true);

                } catch (IOException ex) {
                    System.out.println("Fatal error: " + ex.toString());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefreshTable;
    private javax.swing.JButton btnRestart;
    private javax.swing.JButton btnStop;
    private javax.swing.JButton buttonMod;
    private javax.swing.JComboBox<String> comboImage;
    private javax.swing.JComboBox<String> comboMod;
    private javax.swing.JComboBox<String> comboParent;
    private javax.swing.JLabel imagelabel1;
    private javax.swing.JLabel imageregion;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tbSensors;
    private javax.swing.JTextField txtregionx1;
    private javax.swing.JTextField txtregionx2;
    private javax.swing.JTextField txtregiony1;
    private javax.swing.JTextField txtregiony2;
    private javax.swing.JTextField txtsensorx1;
    private javax.swing.JTextField txtsensorx2;
    private javax.swing.JTextField txtsensory1;
    private javax.swing.JTextField txtsensory2;
    private javax.swing.JTextField x1Mod;
    private javax.swing.JTextField x2Mod;
    private javax.swing.JTextField y1Mod;
    private javax.swing.JTextField y2Mod;
    // End of variables declaration//GEN-END:variables
}
