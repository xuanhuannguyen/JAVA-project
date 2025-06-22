/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.DateTimeException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class SearchIDIssue extends javax.swing.JFrame {

    /**
     * Creates new form ViewAllRecord
     */
    DefaultTableModel model;
    public SearchIDIssue() {
        initComponents();
        setIssueRecordToTable();
    }
    public void setIssueRecordToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from issue_book_details where status = '"+"pending"+"'");
            model =(DefaultTableModel) tbl_issueRecordDetails.getModel();
            model.setRowCount(0);
            while (rs.next()){
                   String id = rs.getString("id");
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String issueDate = rs.getString("issue_date");
                   String dueDate = rs.getString("due_date");
                   String status = rs.getString("status");
                   
                   Object[] obj = {id,bookID,bookName,studentID,studentName,issueDate,dueDate,status};
                   model.addRow(obj);
        
                }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void searchByStudentID() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String keySearch = txt_infor.getText();
            ResultSet rs=st.executeQuery("select * from issue_book_details where status = '"+"pending"+"' and student_ID = '"+keySearch+"'");
             if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
            model =(DefaultTableModel) tbl_issueRecordDetails.getModel();
            model.setRowCount(0);
           
           do{
                   String id = rs.getString("id");
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String issueDate = rs.getString("issue_date");
                   String dueDate = rs.getString("due_date");
                   String status = rs.getString("status");
                   
                   Object[] obj = {id,bookID,bookName,studentID,studentName,issueDate,dueDate,status};
                   model.addRow(obj);
        
                }      while (rs.next());       
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     public void searchByBookID() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String keySearch = txt_infor.getText();
            ResultSet rs=st.executeQuery("select * from issue_book_details where status = '"+"pending"+"' and book_ID = '"+keySearch+"'");
            if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
            model =(DefaultTableModel) tbl_issueRecordDetails.getModel();
            model.setRowCount(0);
            
             do{
                   String id = rs.getString("id");
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String issueDate = rs.getString("issue_date");
                   String dueDate = rs.getString("due_date");
                   String status = rs.getString("status");
                   
                   Object[] obj = {id,bookID,bookName,studentID,studentName,issueDate,dueDate,status};
                   model.addRow(obj);
        
                }      while (rs.next());      
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
     public void searchByStudentName() {
    // Lấy tên cần tìm từ text field
    String nameToSearch = txt_infor.getText();

    // Câu lệnh SQL sử dụng LIKE và tham số '?'
    String sql = "SELECT * FROM issue_book_details WHERE status = '"+"pending"+"'and student_name LIKE ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        // Gán giá trị cho tham số '?'
        // Quan trọng: Thêm ký tự '%' vào chuỗi Java trước khi setString
        pst.setString(1, "%" + nameToSearch + "%");

        ResultSet rs = pst.executeQuery();
        if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
        DefaultTableModel model = (DefaultTableModel) tbl_issueRecordDetails.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        
         do{
                   String id = rs.getString("id");
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String issueDate = rs.getString("issue_date");
                   String dueDate = rs.getString("due_date");
                   String status = rs.getString("status");
                   
                   Object[] obj = {id,bookID,bookName,studentID,studentName,issueDate,dueDate,status};
                   model.addRow(obj);
        
                }      while (rs.next());

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void searchByBookName() {
    String nameToSearch = txt_infor.getText();

    String sql = "SELECT * FROM issue_book_details WHERE status = '"+"pending"+"'and book_name LIKE ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "%" + nameToSearch + "%");

        ResultSet rs = pst.executeQuery();
         if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
        DefaultTableModel model = (DefaultTableModel) tbl_issueRecordDetails.getModel();
        model.setRowCount(0); 
        
         do{
                   String id = rs.getString("id");
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String issueDate = rs.getString("issue_date");
                   String dueDate = rs.getString("due_date");
                   String status = rs.getString("status");
                   
                   Object[] obj = {id,bookID,bookName,studentID,studentName,issueDate,dueDate,status};
                   model.addRow(obj);
        
                }      while (rs.next());

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    //sort function
     public void sortByStudentName() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM issue_book_details WHERE status = '"+"pending"+"' ORDER BY student_name ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_issueRecordDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            Object[] obj = {
                rs.getString("id"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                rs.getString("student_id"),
                rs.getString("student_name"), // Dữ liệu sẽ được sắp xếp theo cột này
                rs.getString("issue_date"),
                rs.getString("due_date"),
                rs.getString("status")
            };
            model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void sortByBookName() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM issue_book_details WHERE status = '"+"pending"+"' ORDER BY book_name ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_issueRecordDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            Object[] obj = {
                rs.getString("id"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                rs.getString("student_id"),
                rs.getString("student_name"), // Dữ liệu sẽ được sắp xếp theo cột này
                rs.getString("issue_date"),
                rs.getString("due_date"),
                rs.getString("status")
            };
            model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void sortByIssueDate() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM issue_book_details WHERE status = '"+"pending"+"' ORDER BY issue_date";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_issueRecordDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            Object[] obj = {
                rs.getString("id"),
                rs.getString("book_id"),
                rs.getString("book_name"),
                rs.getString("student_id"),
                rs.getString("student_name"), // Dữ liệu sẽ được sắp xếp theo cột này
                rs.getString("issue_date"),
                rs.getString("due_date"),
                rs.getString("status")
            };
            model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
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

        evaluatorLong1 = new org.jdesktop.core.animation.timing.evaluators.EvaluatorLong();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        boxSearch = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_infor = new app.bolivia.swing.JCTextField();
        jLabel3 = new javax.swing.JLabel();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        jLabel4 = new javax.swing.JLabel();
        boxSort = new javax.swing.JComboBox<>();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_issueRecordDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel14 = new javax.swing.JLabel();
        lbl_issueID = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_issueDate = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_dueDate = new javax.swing.JLabel();
        lbl_studentID = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rSButtonHover4 = new rojerusan.RSButtonHover();
        jLabel18 = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_bookID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("BACK");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel12.setFont(new java.awt.Font("Sitka Text", 1, 26)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel12.setText("Find Issue Recod");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 510, 110));

        jPanel15.setBackground(new java.awt.Color(255, 0, 0));
        jPanel15.setForeground(new java.awt.Color(255, 0, 0));
        jPanel15.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 250, 3));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Issue Date To:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 170, -1, 20));

        boxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Student Name", "Book ID", "Book Name" }));
        boxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSearchActionPerformed(evt);
            }
        });
        jPanel1.add(boxSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 260, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Enter information:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        txt_infor.setPlaceholder("Enter information...\n");
        txt_infor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inforActionPerformed(evt);
            }
        });
        jPanel1.add(txt_infor, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 260, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Search By:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        rSButtonHover1.setText("Search");
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 120, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Sort By:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 100, -1, -1));

        boxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student Name", "Book Name", "Issue Date", " " }));
        jPanel1.add(boxSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 100, 270, 30));

        rSButtonHover2.setText("Sort");
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 120, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 240));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_issueRecordDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Book ID", "Book Name", "Student ID", "Student Name", "Issue Date", "Due Date", "Status"
            }
        ));
        tbl_issueRecordDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_issueRecordDetails.setRowHeight(35);
        tbl_issueRecordDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_issueRecordDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_issueRecordDetails);
        if (tbl_issueRecordDetails.getColumnModel().getColumnCount() > 0) {
            tbl_issueRecordDetails.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 410));

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel14.setText("Issue ID:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));

        lbl_issueID.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_issueID, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 200, 20));

        jLabel15.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel15.setText("Book Name:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        lbl_bookName.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 200, 20));

        jLabel13.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel13.setText("Issue Date:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, -1, -1));

        lbl_issueDate.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, 200, 20));

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel17.setText("Due Date:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 500, -1, -1));

        lbl_dueDate.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 500, 200, 20));

        lbl_studentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 200, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel16.setText("Student ID:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, -1, 20));

        rSButtonHover4.setText("Select");
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 460, 130, -1));

        jLabel18.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel18.setText("Student Name:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, -1, 20));

        lbl_studentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 200, 20));

        jLabel19.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel19.setText("Book ID:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        lbl_bookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 200, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 1400, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new HomePage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void tbl_issueRecordDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_issueRecordDetailsMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_issueRecordDetails.getSelectedRow();
        TableModel model = tbl_issueRecordDetails.getModel();
        lbl_issueID.setText(model.getValueAt(rowNo,0).toString());
        lbl_bookID.setText(model.getValueAt(rowNo,1).toString());
        lbl_bookName.setText(model.getValueAt(rowNo,2).toString());
        lbl_studentID.setText(model.getValueAt(rowNo,3).toString());
        lbl_studentName.setText(model.getValueAt(rowNo,4).toString());
        lbl_issueDate.setText(model.getValueAt(rowNo,5).toString());
        lbl_dueDate.setText(model.getValueAt(rowNo,6).toString());
       
    }//GEN-LAST:event_tbl_issueRecordDetailsMouseClicked

    private void txt_inforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inforActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inforActionPerformed

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        String searchTarget= (String)boxSearch.getSelectedItem();
        if (searchTarget.equals("Student ID"))
        {
            searchByStudentID();
        }
        else if (searchTarget.equals("Book ID"))
        {
            searchByBookID();
        }
         else if (searchTarget.equals("Student Name"))
        {
            searchByStudentName();
        }
         else if (searchTarget.equals("Book Name")){
             searchByBookName();
         }
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        // TODO add your handling code here:
        String sortTarget = (String)boxSort.getSelectedItem();
        if (sortTarget.equals("Student Name"))
        {
            sortByStudentName();
        }
        else if (sortTarget.equals("Book Name"))
        {
            sortByBookName();
        }
        else 
        {
            sortByIssueDate();
        }
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void boxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSearchActionPerformed

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        // TODO add your handling code here:
        if (lbl_issueID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Select book first!s");
            return;
        }
        ReturnBooks returnBook = new ReturnBooks();
        returnBook.setLbl_issueID(lbl_issueID.getText());
        returnBook.setLbl_bookName(lbl_bookName.getText());
        returnBook.setLbl_studentName(lbl_studentName.getText());
        returnBook.setLbl_issueDate(lbl_issueDate.getText());
        returnBook.setLbl_dueDate(lbl_dueDate.getText());
        returnBook.setTxt_bookID(lbl_bookID.getText());
        returnBook.setTxt_studentID(lbl_studentID.getText());
        
        returnBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

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
            java.util.logging.Logger.getLogger(SearchIDIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchIDIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchIDIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchIDIssue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchIDIssue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxSearch;
    private javax.swing.JComboBox<String> boxSort;
    private org.jdesktop.core.animation.timing.evaluators.EvaluatorLong evaluatorLong1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_bookID;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_dueDate;
    private javax.swing.JLabel lbl_issueDate;
    private javax.swing.JLabel lbl_issueID;
    private javax.swing.JLabel lbl_studentID;
    private javax.swing.JLabel lbl_studentName;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojerusan.RSButtonHover rSButtonHover4;
    private rojeru_san.complementos.RSTableMetro tbl_issueRecordDetails;
    private app.bolivia.swing.JCTextField txt_infor;
    // End of variables declaration//GEN-END:variables
}
