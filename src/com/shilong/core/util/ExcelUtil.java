package com.shilong.core.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.shilong.nsfw.user.entity.User;

public class ExcelUtil {

	public static void exportUserExcel(List<User> userList, ServletOutputStream out) {

		try {
			//1、创建工作簿
			HSSFWorkbook workBook=new HSSFWorkbook();	
			//1.1、创建合并单元格对象
			CellRangeAddress cr=new CellRangeAddress(0,0,0,4);
			//1.2、头标题样式
			HSSFCellStyle style1=createCellStyle(workBook,(short)16);		
			//1.3、列标题样式
			HSSFCellStyle style2=createCellStyle(workBook,(short)13);
			//2、创建工作表
			HSSFSheet sheet=workBook.createSheet("用户列表");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cr);
			//设置默认列宽
			sheet.setDefaultColumnWidth(25);
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1=sheet.createRow(0);
			HSSFCell cell1=row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			
			//3.2、创建列标题行；并且设置列标题
			String[] titles ={"用户名","账号","所属部门","性别","电子邮箱"};
			HSSFRow row2=sheet.createRow(1);
			for(int i=0;i<titles.length;i++){
				
				HSSFCell cell2=row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			//
			//4、操作单元格；将用户列表写入excel
			if(!userList.isEmpty()){
				for(int j=0;j<userList.size();j++){
					HSSFRow r=sheet.createRow(j+2);
					HSSFCell c1=r.createCell(0);
					c1.setCellValue(userList.get(j).getName());
					HSSFCell c2=r.createCell(1);
					c2.setCellValue(userList.get(j).getAccount());
					HSSFCell c3=r.createCell(2);
					c3.setCellValue(userList.get(j).getDept());
					HSSFCell c4=r.createCell(3);
					c4.setCellValue(userList.get(j).isGender()?"男":"女");
					HSSFCell c5=r.createCell(4);
					c5.setCellValue(userList.get(j).getEmail());
				}
			}
			//
			//5、输出
			workBook.write(out);
			workBook.close();
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workBook, short s) {
		HSSFCellStyle style=workBook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.2.1创建字体
		HSSFFont font=workBook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints(s);
		//加载字体
		style.setFont(font);
		return style;
	}
}
