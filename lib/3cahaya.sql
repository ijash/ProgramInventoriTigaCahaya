-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 04, 2018 at 07:27 AM
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
  `jenis` varchar(15) NOT NULL,
  `merk` varchar(20) NOT NULL,
  `ragam` varchar(30) NOT NULL,
  `seri` varchar(300) NOT NULL,
  `qty` int(11) NOT NULL,
  `harga_beli` double NOT NULL,
  `supplier` int(1) NOT NULL,
  `tgl_masuk` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `garansi` enum('Tidak','Distributor','Toko') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`code`, `no`, `id_barang`, `jenis`, `merk`, `ragam`, `seri`, `qty`, `harga_beli`, `supplier`, `tgl_masuk`, `garansi`) VALUES
('P', 126, 'P0000126', 'Lampion', 'tetret', 'tertert', 'terter', 5, 20000, 2, '2018-05-22 19:17:35', 'Distributor'),
('P', 127, 'P0000127', 'Lampion', 'qwdqwd', 'dwqdqw', 'dqwdqwd', 3, 5000, 1, '2018-06-03 20:04:24', 'Distributor'),
('P', 128, 'P0000128', 'Lain-lain', 'test', 'test', 'test', 10, 42000, 3, '2018-06-03 21:39:39', 'Tidak'),
('P', 131, 'P0000131', 'Lampion', 'wqewqe', 'wqewq', 'wqewqe', 2, 25000, 1, '2018-06-03 22:47:53', 'Distributor'),
('P', 132, 'P0000132', 'Lampion', 'weqw', 'eqwe', 'qweqwe', 20, 20230, 1, '2018-06-03 22:53:44', 'Distributor'),
('P', 133, 'P0000133', 'Lampion', 'weq', 'qwe', 'wqeqw', 25, 20, 1, '2018-06-03 22:58:05', 'Distributor');

-- --------------------------------------------------------

--
-- Table structure for table `barang_retur`
--

CREATE TABLE `barang_retur` (
  `id_retur` int(10) NOT NULL,
  `id_barang` varchar(10) NOT NULL,
  `qty` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jenis`
--

CREATE TABLE `jenis` (
  `id_jenis` int(5) NOT NULL,
  `nama_jenis` varchar(15) CHARACTER SET latin1 NOT NULL,
  `deskripsi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `jenis`
--

INSERT INTO `jenis` (`id_jenis`, `nama_jenis`, `deskripsi`) VALUES
(1, 'Lampion', ''),
(2, 'Lampu', ''),
(3, 'Fitting', ''),
(4, 'Mouse', ''),
(5, 'Kabel', ''),
(6, 'Laptop', ''),
(7, 'Imp', ''),
(8, 'Lain-lain', NULL);

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
  `alamat` varchar(100) NOT NULL,
  `level` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karyawan`
--

INSERT INTO `karyawan` (`id`, `password`, `nama`, `jabatan`, `nik`, `npwp`, `hp`, `alamat`, `level`) VALUES
('admin', 'admin', 'Administrator', 'Administrator', '11111111111111111111', '22222222222222222222', '0855555555555', '127.0.0.1', 1),
('ijash', 'ijash', 'jastria R', 'operator input', '123', '123', '321999312', 'ajsdj a dois 2 asiod ojasd jo', 2),
('test', '1234', 'test', 'test', 'test', 'test', 'test', 'test', 2);

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
(1, 'merchant', 'datang sendiri', '082648512659', 'benny@expensive.com', 'sales'),
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
(1, 'TC00000001', 'P0000127', 2, 5000, ''),
(2, 'TC00000002', 'P0000126', 21, 20000, ''),
(3, 'TC00000003', 'P0000126', 2, 20000, '');

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
('TC', 4, 'TC00000004', NULL, NULL, b'0');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`no`),
  ADD UNIQUE KEY `id_barang` (`id_barang`),
  ADD KEY `supplier` (`supplier`),
  ADD KEY `jenis` (`jenis`),
  ADD KEY `jenis_2` (`jenis`);

--
-- Indexes for table `barang_retur`
--
ALTER TABLE `barang_retur`
  ADD PRIMARY KEY (`id_retur`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `jenis`
--
ALTER TABLE `jenis`
  ADD PRIMARY KEY (`id_jenis`),
  ADD UNIQUE KEY `jenis` (`nama_jenis`);

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
  ADD KEY `nama` (`nama`),
  ADD KEY `nama_3` (`nama`);

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
  MODIFY `no` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=134;
--
-- AUTO_INCREMENT for table `barang_retur`
--
ALTER TABLE `barang_retur`
  MODIFY `id_retur` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `jenis`
--
ALTER TABLE `jenis`
  MODIFY `id_jenis` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `transaksi_cart`
--
ALTER TABLE `transaksi_cart`
  MODIFY `id_cart` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `transaksi_invoice`
--
ALTER TABLE `transaksi_invoice`
  MODIFY `no_inv` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`id_supplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `barang_ibfk_2` FOREIGN KEY (`jenis`) REFERENCES `jenis` (`nama_jenis`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `barang_retur`
--
ALTER TABLE `barang_retur`
  ADD CONSTRAINT `barang_retur_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaksi_cart`
--
ALTER TABLE `transaksi_cart`
  ADD CONSTRAINT `transaksi_cart_ibfk_1` FOREIGN KEY (`id_inv`) REFERENCES `transaksi_invoice` (`id_inv`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_cart_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
