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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `clientId` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Уникальный номер(Синтетический ключ) Клиента, по которому он связывается с другими таблицами данной БД.',
  `clientName` varchar(45) NOT NULL COMMENT 'Имя Клиента.',
  `clientSurname` varchar(45) NOT NULL COMMENT 'Фамилия Клиента.',
  `clientLogin` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'Уникальный индификатор Клиента, используемый для авторизации в системе.',
  `clientPassword` text NOT NULL COMMENT 'Пароль Клиента, используемый для обеспечения безопасного доступа в систему.',
  `clientEmail` varchar(45) NOT NULL COMMENT 'Email адресс, использующийся для регистрации клиента в системе, оповещения о каких-либо событиях, а так же для востановления пароля.',
  `clientStatus` varchar(45) NOT NULL COMMENT 'Статус клиента в системе. Возможные статусы: Забанен, незабанен. Если на клиента наклыдавается бан, он не имеет возможности взаибодействовать с системой.',
  `clientPoint` decimal(10,2) DEFAULT '0.00' COMMENT 'Баллы Клиента, которые начисляются при заказе, используя данную систему. В случае если Клиент не забрал(оплатил) закаказ баллы снимаются, а клиент может быть заблокирован. Баллы могут использоваться в качестве бонусов(скидки).',
  PRIMARY KEY (`clientId`),
  UNIQUE KEY `IdClient_UNIQUE` (`clientId`),
  UNIQUE KEY `ClientLogin_UNIQUE` (`clientLogin`),
  UNIQUE KEY `E-mail_UNIQUE` (`clientEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='Данная таблица используется для хранения информации о Клиентах, зарегестрированных в системе.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (53,'Илья','Печуро','Client','4D26A5BAFD3AE19DA1C6E8D5A5B1FFDDD096411A','ilyapechuro991@gmail.com','active',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14 11:38:52
