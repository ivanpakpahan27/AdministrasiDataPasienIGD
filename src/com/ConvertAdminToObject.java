package com;
import exec.ExecuteAdmin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ivan Pakpahan
 */
public class ConvertAdminToObject {
    public String[][] getAdmin(){
        List<Admin> admin = new ArrayList<Admin>();
        ExecuteAdmin edmin = new ExecuteAdmin();
        admin = edmin.getAllAdmin();
        String[][] dataAdmin = new String[admin.size()][8];
        int i=0;
        for(Admin ad : admin){
            dataAdmin[i][0] = ""+ad.getId_Admin();
            dataAdmin[i][1] = ad.getNama();
            dataAdmin[i][2]= ad.getUsername();
            dataAdmin[i][3]= ad.getPassword();
            dataAdmin[i][4] = ad.getLevel();
            dataAdmin[i][5]= ad.getNo_Hp();
            dataAdmin[i][6]= ad.getEmail();
            dataAdmin[i][7]= ad.getAlamat();
            i++;
        }
        return dataAdmin;
    }
    public String[][] getOneAdmin(String id_admin){
        List<Admin> admin = new ArrayList<Admin>();
        ExecuteAdmin edmin = new ExecuteAdmin();
        admin = edmin.getOneAdmin(id_admin);
        String[][] dataAdmin = new String[admin.size()][8];
        int i=0;
        for(Admin ad : admin){
            dataAdmin[i][0] = ""+ad.getId_Admin();
            dataAdmin[i][1] = ad.getNama();
            dataAdmin[i][2]= ad.getUsername();
            dataAdmin[i][3]= ad.getPassword();
            dataAdmin[i][4] = ad.getLevel();
            dataAdmin[i][5]= ad.getNo_Hp();
            dataAdmin[i][6]= ad.getEmail();
            dataAdmin[i][7]= ad.getAlamat();
            i++;
        }
        return dataAdmin;
    }
 }
