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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbkey`
--

LOCK TABLES `tbkey` WRITE;
/*!40000 ALTER TABLE `tbkey` DISABLE KEYS */;
INSERT INTO `tbkey` VALUES (1,1,'5eb08727d6eef33d72fd7efa16659ee9884669be6a7933b0c004b3ea46709e8802b48a456878336422350b7e673f9108de4cc37a43203d3764162873e6bfe024','2018-11-02 12:46:46','2018-11-02 14:35:29'),(2,1,'d57fb214bfd56290ff99aff1547f08af10fc54374ab8553d356998fa72ef45391adbb1138350d4fe7cda2135a70f129a10a72a1678cdfbaeabec862b1a0656b0','2018-11-02 12:47:00','2018-11-02 14:34:38'),(3,1,'3e47535cf2680247acea47c1c4b054b4c6d422d90081eb84dfc13550b141cd89929d0b6eb461cbc93222edd6a05bbf68f0f18baebe71c879182018364ad771b4','2018-11-02 13:49:36',NULL),(4,1,'4283a7dfc460f2de5f2f5f72e3fe64772ae41a85b46df1df94e48ac6dc0d5b82bbe7b67fb04c774e8fe9f0a591eae5abf022cfda1442492e724e532e4a607093','2018-11-02 14:35:51',NULL),(5,1,'4d325b7534cf6d5e87b6431071de5021fe679bbe868224ff51fefbe2cf81670f82646219349d163ac2cfc0bddaf017dcbd0246915772c8b75feba9537484b984','2018-11-02 14:43:21',NULL),(6,1,'93458ade1729a2ac6d015750988033511f017caf1631f98072d40aa5177fc2afbec2bf9b9765f5de30e946fce871a5b66fc80c814e157ebcc3ea97eac44ac1d4','2018-11-02 14:43:55',NULL),(7,1,'f0ef71297b406f404a1f32e80940e434c37c88eabf8128cd32d5420d5319533b253f8fa8d7b0107a765c3fdec1a95d9e99fd7cd74d169991df076e50f890a965','2018-11-02 14:44:07',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
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

-- Dump completed on 2018-11-02 12:25:04
