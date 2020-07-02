package com;
/**
 *
 * @author Ivan Pakpahan
 */
public class Dokter {
    private int id_dok;
    private String nama;
    private String spesialis;
    private String alamat;
    public Dokter() {
    }
    public Dokter(int id_dok, String nama, String spesialis, String alamat) {
        this.id_dok = id_dok;
        this.nama = nama;
        this.spesialis = spesialis;
        this.alamat = alamat;
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
