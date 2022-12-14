-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: MyWeb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `announce`
--

DROP TABLE IF EXISTS `announce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announce` (
  `content` varchar(100) DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announce`
--

LOCK TABLES `announce` WRITE;
/*!40000 ALTER TABLE `announce` DISABLE KEYS */;
INSERT INTO `announce` VALUES ('567',0,'2022-12-18 14:59:02',10),('567',0,'2022-12-18 14:59:05',11),('567',0,'2022-12-18 14:59:06',12),('123',0,'2022-12-20 16:46:34',13),('123',0,'2022-12-20 16:47:27',14),('123',0,'2022-12-20 16:48:14',15),('123',0,'2022-12-20 16:48:40',16),('123',0,'2022-12-20 16:48:49',17),('123',0,'2022-12-20 16:49:09',18),('123',0,'2022-12-20 16:50:26',19),('123',0,'2022-12-20 16:50:48',20),('123',0,'2022-12-20 16:54:06',21),('123',0,'2022-12-20 16:56:37',22),('123',0,'2022-12-20 16:59:48',23),('123',0,'2022-12-20 17:08:59',24),('123',0,'2022-12-20 17:13:43',25),('123',0,'2022-12-20 17:14:23',26);
/*!40000 ALTER TABLE `announce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ano_comment`
--

DROP TABLE IF EXISTS `ano_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ano_comment` (
  `msg_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `message` tinyint(1) DEFAULT NULL,
  KEY `ano_comment_anonymous_message_id_fk` (`msg_id`),
  CONSTRAINT `ano_comment_anonymous_message_id_fk` FOREIGN KEY (`msg_id`) REFERENCES `anonymous_message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ano_comment`
--

LOCK TABLES `ano_comment` WRITE;
/*!40000 ALTER TABLE `ano_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `ano_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous_message`
--

DROP TABLE IF EXISTS `anonymous_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous_message` (
  `ano_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `message` tinyint(1) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `anonymous_message_anonymous_users_id_fk` (`ano_id`),
  CONSTRAINT `anonymous_message_anonymous_users_id_fk` FOREIGN KEY (`ano_id`) REFERENCES `anonymous_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous_message`
--

LOCK TABLES `anonymous_message` WRITE;
/*!40000 ALTER TABLE `anonymous_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `anonymous_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous_users`
--

DROP TABLE IF EXISTS `anonymous_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous_users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mac` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mac` (`mac`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous_users`
--

LOCK TABLES `anonymous_users` WRITE;
/*!40000 ALTER TABLE `anonymous_users` DISABLE KEYS */;
INSERT INTO `anonymous_users` VALUES (9,'12:34:56:78:89:12');
/*!40000 ALTER TABLE `anonymous_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `user_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `message` tinyint(1) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `message_users_id_fk` (`user_id`),
  CONSTRAINT `message_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (3,'123',0,'2022-12-16 22:49:39',1,1),(3,'123',0,'2022-12-16 22:51:45',0,2),(2,'AnoReplyTest',0,'2022-12-20 17:40:51',0,3),(2,'../AllMsg/Ano/Msg/4/20221220171423.txt',1,'2022-12-20 17:14:23',1,4),(2,'../AllMsg/Ano/Msg/4/20221220171343.txt',1,'2022-12-20 17:13:43',1,5),(2,'../AllMsg/Ano/Msg/4/20221220170859.txt',1,'2022-12-20 17:08:59',1,6),(2,'AnoReplyTest',0,'2022-12-20 17:40:51',0,7),(2,'../AllMsg/Usr/Msg/2/20221220171423.txt',1,'2022-12-20 17:14:23',1,8),(2,'../AllMsg/Usr/Msg/2/20221220171343.txt',1,'2022-12-20 17:13:43',1,9),(2,'../AllMsg/Usr/Msg/2/20221220170859.txt',1,'2022-12-20 17:08:59',1,10),(3,'123鍝堝搱',0,'2022-12-20 21:39:36',0,11),(2,'ins',0,'2022-12-26 20:18:28',1,12),(2,'ins',0,'2022-12-26 20:19:38',1,13),(2,'ins',0,'2022-12-26 20:20:16',1,14),(2,'ins',0,'2022-12-26 20:23:47',1,15),(2,'ins',0,'2022-12-26 20:23:47',1,16),(2,'ins',0,'2022-12-26 20:24:22',1,17),(2,'ins',0,'2022-12-26 20:24:22',1,18),(2,'ins',0,'2022-12-26 20:25:08',1,19),(2,'ins',0,'2022-12-26 20:25:08',1,20),(NULL,'ins',0,'2022-12-26 20:33:38',1,21),(NULL,'ins',0,'2022-12-26 20:33:38',1,22),(2,'ins',0,'2022-12-26 20:35:26',1,23),(3,'abc',0,'2022-12-27 12:38:00',1,25),(2,'???',0,'2022-12-27 12:00:00',0,26),(3,'# Hello World\n\n鍦ㄨ繖鐨勭暀瑷€鏄?*涓嶄細**璁╁叾浠栦汉鐪嬪埌鐨?,0,'2022-12-27 17:30:04',1,29),(3,'# ajlskdfmlkvasd\n**sakdflas**\n',0,'2022-12-27 18:09:58',1,30);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

# DROP TABLE IF EXISTS `reply`;
# /*!40101 SET @saved_cs_client     = @@character_set_client */;
# /*!50503 SET character_set_client = utf8mb4 */;
# CREATE TABLE `reply` (
#   `user_id` int DEFAULT NULL,
#   `mac` varchar(17) DEFAULT NULL,
#   `reply` varchar(100) DEFAULT NULL,
#   `local` tinyint(1) DEFAULT NULL,
#   `date` datetime DEFAULT NULL,
#   KEY `reply_users_id_fk` (`user_id`),
#   KEY `reply_anonymous_users_mac_fk` (`mac`),
#   CONSTRAINT `reply_anonymous_users_mac_fk` FOREIGN KEY (`mac`) REFERENCES `anonymous_users` (`mac`),
#   CONSTRAINT `reply_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
# /*!40101 SET character_set_client = @saved_cs_client */;
#
# --
# -- Dumping data for table `reply`
# --
#
# LOCK TABLES `reply` WRITE;
# /*!40000 ALTER TABLE `reply` DISABLE KEYS */;
# INSERT INTO `reply` VALUES (3,NULL,'test1',0,'2022-12-25 01:02:03'),(3,NULL,'test2',0,'2022-12-25 05:06:07'),(3,NULL,'test3',0,'2022-12-25 08:09:10');
# /*!40000 ALTER TABLE `reply` ENABLE KEYS */;
# UNLOCK TABLES;

--
-- Table structure for table `unread_for_ano`
--

DROP TABLE IF EXISTS `unread_for_ano`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unread_for_ano` (
  `ano_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  UNIQUE KEY `ano_id` (`ano_id`),
  CONSTRAINT `unread_for_ano_anonymous_users_id_fk` FOREIGN KEY (`ano_id`) REFERENCES `anonymous_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unread_for_ano`
--

LOCK TABLES `unread_for_ano` WRITE;
/*!40000 ALTER TABLE `unread_for_ano` DISABLE KEYS */;
/*!40000 ALTER TABLE `unread_for_ano` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unread_for_me`
--

DROP TABLE IF EXISTS `unread_for_me`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unread_for_me` (
  `ano_id` int DEFAULT NULL,
  `usr_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  UNIQUE KEY `ano_id` (`ano_id`),
  UNIQUE KEY `usr_id` (`usr_id`),
  CONSTRAINT `unread_for_me_anonymous_users_id_fk` FOREIGN KEY (`ano_id`) REFERENCES `anonymous_users` (`id`),
  CONSTRAINT `unread_for_me_users_id_fk` FOREIGN KEY (`usr_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unread_for_me`
--

LOCK TABLES `unread_for_me` WRITE;
/*!40000 ALTER TABLE `unread_for_me` DISABLE KEYS */;
INSERT INTO `unread_for_me` VALUES (NULL,3,9),(NULL,2,1);
/*!40000 ALTER TABLE `unread_for_me` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unread_for_usr`
--

DROP TABLE IF EXISTS `unread_for_usr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unread_for_usr` (
  `usr_id` int DEFAULT NULL,
  `num` int DEFAULT NULL,
  UNIQUE KEY `user_id` (`usr_id`),
  CONSTRAINT `unread_for_usr_users_id_fk` FOREIGN KEY (`usr_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unread_for_usr`
--

LOCK TABLES `unread_for_usr` WRITE;
/*!40000 ALTER TABLE `unread_for_usr` DISABLE KEYS */;
/*!40000 ALTER TABLE `unread_for_usr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `is_me` tinyint(1) DEFAULT NULL,
  `female` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Looouiiis','lyc020722',1,0),(2,'123','456',0,1),(3,'Looouiiise','lyc020722',0,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usr_comment`
--

DROP TABLE IF EXISTS `usr_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usr_comment` (
  `msg_id` int DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `local` tinyint(1) DEFAULT NULL,
  `message` tinyint(1) DEFAULT NULL,
  KEY `usr_comment_message_id_fk` (`msg_id`),
  CONSTRAINT `usr_comment_message_id_fk` FOREIGN KEY (`msg_id`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usr_comment`
--

LOCK TABLES `usr_comment` WRITE;
/*!40000 ALTER TABLE `usr_comment` DISABLE KEYS */;
INSERT INTO `usr_comment` VALUES (25,'text','2022-12-26 18:52:13',0,1),(25,'../AllCom/Usr/Com/25/20221226185353.txt','2022-12-26 18:53:53',1,1),(25,'../AllCom/Usr/Rep/25/20221226190504.txt','2022-12-26 19:05:04',1,0),(29,'## 閫佸埌浜嗙矇涓?lka','2022-12-27 18:05:32',0,1),(30,'123456789','2022-12-27 18:10:15',0,1);
/*!40000 ALTER TABLE `usr_comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-27 20:35:08
