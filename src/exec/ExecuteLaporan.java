package exec;
import com.Laporan;
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
public class ExecuteLaporan {
    public List<Laporan> getAllLaporan(){
        String query = "select * from laporan";
        ConnectionManager conMan = new ConnectionManager();
        List<Laporan> lsLaporan = new ArrayList<Laporan>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Laporan lapor = new Laporan();
                lapor.setId_laporan(rs.getInt("Id_Layanan"));
                lapor.setId_pas(rs.getInt("Id_Pasien"));
                lapor.setId_admin(rs.getInt("Id_Admin"));
                lapor.setId_surat(rs.getInt("Id_ST"));
                lapor.setTanggal(rs.getString("Tanggal"));
                lsLaporan.add(lapor);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsLaporan;
    }
}
