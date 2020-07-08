package com;

/**
 *
 * @author Ivan Pakpahan
 */
public class Surat {
    private int id_surat;
    private String keterangan;
    private int id_dok;
    private int id_pas;

    public Surat() {
    }

    public Surat(int id_surat, String keterangan, int id_dok, int id_pas) {
        this.id_surat = id_surat;
        this.keterangan = keterangan;
        this.id_dok = id_dok;
        this.id_pas = id_pas;
    }

    public int getId_surat() {
        return id_surat;
    }

    public void setId_surat(int id_surat) {
        this.id_surat = id_surat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getId_dok() {
        return id_dok;
    }

    public void setId_dok(int id_dok) {
        this.id_dok = id_dok;
    }

    public int getId_pas() {
        return id_pas;
    }

    public void setId_pas(int id_pas) {
        this.id_pas = id_pas;
    }
    
}
