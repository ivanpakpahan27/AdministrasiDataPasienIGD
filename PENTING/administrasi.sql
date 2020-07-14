-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for administrasi
CREATE DATABASE IF NOT EXISTS `administrasi` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `administrasi`;

-- Dumping structure for table administrasi.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `Id_Admin` int(5) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(30) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Level` varchar(5) NOT NULL,
  `No_Hp` varchar(13) NOT NULL,
  `Email` varchar(20) NOT NULL,
  `Alamat` varchar(30) NOT NULL,
  PRIMARY KEY (`Id_Admin`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table administrasi.dokter
CREATE TABLE IF NOT EXISTS `dokter` (
  `Id_Dokter` int(5) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(30) NOT NULL,
  `Spesialis` varchar(13) NOT NULL,
  `No_Hp` varchar(13) NOT NULL,
  `Alamat` varchar(30) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Level` varchar(6) NOT NULL,
  PRIMARY KEY (`Id_Dokter`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table administrasi.laporan
CREATE TABLE IF NOT EXISTS `laporan` (
  `Id_Layanan` int(5) NOT NULL AUTO_INCREMENT,
  `Id_Pasien` int(5) NOT NULL,
  `Id_Admin` int(5) NOT NULL,
  `Id_ST` int(5) NOT NULL,
  `Tanggal` varchar(50) NOT NULL,
  PRIMARY KEY (`Id_Layanan`),
  KEY `FK_laporan_admin` (`Id_Admin`),
  KEY `FK_laporan_pasien` (`Id_Pasien`),
  KEY `FK_laporan_surat_tindakan` (`Id_ST`),
  CONSTRAINT `FK_laporan_admin` FOREIGN KEY (`Id_Admin`) REFERENCES `admin` (`Id_Admin`),
  CONSTRAINT `FK_laporan_pasien` FOREIGN KEY (`Id_Pasien`) REFERENCES `pasien` (`Id_Pasien`),
  CONSTRAINT `FK_laporan_surat_tindakan` FOREIGN KEY (`Id_ST`) REFERENCES `surat_tindakan` (`Id_ST`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table administrasi.pasien
CREATE TABLE IF NOT EXISTS `pasien` (
  `Id_Pasien` int(5) NOT NULL AUTO_INCREMENT,
  `Nama` varchar(20) NOT NULL,
  `Alamat` varchar(30) NOT NULL,
  `No_Hp` varchar(13) NOT NULL,
  `Status` varchar(15) NOT NULL,
  `No_Ruangan` varchar(15) NOT NULL,
  PRIMARY KEY (`Id_Pasien`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table administrasi.pembayaran
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `Id_Pembayaran` int(5) NOT NULL AUTO_INCREMENT,
  `Id_Layanan` int(5) NOT NULL,
  `Metode` varchar(10) NOT NULL,
  `Tagihan` int(11) NOT NULL,
  `Jumlah_Bayar` int(11) NOT NULL,
  `Hutang` int(11) NOT NULL,
  `Nama_Wali` varchar(30) NOT NULL,
  `No_Hp` varchar(13) NOT NULL,
  PRIMARY KEY (`Id_Pembayaran`),
  KEY `FK_pembayaran_laporan` (`Id_Layanan`),
  CONSTRAINT `FK_pembayaran_laporan` FOREIGN KEY (`Id_Layanan`) REFERENCES `laporan` (`Id_Layanan`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

-- Dumping structure for table administrasi.surat_tindakan
CREATE TABLE IF NOT EXISTS `surat_tindakan` (
  `Id_ST` int(5) NOT NULL AUTO_INCREMENT,
  `Keterangan` varchar(100) NOT NULL,
  `Id_Dokter` int(5) NOT NULL,
  `Id_Pasien` int(5) NOT NULL,
  PRIMARY KEY (`Id_ST`),
  KEY `FK_surat_tindakan_dokter` (`Id_Dokter`),
  KEY `FK_surat_tindakan_pasien` (`Id_Pasien`),
  CONSTRAINT `FK_surat_tindakan_dokter` FOREIGN KEY (`Id_Dokter`) REFERENCES `dokter` (`Id_Dokter`),
  CONSTRAINT `FK_surat_tindakan_pasien` FOREIGN KEY (`Id_Pasien`) REFERENCES `pasien` (`Id_Pasien`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
