package com.shilong.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {
	@Test
	public void testPOI() throws Exception {
		
	}
	//以下是读取03版xls，若想读取07以后的，HSSF换成XSSF就可了
	@Test
	public void testWrite03Excel() throws Exception {
		//创建工作部
		HSSFWorkbook workBook=new HSSFWorkbook();
		//创建工作表
		HSSFSheet sheet=workBook.createSheet("helloWorld");
		//创建行(第三行)
		HSSFRow row=sheet.createRow(2);
		//创建单元格(第三列)
		HSSFCell cell=row.createCell(2);
		
		cell.setCellValue("helloworld");
		
		//输出到硬盘
		FileOutputStream fos=new FileOutputStream("F:\\a\\a.xls");
		
		workBook.write(fos);
		
		workBook.close();
		fos.close();
	}
	@Test
	public void testRead03Excel() throws Exception {
		//读取到内存
		FileInputStream fis=new FileInputStream("F:\\a\\a.xls");
		//读取工作部
		HSSFWorkbook workBook=new HSSFWorkbook(fis);
		//读取工作表
		//HSSFSheet sheet=workBook.getSheet("helloworld");
		HSSFSheet sheet=workBook.getSheetAt(0);
		//读取行(第三行)
		HSSFRow row=sheet.getRow(2);
		//读取单元格(第三列)
		HSSFCell cell=row.getCell(2);
		
		System.out.println(cell.getStringCellValue());
		
		
		
		workBook.close();
		fis.close();
	}
	@Test
	public void testWrite07Excel() throws Exception {
		//创建工作部
		XSSFWorkbook workBook=new XSSFWorkbook();
		//创建工作表
		XSSFSheet sheet=workBook.createSheet("helloWorld");
		//创建行(第三行)
		XSSFRow row=sheet.createRow(2);
		//创建单元格(第三列)
		XSSFCell cell=row.createCell(2);
		
		cell.setCellValue("helloworld");
		
		//输出到硬盘
		FileOutputStream fos=new FileOutputStream("F:\\a\\a.xlsx");
		
		workBook.write(fos);
		
		workBook.close();
		fos.close();
	}

	@Test
	public void testRead07Or03Excel() throws Exception {
		String f="F:\\a\\a.xlsx";
		if(f.matches("^.+\\.(?i)((xls)|(xlsx))$")){
			//System.out.print("++++++++++++++++++++++");
			//Boolean is03=true;
			Boolean is03=f.matches("^.+\\.(?i)(xls)$");
			//读取到内存
			FileInputStream fis=new FileInputStream(f);
//			if(f.substring(f.lastIndexOf(".")).equals(".xlsx")){
//				is03=false;
//			}
			//读取工作部
			Workbook workBook=is03?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
			//读取工作表
			//XSSFSheet sheet=workBook.getSheet("helloworld");
			Sheet sheet=workBook.getSheetAt(0);
			//读取行(第三行)
			Row row=sheet.getRow(2);
			//读取单元格(第三列)
			Cell cell=row.getCell(2);
			
			System.out.println(cell.getStringCellValue());
			
			
			
			workBook.close();
			fis.close();
		}
	}
	
	@Test
	public void testStyle() throws Exception {
		//创建工作部
		HSSFWorkbook workBook=new HSSFWorkbook();
			//创建合并单元格对象，合并第三行的第三列到第五列
		CellRangeAddress cr=new CellRangeAddress(2,2,2,4);
			//创建单元格样式
		HSSFCellStyle hcs=workBook.createCellStyle();
			//创建字体
		HSSFFont hsf=workBook.createFont();
		hsf.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		hsf.setFontHeightInPoints((short)16);//设置字号
		hcs.setFont(hsf);//设置字体
		hcs.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		hcs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//设置单元格北京
		//设置填充模式
		//hcs.setFillPattern(HSSFCellStyle.DIAMONDS);//钻石模型
		hcs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//以前景色为准
		//设置填充背景色
		hcs.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//设置填充前景色
		hcs.setFillForegroundColor(HSSFColor.RED.index);
		//创建工作表
		HSSFSheet sheet=workBook.createSheet("helloWorld");
			//加载单元格范围地址
		sheet.addMergedRegion(cr);
		//创建行(第三行)
		HSSFRow row=sheet.createRow(2);
		//创建单元格(第三列)
		HSSFCell cell=row.createCell(2);
			
		cell.setCellValue("helloworld");
		
		//加载单元格样式
		
		cell.setCellStyle(hcs);
		
		//输出到硬盘
		FileOutputStream fos=new FileOutputStream("F:\\a\\a.xls");
		
		workBook.write(fos);
		
		workBook.close();
		fos.close();
	}
}
