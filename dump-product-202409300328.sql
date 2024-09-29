-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: product
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `categories`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2024-09-30 02:45:11.872000','Home & Decor'),(2,'2024-09-30 02:45:31.413000','Clothing'),(3,'2024-09-30 02:45:43.299000','Accessories'),(4,'2024-09-30 02:45:52.015000','Outdoor'),(5,'2024-09-30 02:46:21.046000','T-shirt');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_seq`
--

LOCK TABLES `categories_seq` WRITE;
/*!40000 ALTER TABLE `categories_seq` DISABLE KEYS */;
INSERT INTO `categories_seq` VALUES (101);
/*!40000 ALTER TABLE `categories_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,NULL,'Green'),(2,NULL,'Violen'),(3,NULL,'Red'),(4,NULL,'Black');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color_seq`
--

LOCK TABLES `color_seq` WRITE;
/*!40000 ALTER TABLE `color_seq` DISABLE KEYS */;
INSERT INTO `color_seq` VALUES (101);
/*!40000 ALTER TABLE `color_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL,
  `id_product` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKebv5p4e8gjysj4vdgkom261ru` (`id_product`),
  CONSTRAINT `FKebv5p4e8gjysj4vdgkom261ru` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (4,'2024-09-30 03:04:18.931000','D:\\EntranceTest\\imageProduct\\product3.jpg',2),(5,'2024-09-30 03:04:18.939000','D:\\EntranceTest\\imageProduct\\product4.jpg',2),(6,'2024-09-30 03:05:14.463000','D:\\EntranceTest\\imageProduct\\product5.jpg',3),(7,'2024-09-30 03:05:14.470000','D:\\EntranceTest\\imageProduct\\product6.jpg',3),(8,'2024-09-30 03:05:14.477000','D:\\EntranceTest\\imageProduct\\product7.jpg',3),(9,'2024-09-30 03:05:14.483000','D:\\EntranceTest\\imageProduct\\product8.jpg',3),(10,'2024-09-30 03:05:39.988000','D:\\EntranceTest\\imageProduct\\product5.jpg',4),(11,'2024-09-30 03:05:39.995000','D:\\EntranceTest\\imageProduct\\product6.jpg',4),(12,'2024-09-30 03:05:40.003000','D:\\EntranceTest\\imageProduct\\product7.jpg',4),(13,'2024-09-30 03:05:40.012000','D:\\EntranceTest\\imageProduct\\product8.jpg',4),(16,'2024-09-30 03:09:02.780000','D:\\EntranceTest\\imageProduct\\product3.jpg',1),(17,'2024-09-30 03:09:02.782000','D:\\EntranceTest\\imageProduct\\product4.jpg',1),(20,'2024-09-30 03:11:11.592000','D:\\EntranceTest\\imageProduct\\product9.jpg',7),(21,'2024-09-30 03:11:11.600000','D:\\EntranceTest\\imageProduct\\product10.jpg',7);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_seq`
--

LOCK TABLES `image_seq` WRITE;
/*!40000 ALTER TABLE `image_seq` DISABLE KEYS */;
INSERT INTO `image_seq` VALUES (101);
/*!40000 ALTER TABLE `image_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `id_category` int DEFAULT NULL,
  `id_style` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7n4kn48devertuyrehvnnn2ch` (`id_category`),
  KEY `FKkh4jobagqh1j0r5g5ka6xma6u` (`id_style`),
  CONSTRAINT `FK7n4kn48devertuyrehvnnn2ch` FOREIGN KEY (`id_category`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKkh4jobagqh1j0r5g5ka6xma6u` FOREIGN KEY (`id_style`) REFERENCES `style` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2024-09-30 02:59:03.540000','Product 1 update','Product Name 1',100,NULL,'2024-09-30 03:09:02.752000',1,2),(2,'2024-09-30 03:04:18.887000','description Product name 2','Product name 2',11000,NULL,NULL,1,2),(3,'2024-09-30 03:05:14.431000','description Product name 3','Product name 3',11200,NULL,NULL,3,4),(4,'2024-09-30 03:05:39.955000','description Product name 4','Product name 4',14110,NULL,NULL,1,2),(7,'2024-09-30 03:11:11.550000','description Product name 5','Product name 5',17110,NULL,NULL,3,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_color_size`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_color_size` (
  `id_color` int NOT NULL,
  `id_product` int NOT NULL,
  `id_size` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `quantity` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_color`,`id_product`,`id_size`),
  KEY `FK77utvjm2o39h86bjre2tp6piu` (`id_product`),
  KEY `FK5ojll46ies6y1livyq1xx96x1` (`id_size`),
  CONSTRAINT `FK5ojll46ies6y1livyq1xx96x1` FOREIGN KEY (`id_size`) REFERENCES `size` (`id`),
  CONSTRAINT `FK77utvjm2o39h86bjre2tp6piu` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `FK9xyqsxccsliaytjbtiho4l3i1` FOREIGN KEY (`id_color`) REFERENCES `color` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_color_size`
--

LOCK TABLES `product_color_size` WRITE;
/*!40000 ALTER TABLE `product_color_size` DISABLE KEYS */;
INSERT INTO `product_color_size` VALUES (1,1,2,'2024-09-30 03:09:02.771000',10,NULL),(1,2,2,'2024-09-30 03:04:18.913000',1000,NULL),(1,4,3,'2024-09-30 03:05:39.972000',1000,NULL),(1,7,3,'2024-09-30 03:11:11.572000',700,NULL),(2,1,1,'2024-09-30 03:09:02.775000',5,NULL),(2,2,1,'2024-09-30 03:04:18.922000',1500,NULL),(2,3,3,'2024-09-30 03:05:14.448000',1000,NULL),(3,3,2,'2024-09-30 03:05:14.456000',1500,NULL),(3,4,2,'2024-09-30 03:05:39.979000',1500,NULL),(3,7,2,'2024-09-30 03:11:11.581000',800,NULL);
/*!40000 ALTER TABLE `product_color_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_seq`
--

LOCK TABLES `product_seq` WRITE;
/*!40000 ALTER TABLE `product_seq` DISABLE KEYS */;
INSERT INTO `product_seq` VALUES (101);
/*!40000 ALTER TABLE `product_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
INSERT INTO `size` VALUES (1,NULL,'S'),(2,NULL,'M'),(3,NULL,'L'),(4,NULL,'XL'),(5,NULL,'One size'),(6,NULL,'Custom');
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_seq`
--

LOCK TABLES `size_seq` WRITE;
/*!40000 ALTER TABLE `size_seq` DISABLE KEYS */;
INSERT INTO `size_seq` VALUES (101);
/*!40000 ALTER TABLE `size_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style` (
  `id` int NOT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style`
--

LOCK TABLES `style` WRITE;
/*!40000 ALTER TABLE `style` DISABLE KEYS */;
INSERT INTO `style` VALUES (1,'2024-09-30 02:53:21.251000','Modern'),(2,'2024-09-30 02:53:31.724000','Streetwear'),(3,'2024-09-30 02:53:40.669000','Colorfull'),(4,'2024-09-30 02:53:50.264000','Patchwork'),(5,'2024-09-30 02:54:06.380000','Bohemian'),(6,'2024-09-30 02:54:11.270000','Vintage');
/*!40000 ALTER TABLE `style` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `style_seq`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `style_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `style_seq`
--

LOCK TABLES `style_seq` WRITE;
/*!40000 ALTER TABLE `style_seq` DISABLE KEYS */;
INSERT INTO `style_seq` VALUES (101);
/*!40000 ALTER TABLE `style_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'product'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-30  3:28:38
