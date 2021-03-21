-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema iiht
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `iiht` ;

-- -----------------------------------------------------
-- Schema iiht
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iiht` DEFAULT CHARACTER SET latin1 ;
USE `iiht` ;

-- -----------------------------------------------------
-- Table `iiht`.`user_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iiht`.`user_details` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NOT NULL,
  `date_of_birth` DATE NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `user_status` TINYINT(4) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `iiht`.`tweets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iiht`.`tweets` (
  `tweets_user_id` INT(11) NOT NULL,
  `tweet_id` INT(11) NOT NULL AUTO_INCREMENT,
  `tweet_details` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`tweet_id`),
  INDEX `tweets_user_id_idx` (`tweets_user_id` ASC),
  CONSTRAINT `tweets_user_id`
    FOREIGN KEY (`tweets_user_id`)
    REFERENCES `iiht`.`user_details` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
