/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 26/05/2020 14:23:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `p_id` int(8) NOT NULL COMMENT '权限id',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `r_id` int(8) NOT NULL AUTO_INCREMENT,
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `describe_zw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文描述',
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'user', '用户');
INSERT INTO `role` VALUES (2, 'admin', '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` int(8) NOT NULL COMMENT '角色id',
  `permission_id` int(8) NOT NULL COMMENT '权限id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐，用于密码加密',
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `state` int(255) NULL DEFAULT 1 COMMENT '状态    1：活动、2：禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 224 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', ' 34423d2fff26af0a9f3a0975f9c32c35', 'a5t2', '你大哥', '002@qq.com', '111', '2020-01-11 06:07:16', 1);
INSERT INTO `user` VALUES (6, 'guest', '442f451b34d835c70d3d08795d82ca34', 'ml8e', '马小旭', 'aa@qq.com', '1301313131', '2020-04-04 17:55:35', 1);
INSERT INTO `user` VALUES (20, '小马104', 'pwd104', '1234', '我叫104', '104@qq.com', '13013937104', '2020-04-05 10:06:13', 1);
INSERT INTO `user` VALUES (22, '小马106', 'pwd106', '1234', '我叫106', '106@qq.com', '13013937106', '2020-04-07 18:06:13', 2);
INSERT INTO `user` VALUES (24, '小马108', 'pwd108', '1234', '我叫108', '108@qq.com', '13013937108', '2020-04-05 18:06:13', 2);
INSERT INTO `user` VALUES (30, '小马112', 'pwd112', '1234', '我叫112', '112@qq.com', '13013937112', '2020-04-07 18:08:40', 2);
INSERT INTO `user` VALUES (32, '小马114', 'pwd114', '1234', '我叫114', '114@qq.com', '13013937114', '2020-04-08 18:08:40', 1);
INSERT INTO `user` VALUES (33, '小马115', 'pwd115', '1234', '我叫115', '115@qq.com', '13013937115', '2020-04-08 02:08:40', 1);
INSERT INTO `user` VALUES (34, '小马116', 'pwd116', '1234', '我叫116', '116@qq.com', '13013937116', '2020-04-08 02:08:40', 1);
INSERT INTO `user` VALUES (35, '小马117', 'pwd117', '1234', '我叫117', '117@qq.com', '13013937117', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (36, '小马118', 'pwd118', '1234', '我叫118', '118@qq.com', '13013937118', '2020-04-07 10:08:40', 1);
INSERT INTO `user` VALUES (37, '小马119', 'pwd119', '1234', '我叫119', '119@qq.com', '13013937119', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (38, '小马120', 'pwd120', '1234', '我叫120', '120@qq.com', '13013937120', '2020-04-06 10:08:40', 1);
INSERT INTO `user` VALUES (39, '小马121', 'pwd121', '1234', '我叫121', '121@qq.com', '13013937121', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (40, '小马122', 'pwd122', '1234', '我叫122', '122@qq.com', '13013937122', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (41, '小马123', 'pwd123', '1234', '我叫123', '123@qq.com', '13013937123', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (42, '小马124', 'pwd124', '1234', '我叫124', '124@qq.com', '13013937124', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (43, '小马125', 'pwd125', '1234', '我叫125', '125@qq.com', '13013937125', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (44, '小马126', 'pwd126', '1234', '我叫126', '126@qq.com', '13013937126', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (45, '小马127', 'pwd127', '1234', '我叫127', '127@qq.com', '13013937127', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (46, '小马128', 'pwd128', '1234', '我叫128', '128@qq.com', '13013937128', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (48, '小马130', 'pwd130', '1234', '我叫130', '130@qq.com', '13013937130', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (49, '小马131', 'pwd131', '1234', '我叫131', '131@qq.com', '13013937131', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (50, '小马132', 'pwd132', '1234', '我叫132', '132@qq.com', '13013937132', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (51, '小马133', 'pwd133', '1234', '我叫133', '133@qq.com', '13013937133', '2020-04-09 10:08:40', 1);
INSERT INTO `user` VALUES (52, '小马134', 'pwd134', '1234', '我叫134', '134@qq.com', '13013937134', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (53, '小马135', 'pwd135', '1234', '我叫135', '135@qq.com', '13013937135', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (54, '小马136', 'pwd136', '1234', '我叫136', '136@qq.com', '13013937136', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (56, '小马138', 'pwd138', '1234', '我叫138', '138@qq.com', '13013937138', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (57, '小马139', 'pwd139', '1234', '我叫139', '139@qq.com', '13013937139', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (58, '小马140', 'pwd140', '1234', '我叫140', '140@qq.com', '13013937140', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (59, '小马141', 'pwd141', '1234', '我叫141', '141@qq.com', '13013937141', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (60, '小马142', 'pwd142', '1234', '我叫142', '142@qq.com', '13013937142', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (61, '小马143', 'pwd143', '1234', '我叫143', '143@qq.com', '13013937143', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (62, '小马144', 'pwd144', '1234', '我叫144', '144@qq.com', '13013937144', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (63, '小马145', 'pwd145', '1234', '我叫145', '145@qq.com', '13013937145', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (64, '小马146', 'pwd146', '1234', '我叫146', '146@qq.com', '13013937146', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (65, '小马147', 'pwd147', '1234', '我叫147', '147@qq.com', '13013937147', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (66, '小马148', 'pwd148', '1234', '我叫148', '148@qq.com', '13013937148', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (67, '小马149', 'pwd149', '1234', '我叫149', '149@qq.com', '13013937149', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (68, '小马150', 'pwd150', '1234', '我叫150', '150@qq.com', '13013937150', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (70, '小马152', 'pwd152', '1234', '我叫152', '152@qq.com', '13013937152', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (71, '小马153', 'pwd153', '1234', '我叫153', '153@qq.com', '13013937153', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (72, '小马154', 'pwd154', '1234', '我叫154', '154@qq.com', '13013937154', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (73, '小马155', 'pwd155', '1234', '我叫155', '155@qq.com', '13013937155', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (74, '小马156', 'pwd156', '1234', '我叫156', '156@qq.com', '13013937156', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (76, '小马158', 'pwd158', '1234', '我叫158', '158@qq.com', '13013937158', '2020-04-09 02:08:41', 2);
INSERT INTO `user` VALUES (77, '小马159', 'pwd159', '1234', '我叫159', '159@qq.com', '13013937159', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (78, '小马160', 'pwd160', '1234', '我叫160', '160@qq.com', '13013937160', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (79, '小马161', 'pwd161', '1234', '我叫161', '161@qq.com', '13013937161', '2020-04-08 18:08:41', 1);
INSERT INTO `user` VALUES (80, '小马162', 'pwd162', '1234', '我叫162', '162@qq.com', '13013937162', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (81, '小马163', 'pwd163', '1234', '我叫163', '163@qq.com', '13013937163', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (82, '小马164', 'pwd164', '1234', '我叫164', '164@qq.com', '13013937164', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (83, '小马165', 'pwd165', '1234', '我叫165', '165@qq.com', '13013937165', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (84, '小马166', 'pwd166', '1234', '我叫166', '166@qq.com', '13013937166', '2020-04-09 10:08:41', 1);
INSERT INTO `user` VALUES (85, '小马167', 'pwd167', '1234', '我叫167', '167@qq.com', '13013937167', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (86, '小马168', 'pwd168', '1234', '我叫168', '168@qq.com', '13013937168', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (87, '小马169', 'pwd169', '1234', '我叫169', '169@qq.com', '13013937169', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (88, '小马170', 'pwd170', '1234', '我叫170', '170@qq.com', '13013937170', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (89, '小马171', 'pwd171', '1234', '我叫171', '171@qq.com', '13013937171', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (90, '小马172', 'pwd172', '1234', '我叫172', '172@qq.com', '13013937172', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (91, '小马173', 'pwd173', '1234', '我叫173', '173@qq.com', '13013937173', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (92, '小马174', 'pwd174', '1234', '我叫174', '174@qq.com', '13013937174', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (93, '小马175', 'pwd175', '1234', '我叫175', '175@qq.com', '13013937175', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (94, '小马176', 'pwd176', '1234', '我叫176', '176@qq.com', '13013937176', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (95, '小马177', 'pwd177', '1234', '我叫177', '177@qq.com', '13013937177', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (96, '小马178', 'pwd178', '1234', '我叫178', '178@qq.com', '13013937178', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (97, '小马179', 'pwd179', '1234', '我叫179', '179@qq.com', '13013937179', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (98, '小马180', 'pwd180', '1234', '我叫180', '180@qq.com', '13013937180', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (99, '小马181', 'pwd181', '1234', '我叫181', '181@qq.com', '13013937181', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (100, '小马182', 'pwd182', '1234', '我叫182', '182@qq.com', '13013937182', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (101, '小马183', 'pwd183', '1234', '我叫183', '183@qq.com', '13013937183', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (102, '小马184', 'pwd184', '1234', '我叫184', '184@qq.com', '13013937184', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (103, '小马185', 'pwd185', '1234', '我叫185', '185@qq.com', '13013937185', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (104, '小马186', 'pwd186', '1234', '我叫186', '186@qq.com', '13013937186', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (105, '小马187', 'pwd187', '1234', '我叫187', '187@qq.com', '13013937187', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (106, '小马188', 'pwd188', '1234', '我叫188', '188@qq.com', '13013937188', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (107, '小马189', 'pwd189', '1234', '我叫189', '189@qq.com', '13013937189', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (108, '小马190', 'pwd190', '1234', '我叫190', '190@qq.com', '13013937190', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (109, '小马191', 'pwd191', '1234', '我叫191', '191@qq.com', '13013937191', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (110, '小马192', 'pwd192', '1234', '我叫192', '192@qq.com', '13013937192', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (111, '小马193', 'pwd193', '1234', '我叫193', '193@qq.com', '13013937193', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (112, '小马194', 'pwd194', '1234', '我叫194', '194@qq.com', '13013937194', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (113, '小马195', 'pwd195', '1234', '我叫195', '195@qq.com', '13013937195', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (114, '小马196', 'pwd196', '1234', '我叫196', '196@qq.com', '13013937196', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (115, '小马197', 'pwd197', '1234', '我叫197', '197@qq.com', '13013937197', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (116, '小马198', 'pwd198', '1234', '我叫198', '198@qq.com', '13013937198', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (117, '小马199', 'pwd199', '1234', '我叫199', '199@qq.com', '13013937199', '2020-04-09 10:08:42', 1);
INSERT INTO `user` VALUES (118, '小马200', 'pwd200', '1234', '我叫200', '200@qq.com', '13013937200', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (119, '小马201', 'pwd201', '1234', '我叫201', '201@qq.com', '13013937201', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (120, '小马202', 'pwd202', '1234', '我叫202', '202@qq.com', '13013937202', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (121, '小马203', 'pwd203', '1234', '我叫203', '203@qq.com', '13013937203', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (122, '小马204', 'pwd204', '1234', '我叫204', '204@qq.com', '13013937204', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (123, '小马205', 'pwd205', '1234', '我叫205', '205@qq.com', '13013937205', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (124, '小马206', 'pwd206', '1234', '我叫206', '206@qq.com', '13013937206', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (125, '小马207', 'pwd207', '1234', '我叫207', '207@qq.com', '13013937207', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (126, '小马208', 'pwd208', '1234', '我叫208', '208@qq.com', '13013937208', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (127, '小马209', 'pwd209', '1234', '我叫209', '209@qq.com', '13013937209', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (128, '小马210', 'pwd210', '1234', '我叫210', '210@qq.com', '13013937210', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (129, '小马211', 'pwd211', '1234', '我叫211', '211@qq.com', '13013937211', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (130, '小马212', 'pwd212', '1234', '我叫212', '212@qq.com', '13013937212', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (131, '小马213', 'pwd213', '1234', '我叫213', '213@qq.com', '13013937213', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (132, '小马214', 'pwd214', '1234', '我叫214', '214@qq.com', '13013937214', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (133, '小马215', 'pwd215', '1234', '我叫215', '215@qq.com', '13013937215', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (134, '小马216', 'pwd216', '1234', '我叫216', '216@qq.com', '13013937216', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (135, '小马217', 'pwd217', '1234', '我叫217', '217@qq.com', '13013937217', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (136, '小马218', 'pwd218', '1234', '我叫218', '218@qq.com', '13013937218', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (137, '小马219', 'pwd219', '1234', '我叫219', '219@qq.com', '13013937219', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (138, '小马220', 'pwd220', '1234', '我叫220', '220@qq.com', '13013937220', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (139, '小马221', 'pwd221', '1234', '我叫221', '221@qq.com', '13013937221', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (140, '小马222', 'pwd222', '1234', '我叫222', '222@qq.com', '13013937222', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (141, '小马223', 'pwd223', '1234', '我叫223', '223@qq.com', '13013937223', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (142, '小马224', 'pwd224', '1234', '我叫224', '224@qq.com', '13013937224', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (143, '小马225', 'pwd225', '1234', '我叫225', '225@qq.com', '13013937225', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (144, '小马226', 'pwd226', '1234', '我叫226', '226@qq.com', '13013937226', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (145, '小马227', 'pwd227', '1234', '我叫227', '227@qq.com', '13013937227', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (146, '小马228', 'pwd228', '1234', '我叫228', '228@qq.com', '13013937228', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (147, '小马229', 'pwd229', '1234', '我叫229', '229@qq.com', '13013937229', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (148, '小马230', 'pwd230', '1234', '我叫230', '230@qq.com', '13013937230', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (149, '小马231', 'pwd231', '1234', '我叫231', '231@qq.com', '13013937231', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (150, '小马232', 'pwd232', '1234', '我叫232', '232@qq.com', '13013937232', '2020-04-09 10:08:43', 1);
INSERT INTO `user` VALUES (151, '小马233', 'pwd233', '1234', '我叫233', '233@qq.com', '13013937233', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (152, '小马234', 'pwd234', '1234', '我叫234', '234@qq.com', '13013937234', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (153, '小马235', 'pwd235', '1234', '我叫235', '235@qq.com', '13013937235', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (154, '小马236', 'pwd236', '1234', '我叫236', '236@qq.com', '13013937236', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (155, '小马237', 'pwd237', '1234', '我叫237', '237@qq.com', '13013937237', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (156, '小马238', 'pwd238', '1234', '我叫238', '238@qq.com', '13013937238', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (157, '小马239', 'pwd239', '1234', '我叫239', '239@qq.com', '13013937239', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (158, '小马240', 'pwd240', '1234', '我叫240', '240@qq.com', '13013937240', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (159, '小马241', 'pwd241', '1234', '我叫241', '241@qq.com', '13013937241', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (160, '小马242', 'pwd242', '1234', '我叫242', '242@qq.com', '13013937242', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (161, '小马243', 'pwd243', '1234', '我叫243', '243@qq.com', '13013937243', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (162, '小马244', 'pwd244', '1234', '我叫244', '244@qq.com', '13013937244', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (163, '小马245', 'pwd245', '1234', '我叫245', '245@qq.com', '13013937245', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (164, '小马246', 'pwd246', '1234', '我叫246', '246@qq.com', '13013937246', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (165, '小马247', 'pwd247', '1234', '我叫247', '247@qq.com', '13013937247', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (166, '小马248', 'pwd248', '1234', '我叫248', '248@qq.com', '13013937248', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (167, '小马249', 'pwd249', '1234', '我叫249', '249@qq.com', '13013937249', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (168, '小马250', 'pwd250', '1234', '我叫250', '250@qq.com', '13013937250', '2020-04-08 18:08:44', 1);
INSERT INTO `user` VALUES (169, '小马251', 'pwd251', '1234', '我叫251', '251@qq.com', '13013937251', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (170, '小马252', 'pwd252', '1234', '我叫252', '252@qq.com', '13013937252', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (171, '小马253', 'pwd253', '1234', '我叫253', '253@qq.com', '13013937253', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (172, '小马254', 'pwd254', '1234', '我叫254', '254@qq.com', '13013937254', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (173, '小马255', 'pwd255', '1234', '我叫255', '255@qq.com', '13013937255', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (174, '小马256', 'pwd256', '1234', '我叫256', '256@qq.com', '13013937256', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (175, '小马257', 'pwd257', '1234', '我叫257', '257@qq.com', '13013937257', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (176, '小马258', 'pwd258', '1234', '我叫258', '258@qq.com', '13013937258', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (177, '小马259', 'pwd259', '1234', '我叫259', '259@qq.com', '13013937259', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (178, '小马260', 'pwd260', '1234', '我叫260', '260@qq.com', '13013937260', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (179, '小马261', 'pwd261', '1234', '我叫261', '261@qq.com', '13013937261', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (180, '小马262', 'pwd262', '1234', '我叫262', '262@qq.com', '13013937262', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (181, '小马263', 'pwd263', '1234', '我叫263', '263@qq.com', '13013937263', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (182, '小马264', 'pwd264', '1234', '我叫264', '264@qq.com', '13013937264', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (183, '小马265', 'pwd265', '1234', '我叫265', '265@qq.com', '13013937265', '2020-04-09 10:08:44', 1);
INSERT INTO `user` VALUES (184, '小马266', 'pwd266', '1234', '我叫266', '266@qq.com', '13013937266', '2020-04-09 02:08:45', 2);
INSERT INTO `user` VALUES (185, '小马267', 'pwd267', '1234', '我叫267', '267@qq.com', '13013937267', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (186, '小马268', 'pwd268', '1234', '我叫268', '268@qq.com', '13013937268', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (187, '小马269', 'pwd269', '1234', '我叫269', '269@qq.com', '13013937269', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (188, '小马270', 'pwd270', '1234', '我叫270', '270@qq.com', '13013937270', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (189, '小马271', 'pwd271', '1234', '我叫271', '271@qq.com', '13013937271', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (190, '小马272', 'pwd272', '1234', '我叫272', '272@qq.com', '13013937272', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (191, '小马273', 'pwd273', '1234', '我叫273', '273@qq.com', '13013937273', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (193, '小马275', 'pwd275', '1234', '我叫275', '275@qq.com', '13013937275', '2020-04-09 02:08:45', 2);
INSERT INTO `user` VALUES (194, '小马276', 'pwd276', '1234', '我叫276', '276@qq.com', '13013937276', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (195, '小马277', 'pwd277', '1234', '我叫277', '277@qq.com', '13013937277', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (197, '小马279', 'pwd279', '1234', '我叫279', '279@qq.com', '13013937279', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (198, '小马280', 'pwd280', '1234', '我叫280', '280@qq.com', '13013937280', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (199, '小马281', 'pwd281', '1234', '我叫281', '281@qq.com', '13013937281', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (200, '小马282', 'pwd282', '1234', '我叫282', '282@qq.com', '13013937282', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (201, '小马283', 'pwd283', '1234', '我叫283', '283@qq.com', '13013937283', '2020-04-08 18:08:45', 1);
INSERT INTO `user` VALUES (203, '小马285', 'pwd285', '1234', '我叫285', '285@qq.com', '13013937285', '2020-04-09 02:08:45', 2);
INSERT INTO `user` VALUES (204, '小马286', 'pwd286', '1234', '我叫286', '286@qq.com', '13013937286', '2020-04-07 18:08:45', 2);
INSERT INTO `user` VALUES (205, '小马287', 'pwd287', '1234', '我叫287', '287@qq.com', '13013937287', '2020-04-08 18:08:45', 1);
INSERT INTO `user` VALUES (208, '小马290', 'pwd290', '1234', '我叫290', '290@qq.com', '13013937290', '2020-04-09 10:08:45', 1);
INSERT INTO `user` VALUES (211, '小马293', 'pwd293', '1234', '我叫293', '293@qq.com', '13013937293', '2020-04-08 18:08:45', 1);
INSERT INTO `user` VALUES (213, '小马295', 'pwd295', '1234', '我叫295', '295@qq.com', '13013937295', '2020-04-07 10:08:46', 1);
INSERT INTO `user` VALUES (218, '1111', '1111', NULL, 'aaa', '1111', '1111', '2020-04-13 18:42:58', 1);
INSERT INTO `user` VALUES (219, 'mxn', '34029a8457dafd7477b95fe2ea508e54', '0Gya', '马旭楠', '12345@qq.com', '12312312312', '2020-05-08 00:17:54', 1);
INSERT INTO `user` VALUES (221, 'mxn2', '9fd8ea857893a204c61b464f07463750', 'NAEk', '马旭楠', '123mxn@qq.com', '12345612312', '2020-05-08 16:22:32', 1);
INSERT INTO `user` VALUES (223, 'mxn3', 'b1f69169628ec3ddc86f2cd0567a479e', 'DyNb', '马旭楠3号', '123ll@qq.com', '12312312315', '2020-05-08 16:50:55', 1);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `u_id` int(8) NULL DEFAULT NULL COMMENT '用户id',
  `r_id` int(8) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `f_userid`(`u_id`) USING BTREE,
  INDEX `f_roleid`(`r_id`) USING BTREE,
  CONSTRAINT `f_roleid` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `f_userid` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 190 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 2);
INSERT INTO `user_role` VALUES (2, 6, 1);
INSERT INTO `user_role` VALUES (3, 20, 1);
INSERT INTO `user_role` VALUES (5, 22, 1);
INSERT INTO `user_role` VALUES (6, 24, 1);
INSERT INTO `user_role` VALUES (7, 30, 1);
INSERT INTO `user_role` VALUES (9, 32, 1);
INSERT INTO `user_role` VALUES (10, 33, 1);
INSERT INTO `user_role` VALUES (11, 34, 1);
INSERT INTO `user_role` VALUES (12, 35, 1);
INSERT INTO `user_role` VALUES (13, 36, 1);
INSERT INTO `user_role` VALUES (14, 37, 1);
INSERT INTO `user_role` VALUES (15, 38, 1);
INSERT INTO `user_role` VALUES (16, 39, 1);
INSERT INTO `user_role` VALUES (17, 40, 1);
INSERT INTO `user_role` VALUES (18, 41, 1);
INSERT INTO `user_role` VALUES (19, 42, 1);
INSERT INTO `user_role` VALUES (20, 43, 1);
INSERT INTO `user_role` VALUES (21, 44, 1);
INSERT INTO `user_role` VALUES (22, 45, 1);
INSERT INTO `user_role` VALUES (23, 46, 1);
INSERT INTO `user_role` VALUES (24, 48, 1);
INSERT INTO `user_role` VALUES (25, 49, 1);
INSERT INTO `user_role` VALUES (26, 50, 1);
INSERT INTO `user_role` VALUES (27, 51, 1);
INSERT INTO `user_role` VALUES (28, 52, 1);
INSERT INTO `user_role` VALUES (29, 53, 1);
INSERT INTO `user_role` VALUES (30, 54, 1);
INSERT INTO `user_role` VALUES (32, 56, 1);
INSERT INTO `user_role` VALUES (33, 57, 1);
INSERT INTO `user_role` VALUES (34, 58, 1);
INSERT INTO `user_role` VALUES (35, 59, 1);
INSERT INTO `user_role` VALUES (36, 60, 1);
INSERT INTO `user_role` VALUES (37, 61, 1);
INSERT INTO `user_role` VALUES (38, 62, 1);
INSERT INTO `user_role` VALUES (39, 63, 1);
INSERT INTO `user_role` VALUES (40, 64, 1);
INSERT INTO `user_role` VALUES (41, 65, 1);
INSERT INTO `user_role` VALUES (42, 66, 1);
INSERT INTO `user_role` VALUES (43, 67, 1);
INSERT INTO `user_role` VALUES (44, 68, 1);
INSERT INTO `user_role` VALUES (46, 70, 1);
INSERT INTO `user_role` VALUES (47, 71, 1);
INSERT INTO `user_role` VALUES (48, 72, 1);
INSERT INTO `user_role` VALUES (49, 73, 1);
INSERT INTO `user_role` VALUES (50, 74, 1);
INSERT INTO `user_role` VALUES (52, 76, 1);
INSERT INTO `user_role` VALUES (53, 77, 1);
INSERT INTO `user_role` VALUES (54, 78, 1);
INSERT INTO `user_role` VALUES (55, 79, 1);
INSERT INTO `user_role` VALUES (56, 80, 1);
INSERT INTO `user_role` VALUES (57, 81, 1);
INSERT INTO `user_role` VALUES (58, 82, 1);
INSERT INTO `user_role` VALUES (59, 83, 1);
INSERT INTO `user_role` VALUES (60, 84, 1);
INSERT INTO `user_role` VALUES (61, 85, 1);
INSERT INTO `user_role` VALUES (62, 86, 1);
INSERT INTO `user_role` VALUES (63, 87, 1);
INSERT INTO `user_role` VALUES (64, 88, 1);
INSERT INTO `user_role` VALUES (65, 89, 1);
INSERT INTO `user_role` VALUES (66, 90, 1);
INSERT INTO `user_role` VALUES (67, 91, 1);
INSERT INTO `user_role` VALUES (68, 92, 1);
INSERT INTO `user_role` VALUES (69, 93, 1);
INSERT INTO `user_role` VALUES (70, 94, 1);
INSERT INTO `user_role` VALUES (71, 95, 1);
INSERT INTO `user_role` VALUES (72, 96, 1);
INSERT INTO `user_role` VALUES (73, 97, 1);
INSERT INTO `user_role` VALUES (74, 98, 1);
INSERT INTO `user_role` VALUES (75, 99, 1);
INSERT INTO `user_role` VALUES (76, 100, 1);
INSERT INTO `user_role` VALUES (77, 101, 1);
INSERT INTO `user_role` VALUES (78, 102, 1);
INSERT INTO `user_role` VALUES (79, 103, 1);
INSERT INTO `user_role` VALUES (80, 104, 1);
INSERT INTO `user_role` VALUES (81, 105, 1);
INSERT INTO `user_role` VALUES (82, 106, 1);
INSERT INTO `user_role` VALUES (83, 107, 1);
INSERT INTO `user_role` VALUES (84, 108, 1);
INSERT INTO `user_role` VALUES (85, 109, 1);
INSERT INTO `user_role` VALUES (86, 110, 1);
INSERT INTO `user_role` VALUES (87, 111, 1);
INSERT INTO `user_role` VALUES (88, 112, 1);
INSERT INTO `user_role` VALUES (89, 113, 1);
INSERT INTO `user_role` VALUES (90, 114, 1);
INSERT INTO `user_role` VALUES (91, 115, 1);
INSERT INTO `user_role` VALUES (92, 116, 1);
INSERT INTO `user_role` VALUES (93, 117, 1);
INSERT INTO `user_role` VALUES (94, 118, 1);
INSERT INTO `user_role` VALUES (95, 119, 1);
INSERT INTO `user_role` VALUES (96, 120, 1);
INSERT INTO `user_role` VALUES (97, 121, 1);
INSERT INTO `user_role` VALUES (98, 122, 1);
INSERT INTO `user_role` VALUES (99, 123, 1);
INSERT INTO `user_role` VALUES (100, 124, 1);
INSERT INTO `user_role` VALUES (101, 125, 1);
INSERT INTO `user_role` VALUES (102, 126, 1);
INSERT INTO `user_role` VALUES (103, 127, 1);
INSERT INTO `user_role` VALUES (104, 128, 1);
INSERT INTO `user_role` VALUES (105, 129, 1);
INSERT INTO `user_role` VALUES (106, 130, 1);
INSERT INTO `user_role` VALUES (107, 131, 1);
INSERT INTO `user_role` VALUES (108, 132, 1);
INSERT INTO `user_role` VALUES (109, 133, 1);
INSERT INTO `user_role` VALUES (110, 134, 1);
INSERT INTO `user_role` VALUES (111, 135, 1);
INSERT INTO `user_role` VALUES (112, 136, 1);
INSERT INTO `user_role` VALUES (113, 137, 1);
INSERT INTO `user_role` VALUES (114, 138, 1);
INSERT INTO `user_role` VALUES (115, 139, 1);
INSERT INTO `user_role` VALUES (116, 140, 1);
INSERT INTO `user_role` VALUES (117, 141, 1);
INSERT INTO `user_role` VALUES (118, 142, 1);
INSERT INTO `user_role` VALUES (119, 143, 1);
INSERT INTO `user_role` VALUES (120, 144, 1);
INSERT INTO `user_role` VALUES (121, 145, 1);
INSERT INTO `user_role` VALUES (122, 146, 1);
INSERT INTO `user_role` VALUES (123, 147, 1);
INSERT INTO `user_role` VALUES (124, 148, 1);
INSERT INTO `user_role` VALUES (125, 149, 1);
INSERT INTO `user_role` VALUES (126, 150, 1);
INSERT INTO `user_role` VALUES (127, 151, 1);
INSERT INTO `user_role` VALUES (128, 152, 1);
INSERT INTO `user_role` VALUES (129, 153, 1);
INSERT INTO `user_role` VALUES (130, 154, 1);
INSERT INTO `user_role` VALUES (131, 155, 1);
INSERT INTO `user_role` VALUES (132, 156, 1);
INSERT INTO `user_role` VALUES (133, 157, 1);
INSERT INTO `user_role` VALUES (134, 158, 1);
INSERT INTO `user_role` VALUES (135, 159, 1);
INSERT INTO `user_role` VALUES (136, 160, 1);
INSERT INTO `user_role` VALUES (137, 161, 1);
INSERT INTO `user_role` VALUES (138, 162, 1);
INSERT INTO `user_role` VALUES (139, 163, 1);
INSERT INTO `user_role` VALUES (140, 164, 1);
INSERT INTO `user_role` VALUES (141, 165, 1);
INSERT INTO `user_role` VALUES (142, 166, 1);
INSERT INTO `user_role` VALUES (143, 167, 1);
INSERT INTO `user_role` VALUES (144, 168, 1);
INSERT INTO `user_role` VALUES (145, 169, 1);
INSERT INTO `user_role` VALUES (146, 170, 1);
INSERT INTO `user_role` VALUES (147, 171, 1);
INSERT INTO `user_role` VALUES (148, 172, 1);
INSERT INTO `user_role` VALUES (149, 173, 1);
INSERT INTO `user_role` VALUES (150, 174, 1);
INSERT INTO `user_role` VALUES (151, 175, 1);
INSERT INTO `user_role` VALUES (152, 176, 1);
INSERT INTO `user_role` VALUES (153, 177, 1);
INSERT INTO `user_role` VALUES (154, 178, 1);
INSERT INTO `user_role` VALUES (155, 179, 1);
INSERT INTO `user_role` VALUES (156, 180, 1);
INSERT INTO `user_role` VALUES (157, 181, 1);
INSERT INTO `user_role` VALUES (158, 182, 1);
INSERT INTO `user_role` VALUES (159, 183, 1);
INSERT INTO `user_role` VALUES (160, 184, 1);
INSERT INTO `user_role` VALUES (161, 185, 1);
INSERT INTO `user_role` VALUES (162, 186, 1);
INSERT INTO `user_role` VALUES (163, 187, 1);
INSERT INTO `user_role` VALUES (164, 188, 1);
INSERT INTO `user_role` VALUES (165, 189, 1);
INSERT INTO `user_role` VALUES (166, 190, 1);
INSERT INTO `user_role` VALUES (167, 191, 1);
INSERT INTO `user_role` VALUES (169, 193, 1);
INSERT INTO `user_role` VALUES (170, 194, 1);
INSERT INTO `user_role` VALUES (171, 195, 1);
INSERT INTO `user_role` VALUES (173, 197, 1);
INSERT INTO `user_role` VALUES (174, 198, 1);
INSERT INTO `user_role` VALUES (175, 199, 1);
INSERT INTO `user_role` VALUES (176, 200, 1);
INSERT INTO `user_role` VALUES (177, 201, 1);
INSERT INTO `user_role` VALUES (179, 203, 1);
INSERT INTO `user_role` VALUES (180, 204, 1);
INSERT INTO `user_role` VALUES (181, 205, 1);
INSERT INTO `user_role` VALUES (184, 208, 1);
INSERT INTO `user_role` VALUES (187, 211, 1);
INSERT INTO `user_role` VALUES (189, 213, 1);

SET FOREIGN_KEY_CHECKS = 1;
