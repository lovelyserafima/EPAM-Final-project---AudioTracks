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
  `available` BIT NULL DEFAULT 1,
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
  `user_id` INT NOT NULL,
  `audiotrack_id` BIGINT NOT NULL,
  `available` BIT NULL DEFAULT 1,
  INDEX `fk_Basket_User1_idx` (`user_id` ASC),
  INDEX `fk_Basket_AudioTrack1_idx` (`audiotrack_id` ASC),
  PRIMARY KEY (`user_id`, `audiotrack_id`),
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


-- -----------------------------------------------------
-- Table `AudioTracks`.`Reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AudioTracks`.`Reply` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `audio_id` BIGINT NOT NULL,
  `text` TINYTEXT NOT NULL,
  INDEX `ReplyUserFK_idx` (`user_id` ASC),
  INDEX `ReplyAudioTrackFK_idx` (`audio_id` ASC),
  PRIMARY KEY (`id`, `user_id`, `audio_id`),
  CONSTRAINT `ReplyUserFK`
    FOREIGN KEY (`user_id`)
    REFERENCES `AudioTracks`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ReplyAudioTrackFK`
    FOREIGN KEY (`audio_id`)
    REFERENCES `AudioTracks`.`AudioTrack` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (2, 'lovelyarchimond', '39e1338abe9ff12b91ac51f611db4dcc', 'client', 'Артур', 'Люп', 'artur_lyup@mail.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (3, 'grashik', 'ab37c109ac9ed0c44e27e6213b5634d0', 'client', 'Александр', 'Гращенко', 'grashik@yandex.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (4, 'nkirpich', 'cdef0faccb0ae652a3b4e2676cced891', 'client', 'Кирилл', 'Пинчуков', 'nkirpich@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (5, 'seryj', 'fa54e61e8eb0076bc2eaab8322a7f997', 'client', 'Сергей', 'Матюшенок', 'seryj@mail.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (6, 'lizachapuch', '5b89cb1c1d47577277705acc89ee3d31', 'client', 'Елизавета', 'Чапего', 'lizachapuch@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (7, 'ann.krikun', '7341ac65b444121f9e4f4ba5558a07ce', 'client', 'Анна', 'Крикун', 'annkrikun@yandex.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (8, 'ann.artamonova', '0e818cbafe95b0643021f7dd07443c08', 'client', 'Анна', 'Артамонова', 'annartamonova@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (9, 'pavel.zhur', '0beaf2b7ea49c561f59f11e106eda984', 'client', 'Pavel', 'Zhur', 'pavelzhur@bsu.by');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (10, 'igor.blinov', '2f546092277dc2e12f7935816aba3a8c', 'client', 'Igor', 'Blinov', 'blinov@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (11, 'darya', '1f7ba779631452529365b2b0bca89f57', 'client', 'Дарья', 'Терещенко', 'darya@gmail.com');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (12, 'maxim', 'dcaf27f9ceecb076fb859a3a7db5d8c0', 'client', 'Максим', 'Воробьев', 'sir.feterman@mail.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (13, 'edvin', '7d201c65143d8658d1fb8ab62471a1cf', 'client', 'Эдвин', 'Ганбарипур', 'edvin@yandex.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (14, 'kazah', 'b87c3a2ffae4ca5b56f42a30948c3ad3', 'client', 'Рустем', 'Кельменбетов', 'rustem@mail.ru');
INSERT INTO `AudioTracks`.`User` (`id`, `login`, `password`, `role`, `first_name`, `second_name`, `email`) VALUES (15, 'Klichko', 'ebad5102ddadce60660c14c2355b62c5', 'client', 'Максим', 'Личко', 'klichko@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `AudioTracks`.`Client`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (2, false, 600);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (3, true, 1000);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (4, true, 200);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (5, false, 300);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (6, false, 800);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (7, false, 1200);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (8, false, 2000);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (9, false, 1800);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (10, false, 1500);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (11, false, 400);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (12, true, 300);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (13, false, 1000);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (14, false, 400);
INSERT INTO `AudioTracks`.`Client` (`user_id`, `bonus`, `money`) VALUES (15, false, 5000);

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
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (6, 'A Beautiful Lie', '30 Seconds to Mars', 2005, 1700);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (7, 'Get a Grip', 'Aerosmith', 1993, 1500);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (8, 'Hotel California', 'Eagles', 1976, 2300);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (9, 'Nevermind', 'Nirvana', 1991, 1600);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (10, 'News of the World', 'Queen', 1977, 2000);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (11, 'Love At First Sting', 'Scorpions', 1984, 1850);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (12, 'Прекрасная любовь', 'ДДТ', 2007, 1600);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (13, 'Без консервантов', 'Lumen', 2003, 1400);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (14, 'Нажми на газ', 'Сектор газа', 1993, 1000);
INSERT INTO `AudioTracks`.`Album` (`id`, `name`, `artist`, `year`, `price`) VALUES (15, 'Stop!', 'Sam Brown', 1988, 1800);

COMMIT;


-- -----------------------------------------------------
-- Data for table `AudioTracks`.`AudioTrack`
-- -----------------------------------------------------
START TRANSACTION;
USE `AudioTracks`;
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (1, 1, NULL, 'The Mountain', 'Three Days Grace', 2018, 100, '/assets/audio/full/three-days-grace-the-mountain.mp3', '/assets/audio/demo/three_days_grace_-_the_mountain.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (2, 2, NULL, 'Линия Жизни', 'Сплин', 2001, 100, '/assets/audio/full/Сплин–ЛинияЖизни(OSTБрат2).mp3', '/assets/audio/demo/2676.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (3, 3, NULL, 'The Vengeful One', 'Disturbed', 2015, 150, '/assets/audio/full/Disturbed-TheVengefulOne.mp3', '/assets/audio/demo/disturbed-the-vengeful-one.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (4, 4, NULL, 'The Lost Song', 'The Cat Empire', 2003, 130, '/assets/audio/full/TheCatEmpire–TheLostSong(OSTКухня).mp3', '/assets/audio/demo/the-cat-empire-the-lost-song.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (5, 5, NULL, 'Hero', 'Skillet', 2009, 160, '/assets/audio/full/skillet-hero.mp3', '/assets/audio/demo/skillet-hero.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (6, 6, NULL, 'Kill', '30 Seconds to Mars', 2005, 150, '/assets/audio/full/30-seconds-to-mars-the-kill-bury-me.mp3', '/assets/audio/demo/30-Seconds_to_Mars-Kill(demo).mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (7, 7, NULL, 'Crazy', 'Aerosmith', 1993, 160, '/assets/audio/full/Аэросмит–Crazy.mp3', '/assets/audio/demo/Aerosmith-Crazy.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (8, 8, NULL, 'Hotel California', 'Eagles', 1976, 200, '/assets/audio/full/eagles-hotel-california.mp3', '/assets/audio/demo/Eagles-Hotel_California.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (9, 9, NULL, 'Smells like teen spirit', 'Nirvana', 1991, 250, '/assets/audio/full/Nirvana—Smells_Like_Teen_Spirit.mp3', '/assets/audio/demo/Nirvana-Smells_like_teen_spirit.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (10, 10, NULL, 'We Are The Champions', 'Queen', 1977, 180, '/assets/audio/full/queen_-_we-are-the-champions.mp3', '/assets/audio/demo/Queen-Champions.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (11, 11, NULL, 'Still loving you', 'Scorpions', 1984, 190, '/assets/audio/full/scorpions_-_still-loving-you.mp3', '/assets/audio/demo/Scorpions-Still_loving_you.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (12, 12, NULL, 'Новая жизнь', 'ДДТ', 2007, 170, '/assets/audio/full/ДДТ–Новая_жизнь.mp3', '/assets/audio/demo/ДДТ-Новая_жизнь.mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (13, 13, NULL, 'Сид и Нэнси', 'Lumen', 2003, 140, '/assets/audio/full/Lumen–Сид_и_Нэнси.mp3', '/assets/audio/demo/Люмен-Сид_и_нэнси(демо).mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (14, 14, NULL, 'Лирика', 'Сектор газа', 1993, 135, '/assets/audio/full/Сектор_газа-Лирика.mp3', '/assets/audio/demo/Сектор_газа-Лирика(демо).mp3', NULL);
INSERT INTO `AudioTracks`.`AudioTrack` (`id`, `album_id`, `assembly_id`, `name`, `artist`, `year`, `price`, `full_audio_path`, `demo_audio_path`, `available`) VALUES (15, 15, NULL, 'Stop', 'Sam Brown', 1988, 200, '/assets/audio/full/Sam_Brown-Stop', '/assets/audio/demo/Sam_Brown-Stop(demo).mp3', NULL);

COMMIT;

