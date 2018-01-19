-- ------------
-- create test1
-- ------------

CREATE DATABASE test1;

USE test1;

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `age` int(11) NOT NULL,
  `classId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `student` VALUES ('1', '张三', '18', '1');
INSERT INTO `student` VALUES ('2', '李四', '19', '2');

-- ------------
-- create test2
-- ------------

CREATE DATABASE test2;

USE test2;

DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL,
  `class_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `class` VALUES ('1', '高一<1>班');
INSERT INTO `class` VALUES ('2', '高一<2>班');