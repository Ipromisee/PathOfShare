/*
 Navicat Premium Data Transfer

 Source Server         : homework
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 01/12/2023 15:47:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blogs
-- ----------------------------
DROP TABLE IF EXISTS `blogs`;
CREATE TABLE `blogs`  (
  `blogId` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fromWho` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL,
  `visit` int NULL DEFAULT 0,
  `like` int NULL DEFAULT 0,
  PRIMARY KEY (`blogId`) USING BTREE,
  UNIQUE INDEX `blogId_UNIQUE`(`blogId` ASC) USING BTREE,
  INDEX `userId`(`userId` ASC) USING BTREE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs
-- ----------------------------
INSERT INTO `blogs` VALUES (13, 1, '\nthis is a blog\nfrom a common user\nthe class is commonBlog\n', 'user', '2023-11-15 19:41:32', 0, 0);
INSERT INTO `blogs` VALUES (14, 2, '\nthis is a blog\nfrom a manager\nthe class is managerBlog\n', 'manager', '2023-11-15 19:42:26', 0, 0);
INSERT INTO `blogs` VALUES (15, 3, '\nthis is a blog\nfrom a visitor\nthe class is visitorBlog\n', 'visitor', '2023-11-15 19:43:38', 0, 0);
INSERT INTO `blogs` VALUES (16, 1, '\n111\n1111\n11111\n', 'user', '2023-11-16 14:12:48', 0, 0);
INSERT INTO `blogs` VALUES (17, 2, '\n123\n', 'manager', '2023-11-16 15:24:32', 0, 0);
INSERT INTO `blogs` VALUES (18, 2, '\n12\n', 'manager', '2023-11-16 15:31:31', 0, 0);
INSERT INTO `blogs` VALUES (19, 1, '\n123\n', 'user', '2023-11-16 15:31:49', 0, 0);
INSERT INTO `blogs` VALUES (20, 2, '\nPosting a managerBlog\n', 'manager', '2023-11-20 10:58:09', 0, 0);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `commentId` int NOT NULL AUTO_INCREMENT,
  `blogId` int NOT NULL,
  `posterId` int NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fromWho` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL,
  `like` int NULL DEFAULT 0,
  PRIMARY KEY (`commentId`) USING BTREE,
  UNIQUE INDEX `commentId_UNIQUE`(`commentId` ASC) USING BTREE,
  INDEX `blogId`(`blogId` ASC) USING BTREE,
  INDEX `posterId`(`posterId` ASC) USING BTREE,
  CONSTRAINT `blogId` FOREIGN KEY (`blogId`) REFERENCES `blogs` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `posterId` FOREIGN KEY (`posterId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for follows
-- ----------------------------
DROP TABLE IF EXISTS `follows`;
CREATE TABLE `follows`  (
  `userId` int NOT NULL,
  `followTime` datetime NOT NULL,
  `followId` int NOT NULL,
  PRIMARY KEY (`userId`, `followId`) USING BTREE,
  CONSTRAINT `follows_followId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follows_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follows
-- ----------------------------

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `messageId` int NOT NULL AUTO_INCREMENT,
  `senderId` int NOT NULL,
  `receiverId` int NOT NULL,
  `fromWho` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` datetime NOT NULL,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`messageId`) USING BTREE,
  UNIQUE INDEX `messageId_UNIQUE`(`messageId` ASC) USING BTREE,
  INDEX `messages_receiverId`(`receiverId` ASC) USING BTREE,
  INDEX `messages_senderId`(`senderId` ASC) USING BTREE,
  CONSTRAINT `messages_receiverId` FOREIGN KEY (`receiverId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `messages_senderId` FOREIGN KEY (`senderId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passWord` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` tinyint NULL DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tele` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `personalSignature` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthDay` date NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `userId_UNIQUE`(`userId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (2, 'manager', '123456', 'manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (3, 'visitor', '123456', 'visitor', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
