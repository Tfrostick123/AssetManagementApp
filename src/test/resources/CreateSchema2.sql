DROP TABLE `ASSET`;
CREATE TABLE `ASSET` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `type` varchar(45) NOT NULL,
                         `description` varchar(255) NOT NULL,
                         `created_by` varchar(255) NOT NULL,
                         `created_date` datetime(6) NOT NULL,
                         `last_modified_date` datetime(6) NOT NULL,
                         `modified_by` varchar(255) NOT NULL
);
