package cn.jsu.rjxy.wd.writeFileDao;
//����--δ���
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import jxl.Sheet;
import jxl.Workbook;
/**
 * ����Excel���ݵ����ݿ���-goodstable
 * @author a'su's
 *
 */
public class ExcelToDB {
	private static StringBuffer fileName = new StringBuffer("D:\\�����̳�ϵͳ-goodstable.xls");
	private static int count = 0;

	/**
	 *  ��ȡExcel�����е�����
	 * @param args
	 */
	
	public static void getAllExcel() {
		Goods gs=new Goods();
		
		try {
			// ��ù�����
			Workbook workBook =Workbook.getWorkbook(new File(fileName.toString()));
			// ��ù�����
			Sheet sheet = workBook.getSheet(0);
			// ����к��еĳ���
					int col = sheet.getColumns();
					int row = sheet.getRows();
		
		for(int i=1;i<row;i++) {
			for(int j=0;j<col;j++) {
				//�������
				String GsID=sheet.getCell(j++, i).getContents();//ȡ���ֶ�����
				String GsName = sheet.getCell(j++, i).getContents();
				String GsPrice = sheet.getCell(j++, i).getContents();
				String GsNum = sheet.getCell(j++, i).getContents();	
				String GsKind = sheet.getCell(j, i).getContents();
				// �������
				System.out.println(" GsID = " + GsID + " GsName = " + GsName + " GsNum = "
						+ GsNum+"GsKind:"+GsKind);
/**
 * ��������ݿ⣬���������ж��Ƿ���ڡ���������£��������
 */
				String str[]=new String[]{GsID,GsName,GsPrice,GsNum,GsKind};
			if(canInsert(GsID,str)) {
				
			}
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static boolean canInsert(String GsID, String[] str) {
		ResultSet res1 = DatabaseConnection.search("select * from goodstable where GsID=?", new String[] { GsID });
		ResultSet res2 = DatabaseConnection.search("select * from record_charge where username=? and chargemoney=? and chargeTime=?", str);
		
	return false;
}
	public static void main(String[] args) {
		
	}

}
