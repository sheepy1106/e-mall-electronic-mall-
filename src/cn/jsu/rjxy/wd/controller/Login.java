package cn.jsu.rjxy.wd.controller;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import cn.jsu.rjxy.wd.sql.DatabaseConnection;

import java.awt.Color;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.io.File;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

/**
 * 登录背景
 * @author a'su's
 *
 */
class NewPanel extends JPanel {
	  public NewPanel() {
		  
	  }
	  public void paintComponent(Graphics g) {//重绘面板背景
			//创建一个未初始化的图像图标
			ImageIcon icon=new ImageIcon("source"+File.separator+"timg.jpg");
			//绘制指定图像中已缩放到适合指定矩形内部的图像
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
		}
}
/**
 * 登录界面--选择-注册
 * @author a'su's
 *
 */
public class Login extends JFrame {
	/**
	 * 登录用户ID输入值
	 */
	public static String UserID;
	//private JTextField textF01PsW;
	private JPanel contentPane;
	/**
	 * 输入错误提示框
	 */
	private JLabel lbl01;
	private JLabel lbl02;
	/**
	 * 账号输入框
	 */
	private JTextField textF01ZH;
	private static UserGs frame=null;
	/**
	 * 密码输入框
	 */
	
	private JPasswordField textF01PsW;

	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);//窗体居中
				//frame.setBounds(600, 200, 750, 550);
				//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//窗口最大化
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}	
	/**
	 * 登录界面-确定按钮  管理员登录->管理员窗口  用户登录->用户窗口	
	 */
	public  Login() {
		setForeground(Color.WHITE);
	
		setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/\u56FE\u6807/h1.png")));
		setResizable(true);
		setRootPaneCheckingEnabled(false);
		getContentPane().setForeground(Color.LIGHT_GRAY);
		setTitle("欢迎光临宇宙商城");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPane = new NewPanel(); //放背景
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
	
		JLabel lbl01ID = new JLabel("请选择登录身份：");
		lbl01ID.setForeground(Color.WHITE);
		lbl01ID.setBounds(34, 51, 101, 15);
		getContentPane().add(lbl01ID);
		
		JLabel lblPsWord = new JLabel("请输入密码：");
		lblPsWord.setForeground(Color.WHITE);
		lblPsWord.setBounds(34, 172, 137, 15);
		getContentPane().add(lblPsWord);
		
		JRadioButton rdbtnGLY = new JRadioButton("管理员");
		rdbtnGLY.setBounds(145, 47, 127, 23);
		getContentPane().add(rdbtnGLY);
		
		JRadioButton rdbtnYH = new JRadioButton("用户");
		rdbtnYH.setBounds(145, 79, 127, 23);
		getContentPane().add(rdbtnYH);
		//实现单选
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnGLY);
		group.add(rdbtnYH);
		
		JLabel lbl01ZH = new JLabel("请输入账号：");
		lbl01ZH.setForeground(Color.WHITE);
		lbl01ZH.setBounds(34, 124, 81, 15);
		getContentPane().add(lbl01ZH);
		//账号输入框
		textF01ZH = new JTextField();
		textF01ZH.setBounds(188, 121, 127, 21);
		getContentPane().add(textF01ZH);
		textF01ZH.setColumns(10);
	
		JButton btn01QD = new JButton("确定");
		
		btn01QD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkInputZH() & checkInputMM()&&rdbtnYH.isSelected()) {
					
					UserID = textF01ZH.getText();//保存ID到加购按钮事件
	                String PassWord = textF01PsW.getText();
	                String sql = "select * from usertable where UserID=? and PassWord=?";
            	    String[] str = new String[] {UserID ,  PassWord};
            	    ResultSet rs= new DatabaseConnection().search(sql, str);
            	   try {
					if(rs.next()) {
						   JOptionPane.showMessageDialog(null, "用户登录成功");						   	   
          //跳转到用户查看商品窗口
					    JFrame frame=UserGs.getIns();//单例
					    frame.setVisible(true);
					   }				
					else
						JOptionPane.showMessageDialog(null, "登录失败！请重新输入");
				}  catch (Exception e1) {
					e1.printStackTrace();
				}
				}
				
				else if(rdbtnGLY.isSelected()&&checkInputZH() & checkInputMM()) {
					String MagID = textF01ZH.getText();
	                String ManPsW = textF01PsW.getText();
	                String sql = "select * from managertable where MagID=? and ManPsW=?";
            	    String[] str = new String[] {MagID,  ManPsW};
            	    ResultSet rs=  new DatabaseConnection().search(sql, str);
            	    try {
						if(rs.next()) {
							JOptionPane.showMessageDialog(null, "管理员登录成功");
						   //跳转到管理员窗口查看商品
						   JFrame frame1=ManagerGs.getIns();
						  frame1.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "登录失败！请重新输入");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "登录失败");

			}	
		});
		btn01QD.setBounds(126, 223, 127, 23);
		getContentPane().add(btn01QD);
		
		 lbl02 = new JLabel("");
		lbl02.setForeground(Color.RED);
		lbl02.setBounds(332, 175, 99, 15);
		contentPane.add(lbl02);
		
		 lbl01 = new JLabel("");
		lbl01.setForeground(Color.RED);
		lbl01.setBounds(332, 127, 94, 15);
		contentPane.add(lbl01);
		/**
		 * 登录界面-注册按钮  ->注册界面
		 */
		JButton btnZC = new JButton("注册");//跳到注册界面
		btnZC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame ct2= Register.getIns();
					//ct2.main(null);
					ct2.setVisible(true);
			}
		});
		btnZC.setBounds(285, 47, 97, 23);
		contentPane.add(btnZC);
		
		textF01PsW = new JPasswordField();//密文输入
		textF01PsW.setBounds(188, 169, 127, 21);
		contentPane.add(textF01PsW);
		}
	/**
	 * // 判断输入ID是否为空
	 * @return
	 */
	public boolean checkInputZH() {//ID
		//判断字符串是否为空
		String id = textF01ZH.getText();
		if (id.length() == 0) {
			lbl01.setText("不能为空");
			return false;
		}
		return true;
	}
	/**
	 *  判断输入密码是否为空
	 * @return
	 */
public  boolean checkInputMM() {	
		String mm = textF01PsW.getText();
		if (mm.length() == 0) {
			lbl02.setText("不能为空");
			return false;
		}
		return true;
	}
}