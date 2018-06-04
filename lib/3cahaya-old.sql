-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 01, 2018 at 10:01 PM
-- Server version: 10.1.23-MariaDB-9+deb9u1
-- PHP Version: 7.0.27-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `3cahaya`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `code` varchar(1) NOT NULL DEFAULT 'P',
  `no` int(8) NOT NULL,
  `id_barang` varchar(10) DEFAULT NULL,
  `jenis` varchar(16) NOT NULL,
  `merk` varchar(20) NOT NULL,
  `ragam` varchar(30) NOT NULL,
  `seri` varchar(300) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga_beli` double NOT NULL,
  `supplier` int(30) NOT NULL,
  `tgl_masuk` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `garansi` enum('Tidak','Distributor','Toko') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`code`, `no`, `id_barang`, `jenis`, `merk`, `ragam`, `seri`, `qty`, `harga_beli`, `supplier`, `tgl_masuk`, `garansi`) VALUES
('P', 12, 'P0000012', 'lampu', 'philips', 'hallogen', 'Essential Halogen 12V/20W', 95, 15000, 1, '2017-11-17 18:46:00', 'Tidak'),
('P', 20, 'P0000020', 'lampu', 'Holz', 'led', '3W E27 Putih (Cool Day Light)', 19, 31000, 1, '2017-11-17 19:22:26', 'Distributor'),
('P', 60, 'P0000060', 'lampu', 'kawachi', 'led', 'metaled 11w', 20, 35000, 3, '2017-11-22 21:33:48', 'Toko'),
('P', 64, 'P0000064', 'lampu', 'Philips', 'led', '13WATT 13W 13 W (1 paket 4 pcs)', 86, 83250, 4, '2017-11-23 12:23:08', 'Distributor'),
('P', 65, 'P0000065', 'lampu', 'Generic', 'port', 'body karakter Doraemon Hello kitty', 21, 83250, 1, '2017-11-23 12:25:55', 'Toko'),
('P', 66, 'P0000066', 'lampion', 'u&me', 'port', 'lampion benang/lampu tidur u&me ukuran jumbo', 65, 83250, 1, '2017-11-23 12:27:33', 'Tidak'),
('P', 67, 'P0000067', 'lampu', 'plit', 'led', 'lampu meja', 27, 83250, 1, '2017-11-23 12:28:46', 'Toko'),
('P', 69, 'P0000069', 'lampu', 'rechargeable', 'led', 'desk light', 83, 83250, 1, '2017-11-23 12:32:10', 'Distributor'),
('P', 70, 'P0000070', 'lampu', 'clip fashion', 'led', 'desk light', 45, 83250, 1, '2017-11-23 12:33:19', 'Toko'),
('P', 71, 'P0000071', 'lampu', 'pohon natal', 'led', 'christmast', 216, 83250, 1, '2017-11-23 12:34:29', 'Toko'),
('P', 72, 'P0000072', 'lampu', 'TL t5', 'led', '12watt 90cm white', 23, 83250, 4, '2017-11-23 12:35:45', 'Tidak'),
('P', 73, 'P0000073', 'lampu', 'Generic', 'led', 'kap hias gantung indrustrial', 25, 83250, 4, '2017-11-23 12:37:12', 'Toko'),
('P', 74, 'P0000074', 'lampu', 'Tdies', 'led', 'kap hias', 97, 83250, 4, '2017-11-23 12:37:45', 'Tidak'),
('P', 76, 'P0000076', 'Fitting', 'Mikawa', 'Cup Fitting WD', 'Hijau 12\"', 41, 12500, 4, '2017-11-27 16:15:22', 'Tidak'),
('P', 79, 'P0000079', 'lampu', 'Philips', 'led', '13WATT 13W 13 W (1 paket 4 pcs)', 77, 83250, 4, '2017-12-02 17:08:22', 'Distributor'),
('P', 101, 'P0000101', 'lampu', 'xiaomi', 'led', ' yeelight 2 E27 Smart LED Blub 8w', 52, 299900, 1, '2017-12-11 23:10:23', 'Tidak'),
('P', 103, 'P0000103', 'lampu', 'pohon natal', 'neon', 'christmast', 213, 83250, 1, '2018-01-08 16:46:41', 'Toko'),
('P', 109, 'P0000109', 'Mouse', 'A4Tech', 'Gaming', 'X7 Gaming Mouse', 38, 280000, 3, '2018-01-08 22:39:31', 'Distributor'),
('P', 110, 'P0000110', 'Kabel', 'Eterna', 'Tembaga', '80mm Industrial isi 3', 60, 3000, 4, '2018-01-08 22:44:52', 'Distributor');

-- --------------------------------------------------------

--
-- Table structure for table `barang_retur`
--

CREATE TABLE `barang_retur` (
  `id_retur` int(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `qty` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang_retur`
--

INSERT INTO `barang_retur` (`id_retur`, `id_barang`, `qty`) VALUES
(7, 'P0000012', 3),
(8, 'P0000020', 1),
(14, 'P0000069', 2),
(26, 'P0000069', 4);

-- --------------------------------------------------------

--
-- Table structure for table `karyawan`
--

CREATE TABLE `karyawan` (
  `id` varchar(20) NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '1234',
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(20) NOT NULL,
  `nik` varchar(20) NOT NULL,
  `npwp` varchar(20) NOT NULL,
  `hp` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id`, `password`, `nama`, `jabatan`, `nik`, `npwp`, `hp`, `alamat`) VALUES
('admin', 'admin', 'Administrator', 'Administrator', '11111111111111111111', '22222222222222222222', '0855555555555', '127.0.0.1'),
('fahmi', '1234', 'burit', 'a', 'b', 'b', 'b', 'n'),
('jose', 'password', 'Jose Febrian', 'Co-Founder', '99999999999999999999', '99999999999999999999', '082162164546', 'Tangerang'),
('test', '1234', 'test', 'test', 'test', 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` int(5) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `alamat` varchar(180) NOT NULL,
  `telp` varchar(15) NOT NULL,
  `email` varchar(35) NOT NULL,
  `catatan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama`, `alamat`, `telp`, `email`, `catatan`) VALUES
(1, 'merchant', 'datang sendiri', '082648512655', 'benny@expensive.com', 'sales'),
(2, 'umum', 'tidak ada alamat', '', '', 'belanja umum, semua jenis kategori'),
(3, 'Online Shop', 'berbagai macam online shop', '', '', 'www.tokopedia.com\r\n'),
(4, 'Indocahaya', 'Parung, jalan raya parung', '025163545800', 'indocahaya@surga.com', 'supplier kawachi');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_cart`
--

CREATE TABLE `transaksi_cart` (
  `id_cart` bigint(20) NOT NULL,
  `id_inv` varchar(13) DEFAULT NULL,
  `id_barang` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga` double NOT NULL,
  `keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_cart`
--

INSERT INTO `transaksi_cart` (`id_cart`, `id_inv`, `id_barang`, `qty`, `harga`, `keterangan`) VALUES
(2, 'TC00000001', 'P0000011', 1, 41300, ''),
(5, 'TC00000003', 'P0000011', 2, 41300, ''),
(9, 'TC00000005', 'P0000061', 1, 240000, ''),
(14, 'TC00000005', 'ID', 1, 299900, ''),
(15, 'TC00000005', 'P0000069', 4, 83250, ''),
(19, 'TC00000008', 'P0000073', 1, 83250, ''),
(21, 'TC00000009', 'P0000072', 1, 83250, ''),
(30, 'TC00000010', 'P0000069', 1, 83250, ''),
(31, 'TC00000010', 'P0000100', 1, 31750, ''),
(33, 'TC00000011', 'P0000065', 1, 83250, ''),
(36, 'TC00000012', 'P0000100', 1, 31750, ''),
(38, 'TC00000013', 'P0000071', 0, 83250, ''),
(40, 'TC00000014', 'P0000065', 1, 83250, ''),
(41, 'TC00000014', 'P0000011', 2, 41300, ''),
(47, 'TC00000019', 'P0000059', 1, 200000, ''),
(48, 'TC00000020', 'P0000067', 1, 83250, ''),
(54, 'TC00000023', 'P0000012', 1, 15000, ''),
(55, 'TC00000024', 'P0000064', 1, 83250, ''),
(56, 'TC00000025', 'P0000012', 1, 15000, ''),
(57, 'TC00000025', 'P0000019', 1, 299900, ''),
(58, 'TC00000026', 'P0000061', 1, 240000, ''),
(65, 'TC00000027', 'P0000066', 3, 83250, ''),
(66, 'TC00000027', 'P0000071', 0, 83250, ''),
(67, 'TC00000027', 'P0000103', 0, 83250, ''),
(68, 'TC00000027', 'P0000012', 2, 15000, ''),
(69, 'TC00000027', 'P0000101', 1, 299900, ''),
(70, 'TC00000028', 'P0000071', 0, 83250, ''),
(71, 'TC00000029', 'P0000103', 0, 83250, '');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_invoice`
--

CREATE TABLE `transaksi_invoice` (
  `prefix_inv` varchar(3) NOT NULL DEFAULT 'TC',
  `no_inv` int(10) NOT NULL,
  `id_inv` varchar(13) DEFAULT NULL,
  `diskon` double DEFAULT NULL,
  `keterangan` varchar(300) DEFAULT NULL,
  `lunas` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi_invoice`
--

INSERT INTO `transaksi_invoice` (`prefix_inv`, `no_inv`, `id_inv`, `diskon`, `keterangan`, `lunas`) VALUES
('TC', 1, 'TC00000001', NULL, NULL, b'1'),
('TC', 2, 'TC00000002', NULL, NULL, b'1'),
('TC', 3, 'TC00000003', NULL, NULL, b'1'),
('TC', 4, 'TC00000004', NULL, NULL, b'1'),
('TC', 5, 'TC00000005', NULL, NULL, b'1'),
('TC', 6, 'TC00000006', NULL, NULL, b'1'),
('TC', 7, 'TC00000007', NULL, NULL, b'1'),
('TC', 8, 'TC00000008', NULL, NULL, b'1'),
('TC', 9, 'TC00000009', NULL, NULL, b'1'),
('TC', 10, 'TC00000010', NULL, NULL, b'1'),
('TC', 11, 'TC00000011', NULL, NULL, b'1'),
('TC', 12, 'TC00000012', NULL, NULL, b'1'),
('TC', 13, 'TC00000013', NULL, NULL, b'1'),
('TC', 14, 'TC00000014', NULL, NULL, b'1'),
('TC', 15, 'TC00000015', NULL, NULL, b'1'),
('TC', 16, 'TC00000016', NULL, NULL, b'1'),
('TC', 17, 'TC00000017', NULL, NULL, b'1'),
('TC', 18, 'TC00000018', NULL, NULL, b'1'),
('TC', 19, 'TC00000019', NULL, NULL, b'1'),
('TC', 20, 'TC00000020', NULL, NULL, b'1'),
('TC', 21, 'TC00000021', NULL, NULL, b'1'),
('TC', 22, 'TC00000022', NULL, NULL, b'1'),
('TC', 23, 'TC00000023', NULL, NULL, b'1'),
('TC', 24, 'TC00000024', NULL, NULL, b'1'),
('TC', 25, 'TC00000025', NULL, NULL, b'1'),
('TC', 26, 'TC00000026', NULL, NULL, b'1'),
('TC', 27, 'TC00000027', NULL, NULL, b'1'),
('TC', 28, 'TC00000028', NULL, NULL, b'1'),
('TC', 29, 'TC00000029', NULL, NULL, b'1'),
('TC', 30, 'TC00000030', NULL, NULL, b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`no`),
  ADD UNIQUE KEY `id_barang` (`id_barang`),
  ADD KEY `supplier` (`supplier`);

--
-- Indexes for table `barang_retur`
--
ALTER TABLE `barang_retur`
  ADD PRIMARY KEY (`id_retur`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`),
  ADD UNIQUE KEY `nama_2` (`nama`),
  ADD KEY `nama` (`nama`);

--
-- Indexes for table `transaksi_cart`
--
ALTER TABLE `transaksi_cart`
  ADD PRIMARY KEY (`id_cart`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_inv` (`id_inv`);

--
-- Indexes for table `transaksi_invoice`
--
ALTER TABLE `transaksi_invoice`
  ADD PRIMARY KEY (`no_inv`),
  ADD KEY `id_inv` (`id_inv`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `no` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;
--
-- AUTO_INCREMENT for table `barang_retur`
--
ALTER TABLE `barang_retur`
  MODIFY `id_retur` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `transaksi_cart`
--
ALTER TABLE `transaksi_cart`
  MODIFY `id_cart` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `transaksi_invoice`
--
ALTER TABLE `transaksi_invoice`
  MODIFY `no_inv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `barang_retur`
--
ALTER TABLE `barang_retur`
  ADD CONSTRAINT `barang_retur_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
