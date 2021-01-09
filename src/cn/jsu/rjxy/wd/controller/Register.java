package cn.jsu.rjxy.wd.controller;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import cn.jsu.rjxy.wd.writeFileDao.AddToFile;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
/**
 * �û�ע�����--
 */
public class Register extends JFrame {
	/**
	 * ����ע���û�IDд���ļ�
	 * ����ע���û�NAMEд���ļ�
	 * ����ע���û�����д���ļ�
	 */
	public static String UserID;//����ע���û�IDд���ļ�
	public static String UserName;//����ע���û�NAMEд���ļ�
	public static String PassWord;//����ע���û�����д���ļ�
	
	private static JTextField text02FXM;
	private static JTextField textF02ZH;
	private static JLabel lbl01;
	private static JLabel lbl03;
	private static JLabel lbl02;
	private JPasswordField textF02MM;
	private static Register frame;
	//private  Register frame;
  	public static void main(String[] args) {	
					Register frame = new Register();
					frame.setVisible(true);
					//frame.addIcon();
				
	}
  	/**
  	 * ����
  	 * @return Register
  	 */
  	
  	public static  JFrame getIns() {//����
		if(frame==null) {
			frame=new Register();
		}
		return frame;
	}
	public Register() {
	
		ImageIcon icon=new ImageIcon("source"+File.separator+"lm3.jpg");
		Image img=icon.getImage().getScaledInstance(500, 290, Image.SCALE_FAST);
		//                                         ��   ��
		JLabel jlabel=new JLabel(new ImageIcon(img));
		jlabel.setBounds(0, 0, 500, 290);
		this.getLayeredPane().add(jlabel,new Integer(Integer.MIN_VALUE));
		JPanel jp=(JPanel) this.getContentPane();
		JRootPane jpl=(JRootPane) this.getRootPane();
		jp.setOpaque(false);
		jpl.setOpaque(false);
		//frame.setVisible(true);
		//
		//ͼ��
		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/\u56FE\u6807/h1.png")));
		getContentPane().setForeground(Color.RED);
		setResizable(true);
		setBounds(100, 100, 450, 300);
		setTitle("��ӭ��Ϊ�����̳ǵ�һԱ");
		getContentPane().setLayout(null);

		JLabel lbl02YHZC = new JLabel("�� �� ע ��");
		lbl02YHZC.setForeground(Color.WHITE);
		lbl02YHZC.setBounds(184, 26, 149, 15);
		getContentPane().add(lbl02YHZC);

		JLabel lbl02XM = new JLabel("�������û�����");
		lbl02XM.setForeground(Color.WHITE);
		lbl02XM.setBounds(65, 84, 89, 15);
		getContentPane().add(lbl02XM);
		/**
		 *  ע��ID�����
		 */
		text02FXM = new JTextField();
		text02FXM.setBounds(197, 81, 100, 21);
		getContentPane().add(text02FXM);
		text02FXM.setColumns(10);
		// �����ǩ
		JLabel lbl02PsW = new JLabel("���������룺");
		lbl02PsW.setForeground(Color.WHITE);
		lbl02PsW.setBounds(65, 146, 89, 15);
		getContentPane().add(lbl02PsW);
		/**
		 *  ȷ����ť--ע��ɹ������û���Ϣ�������ļ�
		 */
		JButton btn02QD = new JButton("ȷ��");
		btn02QD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkInputID() & checkInputZH() & checkInputMM()) {
					 UserID = textF02ZH.getText();//String String String 
	                UserName = text02FXM.getText();
	                PassWord = textF02MM.getText();
					JOptionPane.showMessageDialog(null, "ע��ɹ�����ӭ���������̳�");
					String sql = "insert into usertable (UserID,UserName,PassWord) values(?,?,?)";
            	    String[] str = new String[] {UserID , UserName, PassWord};
            	    new DatabaseConnection().add(sql, str);//�������ݿ���
            	    //д���ļ���
            	    try {
						AddToFile.writeFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
            	}
			}
		});

		btn02QD.setBounds(100, 202, 89, 22);
		getContentPane().add(btn02QD);
		JLabel lbl02ZH = new JLabel("�������˺ţ�");
		lbl02ZH.setForeground(Color.WHITE);
		lbl02ZH.setBounds(65, 115, 89, 15);
		getContentPane().add(lbl02ZH);

		textF02ZH = new JTextField();
		textF02ZH.setBounds(197, 112, 100, 21);
		getContentPane().add(textF02ZH);
		textF02ZH.setColumns(10);
		// ������ʾ��
		 lbl01 = new JLabel("");
		lbl01.setForeground(Color.RED);
		lbl01.setBounds(307, 84, 121, 15);
		getContentPane().add(lbl01);

		 lbl02 = new JLabel("");
		lbl02.setForeground(Color.RED);
		lbl02.setBounds(307, 115, 121, 15);
		getContentPane().add(lbl02);

		 lbl03 = new JLabel("");
		 lbl03.setForeground(Color.RED);
		lbl03.setBounds(307, 149, 121, 15);
		getContentPane().add(lbl03);
		/**
		 * ���ص�¼����
		 */
		JButton btnFH = new JButton("����");
		btnFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFH.setBounds(266, 203, 89, 23);
		getContentPane().add(btnFH);
		
		textF02MM = new JPasswordField();//��������
		textF02MM.setBounds(197, 143, 100, 21);
		getContentPane().add(textF02MM);
		
	
	}

	public boolean checkInputID() {
		if (text02FXM.getText().length() == 0) {
			lbl01.setText("����Ϊ��");
			return false;
		}
		return true;
	}
/**
 *  �ж�ע��ID�Ƿ�Ϊ��
 * @return �Ƿ�Ϊ��
 */
	public boolean checkInputZH() {
		
		String xm = textF02ZH.getText();
		if (xm.length() == 0) {
			lbl02.setText("����Ϊ��");
			return false;
		}
		return true;
	}
/**
 * �ж�ע�����������Ƿ�Ϊ��
 * @return �Ƿ�Ϊ��
 */
	public boolean checkInputMM() {
		if (textF02MM.getText().length() == 0) {// ��ȡ���ݳ���
			lbl03.setText("���벻��Ϊ��");// ���þ�����Ϣ
			return false;
		}
		return true;
	}
}
