package com;
import exec.ExecuteDokter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertDokterToObject {
    public String[][] getDokter(){
        List<Dokter> dokter = new ArrayList<Dokter>();
        ExecuteDokter edok = new ExecuteDokter();
        dokter = edok.getAllDokter();
        String[][] dataDokter = new String[dokter.size()][4];
        int i=0;
        for(Dokter dok : dokter){
            dataDokter[i][0] = ""+dok.getId_dok();
            dataDokter[i][1] = dok.getNama();
            dataDokter[i][2]= dok.getSpesialis();
            dataDokter[i][3]= dok.getAlamat();
            i++;
        }
        return dataDokter;
    }
 }
