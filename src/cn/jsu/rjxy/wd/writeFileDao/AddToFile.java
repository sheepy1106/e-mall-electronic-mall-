package cn.jsu.rjxy.wd.writeFileDao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JOptionPane;

import cn.jsu.rjxy.wd.controller.Register;

/**
 * ע���û���Ϣд���ļ�UserTable.txt
 * @author a'su's
 *
 */
public class AddToFile {
	   //ע���û���Ϣд���ļ�UserTable.txt
    public static void writeFile() throws IOException {
    	File f=new File("d:"+File.separator+"UserTable.txt");
		Writer out=null;
		out=new FileWriter(f,true);
		String str1=Register.UserID;
		String str2=Register.UserName;
		String str3=Register.PassWord;
		String str4=str1+"\t"+str2+"\t"+str3+"\n";
		out.write(str4);
		//JOptionPane.showMessageDialog(null, "�û���¼�ɹ�");
		System.out.println("�ѳɹ�д���ļ���");
		out.close();
    }
 }
//	public static void main(String[] args) {

//	} 
	

    
