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
 * 连接、关闭数据库、执行SQL语句
 */
public class DatabaseConnection {
	//定义MySQL数据库驱动程序
			private static final String DBRIVER="com.mysql.cj.jdbc.Driver";
			//定义MySQL数据库连接地址，db_library可改成自己的数据库名称
			private static final String DBURL="jdbc:mysql://localhost:3306/电子商城?serverTimezone=GMT%2B8";
			private static final String DBUSER="root"; //MySQL数据库连接用户名
			private static final String PASSWORD="1234"; //MySQL数据库连接密码

			private static Connection conn = null; //保存连接对象
			private static   ResultSet res;
			/**
			 * 构造方法连接数据库
			 */
			public DatabaseConnection(){//构造方法连接数据库
				try {
					Class.forName(DBRIVER);
					this.conn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
					System.out.println("启动成功");
				} catch (Exception e) {
					System.out.println("失败");
					e.printStackTrace();
					}
			}
			/**
			 * 返回数据库连接对象
			 * @return 数据库连接对象
			 */
			public static  Connection getConnection() {
				return conn;
			}
			/**
			 * 添加/删除/修改sql
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
		                    pst.setString(i + 1, str[i]);//放入对应行
		                }
		            }
		            a = pst.executeUpdate();//
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
				return a;//执行条数
			}
		/**
		 * 查询sjk里的数据
		 * @param sql
		 * @param str
		 * @return
		 */
			public static   ResultSet search(String sql, String str[]) {
				new DatabaseConnection();
		    	try {
		    		PreparedStatement pst = conn.prepareStatement(sql);//数据库的操作对象
		            if (str != null) {
		                for (int i = 0; i < str.length; i++) {
		                	//把str[i]设为第i+1个数据
		                	//sql语句没有第0个数据，1开始
		                    pst.setString(i + 1, str[i]);//插入？
		                }
		            }
		            res = pst.executeQuery();//返回查询的结果集
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return res;
		    }
	/**
	 * 导出Excel时的得到shop数据方法
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
 * 关闭数据连接
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
