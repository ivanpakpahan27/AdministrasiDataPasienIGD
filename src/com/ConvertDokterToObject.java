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
        String[][] dataDokter = new String[dokter.size()][8];
        int i=0;
        for(Dokter dok : dokter){
            dataDokter[i][0] = ""+dok.getId_dok();
            dataDokter[i][1] = dok.getNama();
            dataDokter[i][2]= dok.getSpesialis();
            dataDokter[i][3]= dok.getNo_hp();
            dataDokter[i][4]= dok.getAlamat();
            dataDokter[i][5]= dok.getUsername();
            dataDokter[i][6]= dok.getPassword();
            dataDokter[i][7]= dok.getLevel();
            
            i++;
        }
        return dataDokter;
    }
    public String[][] getOneDokter(String id_dokter){
        List<Dokter> dokter = new ArrayList<Dokter>();
        ExecuteDokter edok = new ExecuteDokter();
        dokter = edok.getOneDokter(id_dokter);
        String[][] dataDokter = new String[dokter.size()][8];
        int i=0;
        for(Dokter dok : dokter){
            dataDokter[i][0] = ""+dok.getId_dok();
            dataDokter[i][1] = dok.getNama();
            dataDokter[i][2]= dok.getSpesialis();
            dataDokter[i][3]= dok.getNo_hp();
            dataDokter[i][4]= dok.getAlamat();
            dataDokter[i][5]= dok.getUsername();
            dataDokter[i][6]= dok.getPassword();
            dataDokter[i][7]= dok.getLevel();
            i++;
        }
        return dataDokter;
    }
    public String[][] getProfilDokter(String uname,String pwd){
        List<Dokter> dokter = new ArrayList<Dokter>();
        ExecuteDokter edok = new ExecuteDokter();
        dokter = edok.getProfilDokter(uname, pwd);
        String[][] dataDokter = new String[dokter.size()][8];
        int i=0;
        for(Dokter dok : dokter){
            dataDokter[i][0] = ""+dok.getId_dok();
            dataDokter[i][1] = dok.getNama();
            String nama = dok.getNama();
            dataDokter[i][2]= dok.getSpesialis();
            dataDokter[i][3]= dok.getNo_hp();
            dataDokter[i][4]= dok.getAlamat();
            dataDokter[i][5]= dok.getUsername();
            dataDokter[i][6]= dok.getPassword();
            dataDokter[i][7]= dok.getLevel();
            i++;
        }
        return dataDokter;
    }
 }
