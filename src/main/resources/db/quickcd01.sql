# Host: 127.0.0.1  (Version 5.7.16-log)
# Date: 2018-03-12 14:39:03
# Generator: MySQL-Front 5.4  (Build 4.49) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "agent"
#

DROP TABLE IF EXISTS `agent`;
CREATE TABLE `agent` (
  `id` varchar(32) NOT NULL,
  `data` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `envid` varchar(32) DEFAULT NULL,
  `host` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `maxbuild` int(11) DEFAULT '3',
  `version` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "agent"
#

INSERT INTO `agent` VALUES ('12345678xftreqazwert','1520836686067',NULL,NULL,'127.0.0.1','win6121',62945,1,3,NULL),('4028d081544613eb01544614a2900000',NULL,'009',NULL,'127.0.0.1','agent009',5566,0,3,'2'),('6661319C-BBAF-644C-901A-9F011225',NULL,'local agent',NULL,'127.0.0.1','localAgent',5566,0,3,'2'),('AAA1319C-FFAF-644C-901A-9F011225',NULL,'linux agent',NULL,'192.168.1.3','linuxAgent',5566,0,3,'2');

#
# Structure for table "agent_property"
#

DROP TABLE IF EXISTS `agent_property`;
CREATE TABLE `agent_property` (
  `id` varchar(32) NOT NULL,
  `agentid` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  `value` varchar(1024) DEFAULT NULL,
  `secure` int(11) DEFAULT NULL,
  `data` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "agent_property"
#

INSERT INTO `agent_property` VALUES ('89f71c116218ecbd016218ee31270004','12345678xftreqazwert','agent/server.host','localhost',0,NULL),('89f71c116218ecbd016218ee312e0005','12345678xftreqazwert','agent/agent.home','C:\\E\\quickagenttest',0,NULL),('89f71c116218ecbd016218ee31370006','12345678xftreqazwert','agent/tempFile.keep','true',0,NULL),('89f71c116218ecbd016218ee313e0007','12345678xftreqazwert','agent/server.port','6667',0,NULL),('89f71c116218ecbd016218ee31450008','12345678xftreqazwert','agent/AGENT_TEMP','C:\\E\\quickagenttest/var/temp',0,NULL),('89f71c116218ecbd016218ee31500009','12345678xftreqazwert','agent/agent.name','win6121',0,NULL),('89f71c116218ecbd016218ee3157000a','12345678xftreqazwert','agent/agent.id','12345678xftreqazwert',0,NULL),('89f71c116218ecbd016218ee315e000b','12345678xftreqazwert','agent/PLUGIN_DIR','C:\\E\\quickagenttest/var/plugin',0,NULL),('89f71c116218ecbd016218ee3166000c','12345678xftreqazwert','agent/groovy.home','C:\\F\\groovy-2.4.10',0,NULL),('89f71c116218ecbd016218ee316c000d','12345678xftreqazwert','agent/logFile.keep','true',0,NULL);

#
# Structure for table "agentpool"
#

DROP TABLE IF EXISTS `agentpool`;
CREATE TABLE `agentpool` (
  `id` varchar(32) NOT NULL,
  `criterionUuid` varchar(32) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `type` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "agentpool"
#

INSERT INTO `agentpool` VALUES ('402881eb53feb8eb0153feba056f0000',NULL,'p9','p9',1),('88D1319C-FBAF-644C-901A-9F011225',NULL,'all agents','WinAgents',1);

#
# Structure for table "agentpoolagentmap"
#

DROP TABLE IF EXISTS `agentpoolagentmap`;
CREATE TABLE `agentpoolagentmap` (
  `id` varchar(32) NOT NULL,
  `agentpoolid` varchar(32) DEFAULT NULL,
  `agentid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `poolid` (`agentpoolid`),
  KEY `agentid` (`agentid`),
  CONSTRAINT `fk_pool_id` FOREIGN KEY (`agentpoolid`) REFERENCES `agentpool` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "agentpoolagentmap"
#

INSERT INTO `agentpoolagentmap` VALUES ('ff80808154675c6b0154675d08fe0003','402881eb53feb8eb0153feba056f0000','4028d081544613eb01544614a2900000'),('ff80808161ec084c0161ec09ee540016','88D1319C-FBAF-644C-901A-9F011225','12345678xftreqazwert');

#
# Structure for table "build"
#

DROP TABLE IF EXISTS `build`;
CREATE TABLE `build` (
  `id` varchar(32) NOT NULL,
  `buildno` int(11) NOT NULL DEFAULT '0',
  `createdate` timestamp NULL DEFAULT NULL,
  `enddate` timestamp NULL DEFAULT NULL,
  `fireWay` int(11) DEFAULT NULL,
  `projectid` varchar(32) DEFAULT NULL,
  `agentpoolid` varchar(32) DEFAULT NULL,
  `envid` varchar(32) DEFAULT NULL,
  `startdate` timestamp NULL DEFAULT NULL,
  `result` int(11) NOT NULL DEFAULT '1',
  `status` int(11) NOT NULL DEFAULT '1',
  `userid` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "build"
#

INSERT INTO `build` VALUES ('89f71c116218a5f4016218a66ca9000a',78,'2018-03-12 13:19:42','2018-03-12 13:19:43',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-12 13:19:42',2,4,1),('89f71c116218a5f4016218d3f6c70047',86,'2018-03-12 14:09:27','2018-03-12 14:09:27',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-12 14:09:27',2,4,1),('89f71c116218e7aa016218ea332c0010',91,'2018-03-12 14:33:44','2018-03-12 14:33:44',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-12 14:33:44',2,4,1),('89f71c116218ecbd016218ed3ac00000',92,'2018-03-12 14:37:03',NULL,1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-12 14:37:03',1,2,1),('89f71c116218ecbd016218ed4fdf0002',93,'2018-03-12 14:37:08',NULL,1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-12 14:37:08',1,2,1),('ff80808158fc33b70158fc37ae9c000f',36,'2016-12-14 15:24:17','2016-12-14 15:24:17',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2016-12-14 15:24:17',3,4,1),('ff808081598dc4a801598dd469f8003a',43,'2017-01-11 22:00:25','2017-01-11 22:00:25',1,'84D1319C-FBAF-644C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2017-01-11 22:00:25',2,4,1),('ff80808159c032040159c03257d30009',48,'2017-01-21 16:44:01','2017-01-21 16:44:02',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2017-01-21 16:44:01',2,4,1),('ff80808159c03c450159c03c91170009',49,'2017-01-21 16:55:11','2017-01-21 16:55:12',1,'15D1319C-FBAF-664C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2017-01-21 16:55:11',2,4,1),('ff80808161ec20980161ec20f5d10009',61,'2018-03-03 21:50:38','2018-03-03 21:50:39',1,'84D1319C-FBAF-644C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-03 21:50:38',2,4,1),('ff80808161f0975b0161f098c23e0009',68,'2018-03-04 18:39:58','2018-03-04 18:39:59',1,'84D1319C-FBAF-644C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-04 18:39:58',2,4,1),('ff80808161f0c2720161f0c40c5a000d',72,'2018-03-04 19:27:15','2018-03-04 19:27:16',1,'84D1319C-FBAF-644C-901A-8F091F25','88D1319C-FBAF-644C-901A-9F011225',NULL,'2018-03-04 19:27:15',2,4,1);

#
# Structure for table "env"
#

DROP TABLE IF EXISTS `env`;
CREATE TABLE `env` (
  `id` varchar(32) NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "env"
#

INSERT INTO `env` VALUES ('4028d081541a559b01541a563a450001','desc','env001'),('4028d081541a559b01541a563a450002','desc2','env002'),('4028d081541a559b01541a563a450003','desc3','env003'),('4028d081541a559b01541a563a450004','no','EnvGroupCore'),('4028d081541a559b01541a563a450005','noDesc','EnvBuildMs'),('4028d081541a559b01541a563a450006','noDesc6','EnvAgentBuildAix'),('4028d081541a559b01541a563a450007','DB2 ip db, user, pw','DB2_Env_IP255'),('4028d081541a559b01541a563a450008','mysql ip db, user, pw','MySQL_Env_IP142'),('4028d081541a559b01541a563a450009','derby ip db, user, pw','DERBY_Env_IP127'),('4028d081541a559b01541a563a450010','oracle ip db, user, pw','oracle_Env_IP255'),('4028d081541a559b01541a563a450011','sqlserver ip db, user, pw','SQLSERVER_Env_IP157'),('ff808081554423bc0155442416dc0000','desc04','env04');

#
# Structure for table "enventry"
#

DROP TABLE IF EXISTS `enventry`;
CREATE TABLE `enventry` (
  `id` varchar(32) NOT NULL,
  `envid` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `secure` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `value` varchar(256) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "enventry"
#

INSERT INTO `enventry` VALUES ('4028d081541a559b01541a563a450001','4028d081541a559b01541a563a450001','var1',0,1,'value001',1),('4028d081541a559b01541a563a450002','4028d081541a559b01541a563a450001','var2',0,1,'value002',2),('ff80808161f0c2720161f0c6a4fb0010','4028d081541a559b01541a563a450002','var1',1,1,'var001',1);

#
# Structure for table "plugin"
#

DROP TABLE IF EXISTS `plugin`;
CREATE TABLE `plugin` (
  `id` varchar(32) NOT NULL,
  `identifier` varchar(256) NOT NULL DEFAULT '',
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '1',
  `version` varchar(32) DEFAULT NULL,
  `hash` varchar(256) NOT NULL DEFAULT '',
  `data` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "plugin"
#

INSERT INTO `plugin` VALUES ('89f71c11621818eb0162181d74c00004','com.quickcd.plugin.demo','Demo','a java type plugin demo',1,'1','randomValueNow',NULL),('89f71c116218a5f4016218d1b1fd0040','com.quickcd.plugin.GroovyScriptUtils','GroovyScriptUtils','\n     A Groovy utils, which can run groovy script \n    ',1,'1','randomValueNow',NULL),('ff80808161f08e2d0161f08f01e80009','com.quickcd.plugin.FileUtils','FileUtils','\n     a java type plugin to create file or folder \n    ',1,'2','randomValueNow',NULL);

#
# Structure for table "plugin_step"
#

DROP TABLE IF EXISTS `plugin_step`;
CREATE TABLE `plugin_step` (
  `id` varchar(32) NOT NULL,
  `version` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `plugin_id` varchar(32) NOT NULL DEFAULT '',
  `description` varchar(1000) DEFAULT NULL,
  `pl_step_type` int(11) NOT NULL DEFAULT '1',
  `data` varchar(256) DEFAULT NULL,
  `script_lang` varchar(32) DEFAULT '',
  `validate_script` longtext,
  `post_process_script` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "plugin_step"
#

INSERT INTO `plugin_step` VALUES ('89f71c11621818eb0162181d74ce0005','1.0','step1-echo-script','89f71c11621818eb0162181d74c00004','execute the echo of property script',1,NULL,NULL,NULL,NULL),('89f71c116218a5f4016218d1b2090041','1.0','run a groovy script','89f71c116218a5f4016218d1b1fd0040','execute the groovy script',1,NULL,NULL,NULL,NULL),('ff80808161f08e2d0161f08f01ff000a','1.0','create a file','ff80808161f08e2d0161f08f01e80009','execute the file-creation script',1,NULL,NULL,NULL,NULL),('ff80808161f08e2d0161f08f022c000e','1.0','create a folder','ff80808161f08e2d0161f08f01e80009','execute the folder-creation script',1,NULL,NULL,NULL,NULL);

#
# Structure for table "plugin_step_prop"
#

DROP TABLE IF EXISTS `plugin_step_prop`;
CREATE TABLE `plugin_step_prop` (
  `id` varchar(32) NOT NULL,
  `version` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `plugin_step_id` varchar(32) NOT NULL DEFAULT '',
  `description` varchar(1000) DEFAULT NULL,
  `prop_type` varchar(32) NOT NULL DEFAULT '1',
  `label` varchar(32) NOT NULL DEFAULT '',
  `default_value` varchar(256) DEFAULT NULL,
  `required` int(11) DEFAULT NULL,
  `hidden` int(11) DEFAULT NULL,
  `prop_index` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "plugin_step_prop"
#

INSERT INTO `plugin_step_prop` VALUES ('89f71c11621818eb0162181d74d80006','1.0','scriptBody','89f71c11621818eb0162181d74ce0005','The body of the script.','1','Script Body','',1,1,0),('89f71c116218a5f4016218d1b20f0042','1.0','groovy_home','89f71c116218a5f4016218d1b2090041','The path of the groovy, needs 2.4.10+','1','GROOVY_HOME','=C:/F/groovy-2.4.10',1,1,0),('89f71c116218a5f4016218d1b21c0043','1.0','groovy_script','89f71c116218a5f4016218d1b2090041','The content of the script','4','groovy script','',1,1,1),('ff80808161f08e2d0161f08f0216000b','1.0','filename','ff80808161f08e2d0161f08f01ff000a','The name of the file.','1','File Name','file1.txt',1,1,0),('ff80808161f08e2d0161f08f0220000c','1.0','content','ff80808161f08e2d0161f08f01ff000a','The content of the file.','1','Content','',1,1,1),('ff80808161f08e2d0161f08f0226000d','1.0','overwrite','ff80808161f08e2d0161f08f01ff000a','when checked, if file already exists, file will be overwritten.','2','Overwrite if exists','',1,1,2),('ff80808161f08e2d0161f08f0231000f','1.0','foldername','ff80808161f08e2d0161f08f022c000e','The name of the folder.','1','Directory name','',1,1,0);

#
# Structure for table "plugin_step_prop_value"
#

DROP TABLE IF EXISTS `plugin_step_prop_value`;
CREATE TABLE `plugin_step_prop_value` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `value` varchar(20480) DEFAULT NULL,
  `plugin_step_prop_id` varchar(32) NOT NULL DEFAULT '',
  `step_id` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "plugin_step_prop_value"
#

INSERT INTO `plugin_step_prop_value` VALUES ('89f71c116218a5f4016218d2d7a70045','groovy_home','C:/F/groovy-2.4.10','89f71c116218a5f4016218d1b20f0042','89f71c116218a5f4016218d2d79f0044'),('89f71c116218a5f4016218d2d7e70046','groovy_script','import java.text.SimpleDateFormat\r\nfinal workDir = new File(\'.\').canonicalFile\r\nprintln \"currentDir %s\" \r\n\r\ndef date = new Date()\r\nsdf = new SimpleDateFormat(\"MM/dd/yyyy HH:mm:ss\")\r\nprintln sdf.format(date)\r\n\r\nint MAX = 2000;\r\nfor(int i  = 0 ; i<  MAX  ; i++) {\r\n    sleep(2)\r\n    println \"index:\" + i\r\n}\r\n\r\nprintln sdf.format(date)\r\n\r\ndate = new Date()\r\nsdf = new SimpleDateFormat(\"MM/dd/yyyy HH:mm:ss\")\r\nprintln sdf.format(date)','89f71c116218a5f4016218d1b21c0043','89f71c116218a5f4016218d2d79f0044'),('ff80808161f08e2d0161f08f8ddc0011','filename','file001.txt','ff80808161f08e2d0161f08f0216000b','ff80808161f08e2d0161f08f8dd10010'),('ff80808161f08e2d0161f08f8e140012','content','001','ff80808161f08e2d0161f08f0220000c','ff80808161f08e2d0161f08f8dd10010'),('ff80808161f08e2d0161f08f8e1c0013','overwrite','on','ff80808161f08e2d0161f08f0226000d','ff80808161f08e2d0161f08f8dd10010'),('ff80808161f08e2d0161f08fd9ae0015','filename','file001.txt','ff80808161f08e2d0161f08f0216000b','ff80808161f08e2d0161f08fd9a60014'),('ff80808161f08e2d0161f08fd9b90016','content','002','ff80808161f08e2d0161f08f0220000c','ff80808161f08e2d0161f08fd9a60014'),('ff80808161f08e2d0161f08fd9c40017','overwrite','on','ff80808161f08e2d0161f08f0226000d','ff80808161f08e2d0161f08fd9a60014');

#
# Structure for table "project"
#

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` varchar(255) NOT NULL,
  `name` varchar(32) NOT NULL DEFAULT '',
  `active` int(11) DEFAULT NULL,
  `agentpoolid` varchar(32) DEFAULT NULL,
  `data` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `envid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "project"
#

INSERT INTO `project` VALUES ('15D1319C-FBAF-664C-901A-8F091F25','demo_project',1,'88D1319C-FBAF-644C-901A-9F011225','','dir echo and groovy demo','4028d081541a559b01541a563a450003'),('84D1319C-FBAF-644C-901A-8F091F25','echo',1,'88D1319C-FBAF-644C-901A-9F011225',NULL,'echo project','4028d081541a559b01541a563a450002');

#
# Structure for table "result"
#

DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `id` varchar(32) NOT NULL,
  `buildid` varchar(32) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `stepname` varchar(255) DEFAULT NULL,
  `startdate` timestamp NULL DEFAULT NULL,
  `timeout` int(11) NOT NULL DEFAULT '0',
  `enddate` timestamp NULL DEFAULT NULL,
  `agentid` varchar(32) DEFAULT NULL,
  `envid` varchar(32) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  `cmd` varchar(255) DEFAULT NULL,
  `data` longtext,
  `continueOnFail` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "result"
#

INSERT INTO `result` VALUES ('89f71c116218a5f4016218a66cd4000b','89f71c116218a5f4016218a66ca9000a',1,'step02','2018-03-12 13:19:42',300,'2018-03-12 13:19:43','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\build\\78>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n03/04/2018  10:01    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n03/04/2018  10:02    <DIR>          BaiduYunDownload\r\n07/28/2017  15:57             2,550 cpsweb.log\r\n03/11/2018  12:06    <DIR>          D\r\n07/14/2017  17:03    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n03/08/2018  14:40    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n03/09/2018  17:09    <DIR>          F\r\n03/09/2018  21:45    <DIR>          FeigeDownload\r\n03/12/2018  12:01    <DIR>          fixletp2p\r\n03/10/2018  00:08    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n11/30/2017  09:49            13,899 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n03/11/2018  22:09    <DIR>          Logs\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n04/12/2017  09:02    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/07/2017  10:25    <DIR>          Program Files\r\n03/09/2018  21:22    <DIR>          Program Files (x86)\r\n01/25/2018  10:24    <DIR>          Python27\r\n03/12/2018  09:44    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n03/12/2018  10:14                42 setupisam.log\r\n03/12/2018  09:10           123,720 SUService.log\r\n01/30/2018  11:40    <DIR>          swd\r\n03/09/2018  21:30    <DIR>          System Volume Information\r\n11/28/2017  16:58    <DIR>          temp\r\n12/23/2016  08:48    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n03/12/2018  09:09    <DIR>          Windows\r\n              29 File(s)     12,778,520 bytes\r\n              27 Dir(s)  111,945,764,864 bytes free\r\n OK--win6121',1),('89f71c116218a5f4016218a66def000c','89f71c116218a5f4016218a66ca9000a',2,'run groovy script','2018-03-12 13:19:43',300,'2018-03-12 13:19:43','12345678xftreqazwert',NULL,5,'','--win6121',1),('89f71c116218a5f4016218d3f6d10048','89f71c116218a5f4016218d3f6c70047',1,'step02','2018-03-12 14:09:27',300,'2018-03-12 14:09:27','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\build\\86>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n03/04/2018  10:01    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n03/04/2018  10:02    <DIR>          BaiduYunDownload\r\n07/28/2017  15:57             2,550 cpsweb.log\r\n03/11/2018  12:06    <DIR>          D\r\n07/14/2017  17:03    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n03/12/2018  13:51    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n03/09/2018  17:09    <DIR>          F\r\n03/09/2018  21:45    <DIR>          FeigeDownload\r\n03/12/2018  12:01    <DIR>          fixletp2p\r\n03/10/2018  00:08    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n11/30/2017  09:49            13,899 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n03/11/2018  22:09    <DIR>          Logs\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n04/12/2017  09:02    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/07/2017  10:25    <DIR>          Program Files\r\n03/09/2018  21:22    <DIR>          Program Files (x86)\r\n01/25/2018  10:24    <DIR>          Python27\r\n03/12/2018  09:44    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n03/12/2018  10:14                42 setupisam.log\r\n03/12/2018  09:10           123,720 SUService.log\r\n01/30/2018  11:40    <DIR>          swd\r\n03/09/2018  21:30    <DIR>          System Volume Information\r\n11/28/2017  16:58    <DIR>          temp\r\n12/23/2016  08:48    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n03/12/2018  09:09    <DIR>          Windows\r\n              29 File(s)     12,778,520 bytes\r\n              27 Dir(s)  111,946,678,272 bytes free\r\n OK--win6121',1),('89f71c116218a5f4016218d3f7740049','89f71c116218a5f4016218d3f6c70047',2,'run groovy script','2018-03-12 14:09:27',300,'2018-03-12 14:09:28','12345678xftreqazwert',NULL,5,'','--win6121',1),('89f71c116218e7aa016218ea33360011','89f71c116218e7aa016218ea332c0010',1,'step02','2018-03-12 14:33:44',300,'2018-03-12 14:33:44','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\build\\91>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n03/04/2018  10:01    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n03/04/2018  10:02    <DIR>          BaiduYunDownload\r\n07/28/2017  15:57             2,550 cpsweb.log\r\n03/11/2018  12:06    <DIR>          D\r\n07/14/2017  17:03    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n03/12/2018  13:51    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n03/09/2018  17:09    <DIR>          F\r\n03/09/2018  21:45    <DIR>          FeigeDownload\r\n03/12/2018  12:01    <DIR>          fixletp2p\r\n03/10/2018  00:08    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n11/30/2017  09:49            13,899 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n03/11/2018  22:09    <DIR>          Logs\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n04/12/2017  09:02    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/07/2017  10:25    <DIR>          Program Files\r\n03/09/2018  21:22    <DIR>          Program Files (x86)\r\n01/25/2018  10:24    <DIR>          Python27\r\n03/12/2018  09:44    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n03/12/2018  10:14                42 setupisam.log\r\n03/12/2018  09:10           123,720 SUService.log\r\n01/30/2018  11:40    <DIR>          swd\r\n03/09/2018  21:30    <DIR>          System Volume Information\r\n11/28/2017  16:58    <DIR>          temp\r\n12/23/2016  08:48    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n03/12/2018  09:09    <DIR>          Windows\r\n              29 File(s)     12,778,520 bytes\r\n              27 Dir(s)  111,885,209,600 bytes free\r\n OK--win6121',1),('89f71c116218e7aa016218ea33db0012','89f71c116218e7aa016218ea332c0010',2,'run groovy script','2018-03-12 14:33:44',300,'2018-03-12 14:33:47','12345678xftreqazwert',NULL,4,'','OK--win6121',1),('89f71c116218ecbd016218ed3aec0001','89f71c116218ecbd016218ed3ac00000',1,'step02','2018-03-12 14:37:03',300,'2018-03-12 14:37:03',NULL,'4028d081541a559b01541a563a450001',5,'dir c:\\',NULL,1),('89f71c116218ecbd016218ed4feb0003','89f71c116218ecbd016218ed4fdf0002',1,'step02','2018-03-12 14:37:08',300,'2018-03-12 14:37:08',NULL,'4028d081541a559b01541a563a450001',5,'dir c:\\',NULL,1),('ff80808158fc33b70158fc37aea60010','ff80808158fc33b70158fc37ae9c000f',1,'step01','2016-12-14 15:24:17',300,'2016-12-14 15:24:17','12345678xftreqazwert',NULL,4,'echo 5','\r\nC:\\E\\quickagenttest\\var\\builds\\projects>echo 5 \r\n5\r\n OK--win6121',1),('ff80808158fc33b70158fc37af320011','ff80808158fc33b70158fc37ae9c000f',2,'step02','2016-12-14 15:24:17',300,'2016-12-14 15:24:17','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\builds\\projects>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n12/01/2016  21:52    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n10/23/2016  23:48    <DIR>          BaiduYunDownload\r\n05/16/2016  09:58             7,068 cpsweb.log\r\n12/12/2016  22:19    <DIR>          D\r\n04/25/2016  15:07    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n10/29/2016  14:52    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n12/05/2016  11:05    <DIR>          F\r\n12/14/2016  14:48    <DIR>          fixletp2p\r\n12/11/2016  11:15    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n11/11/2016  11:26             5,704 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n07/25/2016  17:30    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/09/2016  17:33    <DIR>          Program Files\r\n08/11/2016  12:13    <DIR>          Program Files (x86)\r\n12/14/2016  13:30    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n11/28/2016  13:45                42 setupisam.log\r\n12/14/2016  13:30            32,364 SUService.log\r\n12/12/2016  09:28    <DIR>          swd\r\n12/09/2016  17:32    <DIR>          System Volume Information\r\n09/05/2016  16:45    <DIR>          temp\r\n06/29/2010  12:27    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n11/30/2016  21:48    <DIR>          Windows\r\n              29 File(s)     12,683,487 bytes\r\n              24 Dir(s)  239,063,142,400 bytes free\r\n OK--win6121',1),('ff80808158fc33b70158fc37af960012','ff80808158fc33b70158fc37ae9c000f',3,'plStep03','2016-12-14 15:24:17',100,'2016-12-14 15:24:17','12345678xftreqazwert','4028d081541a559b01541a563a450001',5,'','null--win6121',1),('ff80808158fc33b70158fc37afdc0013','ff80808158fc33b70158fc37ae9c000f',4,'step4','2016-12-14 15:24:17',300,'2016-12-14 15:24:17','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'ver','\r\nC:\\E\\quickagenttest\\var\\builds\\projects>ver\r\n\r\nMicrosoft Windows [Version 6.1.7601]\r\n OK--win6121',1),('ff808081598dc4a801598dd46a09003b','ff808081598dc4a801598dd469f8003a',1,'step1','2017-01-11 22:00:25',350,'2017-01-11 22:00:25','12345678xftreqazwert',NULL,4,'echo step1','\r\nC:\\E\\quickagenttest\\var\\build\\project>echo step1 \r\nstep1\r\n OK--win6121',1),('ff80808159c032040159c03257f1000a','ff80808159c032040159c03257d30009',1,'step01','2017-01-21 16:44:01',300,'2017-01-21 16:44:02','12345678xftreqazwert',NULL,4,'echo 5','\r\nC:\\E\\quickagenttest\\var\\build\\project>echo 5 \r\n5\r\n OK--win6121',1),('ff80808159c032040159c0325887000b','ff80808159c032040159c03257d30009',2,'step02','2017-01-21 16:44:02',300,'2017-01-21 16:44:02','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\build\\project>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n12/27/2016  18:24    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n10/23/2016  23:48    <DIR>          BaiduYunDownload\r\n05/16/2016  09:58             7,068 cpsweb.log\r\n01/11/2017  22:03    <DIR>          D\r\n04/25/2016  15:07    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n01/04/2017  14:29    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n12/05/2016  11:05    <DIR>          F\r\n01/21/2017  16:22    <DIR>          fixletp2p\r\n01/01/2017  19:11    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n01/19/2017  10:51             7,250 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n01/06/2017  16:47    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/09/2016  17:33    <DIR>          Program Files\r\n12/29/2016  09:42    <DIR>          Program Files (x86)\r\n01/21/2017  14:24    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n01/12/2017  13:21                42 setupisam.log\r\n01/21/2017  14:24            39,526 SUService.log\r\n01/06/2017  16:56    <DIR>          swd\r\n01/18/2017  09:33    <DIR>          System Volume Information\r\n09/05/2016  16:45    <DIR>          temp\r\n12/23/2016  08:48    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n01/19/2017  10:51    <DIR>          Windows\r\n              29 File(s)     12,692,195 bytes\r\n              24 Dir(s)  225,638,375,424 bytes free\r\n OK--win6121',1),('ff80808159c032040159c03258f5000c','ff80808159c032040159c03257d30009',3,'plStep03','2017-01-21 16:44:02',100,'2017-01-21 16:44:02','12345678xftreqazwert','4028d081541a559b01541a563a450001',5,'','null--win6121',1),('ff80808159c032040159c0325959000d','ff80808159c032040159c03257d30009',4,'step4','2017-01-21 16:44:02',300,'2017-01-21 16:44:02','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'ver','\r\nC:\\E\\quickagenttest\\var\\build\\project>ver\r\n\r\nMicrosoft Windows [Version 6.1.7601]\r\n OK--win6121',1),('ff80808159c03c450159c03c9135000a','ff80808159c03c450159c03c91170009',1,'step01','2017-01-21 16:55:11',300,'2017-01-21 16:55:12','12345678xftreqazwert',NULL,4,'echo 5','\r\nC:\\E\\quickagenttest\\var\\build\\project>echo 5 \r\n5\r\n OK--win6121',1),('ff80808159c03c450159c03c91cb000b','ff80808159c03c450159c03c91170009',2,'step02','2017-01-21 16:55:12',300,'2017-01-21 16:55:12','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'dir c:\\','\r\nC:\\E\\quickagenttest\\var\\build\\project>dir c:\\ \r\n Volume in drive C has no label.\r\n Volume Serial Number is 0AA8-2520\r\n\r\n Directory of c:\\\r\n\r\n12/27/2016  18:24    <DIR>          Afolder\r\n04/11/2016  22:38                26 appname\r\n10/23/2016  23:48    <DIR>          BaiduYunDownload\r\n05/16/2016  09:58             7,068 cpsweb.log\r\n01/11/2017  22:03    <DIR>          D\r\n04/25/2016  15:07    <DIR>          drivers\r\n09/20/2016  21:53           339,385 DUMP1fb0.tmp\r\n01/04/2017  14:29    <DIR>          E\r\n03/23/2010  05:34    <DIR>          EFI\r\n04/11/2016  22:57         5,840,189 extras.cab\r\n12/05/2016  11:05    <DIR>          F\r\n01/21/2017  16:22    <DIR>          fixletp2p\r\n01/01/2017  19:11    <DIR>          G\r\n11/07/2007  08:00             1,110 globdata.ini\r\n08/19/2016  09:16    <DIR>          Go\r\n04/25/2016  10:29    <DIR>          ibmbeta\r\n11/07/2007  08:03           562,688 install.exe\r\n11/07/2007  08:00               843 install.ini\r\n01/19/2017  10:51             7,250 INSTALL.LOG\r\n11/07/2007  08:03            76,304 install.res.1028.dll\r\n11/07/2007  08:03            96,272 install.res.1031.dll\r\n11/07/2007  08:03            91,152 install.res.1033.dll\r\n11/07/2007  08:03            97,296 install.res.1036.dll\r\n11/07/2007  08:03            95,248 install.res.1040.dll\r\n11/07/2007  08:03            81,424 install.res.1041.dll\r\n11/07/2007  08:03            79,888 install.res.1042.dll\r\n11/07/2007  08:03            75,792 install.res.2052.dll\r\n11/07/2007  08:03            96,272 install.res.3082.dll\r\n04/12/2016  03:09    <DIR>          Intel\r\n04/11/2016  22:38           148,992 lai.exe\r\n12/02/2006  11:37           904,704 msdia80.dll\r\n01/06/2017  16:47    <DIR>          notes\r\n04/11/2016  22:38               334 osp.ini\r\n04/12/2016  06:39    <DIR>          ospw764\r\n07/14/2009  11:20    <DIR>          PerfLogs\r\n05/09/2016  12:14               476 pgpsync.log\r\n04/11/2016  22:38               818 pnp64.xml\r\n12/09/2016  17:33    <DIR>          Program Files\r\n12/29/2016  09:42    <DIR>          Program Files (x86)\r\n01/21/2017  14:24    <DIR>          sdwork\r\n10/11/2016  09:27         2,367,752 SEP_INST.log\r\n04/11/2016  22:38               176 servers.ini\r\n01/12/2017  13:21                42 setupisam.log\r\n01/21/2017  14:24            39,526 SUService.log\r\n01/06/2017  16:56    <DIR>          swd\r\n01/18/2017  09:33    <DIR>          System Volume Information\r\n09/05/2016  16:45    <DIR>          temp\r\n12/23/2016  08:48    <DIR>          Users\r\n11/07/2007  08:00             5,686 vcredist.bmp\r\n11/07/2007  08:09         1,442,522 VC_RED.cab\r\n11/07/2007  08:12           232,960 VC_RED.MSI\r\n04/11/2016  22:56    <DIR>          w764drive\r\n01/19/2017  10:51    <DIR>          Windows\r\n              29 File(s)     12,692,195 bytes\r\n              24 Dir(s)  225,638,457,344 bytes free\r\n OK--win6121',1),('ff80808159c03c450159c03c922f000c','ff80808159c03c450159c03c91170009',3,'plStep03','2017-01-21 16:55:12',100,'2017-01-21 16:55:12','12345678xftreqazwert','4028d081541a559b01541a563a450001',5,'','null--win6121',1),('ff80808159c03c450159c03c9293000d','ff80808159c03c450159c03c91170009',4,'step4','2017-01-21 16:55:12',300,'2017-01-21 16:55:12','12345678xftreqazwert','4028d081541a559b01541a563a450001',4,'ver','\r\nC:\\E\\quickagenttest\\var\\build\\project>ver\r\n\r\nMicrosoft Windows [Version 6.1.7601]\r\n OK--win6121',1),('ff80808161ec20980161ec20f604000a','ff80808161ec20980161ec20f5d10009',1,'step1 create file1','2018-03-03 21:50:38',300,'2018-03-03 21:50:39','12345678xftreqazwert',NULL,5,'','\r\nC:\\E\\quickagenttest\\var\\build\\project>java -cp C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/log4j.jar;C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/commons-lang3.jar;C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/fileUtils.jar com.yq.file.FileHelper C:\\Users\\IBM_AD~1\\AppData\\Local\\Temp\\yqdb805c070c84200080ec0000e5dda7f7.props \r\nargs[0]:C:\\Users\\IBM_AD~1\\AppData\\Local\\Temp\\yqdb805c070c84200080ec0000e5dda7f7.props\r\ncurrentDir:C:\\E\\quickagenttest\\var\\build\\project\\.\r\nfile001.txt\r\n--win6121',1),('ff80808161ec20980161ec20f8d7000b','ff80808161ec20980161ec20f5d10009',2,'step2 create folder','2018-03-03 21:50:39',300,'2018-03-03 21:50:39','12345678xftreqazwert',NULL,5,'','\r\nC:\\E\\quickagenttest\\var\\build\\project>java -cp C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/log4j.jar;C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/commons-lang3.jar;C:\\E\\quickagenttest\\var\\plugin\\FileUtils_1/lib/fileUtils.jar com.yq.folder.FolderHelper C:\\Users\\IBM_AD~1\\AppData\\Local\\Temp\\yqdb805e2c0c84200080ee0000e5dda7f7.props \r\nargs[0]:C:\\Users\\IBM_AD~1\\AppData\\Local\\Temp\\yqdb805e2c0c84200080ee0000e5dda7f7.props\r\ncurrentDir:C:\\E\\quickagenttest\\var\\build\\project\\.\r\ndir002\r\n--win6121',1),('ff80808161f0975b0161f098c261000a','ff80808161f0975b0161f098c23e0009',1,'step1','2018-03-04 18:39:58',300,'2018-03-04 18:39:59','12345678xftreqazwert',NULL,5,'','--win6121',1),('ff80808161f0975b0161f098c574000b','ff80808161f0975b0161f098c23e0009',2,'step002','2018-03-04 18:39:59',300,'2018-03-04 18:39:59','12345678xftreqazwert',NULL,5,'','--win6121',1),('ff80808161f0c2720161f0c40c68000e','ff80808161f0c2720161f0c40c5a000d',1,'step1','2018-03-04 19:27:15',300,'2018-03-04 19:27:16','12345678xftreqazwert',NULL,4,'','OK--win6121',1),('ff80808161f0c2720161f0c40e67000f','ff80808161f0c2720161f0c40c5a000d',2,'step002','2018-03-04 19:27:16',300,'2018-03-04 19:27:16','12345678xftreqazwert',NULL,4,'','OK--win6121',1);

#
# Structure for table "schedule"
#

DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `uuid` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(32) NOT NULL DEFAULT '',
  `active` int(11) DEFAULT NULL,
  `data` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "schedule"
#

INSERT INTO `schedule` VALUES ('15D1319C-FBAF-664C-901A-8F091F25','Every 5 minutes',0,'0 0/4 * * * ?','5 minutes'),('99D2329C-FBAF-644C-901A-8F091F25','every night',0,'0 0/5 * * * ?','every night');

#
# Structure for table "scheduletrigger"
#

DROP TABLE IF EXISTS `scheduletrigger`;
CREATE TABLE `scheduletrigger` (
  `uuid` varchar(32) NOT NULL DEFAULT '',
  `scheduuid` varchar(32) NOT NULL DEFAULT '',
  `projuuid` varchar(32) NOT NULL DEFAULT '',
  `active` int(11) DEFAULT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "scheduletrigger"
#

INSERT INTO `scheduletrigger` VALUES ('10D1319C-FBAF-664C-901A-8F091F25','15D1319C-FBAF-664C-901A-8F091F25','ff8080815568d52f015568d5b8ac0000',0),('15D1319C-FBAF-664C-901A-8F091F25','15D1319C-FBAF-664C-901A-8F091F25','84D1319C-FBAF-644C-901A-8F091F25',0),('84D1319C-FBAF-644C-901A-8F091F25','99D2329C-FBAF-644C-901A-8F091F25','92D1319C-FBAF-654C-901A-8F091F25',0),('ff8080815617aff3015617b079ae0000','15D1319C-FBAF-664C-901A-8F091F25','ff8080815568d52f015568d5b8ac0000',0);

#
# Structure for table "setting"
#

DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting` (
  `id` varchar(32) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `value` varchar(256) DEFAULT NULL,
  `data` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "setting"
#

INSERT INTO `setting` VALUES ('12345678xftreqazwert','BUILD_ID_SEQ','build id seq','94',NULL),('qazw5678xftreqazqwer','BASE_URL','login url','http://localhost:9090/QuickCD',NULL);

#
# Structure for table "step"
#

DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
  `id` varchar(32) NOT NULL,
  `seq` int(11) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `projectid` varchar(32) DEFAULT NULL,
  `agentpoolid` varchar(32) DEFAULT NULL,
  `envid` varchar(32) DEFAULT NULL,
  `timeout` int(11) DEFAULT '300',
  `cmd` varchar(256) DEFAULT NULL,
  `continueOnFail` int(11) NOT NULL DEFAULT '1',
  `plugin_step_id` varchar(32) DEFAULT NULL,
  `concurrent` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

#
# Data for table "step"
#

INSERT INTO `step` VALUES ('89f71c116218a5f4016218d2d79f0044',2,'run groovy script','',1,'15D1319C-FBAF-664C-901A-8F091F25',NULL,NULL,300,'',1,'89f71c116218a5f4016218d1b2090041',0),('ff80808158fc215b0158fc2421820009',1,'step02','dir c:\\  to see all dirs in c',1,'15D1319C-FBAF-664C-901A-8F091F25',NULL,'4028d081541a559b01541a563a450001',300,'dir c:\\',1,NULL,0),('ff80808161f08e2d0161f08f8dd10010',1,'step1','',1,'84D1319C-FBAF-644C-901A-8F091F25',NULL,NULL,300,'',1,'ff80808161f08e2d0161f08f01ff000a',0),('ff80808161f08e2d0161f08fd9a60014',2,'step002','',1,'84D1319C-FBAF-644C-901A-8F091F25',NULL,NULL,300,'',1,'ff80808161f08e2d0161f08f01ff000a',0);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `can_delete` int(11) NOT NULL DEFAULT '1',
  `data` varchar(256) DEFAULT NULL,
  `dateformat` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `fullname` varchar(32) DEFAULT NULL,
  `language` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `timeformat` varchar(32) DEFAULT NULL,
  `timezone` varchar(32) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `usertype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,1,0,NULL,'yy-MM-dd','admin@qq.com','admin user','en_US','admin','HH:mm:ss','VST','admin',1),(2,1,0,NULL,'yyyy/MM/dd','build@qq.com','build user','ja_JP','','HH:mm:ss','UTC','build',1),(3,1,0,NULL,'yyyy/MM/dd','guest@qq.com','guest user','ja_JP','guest','hh:mm:ss a zzz','Asia/Shanghai','guest',1),(4,1,1,NULL,'dd/MM/yy','qqq','qq','en_US','qq','HH:mm','GMT+8','qq',1),(5,1,1,NULL,'yyyy-MM-dd','aaaa','u5full','zh_CN','','HH:mm:ss','America/Grand_Turk','u5',2);
