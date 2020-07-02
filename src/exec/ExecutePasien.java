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
                pas.setId_pas(rs.getInt("id_pas"));
                pas.setNama(rs.getString("nama"));
                pas.setNo_hp(rs.getString("no_hp"));
                pas.setAlamat(rs.getString("alamat"));
                pas.setStatus(rs.getString("status"));
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
        String query ="insert into pasien(id_pas, nama, no_hp, alamat, status)values"
 +"("+pas.getId_pas()+",'"+pas.getNama()+"','"+pas.getNo_hp()
 +"','"+pas.getAlamat()+"','"+pas.getStatus()+"')";
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
        String query = "Delete from pasien where id_pas="+id_pas+"";
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
        String query = "Update pasien set nama='"+pas.getNama()+"', no_hp='"+pas.getNo_hp()+
 "', alamat='"+pas.getAlamat()+"',status='"+pas.getStatus()+"' where id_pas="+pas.getId_pas()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecutePasien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
