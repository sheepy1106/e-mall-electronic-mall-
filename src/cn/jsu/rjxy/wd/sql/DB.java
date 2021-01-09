package cn.jsu.rjxy.wd.sql;

//��������û���Ϣ һ����
//��������
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
 * ���������Ϣ-�û�һ����-
 */
public class DB {
	/**
	 *  ������
	 */
	private static String firstName = "��Ǯ��������֣��������������������������ʩ�ſײ��ϻ���κ�ս���л������ˮ��������˸��ɷ�����³Τ������ﻨ������Ԭ��ۺ��ʷ�Ʒ����Ѧ�׺����������ޱϺ�����������ʱ��Ƥ���뿵����Ԫ������ƽ�ƺ�������Ҧ��տ����ë����ױ���갼Ʒ��ɴ�̸��é���ܼ�������ף������������ϯ����ǿ��·¦Σ��ͯ�չ�÷ʢ�ֵ�����������Ĳ��﷮���������֧�¾̹�¬Ī�������Ѹɽ�Ӧ�������ڵ��������������ʯ�޼�ť�������ϻ���½��������춻���κ�ӷ����ഢ���������ɾ��θ����ڽ��͹�����ɽ�ȳ������ȫۭ�����������������ﱩ�����������������ղ����Ҷ��˾��۬�輻��ӡ�ް׻���̨�Ӷ����̼���׿�����ɳ����������ܲ�˫��ݷ����̷�����̼������Ƚ��۪Ӻȴ�ɣ���ţ��ͨ�����༽ۣ����ũ�±�ׯ�̲����ֳ�Ľ����ϰ�°���������������θ����߾Ӻⲽ�����������Ŀܹ�»�ڶ�Ź�����εԽ��¡ʦ�������˹��������������Ǽ��Ŀ�����ɳؿ������ᳲ�������󽭺�����Ȩ�ָ��滸����ٹ˾���Ϲ�ŷ���ĺ�������˶��������ʸ�ξ�ٹ����̨��ұ���������������̫����������������ԯ�������������Ľ����������˾ͽ˾������˾���붽�ӳ�����ľ����������������ṫ���ذμй��׸����������ַ���۳Ϳ�նθɰ��ﶫ�����ź��ӹ麣����΢����˧�ÿ�������������������������Ĳ��٦�����Ϲ�ī�������갮��١�����Ը��ټ�����";
	/**
	 *  ������
	 */
	private static String Name = "���Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ��������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�������������Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼��ΰ�����㿡��ǿ��ƽ�����Ļ�������������־��������ɽ�ʲ���������Ԫȫ��ʤѧ��ŷ���������ɱ�˳���ӽ��β��ɿ��ǹ���ﰲ����ï�����м�ͱ벩���Ⱦ�����׳��˼Ⱥ���İ�����ܹ����ƺ���������ԣ���ܽ���������ǫ�����֮�ֺ��ʲ����������������ά�������������󳿳�ʿ�Խ��������׵���ʱ̩ʢ��衾��ڲ�����ŷ纽�� ";
	/**
	 *  ������ط���ָ����Χ�������
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getNum(int start, int end) {
		// Math.random()�������0.0��1.0֮�����
		return (int) (Math.random() * (end - start + 1) + start);
	}

	/**
	 *  �����������
	 * @return����
	 */
	public static StringBuilder getStuno() {
		StringBuilder x = new StringBuilder(String.valueOf(getNum(1, 999)));// ���ȡ3λ
		return x;
	}
	/**
	 * �������UserID
	 * @return UserID
	 */
	public static StringBuilder getID() {
		StringBuilder x1 = new StringBuilder(String.valueOf(getNum(1, 999999)));// ���ȡ6λ
		return x1;
	}	
	/**
	 * �������GsID
	 * @returnGsID
	 */
	public static StringBuilder getGsID() {
		StringBuilder gs = new StringBuilder(String.valueOf(getNum(1000, 9999)));// ���ȡ4λ
		return gs;
	}
	/**
	 * ������ض�����ShoppingNum
	 * @return
	 */
	public static StringBuilder getShoppingNum() {
		StringBuilder sN = new StringBuilder(String.valueOf(getNum(100000, 100999)));// ���ȡ6λ
		return sN;
	}
	/**
	 *  ������������û���
	 * @return�����û���
	 */
	public static String getChineseName() {
		int index = getNum(0, firstName.length() - 1);// ���ȡ�����ַ����е�����λ��
		String first = firstName.substring(index, index + 1);// ��ȡ��λ�õ�����
		int length = Name.length();
		index = getNum(0, length - 1);// �����ȡ���ֵ�λ��
		String str = Name;
		String second = str.substring(index, index + 1);// ��ȡ��λ���е�����
		int hasThird = getNum(0, 1);// �����ȡ�����Ƿ��е�������
		String third = "";// Ĭ��û�е�������
		if (hasThird == 1) {// ��������ȡΪ1�����е�������
			index = getNum(0, length - 1);
			third = str.substring(index, index + 1);
		}
		return first + second + third;// �����û���
	}
	/**
	 * ��ȡ����ʱ��
	 * @return����ʱ��
	 */
	public static String getTime() {
		Calendar c = new GregorianCalendar();
		// ��ȡ����
		String time = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + " ";
		// ��ȡʱ��2019-12-15 12:12:02
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		// ��ʱ�䲻��10��ʱ���ڸ�λ��ǰ��һ��0��ʹʱ������
		String ph = hour < 10 ? "0" : "";
		String pm = minute < 10 ? "0" : "";
		String ps = second < 10 ? "0" : "";

		time += ph + hour + ":" + pm + minute + ":" + ps + second;
		return time;
	}

	public static void main(String[] args) {
		// random1();//����һ����
		random2();//����--����-�û��ӹ�
	}
	
	/**
	 *  ��������û���Ϣ��Usertable��
	 */
	 @Test
	public  void random1() {
		DatabaseConnection dbcs = new DatabaseConnection();// ʹ��1�ж�����������ݿ����
		String sql = "insert into usertable(UserID,UserName,PassWord) values(?,?,?)";// ʹ��ռλ������������
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����PreparedStatement
			ArrayList<String> alist = new ArrayList<String>();// ���弯��
			for (int i = 1; i <= 5;) {//����
				String UserName = getChineseName();
				String UserID = getID().toString();
				if (!alist.contains(UserID)) {// �ж��û����Ƿ�Ψһ
					alist.add(UserID);// ���û������뼯��
					String password = getStuno().toString();// �����ȡ����
					pstmt.setString(1, UserID);// �����2��ռλ��������

					pstmt.setString(2, UserName);// �����2��ռλ��������
					pstmt.setString(3, password);// �����3��ռλ��������
					pstmt.addBatch();// ����������ȴ�ִ��
					i++;// �û���Ψһ��ѭ����������ִ��
				}
			}
			pstmt.executeBatch();// ����ִ�в������
			System.out.println("success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  ������ɶ���������
	 */ 
	public static void random2() {
		DatabaseConnection dbcs = new DatabaseConnection();// ʹ��1�ж�����������ݿ����
		String sql = "insert into shop(GsID,UserID,shoppingNum,shoppingTime) values(?,?,?,?)";// ʹ��ռλ������������
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����PreparedStatement
			// ArrayList<String> alist = new ArrayList<String>();// ���弯��
			for (int i = 1; i <= 50;) {
				String GsID=getGsID().toString();//
				String UserID = getID().toString();
				String shoppingNum=getShoppingNum().toString();//
				String shoppingTime = getTime();
				if(UserID != null) {//�ж�UserID�ǲ�����user
					System.out.println("success");
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
