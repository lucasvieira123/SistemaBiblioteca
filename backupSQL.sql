CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `aluguellivro`
--

DROP TABLE IF EXISTS `aluguellivro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluguellivro` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_livro` int(11) NOT NULL,
  `matricula_aluno` int(11) NOT NULL,
  `data_inicial` datetime NOT NULL,
  `data_final` datetime NOT NULL,
  `codigo_funcionario` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `b_idx` (`matricula_aluno`),
  KEY `chave_estrangeira_codigo_livro_idx` (`codigo_livro`),
  CONSTRAINT `chave_estrangeira_codigo_aluno` FOREIGN KEY (`matricula_aluno`) REFERENCES `aluno` (`matricula`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `chave_estrangeira_codigo_livro` FOREIGN KEY (`codigo_livro`) REFERENCES `livro` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluguellivro`
--

LOCK TABLES `aluguellivro` WRITE;
/*!40000 ALTER TABLE `aluguellivro` DISABLE KEYS */;
INSERT INTO `aluguellivro` VALUES (2,1,1,'0010-12-12 00:00:00','0010-12-12 00:00:00',2),(3,1,1,'0016-11-01 00:00:00','0016-11-01 00:00:00',1),(4,1,1,'0016-11-01 00:00:00','0016-11-01 00:00:00',1),(5,1,1,'2010-12-12 00:00:00','2010-12-12 00:00:00',1),(7,1,1,'2017-09-13 00:00:00','2017-09-13 00:00:00',1),(8,1,1,'2017-09-13 00:00:00','2017-09-13 00:00:00',1),(9,21,1,'2017-09-13 00:00:00','2017-09-13 00:00:00',1),(10,21,1,'2017-09-13 00:00:00','2017-09-13 00:00:00',1);
/*!40000 ALTER TABLE `aluguellivro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `matricula` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_nascimento` datetime NOT NULL,
  `endereco` varchar(45) NOT NULL,
  PRIMARY KEY (`matricula`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'nome1','2017-11-30 00:00:00','teste endereco'),(43,'qwe','2012-12-12 00:00:00','fzdcv'),(44,'fghfh','1996-01-26 00:00:00','hikhp'),(45,'fghfh','1996-01-26 00:00:00','hikhp'),(46,'ds','2012-12-12 00:00:00','sdasads'),(47,'ds','2012-12-12 00:00:00','sdasads'),(48,'ds','2012-12-12 00:00:00','sdasads'),(49,'ds','2012-12-12 00:00:00','sdasads'),(50,'ds','2012-12-12 00:00:00','sdasads'),(51,'ds','2012-12-12 00:00:00','sdasads'),(52,'ds','2012-12-12 00:00:00','sdasads'),(53,'ds','2012-12-12 00:00:00','sdasads'),(54,'ds','2012-12-12 00:00:00','sdasads'),(55,'ds','2012-12-12 00:00:00','sdasads'),(56,'ds','2012-12-12 00:00:00','sdasads');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Lucas'),(2,'Lucas2'),(3,'Lucas2'),(4,'Lucas2'),(5,'Lucas2'),(6,'Lucas2');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livro`
--

DROP TABLE IF EXISTS `livro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `livro` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `autor` varchar(45) NOT NULL,
  `edicao` varchar(45) NOT NULL,
  `editora` varchar(45) NOT NULL,
  `quantidadeexemplares` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livro`
--

LOCK TABLES `livro` WRITE;
/*!40000 ALTER TABLE `livro` DISABLE KEYS */;
INSERT INTO `livro` VALUES (1,'nome1','testeAutor','testeEdicao ','ghngh',NULL),(2,'nome2','testeAutor','testeEdicao','testeEdicao',NULL),(21,'Joao','asas','asas','asas',12);
/*!40000 ALTER TABLE `livro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-03 21:24:37
