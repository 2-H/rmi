
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ali Al-Jobouri
 */
public class MainForm extends javax.swing.JFrame {

    public static String bindingString = "rmi://localhost:1234/Supervisor";//SupervisorF
    //public SupervisorInterface SRI;
    /**
     * Creates new form MainForm
     */
    private MainForm frm;

    public MainForm() throws IOException {
        initComponents();
        frm = this;

        tbSensors.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row

                if (tbSensors.getSelectedRow() >= 0) {
                    int index = Integer.parseInt(tbSensors.getValueAt(tbSensors.getSelectedRow(), 1).toString());
                    System.out.println(index);
                    try {
                        ShowImage(index);
                    } catch (Exception ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        refreshTable();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jScrollPane1.setViewportView(tbSensors);

        jLabel1.setText("Sensors");

        jButton2.setText("Save Sensor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("X1");

        jLabel3.setText("Y1");

        jLabel4.setText("X2");

        jLabel5.setText("Y2");

        jLabel6.setText("Y2");

        jLabel7.setText("X2");

        jLabel8.setText("Y1");

        jLabel9.setText("X1");

        jButton3.setText("Get Region");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        comboParent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NaN" }));
        comboParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParentActionPerformed(evt);
            }
        });

        imageregion.setText(".");

        imagelabel1.setText(".");

        jLabel10.setText("Parent");

        x1Mod.setEnabled(false);

        y1Mod.setEnabled(false);

        x2Mod.setEnabled(false);

        y2Mod.setEnabled(false);

        jLabel11.setText("X1");

        jLabel12.setText("Y1");

        jLabel13.setText("X2");

        jLabel14.setText("Y2");

        jLabel15.setText("Status");

        comboMod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wake", "StandBy", "Stopped" }));
        comboMod.setEnabled(false);

        buttonMod.setText("Modify");
        buttonMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(x1Mod, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel12)
                                            .addComponent(y1Mod, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel7))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(x2Mod, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(y2Mod, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(comboMod, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtregionx1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtregiony1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtregionx2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtregiony2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonMod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imageregion, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsensorx1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsensory1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsensorx2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsensory2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboParent, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                                        .addComponent(jButton2)))
                                .addGap(61, 61, 61))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(imagelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsensorx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsensory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsensorx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsensory2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(comboParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(imagelabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(x1Mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(y1Mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(x2Mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(y2Mod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonMod))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtregionx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtregiony1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtregionx2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtregiony2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)))
                    .addComponent(imageregion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtsensorx1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowImage(int index) throws IOException, RemoteException, NotBoundException, Exception {

        SensorInterface sn = (SensorInterface) Naming.lookup("rmi://localhost:1236/Sensor" + index);
        SensorData sd = sn.getSensorData();

        System.out.println("MainForm.ShowImage() " + sd.image.length);
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
            } else if (state.equalsIgnoreCase("stopped")) {
                comboMod.setSelectedIndex(2);
            }
        }
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
                JOptionPane.showMessageDialog(null, "please ensure : x1<x2 and y1<y2");
            } else {
                String parentIndexString = String.valueOf(comboParent.getSelectedItem());
                int parentIndex = !parentIndexString.equals("NaN") ? Integer.parseInt(parentIndexString) : -1;
                Sensor s = new Sensor(x1, y1, x2, y2, parentIndex);
                try {
                    ShowImage(s.index);

                } catch (Exception ex) {
                    Logger.getLogger(MainForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                refreshTable();

            }
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        //Add to comboBox


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        SupervisorInterface SRI;
        try {
            SRI = (SupervisorInterface) Naming.lookup(bindingString);
            ArrayList<SensorInterface> lst = SRI.getSensors();
            int x1 = Integer.parseInt(txtregionx1.getText());
            int y1 = Integer.parseInt(txtregiony1.getText());
            int x2 = Integer.parseInt(txtregionx2.getText());
            int y2 = Integer.parseInt(txtregiony2.getText());
            if (x1 >= x2 || y1 >= y2) {
                JOptionPane.showMessageDialog(null, "please ensure : x1<x2 and y1<y2");

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

                    System.out.println(index + " sd.x1.x " + sd.x1.x + "," + sd.x1.y + "_" + sd.x2.x + "," + sd.x2.y);

                    System.out.println(index + " x1 " + x1 + "," + y1 + "_" + x2 + "," + y2);

                    System.out.println(index + " newx1 " + newx1 + "," + newy1 + "_" + newx2 + "," + newy2);

                    System.out.println(index + " cropx1 " + cropx1 + "," + cropy1 + "_" + cropx2 + "," + cropy2);
                    System.out.println(index + " newrectx1 " + newrectx1 + "," + newrecty1 + "_" + newrectx2 + "," + newrecty2);

                    int w = Math.abs(newx2 - newx1);
                    int h = Math.abs(newy2 - newy1);
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
                /*
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

                    int w = Math.abs(x2 - x1);
                    int h = Math.abs(y2 - y1);

                    if (!i.isEmpty()) {
                        {
                            BufferedImage src = ImageIO.read(new ByteArrayInputStream(sd.image));
                            ImageIO.write(src, "png", new File("sensor_" + index + ".png"));
                            ImageIO.write(src, "jpg", new File("sensor_" + index + ".jpg"));

                            dst.getGraphics().drawImage(src, newx1, newy1, newx2, newy2, newx1, newy1, newx2, newy2,
                                    null);
                            ImageIO.write(dst, "png", new File("sensor_cropped_" + index + "_width_" + (int) i.getWidth() + "_height_" + (int) i.getHeight() + "_.png"));
                            ImageIO.write(dst, "jpg", new File("sensor_cropped_" + index + "_width_" + (int) i.getWidth() + "_height_" + (int) i.getHeight() + "_.jpg"));

                        }
                    }
                }
                ImageIO.write(dst, "png", new File("sensor_final.png"));
                 */
            }
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        controllerSwitch(true);
        int sensorId;
        if (buttonMod.getText().equalsIgnoreCase("modify")) {
            buttonMod.setText("Accept");

        } else if (buttonMod.getText().equalsIgnoreCase("accept")) {
            try {

                sensorId = (int) tbSensors.getValueAt(tbSensors.getSelectedRow(), 1);
                int newX1 = Integer.parseInt(x1Mod.getText());
                int newY1 = Integer.parseInt(y1Mod.getText());
                int newX2 = Integer.parseInt(x2Mod.getText());
                int newY2 = Integer.parseInt(y2Mod.getText());
                if (newX1 >= newX2 || newY1 >= newY2) {
                    JOptionPane.showMessageDialog(null, "please ensure : x1<x2 and y1<y2");
                    return;
                }
                SupervisorInterface SRI = (SupervisorInterface) Naming.lookup(bindingString);
                ArrayList<SensorInterface> arr = SRI.getSensors();
                for (SensorInterface s : arr) {
                    if (s.getSensorData().index == sensorId) {
                        s.configureRegion(new Point(newX1, newY1), new Point(newX2, newY2));
                        break;
                    }
                }
                modify(tbSensors.getSelectedRows()[0], newX1, newY1, newX2, newY2, "Wake");
                controllerSwitch(false);
                buttonMod.setText("Modify");
                ShowImage(sensorId);
            } catch (Exception ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_buttonModActionPerformed

    /**
     * @param args the command line arguments
     */
    private void refreshTable() {
        // while (true) {
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
                    //if(index==0)
                    //    ShowImage(sd.image);
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
                Logger.getLogger(MainForm.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
            Logger
                    .getLogger(MainForm.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        //}
    }

    public static void main(String args[]) throws InterruptedException {
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
            java.util.logging.Logger.getLogger(MainForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainForm().setVisible(true);

                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Supervisor.main(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonMod;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
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
