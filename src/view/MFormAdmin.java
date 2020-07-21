package view;
import com.ConvertDokterToObject;
import com.ConvertPasienToObject;
import com.ConvertAdminToObject;
import com.ConvertSuratToObject;
import com.Pasien;
import com.Dokter;
import com.Admin;
import com.ConvertLaporanToObject;
import com.ConvertPembayaranToObject;
import com.Laporan;
import com.Pembayaran;
import db.ConnectionManager;
import exec.ExecuteAdmin;
import exec.ExecutePasien;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.print.PrinterException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable.PrintMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;
import exec.PrintSupport;
import java.util.Date;
/**
 *
 * @author Ivan Pakpahan
 */
public class MFormAdmin extends javax.swing.JFrame {
    /**
     * Creates new form MainFormRS
     */
    String TGL;
    private int tglM,blnM,thnM,tglK;
    private Date tglpinjam;
    private Date tglhrskmbli;
    private Date tglkmbli;
    private String tanggalHrsKembali;
    private String tanggalKembali;
    private int iBulan;
    private String Sbulan;
    private String Kbulan;
    private int inBulan;
    private int BPJS;
    public MFormAdmin() {
        initComponents();
        setDataPasien();
        setDataDokter();
        setDataAdmin();
        setDataSurat();
        setDataLaporan();
        setDataSurat2();
        setDataLaporan2();
        setDataPembayaran();
        
    }
    public MFormAdmin(String msg,String pwd){
        initComponents();
        List<Admin> admin = new ArrayList<Admin>();
        ExecuteAdmin edmin = new ExecuteAdmin();
        admin = edmin.getProfilAdmin(msg, pwd);
        String[][] dataAdmin = new String[admin.size()][8];
        int i=0;
        for(Admin min : admin){
            dataAdmin[i][0] = ""+min.getId_Admin();
            jLabel33.setText(""+min.getId_Admin());
            dataAdmin[i][1] = min.getNama();
            jLabel45.setText(min.getNama());
            dataAdmin[i][2]= min.getUsername();
            dataAdmin[i][3]= min.getPassword();
            dataAdmin[i][4] = min.getLevel();
            dataAdmin[i][5]= min.getNo_Hp();
            dataAdmin[i][6]= min.getEmail();
            dataAdmin[i][7]= min.getAlamat();
            i++;   
        }
        setDataPasien();
        setDataDokter();
        setDataAdmin();
        setDataSurat();
        setDataLaporan();
        setDataSurat2();
        setDataLaporan2();
        setDataPembayaran();
    }
    private void setDataPasien(){
        ConvertPasienToObject cptb = new ConvertPasienToObject();
        String[][] dataPasien = cptb.getPasien();
        tblPasien.setModel(new javax.swing.table.DefaultTableModel(
                dataPasien,
                new String [] {"ID", "Nama", "Alamat","Nomor Hp","Status","Ruang"})
        );
    }
    private void setDataPasien1(){
        ConvertPasienToObject cptb = new ConvertPasienToObject();
        String id_pasien = txtCariPasien.getText();
        String[][] dataPasien = cptb.getOnePasien(id_pasien);
        tblPasien.setModel(new javax.swing.table.DefaultTableModel(dataPasien,new String [] {"ID", "Nama", "Alamat","Nomor Hp","Status","Ruang"})
        );
    }
    
    private void setDataDokter(){
        ConvertDokterToObject cdtb = new ConvertDokterToObject();
        String[][] dataDokter = cdtb.getDokter();
        tblDokter.setModel(new javax.swing.table.DefaultTableModel(
                dataDokter,
                new String [] {"ID", "Nama", "Spesialis","No Hp", "Alamat","Username","Password","Level"})
        );
    }
    private void setDataDokter1(){
        ConvertDokterToObject cdtb = new ConvertDokterToObject();
        String id_dokter = txtCariDokter.getText();
        String[][] dataDokter = cdtb.getOneDokter(id_dokter);
        tblDokter.setModel(new javax.swing.table.DefaultTableModel(
                dataDokter,
                new String [] {"ID", "Nama", "Spesialis","No Hp", "Alamat","Username","Password","Level"})
        );
    }
    private void setDataAdmin(){
        ConvertAdminToObject catb = new ConvertAdminToObject();
        String[][] dataAdmin = catb.getAdmin();
        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
                dataAdmin,
                new String [] {"ID", "Nama", "Username", "Password","Level","Nomor Hp","Email","Alamat"})
        );
    }
    private void setDataAdmin1(){
        ConvertAdminToObject catb = new ConvertAdminToObject();
        String id_admin = txtCariAdmin.getText();
        String[][] dataAdmin = catb.getOneAdmin(id_admin);
        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
                dataAdmin,
                new String [] {"ID", "Nama", "Username", "Password","Level","Nomor Hp","Email","Alamat"})
        );
    }
    private void setDataSurat(){
        ConvertSuratToObject cstb = new ConvertSuratToObject();
        String[][] dataSurat = cstb.getSurat();
        tblSurat.setModel(new javax.swing.table.DefaultTableModel(
                dataSurat,
                new String [] {"ID", "Keterangan", "ID Dokter", "ID Pasien","Nama Pasien"})
        );
    }
    private void setDataLaporan(){
        ConvertLaporanToObject cltb = new ConvertLaporanToObject();
        String[][] dataLaporan = cltb.getLaporan();
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                dataLaporan,
                new String [] {"ID", "ID Pasien", "ID Admin", "ID Surat","Tanggal"})
        );
    }
    private void setDataLaporan1(){
        ConvertLaporanToObject cltb = new ConvertLaporanToObject();
        String id_lap = jTextField2.getText();
        String[][] dataLaporan = cltb.getOneLaporan(id_lap);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                dataLaporan,
                new String [] {"ID", "ID Pasien", "ID Admin", "ID Surat","Tanggal"})
        );
    }
    private void setDataLaporan2(){
        ConvertLaporanToObject cltb = new ConvertLaporanToObject();
        String[][] dataLaporan = cltb.getLaporan();
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                dataLaporan,
                new String [] {"ID", "ID Pasien", "ID Admin", "ID Surat","Tanggal"})
        );
    }
    private void setDataLaporan3(){
        ConvertLaporanToObject cltb = new ConvertLaporanToObject();
        String[][] dataLaporan = cltb.getOneLaporan(jTextField4.getText());
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                dataLaporan,
                new String [] {"ID", "ID Pasien", "ID Admin", "ID Surat","Tanggal"})
        );
    }
    private void setDataSurat1(){
        ConvertSuratToObject cstb = new ConvertSuratToObject();
        String id_surat = txtCariAdmin1.getText();
        String[][] dataSurat = cstb.getOneSurat(id_surat);
        tblSurat.setModel(new javax.swing.table.DefaultTableModel(
                dataSurat,
                new String [] {"ID", "Keterangan", "ID Dokter", "ID Pasien","Nama Pasien"})
        );
    }
    private void setDataSurat2(){
        ConvertSuratToObject cstb = new ConvertSuratToObject();
        String[][] dataSurat = cstb.getSurat();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                dataSurat,
                new String [] {"ID", "Keterangan", "ID Dokter", "ID Pasien","Nama Pasien"})
        );
    }
    private void setDataSurat3(){
        ConvertSuratToObject cstb = new ConvertSuratToObject();
        String id_surat = jTextField1.getText();
        String[][] dataSurat = cstb.getOneSurat(id_surat);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                dataSurat,
                new String [] {"ID", "Keterangan", "ID Dokter", "ID Pasien","Nama Pasien"})
        );
    }
    private void setDataPembayaran(){
        ConvertPembayaranToObject cpetb = new ConvertPembayaranToObject();
        String[][] dataPembayaran = cpetb.getPembayaran();
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                dataPembayaran,
                new String [] {"ID", "ID Laporan", "Metode","Tagihan(RP)","Angsur(RP)","Hutang(RP)","Penanggung Jawab","Nomor Hp"})
        );
    }
    private void setDataPembayaran1(){
        ConvertPembayaranToObject cpetb = new ConvertPembayaranToObject();
        String[][] dataPembayaran = cpetb.getOnePembayaran(jTextField3.getText());
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
                dataPembayaran,
                new String [] {"ID", "ID Laporan", "Metode","Tagihan(RP)","Angsur(RP)","Hutang(RP)","Penanggung Jawab","Nomor Hp"})
        );
    }
     public void tampilReport(String kode){
        java.sql.Connection con = null;
        try {
          String JdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(JdbcDriver);
            String URL = "Jdbc:mysql://localhost:3306/administrasi";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(URL,user,pass);
            Statement stm = con.createStatement();
        try {
          String path="E:\\ITENAS\\Portofolio\\Aplikasi\\AdministrasiDataPasienIGD\\src\\view\\Laporan.jasper";
          Map parameter = new HashMap();
          parameter.put("ID_LAPORAN", kode);
          JasperPrint print = JasperFillManager.fillReport(path,parameter, con);
          JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
        }
          con.close();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, e);
        }
        }
     public void tampilSurat(String kode){
        java.sql.Connection con = null;
        try {
          String JdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(JdbcDriver);
            String URL = "Jdbc:mysql://localhost:3306/administrasi";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(URL,user,pass);
            Statement stm = con.createStatement();
        try {
          String path="E:\\ITENAS\\Portofolio\\Aplikasi\\AdministrasiDataPasienIGD\\src\\view\\Surat.jasper";
          Map parameter = new HashMap();
          parameter.put("ID_SURAT", kode);
          JasperPrint print = JasperFillManager.fillReport(path,parameter, con);
          JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada "+ex);
        }
          con.close();
        } catch (Exception e) {
          JOptionPane.showMessageDialog(rootPane, e);
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

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        pnlUtama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlLaporan = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        pnlPasien = new javax.swing.JPanel();
        pnlInputPasien = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNama_Pasien = new javax.swing.JTextField();
        txtNo_HpPasien = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamatPasien = new javax.swing.JTextArea();
        btnSimpan = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblId_Pasien = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ComboStatus = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        TxtNoRuangan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        pnlTblPasien = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPasien = new javax.swing.JTable();
        txtCariPasien = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        pnlDokter = new javax.swing.JPanel();
        pnlInputDokter = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblId_dokter = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNama_Dokter = new javax.swing.JTextField();
        txtSpesialis_Dokter = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAlamat_Dokter = new javax.swing.JTextArea();
        simpanDok = new javax.swing.JButton();
        UpdateDok = new javax.swing.JButton();
        HapusDok = new javax.swing.JButton();
        ResetDok = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtUsernameDok = new javax.swing.JTextField();
        txtPasswordDok = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNoHpDok = new javax.swing.JTextField();
        lvlDokter = new javax.swing.JLabel();
        pnlTblDokter = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDokter = new javax.swing.JTable();
        txtCariDokter = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        pnlAdmin = new javax.swing.JPanel();
        pnlInputAdmin = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        lblId_Akun = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtNamaAkun = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAlamatAkun = new javax.swing.JTextArea();
        simpanDok1 = new javax.swing.JButton();
        UpdateDok1 = new javax.swing.JButton();
        HapusDok1 = new javax.swing.JButton();
        ResetDok1 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtNomorHpAkun = new javax.swing.JTextField();
        lblLevel = new javax.swing.JLabel();
        txtEmailAdmin = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lblIdAdmin = new javax.swing.JLabel();
        pnlTblAdmin = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        txtCariAdmin = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pnlSurat = new javax.swing.JPanel();
        pnlTampilSurat = new javax.swing.JPanel();
        lblLevel1 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblSurat = new javax.swing.JTable();
        txtCariAdmin1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        pnlPembayaran = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel45 = new javax.swing.JLabel();
        jButton29 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField9 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMIN");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(new javax.swing.border.MatteBorder(null));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setFont(new java.awt.Font("Tw Cen MT", 0, 12)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pasien.png"))); // NOI18N
        jButton1.setText("Data Pasien");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Dokter.png"))); // NOI18N
        jButton2.setText("Data Dokter");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Admin.png"))); // NOI18N
        jButton3.setText("Data Admin");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ST.png"))); // NOI18N
        jButton9.setText("Surat Tindakan");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Layanan.png"))); // NOI18N
        jButton10.setText("Laporan");
        jButton10.setFocusable(false);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton10);

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Pembayaran.png"))); // NOI18N
        jButton11.setText("Pembayaran");
        jButton11.setFocusable(false);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Garamond", 1, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/power.png"))); // NOI18N
        jButton6.setText("Keluar");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        pnlUtama.setBackground(new java.awt.Color(255, 255, 255));
        pnlUtama.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Garamond", 1, 24)); // NOI18N
        jLabel1.setText("Selamat datang Admin ...");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        pnlUtama.add(jLabel1, "card2");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Data Surat");

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField1.setText("-");

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setText("Cari");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Id Surat");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Id Admin (anda)");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel30.setText("Id Pasien");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("Tanggal");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel32.setText("jLabel32");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel33.setText("jLabel33");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setText("jLabel34");

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable2);

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton16.setText("Simpan");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton17.setText("Hapus");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton18.setText("Reset");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton19.setText("Cetak");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField2.setText("-");

        jButton20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton20.setText("Cari");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel35.setText("jLabel35");

        javax.swing.GroupLayout pnlLaporanLayout = new javax.swing.GroupLayout(pnlLaporan);
        pnlLaporan.setLayout(pnlLaporanLayout);
        pnlLaporanLayout.setHorizontalGroup(
            pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLaporanLayout.createSequentialGroup()
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addGap(75, 75, 75)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addGroup(pnlLaporanLayout.createSequentialGroup()
                                .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlLaporanLayout.createSequentialGroup()
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanLayout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton8))
                                .addGroup(pnlLaporanLayout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addGap(81, 81, 81)
                                    .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
                    .addGroup(pnlLaporanLayout.createSequentialGroup()
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton20)))
                .addContainerGap())
            .addGroup(pnlLaporanLayout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlLaporanLayout.setVerticalGroup(
            pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLaporanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLaporanLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton19)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton20))
                        .addGap(15, 15, 15))
                    .addGroup(pnlLaporanLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel32))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlLaporanLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(27, 27, 27)
                                .addComponent(jButton16)
                                .addGap(4, 4, 4)
                                .addComponent(jButton17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pnlUtama.add(pnlLaporan, "cardLaporan");

        pnlPasien.setBackground(new java.awt.Color(255, 255, 255));

        pnlInputPasien.setBackground(new java.awt.Color(255, 255, 255));
        pnlInputPasien.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nama");
        pnlInputPasien.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nomor Hp");
        pnlInputPasien.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Alamat");
        pnlInputPasien.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Status");
        pnlInputPasien.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtNama_Pasien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlInputPasien.add(txtNama_Pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 220, -1));

        txtNo_HpPasien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlInputPasien.add(txtNo_HpPasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 220, -1));

        txtAlamatPasien.setColumns(20);
        txtAlamatPasien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAlamatPasien.setRows(5);
        jScrollPane1.setViewportView(txtAlamatPasien);

        pnlInputPasien.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 220, 90));

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        pnlInputPasien.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 100, 30));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnUpdate.setText("Ubah");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        pnlInputPasien.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("ID");
        pnlInputPasien.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblId_Pasien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId_Pasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlInputPasien.add(lblId_Pasien, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 220, 20));

        btnHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        pnlInputPasien.add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 80, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        pnlInputPasien.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 100, 30));

        ComboStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ComboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Merah", "Kuning", "Hijau", "Putih", "Hitam" }));
        ComboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboStatusActionPerformed(evt);
            }
        });
        pnlInputPasien.add(ComboStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("No Ruang");
        pnlInputPasien.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        TxtNoRuangan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlInputPasien.add(TxtNoRuangan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 220, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unnamed.png"))); // NOI18N
        pnlInputPasien.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 310));

        pnlTblPasien.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblPasien.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tblPasien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPasienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblPasien);

        txtCariPasien.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton14.setText("Cari");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Tahoma", 3, 8)); // NOI18N
        jLabel24.setText("cari berdasarkan id pasien");

        javax.swing.GroupLayout pnlTblPasienLayout = new javax.swing.GroupLayout(pnlTblPasien);
        pnlTblPasien.setLayout(pnlTblPasienLayout);
        pnlTblPasienLayout.setHorizontalGroup(
            pnlTblPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblPasienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTblPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                    .addGroup(pnlTblPasienLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlTblPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(pnlTblPasienLayout.createSequentialGroup()
                                .addComponent(txtCariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(29, 29, 29))
        );
        pnlTblPasienLayout.setVerticalGroup(
            pnlTblPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblPasienLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(pnlTblPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(txtCariPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlPasienLayout = new javax.swing.GroupLayout(pnlPasien);
        pnlPasien.setLayout(pnlPasienLayout);
        pnlPasienLayout.setHorizontalGroup(
            pnlPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasienLayout.createSequentialGroup()
                .addComponent(pnlInputPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTblPasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPasienLayout.setVerticalGroup(
            pnlPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasienLayout.createSequentialGroup()
                .addGroup(pnlPasienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPasienLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(pnlInputPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPasienLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(pnlTblPasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pnlUtama.add(pnlPasien, "cardPasien");

        pnlDokter.setBackground(new java.awt.Color(255, 255, 255));

        pnlInputDokter.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("ID");

        lblId_dokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId_dokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Nama");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Spesialis");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Alamat");

        txtNama_Dokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtSpesialis_Dokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAlamat_Dokter.setColumns(20);
        txtAlamat_Dokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAlamat_Dokter.setRows(5);
        jScrollPane3.setViewportView(txtAlamat_Dokter);

        simpanDok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpanDok.setText("Simpan");
        simpanDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanDokActionPerformed(evt);
            }
        });

        UpdateDok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        UpdateDok.setText("Update");
        UpdateDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDokActionPerformed(evt);
            }
        });

        HapusDok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        HapusDok.setText("Hapus");
        HapusDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusDokActionPerformed(evt);
            }
        });

        ResetDok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResetDok.setText("Reset");
        ResetDok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetDokActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Username");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("Password");

        txtUsernameDok.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtPasswordDok.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Nomor Hp");

        txtNoHpDok.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lvlDokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lvlDokter.setText("Dokter");

        javax.swing.GroupLayout pnlInputDokterLayout = new javax.swing.GroupLayout(pnlInputDokter);
        pnlInputDokter.setLayout(pnlInputDokterLayout);
        pnlInputDokterLayout.setHorizontalGroup(
            pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputDokterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInputDokterLayout.createSequentialGroup()
                        .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel10)
                            .addComponent(jLabel19))
                        .addGap(27, 27, 27)
                        .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsernameDok, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNama_Dokter)
                            .addGroup(pnlInputDokterLayout.createSequentialGroup()
                                .addComponent(lblId_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lvlDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3)))
                    .addGroup(pnlInputDokterLayout.createSequentialGroup()
                        .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel11)
                            .addComponent(jLabel20))
                        .addGap(25, 25, 25)
                        .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPasswordDok)
                            .addComponent(txtSpesialis_Dokter, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNoHpDok)))
                    .addGroup(pnlInputDokterLayout.createSequentialGroup()
                        .addComponent(simpanDok)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UpdateDok, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(HapusDok, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ResetDok, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlInputDokterLayout.setVerticalGroup(
            pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputDokterLayout.createSequentialGroup()
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInputDokterLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputDokterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lvlDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblId_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama_Dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsernameDok, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordDok, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoHpDok, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSpesialis_Dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(pnlInputDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanDok)
                    .addComponent(UpdateDok)
                    .addComponent(HapusDok)
                    .addComponent(ResetDok))
                .addGap(87, 87, 87))
        );

        pnlTblDokter.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane4MouseClicked(evt);
            }
        });

        tblDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDokter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDokter.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDokterMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblDokter);

        txtCariDokter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton12.setText("Cari");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton5.setText("Cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 8)); // NOI18N
        jLabel13.setText("Cari id dokter");

        javax.swing.GroupLayout pnlTblDokterLayout = new javax.swing.GroupLayout(pnlTblDokter);
        pnlTblDokter.setLayout(pnlTblDokterLayout);
        pnlTblDokterLayout.setHorizontalGroup(
            pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDokterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDokterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDokterLayout.createSequentialGroup()
                                .addComponent(txtCariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTblDokterLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)))
        );
        pnlTblDokterLayout.setVerticalGroup(
            pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblDokterLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(txtCariDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTblDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDokterLayout = new javax.swing.GroupLayout(pnlDokter);
        pnlDokter.setLayout(pnlDokterLayout);
        pnlDokterLayout.setHorizontalGroup(
            pnlDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDokterLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlInputDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTblDokter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDokterLayout.setVerticalGroup(
            pnlDokterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInputDokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlTblDokter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlUtama.add(pnlDokter, "cardDokter");

        pnlAdmin.setBackground(new java.awt.Color(255, 255, 255));

        pnlInputAdmin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setText("ID");

        lblId_Akun.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblId_Akun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Nama");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel25.setText("Alamat");

        txtNamaAkun.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAlamatAkun.setColumns(20);
        txtAlamatAkun.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAlamatAkun.setRows(5);
        jScrollPane7.setViewportView(txtAlamatAkun);

        simpanDok1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        simpanDok1.setText("Simpan");
        simpanDok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanDok1ActionPerformed(evt);
            }
        });

        UpdateDok1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        UpdateDok1.setText("Update");
        UpdateDok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateDok1ActionPerformed(evt);
            }
        });

        HapusDok1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        HapusDok1.setText("Hapus");
        HapusDok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HapusDok1ActionPerformed(evt);
            }
        });

        ResetDok1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ResetDok1.setText("Reset");
        ResetDok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetDok1ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Username");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel27.setText("Password");

        txtUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtPassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setText("Nomor Hp");

        txtNomorHpAkun.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lblLevel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblLevel.setText("Admin");

        txtEmailAdmin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel29.setText("Email");

        lblIdAdmin.setFont(new java.awt.Font("Times New Roman", 1, 1)); // NOI18N
        lblIdAdmin.setText("Id_Anda");
        lblIdAdmin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlInputAdminLayout = new javax.swing.GroupLayout(pnlInputAdmin);
        pnlInputAdmin.setLayout(pnlInputAdminLayout);
        pnlInputAdminLayout.setHorizontalGroup(
            pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInputAdminLayout.createSequentialGroup()
                        .addComponent(lblIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlInputAdminLayout.createSequentialGroup()
                        .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel25)
                            .addComponent(jLabel23)
                            .addComponent(jLabel26))
                        .addGap(27, 27, 27)
                        .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNamaAkun)
                            .addGroup(pnlInputAdminLayout.createSequentialGroup()
                                .addComponent(lblId_Akun, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)))
                    .addGroup(pnlInputAdminLayout.createSequentialGroup()
                        .addComponent(simpanDok1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UpdateDok1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HapusDok1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ResetDok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlInputAdminLayout.createSequentialGroup()
                        .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addGap(25, 25, 25)
                        .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmailAdmin)
                            .addComponent(txtPassword)
                            .addComponent(txtNomorHpAkun))))
                .addContainerGap())
        );
        pnlInputAdminLayout.setVerticalGroup(
            pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInputAdminLayout.createSequentialGroup()
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInputAdminLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel22)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInputAdminLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblId_Akun, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(lblLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomorHpAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInputAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanDok1)
                    .addComponent(UpdateDok1)
                    .addComponent(HapusDok1)
                    .addComponent(ResetDok1))
                .addGap(60, 60, 60)
                .addComponent(lblIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlTblAdmin.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane8MouseClicked(evt);
            }
        });

        tblAdmin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblAdmin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblAdmin);

        txtCariAdmin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton13.setText("Cari");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 8)); // NOI18N
        jLabel14.setText("cari id admin");

        javax.swing.GroupLayout pnlTblAdminLayout = new javax.swing.GroupLayout(pnlTblAdmin);
        pnlTblAdmin.setLayout(pnlTblAdminLayout);
        pnlTblAdminLayout.setHorizontalGroup(
            pnlTblAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblAdminLayout.createSequentialGroup()
                .addGroup(pnlTblAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addGroup(pnlTblAdminLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlTblAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(pnlTblAdminLayout.createSequentialGroup()
                                .addComponent(txtCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(6, 6, 6))
        );
        pnlTblAdminLayout.setVerticalGroup(
            pnlTblAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblAdminLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTblAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(txtCariAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAdminLayout = new javax.swing.GroupLayout(pnlAdmin);
        pnlAdmin.setLayout(pnlAdminLayout);
        pnlAdminLayout.setHorizontalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAdminLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlInputAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTblAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlAdminLayout.setVerticalGroup(
            pnlAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlInputAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlTblAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlUtama.add(pnlAdmin, "cardAkun");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/unnamed.png"))); // NOI18N
        pnlUtama.add(jLabel8, "card6");

        pnlSurat.setBackground(new java.awt.Color(255, 255, 255));
        pnlSurat.setName("cardSurat"); // NOI18N

        pnlTampilSurat.setBackground(new java.awt.Color(255, 255, 255));

        lblLevel1.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblLevel1.setText("Surat Rujukan");

        jScrollPane10.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane10MouseClicked(evt);
            }
        });

        tblSurat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblSurat.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblSurat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSuratMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblSurat);

        javax.swing.GroupLayout pnlTampilSuratLayout = new javax.swing.GroupLayout(pnlTampilSurat);
        pnlTampilSurat.setLayout(pnlTampilSuratLayout);
        pnlTampilSuratLayout.setHorizontalGroup(
            pnlTampilSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTampilSuratLayout.createSequentialGroup()
                .addGroup(pnlTampilSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLevel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlTampilSuratLayout.setVerticalGroup(
            pnlTampilSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTampilSuratLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCariAdmin1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton15.setText("Cari");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton28.setText("Cetak");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSuratLayout = new javax.swing.GroupLayout(pnlSurat);
        pnlSurat.setLayout(pnlSuratLayout);
        pnlSuratLayout.setHorizontalGroup(
            pnlSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSuratLayout.createSequentialGroup()
                        .addComponent(txtCariAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlTampilSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSuratLayout.createSequentialGroup()
                        .addComponent(jButton28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(470, 470, 470))
        );
        pnlSuratLayout.setVerticalGroup(
            pnlSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuratLayout.createSequentialGroup()
                .addComponent(pnlTampilSurat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCariAdmin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSuratLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton28))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pnlUtama.add(pnlSurat, "cardSurat");

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel36.setText("Data Laporan");

        jTable3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable3);

        jButton21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton21.setText("Cari");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jTable4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jTable4);

        jButton22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton22.setText("Cetak");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField3.setText("-");

        jButton23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton23.setText("Cari");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField4.setText("-");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("ID Laporan");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setText("Metode");

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setText("Tagihan");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setText("Angsuran");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setText("Nama");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setText("Nomor Hp");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setText("jLabel44");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "Non Tunai" }));

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField5.setText("0");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField6.setText("0");
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField7.setText("-");

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField8.setText("-");

        jButton24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton24.setText("Simpan");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton25.setText("Hapus");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton26.setText("Reset");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton27.setText("Ubah");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 3)); // NOI18N
        jLabel41.setText("jLabel41");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane12.setViewportView(jTextArea1);

        jLabel45.setText("jLabel45");

        jButton29.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton29.setText("Reset");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("BPJS");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setText("Nomor BPJS");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kelas I", "Tidak" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextField9.setText("0");
        jTextField9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPembayaranLayout = new javax.swing.GroupLayout(pnlPembayaran);
        pnlPembayaran.setLayout(pnlPembayaranLayout);
        pnlPembayaranLayout.setHorizontalGroup(
            pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPembayaranLayout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPembayaranLayout.createSequentialGroup()
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabel36)
                            .addComponent(jLabel46)
                            .addComponent(jLabel47))
                        .addGap(19, 19, 19)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5)
                            .addComponent(jTextField6)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8)
                            .addComponent(jComboBox1, 0, 183, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(16, 16, 16)
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlPembayaranLayout.createSequentialGroup()
                            .addComponent(jLabel41)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlPembayaranLayout.createSequentialGroup()
                            .addComponent(jButton22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton29))
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                        .addComponent(jScrollPane12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPembayaranLayout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23)))
                .addContainerGap())
        );
        pnlPembayaranLayout.setVerticalGroup(
            pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPembayaranLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel41)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPembayaranLayout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton21)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPembayaranLayout.createSequentialGroup()
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel38)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton25))
                                    .addComponent(jLabel40)))
                            .addGroup(pnlPembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(9, 9, 9)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton24))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton27)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(jButton26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPembayaranLayout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(18, 18, 18)
                                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel47)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPembayaranLayout.createSequentialGroup()
                        .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton29))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlUtama.add(pnlPembayaran, "cardPembayaran");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUtama, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardPasien");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardDokter");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardAkun");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblPasienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPasienMouseClicked
        // TODO add your handling code here:
        int row = tblPasien.getSelectedRow();
        String id_pasien = tblPasien.getValueAt(row, 0).toString();
        String nama = tblPasien.getValueAt(row, 1).toString();
        String no_hp = tblPasien.getValueAt(row, 3).toString();
        String alamat = tblPasien.getValueAt(row, 2).toString();
        String status = tblPasien.getValueAt(row, 4).toString();
        String no_ruang = tblPasien.getValueAt(row, 5).toString();
        lblId_Pasien.setText(id_pasien);
        txtNama_Pasien.setText(nama);
        txtNo_HpPasien.setText(no_hp);
        txtAlamatPasien.setText(alamat);
        TxtNoRuangan.setText(no_ruang);
    }//GEN-LAST:event_tblPasienMouseClicked

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        //String id = lblId_Pasien.getText();
        //int id_pasien = Integer.parseInt(id);
        String nama = txtNama_Pasien.getText();
        String no_hp = txtNo_HpPasien.getText();
        String alamat = txtAlamatPasien.getText();
        String status = ComboStatus.getSelectedItem().toString();//
        String no_ruang = TxtNoRuangan.getText();
        Pasien pas = new Pasien(0,nama, no_hp, alamat,status,no_ruang);
        exec.ExecutePasien ePas = new exec.ExecutePasien();
        int hasil = ePas.insertData(pas);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            setDataPasien();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String id_pasien = lblId_Pasien.getText();
        exec.ExecutePasien ePas = new exec.ExecutePasien();
        int hasil = ePas.hapusData(id_pasien);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus"); 
            setDataPasien();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di hapus, karena berelasi");
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String id = lblId_Pasien.getText();
        int id_pasien = Integer.parseInt(id);
        String nama = txtNama_Pasien.getText();
        String alamat = txtAlamatPasien.getText();
        String status = ComboStatus.getSelectedItem().toString();
        String no_hp = txtNo_HpPasien.getText();
        String no_ruang = TxtNoRuangan.getText();
        Pasien pas = new Pasien(id_pasien,nama, no_hp, alamat,status,no_ruang);
        exec.ExecutePasien ePas = new exec.ExecutePasien();
        int hasil = ePas.ubahData(pas);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            setDataPasien();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        hapus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tblDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDokterMouseClicked
        // TODO add your handling code here:
        int row = tblDokter.getSelectedRow();
        String id_dok = tblDokter.getValueAt(row, 0).toString();
        String nama = tblDokter.getValueAt(row, 1).toString();
        String spesialis = tblDokter.getValueAt(row, 2).toString();
        String no_hp = tblDokter.getValueAt(row, 3).toString();
        String alamat = tblDokter.getValueAt(row, 4).toString();
        String uname = tblDokter.getValueAt(row, 5).toString();
        String pass = tblDokter.getValueAt(row, 6).toString();
        String level = tblDokter.getValueAt(row, 7).toString();
        lblId_dokter.setText(id_dok);
        txtNama_Dokter.setText(nama);
        txtSpesialis_Dokter.setText(spesialis);
        txtAlamat_Dokter.setText(alamat);
        txtUsernameDok.setText(uname);
        txtPasswordDok.setText(pass);
        lvlDokter.setText(level);
    }//GEN-LAST:event_tblDokterMouseClicked

    private void jScrollPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane4MouseClicked

    private void simpanDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanDokActionPerformed
        // TODO add your handling code here:
        String nama = txtNama_Dokter.getText();
        String spesialis = txtSpesialis_Dokter.getText();
        String alamat = txtAlamat_Dokter.getText();
        String username = txtUsernameDok.getText();
        String pass = txtPasswordDok.getText();
        String nohp = txtNoHpDok.getText();
        Dokter dok = new Dokter(0,nama, spesialis,nohp, alamat,username,pass,"Dokter");
        exec.ExecuteDokter eDok = new exec.ExecuteDokter();
        int hasil = eDok.insertData(dok);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            setDataDokter();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
        }
    }//GEN-LAST:event_simpanDokActionPerformed

    private void UpdateDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDokActionPerformed
        // TODO add your handling code here:
        String id = lblId_dokter.getText();
        int id_dok = Integer.parseInt(id);
        String nama = txtNama_Dokter.getText();
        String alamat = txtAlamat_Dokter.getText();
        String spesialis = txtSpesialis_Dokter.getText();
        String username = txtUsernameDok.getText();
        String pass = txtPasswordDok.getText();
        String nohp = txtNoHpDok.getText();
        Dokter dok = new Dokter(id_dok,nama, spesialis,nohp, alamat,username,pass,"Dokter");
        exec.ExecuteDokter eDok = new exec.ExecuteDokter();
        int hasil = eDok.ubahData(dok);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            setDataDokter();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_UpdateDokActionPerformed

    private void HapusDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusDokActionPerformed
        // TODO add your handling code here:
        String id_dok = lblId_dokter.getText();
        exec.ExecuteDokter eDok = new exec.ExecuteDokter();
        int hasil = eDok.hapusData(id_dok);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus"); 
            setDataDokter();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di hapus");
        }
    }//GEN-LAST:event_HapusDokActionPerformed

    private void ResetDokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetDokActionPerformed
        // TODO add your handling code here:
        hapus2();
        setDataDokter();
    }//GEN-LAST:event_ResetDokActionPerformed

    private void ComboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboStatusActionPerformed

    private void simpanDok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanDok1ActionPerformed
        // TODO add your handling code here:
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String level = "Admin";
        String nama = txtNamaAkun.getText();
        String no_hp = txtNomorHpAkun.getText();
        String email = txtEmailAdmin.getText();
        String alamat = txtAlamatAkun.getText();
        Admin admin = new Admin(0,nama,username,password,level,no_hp,email,alamat);
        exec.ExecuteAdmin eDmin = new exec.ExecuteAdmin();
        int hasil = eDmin.insertData(admin);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            setDataAdmin();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
        }
    }//GEN-LAST:event_simpanDok1ActionPerformed

    private void UpdateDok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateDok1ActionPerformed
        // TODO add your handling code here:
        String id = lblId_Akun.getText();
        int id_akun = Integer.parseInt(id);
        String username = txtUsername.getText();
        String pass = txtPassword.getText();
        String nama = txtNamaAkun.getText();
        String no_hp = txtNomorHpAkun.getText();
        String alamat = txtAlamatAkun.getText();
        String email = txtEmailAdmin.getText();
        Admin admin = new Admin(id_akun,nama,username,pass,"Admin",no_hp,email, alamat);
        exec.ExecuteAdmin eDmin = new exec.ExecuteAdmin();
        int hasil = eDmin.ubahData(admin);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            setDataAdmin();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_UpdateDok1ActionPerformed

    private void HapusDok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HapusDok1ActionPerformed
        // TODO add your handling code here:
        String id_admin = lblId_Akun.getText();
        exec.ExecuteAdmin eDmin = new exec.ExecuteAdmin();
        int hasil = eDmin.hapusData(id_admin);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus"); 
            setDataAdmin();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di hapus");
        }
    }//GEN-LAST:event_HapusDok1ActionPerformed

    private void ResetDok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetDok1ActionPerformed
        // TODO add your handling code here:
        hapus3();
        setDataAdmin();
    }//GEN-LAST:event_ResetDok1ActionPerformed

    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked
        // TODO add your handling code here:
        int row = tblAdmin.getSelectedRow();
        String id_Admin = tblAdmin.getValueAt(row, 0).toString();
        String nama = tblAdmin.getValueAt(row, 1).toString();
        String username = tblAdmin.getValueAt(row, 2).toString();
        String password = tblAdmin.getValueAt(row, 3).toString();
        String level = tblAdmin.getValueAt(row, 4).toString();
        String no_hp = tblAdmin.getValueAt(row, 5).toString();
        String email = tblAdmin.getValueAt(row, 6).toString();
        String alamat = tblAdmin.getValueAt(row, 7).toString();
        lblId_Akun.setText(id_Admin);
        txtNamaAkun.setText(nama);
        txtPassword.setText(password);
        txtEmailAdmin.setText(email);
        txtPassword.setText(password);
        txtNomorHpAkun.setText(no_hp);
        txtUsername.setText(username);
        txtAlamatAkun.setText(alamat);
    }//GEN-LAST:event_tblAdminMouseClicked

    private void jScrollPane8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane8MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        setDataPasien1();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        setDataAdmin1();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        setDataDokter1();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void tblSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSuratMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSuratMouseClicked

    private void jScrollPane10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane10MouseClicked

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
       setDataSurat1();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardSurat");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            tblDokter.print(PrintMode.FIT_WIDTH, new MessageFormat("DATA DOKTER"),null);
        } catch (PrinterException ex) {
            Logger.getLogger(MFormAdmin.class.getName()).log(Level.SEVERE,null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FormLogin fl = new FormLogin();
        fl.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        setDataSurat();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
        setDataLaporan1();
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        setDataSurat3();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        String id_surat = jTable1.getValueAt(row, 0).toString();
        String id_pas = jTable1.getValueAt(row, 3).toString();
        jLabel32.setText(id_surat);
        jLabel34.setText(id_pas);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        if (jDateChooser1.getDate()!=null){
            SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");
            String TGL = fo.format(jDateChooser1.getDate());
        }
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat formatter= new SimpleDateFormat("dd MM yyyy");
        Date date = new Date(System.currentTimeMillis());
        String TanggalSekarang = (formatter.format(date));
        int id_admin = Integer.parseInt(jLabel33.getText());
        int id_surat = Integer.parseInt(jLabel32.getText());
        int id_pasien = Integer.parseInt(jLabel34.getText());
        if (jDateChooser1.getDate()!=null){
            SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");
             TGL = fo.format(jDateChooser1.getDate());
        }
        jLabel35.setText(TGL);
        int tahunH = Integer.parseInt(TanggalSekarang.substring(6, 10));
        int hariH = Integer.parseInt(TanggalSekarang.substring(0, 2));
        Sbulan = (String)TanggalSekarang.substring(3, 5);
        if (Sbulan.compareTo("01") == 0) {
        iBulan = 1;
        } else if (Sbulan.compareTo("02") == 0) {
        iBulan = 2;
        } else if (Sbulan.compareTo("03") == 0) {
        iBulan = 3;
        } else if (Sbulan.compareTo("04") == 0) {
        iBulan = 4;
        } else if (Sbulan.compareTo("05") == 0) {
        iBulan = 5;
        } else if (Sbulan.compareTo("06") == 0) {
        iBulan = 6;
        } else if (Sbulan.compareTo("07") == 0) {
        iBulan = 7;
        } else if (Sbulan.compareTo("08") == 0) {
        iBulan = 8;
        } else if (Sbulan.compareTo("09") == 0) {
        iBulan = 9;
        } else if (Sbulan.compareTo("10") == 0) {
        iBulan = 10;
        } else if (Sbulan.compareTo("11") == 0) {
        iBulan = 11;
        } else {
        iBulan = 12;
        }
        int tahunK = Integer.parseInt(TGL.substring(6, 10));
        int hariK = Integer.parseInt(TGL.substring(0, 2));
        Kbulan = (String)TGL.substring(3, 5);
        if (Kbulan.compareTo("01") == 0) {
        inBulan = 1;
        } else if (Kbulan.compareTo("02") == 0) {
        inBulan = 2;
        } else if (Kbulan.compareTo("03") == 0) {
        inBulan = 3;
        } else if (Kbulan.compareTo("04") == 0) {
        inBulan = 4;
        } else if (Kbulan.compareTo("05") == 0) {
        inBulan = 5;
        } else if (Kbulan.compareTo("06") == 0) {
        inBulan = 6;
        } else if (Kbulan.compareTo("07") == 0) {
        inBulan = 7;
        } else if (Kbulan.compareTo("08") == 0) {
        inBulan = 8;
        } else if (Kbulan.compareTo("09") == 0) {
        inBulan = 9;
        } else if (Kbulan.compareTo("10") == 0) {
        inBulan = 10;
        } else if (Kbulan.compareTo("11") == 0) {
        inBulan = 11;
        } else {
        inBulan = 12;
        }
        int hasilHari = hariK-hariH;
        int hasilBulan = (inBulan-iBulan) * 30;
        int hasiltahun = (tahunK-tahunH) * 365;
        int totalwaktu = (hasilHari + hasilBulan + hasiltahun);
        if (totalwaktu<0){
            JOptionPane.showMessageDialog(null, "Anda tidak bisa membuat laporan di masa lalu");
        }else{
            if (totalwaktu>0){
            JOptionPane.showMessageDialog(null, "Anda tidak bisa membuat laporan di masa depan");
        }else{
        Laporan lapor = new Laporan(0,id_pasien, id_admin, id_surat,jLabel35.getText());
        exec.ExecuteLaporan ePas = new exec.ExecuteLaporan();
        int hasil = ePas.insertData(lapor);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            setDataLaporan();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
        }
        }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        setDataLaporan();
        setDataSurat2();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        String id_surat = jTable2.getValueAt(row, 3).toString();
        String id_pas = jTable2.getValueAt(row, 1).toString();
        jLabel32.setText(id_surat);
        jLabel34.setText(id_pas);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed

        tampilReport(jTextField2.getText());
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardLaporan");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        String id_laporan = jTable2.getValueAt(row, 0).toString();
        exec.ExecuteLaporan eLap = new exec.ExecuteLaporan();
        int hasil = eLap.hapusData(id_laporan);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus"); 
            setDataLaporan();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di hapus, karena berelasi");
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
        setDataLaporan3();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        int row = jTable3.getSelectedRow();
        String id_Lapor = jTable2.getValueAt(row, 0).toString();
        jLabel44.setText(id_Lapor);
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:
        setDataPembayaran();
        setDataLaporan2();
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        if (jComboBox2.getSelectedItem()=="Kelas I"){
            jTextField9.setEnabled(true);
            BPJS = 20000;
        }else if (jComboBox2.getSelectedItem()=="Tidak"){
            jTextField9.setText("");
            jTextField9.setEnabled(false);
            BPJS = 0;
        }
        int id_lapor = Integer.parseInt(jLabel44.getText());
        String metode = jComboBox1.getSelectedItem().toString();
        int tagihan = Integer.parseInt(jTextField5.getText());
        int angsur = Integer.parseInt(jTextField6.getText());
        int Hutang = tagihan - angsur - BPJS;
        if (Hutang<0){
            JOptionPane.showMessageDialog(null, "Mohon maaf nilai tidak boleh bernilai negatif, bulatkan nilai angsuran");
        }else{
        String nama = jTextField7.getText();//
        String no_hp = jTextField8.getText();
        int a = Integer.parseInt(jTextField5.getText());
        int b = Integer.parseInt(jTextField6.getText());
        if (b<0){
            JOptionPane.showMessageDialog(null, "Angsuran tidak boleh bernilai negatif");
        }else{
        if (a<0){
            JOptionPane.showMessageDialog(null, "Tagihan tidak boleh bernilai negatif");
        }else{
        if (b>a){
            JOptionPane.showMessageDialog(null, "Angsuran tidak boleh lebih besar dari tagihan");
        }else{
        Pembayaran pam = new Pembayaran(0,id_lapor, metode, tagihan,angsur,Hutang,nama,no_hp);
        exec.ExecutePembayaran ePem = new exec.ExecutePembayaran();
        int hasil = ePem.insertData(pam);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di simpan");
            setDataPembayaran();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di simpan");
        }
       }
      }
     }
    }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        int id_bayar = Integer.parseInt(jLabel41.getText());
        int id_lapor = Integer.parseInt(jLabel44.getText());
        String metode = jComboBox1.getSelectedItem().toString();
        int Tagihan = Integer.parseInt(jTextField5.getText());
        int Angsur = Integer.parseInt(jTextField6.getText());
        int Hutang = Tagihan - Angsur;
        String nama = jTextField7.getText();
        String no_hp = jTextField8.getText();
        Pembayaran bayar = new Pembayaran(id_bayar,id_lapor,metode,Tagihan,Angsur,Hutang,nama,no_hp);
        exec.ExecutePembayaran ePem = new exec.ExecutePembayaran();
        int hasil = ePem.ubahData(bayar);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
            setDataPembayaran();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal diubah");
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        int row = jTable4.getSelectedRow();
        String id_bayar = jTable4.getValueAt(row, 0).toString();
        exec.ExecutePembayaran ePem = new exec.ExecutePembayaran();
        int hasil = ePem.hapusData(id_bayar);
        if(hasil >0){
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus"); 
            setDataPembayaran();
        }else{
            JOptionPane.showMessageDialog(null, "Data gagal di hapus, Data sudah berelasi");
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        int row = jTable4.getSelectedRow();
        String id_bayar = jTable4.getValueAt(row, 0).toString();
        String id_lapor = jTable4.getValueAt(row, 1).toString();
        String metode = jTable4.getValueAt(row, 2).toString();
        String tagihan = jTable4.getValueAt(row, 3).toString();
        String angsur = jTable4.getValueAt(row, 4).toString();
        String nama = jTable4.getValueAt(row, 6).toString();
        String no_hp = jTable4.getValueAt(row, 7).toString();
        String hutang = jTable4.getValueAt(row, 5).toString();
        jLabel41.setText(id_bayar);
        jLabel44.setText(id_lapor);
        jTextField5.setText(tagihan);
        jTextField6.setText(angsur);
        jTextField7.setText(nama);
        jTextField8.setText(no_hp);
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy ',' HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        String Tanggal = (formatter.format(date));
        jTextArea1.append("___________________________________NOTA PEMBAYARAN__________________________________\n");
        jTextArea1.append("___________________________LAYANAN INSTALASI GAWAT DARURAT__________________________\n");
        jTextArea1.append("------------------------------------------------------------------------------------\n");
        jTextArea1.append("NOMOR PEMBAYARAN         : "+id_bayar+"\n");
        jTextArea1.append("NOMOR LAPORAN            : "+id_lapor+"\n");
        jTextArea1.append("TANGGAL/JAM              : "+Tanggal+"\n");
        jTextArea1.append("NAMA                     : Tn/Ny "+nama+"\n");
        jTextArea1.append("KONTAK                   : "+no_hp+"\n");
        jTextArea1.append("METODE                   : "+metode+"\n");
        jTextArea1.append("TAGIHAN                  : Rp "+tagihan+",00\n");
        jTextArea1.append("BPJS KESEHATAN           : -\n");
        jTextArea1.append("TOTAL ANGSURAN           : Rp "+angsur+",00\n");
        jTextArea1.append("HUTANG                   : Rp "+hutang+",00\n");
        String putus;
        int hutang1 = Integer.parseInt(hutang);
        if (hutang1 == 0){
          putus = "LUNAS";
          jTextArea1.append("TOTAL                    : "+putus+"\n");
        }else{
          putus = ""+hutang1;
          jTextArea1.append("TOTAL                    : Rp "+putus+",00\n");
        }
        jTextArea1.append("------------------------------------------------------------------------------------\n");
        //String putus = 
        
        jTextArea1.append("\n");
        jTextArea1.append("\n");
        jTextArea1.append("------------------------------------------------------------------------------------\n");
        jTextArea1.append("Admin\n");
        jTextArea1.append("\n");
        jTextArea1.append("\n");
        jTextArea1.append(""+jLabel45.getText()+"\n");
        jTextArea1.setEditable(false);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        PrintSupport.printComponent(jTextArea1);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        cl = (CardLayout) pnlUtama.getLayout();
        cl.show(pnlUtama, "cardPembayaran");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        setDataPembayaran1();
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
        tampilSurat(txtCariAdmin1.getText());
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        jTextArea1.setText("");
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        if (jComboBox2.getSelectedIndex()==0){
            jTextField9.setEnabled(true);
            BPJS = 20000;
        }else if (jComboBox2.getSelectedIndex()==1){
            jTextField9.setText("");
            jTextField9.setEnabled(false);
            BPJS = 0;
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextField9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField9ActionPerformed
        // TODO add your handling code here:
        jTextField9.setEnabled(false);
    }//GEN-LAST:event_jTextField9ActionPerformed
    private void hapus(){
        txtNama_Pasien.setText("");
        txtAlamatPasien.setText("");
        txtNo_HpPasien.setText("");
        lblId_Pasien.setText("");
    }
    private void hapus2(){
        txtNama_Dokter.setText("");
        txtAlamat_Dokter.setText("");
        txtSpesialis_Dokter.setText("");
        lblId_dokter.setText("0");
    }
    private void hapus3(){
        txtNamaAkun.setText("");
        txtAlamatAkun.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtAlamatAkun.setText("");
        txtNomorHpAkun.setText("");
        txtEmailAdmin.setText("");
        lblLevel.setText("Admin");
        lblId_Akun.setText("0");
    }
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
                if ("Windows Classic".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MFormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MFormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MFormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MFormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MFormAdmin().setVisible(true);
            }
        });
    }
    private CardLayout cl;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboStatus;
    private javax.swing.JButton HapusDok;
    private javax.swing.JButton HapusDok1;
    private javax.swing.JButton ResetDok;
    private javax.swing.JButton ResetDok1;
    private javax.swing.JTextField TxtNoRuangan;
    private javax.swing.JButton UpdateDok;
    private javax.swing.JButton UpdateDok1;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblIdAdmin;
    private javax.swing.JLabel lblId_Akun;
    private javax.swing.JLabel lblId_Pasien;
    private javax.swing.JLabel lblId_dokter;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblLevel1;
    private javax.swing.JLabel lvlDokter;
    private javax.swing.JPanel pnlAdmin;
    private javax.swing.JPanel pnlDokter;
    private javax.swing.JPanel pnlInputAdmin;
    private javax.swing.JPanel pnlInputDokter;
    private javax.swing.JPanel pnlInputPasien;
    private javax.swing.JPanel pnlLaporan;
    private javax.swing.JPanel pnlPasien;
    private javax.swing.JPanel pnlPembayaran;
    private javax.swing.JPanel pnlSurat;
    private javax.swing.JPanel pnlTampilSurat;
    private javax.swing.JPanel pnlTblAdmin;
    private javax.swing.JPanel pnlTblDokter;
    private javax.swing.JPanel pnlTblPasien;
    private javax.swing.JPanel pnlUtama;
    private javax.swing.JButton simpanDok;
    private javax.swing.JButton simpanDok1;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTable tblDokter;
    private javax.swing.JTable tblPasien;
    private javax.swing.JTable tblSurat;
    private javax.swing.JTextArea txtAlamatAkun;
    private javax.swing.JTextArea txtAlamatPasien;
    private javax.swing.JTextArea txtAlamat_Dokter;
    private javax.swing.JTextField txtCariAdmin;
    private javax.swing.JTextField txtCariAdmin1;
    private javax.swing.JTextField txtCariDokter;
    private javax.swing.JTextField txtCariPasien;
    private javax.swing.JTextField txtEmailAdmin;
    private javax.swing.JTextField txtNamaAkun;
    private javax.swing.JTextField txtNama_Dokter;
    private javax.swing.JTextField txtNama_Pasien;
    private javax.swing.JTextField txtNoHpDok;
    private javax.swing.JTextField txtNo_HpPasien;
    private javax.swing.JTextField txtNomorHpAkun;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordDok;
    private javax.swing.JTextField txtSpesialis_Dokter;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsernameDok;
    // End of variables declaration//GEN-END:variables
}
