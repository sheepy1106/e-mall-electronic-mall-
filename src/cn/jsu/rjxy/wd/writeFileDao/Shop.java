package cn.jsu.rjxy.wd.writeFileDao;
/**
 * Excel��shop��
 * @author a'su's
 *
 */
public class Shop {
private String GsID;
private String UserID;
private String shoppingNum;
private String shoppingTime;
	public String getGsID() {
	return GsID;
}
	/**
	 * 
	 * @param gsID
	 * @param userID
	 * @param shoppingNum
	 * @param shoppingTime
	 */
public Shop(String gsID, String userID, String shoppingNum, String shoppingTime) {
	super();
	GsID = gsID;
	UserID = userID;
	this.shoppingNum = shoppingNum;
	this.shoppingTime = shoppingTime;
}
public String getUserID() {
	return UserID;
}
/**
 * 
 * @return �������
 */
public String getShoppingNum() {
	return shoppingNum;
}
/**
 * 
 * @return  shoppingTime
 */
public String getShoppingTime() {
	return shoppingTime;
}
//	public static void main(String[] args) {
//		
//	}

}
