package com.shilong.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.shilong.core.exception.ServiceException;
import com.shilong.core.service.impl.BaseserviceImpl;
import com.shilong.core.util.ExcelUtil;
import com.shilong.nsfw.role.entity.Role;
import com.shilong.nsfw.user.dao.UserDao;
import com.shilong.nsfw.user.entity.User;
import com.shilong.nsfw.user.entity.UserRole;
import com.shilong.nsfw.user.entity.UserRoleId;
import com.shilong.nsfw.user.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseserviceImpl<User> implements UserService {
	
	
	private UserDao userDao;
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	@Override
	public void delete(Serializable id) {
		userDao.deleteUserRoleByUserId(id);
		super.delete(id);
	}

	@Override
	public void exportExcel(List<User> userList, ServletOutputStream out) {

		ExcelUtil.exportUserExcel(userList, out);
	}

	

	@Override
	public void importExcel(File userExcel, String userExcelFileName) {
		try {
			FileInputStream fis=new FileInputStream(userExcel);
			boolean isExcel03=userExcelFileName.matches("^.+\\.(?i)((xls))$");
//		1、读取工作簿
			Workbook workBook=isExcel03?new HSSFWorkbook(fis):new XSSFWorkbook(fis);
//		2、读取工作表
			Sheet sheet=workBook.getSheetAt(0);
//		3、读取行
			if(sheet.getPhysicalNumberOfRows()>2){
				User u=null;
				//4、读取单元格
				for(int k=2;k<sheet.getPhysicalNumberOfRows();k++){
					u=new User();
					Row r=sheet.getRow(k);
					Cell c1=r.getCell(0);
					u.setName(c1.getStringCellValue());
					
					Cell c2=r.getCell(1);
					u.setAccount(c2.getStringCellValue());
					
					Cell c3=r.getCell(2);
					u.setDept(c3.getStringCellValue());
					
					Cell c4=r.getCell(3);
					u.setGender(c4.getStringCellValue().equals("男"));
					
					Cell c5=r.getCell(4);
					String mobile="";
					try {
						mobile=c5.getStringCellValue();
					} catch (Exception e) {
						double dmobile=c5.getNumericCellValue();
						mobile=BigDecimal.valueOf(dmobile).toString();
						//e.printStackTrace();
					}
					u.setMobile(mobile);
					
					Cell c6=r.getCell(5);
					u.setEmail(c6.getStringCellValue());
					
					Cell c7=r.getCell(6);
					if(c7.getDateCellValue()!=null){
						u.setBirthday(c7.getDateCellValue());
					}
					//默认属性
					u.setPassword("123456");
					u.setState(User.USER_STATE_VALID);
					//5、保存用户
					this.save(u);
				}
				
			}
			workBook.close();
			fis.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getUserByAccountAndId(String account, String id) {
	
		
		return userDao.getUserByAccountAndId(account, id);
	}

	@Override
	public void updateUserAndRole(User user, String... roleIds) {
		userDao.deleteUserRoleByUserId(user.getId());
		
		update(user);
		if(roleIds!=null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		this.save(user);
		if(roleIds!=null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
		
		
	}

	@Override
	public List<UserRole> getUserRolesByUserId(String id) {
		
		return userDao.findUserRolesByUserId(id);
	}

	@Override
	public List<User> findUserByAccountAndPassword(String account, String password) {
		
		return userDao.findUserByAccountAndPassword(account,password);
	}
	

}
