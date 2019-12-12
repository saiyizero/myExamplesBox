CREATE TABLE `hxsys_dsacct` (
  `faredma` varchar(3) NOT NULL COMMENT '法人代码',
  `acctnmb` varchar(30) NOT NULL COMMENT '账户',
  `acctseq` varchar(6) NOT NULL COMMENT '账户序号',
  `balance` decimal(10,0) DEFAULT NULL COMMENT '余额',
  `acctnme` varchar(100) DEFAULT NULL COMMENT '账户名称',
  `kehuhoo` varchar(20) DEFAULT NULL COMMENT '客户号',
  `oporgan` varchar(6) DEFAULT NULL COMMENT '开户机构',
  `hesucod` varchar(30) DEFAULT NULL COMMENT '核算代码',
  PRIMARY KEY (`faredma`,`acctnmb`,`acctseq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对私账户信息';