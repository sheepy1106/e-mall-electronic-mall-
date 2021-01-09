package cn.jsu.rjxy.wd.sql;

//到表里Jtable
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
/**
 * 插入数据到表里
 */

public class DataAddToJTable {
	/**
	 * GsID  UserID  shoppingNum  shoppingTime
	 * @author a'su's
	 *
	 */
	public static Vector<Vector> getSelectAll2(String sql) {// 查看订单
		Vector<Vector> rows = new Vector<Vector>();// 定义要返回的所有记录集合
		DatabaseConnection dbcs = new DatabaseConnection();// 使用1中定义的连接数据库的类
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();// 执行查询语句
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString(1));// GsID
				row.add(rs.getString(2));// UserID
				row.add(rs.getInt(3));// shoppingNum
				row.add(rs.getString(4));// shoppingTime
				rows.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	/**
	 * GsID  GsName  GsPrice  GsNum
	 * @param sql
	 * @return返回所有行数据  
	 */
	public static Vector<Vector> getSelectAll(String sql) {// 查看商品时
		Vector<Vector> rows = new Vector<Vector>();// 定义要返回的所有记录集合
		DatabaseConnection dbcs = new DatabaseConnection();// 使用1中定义的连接数据库的类
		try (Connection conn = dbcs.getConnection(); // 获取数据库接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化PreparedStatement
			// PreparedStatement可以有效防止sql注入
			ResultSet rs = pstmt.executeQuery();// 执行查询语句，结果放到数据集中//
			while (rs.next()) {// 遍历数据集
				Vector row = new Vector();// 定义行数据
				row.add(rs.getString(1));// 获取第一个字段UserID
				row.add(rs.getString(2));// 获取第二个字段UserName姓名
				row.add(rs.getFloat(3));// 获取3字段GsPrice
				row.add(rs.getString(4)); // 获取第4个字段
				// row.add(rs.getString(5)); //获取第5个字段
				// 多于4个传不成功
				rows.add(row);// 将行数据添加到记录集合中
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// 返回所有行数据
	}
}
	

