/*
 Navicat Premium Data Transfer

 Source Server         : 数据库1
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : movie_buy

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 19/06/2022 20:18:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cinema
-- ----------------------------
DROP TABLE IF EXISTS `cinema`;
CREATE TABLE `cinema`  (
  `cinema_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '影院id',
  `cinema_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院名字',
  `cinema_address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院地址',
  `cinema_worktime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院营业时间',
  `cinema_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院联系电话',
  `cinema_username` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院用户名',
  `cinema_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影院ima',
  PRIMARY KEY (`cinema_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cinema
-- ----------------------------
INSERT INTO `cinema` VALUES (1, '踏雪寻梅影院', '广西科技师范学院6栋218', '9：00-22：00', '620-220-100', 'txxm', 'txxm');
INSERT INTO `cinema` VALUES (2, '王侯将相影院', '广西科技师范谢园6栋219', '8：00-21：00', '620-220-101', 'whjx', 'whjx');
INSERT INTO `cinema` VALUES (3, '卿本佳人影院', '广西科技师范学院6栋220', '9：00-22：00', '620-220-102', 'qbjr', 'qbjr');

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `movie_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '影片id',
  `movie_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片名字',
  `movie_img` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片封面',
  `movie_director` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片导演',
  `movie_actor` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片演员',
  `movie_types` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片类型',
  `movie_uptime` date NOT NULL COMMENT '影片上映时间',
  `movie_duration` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片时长',
  `movie_detail` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '影片详情',
  `movie_status` int(1) NOT NULL COMMENT '影片是否在上映，0为未上映，1为上映。',
  PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '霸王别姬', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsh.yuloo.com%2Fd%2Ffile%2Fzaizhi%2Frenda%2Fnews%2F2018-10-22%2Fdaaaaec913bbc14a9467e2307fdb0da2.jpg&refer=http%3A%2F%2Fsh.yuloo.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655101787&t=665d2410c6f823972a5ba3151201ef28', '陈凯歌', '张国荣、巩俐、张丰毅、葛优、英达、雷汉 ', '剧情、爱情', '2022-05-12', '171', '段小楼（张丰毅 饰）与程蝶衣（张国荣 饰）是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城，为此，两人约定合演一辈子《霸王别姬》。但两人对戏剧与人生关系的理解有本质不同，段小楼深知戏非人生，程蝶衣则是人戏不分。段小楼在认为该成家立业之时迎娶了名妓菊仙（巩俐 饰），致使程蝶衣认定菊仙是可耻的第三者，使段小楼做了叛徒...', 0);
INSERT INTO `movie` VALUES (2, '喜欢你', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fupload.mnw.cn%2F2017%2F0427%2F1493276371510.jpg&refer=http%3A%2F%2Fupload.mnw.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657943373&t=d3e89458d439e99aa5ff0a01d9e81b20', '许宏宇', '金城武、周冬雨、孙艺洲、奚梦瑶、林志玲、杨祐宁、高晓松', '爱情，剧情', '2022-05-12', '171', '29岁的顾胜男（周冬雨饰）是一家百老汇酒店西餐厅的二厨，一直与酒店总经理秘密恋爱，结果酒店遭遇经营危机，总经理提出分手。顾胜男在惨遭失恋、事业双重打击后，遇见了前来收购酒店的挑剔、毒舌且自大的路晋（金城武饰）。性格各异的两人在鸡飞狗跳的相处过程中产生了情愫，路晋最终成为忠犬男友。', 0);
INSERT INTO `movie` VALUES (3, '太平轮（上）', 'https://img2.baidu.com/it/u=1711860519,2330522125&fm=253&fmt=auto&app=138&f=JPEG?w=423&h=564', '吴宇森', '章子怡、金城武、黄晓明、宋慧乔、佟大为、长泽雅美', '剧情/爱情/战争/灾难', '2022-05-12', '171', '1945年夏，国军将军雷义方（黄晓明饰）在平原战场率兵大败日军。国军通讯兵佟大庆（佟大为饰）俘获了台湾籍日军军医严泽坤（金城武饰）。日军投降之后，三人各行其道。雷义方回到了上海，认识了豪门千金周蕴芬（宋慧乔饰）。两人一见钟情，不久成婚。但随即，内战爆发了。\r\n严泽坤在抗战后回到台湾，却发现曾经的恋人日本女孩雅子（长泽雅美饰）已经被遣返回日本。佟大庆则爱上了白天做护士照顾伤兵，晚上在街头招客的妓女于真（章子怡饰），两人原本各取所需照了张“结婚照”，没想到真的成了一对苦恋的情侣。\r\n国共一场大战，国民党军全面溃败。众人都想登上太平轮，离开上海去台湾。这艘船成了最后的希望。没想到，意外的沉船改变了所有人的命运，船上近千人绝大多数都在海难中遇难。', 1);
INSERT INTO `movie` VALUES (4, '太平轮（下）', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.doubanio.com%2Fview%2Fgroup_topic%2Fl%2Fpublic%2Fp40344965.jpg&refer=http%3A%2F%2Fimg9.doubanio.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657943800&t=bbd62dc680dc3a6f082dfef6adf6a8c6', '陈凯歌', '张国荣、巩俐、张丰毅、葛优、英达、雷汉 ', '古装', '2022-05-12', '171', '战争年代，时局动荡，人民颠沛流离。 三对不同背景的主人公被卷入硝烟与战火，与挚爱分离。国军军官雷义方（黄晓明饰）生死不明，太太（宋慧乔饰）独在台湾饱受相思之苦；军医严泽坤（金城武饰）与青梅竹马的日本女孩雅子（长泽雅美饰）因政局变化被分隔，再难相见；底层女性于真（章子怡饰）和通信兵（佟大为饰）萍水相逢，又被乱世冲散；尽管命途颠簸，但他们仍不惜与命运奋力抗争，书写了战乱年代的悲欢离合。最终，命运的齿轮将所有人卷入逃难的太平轮上，他们会如何相遇，各自的命运又将何去何从？\r\n重点讲述众人上船之后的生离死别，有人舍生取义，有人苟且偷生，有人死去，有人生还，真正的惊心动魄、悲喜人性即将开启画幕，描绘一幅大灾难中乱世求生的史诗浮世绘。', 1);
INSERT INTO `movie` VALUES (5, '武侠', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2Fd089b986eba9fb5f66096e04&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657943735&t=37bb3443e9d2833765f50e1d5a5e86ca', '陈可辛', '甄子丹、金城武、汤唯、李小冉', '武侠，动作', '2022-05-12', '171', '清朝末年，刘金喜（甄子丹饰）是一个与两个儿子在偏远小镇安度日子的造纸工人，平静的生活即将被一个闯入的侦探给打破。\r\n徐百九（金城武饰）为了调查两个强盗之死而来到小镇，看似意外死于刘金喜的自卫反应的两人，居然竟是武功高强的十大逃犯，一个手无寸铁的村民如何能够打死他们呢？对武术颇有研究的徐百九认定这绝不是意外，刘金喜必然是身怀绝技才能如此杀人于无形。随着追查的深入，徐百九发现他与72地煞——江湖中最为残暴邪恶、杀人不眨眼的组织联系紧密。而72地煞的教主，武艺则更是深不可测，无人能及。', 0);
INSERT INTO `movie` VALUES (6, '伤城', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.sinaimg.cn%2Fent%2Fm%2F2008-02-02%2FU1584P28T3D1901561F326DT20080202140956.jpg&refer=http%3A%2F%2Fi0.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657945034&t=e9a41fbacdb2dfa11d76765f0e594793', '刘伟强', '梁朝伟、金城武、徐静蕾、舒淇', '剧情/惊悚/犯罪', '2022-05-12', '171', '2003年平安夜，凶杀组探员丘建邦(金城武饰)拘捕了一个连环奸杀犯。圣诞日的早上回到家中，发现Rachel在床上自杀身亡，Rachel是他的女友。验尸后，丘建邦方知她刚刚怀有两个月多身孕。之后，丘建邦转行当了三年私家侦探，同时他变成一个酒鬼。\r\n2006年，丘建邦最好的朋友，乒乓球桌上最好的对手、最信赖的上司凶杀组总督察刘正熙(梁朝伟饰)谋杀了他太太金淑珍(徐静蕾饰)的父亲——隐形富豪金元胜。干了凶杀组头目多年，设计这次谋杀案当然是天衣无缝。刘正熙刻意留下的每一个证据，将矛头指向两名无业游民陈伟强、黄斌。案发后一星期，陈伟强和黄斌伏尸西贡村屋，证据显示二人分赃不均、自相残杀，警方及律政署迅速结案。', 1);
INSERT INTO `movie` VALUES (7, '不夜城', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.wahouse.com.tw%2Fblog%2Fimages%2Fupimages%2Fw1c3p306o2n5p0q8e0x7.jpg&refer=http%3A%2F%2Fwww.wahouse.com.tw&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944208&t=f4042bbf9081c6a5246791332f49c199', '李志毅', '金城武、山本未来、椎名桔平、郎雄、曾志伟', '剧情', '2022-05-12', '171', '歌舞伎町——混杂了不同的民族和语言、谋权和贪欲；被上帝遗忘的地方。刘健一，就是在这样的地方以买卖赃物维生，身为中国台湾人与日本人的混血儿，被叫作半唐番，既不为日本人所接受，亦不为中国台湾人社会所容纳；健一，从不相信、亦不倚赖任何人，只对人性的贪婪感兴趣，触角特别敏锐，像飞舞于夜空的蝙蝠，生活于险恶中。', 0);
INSERT INTO `movie` VALUES (8, '马永贞', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimage11.m1905.cn%2Fuploadfile%2F2012%2F1101%2F20121101042320431.jpg&refer=http%3A%2F%2Fimage11.m1905.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944269&t=7aa2de59860daca4577d7b17cafa2a1b', '元奎', '金城武、宣萱、元彪', '动作/剧情', '2022-05-12', '171', '1898年山东大旱，无数难民涌入上海求生，人流中马永贞（金城武 饰）、马大祥（元华 饰）兄弟俩亦来到这片英国人掌控下的东方乐园，二马无技傍身，只好从码头苦力做起。马永贞机缘巧合结识上海码头之王谭四（元彪 饰），并凭借出色身手获得其欣赏。其时谭四与巡捕房支持的杨双连番血战，马永贞遭遇混战，为谭四的一壶春舞厅出手击退强敌，对卖场其中的歌女金铃子（宣萱 饰）一见钟情。谭四感佩马永贞义气，将一壶春和老板娘艳阳天一同赠送之。马永贞遂以一壶春为起点，率众攻占杨双地盘，激起上海滩无数杀伐。马永贞遭遇杨双暗算，命悬一线之际却再遇金铃子……', 1);
INSERT INTO `movie` VALUES (9, '重庆森林', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.thehour.cn%2F2021%2F09%2F16%2F16317699703738488007955849548962.jpeg%3Fw%3D720%26h%3D1041&refer=http%3A%2F%2Fimg.thehour.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944425&t=8a29a313e8e366de9ec237d4e1d509b7', '王家卫', '梁朝伟、王菲、金城武、林青霞', '剧情、悬疑、爱情', '2022-05-12', '171', '警察阿武处于失恋综合症中，一日在街头与一金发女子擦肩而过，金发女子是一名杀手。56小时后，两人在酒吧偶遇，并相处一晚。在阿武预感情感过期的早上，收到了金发女子的生日祝福：“这一刻是永远不会过期的。如果要加一个期限的话，我想是：一万年。”\r\n阿武经常光顾的快餐店来了新的女招应阿菲，她爱上了警察663。663的女友离开了他，而阿菲拆了663女友留在快餐店给他的信，拿了663家的钥匙，趁663不在时去他家悄悄地改变663原来的生活。终于有一天被663撞见，663忽然感到感情的回归。当晚，663约阿菲见面，却得知阿菲去了美国，说一年后见面。一年后，成为空姐的阿菲回来了，而此时663已不再是警察，而成为快餐店老板。两人相见，663说跟阿菲去哪都可以', 1);
INSERT INTO `movie` VALUES (10, '赤壁（上）', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F24262ae29b5918bb544c1c736e96bb2cb178e434146e03-8ErxMQ_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944482&t=a756d9f5f19def544b8c43b81e1b9e6b', '吴宇森', '梁朝伟、金城武、张震、林志玲、张丰毅、胡军、中村狮童', '历史、古装、战争', '2022-05-12', '171', '东汉末年，汉室幼主难当大局，朝廷中曹操（张丰毅饰）独断专行，挟天子以令诸侯，大夫孔融（王庆祥饰）誓死直谏，结果落得给曹兵祭旗的下场。原来曹操决心已定，欲剿灭刘备（尤勇饰）、孙权（张震饰）等江南反贼，一统天下。长坂坡一战后，刘备在军师诸葛亮（金城武饰）的辅佐之下，率结义弟兄关羽、张飞（臧金生饰）、赵云（胡军饰）等，带领难民且战且退，最后只得求助于江东孙权。对于抗曹之事，孙权亦正犹豫不决。但大都督周瑜（梁朝伟饰）与其妹孙尚香均力挺其出战，眼看大兵压境，孙刘联盟方现雏形。诸葛亮观周瑜严明治军，旗下大将甘兴（中村狮童饰）身先士卒，颇感欣慰。此刻突然传来军中箭弩严重缺乏，孔明不顾鲁肃（侯勇饰）好心劝阻，甘愿立下军令状，谋求箭弩', 1);
INSERT INTO `movie` VALUES (11, '赤壁（下）', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg31.mtime.cn%2Fpi%2F2014%2F02%2F27%2F233311.62759990_1000X1000.jpg&refer=http%3A%2F%2Fimg31.mtime.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944571&t=7e5b691893595a4a3f7afa3babebd6de', '吴宇森', '梁朝伟、金城武、张丰毅、张震、林志玲', '历史、古装、战争', '2022-05-12', '171', '大战在即，此时联军兵力不占优，同时亟需箭枝。诸葛亮便设下圈套，趁机削弱曹军的实力。他派二十只草船首尾相连，趁着浓雾连夜佯攻曹操的水军。曹操不知是计，传令不可妄动，只叫水陆两军的弓箭手向江上射箭。结果，二十只草船满载十万余支箭胜利返航。次日清晨，曹操怀疑军中藏有奸细，并且发现了他的两位水军头领写给周瑜的书信（实为周瑜假造），信中声言送十万支箭作为投靠的礼物。曹操大怒，命将二人处斩。众水兵恐受连累，四散逃命，结果皆被俘获，并以叛降之罪处死。尽管如此，曹操仍然执意在数日后攻打联军。他慷慨陈词，终于扭转了军中的士气', 1);
INSERT INTO `movie` VALUES (12, '十面埋伏', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi-7.vcimg.com%2Ftrim%2F8824d7740425bc3dfe04b52138c55219690451%2Ftrim.jpg&refer=http%3A%2F%2Fi-7.vcimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944712&t=0c91fd0544c10411854c220be4770b2e', '张艺谋', '金城武、刘德华、章子怡、宋丹丹', '爱情、动作、奇幻', '2022-05-12', '171', '奉天县两大捕头：刘捕头、金捕头奉命于10日之内，将飞刀门新任帮主缉拿归案。刘捕头怀疑新店牡丹坊的舞妓小妹是飞刀门前帮主柳云飞的女儿，于是用计将她拿下，押入天牢。两人并再度设下圈套：由金捕头化名随风大侠，乘夜劫狱，救出小妹；藉此骗取小妹的信任，查出飞刀门的巢穴，以便一举剿灭。', 1);
INSERT INTO `movie` VALUES (13, '投名状', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fe362bbd7a39e349ee6cbea699a67a608cd85acbc14c5d-2hx64h_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944996&t=b76ea9dadd328e4e436f9c6be6179739', '陈可辛', '李连杰、刘德华、金城武、徐静蕾', '动作，剧情，战争，历史', '2022-05-12', '171', '1870年秋，大哥庞青云(李连杰 饰演)一身官服，在城墙顶俯瞰自己的就职典礼舞台；充满理想、胸怀壮志的他，如果没有走上这条不归路，极可能是推翻腐败清庭的新中国革命者之一，创大事业名留青史，可惜两个强盗和一个女人改变了他的一生，造就了他的成功，亦导致他最后的毁灭。这两个强盗是曾跟他矢誓生死与共的结拜兄弟：二哥赵二虎(刘德华 饰演)和三弟姜午阳(金城武 饰演)，而那个女人正是二哥的妻子莲生(徐静蕾 饰演)。', 1);
INSERT INTO `movie` VALUES (14, '如果·爱', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic3.zhimg.com%2Fv2-9640c39b36714a12d71d60326c2554ee_qhd.jpg&refer=http%3A%2F%2Fpic3.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944815&t=d0516d4c7d59db5b6f3093d9791e7b48', '陈可辛', '金城武、周迅、张学友、池珍熙', '戏曲', '2022-05-12', '171', '1995年的北京，林见东前往北京去读电影，他一心想挑战自己读电影导演系的野心，却惨遭滑铁卢。他的成绩只是平平，理想完全破灭。就在心灰意冷之际，他遇上了孙纳，当时，孙纳在一个歌舞团当洗烫工，却已雄心壮志，努力要成为一流的歌舞演员，同时她更身兼多职，赚钱养活湖南乡下的家人，对于只会伸开手掌问家人索取生活费的见东来说，孙纳顿时成为他崇拜的对象，鼓舞他重燃斗志。\r\n这一段情缘，本来可以有个美好的回忆，可是见东发现孙纳为了多赚一点钱，竟不择手段，天真的见东一心为了帮孙纳找岀路，介绍她认识一些电影制作人，孙纳竟暗地出卖了他；孙纳从此消失，见东的大学生活也结束。', 1);
INSERT INTO `movie` VALUES (15, '心动', 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2F00.minipic.eastday.com%2F20170105%2F20170105013318_a7cf39dae8dcac65d089901d122a53a8_16.jpeg&refer=http%3A%2F%2F00.minipic.eastday.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657944923&t=8be6b4a0b0ff45c2ef4190e81d639d38', '张艾嘉', '金城武、梁咏琪、莫文蔚、张艾嘉、苏永康、戴立忍', '爱情', '2022-05-12', '171', '一位女导演Cheryl（张艾嘉饰）要拍一部关于初恋的爱情故事，找了编剧（苏永康饰）一起谈剧本，将她心目中故事的开始设定于1977年，女主角是十七岁的小柔（梁咏琪饰），在一间女校念书，男主角是十九岁的浩君（金城武饰），因没考上大学，正在家中自修准备再一次冲刺，但是他一心只喜欢弹奏吉他，直到遇见了让他心动不已的小柔。浩君与小柔陷入热恋，爱得彻底、直接、昏天暗地，除了没有发生肉体关系之外，其他全部付出，而小柔身边的好友陈莉（莫文蔚饰），则夹在这对恋人之间，除了是小柔倾诉的对象，也是小柔向家人撒谎外出的好借口，当然更是她与浩君之间最适当的传声筒，而三角恋曲的微妙情愫似乎也发生在这三人之间。', 1);

-- ----------------------------
-- Table structure for order1
-- ----------------------------
DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1`  (
  `order_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NULL DEFAULT NULL,
  `schedule_id` int(8) NOT NULL,
  `seat_id` int(8) NOT NULL,
  `order_createtime` datetime(0) NOT NULL,
  `order_status` int(1) NULL DEFAULT NULL COMMENT '订单状态：1为订单完成，0为订单取消，2为订单进行中',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order1
-- ----------------------------
INSERT INTO `order1` VALUES (5, 4, 1, 3, '2022-06-16 12:35:28', 0);
INSERT INTO `order1` VALUES (41, 4, 47, 41, '2022-06-19 13:56:21', 1);
INSERT INTO `order1` VALUES (49, 4, 47, 43, '2022-06-19 15:56:55', 1);
INSERT INTO `order1` VALUES (50, 4, 47, 41, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (51, 4, 47, 42, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (52, 4, 47, 43, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (53, 4, 47, 44, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (54, 4, 47, 45, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (55, 4, 47, 46, '2022-06-19 16:01:21', 1);
INSERT INTO `order1` VALUES (56, 4, 47, 41, '2022-06-19 16:05:50', 1);
INSERT INTO `order1` VALUES (59, 4, 47, 44, '2022-06-19 16:05:50', 2);
INSERT INTO `order1` VALUES (60, 4, 47, 45, '2022-06-19 16:05:50', 0);

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `schedule_id` int(8) NOT NULL AUTO_INCREMENT,
  `cinema_id` int(8) NOT NULL,
  `movie_id` int(8) NOT NULL,
  `schedule_begintime` datetime(0) NOT NULL,
  `schedule_endtime` datetime(0) NOT NULL,
  `price` double(10, 2) NOT NULL,
  PRIMARY KEY (`schedule_id`) USING BTREE,
  INDEX `cinema_id`(`cinema_id`) USING BTREE,
  INDEX `movie_id`(`movie_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, 1, 1, '2022-05-12 09:00:00', '2022-05-12 12:50:00', 19.00);
INSERT INTO `schedule` VALUES (2, 1, 2, '2022-05-12 13:00:00', '2022-05-12 15:30:00', 20.50);
INSERT INTO `schedule` VALUES (3, 1, 3, '2022-05-12 16:00:00', '2022-05-12 17:30:00', 25.00);
INSERT INTO `schedule` VALUES (4, 2, 4, '2022-05-12 08:00:00', '2022-05-12 10:30:00', 20.20);
INSERT INTO `schedule` VALUES (5, 2, 5, '2022-05-12 11:00:00', '2022-05-12 13:00:00', 25.80);
INSERT INTO `schedule` VALUES (47, 1, 6, '2022-05-12 11:00:00', '2022-05-12 11:00:00', 12.00);
INSERT INTO `schedule` VALUES (49, 2, 6, '2022-05-12 11:00:00', '2022-05-12 11:00:00', 15.60);

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `seat_id` int(8) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(8) NOT NULL,
  `seat_row` int(2) NOT NULL,
  `seat_col` int(2) NOT NULL,
  `seat_status` int(1) NOT NULL,
  PRIMARY KEY (`seat_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seat
-- ----------------------------
INSERT INTO `seat` VALUES (1, 1, 1, 1, 0);
INSERT INTO `seat` VALUES (2, 1, 1, 2, 0);
INSERT INTO `seat` VALUES (3, 1, 2, 1, 0);
INSERT INTO `seat` VALUES (4, 1, 2, 2, 0);
INSERT INTO `seat` VALUES (5, 5, 1, 1, 0);
INSERT INTO `seat` VALUES (6, 5, 1, 2, 0);
INSERT INTO `seat` VALUES (7, 5, 2, 1, 0);
INSERT INTO `seat` VALUES (8, 5, 2, 2, 0);
INSERT INTO `seat` VALUES (9, 2, 1, 1, 0);
INSERT INTO `seat` VALUES (10, 2, 1, 2, 0);
INSERT INTO `seat` VALUES (11, 2, 2, 1, 0);
INSERT INTO `seat` VALUES (12, 2, 2, 2, 0);
INSERT INTO `seat` VALUES (13, 3, 1, 1, 0);
INSERT INTO `seat` VALUES (14, 3, 1, 2, 0);
INSERT INTO `seat` VALUES (15, 3, 2, 1, 0);
INSERT INTO `seat` VALUES (16, 3, 2, 2, 0);
INSERT INTO `seat` VALUES (17, 4, 1, 1, 0);
INSERT INTO `seat` VALUES (18, 4, 1, 2, 0);
INSERT INTO `seat` VALUES (19, 4, 2, 1, 0);
INSERT INTO `seat` VALUES (20, 4, 2, 2, 0);
INSERT INTO `seat` VALUES (41, 47, 1, 1, 0);
INSERT INTO `seat` VALUES (42, 47, 1, 2, 0);
INSERT INTO `seat` VALUES (43, 47, 1, 3, 1);
INSERT INTO `seat` VALUES (44, 47, 2, 1, 1);
INSERT INTO `seat` VALUES (45, 47, 2, 2, 0);
INSERT INTO `seat` VALUES (46, 47, 2, 3, 0);
INSERT INTO `seat` VALUES (47, 49, 1, 1, 0);
INSERT INTO `seat` VALUES (48, 49, 1, 2, 0);
INSERT INTO `seat` VALUES (49, 49, 2, 1, 0);
INSERT INTO `seat` VALUES (50, 49, 2, 2, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'a', 'a', 'a@qq.com', '12345678910');
INSERT INTO `user` VALUES (2, 'b', 'b', 'b@qq.com', '12345678911');
INSERT INTO `user` VALUES (3, 'c', 'c', 'c@qq.com', '12345678912');
INSERT INTO `user` VALUES (4, 'admin', 'admin', NULL, NULL);
INSERT INTO `user` VALUES (6, '1', '1', '1', '');

SET FOREIGN_KEY_CHECKS = 1;
