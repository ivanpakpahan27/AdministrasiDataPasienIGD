package exec;
import com.Pasien;
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
public class ExecutePasien {
    public List<Pasien> getAllPasien(){
        String query = "select * from pasien";
        ConnectionManager conMan = new ConnectionManager();
        List<Pasien> lsPasien = new ArrayList<Pasien>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pasien pas = new Pasien();
                pas.setId_pas(rs.getInt("Id_Pasien"));
                pas.setNama(rs.getString("Nama"));
                pas.setNo_hp(rs.getString("No_Hp"));
                pas.setAlamat(rs.getString("Alamat"));
                pas.setStatus(rs.getString("Status"));
                pas.setNo_ruang(rs.getString("No_Ruangan"));
                lsPasien.add(pas);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsPasien;
    }
    public List<Pasien> getOnePasien(String id_pasien){
        String query = "select * from pasien where Id_Pasien="+id_pasien+"";;
        ConnectionManager conMan = new ConnectionManager();
        List<Pasien> lsPasien = new ArrayList<Pasien>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Pasien pas = new Pasien();
                pas.setId_pas(rs.getInt("Id_Pasien"));
                pas.setNama(rs.getString("Nama"));
                pas.setNo_hp(rs.getString("No_Hp"));
                pas.setAlamat(rs.getString("Alamat"));
                pas.setStatus(rs.getString("Status"));
                pas.setNo_ruang(rs.getString("No_Ruangan"));
                lsPasien.add(pas);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsPasien;
    }
    public int insertData(Pasien pas){
        int hasil = 0;
        String query ="insert into pasien(Id_Pasien, Nama, No_Hp, Alamat, Status,No_Ruangan)values"
 +"("+pas.getId_pas()+",'"+pas.getNama()+"','"+pas.getNo_hp()
 +"','"+pas.getAlamat()+"','"+pas.getStatus()+"','"+pas.getNo_ruang()+"')";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int hapusData(String id_pas){
        String query = "Delete from pasien where Id_Pasien="+id_pas+"";
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int ubahData(Pasien pas){
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        String query = "Update pasien set Nama='"+pas.getNama()+"', No_Hp='"+pas.getNo_hp()+
 "', Alamat='"+pas.getAlamat()+"',Status='"+pas.getStatus()+"',No_Ruangan='"+pas.getNo_ruang()+"' where Id_Pasien="+pas.getId_pas()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
