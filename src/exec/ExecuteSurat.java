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
        String query = "select surat_tindakan.Id_ST,surat_tindakan.Keterangan,"
                + "surat_tindakan.Id_Dokter,surat_tindakan.Id_Pasien,pasien.Nama "
                + "from surat_tindakan,pasien WHERE surat_tindakan.Id_Pasien=pasien.Id_Pasien";
        ConnectionManager conMan = new ConnectionManager();
        List<Surat> lsSurat = new ArrayList<Surat>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Surat sr = new Surat();
                sr.setId_surat(rs.getInt("surat_tindakan.Id_ST"));
                sr.setKeterangan(rs.getString("surat_tindakan.Keterangan"));
                sr.setId_dok(rs.getInt("surat_tindakan.Id_Dokter"));
                sr.setId_pas(rs.getInt("surat_tindakan.Id_Pasien"));
                sr.setFknamaPas(rs.getString("pasien.Nama"));
                lsSurat.add(sr);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteSurat.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsSurat;}
    public List<Surat> getOneSurat(String id_surat){
        String query = "select surat_tindakan.Id_ST,surat_tindakan.Keterangan,"
                + "surat_tindakan.Id_Dokter,surat_tindakan.Id_Pasien,pasien.Nama "
                + "from surat_tindakan,pasien WHERE surat_tindakan.Id_Pasien=pasien.Id_Pasien AND surat_tindakan.Id_St ="+id_surat+"";;
        ConnectionManager conMan = new ConnectionManager();
        List<Surat> lsSurat = new ArrayList<Surat>();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                Surat sr = new Surat();
                sr.setId_surat(rs.getInt("surat_tindakan.Id_ST"));
                sr.setKeterangan(rs.getString("surat_tindakan.Keterangan"));
                sr.setId_dok(rs.getInt("surat_tindakan.Id_Dokter"));
                sr.setId_pas(rs.getInt("surat_tindakan.Id_Pasien"));
                sr.setFknamaPas(rs.getString("pasien.Nama"));
                lsSurat.add(sr);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ExecuteSurat.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return lsSurat;}
    public int insertData(Surat sur){
        int hasil = 0;
        String query ="insert into surat_tindakan(Id_ST, Keterangan, Id_Dokter,Id_Pasien)values("+sur.getId_surat()+",'"+sur.getKeterangan()+"',"+sur.getId_dok()+","+sur.getId_pas()+")";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteSurat.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }
}
