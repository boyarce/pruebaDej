-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Nov 09, 2019 at 09:04 PM
-- Server version: 5.7.23
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prueba`
--

-- --------------------------------------------------------

--
-- Table structure for table `empleado`
--

CREATE TABLE `empleado` (
  `run` varchar(15) NOT NULL,
  `nombre` varchar(90) NOT NULL,
  `apa` varchar(90) NOT NULL,
  `ama` varchar(90) NOT NULL,
  `tipo_empleado` int(5) NOT NULL,
  `telefono` int(9) NOT NULL,
  `remuneracion` int(11) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `fecha_contratacion` date NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `correo` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Stand-in structure for view `prueba`
-- (See below for the actual view)
--
CREATE TABLE `prueba` (
`run` varchar(15)
,`nombre` varchar(90)
,`apa` varchar(90)
,`ama` varchar(90)
,`tipo_empleado` int(5)
,`telefono` int(9)
,`remuneracion` int(11)
,`fecha_nacimiento` date
,`fecha_contratacion` date
,`direccion` varchar(45)
,`correo` varchar(30)
);

-- --------------------------------------------------------

--
-- Table structure for table `tipo`
--

CREATE TABLE `tipo` (
  `codigo` int(5) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `codigo` int(11) NOT NULL,
  `user` varchar(90) NOT NULL,
  `pass` varchar(90) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`codigo`, `user`, `pass`) VALUES
(1, 'admin', '123456');

-- --------------------------------------------------------

--
-- Structure for view `prueba`
--
DROP TABLE IF EXISTS `prueba`;

CREATE ALGORITHM=UNDEFINED DEFINER=`ADMIN`@`%` SQL SECURITY DEFINER VIEW `prueba`  AS  select `empleado`.`run` AS `run`,`empleado`.`nombre` AS `nombre`,`empleado`.`apa` AS `apa`,`empleado`.`ama` AS `ama`,`empleado`.`tipo_empleado` AS `tipo_empleado`,`empleado`.`telefono` AS `telefono`,`empleado`.`remuneracion` AS `remuneracion`,`empleado`.`fecha_nacimiento` AS `fecha_nacimiento`,`empleado`.`fecha_contratacion` AS `fecha_contratacion`,`empleado`.`direccion` AS `direccion`,`empleado`.`correo` AS `correo` from `empleado` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`run`);

--
-- Indexes for table `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`codigo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codigo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
