package db;
import java.sql.Connection;
/**
 *
 * @author Ivan Pakpahan
 */
public class TesKoneksi {
    public static void main(String[] args) {
    ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        System.out.println("Koneksi Anda : " + conn);
    }
}
