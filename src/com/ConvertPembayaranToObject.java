/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import exec.ExecutePembayaran;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertPembayaranToObject {
    public String[][] getPembayaran(){
        List<Pembayaran> bayar = new ArrayList<Pembayaran>();
        ExecutePembayaran ebay = new ExecutePembayaran();
        bayar = ebay.getAllPembayaran();
        String[][] dataPembayaran = new String[bayar.size()][8];
        int i=0;
        for(Pembayaran bay : bayar){
            dataPembayaran[i][0] = ""+bay.getId_bayar();
            dataPembayaran[i][1] = ""+bay.getId_lapor();
            dataPembayaran[i][2]= bay.getMetode();
            dataPembayaran[i][3]= ""+bay.getTagihan();
            dataPembayaran[i][4]= ""+bay.getAngsur();
            dataPembayaran[i][5]= ""+bay.getHutang();
            dataPembayaran[i][6]= bay.getNama();
            dataPembayaran[i][7]= bay.getNo_hp();
            i++;
        }
        return dataPembayaran;
    }
    public String[][] getOnePembayaran(String id_bayar){
        List<Pembayaran> bayar = new ArrayList<Pembayaran>();
        ExecutePembayaran ebay = new ExecutePembayaran();
        bayar = ebay.getOnePembayaran(id_bayar);
        String[][] dataPembayaran = new String[bayar.size()][8];
        int i=0;
        for(Pembayaran bay : bayar){
            dataPembayaran[i][0] = ""+bay.getId_bayar();
            dataPembayaran[i][1] = ""+bay.getId_lapor();
            dataPembayaran[i][2]= bay.getMetode();
            dataPembayaran[i][3]= ""+bay.getTagihan();
            dataPembayaran[i][4]= ""+bay.getAngsur();
            dataPembayaran[i][5]= ""+bay.getHutang();
            dataPembayaran[i][6]= bay.getNama();
            dataPembayaran[i][7]= bay.getNo_hp();
            i++;
        }
        return dataPembayaran;
    }
}
