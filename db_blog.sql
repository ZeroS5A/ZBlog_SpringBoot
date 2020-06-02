/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : db_blog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 03/06/2020 00:22:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `adminId` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `jobNumber` int(0) NULL DEFAULT NULL,
  `sex` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `blogId` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NOT NULL,
  `classId` int(0) NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `blogDate` datetime(0) NOT NULL,
  `blogContentHtml` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `blogContentMd` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `browse` int(0) NOT NULL DEFAULT 0,
  `summary` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '该博主没有写简介噢',
  PRIMARY KEY (`blogId`) USING BTREE,
  INDEX `b_userId`(`userId`) USING BTREE,
  INDEX `b_classification`(`classId`) USING BTREE,
  CONSTRAINT `b_classification` FOREIGN KEY (`classId`) REFERENCES `t_classification` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `b_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blogLike
-- ----------------------------
DROP TABLE IF EXISTS `t_blogLike`;
CREATE TABLE `t_blogLike`  (
  `likeId` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NOT NULL,
  `blogId` int(0) NOT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`likeId`) USING BTREE,
  INDEX `bl_userId`(`userId`) USING BTREE,
  INDEX `bl_blogId`(`blogId`) USING BTREE,
  CONSTRAINT `bl_blogId` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bl_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_blogtags
-- ----------------------------
DROP TABLE IF EXISTS `t_blogtags`;
CREATE TABLE `t_blogtags`  (
  `blogTagsId` int(0) NOT NULL AUTO_INCREMENT,
  `blogId` int(0) NOT NULL,
  `tagsId` int(0) NOT NULL,
  PRIMARY KEY (`blogTagsId`) USING BTREE,
  INDEX `bg_blogId`(`blogId`) USING BTREE,
  INDEX `bg_tagsId`(`tagsId`) USING BTREE,
  CONSTRAINT `bg_blogId` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bg_tagsId` FOREIGN KEY (`tagsId`) REFERENCES `t_tags` (`tagsId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_classification
-- ----------------------------
DROP TABLE IF EXISTS `t_classification`;
CREATE TABLE `t_classification`  (
  `classId` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `childName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`classId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `commentId` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NOT NULL,
  `blogId` int(0) NULL DEFAULT NULL,
  `like` int(0) NULL DEFAULT NULL,
  `date` datetime(0) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rootId` int(0) NULL DEFAULT NULL COMMENT '对回复中的回复的人',
  `dialogId` int(0) NULL DEFAULT NULL COMMENT '回复中的回复同属子评论',
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `c_userId`(`userId`) USING BTREE,
  INDEX `c_dialogId`(`dialogId`) USING BTREE,
  INDEX `c_blog`(`blogId`) USING BTREE,
  INDEX `c_rootId`(`rootId`) USING BTREE,
  CONSTRAINT `c_blog` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_dialogId` FOREIGN KEY (`dialogId`) REFERENCES `t_comment` (`commentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_rootId` FOREIGN KEY (`rootId`) REFERENCES `t_comment` (`commentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_commentLike
-- ----------------------------
DROP TABLE IF EXISTS `t_commentLike`;
CREATE TABLE `t_commentLike`  (
  `likeId` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NOT NULL,
  `date` datetime(0) NOT NULL,
  `blogId` int(0) NULL DEFAULT NULL,
  `commentId` int(0) NULL DEFAULT NULL,
  `dialogId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`likeId`) USING BTREE,
  INDEX `l_userId`(`userId`) USING BTREE,
  INDEX `l_blog`(`blogId`) USING BTREE,
  INDEX `l_comment`(`commentId`) USING BTREE,
  INDEX `l_dialogId`(`dialogId`) USING BTREE,
  CONSTRAINT `l_blog` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `l_comment` FOREIGN KEY (`commentId`) REFERENCES `t_comment` (`commentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `l_dialogId` FOREIGN KEY (`dialogId`) REFERENCES `t_comment` (`commentId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `l_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `fileId` int(0) NOT NULL AUTO_INCREMENT,
  `userId` int(0) NULL DEFAULT NULL,
  `blogId` int(0) NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `fileMd5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `uploadMd5` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `isAvatar` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`fileId`) USING BTREE,
  INDEX `f_blogId`(`blogId`) USING BTREE,
  INDEX `f_userId`(`userId`) USING BTREE,
  INDEX `uploadMd5`(`uploadMd5`) USING BTREE,
  INDEX `f_fileMd5`(`fileMd5`) USING BTREE,
  CONSTRAINT `f_blogId` FOREIGN KEY (`blogId`) REFERENCES `t_blog` (`blogId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_fileMd5` FOREIGN KEY (`fileMd5`) REFERENCES `t_file` (`uploadMd5`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_userId` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_relationship
-- ----------------------------
DROP TABLE IF EXISTS `t_relationship`;
CREATE TABLE `t_relationship`  (
  `relationship_id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user1` int(0) NULL DEFAULT NULL,
  `user2` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`relationship_id`) USING BTREE,
  INDEX `r_user1`(`user1`) USING BTREE,
  INDEX `r_user2`(`user2`) USING BTREE,
  CONSTRAINT `r_user1` FOREIGN KEY (`user1`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `r_user2` FOREIGN KEY (`user2`) REFERENCES `t_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_tags`;
CREATE TABLE `t_tags`  (
  `tagsId` int(0) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `classification` int(0) NOT NULL,
  `createUser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`tagsId`, `tagName`, `createUser`) USING BTREE,
  INDEX `t_classification`(`classification`) USING BTREE,
  INDEX `tagsId`(`tagsId`) USING BTREE,
  CONSTRAINT `t_classification` FOREIGN KEY (`classification`) REFERENCES `t_classification` (`classId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userId` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `occupation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'c' COMMENT 'a为男，b为女，c未知',
  `attribute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT 'user用户，admin管理员，ban封禁',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
