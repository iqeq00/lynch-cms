/*
Navicat MySQL Data Transfer

Source Server         : YJC
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : lynchcms

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-06-01 09:41:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_admin`
-- ----------------------------
DROP TABLE IF EXISTS `cms_admin`;
CREATE TABLE `cms_admin` (
  `adminId` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminCreateTime` datetime DEFAULT NULL,
  `adminName` varchar(255) DEFAULT NULL,
  `adminPassword` varchar(255) DEFAULT NULL,
  `adminUpadteTime` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_admin
-- ----------------------------
INSERT INTO `cms_admin` VALUES ('1', '2012-06-01 09:33:58', 'admin', 'ceb4f32325eda6142bd65215f4c0f371', '2012-06-01 09:34:05', '');
INSERT INTO `cms_admin` VALUES ('2', '2012-06-01 09:34:27', 'user', '47a733d60998c719cf3526ae7d106d13', '2012-06-01 09:34:36', '');

-- ----------------------------
-- Table structure for `cms_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `cms_admin_role`;
CREATE TABLE `cms_admin_role` (
  `adminRoleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `adminRoleCreateTime` datetime DEFAULT NULL,
  `adminRoleUpadteTime` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `cmsAdmin_adminId` bigint(20) DEFAULT NULL,
  `cmsRole_roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`adminRoleId`),
  KEY `FKC53D4C1C96B2677E` (`cmsRole_roleId`),
  KEY `FKC53D4C1CB0A91749` (`cmsAdmin_adminId`),
  CONSTRAINT `FKC53D4C1CB0A91749` FOREIGN KEY (`cmsAdmin_adminId`) REFERENCES `cms_admin` (`adminId`),
  CONSTRAINT `FKC53D4C1C96B2677E` FOREIGN KEY (`cmsRole_roleId`) REFERENCES `cms_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_admin_role
-- ----------------------------
INSERT INTO `cms_admin_role` VALUES ('1', '2012-06-01 09:36:24', null, '', '1', '1');
INSERT INTO `cms_admin_role` VALUES ('2', '2012-06-01 09:38:15', null, '', '2', '2');

-- ----------------------------
-- Table structure for `cms_authoritiy`
-- ----------------------------
DROP TABLE IF EXISTS `cms_authoritiy`;
CREATE TABLE `cms_authoritiy` (
  `authoritiyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `authoritiyCreateTime` datetime DEFAULT NULL,
  `authoritiyDesc` varchar(255) DEFAULT NULL,
  `authoritiyName` varchar(255) DEFAULT NULL,
  `authoritiyUpadteTime` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`authoritiyId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_authoritiy
-- ----------------------------
INSERT INTO `cms_authoritiy` VALUES ('1', '2012-06-01 09:30:51', '管理员权限', 'ROLE_ADMIN', '2012-06-01 09:30:55', '');
INSERT INTO `cms_authoritiy` VALUES ('2', '2012-06-01 09:31:20', '普通用户权限', 'ROLE_USER', '2012-06-01 09:38:47', '');

-- ----------------------------
-- Table structure for `cms_authoritiy_resource`
-- ----------------------------
DROP TABLE IF EXISTS `cms_authoritiy_resource`;
CREATE TABLE `cms_authoritiy_resource` (
  `authoritiyResourceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `authoritiyResourceCreateTime` datetime DEFAULT NULL,
  `authoritiyResourceUpadteTime` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `cmsAuthoritiy_authoritiyId` bigint(20) DEFAULT NULL,
  `cmsResource_resourceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`authoritiyResourceId`),
  KEY `FKDF11E091C887F16` (`cmsResource_resourceId`),
  KEY `FKDF11E0914610962E` (`cmsAuthoritiy_authoritiyId`),
  CONSTRAINT `FKDF11E0914610962E` FOREIGN KEY (`cmsAuthoritiy_authoritiyId`) REFERENCES `cms_authoritiy` (`authoritiyId`),
  CONSTRAINT `FKDF11E091C887F16` FOREIGN KEY (`cmsResource_resourceId`) REFERENCES `cms_resource` (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_authoritiy_resource
-- ----------------------------
INSERT INTO `cms_authoritiy_resource` VALUES ('1', '2012-06-01 09:31:49', null, '', '1', '1');
INSERT INTO `cms_authoritiy_resource` VALUES ('2', '2012-06-01 09:32:01', null, '', '2', '2');

-- ----------------------------
-- Table structure for `cms_resource`
-- ----------------------------
DROP TABLE IF EXISTS `cms_resource`;
CREATE TABLE `cms_resource` (
  `resourceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `resourceCreateTime` datetime DEFAULT NULL,
  `resourceDesc` varchar(255) DEFAULT NULL,
  `resourceName` varchar(255) DEFAULT NULL,
  `resourcePriority` int(11) DEFAULT NULL,
  `resourceString` varchar(255) DEFAULT NULL,
  `resourceType` varchar(255) DEFAULT NULL,
  `resourceUpadteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_resource
-- ----------------------------
INSERT INTO `cms_resource` VALUES ('1', '', '2012-06-01 09:28:53', '管理员访问的', '管理员资源', '2', '/admin/**', 'url', '2012-06-01 09:28:58');
INSERT INTO `cms_resource` VALUES ('2', '', '2012-06-01 09:29:41', '普通用户访问的', '普通用户资源', '2', '/index/**', 'url', '2012-06-01 09:31:38');

-- ----------------------------
-- Table structure for `cms_role`
-- ----------------------------
DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `roleCreateTime` datetime DEFAULT NULL,
  `roleDesc` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `roleUpadteTime` datetime DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_role
-- ----------------------------
INSERT INTO `cms_role` VALUES ('1', '', '2012-06-01 09:33:00', '管理员角色', '管理员角色', '2012-06-01 09:33:03');
INSERT INTO `cms_role` VALUES ('2', '', '2012-06-01 09:33:15', '普通用户角色', '普通用户角色', '2012-06-01 09:33:35');

-- ----------------------------
-- Table structure for `cms_role_authoritiy`
-- ----------------------------
DROP TABLE IF EXISTS `cms_role_authoritiy`;
CREATE TABLE `cms_role_authoritiy` (
  `roleAuthoritiyId` bigint(20) NOT NULL AUTO_INCREMENT,
  `enabled` bit(1) DEFAULT NULL,
  `roleAuthoritiyCreateTime` datetime DEFAULT NULL,
  `roleAuthoritiyUpadteTime` datetime DEFAULT NULL,
  `cmsAuthoritiy_authoritiyId` bigint(20) DEFAULT NULL,
  `cmsRole_roleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`roleAuthoritiyId`),
  KEY `FKAF19141996B2677E` (`cmsRole_roleId`),
  KEY `FKAF1914194610962E` (`cmsAuthoritiy_authoritiyId`),
  CONSTRAINT `FKAF1914194610962E` FOREIGN KEY (`cmsAuthoritiy_authoritiyId`) REFERENCES `cms_authoritiy` (`authoritiyId`),
  CONSTRAINT `FKAF19141996B2677E` FOREIGN KEY (`cmsRole_roleId`) REFERENCES `cms_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_role_authoritiy
-- ----------------------------
INSERT INTO `cms_role_authoritiy` VALUES ('1', '', '2012-06-01 09:33:23', null, '1', '1');
INSERT INTO `cms_role_authoritiy` VALUES ('2', '', '2012-06-01 09:33:30', null, '2', '2');
