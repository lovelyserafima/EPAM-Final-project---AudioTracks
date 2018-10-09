SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `AudioTracks` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `AudioTracks` ;

-- -----------------------------------------------------
-- Table `AudioTracks`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(35) NOT NULL,
  `role` ENUM('admin', 'client') NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `second_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AudioTracks`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`Client` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `bonus` TINYINT(1) NOT NULL DEFAULT false,
  `money` DECIMAL NULL DEFAULT 500,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `ClientUserFK`
    FOREIGN KEY (`user_id`)
    REFERENCES `AudioTracks`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AudioTracks`.`Album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`Album` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `artist` VARCHAR(45) NOT NULL,
  `year` SMALLINT NOT NULL,
  `price` DECIMAL NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AudioTracks`.`AudioTrack`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`AudioTrack` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `album_id` BIGINT NULL,
  `assembly_id` INT NULL,
  `name` VARCHAR(45) NOT NULL,
  `artist` VARCHAR(45) NOT NULL,
  `year` SMALLINT NOT NULL,
  `price` DECIMAL NOT NULL,
  `full_audio_path` VARCHAR(1000) NOT NULL,
  `demo_audio_path` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_AudioTrack_Album1_idx` (`album_id` ASC),
  CONSTRAINT `fk_AudioTrack_Album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `AudioTracks`.`Album` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AudioTracks`.`Basket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`Basket` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `audiotrack_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Basket_User1_idx` (`user_id` ASC),
  INDEX `fk_Basket_AudioTrack1_idx` (`audiotrack_id` ASC),
  CONSTRAINT `fk_Basket_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `AudioTracks`.`User` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Basket_AudioTrack1`
    FOREIGN KEY (`audiotrack_id`)
    REFERENCES `AudioTracks`.`AudioTrack` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AudioTracks`.`MediaLibrary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`MediaLibrary` (
  `audio_id` BIGINT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`audio_id`, `user_id`),
  INDEX `fk_User_has_AudioTrack_AudioTrack1_idx` (`audio_id` ASC),
  INDEX `fk_User_has_AudioTrack_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_User_has_AudioTrack_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `AudioTracks`.`User` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_User_has_AudioTrack_AudioTrack1`
    FOREIGN KEY (`audio_id`)
    REFERENCES `AudioTracks`.`AudioTrack` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `AudioTracks`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (1, 'lovelyserafima', '1fa7693dcabc9b4a6afb15775f407d38', 'admin', 'Arthur', 'Lyup', 'arturlyup@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (2, 'grashik', '5ceebd11c61269c604fe5b17e38e8cec', 'client', 'Alexander', 'Grashenko', 'grashik@mail.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (3, 'nkirpich', '8b33503a73a74eb7d6419a96991ee701', 'client', 'Kirpich', 'Niggerashvili', 'nkirpich@tut.by');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (4, 'seryj', '02aa855472fa145dece7dd1c4ea01248', 'client', 'Sergey', 'Matyoshonok', 'seryj@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (5, 'your_son', 'e8ab048d4fcb01075f58275792724d11', 'client', 'John', 'Lennon', 'your_son@mail.ru');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AudioTracks`.`Client`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (2, false, NULL);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (3, true, NULL);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (4, true, NULL);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (5, false, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `AudioTracks`.`Album`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (1, 'Outsider', 'Three Days Grace', 2018, 1500);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (2, '25-й кадр', 'Сплин', 2001, 1400);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (3, 'Immortalized', 'Disturbed', 2015, 1600);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (4, 'The Cat Empire', 'The Cat Empire', 2003, 1300);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (5, 'Awake', 'Skillet', 2009, 1600);

COMMIT;


-- -----------------------------------------------------
-- Data for table `AudioTracks`.`AudioTrack`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`) VALUES (1, 1, NULL, 'The Mountain', 'Three Days Grace', 2018, 100, '/assets/audio/full/three-days-grace-the-mountain.mp3', '/assets/audio/demo/three_days_grace_-_the_mountain.mp3');
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`) VALUES (2, 2, NULL, 'Линия Жизни', 'Сплин', 2001, 100, '/assets/audio/full/Сплин–ЛинияЖизни(OSTБрат2).mp3', '/assets/audio/demo/2676.mp3');
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`) VALUES (3, 3, NULL, 'The Vengeful One', 'Disturbed', 2015, 150, '/assets/audio/full/Disturbed%20-%20The%20Vengeful%20One(playvk.com).mp3', '/assets/audio/demo/disturbed-the-vengeful-one.mp3');
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`) VALUES (4, 4, NULL, 'The Lost Song', 'The Cat Empire', 2003, 130, '/assets/audio/full/TheCatEmpire–TheLostSong(OSTКухня).mp3', '/assets/audio/demo/the-cat-empire-the-lost-song.mp3');
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`) VALUES (5, 5, NULL, 'Hero', 'Skillet', 2009, 160, '/assets/audio/full/skillet-hero.mp3', '/assets/audio/demo/skillet-hero.mp3');

COMMIT;

