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
 * �û���¼�ɹ��Ľ���--�鿴��Ʒ--����
 */
public class UserGs extends JFrame{
	private static JFrame frame;
	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private JTextField txtKeyF;//������ҹؼ����ı���
	private DefaultTableModel model;// ����������ģ��
	private Vector<String> titles;
	public static void main(String[] args) {
		frame = new UserGs();// ʵ��������
		frame.setLocationRelativeTo(null);// �������
		frame.setVisible(true);// ����ɼ�
	}
	/**
	 * ���幹�췽���������弰���.
	 */
	public UserGs() {
		setTitle("�߹�·����Ҫ���");
		setIconImage(Toolkit.getDefaultToolkit().getImage(UserGs.class.getResource("/\u56FE\u6807/h11.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���رհ�ť
		setBounds(100, 100, 450, 403);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����

		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(10, 85, 416, 271);// ���ô�С��λ��
		contentPane.add(scrollPane);// �����������뵽���������

		// ʹ�ö�̬�������ݣ��б����������ݣ�
		titles = new Vector<String>();// ���嶯̬�����ʾ������
		//��Ʒ��,"GsNum"
		Collections.addAll(titles, "GsID", "GsName", "GsPrice");
		String sql="select * from goodstable order by GsPrice asc";//�����ѯ���---order by����
		Vector<Vector> stuInfo = DataOperate.getSelectAll(sql);// �����ݿ��ж�ȡ����������

		model = new DefaultTableModel(stuInfo, titles) ;
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		scrollPane.setViewportView(table);//����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
		
		JLabel lblKey = new JLabel("��������Ʒ���ƣ�");
		lblKey.setBounds(29, 26, 102, 15);
		contentPane.add(lblKey);
	
		txtKeyF = new JTextField();
		txtKeyF.setBounds(158, 23, 232, 21);
		contentPane.add(txtKeyF);
		txtKeyF.setColumns(10);	
		/**
		 * ������Ұ�ť-������-������ʾ
		 */
		JButton btnFindF = new JButton("������Ʒ");//��ѯ��Ʒ
		btnFindF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = String.valueOf(txtKeyF.getText());
				String[] str = new String[] {a};
				String sql = "select * from goodstable where GsName=?";//������
				ResultSet rst=new DatabaseConnection().search(sql,str);
				try {
					if(rst.next()) {
						JOptionPane.showMessageDialog(null,"���ҳɹ���"+"��ƷID:"+rst.getString(1)+"��Ʒ����"+rst.getString(2)+"��Ʒ�۸�:"+rst.getString(3)+"��Ʒ������"+rst.getString(4)+"��Ʒ���ࣺ"+rst.getNString(5));
					}
				} catch (SQLException e1) {	
					e1.printStackTrace();
				}
			}
		});
		btnFindF.setBounds(29, 50, 95, 25);
		contentPane.add(btnFindF);
//�û�--�鿴��Ʒ
		/**
		 * �鿴������ť-��ת���û���������
		 */
		JButton btnCX = new JButton("�鿴����");
		btnCX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame ctaa=UserOrder.getIns();//�û��������棡
				ctaa.setVisible(true);// ����ɼ�
				ctaa.setLocationRelativeTo(null);// �������
			}
		});
		btnCX.setBounds(300, 50, 95, 25);
		contentPane.add(btnCX);
		/**
		 * ����ť--�����Ʒ-����ӹ�-��������
		 */
		JButton btnBuy = new JButton("�ӹ�");//���ѡ��
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int count=table.getSelectedRow();////��ȡ��ѡ�е��кţ���¼��
				String GsID= table.getValueAt(count, 0).toString();//��ȡ��ȡ�кŵ�ĳһ�е�ֵ��Ҳ�����ֶΣ�
				String shopNum=DB.getShoppingNum().toString();//��ö������
				String date=DB.getTime();//��õ�ǰʱ��
				
				String sql="insert into shop(GsID,UserID,shoppingNum,shoppingTime) values(?,?,?,?)";
				PreparedStatement pst;
				try {
				pst=new DatabaseConnection().getConnection().prepareStatement(sql);
					pst.setString(1, GsID);
				    pst.setString(2, Login.UserID);
				    pst.setString(3, shopNum);
				   pst.setString(4, date);
				    pst.executeUpdate();//executeUpdate
				    JOptionPane.showMessageDialog(null,"�ӹ��ɹ���");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});	
		btnBuy.setBounds(160, 50, 95, 25);
		contentPane.add(btnBuy);
}
	/**
	 * ����
	 * @return UserGs
	 */
	public static  JFrame getIns() {
		if(frame==null) {
			frame=new UserGs();
		}
		return frame;
	}
}