package cn.jsu.rjxy.wd.writeFileDao;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * ����shop���ݵ�Excel
 * @author a'su's
 *
 */

public class ToExcel {
	public static void toWriteExcel() throws Exception {
		WritableWorkbook book =null ;
		File f=new File("d:\\�����̳�ϵͳ-UserOrderTable.xls");
		book=Workbook.createWorkbook(f);//���ļ�������һ��Workbook
		//����������
		WritableSheet sheet=book.createSheet("test1", 0);
		//�������ݿ��б������
		String sqlTableName = "shop";
		List<Shop> list= DatabaseConnection.getAllOrder("select * from shop", null);
		//���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ-��ͷ
		Label labelGsID = new Label(0, 0 , "GsID");
		Label labelUserID = new Label(1, 0 , "UserID");
		Label labelshoppingNum = new Label(2, 0 , "shoppingNum");
		Label labelshoppingTime = new Label(3, 0 , "shoppingTime");
		//��ӵ�һ�е���Ԫ��
		sheet.addCell(labelGsID);
		sheet.addCell(labelUserID);
		sheet.addCell(labelshoppingNum);
		sheet.addCell(labelshoppingTime);
		//�����ݿ⵽Excel
		for(int i = 0; i < list.size(); i++) {
			//���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
    		Label label_GsID = new Label(0, i+1 , list.get(i).getGsID());
    		Label label_UserID = new Label(1, i+1 , list.get(i).getUserID() );
    		Label label_shoppingNum = new Label(2, i+1 , list.get(i).getShoppingNum());
    		Label label_shoppingTime = new Label(3, i+1 , list.get(i).getShoppingTime());
		//��ӵ���һ�е�Ԫ��
    		sheet.addCell(label_GsID);
    		sheet.addCell(label_UserID);
    		sheet.addCell(label_shoppingNum);
    		sheet.addCell(label_shoppingTime);
		}
		book.write();//д
		book.close();//�ر�
		System.out.println("ToExcel���");
	}
	
	public static void main(String[] args){//����
		try {
			toWriteExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
