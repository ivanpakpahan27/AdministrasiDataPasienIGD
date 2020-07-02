package com;

/**
 *
 * @author Ivan Pakpahan
 */
public class Akun {
    private int id_akun;
    private String username;
    private String pass;
    private String level;
    private String nama;
    private String no_hp;
    private String alamat;

    public Akun() {
    }

    public Akun(int id_akun, String username, String pass, String level, String nama, String no_hp, String alamat) {
        this.id_akun = id_akun;
        this.username = username;
        this.pass = pass;
        this.level = level;
        this.nama = nama;
        this.no_hp = no_hp;
        this.alamat = alamat;
    }
    public int getId_akun() {
        return id_akun;
    }
    public void setId_akun(int id_akun) {
        this.id_akun = id_akun;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getNo_hp() {
        return no_hp;
    }
    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
