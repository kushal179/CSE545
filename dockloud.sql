CREATE DATABASE  IF NOT EXISTS `dockloud` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dockloud`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 10.0.2.125    Database: dockloud
-- ------------------------------------------------------
-- Server version	5.1.63-0ubuntu0.10.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` text,
  `PASSWORD` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `ID` tinyint(4) NOT NULL,
  `NAME` text,
  `DESC` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `FILE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PATH` text,
  `OWNER_ID` bigint(20) DEFAULT NULL,
  `DEPT_ID` tinyint(4) DEFAULT NULL,
  `CREATION_TIME` datetime DEFAULT NULL,
  `MOD_TIME` datetime DEFAULT NULL,
  `LOCK` bit(1) DEFAULT NULL,
  `PASSWORD` text,
  PRIMARY KEY (`FILE_ID`),
  KEY `fk_files_1_idx` (`OWNER_ID`),
  KEY `DEPT_FK_idx` (`DEPT_ID`),
  CONSTRAINT `FILES_DEPT_FK` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FILES_OWNER_USER_FK` FOREIGN KEY (`OWNER_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logs` (
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `PATH` text,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `DESC` text,
  `CREATE` bit(1) DEFAULT NULL,
  `UPDATE` bit(1) DEFAULT NULL,
  `DOWNLOAD` bit(1) DEFAULT NULL,
  `DELETE` bit(1) DEFAULT NULL,
  `SHARE` bit(1) DEFAULT NULL,
  `CHECKIN_OUT` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sharing`
--

DROP TABLE IF EXISTS `sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sharing` (
  `FILE_ID` bigint(20) NOT NULL,
  `USER_ID_BY` bigint(20) NOT NULL,
  `USER_ID_TO` bigint(20) NOT NULL,
  `DOWNLOAD` bit(1) DEFAULT NULL,
  `UPDATE` bit(1) DEFAULT NULL,
  `CHECKIN_OUT` bit(1) DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`,`USER_ID_BY`,`USER_ID_TO`),
  KEY `FILE_FK_idx` (`FILE_ID`),
  KEY `USER_BY_FK_idx` (`USER_ID_BY`),
  KEY `USER_TO_FK_idx` (`USER_ID_TO`),
  CONSTRAINT `SHARING_FILE_FK` FOREIGN KEY (`FILE_ID`) REFERENCES `files` (`FILE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SHARING_USER_BY_FK` FOREIGN KEY (`USER_ID_BY`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `SHARING_USER_TO_FK` FOREIGN KEY (`USER_ID_TO`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharing`
--

LOCK TABLES `sharing` WRITE;
/*!40000 ALTER TABLE `sharing` DISABLE KEYS */;
/*!40000 ALTER TABLE `sharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` text,
  `PASSWORD` text,
  `SALT` text,
  `FIRST_NAME` text,
  `LAST_NAME` text,
  `EMAIL` text,
  `ROLE_ID` tinyint(4) DEFAULT NULL,
  `IS_APPROVED` bit(1) DEFAULT NULL,
  `LOGIN_ATTEMPTS` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ROLE_FK_idx` (`ROLE_ID`),
  CONSTRAINT `ROLE_FK` FOREIGN KEY (`ROLE_ID`) REFERENCES `roles` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_dept`
--

DROP TABLE IF EXISTS `user_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_dept` (
  `user_id` bigint(20) NOT NULL,
  `dept_id` tinyint(4) NOT NULL,
  PRIMARY KEY (`user_id`,`dept_id`),
  KEY `user_fk_idx` (`user_id`),
  KEY `user_fk_idx1` (`user_id`),
  KEY `user_drpt_dept_fk_idx` (`dept_id`),
  CONSTRAINT `user_dept_dept_fk` FOREIGN KEY (`dept_id`) REFERENCES `department` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_dept_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_dept`
--

LOCK TABLES `user_dept` WRITE;
/*!40000 ALTER TABLE `user_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versioning`
--

DROP TABLE IF EXISTS `versioning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versioning` (
  `FILE_ID` bigint(20) NOT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `TIMESTAMP` timestamp NULL DEFAULT NULL,
  `MOD_USER_ID` bigint(20) DEFAULT NULL,
  `versioningcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`FILE_ID`),
  KEY `VERSIONING_FILE_FK_idx` (`FILE_ID`),
  CONSTRAINT `VERSIONING_FILE_FK` FOREIGN KEY (`FILE_ID`) REFERENCES `files` (`FILE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versioning`
--

LOCK TABLES `versioning` WRITE;
/*!40000 ALTER TABLE `versioning` DISABLE KEYS */;
/*!40000 ALTER TABLE `versioning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `write_protection`
--

DROP TABLE IF EXISTS `write_protection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `write_protection` (
  `USER_ID` bigint(20) NOT NULL,
  `FILE_ID` bigint(20) DEFAULT NULL,
  `VERSION` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `USER_FK_idx` (`USER_ID`),
  KEY `WRITE_PROTECTION_FILE_FK_idx` (`FILE_ID`),
  CONSTRAINT `WRITE_PROTECTION_USER_FK` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `WRITE_PROTECTION_FILE_FK` FOREIGN KEY (`FILE_ID`) REFERENCES `files` (`FILE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `write_protection`
--

LOCK TABLES `write_protection` WRITE;
/*!40000 ALTER TABLE `write_protection` DISABLE KEYS */;
/*!40000 ALTER TABLE `write_protection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-02 11:19:19
