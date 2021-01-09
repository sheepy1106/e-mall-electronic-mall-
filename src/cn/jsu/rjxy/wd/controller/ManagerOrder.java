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
 * ����Ա�鿴����
 */
public class ManagerOrder extends JFrame {
/**
 * ����Ա�鿴��������
 */
	private static JFrame frame101;
	private JPanel contentPane;
	/**
	 * ��������
	 */
	private Vector<String> titles;
	
	private DefaultTableModel model;
	/**
	 * ������
	 */
	
	private JTable table;
	/**
	 * �����������
	 */
	private JTextField txtKey;
	/**
	 * �޸�ֵ-GsID�����
	 */
	private JTextField textXG;

	public static void main(String[] args) {
		ManagerOrder frame101 = new ManagerOrder();
		frame101.setVisible(true);// ����ɼ�
		frame101.setLocationRelativeTo(null);// �������
	}
	public static  JFrame getIns() {//����
		if(frame101==null) {
			frame101=new ManagerOrder();
		}
		return frame101;
	}
	public ManagerOrder() {
		setTitle("������Ϣ����");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ManagerOrder.class.getResource("/\u56FE\u6807/h3.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));// �������߿�
		setContentPane(contentPane);
		contentPane.setLayout(null);// ������岼��Ϊ���Բ���
		// ���ù������
		JScrollPane scrollPane = new JScrollPane();// �����������
		scrollPane.setBounds(0, 84, 537, 161);// ���ô�С��λ��
		
		contentPane.add(scrollPane);// �����������뵽���������
		// ʹ�ö�̬�������ݣ��б����������ݣ�
		titles = new Vector<String>();// ���嶯̬�����ʾ������
		// ������
		Collections.addAll(titles, "GsID", "UserID", "shoppingNum", "shoppingTime");
		String sql = "select * from shop";// �����ѯ���
		Vector<Vector> stuInfo = DataOperate.getSelectAll2(sql);// �����ݿ��ж�ȡ����������

		model = new DefaultTableModel(stuInfo, titles) ;
		table = new JTable(model);// ʹ��DefaultTableModel����ģ��ʵ�������
		
		table = new JTable(stuInfo, titles);// ʹ�þ�̬����ʵ�������
		/* ����Ա�鿴��Ʒ����
		 */
		scrollPane.setViewportView(table);// ����ʹ�ù��������ʾ��������ʹ�ù��������ʾ��������б����޷���ʾ
		JLabel lblKey = new JLabel("������(������):");
		lblKey.setBounds(26, 26, 111, 15);
		contentPane.add(lblKey);
		// �����������
		txtKey = new JTextField();
		txtKey.setBounds(141, 23, 262, 21);
		contentPane.add(txtKey);
		txtKey.setColumns(10);
		/**
		 *  ������Ұ�ť-��������Ų�-��ʾ�ڵ���
		 */
		JButton btnFind101 = new JButton("����");
		btnFind101.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ѯ���� 4 shoppingNum
				String key = String.valueOf(txtKey.getText());// ��ȡ����ؼ����ı����ֵ--String
				String sql = "select * from shop where shoppingNum=? ";// ��ѯ����--������
				String[] str = new String[] { key };
				ResultSet rst = new DatabaseConnection().search(sql, str);//
				// ��ѯ����ʾ
				try {
					if (rst.next()) {
						JOptionPane.showMessageDialog(null, "���ҳɹ���" + "��ƷID:" + rst.getString(1) + " �û�ID��"
								+ rst.getString(2) + " ������:" + rst.getString(3) + " ����ʱ�䣺" + rst.getString(4));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});// ����Ա�鿴��������
		btnFind101.setBounds(80, 51, 68, 23);
		contentPane.add(btnFind101);
/**
 * ɾ����ť-��������ɾ������-������ʾ�ɹ�
 */
		JButton btnSC101 = new JButton("ɾ��");
		btnSC101.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = String.valueOf(txtKey.getText());// ��ȡ����ؼ����ı����ֵ--String
				String sql1 = "select * from shop where shoppingNum=?";// ������
				String[] str1 = new String[] { key };
				ResultSet rst = new DatabaseConnection().search(sql1, str1);
				try {
					if (rst.next()) {
						String sql = "delete from shop where shoppingNum=?";
						String[] str = new String[] { key };// ?
						new DatabaseConnection().add(sql, str);// ɾ����������
						JOptionPane.showMessageDialog(null, "�Ѵ����ݿ���ɾ��");
					} else
						JOptionPane.showMessageDialog(null, "δɾ���ɹ�������������");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSC101.setBounds(225, 51, 60, 23);
		contentPane.add(btnSC101);
/**
 *  �޸Ķ���������Ա��-���������޸�GsID		
 */
		JButton btnXG = new JButton("ȷ���޸�");
		btnXG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key1 = String.valueOf(txtKey.getText());// ��ȡ�ı����ֵ--shoppingNum
				String key2 = String.valueOf(textXG.getText());// ��ȡ�ı����ֵ--�޸���ȷ��shoppingNum
				String[] str = new String[] { key2, key1 };
				String sql1 = "update shop set GsID=? where shoppingNum=?";
				new DatabaseConnection().add(sql1, str);// �޸ı�������
				JOptionPane.showMessageDialog(null, "���ݿ�����Ϣ�ѳɹ��޸ģ�");
			}
		});

		btnXG.setBounds(354, 269, 86, 19);
		contentPane.add(btnXG);
		JButton btnFH = new JButton("����");
		// ������һ����
		btnFH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFH.setBounds(449, 22, 60, 23);
		contentPane.add(btnFH);

		JLabel lblXG = new JLabel("��������ȷGsID��");
		lblXG.setBounds(26, 271, 117, 15);
		contentPane.add(lblXG);

		textXG = new JTextField();
		textXG.setBounds(142, 268, 116, 21);
		contentPane.add(textXG);
		textXG.setColumns(10);
		
		JButton btnDC = new JButton("\u5BFC\u51FA\u8BA2\u5355\u6570\u636E");//����������
		btnDC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ToExcel.main(null);
				JOptionPane.showMessageDialog(null, "���������̳�ϵͳ-UserOrderTable�ɹ����˳�������D�̲鿴");
			}
		});
		btnDC.setBounds(354, 51, 111, 23);
		contentPane.add(btnDC);
	}
}
