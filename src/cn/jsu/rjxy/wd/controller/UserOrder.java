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
 * �û���������
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
		frameAAA.setVisible(true);// ����ɼ�
		frameAAA.setLocationRelativeTo(null);// �������
	}
	public static  JFrame getIns() {
		if(frameAAA==null) {
			frameAAA=new UserOrder();
		}
		return frameAAA;
	}
		public UserOrder(){
			setTitle("������ѯ");
			//ͼ��
			setIconImage(Toolkit.getDefaultToolkit().getImage(UserOrder.class.getResource("/\u56FE\u6807/h11.png")));
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 557, 382);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
			setContentPane(contentPane);
			contentPane.setLayout(null);// ������岼��Ϊ���Բ���
			// ���ù������
			JScrollPane scrollPane = new JScrollPane();// �����������
			scrollPane.setBounds(10, 87, 508,229);// ���ô�С��λ��
			contentPane.add(scrollPane);// �����������뵽���������
			// ʹ�ö�̬�������ݣ��б����������ݣ�
			titles = new Vector<String>();// ���嶯̬�����ʾ������
			//������
			Collections.addAll(titles, "GsID", "UserID", "shoppingNum","shoppingTime");
	
			String sql="select * from shop";//�����ѯ���
			Vector<Vector> stuInfo = DataOperate.getSelectAll2(sql);// �鿴����-���ݿ�
			model = new DefaultTableModel(stuInfo, titles);
			table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
			table = new JTable(stuInfo,titles);// ʹ�þ�̬����ʵ�������
	
			scrollPane.setViewportView(table);//����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
			JLabel lblKeyA = new JLabel("�����������˺�:");
			lblKeyA.setBounds(10, 10, 105, 15);
			contentPane.add(lblKeyA);
			txtKeyA = new JTextField();
			txtKeyA.setBounds(125, 7, 166, 21);
			contentPane.add(txtKeyA);
		    txtKeyA.setColumns(10);
		    
		    JButton btnFH = new JButton("\u8FD4\u56DE\u8D2D\u7269\u5546\u57CE");//������һ��
			btnFH.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnFH.setBounds(362, 51, 119, 23);
			contentPane.add(btnFH);			
			/**
			 * ���Ұ�ť(�û���-��UserID����-������ʾ
			 */
			JButton btnFindA = new JButton("\u786E\u5B9A\u67E5\u8BE2\u8BA2\u5355");
			btnFindA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String a1 = String.valueOf(txtKeyA.getText());//�û�ID
					String b1=String.valueOf(textB.getText());//GsID
					String[] str = new String[] {a1,b1};
					//String [] str2=new String [] {};
					String sql = "select * from shop where UserID=? and GsID=?";
					ResultSet rst=new DatabaseConnection().search(sql,str);
					try {
						if(rst.next()) {
							JOptionPane.showMessageDialog(null,"���ҳɹ���"+"��ƷID:"+rst.getString(1)+"�û�ID��"+rst.getString(2)+"������:"+rst.getString(3)+"����ʱ�䣺"+rst.getString(4));
						}
						else
							JOptionPane.showMessageDialog(null,"����ʧ�ܣ�����������");
					} catch (SQLException e1) {	
						e1.printStackTrace();
					}
				}
			});
			btnFindA.setBounds(362, 6, 119, 23);
			contentPane.add(btnFindA);
			
			JLabel lblB = new JLabel("��������ƷID��");
			lblB.setBounds(10, 55, 105, 15);
			contentPane.add(lblB);
			
			textB = new JTextField();
			textB.setBounds(125, 52, 166, 21);
			contentPane.add(textB);
			textB.setColumns(10);
		}
}