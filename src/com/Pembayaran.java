package com;
/**
 *
 * @author Ivan Pakpahan
 */
public class Pembayaran {
    private int id_bayar;
    private int id_lapor;
    private String metode;
    private int tagihan;
    private int angsur;
    private int hutang;
    private String nama;
    private String no_hp;
    public Pembayaran() {
    }
    public Pembayaran(int id_bayar, int id_lapor, String metode, int tagihan, int angsur, int hutang, String nama, String no_hp) {
        this.id_bayar = id_bayar;
        this.id_lapor = id_lapor;
        this.metode = metode;
        this.tagihan = tagihan;
        this.angsur = angsur;
        this.hutang = hutang;
        this.nama = nama;
        this.no_hp = no_hp;
    }
    public int getId_bayar() {
        return id_bayar;
    }
    public void setId_bayar(int id_bayar) {
        this.id_bayar = id_bayar;
    }
    public int getId_lapor() {
        return id_lapor;
    }
    public void setId_lapor(int id_lapor) {
        this.id_lapor = id_lapor;
    }
    public String getMetode() {
        return metode;
    }
    public void setMetode(String metode) {
        this.metode = metode;
    }
    public int getTagihan() {
        return tagihan;
    }
    public void setTagihan(int tagihan) {
        this.tagihan = tagihan;
    }
    public int getAngsur() {
        return angsur;
    }
    public void setAngsur(int angsur) {
        this.angsur = angsur;
    }
    public int getHutang() {
        return hutang;
    }
    public void setHutang(int hutang) {
        this.hutang = hutang;
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
}
