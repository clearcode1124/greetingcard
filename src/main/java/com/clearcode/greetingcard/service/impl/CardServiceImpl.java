package com.clearcode.greetingcard.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.clearcode.greetingcard.domain.Contact;
import com.clearcode.greetingcard.domain.Contacts;
import com.clearcode.greetingcard.domain.Greeting;
import com.clearcode.greetingcard.service.CardService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CardServiceImpl implements CardService {

	@Override
	public String gen() {
		String s = "<p>【<strong>导语</strong>】大年三十福来登，合家欢乐喜庆呈，安康相守不变更，财运福运把你争，为你吹来吉祥风，忧愁烦恼统统扔，开心快乐你全能，春节快乐，心想事成!以下是无忧考网为您整理的《企业2019年新年贺卡祝词》，希望大家能够喜欢。</p><br><br><strong><fontstyle=\"background-color:#ffffff\"color=#ff0000>【篇一】</font></strong></p></p><p>天增岁月人增寿，春满乾坤福满门。三羊开泰送吉祥，五福临门财源茂。恭祝新春快乐，幸福安康!!</p><p>新春好，好事全来了!朋友微微笑，喜气围你绕!欢庆节日里，生活美满又如意!喜气!喜气!一生平安如意!</p><p>新春到来喜事多，合家团圆幸福多;心情愉快朋友多，身体健康快乐多;一切顺利福气多，新年吉祥生意多;祝您好事多!多!多!</p><p>谢谢您的情，谢谢你的意，谢谢你的关怀和厚爱，新年来临之际，祝您全家新年快乐，身体健康，万事如意!</p><p>向你拜大年!祝你身体健康，心情越来越好，人越来越漂亮!生活越来越浪漫!新春快乐!</p><p>相聚的日子都只为酿一杯浓酒，倒成流动的相思，在新年的鞭炮声中凝视你如此迷人的面庞，只想对你说：爱你一万年!</p><p>无惊无险，又是一年，新年来临，衷心祝愿，银行存款，只增不减，美好未来，努力今天，人生目标，一直向钱!</p><p>我跟你讲你不要跟他讲因为他叫我不要跟你讲现在我跟你讲你不要跟他讲我有讲如他问你我有没讲你跟他讲我没讲，新年快乐!</p><p>猪年到，鸿运旺，烦恼的事儿往边靠，祝君出门遇贵人，在家听喜报!年年有今日，岁岁有今朝!猪年快乐，大吉大利!</p><p>猪年到，好运到。幸福围你绕，健康对你把手招，平安对你露微笑，吉星把你照，财神牵着你跑，所有好事都来到，祝你在新的一年里幸福乐逍遥!</p><p>猪年到，福星照，送你一个大红包，不装金来不装银，字字句句表真情。好运罩着你，财运迷恋你，健康平安缠着你，幸福吉祥伴随你。祝全家春节好!</p><p>新的一年里，真诚地祝福你一切都好。狗到人心到，我的祝福早到，愿你看见短信心儿乐开花。</p><p>新的一年里，愿我的朋友：钱多钱少常有就好;人俊人丑顺眼就要;人老人少健康就好;家穷家富和气就好;一切烦恼理解就好;人的一生平安就好!</p><p>新春快乐，我的朋友!愿你~年年圆满如意，月月事事顺心，日日喜悦无忧，时时高兴欢喜，刻刻充满朝气，祝福你!</p><p>新年新事新开始新起点定有新的收获，祝朋友们事事如意，岁岁平安，精神愉快，春节好。<br><br><strong><fontstyle=\"background-color:#ffffff\"color=#ff0000>【篇二】</font></strong></p></p><p>又是一年春来到，祝你新年大旺，前程似锦，吉星高照，财运亨通，合家欢乐，飞黄腾达，福如东海，寿比南山，幸福美满，官运亨通，美梦连连!</p><p>悠悠的云里有淡淡的诗;淡淡的诗里有绵绵的喜悦;绵绵的喜悦里有我轻轻的问候。祝新年大吉!</p><p>迎春接福，玉狗吐宝庆吉日;招财进宝，金凤含珠贺新年!我的朋友，祝福你新年进步!</p><p>因考虑到过几天会有铺天盖地的祝福短信堵塞网络，有理想有远见且智慧过人的举世无双宇宙超级无敌天才提前恭祝:麦瑞克瑞斯么斯嗯得嗨皮牛野儿!</p><p>一起工作的日子是快乐，一起奋斗的日子是难忘的!感谢您一直对我的帮助，新年之际给你多多祝福。祝新年快乐!</p><p>一年一年开心过，开开心心，一生快快乐乐，一世平平安安，一家和和睦睦，愿你生命中的每一个愿望全能得到实现!新春快乐!</p><p>一轮圆月寄托不了我的哀思，一道霓虹淹没不了寂寞的孤独，一声叹息道尽漂泊的无奈，一条短信包含我无尽的祝福。朋友，祝新年快乐!美好如初!</p><p>新年新气象，新春节日到!节日快乐!愿你在年，天天开怀，时时快乐，分分精彩，秒秒幸福。</p><p>新年好运到，好事来得早!朋友微微笑，喜庆围你绕!花儿对你开，鸟儿向你叫。生活美满又如意!喜庆!喜庆!一生平安如意!</p><p>新年好!新年到，好事全到了!祝您及全家新年快乐!身体健康!工作顺利!吉祥如意!</p><p>祝你在猪年的日子里，“钱”程似锦，“富”如东海，“瘦”比南山，“性”福美满，“鑫”春快乐，兔年行大运!</p><p>祝你一帆风顺，二狗腾飞，三羊开泰，四季平安，五福临门，六六大顺，七星高照，八方来财，九九同心，十全十美。</p><p>祝你新的一年，工作忙中有闲，自己打牌赢钱，存折增加无限，口袋装满美元，美女挤满床前，精力充沛源源，情人又猛又甜。</p><p>祝你猪年喜气洋洋，满面阳光灿烂，爱情扬眉吐气，事业洋洋得意，晦气扬长而去，万事阳关大道，猪年一旺向前!</p><p>祝福是份真心意，不是千言万语的话语。一首心曲，愿你年年平安，岁岁如意!<br><br><strong><fontstyle=\"background-color:#ffffff\"color=#ff0000>【篇三】</font></strong></p></p><p>值此猪年之际，祝您：大财小财意外财，财源滚滚;亲情友情爱情，情情如意;官运财运桃花运，运运亨通;爱人亲人友人，人人平安。</p><p>祝新的一年一如既往，二人同心，三口之家，四季欢唱，五福临门，六六顺意，七喜来财，八方鸿运，九九吉祥，十分美满。</p><p>祝您春节快乐合家欢乐万事如意!新春新年新气象跃狗奔腾迎新年!</p><p>祝你在新的一年里：财源滚滚，发得像肥猪;身体棒棒，壮得像狗熊;爱情甜甜，美得像蜜蜂;财源滚滚，多得像牛毛!</p><p>郑重声名:此条短信不曾转发不曾见过包装质朴情真意浓原装正版翻版必究如有雷同实属巧合。猪年快乐!</p><p>愿您每天用大海的胸怀面对，用小猪的感觉熟睡，用南非的阳光晒背，用盖茨的美元消费，用布什的千金陪醉，用拉登的方法逃税，新年快乐!</p><p>新年好!给您拜年了!过去的一年我们合作得都很愉快，谢谢您的关照，祝您春节快乐!吉祥如意!心想事成!</p><p>新年到了，送你一篮水果，愿你：苹安福贵，橘祥如意，梨想事成，杏福快乐，柿事顺利，核家欢乐，梅有烦恼，甜如甘蔗，幸福如葡萄，请笑纳!</p><p>祝新年吉祥，前程似锦，吉星高照，财运亨通，合家欢乐，飞黄腾达，福如东海，寿比南山，幸福美满，官运亨通，美梦连连。!</p><p>祝新年行大运!仕途步步高升万事胜意!麻雀得心应手财源广进!身体棒吃饭香睡觉安，合家幸福，恭喜发财!</p><p>猪年到，福星照，送你一个大红包，不装金来不装银，字字句句表真情。好运罩着你，财运迷恋你，健康平安缠着你，幸福吉祥伴随你。祝全家春节好!</p><p>敲响的是钟声，走过的是岁月，留下的是故事，带来的是希望。盼望的是美好，送来的是祝福。愿我的朋友一生平安快乐，永远幸福!祝是年好运!</p><p>起风了，是我想你的声音;下雪了，是我思念你的表情;回家了，是我离不开你的表现，睡醒了，是我祝福你的开始!朋友，新的一年，一切都好!</p><p>狗跃神州显威风，春节瑞祥万象新;乘狗追风送祝福，点点滴滴尽是情;平安健康带给你，财富好运免费送;儿女聪明学习好，父母欢乐显童心;夫妻恩爱家和谐，样样如意都称心。</p><p>老板，新年好，感谢你的关照。祝开心如意，财源滚滚!新春到了，愿所有的幸福都陪伴着你，仰首是春，俯首是秋;愿所有的欢乐都追随着你，月圆是诗，月缺是画!</p><p>酒是温的好，菜是热的好，工作是乐的好，钱包是鼓的好，同事是铁的好，上司还是你。祝猪年愉快，兔年旺财，步步高升!</p><p>今年大家一起经历了很多，很开心在你的带领下，我们终于获得了满意的成绩。不管来年有多困难，我相信：笑容是良药，音乐是秘方，睡觉则可以让你忘掉一切。呵呵，在新年之际，给你说声祝福：祝猪年大吉万事如意!</p><p>猪年到了，愿你猪年工作业绩“狗”出，事业励精“狗”治，天天扬眉“狗”气，前程“狗”满阳光色彩。但别做好色之“狗”，不许“狗”然关机。</p><p>猪年的新年，祝愿你的好运源源不断;迎接这个新年，你的烦恼像兔子的尾巴很快完蛋;过了这个新年，你的幸福永驻在你的心间。祝你新年快乐!</p><p>大年三十福来登，合家欢乐喜庆呈，安康相守不变更，财运福运把你争，为你吹来吉祥风，忧愁烦恼统统扔，开心快乐你全能，春节快乐，心想事成!</p>";
		String[] sArr = s.split("<p>");
		List<Greeting> greetings = new ArrayList<>();
		for (int i = 0; i < sArr.length; i++) {
			if (i == 0 || i == 1 || i == 16 || i == 31) {
				continue;
			}
			Greeting greeting = new Greeting();
			String si = sArr[i].replaceAll("</p>", "");
			greeting.setMessage(si.replaceAll(" ", ""));
			greetings.add(greeting);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Contacts contacts = objectMapper.readValue(new File("src/main/resources/static/contact.json"),
					Contacts.class);
			List<Contact> contactList = contacts.getContacts();
			int index = 0;
			for (Contact contact : contactList) {
				if (index >= greetings.size()) {
					index = 0;
				}
				editImage(contact.getRemarkName(), greetings.get(index).getMessage());
				index++;
				if (index == 10) {
					break;
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	public String editImage(String name, String message) {
		try {
			BufferedImage image = ImageIO.read(new File("src/main/resources/static/hk1.png"));
			Graphics graphics = image.getGraphics();
			graphics.setColor(Color.BLACK);
			graphics.setFont(
					Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/static/1.ttf")).deriveFont(120f));
			graphics.drawString(name + "：", 150, 950);
			int length = message.length();
			int index = 0;
			for (int i = 0; i <= (length + 2) / 11; i++) {
				if (i == 0) {
					graphics.drawString(message.substring(index, 9), 360, 1100 + i * 160);
					index += 9;
				} else {
					if ((i + 1) * 11 >= length) {
						graphics.drawString(message.substring(index, length), 150, 1100 + i * 160);
					} else {
						graphics.drawString(message.substring(index, (i + 1) * 11 - 2), 150, 1100 + i * 160);
					}
					index += 11;
				}
			}
//			graphics.drawString("新的一年，新的起点，", 360, 1100);
//			graphics.drawString("新的开始；心的祝福：祝", 150, 1260);
//			graphics.drawString("你新年快乐！", 150, 1420);
			graphics.dispose();
			ImageIO.write(image, "png", new File(String.format("src/main/resources/static/hk1-%s.png", name)));
		} catch (IOException | FontFormatException e) {
			return "fail";
		}
		return "success";
	}
}
