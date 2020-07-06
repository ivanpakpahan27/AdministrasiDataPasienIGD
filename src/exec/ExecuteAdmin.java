package exec;
import com.Pasien;
import com.Admin;
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
public class ExecuteAdmin {
    public List<Admin> getAllAdmin(){
        String query = "select * from admin";
        ConnectionManager conMan = new ConnectionManager();
        List<Admin> lsAdmin = new ArrayList<Admin>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Admin admin = new Admin();
                admin.setId_Admin(rs.getInt("Id_Admin"));
                admin.setNama(rs.getString("Nama"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setLevel(rs.getString("Level"));
                admin.setNo_Hp(rs.getString("No_Hp"));
                admin.setEmail(rs.getString("Email"));
                admin.setAlamat(rs.getString("Alamat"));
                lsAdmin.add(admin);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsAdmin;
    }
    public List<Admin> getOneAdmin(String id_admin){
        String query = "select * from admin WHERE Id_Admin="+id_admin+"";
        ConnectionManager conMan = new ConnectionManager();
        List<Admin> lsAdmin = new ArrayList<Admin>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Admin admin = new Admin();
                admin.setId_Admin(rs.getInt("Id_Admin"));
                admin.setNama(rs.getString("Nama"));
                admin.setUsername(rs.getString("Username"));
                admin.setPassword(rs.getString("Password"));
                admin.setLevel(rs.getString("Level"));
                admin.setNo_Hp(rs.getString("No_Hp"));
                admin.setEmail(rs.getString("Email"));
                admin.setAlamat(rs.getString("Alamat"));
                lsAdmin.add(admin);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsAdmin;
    }
    public int insertData(Admin admin){
        int hasil = 0;
        String query ="insert into admin(Id_Admin,Nama,Username,Password,Level,No_Hp,Email,Alamat)values"
 +"("+admin.getId_Admin()+",'"+admin.getNama()+"','"+admin.getUsername()
 +"','"+admin.getPassword()+"','"+admin.getLevel()+"','"+admin.getNo_Hp()+"','"+admin.getEmail()+"','"+admin.getAlamat()+"')";
        
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int hapusData(String Id_Admin){
        String query = "Delete from admin where Id_Admin="+Id_Admin+"";
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
    public int ubahData(Admin admin){
        int hasil = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        String query = "Update admin set Username='"+admin.getUsername()+"', Password='"+admin.getPassword()+
 "', Level='"+admin.getLevel()+"',Nama='"+admin.getNama()+"', No_Hp='"+admin.getNo_Hp()+"',Email='"+admin.getEmail()+"', Alamat='"+admin.getAlamat()+"' where Id_Admin="+admin.getId_Admin()+"";
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    public int masukData(String username,String pass){
        String query = "SELECT * from admin where Username='"+username+"'AND Password='"+pass+"'";
        int input = 0;
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            ResultSet ab = conn.createStatement().executeQuery(query);
            if(ab.next()){
                if(username.equals(ab.getString("Username")) && pass.equals(ab.getString("Password"))){
                    input = input + 1 ;
                }
            }else{
                input = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return input;
    }
}
