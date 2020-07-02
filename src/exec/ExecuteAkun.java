package exec;
import com.Pasien;
import com.Akun;
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
public class ExecuteAkun {
    public List<Akun> getAllAkun(){
        String query = "select * from akun";
        ConnectionManager conMan = new ConnectionManager();
        List<Akun> lsAkun = new ArrayList<Akun>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Akun akun = new Akun();
                akun.setId_akun(rs.getInt("id_akun"));
                akun.setUsername(rs.getString("username"));
                akun.setPass(rs.getString("pass"));
                akun.setLevel(rs.getString("level"));
                akun.setNama(rs.getString("nama"));
                akun.setNo_hp(rs.getString("no_hp"));
                akun.setAlamat(rs.getString("alamat"));
                lsAkun.add(akun);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsAkun;
    }
    public int insertData(Akun akun){
        int hasil = 0;
        String query ="insert into akun(id_akun, username, pass, level,nama,no_hp,alamat)values"
 +"("+akun.getId_akun()+",'"+akun.getUsername()+"','"+akun.getPass()
 +"','"+akun.getLevel()+"','"+akun.getNama()+"','"+akun.getNo_hp()+"','"+akun.getAlamat()+"')";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int hapusData(String id_akun){
        String query = "Delete from akun where id_akun="+id_akun+"";
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int ubahData(Akun akun){
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        String query = "Update akun set username='"+akun.getUsername()+"', pass='"+akun.getPass()+
 "', level='"+akun.getLevel()+"',nama='"+akun.getNama()+"', no_hp='"+akun.getNo_hp()+"',alamat='"+akun.getAlamat()+"' where id_akun="+akun.getId_akun()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAkun.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
}
