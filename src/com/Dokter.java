package com;
/**
 *
 * @author Ivan Pakpahan
 */
public class Dokter {
    private int id_dok;
    private String nama;
    private String spesialis;
    private String no_hp;
    private String alamat;
    private String username;
    private String password;
    private String level;
    public Dokter() {
    }

    public Dokter(int id_dok, String nama, String spesialis, String no_hp, String alamat, String username, String password, String level) {
        this.id_dok = id_dok;
        this.nama = nama;
        this.spesialis = spesialis;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    public int getId_dok() {
        return id_dok;
    }
    public void setId_dok(int id_dok) {
        this.id_dok = id_dok;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getSpesialis() {
        return spesialis;
    }
    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
