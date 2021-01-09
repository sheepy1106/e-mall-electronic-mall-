package cn.jsu.rjxy.wd.sql;

//随机生成用户信息 一万条
//导入数据
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.mysql.cj.xdevapi.Statement;
/**
 * 随机生成信息-用户一万条-
 */
public class DB {
	/**
	 *  定义姓
	 */
	private static String firstName = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚阎法汝鄢涂钦段干百里东郭南门呼延归海羊舌微生岳帅缑亢况后有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福百家姓续";
	/**
	 *  定义名
	 */
	private static String Name = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘 ";
	/**
	 *  随机返回返回指定范围间的整数
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getNum(int start, int end) {
		// Math.random()随机返回0.0至1.0之间的数
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 *  随机返回密码
	 * @return密码
	 */
	public static StringBuilder getStuno() {
		StringBuilder x = new StringBuilder(String.valueOf(getNum(1, 999)));// 随机取3位
		return x;
	}
	/**
	 * 随机返回UserID
	 * @return UserID
	 */
	public static StringBuilder getID() {
		StringBuilder x1 = new StringBuilder(String.valueOf(getNum(1, 999999)));// 随机取6位
		return x1;
	}	
	/**
	 * 随机返回GsID
	 * @returnGsID
	 */
	public static StringBuilder getGsID() {
		StringBuilder gs = new StringBuilder(String.valueOf(getNum(1000, 9999)));// 随机取4位
		return gs;
	}
	/**
	 * 随机返回订单号ShoppingNum
	 * @return
	 */
	public static StringBuilder getShoppingNum() {
		StringBuilder sN = new StringBuilder(String.valueOf(getNum(100000, 100999)));// 随机取6位
		return sN;
	}
	/**
	 *  随机返回中文用户名
	 * @return中文用户名
	 */
	public static String getChineseName() {
		int index = getNum(0, firstName.length() - 1);// 随机取姓氏字符串中的任意位置
		String first = firstName.substring(index, index + 1);// 获取该位置的姓氏
		int length = Name.length();
		index = getNum(0, length - 1);// 随机获取名字的位置
		String str = Name;
		String second = str.substring(index, index + 1);// 获取该位置中的名字
		int hasThird = getNum(0, 1);// 随机获取名字是否有第三个字
		String third = "";// 默认没有第三个字
		if (hasThird == 1) {// 如果随机获取为1，则有第三个字
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;// 返回用户名
	}
	/**
	 * 获取本地时间
	 * @return本地时间
	 */
	public static String getTime() {
		Calendar c = new GregorianCalendar();
		// 获取日期
		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " ";
		// 获取时刻2019-12-15 12:12:02
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		// 当时间不足10的时候，在个位数前加一个0，使时间整齐
		String ph = hour < 10 ? "0" : "";
		String pm = minute < 10 ? "0" : "";
		String ps = second < 10 ? "0" : "";

		time += ph + hour + ":" + pm + minute + ":" + ps + second;
		return time;
	}

	public static void main(String[] args) {
		// random1();//加入一万条
		random2();//测试--订单-用户加购
	}
	
	/**
	 *  随机生成用户信息到Usertable里
	 */
	 @Test
	public  void random1() {
		DatabaseConnection dbcs = new DatabaseConnection();// 使用1中定义的连接数据库的类
		String sql = "insert into usertable(UserID,UserName,PassWord) values(?,?,?)";// 使用占位符定义插入语句
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化PreparedStatement
			ArrayList<String> alist = new ArrayList<String>();// 定义集合
			for (int i = 1; i <= 5;) {//条数
				String UserName = getChineseName();
				String UserID = getID().toString();
				if (!alist.contains(UserID)) {// 判断用户名是否唯一
					alist.add(UserID);// 将用户名加入集合
					String password = getStuno().toString();// 随机获取密码
					pstmt.setString(1, UserID);// 定义第2个占位符的内容

					pstmt.setString(2, UserName);// 定义第2个占位符的内容
					pstmt.setString(3, password);// 定义第3个占位符的内容
					pstmt.addBatch();// 加入批处理等待执行
					i++;// 用户名唯一，循环继续往下执行
				}
			}
			pstmt.executeBatch();// 批量执行插入操作
			System.out.println("success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  随机生成订单（测试
	 */ 
	public static void random2() {
		DatabaseConnection dbcs = new DatabaseConnection();// 使用1中定义的连接数据库的类
		String sql = "insert into shop(GsID,UserID,shoppingNum,shoppingTime) values(?,?,?,?)";// 使用占位符定义插入语句
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化PreparedStatement
			// ArrayList<String> alist = new ArrayList<String>();// 定义集合
			for (int i = 1; i <= 50;) {
				String GsID=getGsID().toString();//
				String UserID = getID().toString();
				String shoppingNum=getShoppingNum().toString();//
				String shoppingTime = getTime();
				if(UserID != null) {//判断UserID是不是在user
					System.out.println("success");
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
