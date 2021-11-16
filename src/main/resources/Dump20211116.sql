CREATE DATABASE  IF NOT EXISTS `calcetto_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `calcetto_db`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: calcetto_db
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arena_table`
--

DROP TABLE IF EXISTS `arena_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arena_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `field_type` varchar(255) DEFAULT NULL,
  `is_outdoor` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arena_table`
--

LOCK TABLES `arena_table` WRITE;
/*!40000 ALTER TABLE `arena_table` DISABLE KEYS */;
INSERT INTO `arena_table` VALUES (1,'Terra',_binary '\0','Campo A',100),(2,'Ghiaccio',_binary '\0','Campo B',100);
/*!40000 ALTER TABLE `arena_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_table`
--

DROP TABLE IF EXISTS `match_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) DEFAULT NULL,
  `is_private` bit(1) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `arena_id` bigint DEFAULT NULL,
  `matchmaker_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjv4b6fqojs4249vxm7g2xaojy` (`arena_id`),
  KEY `FKl7pufd7f1ip6vn7cdxsblvg29` (`matchmaker_id`),
  CONSTRAINT `FKjv4b6fqojs4249vxm7g2xaojy` FOREIGN KEY (`arena_id`) REFERENCES `arena_table` (`id`),
  CONSTRAINT `FKl7pufd7f1ip6vn7cdxsblvg29` FOREIGN KEY (`matchmaker_id`) REFERENCES `player` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_table`
--

LOCK TABLES `match_table` WRITE;
/*!40000 ALTER TABLE `match_table` DISABLE KEYS */;
INSERT INTO `match_table` VALUES (1,'2021-12-12 19:00:00.000000',_binary '\0','active',1,3),(2,'2020-12-12 19:00:00.000000',_binary '\0','active',1,2),(3,'2021-12-14 19:00:00.000000',_binary '','active',1,1),(4,'2021-12-19 19:00:00.000000',_binary '\0','active',2,1);
/*!40000 ALTER TABLE `match_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` blob,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oivbimcon0iqmb8efpv723h08` (`email`),
  UNIQUE KEY `UK_639nyj2kh8to2bwtv0k9u7jlc` (`phone_number`),
  UNIQUE KEY `UK_o39xn8lmj05iew7d2tgw836jy` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'test@player.com','Pippo','$2a$12$TYXyiiXod78XbjWFtF9vsOwtalda05.A6eNkWXFEBaiAXOFzQuREq','3345676283',NULL,'Franco','Pippo.Franco56',_binary ''),(2,'test@admin.com','Admin','$2a$12$4QmOU0DuVqVfNSKW2vtgzeS5qtYPfVSSrChhyGA.2M4T70EdTNgd6','3356472823',NULL,'Admin','Admin',_binary ''),(3,'francesco.totti@player.com','Francesco','$2a$12$KGHb0uoPfUylc33mA4gxeu0.IrTBiX0hB521T.zS.jpDBCstvSssC','3346756234',NULL,'Totti','Francesco.Totti10',_binary '');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players_roles`
--

DROP TABLE IF EXISTS `players_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players_roles` (
  `player_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`player_id`,`role_id`),
  KEY `FKkpl22h274paoehdkg3ibrmbha` (`role_id`),
  CONSTRAINT `FK2uth2vxxckms5xt2fb3kfxyov` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`),
  CONSTRAINT `FKkpl22h274paoehdkg3ibrmbha` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players_roles`
--

LOCK TABLES `players_roles` WRITE;
/*!40000 ALTER TABLE `players_roles` DISABLE KEYS */;
INSERT INTO `players_roles` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `players_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'PLAYER'),(2,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) NOT NULL,
  `match_table_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK494hfa44insc8ef8yuwqlxls9` (`match_table_id`),
  CONSTRAINT `FK494hfa44insc8ef8yuwqlxls9` FOREIGN KEY (`match_table_id`) REFERENCES `match_table` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_player`
--

DROP TABLE IF EXISTS `team_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_player` (
  `id_team` bigint NOT NULL,
  `id_player` bigint NOT NULL,
  KEY `FKbjd3s8rge43jbfg44376scbr` (`id_player`),
  KEY `FKopxx54pb4dko8b9jytebv48fs` (`id_team`),
  CONSTRAINT `FKbjd3s8rge43jbfg44376scbr` FOREIGN KEY (`id_player`) REFERENCES `player` (`id`),
  CONSTRAINT `FKopxx54pb4dko8b9jytebv48fs` FOREIGN KEY (`id_team`) REFERENCES `team` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_player`
--

LOCK TABLES `team_player` WRITE;
/*!40000 ALTER TABLE `team_player` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_player` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-16 17:05:12
