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

 Date: 15/11/2023 19:22:38
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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blogs
-- ----------------------------
INSERT INTO `blogs` VALUES (4, 48, '111', 'user', '2023-11-14 19:24:01', 0, 0);
INSERT INTO `blogs` VALUES (5, 48, '111', 'user', '2023-11-14 19:27:43', 0, 0);
INSERT INTO `blogs` VALUES (6, 48, '222', 'user', '2023-11-14 19:27:43', 0, 0);
INSERT INTO `blogs` VALUES (7, 48, '111', 'user', '2023-11-14 19:28:46', 0, 0);
INSERT INTO `blogs` VALUES (8, 49, '222', 'manager', '2023-11-14 19:28:46', 0, 0);
INSERT INTO `blogs` VALUES (9, 48, '111111111111111', 'user', '2023-11-15 18:18:24', 0, 0);
INSERT INTO `blogs` VALUES (10, 48, '\n111\n1111\n11111\n', 'user', '2023-11-15 18:27:49', 0, 0);
INSERT INTO `blogs` VALUES (11, 48, '\n22\n222\n2222\n22222\n', 'user', '2023-11-15 18:29:49', 0, 0);
INSERT INTO `blogs` VALUES (12, 48, '\n111\n', 'user', '2023-11-15 18:58:25', 0, 0);

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
  CONSTRAINT `follows_userId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follows_followId` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
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
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (48, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (49, 'insert', 'insert', 'manager', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (50, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (51, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (52, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (53, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (54, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (55, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (56, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (57, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (58, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (59, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (60, '123', '123456', 'user', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `users` VALUES (61, 'insert', 'insert', 'user', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
