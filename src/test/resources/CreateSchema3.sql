DROP TABLE VALUATION;
CREATE TABLE `VALUATION` (
                             `id` int NOT NULL,
                             `date` datetime(6) NOT NULL,
                             `value` double NOT NULL,
                             `asset_id` int NOT NULL,
                             `created_by` varchar(255) DEFAULT NULL,
                             `created_date` timestamp NULL DEFAULT NULL,
                             `last_modified_date` timestamp NULL DEFAULT NULL,
                             `modified_by` varchar(255) DEFAULT NULL
);
