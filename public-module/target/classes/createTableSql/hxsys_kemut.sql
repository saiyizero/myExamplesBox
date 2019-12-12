CREATE TABLE `hxsys_kemut` (
  `faredma` varchar(3) NOT NULL COMMENT '法人代码',
  `kemuhoo` int(11) NOT NULL COMMENT '科目号',
  `kemujib` int(11) DEFAULT NULL COMMENT '科目级别',
  `kemulex` varchar(2) DEFAULT NULL COMMENT '科目类型',
  `kemunme` varchar(100) DEFAULT NULL COMMENT '科目中文名',
  `kemumas` varchar(500) DEFAULT NULL COMMENT '科目描述',
  PRIMARY KEY (`faredma`,`kemuhoo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='科目表';