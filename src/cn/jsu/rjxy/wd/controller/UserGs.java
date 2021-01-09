package cn.jsu.rjxy.wd.controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.jsu.rjxy.wd.sql.DB;
import cn.jsu.rjxy.wd.sql.DataOperate;
import cn.jsu.rjxy.wd.sql.DatabaseConnection;

import java.awt.Toolkit;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 用户登录成功的界面--查看商品--订单
 */
public class UserGs extends JFrame{
	private static JFrame frame;
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private JTextField txtKeyF;//定义查找关键字文本框
	private DefaultTableModel model;// 定义表格数据模型
	private Vector<String> titles;
	public static void main(String[] args) {
		frame = new UserGs();// 实例化窗体
		frame.setLocationRelativeTo(null);// 窗体居中
		frame.setVisible(true);// 窗体可见
	}
	/**
	 * 定义构造方法创建窗体及组件.
	 */
	public UserGs() {
		setTitle("走过路过不要错过");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserGs.class.getResource("/\u56FE\u6807/h11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭按钮
		setBounds(100, 100, 450, 403);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板

		// 设置滚动面板
		JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		scrollPane.setBounds(10, 85, 416, 271);// 设置大小与位置
		contentPane.add(scrollPane);// 将滚动面板加入到内容面板中

		// 使用动态数组数据（列标题与行数据）
		titles = new Vector<String>();// 定义动态数组表示表格标题
		//商品表,"GsNum"
		Collections.addAll(titles, "GsID", "GsName", "GsPrice");
		String sql="select * from goodstable order by GsPrice asc";//定义查询语句---order by排序！
		Vector<Vector> stuInfo = DataOperate.getSelectAll(sql);// 从数据库中读取所有行数据

		model = new DefaultTableModel(stuInfo, titles) ;
		table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
		scrollPane.setViewportView(table);//设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
		
		JLabel lblKey = new JLabel("请输入商品名称：");
		lblKey.setBounds(29, 26, 102, 15);
		contentPane.add(lblKey);
	
		txtKeyF = new JTextField();
		txtKeyF.setBounds(158, 23, 232, 21);
		contentPane.add(txtKeyF);
		txtKeyF.setColumns(10);	
		/**
		 * 定义查找按钮-按名称-弹窗提示
		 */
		JButton btnFindF = new JButton("查找商品");//查询商品
		btnFindF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = String.valueOf(txtKeyF.getText());
				String[] str = new String[] {a};
				String sql = "select * from goodstable where GsName=?";//按名称
				ResultSet rst=new DatabaseConnection().search(sql,str);
				try {
					if(rst.next()) {
						JOptionPane.showMessageDialog(null,"查找成功！"+"商品ID:"+rst.getString(1)+"商品名称"+rst.getString(2)+"商品价格:"+rst.getString(3)+"商品数量："+rst.getString(4)+"商品种类："+rst.getNString(5));
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});
		btnFindF.setBounds(29, 50, 95, 25);
		contentPane.add(btnFindF);
//用户--查看商品
		/**
		 * 查看订单按钮-跳转到用户订单窗口
		 */
		JButton btnCX = new JButton("查看订单");
		btnCX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ctaa=UserOrder.getIns();//用户订单界面！
				ctaa.setVisible(true);// 窗体可见
				ctaa.setLocationRelativeTo(null);// 窗体居中
			}
		});
		btnCX.setBounds(300, 50, 95, 25);
		contentPane.add(btnCX);
		/**
		 * 购买按钮--点击商品-点击加购-订单增加
		 */
		JButton btnBuy = new JButton("加购");//点击选中
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count=table.getSelectedRow();////获取你选中的行号（记录）
				String GsID= table.getValueAt(count, 0).toString();//读取获取行号的某一列的值（也就是字段）
				String shopNum=DB.getShoppingNum().toString();//获得订单编号
				String date=DB.getTime();//获得当前时间
				
				String sql="insert into shop(GsID,UserID,shoppingNum,shoppingTime) values(?,?,?,?)";
				PreparedStatement pst;
				try {
				pst=new DatabaseConnection().getConnection().prepareStatement(sql);
					pst.setString(1, GsID);
				    pst.setString(2, Login.UserID);
				    pst.setString(3, shopNum);
				   pst.setString(4, date);
				    pst.executeUpdate();//executeUpdate
				    JOptionPane.showMessageDialog(null,"加购成功！");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});	
		btnBuy.setBounds(160, 50, 95, 25);
		contentPane.add(btnBuy);
}
	/**
	 * 单例
	 * @return UserGs
	 */
	public static  JFrame getIns() {
		if(frame==null) {
			frame=new UserGs();
		}
		return frame;
	}
}