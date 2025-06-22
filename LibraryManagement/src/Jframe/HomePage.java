/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class HomePage extends javax.swing.JFrame {

    /**
     * Creates new form HomePage
     */
    Color mouseEnterColor = new Color(0,0,0);
    Color mouseExitColor = new Color(51,51,51);
    private int studentsCount=0,booksCount=0;
    DefaultTableModel modelBook,modelStudent;
    public HomePage() {
        initComponents();
        showPieChart();
        setBookDetailsToTable();
        setStudentDetailsToTable();
        setDataToCards();
        
    }
  // to set the book details to table
    public void setBookDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from book_details");
            
            modelBook =(DefaultTableModel) tbl_bookDetails.getModel();
            modelBook.setRowCount(0);
             while (rs.next()){
                   booksCount++;
                   String bookID = rs.getString("book_id");
                   String bookName = rs.getString("book_name");
                   String author = rs.getString("author");
                   int quantity = rs.getInt("quantity");
                   
                   Object[] obj = {bookID,bookName,author,quantity};
                   modelBook.addRow(obj);
        
                }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // to set the book details to table
    public void setStudentDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("select * from student_details");
            
            modelStudent =(DefaultTableModel) tbl_studentDetails.getModel();
            modelStudent.setRowCount(0);
             while (rs.next()){
                   studentsCount++;
                   String studentID = rs.getString("student_id");
                   String studentName = rs.getString("student_name");
                   String email = rs.getString("email");
                   String studentClass = rs.getString("class");
                   
                   Object[] obj = {studentID,studentName,email,studentClass};
                   modelStudent.addRow(obj);
        
                }            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDataToCards() {
        lbl_noOfBooks.setText(Integer.toString(booksCount));
        lbl_noOfStudents.setText(Integer.toString(studentsCount));
        Statement st = null;
        ResultSet rs = null;
        long longTime = System.currentTimeMillis();
        java.sql.Date todays = new java.sql.Date(longTime);
        
        try {
            
            Connection con = DBConnection.getConnection();
            st = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=st.executeQuery("select * from issue_book_details where status = '"+"pending"+"'");
            rs.last();
            lbl_noOfIssued.setText(Integer.toString(rs.getRow()));            
            rs=st.executeQuery("select * from issue_book_details where due_date < '"+todays+"' and status = '"+"pending"+"' ");
            rs.last();
            lbl_noOfDefaulter.setText(Integer.toString(rs.getRow()));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
public void showPieChart() {
    // Create dataset
    DefaultPieDataset barDataset = new DefaultPieDataset();

    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT class, COUNT(*) AS class_count FROM student_details GROUP BY class";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            // Thêm dữ liệu vào dataset (không cần new Double)
            barDataset.setValue(rs.getString("class"), rs.getDouble("class_count"));
        }

        // Đóng tài nguyên sau khi dùng
        rs.close();
        st.close();
        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    // Create pie chart
    JFreeChart piechart = ChartFactory.createPieChart(
        "Class statistic",   // tiêu đề
        barDataset,          // dữ liệu
        true,                // legend
        true,                // tooltips
        true                 // URLs
    );

    // Lấy đối tượng PiePlot để tùy chỉnh
    PiePlot piePlot = (PiePlot) piechart.getPlot();

    // Đặt màu nền cho biểu đồ
    piePlot.setBackgroundPaint(Color.white);

    // Hiển thị tên lớp và số lượng sinh viên
    piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1}"));

    // Nếu bạn muốn hiển thị cả phần trăm:
    // piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

    // Create chart panel to display chart
    ChartPanel barChartPanel = new ChartPanel(piechart);
    panelPieChart.removeAll();
    panelPieChart.add(barChartPanel, BorderLayout.CENTER);
    panelPieChart.validate();
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel02 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel01 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel03 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel04 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel05 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel06 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel07 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lbl_noOfBooks = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lbl_noOfStudents = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lbl_noOfIssued = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        lbl_noOfDefaulter = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojeru_san.complementos.RSTableMetro();
        jLabel25 = new javax.swing.JLabel();
        panelPieChart = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 214, 98));
        jPanel1.setPreferredSize(new java.awt.Dimension(1842, 50));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/male_user_50px.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 20, 58, -1));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 6, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome, Admin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 30, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Library Management");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 22, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 17, 39, 35));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(0, 83, 156));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Home_26px_2.png"))); // NOI18N
        jLabel7.setText("    Home Page");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 210, 50));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Library_26px_1.png"))); // NOI18N
        jLabel8.setText("    LMS DashBoard");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 50));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Features");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jPanel02.setBackground(new java.awt.Color(51, 51, 51));
        jPanel02.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel9.setText("    Manage Students");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        jPanel02.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel02, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 210, 50));

        jPanel01.setBackground(new java.awt.Color(51, 51, 51));
        jPanel01.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Book_26px.png"))); // NOI18N
        jLabel11.setText("    Manage Books");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel11MouseExited(evt);
            }
        });
        jPanel01.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel01, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 210, 50));

        jPanel03.setBackground(new java.awt.Color(51, 51, 51));
        jPanel03.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Sell_26px.png"))); // NOI18N
        jLabel12.setText("    Issue Book");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel12MouseExited(evt);
            }
        });
        jPanel03.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel03, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 210, 50));

        jPanel04.setBackground(new java.awt.Color(51, 51, 51));
        jPanel04.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel13.setText("    Return Book");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel04.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel04, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 210, 50));

        jPanel05.setBackground(new java.awt.Color(51, 51, 51));
        jPanel05.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_View_Details_26px.png"))); // NOI18N
        jLabel14.setText("    View Records");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel14MouseExited(evt);
            }
        });
        jPanel05.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel05, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 210, 50));

        jPanel06.setBackground(new java.awt.Color(51, 51, 51));
        jPanel06.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Books_26px.png"))); // NOI18N
        jLabel15.setText("    View Issued Books");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel15MouseExited(evt);
            }
        });
        jPanel06.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel06, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 210, 50));

        jPanel07.setBackground(new java.awt.Color(51, 51, 51));
        jPanel07.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Conference_26px.png"))); // NOI18N
        jLabel16.setText("    Defaulter list");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        jPanel07.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel07, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 210, 50));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setForeground(new java.awt.Color(255, 255, 255));
        jPanel14.setPreferredSize(new java.awt.Dimension(2, 55));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 2));

        jPanel7.setBackground(new java.awt.Color(255, 214, 98));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Exit_26px_2.png"))); // NOI18N
        jLabel10.setText("    Logout");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 210, 50));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfBooks.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        lbl_noOfBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noOfBooks.setText("10");
        jPanel16.add(lbl_noOfBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jPanel15.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 158, 123));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Book Details");
        jPanel15.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("No of Students");
        jPanel15.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jPanel17.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfStudents.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        lbl_noOfStudents.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_People_50px.png"))); // NOI18N
        lbl_noOfStudents.setText("10");
        jPanel17.add(lbl_noOfStudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jPanel15.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 158, 123));

        jPanel18.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfIssued.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        lbl_noOfIssued.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfIssued.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_Sell_50px.png"))); // NOI18N
        lbl_noOfIssued.setText("10");
        jPanel18.add(lbl_noOfIssued, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jPanel15.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 158, 123));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Issued Books");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, -1, -1));

        jPanel19.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noOfDefaulter.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        lbl_noOfDefaulter.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noOfDefaulter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AdminIcon/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_noOfDefaulter.setText("10");
        jPanel19.add(lbl_noOfDefaulter, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jPanel15.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 60, 158, 123));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Defaulter List");
        jPanel15.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, -1, -1));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"DE190316", "Xuân Huấn", "xuanhuanlqd@gmail.com", "0833283840"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Book ID", "Book Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setRowHeight(35);
        jScrollPane1.setViewportView(tbl_bookDetails);

        jPanel15.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 760, 230));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("No of Books");
        jPanel15.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"DE190316", "Xuân Huấn", "xuanhuanlqd@gmail.com", "0833283840"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Student ID", "Name", "Email", "Phone"
            }
        ));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setRowHeight(35);
        jScrollPane2.setViewportView(tbl_studentDetails);

        jPanel15.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 760, 230));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Students Details");
        jPanel15.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        panelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel15.add(panelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, 430, 410));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 1233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        new ManageBooks().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseEntered
        // TODO add your handling code here:
        jPanel01.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel11MouseEntered

    private void jLabel11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseExited
        // TODO add your handling code here:
        jPanel01.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel11MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
        jPanel02.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        // TODO add your handling code here:
        jPanel02.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        
        ManageStudents main = new ManageStudents();
        main.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseEntered
        // TODO add your handling code here:
        jPanel03.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel12MouseEntered

    private void jLabel12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseExited
        // TODO add your handling code here:
        jPanel03.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel12MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        IssueBooks newPage = new IssueBooks();
        newPage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        new ViewAllRecord().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseEntered
        // TODO add your handling code here:
        jPanel05.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel14MouseEntered

    private void jLabel14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseExited
        // TODO add your handling code here:
        jPanel05.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel14MouseExited

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
            // TODO add your handling code here:
         new ReturnBooks().setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        // TODO add your handling code here:
        jPanel04.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        // TODO add your handling code here:
        jPanel04.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        new ViewIssuedRecord().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseEntered
        // TODO add your handling code here:
        jPanel06.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel15MouseEntered

    private void jLabel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseExited
        // TODO add your handling code here:
         jPanel06.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel15MouseExited

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        new DefaulterList().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        // TODO add your handling code here:
        jPanel07.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        // TODO add your handling code here:
        jPanel07.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel16MouseExited

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new LoginPage().setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

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
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel01;
    private javax.swing.JPanel jPanel02;
    private javax.swing.JPanel jPanel03;
    private javax.swing.JPanel jPanel04;
    private javax.swing.JPanel jPanel05;
    private javax.swing.JPanel jPanel06;
    private javax.swing.JPanel jPanel07;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_noOfBooks;
    private javax.swing.JLabel lbl_noOfDefaulter;
    private javax.swing.JLabel lbl_noOfIssued;
    private javax.swing.JLabel lbl_noOfStudents;
    private javax.swing.JPanel panelPieChart;
    private rojeru_san.complementos.RSTableMetro tbl_bookDetails;
    private rojeru_san.complementos.RSTableMetro tbl_studentDetails;
    // End of variables declaration//GEN-END:variables
}
