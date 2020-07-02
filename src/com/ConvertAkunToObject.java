package com;
import exec.ExecuteAkun;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertAkunToObject {
    public String[][] getAkun(){
        List<Akun> akun = new ArrayList<Akun>();
        ExecuteAkun ekun = new ExecuteAkun();
        akun = ekun.getAllAkun();
        String[][] dataAkun = new String[akun.size()][7];
        int i=0;
        for(Akun ak : akun){
            dataAkun[i][0] = ""+ak.getId_akun();
            dataAkun[i][1] = ak.getUsername();
            dataAkun[i][2]= ak.getPass();
            dataAkun[i][3]= ak.getLevel();
            dataAkun[i][4] = ak.getNama();
            dataAkun[i][5]= ak.getNo_hp();
            dataAkun[i][6]= ak.getAlamat();
            i++;
        }
        return dataAkun;
    }
 }
