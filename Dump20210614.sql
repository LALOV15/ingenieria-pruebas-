CREATE DATABASE  IF NOT EXISTS `cafeteria`;
USE `cafeteria`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: cafeteria
-- ------------------------------------------------------
-- Server version	8.0.24

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
CREATE TABLE `articulo` (
  `id_articulo` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_articulo`)
);

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `telefono` int NOT NULL,
  `domicilio` varchar(100) NOT NULL,
  PRIMARY KEY (`id_proveedor`)
);

--
-- Table structure for table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE `ingrediente` (
  `id_ingrediente` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `disponible` int NOT NULL,
  `costo` decimal(10,0) NOT NULL,
  `id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_ingrediente`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `ingrediente_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
);

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `id_pago` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(20) NOT NULL,
  PRIMARY KEY (`id_pago`)
);

--
-- Table structure for table `presentacion`
--

DROP TABLE IF EXISTS `presentacion`;
CREATE TABLE `presentacion` (
  `id_presentacion` int NOT NULL AUTO_INCREMENT,
  `tamaño` varchar(10) NOT NULL,
  PRIMARY KEY (`id_presentacion`)
);

--
-- Table structure for table `preparacion`
--

DROP TABLE IF EXISTS `preparacion`;
CREATE TABLE `preparacion` (
  `id_preparacion` int NOT NULL AUTO_INCREMENT,
  `articulo` int NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `tamaño` int NOT NULL,
  `ingrediente` int NOT NULL,
  PRIMARY KEY (`id_preparacion`),
  KEY `articulo` (`articulo`),
  KEY `tamaño` (`tamaño`),
  KEY `ingrediente` (`ingrediente`),
  CONSTRAINT `preparacion_ibfk_1` FOREIGN KEY (`articulo`) REFERENCES `articulo` (`id_articulo`),
  CONSTRAINT `preparacion_ibfk_2` FOREIGN KEY (`tamaño`) REFERENCES `presentacion` (`id_presentacion`),
  CONSTRAINT `preparacion_ibfk_3` FOREIGN KEY (`ingrediente`) REFERENCES `ingrediente` (`id_ingrediente`)
);

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
  `id_venta` int NOT NULL AUTO_INCREMENT,
  `fecha` varchar(50) NOT NULL,
  `pago` int NOT NULL,
  `costo` decimal(10,0) NOT NULL,
  `producto` int NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `pago` (`pago`),
  KEY `producto` (`producto`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`pago`) REFERENCES `pago` (`id_pago`),
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`producto`) REFERENCES `articulo` (`id_articulo`)
);