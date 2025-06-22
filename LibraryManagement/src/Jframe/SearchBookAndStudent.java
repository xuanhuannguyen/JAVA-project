/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.sql.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admin
 */
public class SearchBookAndStudent extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooKs
     */
    String bookName,author;
    int bookID,quantity;
    String studentName,email,studentID,studentClass;
    DefaultTableModel model;
    public SearchBookAndStudent() {
        initComponents();
        setBookDetailsToTable();
        setStudentDetailsToTable();
    }
    // to set the book details to table
    public void setStudentDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from student_details");
            
            model =(DefaultTableModel) tbl_studentDetails.getModel();
            model.setRowCount(0);
             while (rs.next()){
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String email = rs.getString("email");
                   String studentClass = rs.getString("class");
                   
                   Object[] obj = {studentID,studentName,email,studentClass};
                   model.addRow(obj);
        
                }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setBookDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from book_details");
            
            model =(DefaultTableModel) tbl_bookDetails.getModel();
            model.setRowCount(0);
             while (rs.next()){
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        
                }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchByBookID() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String keySearch = txt_infor.getText();
            ResultSet rs=st.executeQuery("select * from book_details where book_id = '"+keySearch+"'");
            if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
            model =(DefaultTableModel) tbl_bookDetails.getModel();
            model.setRowCount(0);
             do{
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        
                }  while (rs.next());          
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void searchByBookName() {
    // Lấy tên cần tìm từ text field
    String nameToSearch = txt_infor.getText();

    // Câu lệnh SQL sử dụng LIKE và tham số '?'
    String sql = "SELECT * FROM book_details WHERE book_name LIKE ?";

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
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        
         do{
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        
                }  while (rs.next());  
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public void searchByAuthor() {
    // Lấy tên cần tìm từ text field
    String nameToSearch = txt_infor.getText();

    // Câu lệnh SQL sử dụng LIKE và tham số '?'
    String sql = "SELECT * FROM book_details WHERE author LIKE ?";

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
        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        
         do{
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        
                }  while (rs.next());  

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public void sortByBookID() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM book_details ORDER BY book_id ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void sortByBookName() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM book_details ORDER BY book_name ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void sortByAuthor() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM book_details ORDER BY author ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void sortByQuantity() {
    String nameToSearch = txt_infor.getText();

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM book_details ORDER BY quantity ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_bookDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
    // add new books
    public void searchByStudentID() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            String keySearch = txt_inforStudent.getText();
            ResultSet rs=st.executeQuery("select * from student_details where student_id = '"+keySearch+"'");
            if (!rs.next())
            {
                JOptionPane.showMessageDialog(this, "Not found!");
                return;
            }
            model =(DefaultTableModel) tbl_studentDetails.getModel();
            model.setRowCount(0);
             do{
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String email = rs.getString("email");
                   String studentClass = rs.getString("class");
                   
                   Object[] obj = {studentID,studentName,email,studentClass};
                   model.addRow(obj);
        
                }  while (rs.next());          
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void searchByStudentName() {
    // Lấy tên cần tìm từ text field
    String nameToSearch = txt_inforStudent.getText();

    // Câu lệnh SQL sử dụng LIKE và tham số '?'
    String sql = "SELECT * FROM student_details WHERE student_name LIKE ?";

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
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        
         do{
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String email = rs.getString("email");
                   String studentClass = rs.getString("class");
                   
                   Object[] obj = {studentID,studentName,email,studentClass};
                   model.addRow(obj);
        
                }  while (rs.next());  
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public void searchByStudentClass() {
    // Lấy tên cần tìm từ text field
    String nameToSearch = txt_inforStudent.getText();

    // Câu lệnh SQL sử dụng LIKE và tham số '?'
    String sql = "SELECT * FROM student_details WHERE class LIKE ?";

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
        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trên bảng
        
         do{
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String email = rs.getString("email");
                   String studentClass = rs.getString("class");
                   
                   Object[] obj = {studentID,studentName,email,studentClass};
                   model.addRow(obj);
        
                }  while (rs.next());  

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public void sortByStudentID() {

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM student_details ORDER BY student_id ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            Object[] obj = {
                rs.getString("student_id"),
                rs.getString("student_name"), // Cột đang được tìm kiếm
                rs.getString("email"),
                rs.getString("class"),
            };
            model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    public void sortByStudentName() {

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM student_details ORDER BY student_name ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
            Object[] obj = {
                rs.getString("student_id"),
                rs.getString("student_name"), // Cột đang được tìm kiếm
                rs.getString("email"),
                rs.getString("class"),
            };
            model.addRow(obj);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}
     public void sortByStudentClass() {

    // Thêm "ORDER BY student_name" vào cuối câu lệnh SQL
    String sql = "SELECT * FROM student_details ORDER BY class ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) tbl_studentDetails.getModel();
        model.setRowCount(0);

        while (rs.next()) {
           Object[] obj = {
                rs.getString("student_id"),
                rs.getString("student_name"), // Cột đang được tìm kiếm
                rs.getString("email"),
                rs.getString("class"),
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbl_bookID = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        rSButtonHover4 = new rojerusan.RSButtonHover();
        jLabel3 = new javax.swing.JLabel();
        boxSearch = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_infor = new app.bolivia.swing.JCTextField();
        rSButtonHover1 = new rojeru_san.complementos.RSButtonHover();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        boxSort = new javax.swing.JComboBox<>();
        rSButtonHover2 = new rojeru_san.complementos.RSButtonHover();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();
        boxSearchStudent = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_inforStudent = new app.bolivia.swing.JCTextField();
        boxSortStudent = new javax.swing.JComboBox<>();
        rSButtonHover3 = new rojeru_san.complementos.RSButtonHover();
        rSButtonHover6 = new rojeru_san.complementos.RSButtonHover();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel19 = new javax.swing.JLabel();
        lbl_studentID = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lbl_class = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 0, 0));
        jPanel4.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 260, 5));

        lbl_bookID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_bookID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 200, 20));

        jLabel16.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel16.setText("Book ID:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, -1, 20));

        jLabel18.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel18.setText("Book Name:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 620, -1, 20));

        lbl_bookName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 620, 200, 20));

        jLabel15.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel15.setText("Author:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, -1, -1));

        lbl_author.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 660, 200, 20));

        jLabel17.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel17.setText("Quantity:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 710, -1, -1));

        lbl_quantity.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 710, 200, 20));

        rSButtonHover4.setText("Select");
        rSButtonHover4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover4ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover4, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 740, 130, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Search By:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        boxSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book ID", "Book Name", "Author", " " }));
        boxSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSearchActionPerformed(evt);
            }
        });
        jPanel3.add(boxSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 260, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Enter information:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        txt_infor.setPlaceholder("Enter information...\n");
        txt_infor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inforActionPerformed(evt);
            }
        });
        jPanel3.add(txt_infor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 260, 30));

        rSButtonHover1.setText("Search");
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 120, 30));

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

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Sort By:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, -1, -1));

        boxSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Book ID", "Book Name", "Author", "Quantity", " ", " " }));
        jPanel3.add(boxSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 270, 30));

        rSButtonHover2.setText("Sort");
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 120, 30));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Book Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setRowHeight(35);
        tbl_bookDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 640, 270));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Book_50px_1.png"))); // NOI18N
        jLabel2.setText("BOOK LIST");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 240, -1));

        boxSearchStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Student Name", "Class", " " }));
        boxSearchStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSearchStudentActionPerformed(evt);
            }
        });
        jPanel3.add(boxSearchStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 260, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Search By:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Enter information:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, -1, -1));

        txt_inforStudent.setPlaceholder("Enter information...\n");
        txt_inforStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inforStudentActionPerformed(evt);
            }
        });
        jPanel3.add(txt_inforStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 260, 30));

        boxSortStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student ID", "Student Name", "Class", " ", " " }));
        jPanel3.add(boxSortStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 220, 270, 30));

        rSButtonHover3.setText("Search");
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 110, 120, 30));

        rSButtonHover6.setText("Sort");
        rSButtonHover6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover6ActionPerformed(evt);
            }
        });
        jPanel3.add(rSButtonHover6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 220, 120, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Sort By:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Student Name", "Email", "Class"
            }
        ));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setRowHeight(35);
        tbl_studentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_studentDetailsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_studentDetails);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 640, 270));

        jLabel19.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel19.setText("Student ID:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 580, -1, 20));

        lbl_studentID.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_studentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 580, 200, 20));

        jLabel20.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel20.setText("Student Name:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 620, -1, 20));

        lbl_studentName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel3.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 630, 200, 20));

        jLabel21.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel21.setText("Class:");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 720, -1, -1));

        jLabel22.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jLabel22.setText("Email:");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 670, -1, -1));

        lbl_email.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 670, 200, 20));

        lbl_class.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        jPanel3.add(lbl_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 720, 200, 20));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel9.setText("STUDENTS LIST");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 430, 90));

        jPanel5.setBackground(new java.awt.Color(255, 0, 0));
        jPanel5.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 70, 260, 5));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 800));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSButtonHover4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover4ActionPerformed
        // TODO add your handling code here:
        if (lbl_bookID.getText().equals("") || lbl_studentID.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Select book and student first!s");
            return;
        }
      
        IssueBooks issueBooks = new IssueBooks();
        issueBooks.setLbl_bookID(lbl_bookID.getText());
        issueBooks.setLbl_bookName(lbl_bookName.getText());
        issueBooks.setLbl_author(lbl_author.getText());
        issueBooks.setLbl_quantity(lbl_quantity.getText());
        issueBooks.setTxt_bookID(lbl_bookID.getText());
        issueBooks.setLbl_studentID(lbl_studentID.getText());
        issueBooks.setLbl_studentName(lbl_studentName.getText());
        issueBooks.setLbl_email(lbl_email.getText());
        issueBooks.setLbl_class(lbl_class.getText());
        issueBooks.setTxt_studentID(lbl_studentID.getText());
        issueBooks.setVisible(true);
        
        dispose();
    }//GEN-LAST:event_rSButtonHover4ActionPerformed

    private void boxSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSearchActionPerformed

    private void txt_inforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inforActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inforActionPerformed

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        // TODO add your handling code here:
        String searchTarget= (String)boxSearch.getSelectedItem();
        if (searchTarget.equals("Book ID"))
        {
            searchByBookID();
        }
        else if (searchTarget.equals("Book Name"))
        {
            searchByBookName();
        }
        else if (searchTarget.equals("Author")){
            searchByAuthor();
        }
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new IssueBooks().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        // TODO add your handling code here:
        String sortTarget = (String)boxSort.getSelectedItem();
        if (sortTarget.equals("Book Name"))
        {
            sortByBookName();
        }
        else if (sortTarget.equals("Book ID"))
        {
            sortByBookID();
        }
        else if (sortTarget.equals("Author"))
        {
            sortByAuthor();
        }
        else
        {
            sortByQuantity();
        }
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void tbl_bookDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookDetailsMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_bookDetails.getSelectedRow();
        TableModel model = tbl_bookDetails.getModel();
        lbl_bookID.setText(model.getValueAt(rowNo,0).toString());
        lbl_bookName.setText(model.getValueAt(rowNo,1).toString());
        lbl_author.setText(model.getValueAt(rowNo,2).toString());
        lbl_quantity.setText(model.getValueAt(rowNo,3).toString());
    }//GEN-LAST:event_tbl_bookDetailsMouseClicked

    private void boxSearchStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSearchStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSearchStudentActionPerformed

    private void txt_inforStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inforStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inforStudentActionPerformed

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        // TODO add your handling code here:
        String searchTarget= (String)boxSearchStudent.getSelectedItem();
        if (searchTarget.equals("Student ID"))
        {
            searchByStudentID();
        }
        else if (searchTarget.equals("Student Name"))
        {
            searchByStudentName();
        }
        else if (searchTarget.equals("Class")){
            searchByStudentClass();
        }
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void rSButtonHover6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover6ActionPerformed
        // TODO add your handling code here:
        String sortTarget = (String)boxSortStudent.getSelectedItem();
        if (sortTarget.equals("Student Name"))
        {
            sortByStudentName();
        }
        else if (sortTarget.equals("Student ID"))
        {
            sortByStudentID();
        }
        else
        {
            sortByStudentClass();
        }
    }//GEN-LAST:event_rSButtonHover6ActionPerformed

    private void tbl_studentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_studentDetailsMouseClicked
        // TODO add your handling code here:
        int rowNo = tbl_studentDetails.getSelectedRow();
        TableModel model = tbl_studentDetails.getModel();
        lbl_studentID.setText(model.getValueAt(rowNo,0).toString());
        lbl_studentName.setText(model.getValueAt(rowNo,1).toString());
        lbl_email.setText(model.getValueAt(rowNo,2).toString());
        lbl_class.setText(model.getValueAt(rowNo,3).toString());
    }//GEN-LAST:event_tbl_studentDetailsMouseClicked

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
            java.util.logging.Logger.getLogger(SearchBookAndStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchBookAndStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchBookAndStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchBookAndStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new SearchBookAndStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxSearch;
    private javax.swing.JComboBox<String> boxSearchStudent;
    private javax.swing.JComboBox<String> boxSort;
    private javax.swing.JComboBox<String> boxSortStudent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookID;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_class;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentID;
    private javax.swing.JLabel lbl_studentName;
    private rojeru_san.complementos.RSButtonHover rSButtonHover1;
    private rojeru_san.complementos.RSButtonHover rSButtonHover2;
    private rojeru_san.complementos.RSButtonHover rSButtonHover3;
    private rojerusan.RSButtonHover rSButtonHover4;
    private rojeru_san.complementos.RSButtonHover rSButtonHover6;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    private app.bolivia.swing.JCTextField txt_infor;
    private app.bolivia.swing.JCTextField txt_inforStudent;
    // End of variables declaration//GEN-END:variables
}
