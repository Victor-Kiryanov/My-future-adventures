-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               8.0.19 - MySQL Community Server - GPL
-- Операционная система:         Win64
-- HeidiSQL Версия:              11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Дамп структуры базы данных myfutureadventures
DROP DATABASE IF EXISTS `myfutureadventures`;
CREATE DATABASE IF NOT EXISTS `myfutureadventures` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `myfutureadventures`;

-- Дамп структуры для таблица myfutureadventures.applicationstatuses
DROP TABLE IF EXISTS `applicationstatuses`;
CREATE TABLE IF NOT EXISTS `applicationstatuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `TitleStatuses` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `ValuesStatuses` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы myfutureadventures.applicationstatuses: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `applicationstatuses` DISABLE KEYS */;
INSERT INTO `applicationstatuses` (`id`, `TitleStatuses`, `ValuesStatuses`) VALUES
	(1, 'Новий', 'NEWStatus'),
	(2, 'В процесі', 'INPROGRESS'),
	(3, 'Виповнено-оброблено', 'CLOSED');
/*!40000 ALTER TABLE `applicationstatuses` ENABLE KEYS */;

-- Дамп структуры для таблица myfutureadventures.clients
DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SurName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Tell` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `Profession` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_planets` int NOT NULL,
  `id_ApplicationStatuses` int NOT NULL,
  `id_Operators` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Operators` (`id_Operators`),
  KEY `id_planets` (`id_planets`),
  KEY `id_ApplicationStatuses` (`id_ApplicationStatuses`),
  CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`id_Operators`) REFERENCES `operators` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clients_ibfk_2` FOREIGN KEY (`id_planets`) REFERENCES `planets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `clients_ibfk_3` FOREIGN KEY (`id_ApplicationStatuses`) REFERENCES `applicationstatuses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы myfutureadventures.clients: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`id`, `Name`, `SurName`, `Tell`, `Profession`, `id_planets`, `id_ApplicationStatuses`, `id_Operators`) VALUES
	(3, 'Віктор', 'Пашко', '094 062 17 50', 'Інженер', 3, 3, 1),
	(4, 'Ольга', 'Пашко', '098 047 70 81', 'Космічний біолог', 3, 3, 1),
	(5, 'Phenix', 'fff', '095 062 17 10', 'fff', 6, 3, 1),
	(7, 'dgfhngng', 'nfngxfngnf', '067 700 80 91', 'dd', 1, 3, 1),
	(8, 'dgfhngnghyhh', 'nfngxfngnfhfhh', '067 700 80 93', 'dd', 1, 2, 2);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;

-- Дамп структуры для таблица myfutureadventures.operators
DROP TABLE IF EXISTS `operators`;
CREATE TABLE IF NOT EXISTS `operators` (
  `id` int NOT NULL AUTO_INCREMENT,
  `PIB` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `accessIdentifier` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы myfutureadventures.operators: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `operators` DISABLE KEYS */;
INSERT INTO `operators` (`id`, `PIB`, `accessIdentifier`) VALUES
	(1, 'Василько Іван Іванович', '456'),
	(2, 'Хомяк Олег Олегович', 'XOO223');
/*!40000 ALTER TABLE `operators` ENABLE KEYS */;

-- Дамп структуры для таблица myfutureadventures.planets
DROP TABLE IF EXISTS `planets`;
CREATE TABLE IF NOT EXISTS `planets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ValuesPlanets` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `Title` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Дамп данных таблицы myfutureadventures.planets: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `planets` DISABLE KEYS */;
INSERT INTO `planets` (`id`, `ValuesPlanets`, `Title`) VALUES
	(1, 'Venus', '1 Венера'),
	(2, 'Earth', '2 Земля'),
	(3, 'Mars', '3 Марс'),
	(4, 'Mercury', '4 Меркурій'),
	(5, 'Neptune', '5 Нептун'),
	(6, 'Uranus', '6 Уран');
/*!40000 ALTER TABLE `planets` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
