 
DROP TABLE IF EXISTS `usercontact`;
CREATE TABLE `usercontact` (
  `contactId` int(20) NOT NULL AUTO_INCREMENT,
  `userID` int(10) NOT NULL,
  `contactName` varchar(50) DEFAULT NULL,
  `contactNumber` varchar(50) DEFAULT NULL,
  `contactAvatar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`contactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userID` int(10) NOT NULL,
  `nickName` varchar(20) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 

DROP TABLE IF EXISTS `userlog`;
CREATE TABLE `userlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `time` varchar(100) DEFAULT NULL,
  `userLog` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE `useraccount` (
  `userAccount` varchar(50) NOT NULL,
  `userID` bigint(50) NOT NULL,
  `clientType` varchar(10) NOT NULL,
  `clientID` varchar(50) NOT NULL,
  `loginType` varchar(50) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL,
  `province` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
