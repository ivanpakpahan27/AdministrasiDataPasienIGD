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
                dok.setId_dok(rs.getInt("id_dok"));
                dok.setNama(rs.getString("nama"));
                dok.setSpesialis(rs.getString("spesialis"));
                dok.setAlamat(rs.getString("alamat"));
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
        String query ="insert into dokter(id_dok, nama, spesialis, alamat)values"
 +"("+dok.getId_dok()+",'"+dok.getNama()+"','"+dok.getSpesialis()
 +"','"+dok.getAlamat()+"')";
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
        String query = "Delete from dokter where id_dok="+id_dok+"";
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
        String query = "Update dokter set nama='"+dok.getNama()+"', spesialis='"+dok.getSpesialis()+
 "', alamat='"+dok.getAlamat()+"' where id_dok="+dok.getId_dok()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteDokter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}