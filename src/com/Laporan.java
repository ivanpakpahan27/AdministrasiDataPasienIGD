package com;
public class Laporan {
    private int id_laporan;
    private int id_pas ;
    private int id_admin;
    private int id_surat;
    private String tanggal;
    public Laporan() {
    }
    public Laporan(int id_laporan, int id_pas, int id_admin, int id_surat, String tanggal) {
        this.id_laporan = id_laporan;
        this.id_pas = id_pas;
        this.id_admin = id_admin;
        this.id_surat = id_surat;
        this.tanggal = tanggal;
    }
    public int getId_laporan() {
        return id_laporan;
    }
    public void setId_laporan(int id_laporan) {
        this.id_laporan = id_laporan;
    }
    public int getId_pas() {
        return id_pas;
    }
    public void setId_pas(int id_pas) {
        this.id_pas = id_pas;
    }
    public int getId_admin() {
        return id_admin;
    }
    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }
    public int getId_surat() {
        return id_surat;
    }
    public void setId_surat(int id_surat) {
        this.id_surat = id_surat;
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
