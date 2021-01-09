package cn.jsu.rjxy.wd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.jsu.rjxy.wd.sql.DataOperate;
import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import cn.jsu.rjxy.wd.writeFileDao.ToExcel;

import java.awt.Toolkit;
/**
 * 管理员查看订单
 */
public class ManagerOrder extends JFrame {
/**
 * 管理员查看订单窗口
 */
	private static JFrame frame101;
	private JPanel contentPane;
	/**
	 * 订单表名
	 */
	private Vector<String> titles;
	
	private DefaultTableModel model;
	/**
	 * 订单表
	 */
	
	private JTable table;
	/**
	 * 订单号输入框
	 */
	private JTextField txtKey;
	/**
	 * 修改值-GsID输入框
	 */
	private JTextField textXG;

	public static void main(String[] args) {
		ManagerOrder frame101 = new ManagerOrder();
		frame101.setVisible(true);// 窗体可见
		frame101.setLocationRelativeTo(null);// 窗体居中
	}
	public static  JFrame getIns() {//单例
		if(frame101==null) {
			frame101=new ManagerOrder();
		}
		return frame101;
	}
	public ManagerOrder() {
		setTitle("订单信息管理");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerOrder.class.getResource("/\u56FE\u6807/h3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		setContentPane(contentPane);
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(0, 84, 537, 161);// 设置大小与位置
		
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
		// 使用动态数组数据（列标题与行数据）
		titles = new Vector<String>();// 定义动态数组表示表格标题
		// 订单表
		Collections.addAll(titles, "GsID", "UserID", "shoppingNum", "shoppingTime");
		String sql = "select * from shop";// 定义查询语句
		Vector<Vector> stuInfo = DataOperate.getSelectAll2(sql);// 从数据库中读取所有行数据

		model = new DefaultTableModel(stuInfo, titles) ;
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		
		table = new JTable(stuInfo, titles);// 使用静态数据实例化表格
		/* 管理员查看商品窗口
		 */
		scrollPane.setViewportView(table);// 设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
		JLabel lblKey = new JLabel("请输入(订单号):");
		lblKey.setBounds(26, 26, 111, 15);
		contentPane.add(lblKey);
		// 订单号输入框
		txtKey = new JTextField();
		txtKey.setBounds(141, 23, 262, 21);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		/**
		 *  定义查找按钮-按订单编号查-显示于弹窗
		 */
		JButton btnFind101 = new JButton("查找");
		btnFind101.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 查询订单 4 shoppingNum
				String key = String.valueOf(txtKey.getText());// 获取输入关键字文本框的值--String
				String sql = "select * from shop where shoppingNum=? ";// 查询条件--订单号
				String[] str = new String[] { key };
				ResultSet rst = new DatabaseConnection().search(sql, str);//
				// 查询后显示
				try {
					if (rst.next()) {
						JOptionPane.showMessageDialog(null, "查找成功！" + "商品ID:" + rst.getString(1) + " 用户ID："
								+ rst.getString(2) + " 订单号:" + rst.getString(3) + " 订单时间：" + rst.getString(4));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});// 管理员查看订单窗口
		btnFind101.setBounds(80, 51, 68, 23);
		contentPane.add(btnFind101);
/**
 * 删除按钮-按订单号删除订单-弹窗提示成功
 */
		JButton btnSC101 = new JButton("删除");
		btnSC101.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = String.valueOf(txtKey.getText());// 获取输入关键字文本框的值--String
				String sql1 = "select * from shop where shoppingNum=?";// 订单号
				String[] str1 = new String[] { key };
				ResultSet rst = new DatabaseConnection().search(sql1, str1);
				try {
					if (rst.next()) {
						String sql = "delete from shop where shoppingNum=?";
						String[] str = new String[] { key };// ?
						new DatabaseConnection().add(sql, str);// 删除表中数据
						JOptionPane.showMessageDialog(null, "已从数据库中删除");
					} else
						JOptionPane.showMessageDialog(null, "未删除成功！请重新输入");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSC101.setBounds(225, 51, 60, 23);
		contentPane.add(btnSC101);
/**
 *  修改订单（管理员）-按订单号修改GsID		
 */
		JButton btnXG = new JButton("确定修改");
		btnXG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key1 = String.valueOf(txtKey.getText());// 获取文本框的值--shoppingNum
				String key2 = String.valueOf(textXG.getText());// 获取文本框的值--修改正确的shoppingNum
				String[] str = new String[] { key2, key1 };
				String sql1 = "update shop set GsID=? where shoppingNum=?";
				new DatabaseConnection().add(sql1, str);// 修改表中数据
				JOptionPane.showMessageDialog(null, "数据库中信息已成功修改！");
			}
		});

		btnXG.setBounds(354, 269, 86, 19);
		contentPane.add(btnXG);
		JButton btnFH = new JButton("返回");
		// 返回上一窗口
		btnFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFH.setBounds(449, 22, 60, 23);
		contentPane.add(btnFH);

		JLabel lblXG = new JLabel("请输入正确GsID：");
		lblXG.setBounds(26, 271, 117, 15);
		contentPane.add(lblXG);

		textXG = new JTextField();
		textXG.setBounds(142, 268, 116, 21);
		contentPane.add(textXG);
		textXG.setColumns(10);
		
		JButton btnDC = new JButton("\u5BFC\u51FA\u8BA2\u5355\u6570\u636E");//导出订单表
		btnDC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToExcel.main(null);
				JOptionPane.showMessageDialog(null, "导出电子商城系统-UserOrderTable成功！退出即可在D盘查看");
			}
		});
		btnDC.setBounds(354, 51, 111, 23);
		contentPane.add(btnDC);
	}
}
