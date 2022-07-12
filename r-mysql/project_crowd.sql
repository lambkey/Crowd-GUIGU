/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : project_crowd

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 12/07/2022 23:52:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for inner_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `inner_admin_role`;
CREATE TABLE `inner_admin_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `admin_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_admin_role
-- ----------------------------
INSERT INTO `inner_admin_role` VALUES (20, 424, 3);
INSERT INTO `inner_admin_role` VALUES (21, 1, 1);
INSERT INTO `inner_admin_role` VALUES (23, 427, 5);

-- ----------------------------
-- Table structure for inner_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `inner_role_auth`;
CREATE TABLE `inner_role_auth`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NULL DEFAULT NULL,
  `auth_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inner_role_auth
-- ----------------------------
INSERT INTO `inner_role_auth` VALUES (13, 4, 4);
INSERT INTO `inner_role_auth` VALUES (14, 4, 6);
INSERT INTO `inner_role_auth` VALUES (15, 1, 1);
INSERT INTO `inner_role_auth` VALUES (16, 1, 3);
INSERT INTO `inner_role_auth` VALUES (17, 5, 1);
INSERT INTO `inner_role_auth` VALUES (18, 5, 8);

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receive_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收获地址',
  `member_id` int(0) NULL DEFAULT NULL COMMENT '该订单属于哪个会员的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, '杨先生', '123456', '广州华商学院', 24);
INSERT INTO `t_address` VALUES (3, '杨小姐', '13612626908', '湛江市遂溪县', 24);

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `login_acct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pswd` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `login_acct`) USING BTREE,
  UNIQUE INDEX `login_acct`(`login_acct`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 431 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, '2374785659', '$2a$10$hVwK6AjPFBzFR3fybfrK0./.9LrNlJGumHbBM8NnUDVIJGfXD/kte', '小羊', '2374785659.com', NULL);
INSERT INTO `t_admin` VALUES (427, '123456', '$2a$10$bDRZWHSkfwlNDbNFJ0gCoudysvVi93EzLgceIoRdzza4mMMpqrp9S', '小猫', '2374785659@qq.com', '2022-05-09 12:12:44');
INSERT INTO `t_admin` VALUES (428, '1234567', '$2a$10$CWyMmmJ8nssHurVCyK6jdel5RxBpexJlIgHZNPZSN4KL0RDDfnQky', '小tip', '2374785659@qq.com', '2022-05-09 08:34:44');
INSERT INTO `t_admin` VALUES (431, '12345678', '$2a$10$l2Z4ahtH25yal68bmsEMNuHljtMUsw94r13uKsEyZYzc8FbxDe4tG', '阿靖', '2374785659@qq.com', '2022-05-10 01:07:21');

-- ----------------------------
-- Table structure for t_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `category_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES (1, '', '用户模块', NULL);
INSERT INTO `t_auth` VALUES (2, 'user:delete', '删除', 1);
INSERT INTO `t_auth` VALUES (3, 'user:get', '查询', 1);
INSERT INTO `t_auth` VALUES (4, '', '角色模块', NULL);
INSERT INTO `t_auth` VALUES (5, 'role:delete', '删除', 4);
INSERT INTO `t_auth` VALUES (6, 'role:get', '查询', 4);
INSERT INTO `t_auth` VALUES (7, 'role:add', '新增', 4);
INSERT INTO `t_auth` VALUES (8, 'user:save', '新增', 1);

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `loginacct` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userpswd` char(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authstatus` int(0) NULL DEFAULT NULL COMMENT '实名认证状态 0 - 未实名认证， 1 - 实名认证申 请中， 2 - 已实名认证',
  `usertype` int(0) NULL DEFAULT NULL COMMENT ' 0 - 个人， 1 - 企业',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardnum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `accttype` int(0) NULL DEFAULT NULL COMMENT '0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `loginacct`(`loginacct`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member
-- ----------------------------
INSERT INTO `t_member` VALUES (3, 'abc', 'abc', 'lamb', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (5, '2374785659', '123456', 'lamb', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (8, '237478565', '123456', 'lamb', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (9, '12345678', 'dddwadaw', 'dwadwa', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (10, '12', 'dddwadaw', 'dwadwa', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (12, '1234', 'dddwadaw', 'dwadwa', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (15, '12345', 'dddwadaw', 'dwadwa', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (16, 'qwer', '$2a$10$4Ck.I4E0ZmQZPMPY/EuT5.HPZWa0bpcvsKlyQhVA.5czlQhPVAaAe', 'jksb', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (18, '1320123', '$2a$10$Xk8.j./nYHCmHPSVlK71YuiO6HWAOLpOkefk7qqTjLx9hjEqCPie.', 'djahdjkawh', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (19, '042452123', '$2a$10$qSTnvkq129d0YbF9afW/y./j.HsYBJow4DK5QakygHLjNe9po7LNe', '123123', 'dwadaw', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (20, 'qwerjklj', '$2a$10$YMIlrrb5wP6lYATOzzcA1.Jem4Q7gK41KVD6f/nSo5CBaa9QsfwaS', '杨卓颖', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (21, '1231353dwad', '$2a$10$yBc90WFB.doMho48dQTRwunL9pX6nL1DL6BilAdiwDH94o7crYDL.', 'dawd', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (23, 'dwaddwadwad', '$2a$10$nn/DWHi2BRr7bNjyZf3z.e0pgF53ggx.5SRmJv659priIWqjvfs6y', 'dwad', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (24, '123456', '$2a$10$.1lvTRXXczT7A0ORx/1EVu0M7oA7JMl.Jx89YHwISzXbqI4QTHnDa', 'sb', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (26, 'dwadwaddad', '$2a$10$I4eF8xrMnQbV.UqzVNtUN.EKb3HMCm1iiC0zUxrtQboQ8Gb5q.iw.', '2374785659', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (27, 'dawdadawdfafafaw', '$2a$10$lOk5gmvRbnNPdI0o/p5vHuU7N5NvQzCAO1gWFOKLRPjB4.kpJVXHy', '2374785659', '2374785659@qq.com', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_member` VALUES (29, '123', '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_member_confirm_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_confirm_info`;
CREATE TABLE `t_member_confirm_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `memberid` int(0) NULL DEFAULT NULL COMMENT '会员 id',
  `paynum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '易付宝企业账号',
  `cardnum` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member_confirm_info
-- ----------------------------
INSERT INTO `t_member_confirm_info` VALUES (15, 24, '16513132123', 'dwadawdawd');
INSERT INTO `t_member_confirm_info` VALUES (16, 24, '16513132123', 'dwadawdawd');
INSERT INTO `t_member_confirm_info` VALUES (17, 24, 'dwdadw', 'dawdawdawdad');
INSERT INTO `t_member_confirm_info` VALUES (18, 24, 'dwdawdwad', 'wdadwadawd');
INSERT INTO `t_member_confirm_info` VALUES (19, 24, '16513132123', 'wdwdawdawd');
INSERT INTO `t_member_confirm_info` VALUES (20, 24, '16513132123', '354345343');
INSERT INTO `t_member_confirm_info` VALUES (21, 24, '16513132123', 'wdwadwadwadwda');
INSERT INTO `t_member_confirm_info` VALUES (22, 24, '16513132123', '15131321213');
INSERT INTO `t_member_confirm_info` VALUES (23, 24, '16513132123', 'dwadwadawd');

-- ----------------------------
-- Table structure for t_member_launch_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_launch_info`;
CREATE TABLE `t_member_launch_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `memberid` int(0) NULL DEFAULT NULL COMMENT '会员 id',
  `description_simple` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简单介绍',
  `description_detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细介绍',
  `phone_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `service_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客服电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_member_launch_info
-- ----------------------------
INSERT INTO `t_member_launch_info` VALUES (46, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (47, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (48, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (49, 24, 'i am mao', '我是lamb', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (50, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (51, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (52, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (53, 24, 'i am mao', '我是猫哥', '123456', '654321');
INSERT INTO `t_member_launch_info` VALUES (54, 24, 'i am mao', '我是猫哥', '123456', '654321');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NULL DEFAULT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, NULL, ' 系统权限菜单', NULL, 'glyphicon glyphicon-th-list');
INSERT INTO `t_menu` VALUES (2, 1, ' 控 制 面 板 ', 'main.htm', 'glyphicon glyphicon-dashboard');
INSERT INTO `t_menu` VALUES (3, 1, '权限管理', NULL, 'glyphicon glyphicon glyphicon-tasks');
INSERT INTO `t_menu` VALUES (4, 3, ' 用 户 维 护 ', 'user/index.htm', 'glyphicon glyphicon-user');
INSERT INTO `t_menu` VALUES (5, 3, ' 角 色 维 护 ', 'role/index.htm', 'glyphicon glyphicon-king');
INSERT INTO `t_menu` VALUES (6, 3, ' 菜 单 维 护 ', 'permission/index.htm', 'glyphicon glyphicon-lock');
INSERT INTO `t_menu` VALUES (7, 1, ' 业 务 审 核 ', NULL, 'glyphicon glyphicon-ok');
INSERT INTO `t_menu` VALUES (8, 7, ' 实名认证审核', 'auth_cert/index.htm', 'glyphicon glyphicon-check');
INSERT INTO `t_menu` VALUES (9, 7, ' 广 告 审 核 ', 'auth_adv/index.htm', 'glyphicon glyphicon-check');
INSERT INTO `t_menu` VALUES (10, 7, ' 项 目 审 核 ', 'auth_project/index.htm', 'glyphicon glyphicon-check');
INSERT INTO `t_menu` VALUES (11, 1, ' 业 务 管 理 ', NULL, 'glyphicon glyphicon-th-large');
INSERT INTO `t_menu` VALUES (12, 11, ' 资 质 维 护 ', 'cert/index.htm', 'glyphicon glyphicon-picture');
INSERT INTO `t_menu` VALUES (13, 11, ' 分 类 管 理 ', 'certtype/index.htm', 'glyphicon glyphicon-equalizer');
INSERT INTO `t_menu` VALUES (14, 11, ' 流 程 管 理 ', 'process/index.htm', 'glyphicon glyphicon-random');
INSERT INTO `t_menu` VALUES (15, 11, ' 广 告 管 理 ', 'advert/index.htm', 'glyphicon glyphicon-hdd');
INSERT INTO `t_menu` VALUES (16, 11, ' 消 息 模 板 ', 'message/index.htm', 'glyphicon glyphicon-comment');
INSERT INTO `t_menu` VALUES (17, 11, ' 项 目 分 类 ', 'projectType/index.htm', 'glyphicon glyphicon-list');
INSERT INTO `t_menu` VALUES (18, 11, ' 项 目 标 签 ', 'tag/index.htm', 'glyphicon glyphicon-tags');
INSERT INTO `t_menu` VALUES (19, 1, ' 参 数 管 理 ', 'param/index.htm', 'glyphicon glyphicon-list-alt');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_num` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `pay_order_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付宝流水单号',
  `order_amount` double NULL DEFAULT NULL COMMENT '订单金额',
  `invoice` int(0) NULL DEFAULT NULL COMMENT '是否开发票',
  `invoice_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票抬头',
  `order_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `address_id` int(0) NULL DEFAULT NULL COMMENT '会员提交地址的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, '202207062215188B93B769B94849F5BE5E90A5BE2653B1', NULL, 50, 0, '广州华商学院', '加多点菜', 3);
INSERT INTO `t_order` VALUES (2, '20220706224032F9C1CD6FF6594506BCD232E46525DD93', '2022070622001483950501776171', 30, 0, '广州华商学院', '好', 1);

-- ----------------------------
-- Table structure for t_order_project
-- ----------------------------
DROP TABLE IF EXISTS `t_order_project`;
CREATE TABLE `t_order_project`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `launch_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `return_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报内容',
  `return_count` int(0) NULL DEFAULT NULL COMMENT '回报数量',
  `support_price` int(0) NULL DEFAULT NULL COMMENT '支持金额',
  `freight` int(0) NULL DEFAULT NULL COMMENT '配送费用',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单表的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order_project
-- ----------------------------
INSERT INTO `t_order_project` VALUES (1, 'hei', 'i am mao', '以身相许', 5, 10, 0, 1);
INSERT INTO `t_order_project` VALUES (2, 'hei', 'i am mao', '以身相许', 3, 10, 0, 2);

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `project_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目描述',
  `money` bigint(0) NULL DEFAULT NULL COMMENT '筹集金额',
  `day` int(0) NULL DEFAULT NULL COMMENT '筹集天数',
  `status` int(0) NULL DEFAULT NULL COMMENT '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败 ',
  `deploydate` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目发起时间',
  `supportmoney` bigint(0) NULL DEFAULT NULL COMMENT '已筹集到的金额',
  `supporter` int(0) NULL DEFAULT NULL COMMENT '支持人数',
  `completion` int(0) NULL DEFAULT NULL COMMENT '百分比完成度',
  `memberid` int(0) NULL DEFAULT NULL COMMENT '发起人的会员 id',
  `createdate` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目创建时间',
  `follower` int(0) NULL DEFAULT NULL COMMENT '关注人数',
  `header_picture_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头图路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project
-- ----------------------------
INSERT INTO `t_project` VALUES (75, '宽松睡衣', '就是帅！', 5000, 13, 0, '2022-06-22', 500, 1000, NULL, 24, '22-06-22 21', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/f4cef64a-62b6-4eb7-bf6f-bbe9966e8683.jpg');
INSERT INTO `t_project` VALUES (76, '书架', '很大，有抽屉', 1333, 19, 0, '2022-06-22', 5000, 3000, NULL, 24, '22-06-22 21', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/ef455ac3-e480-439a-a4c2-3d3bd81db9a2.png');
INSERT INTO `t_project` VALUES (77, '截图1', '就是帅！', 100000, 30, 0, '2022-06-22', 6000, 2000, NULL, 24, '22-06-22 22:49:05', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/5a6e1b41-4aa0-44f1-942b-6b5019107ad8.png');
INSERT INTO `t_project` VALUES (78, '飞机', '就是帅！', 1000, 15, 0, '2022-06-22', 7000, 4000, NULL, 24, '22-06-22 23:39:37', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/ab2c4450-26a4-4eb0-9040-26c34d5d5340.png');
INSERT INTO `t_project` VALUES (79, '吉他谱', '尽情弹', 3333, 90, 0, '2022-06-22', 7000, 4000, NULL, 24, '22-06-23 10:29:06', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/23/cc75a422-b0c6-4c7c-9925-9ee617f87deb.png');
INSERT INTO `t_project` VALUES (80, 'brotherMao', '就是帅！', 500, 30, 0, '2022-06-22', NULL, NULL, NULL, 24, '22-06-23 11:15:47', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/23/7448b99b-b568-47b6-bd28-dec982068b13.png');
INSERT INTO `t_project` VALUES (81, 'hei', '就是帅！', 100000, 30, 0, '2022-06-22', NULL, NULL, NULL, 24, '22-07-01 11:28:48', NULL, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/6/1/e982af4e-3074-4310-b320-92ebb945215f.png');

-- ----------------------------
-- Table structure for t_project_item_pic
-- ----------------------------
DROP TABLE IF EXISTS `t_project_item_pic`;
CREATE TABLE `t_project_item_pic`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `projectid` int(0) NULL DEFAULT NULL,
  `item_pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_item_pic
-- ----------------------------
INSERT INTO `t_project_item_pic` VALUES (148, 75, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/16b52597-b28a-4b73-b7fb-b18d0b4a380f.png');
INSERT INTO `t_project_item_pic` VALUES (149, 75, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/d99b5d8c-b228-4570-9a26-9ad76b7c8ecd.png');
INSERT INTO `t_project_item_pic` VALUES (150, 75, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/ee70ac9f-0f8d-4e76-90c1-f102eae133e6.png');
INSERT INTO `t_project_item_pic` VALUES (151, 76, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/16b52597-b28a-4b73-b7fb-b18d0b4a380f.png');
INSERT INTO `t_project_item_pic` VALUES (152, 76, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/d99b5d8c-b228-4570-9a26-9ad76b7c8ecd.png');
INSERT INTO `t_project_item_pic` VALUES (153, 76, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/ee70ac9f-0f8d-4e76-90c1-f102eae133e6.png');
INSERT INTO `t_project_item_pic` VALUES (156, 77, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/43939bb0-5caa-40c8-8052-e2fddee60428.png');
INSERT INTO `t_project_item_pic` VALUES (157, 78, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/22/567fb9da-6cf7-4492-bd4f-a6dadf134ef7.png');
INSERT INTO `t_project_item_pic` VALUES (158, 79, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/23/5f64bae2-a680-4077-b47c-c57069c83bf2.png');
INSERT INTO `t_project_item_pic` VALUES (159, 79, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/23/3dd30a03-9004-4272-abcd-45e1806ac5b8.png');
INSERT INTO `t_project_item_pic` VALUES (160, 80, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/5/23/23e6c19d-65e5-4da7-9977-778144956012.png');
INSERT INTO `t_project_item_pic` VALUES (161, 81, 'https://lambkey-1309051782.cos.ap-guangzhou.myqcloud.com/2022/6/1/d7f0dfc6-47a2-470d-819b-5dad541276d8.png');

-- ----------------------------
-- Table structure for t_project_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_project_tag`;
CREATE TABLE `t_project_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `projectid` int(0) NULL DEFAULT NULL,
  `tagid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 203 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_tag
-- ----------------------------
INSERT INTO `t_project_tag` VALUES (185, 75, 4);
INSERT INTO `t_project_tag` VALUES (186, 75, 8);
INSERT INTO `t_project_tag` VALUES (187, 75, 9);
INSERT INTO `t_project_tag` VALUES (188, 76, 5);
INSERT INTO `t_project_tag` VALUES (189, 76, 8);
INSERT INTO `t_project_tag` VALUES (190, 76, 9);
INSERT INTO `t_project_tag` VALUES (191, 77, 6);
INSERT INTO `t_project_tag` VALUES (192, 77, 10);
INSERT INTO `t_project_tag` VALUES (193, 78, 4);
INSERT INTO `t_project_tag` VALUES (194, 78, 8);
INSERT INTO `t_project_tag` VALUES (195, 78, 9);
INSERT INTO `t_project_tag` VALUES (196, 79, 4);
INSERT INTO `t_project_tag` VALUES (197, 79, 7);
INSERT INTO `t_project_tag` VALUES (198, 79, 9);
INSERT INTO `t_project_tag` VALUES (199, 80, 5);
INSERT INTO `t_project_tag` VALUES (200, 80, 9);
INSERT INTO `t_project_tag` VALUES (201, 81, 6);
INSERT INTO `t_project_tag` VALUES (202, 81, 7);
INSERT INTO `t_project_tag` VALUES (203, 81, 10);

-- ----------------------------
-- Table structure for t_project_type
-- ----------------------------
DROP TABLE IF EXISTS `t_project_type`;
CREATE TABLE `t_project_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `projectid` int(0) NULL DEFAULT NULL,
  `typeid` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_project_type
-- ----------------------------
INSERT INTO `t_project_type` VALUES (148, 75, 2);
INSERT INTO `t_project_type` VALUES (149, 75, 4);
INSERT INTO `t_project_type` VALUES (150, 76, 1);
INSERT INTO `t_project_type` VALUES (151, 77, 3);
INSERT INTO `t_project_type` VALUES (152, 78, 2);
INSERT INTO `t_project_type` VALUES (153, 79, 1);
INSERT INTO `t_project_type` VALUES (154, 80, 3);
INSERT INTO `t_project_type` VALUES (155, 80, 4);
INSERT INTO `t_project_type` VALUES (156, 81, 2);
INSERT INTO `t_project_type` VALUES (157, 81, 4);

-- ----------------------------
-- Table structure for t_return
-- ----------------------------
DROP TABLE IF EXISTS `t_return`;
CREATE TABLE `t_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `projectid` int(0) NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL COMMENT '0 - 实物回报， 1 虚拟物品回报',
  `supportmoney` int(0) NULL DEFAULT NULL COMMENT '支持金额',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回报内容',
  `count` int(0) NULL DEFAULT NULL COMMENT '回报产品限额，“0”为不限回报数量',
  `signalpurchase` int(0) NULL DEFAULT NULL COMMENT '是否设置单笔限购',
  `purchase` int(0) NULL DEFAULT NULL COMMENT '具体限购数量',
  `freight` int(0) NULL DEFAULT NULL COMMENT '运费，“0”为包邮',
  `invoice` int(0) NULL DEFAULT NULL COMMENT '0 - 不开发票， 1 - 开发票',
  `returndate` int(0) NULL DEFAULT NULL COMMENT '项目结束后多少天向支持者发送回报',
  `describ_pic_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明图片路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_return
-- ----------------------------
INSERT INTO `t_return` VALUES (32, 75, 0, 500, '以身相许', 5, 0, 8, NULL, NULL, 10, NULL);
INSERT INTO `t_return` VALUES (33, 76, 0, 5000, '不用回报，教我雷锋', 5, 0, 8, NULL, NULL, 15, NULL);
INSERT INTO `t_return` VALUES (34, 77, 0, 6000, '以身相许', 5, 0, 8, NULL, NULL, 20, NULL);
INSERT INTO `t_return` VALUES (35, 78, 0, 7000, '以身相许', 10, 1, 8, NULL, NULL, 25, NULL);
INSERT INTO `t_return` VALUES (36, 79, NULL, 10, '以身相许', 10, 1, 52, NULL, NULL, 30, NULL);
INSERT INTO `t_return` VALUES (37, 79, 0, 2222, '以身相许', 10, 0, 16, NULL, NULL, 35, NULL);
INSERT INTO `t_return` VALUES (38, 80, 0, 10, '以身相许', 5, 1, 8, 0, NULL, 15, NULL);
INSERT INTO `t_return` VALUES (39, 81, 0, 10, '以身相许', 5, 0, 8, 0, NULL, 15, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'PM-项目经理');
INSERT INTO `t_role` VALUES (2, 'SE-软件工程师');
INSERT INTO `t_role` VALUES (3, 'PG-程序员');
INSERT INTO `t_role` VALUES (4, 'TL-组长');
INSERT INTO `t_role` VALUES (5, 'OPR-项目经理');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (1, '科技', '开启智慧未来');
INSERT INTO `t_type` VALUES (2, '设计', '创建改变未来');
INSERT INTO `t_type` VALUES (3, '农业', '网络天下肥美');
INSERT INTO `t_type` VALUES (4, '公益', '汇聚点点爱心');

SET FOREIGN_KEY_CHECKS = 1;
