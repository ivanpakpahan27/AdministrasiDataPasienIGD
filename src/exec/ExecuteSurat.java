package exec;

import com.Surat;
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
public class ExecuteSurat {
    public List<Surat> getAllSurat(){
        String query = "select * from surat_tindakan";
        ConnectionManager conMan = new ConnectionManager();
        List<Surat> lsSurat = new ArrayList<Surat>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Surat sr = new Surat();
                sr.setId_surat(rs.getInt("Id_ST"));
                sr.setKeterangan(rs.getString("Keterangan"));
                sr.setId_dok(rs.getInt("Id_Dokter"));
                sr.setId_pas(rs.getInt("Id_Pasien"));
                lsSurat.add(sr);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteSurat.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsSurat;}
}
