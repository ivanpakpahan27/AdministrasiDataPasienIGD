package com;
/**
 *
 * @author Ivan Pakpahan
 */
public class Pasien {
    private int id_pas;
    private String nama;
    private String no_hp;
    private String alamat;
    private String status;
    private String no_ruang;
    public Pasien() {
    }
    public Pasien(int id_pas, String nama, String no_hp, String alamat, String status, String no_ruang) {
        this.id_pas = id_pas;
        this.nama = nama;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.status = status;
        this.no_ruang = no_ruang;
    }
    public String getNo_ruang() {
        return no_ruang;
    }
    public void setNo_ruang(String no_ruang) {
        this.no_ruang = no_ruang;
    }
    public int getId_pas() {
        return id_pas;
    }
    public void setId_pas(int id_pas) {
        this.id_pas = id_pas;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
