package cn.jsu.rjxy.wd.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.jsu.rjxy.wd.writeFileDao.Shop;
/**
 * ���ӡ��ر����ݿ⡢ִ��SQL���
 */
public class DatabaseConnection {
	//����MySQL���ݿ���������
			private static final String DBRIVER="com.mysql.cj.jdbc.Driver";
			//����MySQL���ݿ����ӵ�ַ��db_library�ɸĳ��Լ������ݿ�����
			private static final String DBURL="jdbc:mysql://localhost:3306/�����̳�?serverTimezone=GMT%2B8";
			private static final String DBUSER="root"; //MySQL���ݿ������û���
			private static final String PASSWORD="1234"; //MySQL���ݿ���������

			private static Connection conn = null; //�������Ӷ���
			private static   ResultSet res;
			/**
			 * ���췽���������ݿ�
			 */
			public DatabaseConnection(){//���췽���������ݿ�
				try {
					Class.forName(DBRIVER);
					this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
					System.out.println("�����ɹ�");
				} catch (Exception e) {
					System.out.println("ʧ��");
					e.printStackTrace();
					}
			}
			/**
			 * �������ݿ����Ӷ���
			 * @return ���ݿ����Ӷ���
			 */
			public static  Connection getConnection() {
				return conn;
			}
			/**
			 * ���/ɾ��/�޸�sql
			 * @param sql
			 * @param str
			 * @return
			 */
			public  static int add(String sql, String str[]) {
				new DatabaseConnection();
				int a = 0;
				try {
		            PreparedStatement pst = conn.prepareStatement(sql);
		            if (str != null) {
		                for (int i = 0; i < str.length; i++) {
		                    pst.setString(i + 1, str[i]);//�����Ӧ��
		                }
		            }
		            a = pst.executeUpdate();//
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return a;//ִ������
			}
		/**
		 * ��ѯsjk�������
		 * @param sql
		 * @param str
		 * @return
		 */
			public static   ResultSet search(String sql, String str[]) {
				new DatabaseConnection();
		    	try {
		    		PreparedStatement pst = conn.prepareStatement(sql);//���ݿ�Ĳ�������
		            if (str != null) {
		                for (int i = 0; i < str.length; i++) {
		                	//��str[i]��Ϊ��i+1������
		                	//sql���û�е�0�����ݣ�1��ʼ
		                    pst.setString(i + 1, str[i]);//���룿
		                }
		            }
		            res = pst.executeQuery();//���ز�ѯ�Ľ����
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return res;
		    }
	/**
	 * ����Excelʱ�ĵõ�shop���ݷ���
	 * @param sql
	 * @param str
	 * @return List<Shop>
	 */
	
			public static List<Shop> getAllOrder(String sql,String[] str){
				List<Shop> list = new ArrayList<>();
				try {
					ResultSet res = DatabaseConnection.search(sql, str);

					while (res.next()) {
						String GsID = res.getString("GsID");
						String UserID= res.getString("UserID");
						String shoppingNum = res.getString("shoppingNum");
						String shoppingTime = res.getString("shoppingTime");
						
						list.add(new Shop(GsID, UserID, shoppingNum,shoppingTime));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
/**
 * �ر���������
 */
			public void close() {
				if(this.conn!=null) {
					try {
						this.conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		public static void main(String[] args) {
			DatabaseConnection s = new DatabaseConnection();
		}
}
