SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `proname` varchar(50) NOT NULL COMMENT '省级名称',
  `procode` varchar(45) NOT NULL COMMENT '省级代码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `proname` (`proname`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='省会表';

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES ('1', '北京', 'BJ');
INSERT INTO `province` VALUES ('2', '上海', 'SH');
INSERT INTO `province` VALUES ('3', '天津', 'TJ');
INSERT INTO `province` VALUES ('4', '重庆', 'CQ');
INSERT INTO `province` VALUES ('5', '河北', 'HE');
INSERT INTO `province` VALUES ('6', '山西', 'SA');
INSERT INTO `province` VALUES ('7', '内蒙古自治区', 'NM');
INSERT INTO `province` VALUES ('8', '辽宁', 'LN');
INSERT INTO `province` VALUES ('9', '吉林', 'JL');
INSERT INTO `province` VALUES ('10', '黑龙江', 'HL');
INSERT INTO `province` VALUES ('11', '江苏', 'JS');
INSERT INTO `province` VALUES ('12', '浙江', 'ZJ');
INSERT INTO `province` VALUES ('13', '安徽', 'AH');
INSERT INTO `province` VALUES ('14', '福建', 'FJ');
INSERT INTO `province` VALUES ('15', '江西', 'JX');
INSERT INTO `province` VALUES ('16', '山东', 'SD');
INSERT INTO `province` VALUES ('17', '河南', 'HS');
INSERT INTO `province` VALUES ('18', '湖北', 'HB');
INSERT INTO `province` VALUES ('19', '湖南', 'HN');
INSERT INTO `province` VALUES ('20', '广东', 'GD');
INSERT INTO `province` VALUES ('21', '广西壮族自治区', 'GX');
INSERT INTO `province` VALUES ('22', '海南', 'HA');
INSERT INTO `province` VALUES ('23', '四川', 'SC');
INSERT INTO `province` VALUES ('24', '贵州', 'GZ');
INSERT INTO `province` VALUES ('25', '云南', 'YN');
INSERT INTO `province` VALUES ('26', '西藏自治区', 'XZ');
INSERT INTO `province` VALUES ('27', '陕西', 'SX');
INSERT INTO `province` VALUES ('28', '甘肃', 'GS');
INSERT INTO `province` VALUES ('29', '青海', 'QH');
INSERT INTO `province` VALUES ('30', '宁夏回族自治区', 'NX');
INSERT INTO `province` VALUES ('31', '新疆维吾尔自治区', 'XJ');
