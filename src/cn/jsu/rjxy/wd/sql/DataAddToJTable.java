package cn.jsu.rjxy.wd.sql;

//������Jtable
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
/**
 * �������ݵ�����
 */

public class DataAddToJTable {
	/**
	 * GsID  UserID  shoppingNum  shoppingTime
	 * @author a'su's
	 *
	 */
	public static Vector<Vector> getSelectAll2(String sql) {// �鿴����
		Vector<Vector> rows = new Vector<Vector>();// ����Ҫ���ص����м�¼����
		DatabaseConnection dbcs = new DatabaseConnection();// ʹ��1�ж�����������ݿ����
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();// ִ�в�ѯ���
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
	 * @return��������������  
	 */
	public static Vector<Vector> getSelectAll(String sql) {// �鿴��Ʒʱ
		Vector<Vector> rows = new Vector<Vector>();// ����Ҫ���ص����м�¼����
		DatabaseConnection dbcs = new DatabaseConnection();// ʹ��1�ж�����������ݿ����
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ��
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����PreparedStatement
			// PreparedStatement������Ч��ֹsqlע��
			ResultSet rs = pstmt.executeQuery();// ִ�в�ѯ��䣬����ŵ����ݼ���//
			while (rs.next()) {// �������ݼ�
				Vector row = new Vector();// ����������
				row.add(rs.getString(1));// ��ȡ��һ���ֶ�UserID
				row.add(rs.getString(2));// ��ȡ�ڶ����ֶ�UserName����
				row.add(rs.getFloat(3));// ��ȡ3�ֶ�GsPrice
				row.add(rs.getString(4)); // ��ȡ��4���ֶ�
				// row.add(rs.getString(5)); //��ȡ��5���ֶ�
				// ����4�������ɹ�
				rows.add(row);// ����������ӵ���¼������
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;// ��������������
	}
}
	

