/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exec;
import com.Pasien;
import com.Pembayaran;
import db.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Pakpahan
 */
public class ExecutePembayaran {
    public List<Pembayaran> getAllPembayaran(){
        String query = "select * from pembayaran";
        ConnectionManager conMan = new ConnectionManager();
        List<Pembayaran> lsPembayaran = new ArrayList<Pembayaran>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pembayaran pem = new Pembayaran();
                pem.setId_bayar(rs.getInt("Id_Pembayaran"));
                pem.setId_lapor(rs.getInt("Id_Layanan"));
                pem.setMetode(rs.getString("Metode"));
                pem.setTagihan(rs.getInt("Tagihan"));
                pem.setAngsur(rs.getInt("Jumlah_Bayar"));
                pem.setHutang(rs.getInt("Hutang"));
                pem.setNama(rs.getString("Nama_Wali"));
                pem.setNo_hp(rs.getString("No_Hp"));
                lsPembayaran.add(pem);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecutePembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsPembayaran;
    }
    public List<Pembayaran> getOnePembayaran(String id_bayar){
        String query = "select * from pembayaran where Id_Pembayaran ="+id_bayar+"";;
        ConnectionManager conMan = new ConnectionManager();
        List<Pembayaran> lsPembayaran = new ArrayList<Pembayaran>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pembayaran pem = new Pembayaran();
                pem.setId_bayar(rs.getInt("Id_Pembayaran"));
                pem.setId_lapor(rs.getInt("Id_Layanan"));
                pem.setMetode(rs.getString("Metode"));
                pem.setTagihan(rs.getInt("Tagihan"));
                pem.setAngsur(rs.getInt("Jumlah_Bayar"));
                pem.setHutang(rs.getInt("Hutang"));
                pem.setNama(rs.getString("Nama_Wali"));
                pem.setNo_hp(rs.getString("No_Hp"));
                lsPembayaran.add(pem);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecutePembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsPembayaran;
    }
    public int insertData(Pembayaran pem){
        int hasil = 0;
        String query ="insert into pembayaran(Id_Pembayaran, Id_Layanan, Metode, Tagihan, Jumlah_Bayar,Hutang,Nama_Wali,No_Hp)values"
 +"("+pem.getId_bayar()+","+pem.getId_lapor()+",'"+pem.getMetode()
 +"',"+pem.getTagihan()+","+pem.getAngsur()+","+pem.getHutang()+",'"+pem.getNama()+"','"+pem.getNo_hp()+"')";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int hapusData(String id_bayar){
        String query = "Delete from pembayaran where Id_Pembayaran="+id_bayar+"";
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int ubahData(Pembayaran pem){
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        String query = "Update pembayaran set Nama_Wali='"+pem.getNama()+"', Id_Layanan="+pem.getId_lapor()+
 ", Metode='"+pem.getMetode()+"',Tagihan="+pem.getTagihan()+",Jumlah_Bayar="+pem.getAngsur()+",Hutang="+pem.getHutang()+",No_Hp='"+pem.getNo_hp()+"' WHERE Id_Pembayaran="+pem.getId_bayar()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
