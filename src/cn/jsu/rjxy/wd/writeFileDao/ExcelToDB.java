package cn.jsu.rjxy.wd.writeFileDao;
//导入--未完成
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import cn.jsu.rjxy.wd.sql.DatabaseConnection;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 导入Excel数据到数据库里-goodstable
 * @author a'su's
 *
 */
public class ExcelToDB {
	private static StringBuffer fileName = new StringBuffer("D:\\电子商城系统-goodstable.xls");
	private static int count = 0;

	/**
	 *  获取Excel中所有的数据
	 * @param args
	 */
	
	public static void getAllExcel() {
		Goods gs=new Goods();
		
		try {
			// 获得工作薄
			Workbook workBook =Workbook.getWorkbook(new File(fileName.toString()));
			// 获得工作表
			Sheet sheet = workBook.getSheet(0);
			// 获得行和列的长度
					int col = sheet.getColumns();
					int row = sheet.getRows();
		
		for(int i=1;i<row;i++) {
			for(int j=0;j<col;j++) {
				//获得数据
				String GsID=sheet.getCell(j++, i).getContents();//取得字段内容
				String GsName = sheet.getCell(j++, i).getContents();
				String GsPrice = sheet.getCell(j++, i).getContents();
				String GsNum = sheet.getCell(j++, i).getContents();	
				String GsKind = sheet.getCell(j, i).getContents();
				// 输出数据
				System.out.println(" GsID = " + GsID + " GsName = " + GsName + " GsNum = "
						+ GsNum+"GsKind:"+GsKind);
/**
 * 添加至数据库，若有主键判断是否存在。存在则更新，无则加入
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
