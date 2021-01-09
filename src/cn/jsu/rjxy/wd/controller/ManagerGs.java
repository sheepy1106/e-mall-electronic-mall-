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
 * ����Ա�鿴��Ʒ����--
 */
public class ManagerGs extends JFrame {
	private static JFrame frame;
	//private JPanel contentPane;
	private JPanel contentPane;// ���崰��������壬���ø����
	private JTable table;// ������
	private JTextField txtKey;//������ҹؼ����ı���
	private DefaultTableModel model;// ����������ģ��
	private TableRowSorter sorter;//����������
	private ArrayList<RowSorter.SortKey> sortKeys;//�����������
	
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
	 * ����
	 * @return ManagerGs
	 */
	public static  JFrame getIns() {//����
		if(frame==null) {
			frame=new ManagerGs();
		}
		return frame;
	}
	public ManagerGs() {
		setTitle("��Ʒ��Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerGs.class.getResource("/\u56FE\u6807/h3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ô���رհ�ť
		setBounds(100, 100, 450, 403);// ���ô���λ�����С
		contentPane = new JPanel();// ʵ�����������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		setContentPane(contentPane);// ������Ĭ�����		
		// ���ù������
				JScrollPane scrollPane = new JScrollPane();// �����������
				scrollPane.setBounds(31, 82, 367, 161);// ���ô�С��λ��
				contentPane.add(scrollPane);// �����������뵽���������
			// ʹ�ö�̬�������ݣ��б����������ݣ�
				titles = new Vector<String>();// ���嶯̬�����ʾ������
				Collections.addAll(titles, "GsID", "GsName", "GsPrice","GsNum");
				String sql="select * from goodstable order by GsPrice asc ";//�����ѯ���
				Vector<Vector> stuInfo = DataOperate.getSelectAll(sql);// �����ݿ��ж�ȡ����������
				//ʹ�þ�̬���ݴ���DefaultTableModel����ģ��
				model = new DefaultTableModel(stuInfo, titles);				
				table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
				scrollPane.setViewportView(table);//����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
			
				txtKey = new JTextField();
				txtKey.setBounds(115, 23, 119, 21);
				contentPane.add(txtKey);
				txtKey.setColumns(10);
	
				JButton btnFind = new JButton("����");
				btnFind.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
			//��ƷID����
						String a = String.valueOf(txtKey.getText());
						String[] str = new String[] {a};
						String sql = "select * from goodstable where GsID=?";//������
						ResultSet rst=new DatabaseConnection().search(sql,str);
						try {
							if(rst.next()) {
								JOptionPane.showMessageDialog(null,"���ҳɹ���"+"��ƷID: "+rst.getString(1)+" ��Ʒ���ƣ�"+rst.getString(2)+"��Ʒ�۸�:"+rst.getString(3)+" ��Ʒ������"+rst.getString(4)+" ��Ʒ���ࣺ"+rst.getNString(5));
							}
							else
								JOptionPane.showMessageDialog(null,"����Ʒ�����ڣ�");
						} catch (SQLException e1) {	
							e1.printStackTrace();
						}
					}
				});
				btnFind.setBounds(272, 5, 58, 25);
				contentPane.add(btnFind);
				JLabel lblKey = new JLabel("��������ƷID:");
				lblKey.setBounds(22, 26, 86, 15);
				contentPane.add(lblKey);
	
				JButton btnAdd = new JButton("����");
				btnAdd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String GsID=textID.getText();
						String GsName=textName.getText();
						String GsPrice=textPrice.getText();
						String GsNum=textNum.getText();
						String GsKind=textKind.getText();
						String sql = "insert into goodstable (GsID,GsName,GsPrice,GsNum,GsKind) values(?,?,?,?,?)";
						String[] str = new String[] {GsID,GsName,GsPrice,GsNum,GsKind};
						new DatabaseConnection().add(sql, str);//��������
						JOptionPane.showMessageDialog(null, "��Ϣ�Ѽ������ݿ�");
					}
				});
				btnAdd.setBounds(340, 7, 58, 21);
				contentPane.add(btnAdd);
				
				JLabel lblAdd = new JLabel("����������Ʒ��Ϣ��");//��ǩ
				lblAdd.setBounds(32, 269, 144, 15);
				contentPane.add(lblAdd);
/**
 * ����Ա�鿴��Ʒ����--ɾ����ť-��GsIDɾ����Ʒ��Ϣ
 */
				
				JButton btnDelete = new JButton("ɾ��");//��Ʒ��Ϣ
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
							 new DatabaseConnection().add(sql, str);//ɾ����������
								JOptionPane.showMessageDialog(null, "�Ѵ����ݿ���ɾ��");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}						
					}
				});
				btnDelete.setBounds(340, 49, 58, 23);
				contentPane.add(btnDelete);
	/**
	 * ������ť-��ת��������	
	 */
				JButton btnNewButton = new JButton("����");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//�������ڣ�
						JFrame frame03= ManagerOrder.getIns();//��ת������Ա��������
						frame03.setVisible(true);
					}
				});
				btnNewButton.setBounds(272, 49, 58, 23);
				contentPane.add(btnNewButton);
				//������Ϣ�����
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
