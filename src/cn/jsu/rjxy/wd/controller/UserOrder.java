package cn.jsu.rjxy.wd.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import java.awt.Toolkit;
/**
 * 用户订单窗口
 */
public class UserOrder extends JFrame {
	
	private JPanel contentPane;
	private Vector<String> titles;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtKeyA;
	private JTextField textB;
	private static JFrame frameAAA;
	
	public static void main(String[] args) {
		UserOrder frameAAA = new UserOrder();
		frameAAA.setVisible(true);// 窗体可见
		frameAAA.setLocationRelativeTo(null);// 窗体居中
	}
	public static  JFrame getIns() {
		if(frameAAA==null) {
			frameAAA=new UserOrder();
		}
		return frameAAA;
	}
		public UserOrder(){
			setTitle("订单查询");
			//图标
			setIconImage(Toolkit.getDefaultToolkit().getImage(UserOrder.class.getResource("/\u56FE\u6807/h11.png")));
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 557, 382);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
			setContentPane(contentPane);
			contentPane.setLayout(null);// 设置面板布局为绝对布局
			// 设置滚动面板
			JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
			scrollPane.setBounds(10, 87, 508,229);// 设置大小与位置
			contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
			// 使用动态数组数据（列标题与行数据）
			titles = new Vector<String>();// 定义动态数组表示表格标题
			//订单表
			Collections.addAll(titles, "GsID", "UserID", "shoppingNum","shoppingTime");
	
			String sql="select * from shop";//定义查询语句
			Vector<Vector> stuInfo = DataOperate.getSelectAll2(sql);// 查看订单-数据库
			model = new DefaultTableModel(stuInfo, titles);
			table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
			table = new JTable(stuInfo,titles);// 使用静态数据实例化表格
	
			scrollPane.setViewportView(table);//设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
			JLabel lblKeyA = new JLabel("请输入您的账号:");
			lblKeyA.setBounds(10, 10, 105, 15);
			contentPane.add(lblKeyA);
			txtKeyA = new JTextField();
			txtKeyA.setBounds(125, 7, 166, 21);
			contentPane.add(txtKeyA);
		    txtKeyA.setColumns(10);
		    
		    JButton btnFH = new JButton("\u8FD4\u56DE\u8D2D\u7269\u5546\u57CE");//返回上一步
			btnFH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnFH.setBounds(362, 51, 119, 23);
			contentPane.add(btnFH);			
			/**
			 * 查找按钮(用户）-按UserID查找-弹窗提示
			 */
			JButton btnFindA = new JButton("\u786E\u5B9A\u67E5\u8BE2\u8BA2\u5355");
			btnFindA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String a1 = String.valueOf(txtKeyA.getText());//用户ID
					String b1=String.valueOf(textB.getText());//GsID
					String[] str = new String[] {a1,b1};
					//String [] str2=new String [] {};
					String sql = "select * from shop where UserID=? and GsID=?";
					ResultSet rst=new DatabaseConnection().search(sql,str);
					try {
						if(rst.next()) {
							JOptionPane.showMessageDialog(null,"查找成功！"+"商品ID:"+rst.getString(1)+"用户ID："+rst.getString(2)+"订单号:"+rst.getString(3)+"订单时间："+rst.getString(4));
						}
						else
							JOptionPane.showMessageDialog(null,"查找失败！请重新输入");
					} catch (SQLException e1) {	
						e1.printStackTrace();
					}
				}
			});
			btnFindA.setBounds(362, 6, 119, 23);
			contentPane.add(btnFindA);
			
			JLabel lblB = new JLabel("请输入商品ID：");
			lblB.setBounds(10, 55, 105, 15);
			contentPane.add(lblB);
			
			textB = new JTextField();
			textB.setBounds(125, 52, 166, 21);
			contentPane.add(textB);
			textB.setColumns(10);
		}
}