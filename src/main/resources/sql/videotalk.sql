 
DROP TABLE IF EXISTS `usercontact`;
CREATE TABLE `usercontact` (
  `contactId` int(20) NOT NULL DEFAULT 0 AUTO_INCREMENT,
  `userID` int(10) DEFAULT NULL,
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
  `userID` int(11) DEFAULT NULL,
  `time` varchar(100) DEFAULT NULL,
  `userLog` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE `useraccount` (
  `userAccount` varchar(50) NOT NULL,
  `userID` bigint(50) DEFAULT NULL,
  `clientType` varchar(50) DEFAULT NULL,
  `clientID` varchar(50) DEFAULT NULL,
  `loginType` varchar(50) DEFAULT NULL,
  `phoneNumber` varchar(15) DEFAULT NULL,
  `province` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
