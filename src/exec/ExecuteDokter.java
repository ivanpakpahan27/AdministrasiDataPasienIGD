package exec;
import com.Dokter;
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
public class ExecuteDokter {
    public List<Dokter> getAllDokter(){
        String query = "select * from dokter";
        ConnectionManager conMan = new ConnectionManager();
        List<Dokter> lsDokter = new ArrayList<Dokter>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Dokter dok = new Dokter();
                dok.setId_dok(rs.getInt("Id_Dokter"));
                dok.setNama(rs.getString("Nama"));
                dok.setSpesialis(rs.getString("Spesialis"));
                dok.setNo_hp(rs.getString("No_Hp"));
                dok.setAlamat(rs.getString("Alamat"));
                dok.setUsername(rs.getString("Username"));
                dok.setPassword(rs.getString("Password"));
                dok.setLevel(rs.getString("Level"));
                lsDokter.add(dok);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsDokter;
    }
    public List<Dokter> getOneDokter(String id_dokter){
        String query = "select * from dokter where Id_Dokter ="+id_dokter+"";;
        ConnectionManager conMan = new ConnectionManager();
        List<Dokter> lsDokter = new ArrayList<Dokter>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Dokter dok = new Dokter();
                dok.setId_dok(rs.getInt("Id_Dokter"));
                dok.setNama(rs.getString("Nama"));
                dok.setSpesialis(rs.getString("Spesialis"));
                dok.setNo_hp(rs.getString("No_Hp"));
                dok.setAlamat(rs.getString("Alamat"));
                dok.setUsername(rs.getString("Username"));
                dok.setPassword(rs.getString("Password"));
                dok.setLevel(rs.getString("Level"));
                lsDokter.add(dok);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsDokter;
    }
    public int insertData(Dokter dok){
        int hasil = 0;
        String query ="insert into dokter(Id_Dokter, Nama, Spesialis,No_Hp, Alamat,Username,Password,Level)values"
 +"("+dok.getId_dok()+",'"+dok.getNama()+"','"+dok.getSpesialis()+"','"+dok.getNo_hp()
 +"','"+dok.getAlamat()+"','"+dok.getUsername()+"','"+dok.getPassword()+"','"+dok.getLevel()+"')";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int hapusData(String id_dok){
        String query = "Delete from dokter where Id_Dokter="+id_dok+"";
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int ubahData(Dokter dok){
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        String query = "Update dokter set Nama='"+dok.getNama()+"', Spesialis='"+dok.getSpesialis()+"',No_Hp='"+dok.getNo_hp()+
 "', Alamat='"+dok.getAlamat()+"', Username='"+dok.getUsername()+"', Password='"+dok.getPassword()+"', Level='"+dok.getLevel()+"' where Id_Dokter="+dok.getId_dok()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}