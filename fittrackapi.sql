-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 07, 2023 at 08:12 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fittrackapi`
--
CREATE DATABASE IF NOT EXISTS `fittrackapi` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `fittrackapi`;

-- --------------------------------------------------------

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
CREATE TABLE IF NOT EXISTS `exercise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `exercise`
--

INSERT INTO `exercise` (`id`, `description`, `name`, `type`) VALUES
(1, 'Tricpes con polea alta', 'Triceps Polea', 'Weight x Reps'),
(2, 'Sentadilla tradicional con barra olímpica', 'Sentadilla', 'Weight x Reps'),
(3, 'Biceps con recorrido tradicional', 'Curl Biceps', 'Weight x Reps');

-- --------------------------------------------------------

--
-- Table structure for table `exercise_muscles`
--

DROP TABLE IF EXISTS `exercise_muscles`;
CREATE TABLE IF NOT EXISTS `exercise_muscles` (
  `exercise_id` bigint(20) NOT NULL,
  `muscles` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  KEY `FKdi7m9ollwk0g85xtdn5gp8t6v` (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `exercise_muscles`
--

INSERT INTO `exercise_muscles` (`exercise_id`, `muscles`) VALUES
(1, 'TRICEPS'),
(2, 'QUADRICEPS'),
(3, 'BICEPS');

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
CREATE TABLE IF NOT EXISTS `record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `routine_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36rn183dhc3qxgw6ig00beluq` (`routine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Table structure for table `record_exercise_list`
--

DROP TABLE IF EXISTS `record_exercise_list`;
CREATE TABLE IF NOT EXISTS `record_exercise_list` (
  `record_id` bigint(20) NOT NULL,
  `exercise_list_id` bigint(20) NOT NULL,
  KEY `FK829kiyw4vfbacth51owvxq206` (`exercise_list_id`),
  KEY `FKrjbqrgk7fvn5cae55f9952g8b` (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Table structure for table `routine`
--

DROP TABLE IF EXISTS `routine`;
CREATE TABLE IF NOT EXISTS `routine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `routine`
--

INSERT INTO `routine` (`id`, `description`, `name`) VALUES
(1, 'Para brazos', 'Brazo'),
(2, 'Para pierna', 'Pierna'),
(3, 'Para focalizar únicamente biceps', 'Biceps');

-- --------------------------------------------------------

--
-- Table structure for table `routine_exercise_list`
--

DROP TABLE IF EXISTS `routine_exercise_list`;
CREATE TABLE IF NOT EXISTS `routine_exercise_list` (
  `routine_id` bigint(20) NOT NULL,
  `exercise_list_id` bigint(20) NOT NULL,
  KEY `FKjm4mg2vvi773tv9y7m3ncstrj` (`exercise_list_id`),
  KEY `FKh4ax2914gy32jbbhpcxsvbpe9` (`routine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `routine_exercise_list`
--

INSERT INTO `routine_exercise_list` (`routine_id`, `exercise_list_id`) VALUES
(1, 1),
(2, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `genre` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `height` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `weight` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `age`, `genre`, `height`, `name`, `password`, `surname`, `username`, `weight`) VALUES
(1, 0, NULL, 0, 'Ales', '$2a$10$4mXgr6d11l5ytxqLhh1AjuzC/t3l/cMLCqHs3GzdQNFb2TrTWV0Ra', 'Antelo', 'Ales', 0),
(2, 0, NULL, 0, 'John', '$2a$10$EX.B6NvgaMYVWZQJ94Wk0eSmJhioe1igiFbxlV32TCqABOX5yrXnC', 'Doe', 'john', 0),
(3, 0, NULL, 0, 'Jane', '$2a$10$pznpNqPzcAx4puKP1mjvQOsZbpKty2eZFxMCKXyZAnzlYvF6/krjK', 'Doe', 'jane', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_records`
--

DROP TABLE IF EXISTS `user_records`;
CREATE TABLE IF NOT EXISTS `user_records` (
  `user_id` bigint(20) NOT NULL,
  `records_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_kgnlh0dfejf2cmil2qaau3q6l` (`records_id`),
  KEY `FKqot29pwes1c3e16eau5sc0om6` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_routine`
--

DROP TABLE IF EXISTS `user_routine`;
CREATE TABLE IF NOT EXISTS `user_routine` (
  `user_id` bigint(20) NOT NULL,
  `routine_id` bigint(20) NOT NULL,
  KEY `FK8ft9mwytg2ygd2u7xalvo8cns` (`routine_id`),
  KEY `FKtes1m4ftokae8l8e0tuu21eme` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `user_routine`
--

INSERT INTO `user_routine` (`user_id`, `routine_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE IF NOT EXISTS `user_type` (
  `user_id` bigint(20) NOT NULL,
  `type` varchar(255) COLLATE utf8_spanish2_ci DEFAULT NULL,
  KEY `FKj0whdmtccunmsfxctomsgp1vn` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`user_id`, `type`) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'USER');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `exercise_muscles`
--
ALTER TABLE `exercise_muscles`
  ADD CONSTRAINT `FKdi7m9ollwk0g85xtdn5gp8t6v` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`);

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `FK36rn183dhc3qxgw6ig00beluq` FOREIGN KEY (`routine_id`) REFERENCES `routine` (`id`);

--
-- Constraints for table `record_exercise_list`
--
ALTER TABLE `record_exercise_list`
  ADD CONSTRAINT `FK829kiyw4vfbacth51owvxq206` FOREIGN KEY (`exercise_list_id`) REFERENCES `exercise` (`id`),
  ADD CONSTRAINT `FKrjbqrgk7fvn5cae55f9952g8b` FOREIGN KEY (`record_id`) REFERENCES `record` (`id`);

--
-- Constraints for table `routine_exercise_list`
--
ALTER TABLE `routine_exercise_list`
  ADD CONSTRAINT `FKh4ax2914gy32jbbhpcxsvbpe9` FOREIGN KEY (`routine_id`) REFERENCES `routine` (`id`),
  ADD CONSTRAINT `FKjm4mg2vvi773tv9y7m3ncstrj` FOREIGN KEY (`exercise_list_id`) REFERENCES `exercise` (`id`);

--
-- Constraints for table `user_records`
--
ALTER TABLE `user_records`
  ADD CONSTRAINT `FK1nhvbm8is4ceqj8vh4wj2r9v2` FOREIGN KEY (`records_id`) REFERENCES `record` (`id`),
  ADD CONSTRAINT `FKqot29pwes1c3e16eau5sc0om6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_routine`
--
ALTER TABLE `user_routine`
  ADD CONSTRAINT `FK8ft9mwytg2ygd2u7xalvo8cns` FOREIGN KEY (`routine_id`) REFERENCES `routine` (`id`),
  ADD CONSTRAINT `FKtes1m4ftokae8l8e0tuu21eme` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_type`
--
ALTER TABLE `user_type`
  ADD CONSTRAINT `FKj0whdmtccunmsfxctomsgp1vn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
