-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: supero
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbkey`
--

DROP TABLE IF EXISTS `tbkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbkey` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `idUser` bigint(20) NOT NULL,
  `txKey` varchar(255) NOT NULL,
  `dtStart` datetime NOT NULL,
  `dtEnd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_key_user_idx` (`idUser`),
  CONSTRAINT `fk_key_user` FOREIGN KEY (`idUser`) REFERENCES `tbuser` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbkey`
--

LOCK TABLES `tbkey` WRITE;
/*!40000 ALTER TABLE `tbkey` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbkey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbtask`
--

DROP TABLE IF EXISTS `tbtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbtask` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `txTitle` varchar(255) NOT NULL,
  `txStatus` varchar(255) NOT NULL,
  `txDesc` varchar(255) DEFAULT NULL,
  `dtStart` datetime NOT NULL,
  `dtEdit` datetime DEFAULT NULL,
  `dtDelete` datetime DEFAULT NULL,
  `dtEnd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `in_title` (`txTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbtask`
--

LOCK TABLES `tbtask` WRITE;
/*!40000 ALTER TABLE `tbtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbuser`
--

DROP TABLE IF EXISTS `tbuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `txUserName` varchar(255) NOT NULL,
  `txPassword` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `in_username` (`txUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbuser`
--

LOCK TABLES `tbuser` WRITE;
/*!40000 ALTER TABLE `tbuser` DISABLE KEYS */;
INSERT INTO `tbuser` VALUES (1,'administrador','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413');
/*!40000 ALTER TABLE `tbuser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-05 11:38:51
