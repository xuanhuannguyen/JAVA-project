/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import app.bolivia.swing.JCTextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class IssueBooks extends javax.swing.JFrame {

    /**
     * Creates new form IssueBooks
     */
    public IssueBooks() {
        initComponents();
    }
public void getBookDetails() {
    int bookID;
    try {
         bookID = Integer.parseInt(txt_bookID.getText());
         try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1,bookID);
            ResultSet rs = pst.executeQuery();
            if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "This ID isn't exist!");
                  lbl_bookID.setText("");
                  lbl_bookName.setText("");;
                  lbl_author.setText("");
                  lbl_quantity.setText("");
            }
            else
            {
           
                  lbl_bookID.setText(rs.getString("book_id"));
                  lbl_bookName.setText(rs.getString("book_name"));;
                  lbl_author.setText(rs.getString("author"));
                  lbl_quantity.setText(rs.getString("quantity"));
        
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Enter number for BookID!");
        txt_bookID.setText("");
    }
   
    
}
public void getStudentDetails() {
  String studentID = txt_studentID.getText();

         try {
            Connection con = DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement("select * from student_details where student_id = ?");
            pst.setString(1,studentID);
            ResultSet rs = pst.executeQuery();
            if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "This ID isn't exist!");
                  lbl_studentID.setText("");
                  lbl_studentName.setText("");;
                  lbl_email.setText("");
                  lbl_class.setText("");
            }
            else
            {
           
                  lbl_studentID.setText(rs.getString("student_id"));
                  lbl_studentName.setText(rs.getString("student_name"));;
                  lbl_email.setText(rs.getString("email"));
                  lbl_class.setText(rs.getString("class"));
        
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

   
    
}
public boolean issueBook() {
    boolean isIssued = false;
    int bookID = Integer.parseInt(lbl_bookID.getText());
    String studentID = lbl_studentID.getText();
    String bookName= lbl_bookName.getText();
    String studentName= lbl_studentName.getText();
    Date uIssueDate = date_issueDate.getDatoFecha();
    Date uDueDate = date_dueDate.getDatoFecha();
    
    if (uIssueDate == null || uDueDate == null) {
            JOptionPane.showMessageDialog(this, "Enter correct date!");
            return false;
       }
        long l1 =  uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        if (l1>l2)
        {
            JOptionPane.showMessageDialog(this, "Enter correct date!");
            return false;
        }
    java.sql.Date sIssueDate = new java.sql.Date(l1);
    java.sql.Date sDueDate = new java.sql.Date(l2);
    try {
        Connection con = DBConnection.getConnection();
        String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issue_date,due_date,status) value(?,?,?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, bookID);
        pst.setString(2, bookName);
        pst.setString(3, studentID);
        pst.setString(4, studentName);
        pst.setDate(5, sIssueDate);
        pst.setDate(6,sDueDate);
        pst.setString(7, "pending");
        int rowCount = pst.executeUpdate();
        if (rowCount>0) isIssued = true;
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    return isIssued;
    
}
public void updateBookQuantity() {
    int bookId = Integer.parseInt(txt_bookID.getText());
    try {
        Connection con = DBConnection.getConnection();
        String sql = "update book_details set quantity = quantity -1 where book_id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1,bookId);
        int rowCount = pst.executeUpdate();
        if (rowCount>0) {
            int initialCount = Integer.parseInt(lbl_quantity.getText())-1;
            lbl_quantity.setText(Integer.toString(initialCount));
        }
        
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public boolean isAlreadyIssued() {
    boolean isAlreadyIssued = false;
    int bookID = Integer.parseInt(lbl_bookID.getText());
    String studentID = lbl_studentID.getText();
    try {
        Connection con = DBConnection.getConnection();
        String sql = "select * from issue_book_details where student_id=? and book_id = ? and status = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,studentID);
        pst.setInt(2, bookID);
        pst.setString(3, "pending");
        ResultSet rs = pst.executeQuery();
        if (rs.next())
        {
            isAlreadyIssued = true;
        }
        
        
        
        
        } catch (Exception e) {
        e.printStackTrace();
    }
    return isAlreadyIssued;
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookID = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_class = new javax.swing.JLabel();
        lbl_studentID = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        txt_bookID = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_studentID = new app.bolivia.swing.JCTextField();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        btn_issueBook = new necesario.RSMaterialButtonCircle();
        rSButtonHover1 = new rojerusan.RSButtonHover();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 214, 98));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 83, 156));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel11.setText("BACK");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 30));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jLabel12.setFont(new java.awt.Font("Sitka Text", 1, 26)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel12.setText("Book Details");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 310, 110));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setForeground(new java.awt.Color(255, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 300, 3));

        jLabel13.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Quantity:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        jLabel14.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Book ID:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel15.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Book Name:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Author:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        lbl_quantity.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 200, 20));

        lbl_bookID.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_bookID.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 200, 20));

        lbl_bookName.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 200, 20));

        lbl_author.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 200, 20));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 800));

        jPanel3.setBackground(new java.awt.Color(0, 83, 156));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Sitka Text", 1, 26)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel7.setText("Student Details");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 310, 110));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));
        jPanel16.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 300, 3));

        jLabel8.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Class:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, -1, -1));

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Student ID:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel10.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Student Name:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Email:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, -1, -1));

        lbl_class.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_class.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 200, 20));

        lbl_studentID.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_studentID.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 200, 20));

        lbl_studentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 200, 20));

        lbl_email.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_email.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 200, 20));

        panel_main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 420, 800));

        jLabel18.setFont(new java.awt.Font("Sitka Text", 1, 26)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel18.setText("ISSUE BOOK");
        panel_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 80, 310, 110));

        jPanel17.setBackground(new java.awt.Color(255, 51, 0));
        jPanel17.setForeground(new java.awt.Color(255, 0, 0));
        jPanel17.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel_main.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, 300, 3));

        txt_bookID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        txt_bookID.setPlaceholder("Enter BookID...");
        txt_bookID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIDFocusLost(evt);
            }
        });
        txt_bookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIDActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 270, 30));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 0, 0));
        jLabel19.setText("Book ID:");
        panel_main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 280, -1, 20));

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 0, 0));
        jLabel20.setText("Issue Date:");
        panel_main.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 410, -1, 20));

        txt_studentID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        txt_studentID.setPlaceholder("Enter StudentID...");
        txt_studentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIDFocusLost(evt);
            }
        });
        txt_studentID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIDActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 330, 270, 30));

        date_dueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_dueDate.setColorButtonHover(new java.awt.Color(204, 204, 204));
        date_dueDate.setColorForeground(new java.awt.Color(140, 140, 140));
        date_dueDate.setPlaceholder("Select Due Date");
        panel_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 480, 310, 30));

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));
        jLabel21.setText("Due Date:");
        panel_main.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 480, -1, 20));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));
        jLabel22.setText("Student ID:");
        panel_main.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 340, -1, 20));

        date_issueDate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_issueDate.setColorButtonHover(new java.awt.Color(204, 204, 204));
        date_issueDate.setColorForeground(new java.awt.Color(140, 140, 140));
        date_issueDate.setPlaceholder("Select Issue Date");
        panel_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 410, 310, 30));

        btn_issueBook.setBackground(new java.awt.Color(255, 51, 51));
        btn_issueBook.setText("Issue Book");
        btn_issueBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_issueBookActionPerformed(evt);
            }
        });
        panel_main.add(btn_issueBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 550, 220, 60));

        rSButtonHover1.setText("Search");
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        panel_main.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 370, 80, 30));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void txt_bookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIDActionPerformed

    private void txt_studentIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIDActionPerformed

    private void btn_issueBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_issueBookActionPerformed
        // TODO add your handling code here
        if (lbl_bookID.getText().equals("") || lbl_studentID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Find Book or Student ID first!");
            return;
        }
        if (lbl_quantity.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this,"Book not available!");
            lbl_bookID.setText("");
            lbl_bookName.setText("");;
            lbl_author.setText("");
            lbl_quantity.setText("");
            txt_bookID.setText("");
            return;
        }
        if (!isAlreadyIssued())
        {
                    if (issueBook())
                {
                    JOptionPane.showMessageDialog(this,"Book issued successful!");
                    updateBookQuantity();
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Book issued fail");
                }
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Student already issue this book!");
            lbl_bookID.setText("");
            lbl_bookName.setText("");;
            lbl_author.setText("");
            lbl_quantity.setText("");
            txt_bookID.setText("");
        }
        
            
        
    }//GEN-LAST:event_btn_issueBookActionPerformed

    private void txt_bookIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIDFocusLost
        // TODO add your handling code here:
        if (!txt_bookID.getText().equals(""))
            getBookDetails();
    }//GEN-LAST:event_txt_bookIDFocusLost

    private void txt_studentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIDFocusLost
        // TODO add your handling code here:
        if (!txt_studentID.getText().equals(""))
            getStudentDetails();
    }//GEN-LAST:event_txt_studentIDFocusLost

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        new SearchBookAndStudent().setVisible(true);
        dispose();
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

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
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBooks().setVisible(true);
            }
        });
    }

    public void setLbl_author(String input) {
        this.lbl_author.setText(input);
    }

    public void setLbl_bookID(String input) {
        this.lbl_bookID.setText(input);
    }

    public void setLbl_bookName(String input) {
        this.lbl_bookName.setText(input);
    }

    public void setLbl_class(String input) {
        this.lbl_class.setText(input);
    }

    public void setLbl_email(String input) {
        this.lbl_email.setText(input);;
    }

    public void setLbl_quantity(String input) {
        this.lbl_quantity.setText(input);
    }

    public void setLbl_studentID(String input) {
        this.lbl_studentID.setText(input);
    }

    public void setLbl_studentName(String input) {
        this.lbl_studentName.setText(input);
    }

    public void setTxt_bookID(String input) {
        this.txt_bookID.setText(input);
    }

    public void setTxt_studentID(String input) {
        this.txt_studentID.setText(input);
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private necesario.RSMaterialButtonCircle btn_issueBook;
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookID;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_class;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentID;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSButtonHover rSButtonHover1;
    private app.bolivia.swing.JCTextField txt_bookID;
    private app.bolivia.swing.JCTextField txt_studentID;
    // End of variables declaration//GEN-END:variables
}
