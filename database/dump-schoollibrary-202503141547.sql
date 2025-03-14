-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: schoollibrary
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book_catalog`
--

DROP TABLE IF EXISTS `book_catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_catalog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `book_code` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `publish_date` date NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `book_catalog_unique` (`book_code`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_catalog`
--

LOCK TABLES `book_catalog` WRITE;
/*!40000 ALTER TABLE `book_catalog` DISABLE KEYS */;
INSERT INTO `book_catalog` VALUES (1,'The Great Adventure','BC1001','Fiction','John Doe','2022-03-15','HarperCollins',0),(2,'Science of Tomorrow update','BC1002','Science','Alice Smith','2021-06-10','Penguin Books',0),(3,'The Mystery Island','BC1003','Fiction','Robert Brown','2023-01-25','HarperCollins',0),(4,'History of the World','BC1004','History','Emily Johnson','2020-11-05','Oxford University Press',0),(5,'Fictional Reality','BC1005','Fiction','David White','2023-09-12','HarperCollins',0),(6,'Astrophysics Explained','BC1006','Science','Richard Newton','2022-08-18','Penguin Books',0),(7,'Ancient Civilizations','BC1007','History','Jessica Green','2019-07-20','Oxford University Press',0),(8,'Modern Science Discoveries','BC1008','Science','Michael Lee','2024-02-11','Penguin Books',0),(9,'Legends of the Past','BC1009','History','Sophia Clark','2018-12-30','Oxford University Press',0),(10,'Parallel Universes','BC1010','Science','Daniel Evans','2023-05-22','Penguin Books',0),(11,'Buku 1001 Kisah','BC1011','Fiction','Ali','2025-03-15','HarperCollins',1),(12,'101 kisah binatang','TEST-B-01','Fiction','Ali','2025-03-13','Kuda Tiga',1),(13,'Buku Fisika Update','TEST-B-02','Sejarah','Ali','2025-03-13','Kuda Tiga',1);
/*!40000 ALTER TABLE `book_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_rents`
--

DROP TABLE IF EXISTS `book_rents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_rents` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_code` varchar(100) NOT NULL,
  `student_number` varchar(100) NOT NULL,
  `rent_date` date NOT NULL,
  `due_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `status` enum('RENTED','RETURNED','OVERDUE') NOT NULL DEFAULT 'RENTED',
  PRIMARY KEY (`id`),
  KEY `book_rents_members_FK` (`student_number`),
  KEY `book_rents_book_catalog_FK` (`book_code`),
  CONSTRAINT `book_rents_book_catalog_FK` FOREIGN KEY (`book_code`) REFERENCES `book_catalog` (`book_code`),
  CONSTRAINT `book_rents_members_FK` FOREIGN KEY (`student_number`) REFERENCES `members` (`student_number`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_rents`
--

LOCK TABLES `book_rents` WRITE;
/*!40000 ALTER TABLE `book_rents` DISABLE KEYS */;
INSERT INTO `book_rents` VALUES (1,'BC1001','12-2024-A','2025-03-14','2025-03-21','2025-03-14','RETURNED'),(2,'BC1001','12-2024-A','2025-03-14','2025-03-21','2025-03-14','RETURNED'),(3,'BC1001','12-2024-A','2025-03-14','2025-03-21','2025-03-14','RETURNED');
/*!40000 ALTER TABLE `book_rents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `student_number` varchar(100) NOT NULL COMMENT 'NISN',
  `gender` enum('MALE','FEMALE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `members_unique` (`student_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Ali Kusnadin Update','12-2024-A','MALE',0),(2,'bagas subagas','TEST-12','MALE',1),(3,'Tony Update','TEST-2','MALE',1);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'schoollibrary'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-14 15:47:56
