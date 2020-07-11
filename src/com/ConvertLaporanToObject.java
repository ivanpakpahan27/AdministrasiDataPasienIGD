/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import exec.ExecuteDokter;
import exec.ExecuteLaporan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertLaporanToObject {
    public String[][] getLaporan(){
        List<Laporan> lapor = new ArrayList<Laporan>();
        ExecuteLaporan edok = new ExecuteLaporan();
        lapor = edok.getAllLaporan();
        String[][] dataLaporan = new String[lapor.size()][5];
        int i=0;
        for(Laporan lap : lapor){
            dataLaporan[i][0] = ""+lap.getId_laporan();
            dataLaporan[i][1] = ""+lap.getId_pas();
            dataLaporan[i][2]= ""+lap.getId_admin();
            dataLaporan[i][3]= ""+lap.getId_surat();
            dataLaporan[i][4]= lap.getTanggal();
            i++;
        }
        return dataLaporan;
    }
    public String[][] getOneLaporan(String id_lap){
        List<Laporan> lapor = new ArrayList<Laporan>();
        ExecuteLaporan edok = new ExecuteLaporan();
        lapor = edok.getOneLaporan(id_lap);
        String[][] dataLaporan = new String[lapor.size()][5];
        int i=0;
        for(Laporan lap : lapor){
            dataLaporan[i][0] = ""+lap.getId_laporan();
            dataLaporan[i][1] = ""+lap.getId_pas();
            dataLaporan[i][2]= ""+lap.getId_admin();
            dataLaporan[i][3]= ""+lap.getId_surat();
            dataLaporan[i][4]= lap.getTanggal();
            i++;
        }
        return dataLaporan;
    }
}
