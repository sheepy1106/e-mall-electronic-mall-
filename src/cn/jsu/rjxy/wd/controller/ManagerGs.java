package cn.jsu.rjxy.wd.controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import cn.jsu.rjxy.wd.sql.DataOperate;
import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Toolkit;
/**
 * 管理员查看商品界面--
 */
public class ManagerGs extends JFrame {
	private static JFrame frame;
	//private JPanel contentPane;
	private JPanel contentPane;// 定义窗体内容面板，放置各组件
	private JTable table;// 定义表格
	private JTextField txtKey;//定义查找关键字文本框
	private DefaultTableModel model;// 定义表格数据模型
	private TableRowSorter sorter;//定义排序器
	private ArrayList<RowSorter.SortKey> sortKeys;//设置排序规则
	
	private Vector<String> titles;
	private JTextField textID;
	private JTextField textName;
	private JTextField textPrice;
	private JTextField textNum;
	private JTextField textKind;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGs frame = new ManagerGs();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * 单例
	 * @return ManagerGs
	 */
	public static  JFrame getIns() {//单例
		if(frame==null) {
			frame=new ManagerGs();
		}
		return frame;
	}
	public ManagerGs() {
		setTitle("商品信息管理");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerGs.class.getResource("/\u56FE\u6807/h3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置窗体关闭按钮
		setBounds(100, 100, 450, 403);// 设置窗体位置与大小
		contentPane = new JPanel();// 实例化内容面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// 设置面板边框
		contentPane.setLayout(null);// 设置面板布局为绝对布局
		setContentPane(contentPane);// 将窗体默认面板		
		// 设置滚动面板
				JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
				scrollPane.setBounds(31, 82, 367, 161);// 设置大小与位置
				contentPane.add(scrollPane);// 将滚动面板加入到内容面板中
			// 使用动态数组数据（列标题与行数据）
				titles = new Vector<String>();// 定义动态数组表示表格标题
				Collections.addAll(titles, "GsID", "GsName", "GsPrice","GsNum");
				String sql="select * from goodstable order by GsPrice asc ";//定义查询语句
				Vector<Vector> stuInfo = DataOperate.getSelectAll(sql);// 从数据库中读取所有行数据
				//使用静态数据创建DefaultTableModel数据模型
				model = new DefaultTableModel(stuInfo, titles);				
				table = new JTable(model);// 使用DefaultTableModel数据模型实例化表格
				scrollPane.setViewportView(table);//设置使用滚动面板显示表格，如果不使用滚动面板显示，则表格的列标题无法显示
			
				txtKey = new JTextField();
				txtKey.setBounds(115, 23, 119, 21);
				contentPane.add(txtKey);
				txtKey.setColumns(10);
	
				JButton btnFind = new JButton("查找");
				btnFind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
			//商品ID查找
						String a = String.valueOf(txtKey.getText());
						String[] str = new String[] {a};
						String sql = "select * from goodstable where GsID=?";//按名称
						ResultSet rst=new DatabaseConnection().search(sql,str);
						try {
							if(rst.next()) {
								JOptionPane.showMessageDialog(null,"查找成功！"+"商品ID: "+rst.getString(1)+" 商品名称："+rst.getString(2)+"商品价格:"+rst.getString(3)+" 商品数量："+rst.getString(4)+" 商品种类："+rst.getNString(5));
							}
							else
								JOptionPane.showMessageDialog(null,"该商品不存在！");
						} catch (SQLException e1) {	
							e1.printStackTrace();
						}
					}
				});
				btnFind.setBounds(272, 5, 58, 25);
				contentPane.add(btnFind);
				JLabel lblKey = new JLabel("请输入商品ID:");
				lblKey.setBounds(22, 26, 86, 15);
				contentPane.add(lblKey);
	
				JButton btnAdd = new JButton("增加");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String GsID=textID.getText();
						String GsName=textName.getText();
						String GsPrice=textPrice.getText();
						String GsNum=textNum.getText();
						String GsKind=textKind.getText();
						String sql = "insert into goodstable (GsID,GsName,GsPrice,GsNum,GsKind) values(?,?,?,?,?)";
						String[] str = new String[] {GsID,GsName,GsPrice,GsNum,GsKind};
						new DatabaseConnection().add(sql, str);//加入数据
						JOptionPane.showMessageDialog(null, "信息已加入数据库");
					}
				});
				btnAdd.setBounds(340, 7, 58, 21);
				contentPane.add(btnAdd);
				
				JLabel lblAdd = new JLabel("输入增加商品信息：");//标签
				lblAdd.setBounds(32, 269, 144, 15);
				contentPane.add(lblAdd);
/**
 * 管理员查看商品界面--删除按钮-按GsID删除商品信息
 */
				
				JButton btnDelete = new JButton("删除");//商品信息
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String GsID = String.valueOf(txtKey.getText());
						String sql1 = "select * from goodstable where GsID=? ";
						String[] str1 = new String[] {GsID };
						ResultSet rs=new DatabaseConnection().search(sql1, str1);
						try {
							if(rs.next()) {
								String sql="delete from goodstable where GsID=?";
							String[] str = new String[] {GsID };
							 new DatabaseConnection().add(sql, str);//删除表中数据
								JOptionPane.showMessageDialog(null, "已从数据库中删除");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}						
					}
				});
				btnDelete.setBounds(340, 49, 58, 23);
				contentPane.add(btnDelete);
	/**
	 * 订单按钮-跳转订单窗口	
	 */
				JButton btnNewButton = new JButton("订单");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//订单窗口！
						JFrame frame03= ManagerOrder.getIns();//跳转到管理员订单窗口
						frame03.setVisible(true);
					}
				});
				btnNewButton.setBounds(272, 49, 58, 23);
				contentPane.add(btnNewButton);
				//增加信息数入框
				JLabel lblID = new JLabel("GsID:");
				lblID.setBounds(31, 301, 58, 15);
				contentPane.add(lblID);
				
				textID = new JTextField();
				textID.setBounds(82, 298, 66, 21);
				contentPane.add(textID);
				textID.setColumns(10);
				
				JLabel lblNa = new JLabel("GsName:");
				lblNa.setBounds(31, 335, 58, 21);
				contentPane.add(lblNa);
				
				textName = new JTextField();
				textName.setBounds(82, 335, 66, 21);
				contentPane.add(textName);
				textName.setColumns(10);
				
				JLabel lblPrice = new JLabel("GsPrice:");
				lblPrice.setBounds(186, 269, 58, 15);
				contentPane.add(lblPrice);
				
				textPrice = new JTextField();
				textPrice.setBounds(254, 266, 66, 21);
				contentPane.add(textPrice);
				textPrice.setColumns(10);
				
				JLabel lblNum = new JLabel("GsNum:");
				lblNum.setBounds(186, 301, 58, 15);
				contentPane.add(lblNum);
				
				textNum = new JTextField();
				textNum.setBounds(254, 298, 66, 21);
				contentPane.add(textNum);
				textNum.setColumns(10);
				
				JLabel lblKind = new JLabel("GsKind:");
				lblKind.setBounds(184, 332, 50, 27);
				contentPane.add(lblKind);
				
				textKind = new JTextField();
				textKind.setBounds(254, 335, 66, 21);
				contentPane.add(textKind);
				textKind.setColumns(10);				
	}
}
