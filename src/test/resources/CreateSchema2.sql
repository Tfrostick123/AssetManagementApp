CREATE DATABASE `asset` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `asset` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `type` varchar(45) NOT NULL,
                         `description` varchar(255) NOT NULL,
                         `created_by` varchar(255) NOT NULL,
                         `created_date` datetime(6) NOT NULL,
                         `last_modified_date` datetime(6) NOT NULL,
                         `modified_by` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id_UNIQUE` (`id`),
                         UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `valuation` (
                             `id` int NOT NULL,
                             `date` datetime(6) NOT NULL,
                             `value` double NOT NULL,
                             `asset_id` int NOT NULL,
                             `created_by` varchar(255) DEFAULT NULL,
                             `created_date` timestamp NULL DEFAULT NULL,
                             `last_modified_date` timestamp NULL DEFAULT NULL,
                             `modified_by` varchar(255) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK3emuyk6kltp7v4psxygv19blc` (`asset_id`),
                             CONSTRAINT `FK3emuyk6kltp7v4psxygv19blc` FOREIGN KEY (`asset_id`) REFERENCES `asset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
