package com;

/**
 *
 * @author Ivan Pakpahan
 */
public class Admin {
    private int Id_Admin;
    private String Nama;
    private String Username;
    private String Password;
    private String Level;
    private String No_Hp;
    private String Email;
    private String Alamat;

    public Admin() {
    }
    public Admin(int Id_Admin, String Nama, String Username, String Password, String Level, String No_Hp, String Email, String Alamat) {
        this.Id_Admin = Id_Admin;
        this.Nama = Nama;
        this.Username = Username;
        this.Password = Password;
        this.Level = Level;
        this.No_Hp = No_Hp;
        this.Email = Email;
        this.Alamat = Alamat;
    }

    public int getId_Admin() {
        return Id_Admin;
    }

    public void setId_Admin(int Id_Admin) {
        this.Id_Admin = Id_Admin;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getNo_Hp() {
        return No_Hp;
    }

    public void setNo_Hp(String No_Hp) {
        this.No_Hp = No_Hp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String Alamat) {
        this.Alamat = Alamat;
    }
    
    
}

   