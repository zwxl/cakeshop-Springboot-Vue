/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : cakeshop

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 21/12/2022 22:02:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `images1` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品详情图1',
  `price` float(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `stock` int(10) NULL DEFAULT NULL COMMENT '商品库存',
  `typeid` int(11) NULL DEFAULT NULL COMMENT '商品类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '巧克力蛋糕', '/picture/1-1.jpg', 25.00, '巧克力和奶油制作', 5, 1);
INSERT INTO `goods` VALUES (3, '冰雪之恋', '/picture/2-1.jpg', 50.20, '香浓巧克力，浓浓安慕兮啊，，，', 781, 1);
INSERT INTO `goods` VALUES (4, '留恋之恋', '/picture/3-1.jpg', 229.00, '主口味:榴莲奶油味(原味蛋糕胚+榴莲酱+乳脂奶油)主要原料：新西兰乳脂奶油，古巴朗姆酒，韩国幼砂糖，鲜草莓。 甜度： 三星（满五星） 最佳食用温度：5-7摄氏度', 10, 1);
INSERT INTO `goods` VALUES (5, '玫瑰物语', '/picture/4-1.jpg', 299.00, '一座小小的花园 一场优美的缄默 色彩的冲撞带来幻美的视觉享受 自制的玫瑰酱调配而成的玫瑰慕斯蛋糕 口感丰富 异香诱人 忠贞的爱情充溢了玫瑰的内心,因而更加美丽\r\n主口味:玫瑰慕斯 主要原料:糖渍玫瑰,奶油芝士,乳脂奶油,朗姆酒,白巧克力软糖 甜度:三星（满五星） 最佳食用温度：5-7摄氏度', 8, 2);
INSERT INTO `goods` VALUES (6, '芒果列车', '/picture/5-1.jpg', 269.00, '黄橙橙的时光列车,散发出温暖的灯光,连接着过去与未来.这是一款纯慕斯型的蛋糕,芒果慕斯镶嵌芒果果冻丁,不含蛋糕胚体,均匀莫斯体和果冻,口感细腻嫩滑,酸甜适口零脂肪,美容又零脂肪\r\n主口味: 芒果慕斯 主要原料:芒果果溶,芒果果冻,乳脂奶油,法国君度力娇酒  甜度:三星（满五星） 最佳食用温度：5-7摄氏度', 10, 2);

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单项id',
  `total` float(11, 2) NULL DEFAULT NULL COMMENT '商品总额',
  `amount` int(6) NULL DEFAULT NULL COMMENT '商品数量',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '支付状态',
  `paytype` tinyint(1) NULL DEFAULT 0 COMMENT '支付方式',
  `datetime` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单日期',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `goodsid` int(11) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (1, 50.60, 5, 5, 0, '2022-12-06 20:58:19.000000', 1, 3);
INSERT INTO `orderlist` VALUES (2, 60.50, 8, 0, 0, '2022-12-07 22:55:52.000000', 1, 4);
INSERT INTO `orderlist` VALUES (13, 50.60, 5, 1, 0, '2022-12-18 14:23:44', 1, 3);
INSERT INTO `orderlist` VALUES (16, 100.40, 2, 1, 0, '2022-12-19 00:28:54', 1, 3);
INSERT INTO `orderlist` VALUES (17, 598.00, 2, 1, 0, '2022-12-19 00:34:33', 1, 5);
INSERT INTO `orderlist` VALUES (18, 100.40, 2, 1, 0, '2022-12-19 00:42:29', 1, 3);
INSERT INTO `orderlist` VALUES (19, 200.80, 2, 1, 0, '2022-12-19 00:43:56', 1, 3);
INSERT INTO `orderlist` VALUES (20, 544.50, 9, 1, 0, '2022-12-19 10:07:13', 1, 4);
INSERT INTO `orderlist` VALUES (21, 253.00, 5, 1, 0, '2022-12-19 10:07:13', 1, 3);
INSERT INTO `orderlist` VALUES (22, 1196.00, 2, 1, 0, '2022-12-19 10:07:13', 1, 5);
INSERT INTO `orderlist` VALUES (23, 200.80, 2, 1, 0, '2022-12-19 10:07:13', 1, 3);
INSERT INTO `orderlist` VALUES (24, 401.60, 2, 1, 0, '2022-12-19 10:07:13', 1, 3);
INSERT INTO `orderlist` VALUES (25, 25.00, 1, 0, 0, '2022-12-19 10:42:49', 1, 1);
INSERT INTO `orderlist` VALUES (32, 25.00, 3, 1, 0, '2022-12-20 23:13:00', 34, 1);
INSERT INTO `orderlist` VALUES (33, 598.00, 4, 0, 0, '2022-12-20 23:14:09', 34, 5);
INSERT INTO `orderlist` VALUES (34, 50.00, 4, 5, 0, '2022-12-21 14:44:22', 37, 1);
INSERT INTO `orderlist` VALUES (35, 50.00, 2, 5, 0, '2022-12-21 15:07:00', 38, 1);
INSERT INTO `orderlist` VALUES (36, 100.40, 2, 0, 0, '2022-12-21 15:07:16', 38, 3);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类型id',
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '冰淇凌系列6339');
INSERT INTO `type` VALUES (2, '儿童系列1885');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `phone` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `isadmin` bit(1) NULL DEFAULT b'0' COMMENT '是否为管理员',
  `isvalidate` bit(1) NULL DEFAULT b'1' COMMENT '账户是否有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zwx', '1234', '张伟雄', '3036354190@qq.com', '13789193492', '湖南长沙开福区长沙学院', b'1', b'1');
INSERT INTO `user` VALUES (18, 'zcq', '1234', '曾超群', 'ciaociao@qq.com', '13789193492', '湖南长沙', b'1', b'1');
INSERT INTO `user` VALUES (19, 'lisi', 'rytes', '李四', 'lisi@qq.com', '110086', '广东广州', b'0', b'0');
INSERT INTO `user` VALUES (21, 'xxy', '1234', '夏昕宇', 'xxy@qq.com', '13789493492', '湖南长沙', b'0', b'0');
INSERT INTO `user` VALUES (22, 'zxy', '3654', '张晓妍', 'wangwu@gmai.com', '120', '湖南岳阳', b'0', b'1');
INSERT INTO `user` VALUES (28, 'zhangweixiong', 'zwx', 'zwx', '3036354190@qq.com', '110', '湖南', b'0', b'1');
INSERT INTO `user` VALUES (30, 'zcx', '1234', 'zhangchunxiang', '123456@qq.com', '18273965454', '湖南长沙', b'0', b'1');
INSERT INTO `user` VALUES (31, 'zzz', '1234', 'zzz', 'zzz@qq.om', '1110', '湖南', b'0', b'1');
INSERT INTO `user` VALUES (34, 'ccc', 'ccc', 'ccc', 'ccc@qq.com', 'ccc', 'ccc', b'0', b'1');
INSERT INTO `user` VALUES (35, 'xxx', 'xxx', 'xxx', 'xxx@qq.com', '137891934912', '湖南', b'0', b'1');
INSERT INTO `user` VALUES (36, '111', '111', '111', '111@qq.com', '10010', '湖南', b'0', b'1');
INSERT INTO `user` VALUES (37, 'ppp', 'ppp', 'ppp', 'ppp@qq.com', '15573936106', '湖南', b'0', b'1');
INSERT INTO `user` VALUES (38, 'www', 'www', 'www', 'www@qq.com', '15573936106', '湖南', b'0', b'1');

SET FOREIGN_KEY_CHECKS = 1;
