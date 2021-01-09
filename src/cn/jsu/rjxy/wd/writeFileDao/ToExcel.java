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
 * 导出shop数据到Excel
 * @author a'su's
 *
 */

public class ToExcel {
	public static void toWriteExcel() throws Exception {
		WritableWorkbook book =null ;
		File f=new File("d:\\电子商城系统-UserOrderTable.xls");
		book=Workbook.createWorkbook(f);//以文件名创建一个Workbook
		//创建工作表
		WritableSheet sheet=book.createSheet("test1", 0);
		//导出数据库中表的数据
		String sqlTableName = "shop";
		List<Shop> list= DatabaseConnection.getAllOrder("select * from shop", null);
		//插入到的Excel表格的行号，默认从0开始-表头
		Label labelGsID = new Label(0, 0 , "GsID");
		Label labelUserID = new Label(1, 0 , "UserID");
		Label labelshoppingNum = new Label(2, 0 , "shoppingNum");
		Label labelshoppingTime = new Label(3, 0 , "shoppingTime");
		//添加第一列到单元格
		sheet.addCell(labelGsID);
		sheet.addCell(labelUserID);
		sheet.addCell(labelshoppingNum);
		sheet.addCell(labelshoppingTime);
		//从数据库到Excel
		for(int i = 0; i < list.size(); i++) {
			//插入到的Excel表格的行号，默认从0开始
    		Label label_GsID = new Label(0, i+1 , list.get(i).getGsID());
    		Label label_UserID = new Label(1, i+1 , list.get(i).getUserID() );
    		Label label_shoppingNum = new Label(2, i+1 , list.get(i).getShoppingNum());
    		Label label_shoppingTime = new Label(3, i+1 , list.get(i).getShoppingTime());
		//添加到第一列单元格
    		sheet.addCell(label_GsID);
    		sheet.addCell(label_UserID);
    		sheet.addCell(label_shoppingNum);
    		sheet.addCell(label_shoppingTime);
		}
		book.write();//写
		book.close();//关闭
		System.out.println("ToExcel完成");
	}
	
	public static void main(String[] args){//订单
		try {
			toWriteExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
