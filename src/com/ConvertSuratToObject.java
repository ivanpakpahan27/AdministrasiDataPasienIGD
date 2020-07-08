package com;

import exec.ExecuteSurat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertSuratToObject {
    public String[][] getSurat(){
        List<Surat> surat = new ArrayList<Surat>();
        ExecuteSurat eRat = new ExecuteSurat();
        surat = eRat.getAllSurat();
        String[][] dataSurat = new String[surat.size()][5];
        int i=0;
        for(Surat sur : surat){
            dataSurat[i][0] = ""+sur.getId_surat();
            dataSurat[i][1] = sur.getKeterangan();
            dataSurat[i][2]= ""+sur.getId_dok();
            dataSurat[i][3]= ""+sur.getId_pas();
            dataSurat[i][4]= sur.getFknamaPas();
            i++;
        }
        return dataSurat;
    }
}
