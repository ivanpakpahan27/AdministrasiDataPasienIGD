package com;
import exec.ExecutePasien;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertPasienToObject {
    public String[][] getPasien(){
        List<Pasien> pasien = new ArrayList<Pasien>();
        ExecutePasien ePas = new ExecutePasien();
        pasien = ePas.getAllPasien();
        String[][] dataPasien = new String[pasien.size()][6];
        int i=0;
        for(Pasien pas : pasien){
            dataPasien[i][0] = ""+pas.getId_pas();
            dataPasien[i][1] = pas.getNama();
            dataPasien[i][2]= pas.getAlamat();
            dataPasien[i][3]= pas.getNo_hp();
            dataPasien[i][4]= pas.getStatus();
            dataPasien[i][5]= pas.getNo_ruang();
            i++;
        }
        return dataPasien;
    }
    public String[][] getOnePasien(String id_pasien){
        List<Pasien> pasien = new ArrayList<Pasien>();
        ExecutePasien ePas = new ExecutePasien();
        pasien = ePas.getOnePasien(id_pasien);
        String[][] dataOnePasien = new String[pasien.size()][6];
        int i=0;
        for(Pasien pas : pasien){
            dataOnePasien[i][0] = ""+pas.getId_pas();
            dataOnePasien[i][1] = pas.getNama();
            dataOnePasien[i][2]= pas.getAlamat();
            dataOnePasien[i][3]= pas.getNo_hp();
            dataOnePasien[i][4]= pas.getStatus();
            dataOnePasien[i][5]= pas.getNo_ruang();
            i++;
        }
        return dataOnePasien;
    }
        
 }

