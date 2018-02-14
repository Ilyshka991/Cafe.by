-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cafeby
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Уникальный индификационный номер(Синтетический ключ) Продукта, использующийся для связи таблицы Меню с другими таблицами.',
  `productType` varchar(45) NOT NULL COMMENT 'Тип продука. Например, напиток, пицца...',
  `productNameRu` varchar(45) NOT NULL COMMENT 'Название блюда.',
  `productWeight` int(11) NOT NULL COMMENT 'Вес продукта.',
  `productCost` decimal(10,2) NOT NULL COMMENT 'Стоимость Продукта.',
  `productDescriptionRu` text COMMENT 'Описание продукта. Состав.',
  `productImage` tinytext NOT NULL COMMENT 'Путь для подгрузки изображения продукта.',
  `productNameEn` varchar(45) NOT NULL,
  `productDescriptionEn` text,
  PRIMARY KEY (`productId`),
  UNIQUE KEY `menuId_UNIQUE` (`productId`),
  UNIQUE KEY `product_prioductNameEn_uindex` (`productNameEn`),
  UNIQUE KEY `productNameRu_UNIQUE` (`productNameRu`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='Таблица Меню содержит информацию о доступных продектах, блюдах для заказа Пользователем.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (29,'juice','Яблочный компот',400,0.80,NULL,'apple.jpg','Apple compot',NULL),(30,'hotDrink','Чай',350,1.40,'','tea.jpg','Tea',''),(31,'soda','Фанта',250,1.20,'','fanta.jpg','Fanta',''),(33,'dessert','Тирамису',100,2.60,'','tiramisu.jpg','Tiramisu',''),(34,'hotDish','Стейк',500,18.00,'Варианты прожарки: средняя прожарка, полная прожарка','steak.jpg','Steak','Options for roasting: medium, well done'),(35,'bakeryProduct','Смаженка',150,1.10,'','smajenka.jpg','Smajenka',NULL),(36,'garnish','Рис',200,1.20,NULL,'rice.jpg','Rice',''),(37,'bakeryProduct','Пирог',450,7.40,'Начинка: Вишня, Клубника, Манго','pie.jpg','Pie','Filling: Cherry, Strawberry, Mango'),(38,'dessert','Мороженое',150,3.20,'Топинг: карамель, вишневый сироп ','ice-scream.jpg','Ice-scream','Topping: caramel, cherry syrup'),(39,'salad','Мимоза',220,1.60,'Состав: картофель, яйца, майонез, сыр, морковь','mimosa.jpg','Mimosa','Ingredients: potatoes, eggs, mayonnaise, cheese, carrots'),(40,'hotDrink','Кофе',250,3.50,'','coffe.jpg','Coffe',''),(41,'hotDish','Котлета по-киевски',120,1.85,NULL,'kiev.jpg','Tre Kiev`s cutlet',NULL),(42,'soda','Кока-Кола',500,1.10,'Попробуй... Почувствуй...','cola.jpg','Coca-cola','Taste The Feeling'),(43,'hotDish','Картошка',200,1.00,'С лучших ферм странны','bulba.jpg','Potato','From the best farms of the our country'),(44,'soup','Гороховый суп',350,1.20,'Очень вкусный и сытный суп','pea soup.jpg','Pea soup','Very well'),(46,'soup','Борщ',250,1.40,'','borsch.jpg','Borsch',NULL),(47,'juice','Томатный сок',250,0.56,'Только из свежих томатов','tomato juice.jpg','Tomato juice','Only fresh tomato'),(48,'water','Вода',200,0.25,'','water.jpg','Water','');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14 11:38:51
