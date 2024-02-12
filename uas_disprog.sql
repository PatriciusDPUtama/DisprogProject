-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2022 at 08:38 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uas_disprog`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `customer` varchar(255) NOT NULL,
  `restaurant` varchar(255) NOT NULL,
  `num_people` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `customer`, `restaurant`, `num_people`, `date`) VALUES
(1, 'Velicia', 'Hollanda', 6, '2022-06-08 00:00:00'),
(2, 'Velicia', 'Hollanda', 6, '2022-06-08 00:00:00'),
(3, 'Velicia', 'SukaSuka', 6, '2022-06-02 00:00:00'),
(4, 'Velicia', 'Double', 9, '2022-06-11 00:00:00'),
(5, 'Velicia', 'Star', 0, '2022-06-03 00:00:00'),
(6, 'Velicia', 'Royal', 0, '2022-06-04 00:00:00'),
(7, 'Velicia', 'SukaSuka', 1, '2022-06-10 00:00:00'),
(8, 'Velicia', 'Hollanda', 7, '2022-06-10 00:00:00'),
(9, 'Velicia', 'Hollanda', 10, '2022-06-18 00:00:00'),
(10, 'Velicia', 'Hollanda', 7, '2022-06-21 00:00:00'),
(11, 'Velicia', 'Royal', 7, '2022-06-17 00:00:00'),
(12, 'Velicia', 'Royal', 7, '2022-06-19 20:00:00'),
(13, 'Velicia', 'SukaSuka', 12, '2022-06-26 19:00:00'),
(14, 'Velicia', 'Royal', 5, '2022-06-21 10:00:00'),
(15, 'veliciamp', 'Royal', 1, '2022-06-09 14:00:00'),
(16, 'veliciamp', 'Royal', 1, '2022-06-09 14:00:00'),
(17, 'veliciamp', 'SukaSuka', 1, '2022-06-10 10:00:00'),
(18, 'veliciamp', 'Hollanda', 1, '2022-06-02 10:00:00'),
(19, 'veliciamp', 'Hollanda', 1, '2022-06-02 10:00:00'),
(20, 'veliciamp', 'Hollanda', 6, '2022-06-10 10:00:00'),
(21, 'veliciamp', 'SukaSuka', 4, '2022-06-02 10:00:00'),
(22, 'veliciamp', 'Hollanda', 10, '2022-06-25 13:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `username`, `name`, `password`) VALUES
(3, 'veliciamp', 'Velicia', 'velicia'),
(4, 'paddy', 'Patricius', 'patricius'),
(5, 'enjel', 'Evangeline', 'evangeline'),
(6, 'mel', 'Melani', 'melani'),
(7, 'alvin', 'Gregorius', 'gregorius'),
(8, 'wewe', 'William', 'william');

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(10) UNSIGNED NOT NULL,
  `restaurant_name` varchar(255) NOT NULL,
  `restaurant_owner` varchar(255) NOT NULL,
  `number_seats` double NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id`, `restaurant_name`, `restaurant_owner`, `number_seats`, `username`, `password`) VALUES
(1, 'SukaSuka', 'Melani', 40, 'sukasuka', 'password'),
(2, 'Hollanda', 'Angel', 50, 'hollanda', 'password'),
(3, 'Royal', 'Veli', 40, 'royal', 'password'),
(4, 'Double', 'Pad', 60, 'double', 'password'),
(5, 'Star', 'Alvin', 60, 'star', 'password'),
(6, 'Happy', 'William', 50, 'happy', 'password');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
