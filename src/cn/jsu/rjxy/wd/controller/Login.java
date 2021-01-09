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
 * ��¼����
 * @author a'su's
 *
 */
class NewPanel extends JPanel {
	  public NewPanel() {
		  
	  }
	  public void paintComponent(Graphics g) {//�ػ���屳��
			//����һ��δ��ʼ����ͼ��ͼ��
			ImageIcon icon=new ImageIcon("source"+File.separator+"timg.jpg");
			//����ָ��ͼ���������ŵ��ʺ�ָ�������ڲ���ͼ��
			g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),this);
		}
}
/**
 * ��¼����--ѡ��-ע��
 * @author a'su's
 *
 */
public class Login extends JFrame {
	/**
	 * ��¼�û�ID����ֵ
	 */
	public static String UserID;
	//private JTextField textF01PsW;
	private JPanel contentPane;
	/**
	 * ���������ʾ��
	 */
	private JLabel lbl01;
	private JLabel lbl02;
	/**
	 * �˺������
	 */
	private JTextField textF01ZH;
	private static UserGs frame=null;
	/**
	 * ���������
	 */
	
	private JPasswordField textF01PsW;

	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Login login = new Login();
				login.setVisible(true);
				login.setLocationRelativeTo(null);//�������
				//frame.setBounds(600, 200, 750, 550);
				//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//�������
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}	
	/**
	 * ��¼����-ȷ����ť  ����Ա��¼->����Ա����  �û���¼->�û�����	
	 */
	public  Login() {
		setForeground(Color.WHITE);
	
		setBackground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/\u56FE\u6807/h1.png")));
		setResizable(true);
		setRootPaneCheckingEnabled(false);
		getContentPane().setForeground(Color.LIGHT_GRAY);
		setTitle("��ӭ���������̳�");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPane = new NewPanel(); //�ű���
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		 contentPane.setLayout(null);
	
		JLabel lbl01ID = new JLabel("��ѡ���¼��ݣ�");
		lbl01ID.setForeground(Color.WHITE);
		lbl01ID.setBounds(34, 51, 101, 15);
		getContentPane().add(lbl01ID);
		
		JLabel lblPsWord = new JLabel("���������룺");
		lblPsWord.setForeground(Color.WHITE);
		lblPsWord.setBounds(34, 172, 137, 15);
		getContentPane().add(lblPsWord);
		
		JRadioButton rdbtnGLY = new JRadioButton("����Ա");
		rdbtnGLY.setBounds(145, 47, 127, 23);
		getContentPane().add(rdbtnGLY);
		
		JRadioButton rdbtnYH = new JRadioButton("�û�");
		rdbtnYH.setBounds(145, 79, 127, 23);
		getContentPane().add(rdbtnYH);
		//ʵ�ֵ�ѡ
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnGLY);
		group.add(rdbtnYH);
		
		JLabel lbl01ZH = new JLabel("�������˺ţ�");
		lbl01ZH.setForeground(Color.WHITE);
		lbl01ZH.setBounds(34, 124, 81, 15);
		getContentPane().add(lbl01ZH);
		//�˺������
		textF01ZH = new JTextField();
		textF01ZH.setBounds(188, 121, 127, 21);
		getContentPane().add(textF01ZH);
		textF01ZH.setColumns(10);
	
		JButton btn01QD = new JButton("ȷ��");
		
		btn01QD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkInputZH() & checkInputMM()&&rdbtnYH.isSelected()) {
					
					UserID = textF01ZH.getText();//����ID���ӹ���ť�¼�
	                String PassWord = textF01PsW.getText();
	                String sql = "select * from usertable where UserID=? and PassWord=?";
            	    String[] str = new String[] {UserID ,  PassWord};
            	    ResultSet rs= new DatabaseConnection().search(sql, str);
            	   try {
					if(rs.next()) {
						   JOptionPane.showMessageDialog(null, "�û���¼�ɹ�");						   	   
          //��ת���û��鿴��Ʒ����
					    JFrame frame=UserGs.getIns();//����
					    frame.setVisible(true);
					   }				
					else
						JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�����������");
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
							JOptionPane.showMessageDialog(null, "����Ա��¼�ɹ�");
						   //��ת������Ա���ڲ鿴��Ʒ
						   JFrame frame1=ManagerGs.getIns();
						  frame1.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�����������");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "��¼ʧ��");

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
		 * ��¼����-ע�ᰴť  ->ע�����
		 */
		JButton btnZC = new JButton("ע��");//����ע�����
		btnZC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame ct2= Register.getIns();
					//ct2.main(null);
					ct2.setVisible(true);
			}
		});
		btnZC.setBounds(285, 47, 97, 23);
		contentPane.add(btnZC);
		
		textF01PsW = new JPasswordField();//��������
		textF01PsW.setBounds(188, 169, 127, 21);
		contentPane.add(textF01PsW);
		}
	/**
	 * // �ж�����ID�Ƿ�Ϊ��
	 * @return
	 */
	public boolean checkInputZH() {//ID
		//�ж��ַ����Ƿ�Ϊ��
		String id = textF01ZH.getText();
		if (id.length() == 0) {
			lbl01.setText("����Ϊ��");
			return false;
		}
		return true;
	}
	/**
	 *  �ж����������Ƿ�Ϊ��
	 * @return
	 */
public  boolean checkInputMM() {	
		String mm = textF01PsW.getText();
		if (mm.length() == 0) {
			lbl02.setText("����Ϊ��");
			return false;
		}
		return true;
	}
}