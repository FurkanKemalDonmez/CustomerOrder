DROP SCHEMA IF EXISTS `Customer_Orders`;

CREATE SCHEMA `Customer_Orders`;

use `Customer_Orders`;

SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `Customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` INT DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE TABLE `Orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATE DEFAULT NULL,
  `total_price` DECIMAL(10, 2) DEFAULT NULL,
  `customer_id` INT NOT NULL,
  
  PRIMARY KEY (`id`),

  FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) 
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;