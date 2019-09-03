package com.philip.edu.eval.users;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblUsers;

import com.philip.edu.eval.util.Code;
import com.philip.edu.eval.util.EvalConstants;
//import com.philip.edu.eval.util.PasswordUtil;
import com.philip.edu.eval.role.RolesService;
import com.philip.edu.eval.util.Code;
import com.philip.edu.eval.util.PropertiesUtil;
import com.philip.edu.eval.util.SecurityUtil;
import com.philip.edu.eval.util.UsersFieldConstants;

import net.sf.json.JSONArray;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

@Controller
@RequestMapping(value = "/user")
public class UsersController {
	
	private static final Logger logger = Logger.getLogger(UsersController.class);
	private Properties propConfig = PropertiesUtil.getProperty("config");
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private UsersService service;
	@Autowired
	private RolesService role_service;
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> users(){
		
		ArrayList usersList = (ArrayList) service.getUsersList();
		logger.info("successfully get the users list");
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addUsers(HttpServletRequest request) {
		
		int code=0;
		String msg="";
		TblUsers users = new TblUsers();
		String chineseName = request.getParameter("chineseName");
		String creator = request.getParameter("creator");
		String email = request.getParameter("email");
		String fixPhone = request.getParameter("fixPhone");
		//String institution = request.getParameter("institution");
		
		//String major = request.getParameter("major");
		String memo = request.getParameter("memo"); 
		String mobilePhone = request.getParameter("mobilePhone");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		String qq = request.getParameter("qq");
		//String salt = request.getParameter("salt");
		String status = request.getParameter("status");
		String userName = request.getParameter("userName").toLowerCase();
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
	
		int sieq = service.getUsers(userName).size();
		if(sieq>0) {
			 code=2;
			 msg="用户名已存在";
			return   new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
		}
		
		if(!checkName(userName)) {
			code =3;			 
			msg="非法用户名："+userName;
			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
		}
		
		if(!checkPwd(password)) {
			code =4;
			msg="非法密码："+password;
			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
		}
		
		users.setChineseName(chineseName);
		users.setCreateTime(new Date());
		 
		if (creator!= null && !creator.equals("")) {
			users.setCreator(Integer.parseInt(creator));
		}
		 

		users.setEmail(email);
		users.setFixPhone(fixPhone);
		//users.setInstitution(institution);
		//users.setMajor(major);
		users.setMemo(memo);
		users.setMobilePhone(mobilePhone);
		users.setPosition(position);
		users.setQq(qq);
		users.setSalt(SecurityUtil.createSalt().toString());
		users.setStatus(status);
		users.setUpdateTime(new Date());
		users.setUserName(userName);
		password = SecurityUtil.md5Hex(userName + password + users.getSalt());
		users.setPassword(password); 
		
		if( roleId !=null && !roleId.equals("") ) {
			users.setRoleId(Integer.parseInt(roleId));
		}else {
			users.setRoleId(null);
		}
		
		if( schoolId !=null && !schoolId.equals("") ) {
			users.setSchoolId(Integer.parseInt(schoolId));
		}else {
			users.setSchoolId(null);
		}
		
		int result = service.createUsers(users);
		service.createUserRole(users);
		service.createUserSchool(users);
		if(result!=0){
			code =0;
			msg="用户添加成功";
		}else{
			code =99;
			msg="用户添加失败";
		}
		
		return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/updateUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity updateUsers(HttpServletRequest request) {
		
		int code=0;
		String msg="";
		
		TblUsers users = new TblUsers();
		
		String id = request.getParameter("id");
		String chineseName = request.getParameter("chineseName");
		String creator = request.getParameter("creator");
		String email = request.getParameter("email");
		String fixPhone = request.getParameter("fixPhone");
		String institution = request.getParameter("institution");
		//String major = request.getParameter("major");		
		String memo = request.getParameter("memo");
		String mobilePhone = request.getParameter("mobilePhone");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		String qq = request.getParameter("qq");
		//String salt = request.getParameter("salt");
		String status = request.getParameter("status");
		String userName = request.getParameter("userName");
		String roleId = request.getParameter("roleId");
		String schoolId = request.getParameter("schoolId");
		
	
		if (id != null && !id.equals("")) {
			users.setId(Integer.parseInt(id));
		}
		users.setChineseName(chineseName);
		users.setCreateTime(new Date());
		

		if (creator != null && !creator.equals("")) {
			users.setCreator(Integer.parseInt(creator));
		}
		
		if(!checkPwd(password)) {
			code =4;
			msg="非法密码："+password;
			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
		}
		
		users.setEmail(email);
		users.setFixPhone(fixPhone);
		users.setInstitution(institution);
		//users.setMajor(major);
		users.setMemo(memo);
		users.setMobilePhone(mobilePhone);
		
		users.setPosition(position);
		users.setQq(qq);
		//users.setSalt(salt);
		users.setStatus(status);
		users.setUpdateTime(new Date());
		users.setUserName(userName);
		if(!roleId.equals("") && roleId!=null) {
			users.setRoleId(Integer.parseInt(roleId));
		}else {
			users.setRoleId(null);
		}
		
		if(!schoolId.equals("") && schoolId!=null) {
			users.setSchoolId(Integer.parseInt(schoolId));
		}else {
			users.setSchoolId(null);
		}
			
		if(!password.equals("") && password != null) {
			ArrayList tempUsers = (ArrayList)service.getUsers(userName);
			TblUsers tempUser = (TblUsers)tempUsers.get(0);
			
			password = SecurityUtil.md5Hex(userName + password + tempUser.getSalt());
			users.setPassword(password);
		} 
		
	 
		int result = service.updateUsers(users);

		int result_ur =  service.updateUserRole(users);
		
		int result_us = service.updateUserSchool(users);
		
		if(result_ur==0) {
			service.createUserRole(users);		
		}
		
		if(result_us==0) {
			service.createUserSchool(users);
		}
		
		if(result!=0){
			code= 0;
			msg="用户修改成功";
		}else{
			code= 99;
			msg="用户修改失败";
		}
		
		return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/deleteUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteUsers(HttpServletRequest request) {

		int code=0;
		String msg="";
		
		String id = request.getParameter("id");
		
		int result = service.deleteUsers(Integer.parseInt(id));

		if(result!=0){
			code= 0;
			msg="用户删除成功";
		}else{
			code= 99;
			msg= "用户删除失败";
		}
		
		return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
	}
	
	@RequestMapping(value="/deleteUserss", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteUserss(HttpServletRequest request) {
		
		int code=0;
		String msg="";
		
		String[] sIds = request.getParameterValues("id");
		int[] ids = new int[sIds.length];
		for(int i=0; i<sIds.length; i++){
			ids[i] = Integer.parseInt(sIds[i]);
		}
		
		int result = service.batchDeleteUsers(ids);

		if(result!=0){
			code =0;
			msg= "用户删除成功";
		}else{
			code= 99;
			msg= "用户删除失败";
		}
		
		return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/uploadUsers", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity uploadUsers(HttpServletRequest request,MultipartFile file)  {
		 // TODO Auto-generated method stub
		int code=0;
		String msg="";
		
		JSONObject object = new JSONObject();
		List<String> name_list = new ArrayList<String>();
		 //检查文件
        try {
			checkFile(file);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			code= 99;
			msg= "失败";
			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
		}
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){ //为了过滤到第一行因为我的第一行是数据库的列
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = UsersFieldConstants.COUNTS;//row.getLastCellNum();//为空列获取
                    //int lastCellNum = row.getPhysicalNumberOfCells();//为空列不获取
                    //String[] cells = new String[row.getPhysicalNumberOfCells()];
                    String[] cells = new String[lastCellNum];//new String[row.getLastCellNum()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                    	
                        Cell cell = row.getCell(cellNum);
                        String  cellValue= getCellValue(cell).toLowerCase();
                        cells[cellNum] = cellValue;
                    	if(cellNum == firstCellNum) {
                    		if(name_list.contains(cellValue)) {
                    		 
                    			code= 2;
                    			msg="文件存在重复用户名："+cellValue;
                    			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
                    			
                    		}else {
                    			if(!checkName(cellValue)) {
                    				code= 3;
                        			msg="文件存在非法用户名："+cellValue;

                        			return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);
                    			}
                    			name_list.add(cellValue);
                    		}
                    	}
                    }
                    list.add(cells);
                }
            }
        }
      
        int  type = addUsersList(list,name_list);
       
        if(type==2) {
        	code = 2;
			msg= "文件存在重复用户名";
			
        }else if(type==1) {
        	code =1;
        	msg= "上传成功";
      		 
        }else {
        	code= 0;
        	msg= "上传失败";
      		 
        }

        return new ResponseEntity<BackendData>(mes(code,msg), HttpStatus.OK);		
	}
	
	/**
	 * 用户名验证
	 *  @param name
	 *  @return
	 */
	public static boolean checkName(String name) {
		String regExp = "(^_([a-zA-Z0-9]_?)*$)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?$)";
		if(name.matches(regExp)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 密码验证
	 *  @param pwd
	 *  @return
	 */
	public static boolean checkPwd(String pwd) {
	 
		if(!pwd.contains(" ") && !pwd.contains("	")) {
			return true;
		}
		return false;
	}

		
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> login(HttpServletRequest request){
		
		BackendData data = new BackendData();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//user exsits:
		boolean exsits = service.exsitsUser(username);
		if(!exsits){
			data.setMsg("用户不存在！");
			data.setCode(EvalConstants.LOGIN_STATUS_USER_NO_EXSITS);
			
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		
		//password correct:
		boolean passwordRight = service.checkPassword(username, password);
		if(!passwordRight){
			data.setMsg("您的密码不正确！");
			data.setCode(EvalConstants.LOGIN_STATUS_PASSWORD_NOT_RIGHT);
			
			return new ResponseEntity<BackendData>(data, HttpStatus.OK);
		}
		
		logger.info("successfully login");
		ArrayList returnInfo = new ArrayList();
		
		List<TblUsers> users = service.getUsers(username);
		returnInfo.add(users.get(0)); 
		//TblUsers user = users.get(0);
		
		try {
			String token = SecurityUtil.createToken(username);
			returnInfo.add(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("生成token的时候发生错误!");
		}

		data.setMsg("登录成功!"); 
		data.setCode(EvalConstants.LOGIN_STATUS_SUCCESS); 
		data.setData(returnInfo);
		//data.setCount(usersList.size());
		//BackendData data = new BackendData();
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	

	private int addUsersList( List<String[]> list,List<String> name_list) {
			
		
		List<TblUsers> all_list = service.getUsersList();
		List<TblRoles>  roles_list =   role_service.getRolenameRoles();
		List<Integer> role_id = new ArrayList<Integer>();
		List<String> role_name = new ArrayList<String>();
		for(TblRoles trole : roles_list) {
			role_id.add(trole.getId());
			role_name.add(trole.getRoleName());
		}
		
		for(TblUsers user : all_list) {
			
			if(name_list.contains(user.getUserName())) {
				return   2;
			}
		}
		
		List usersList = new ArrayList<TblUsers>();
			for(String [] users : list) {	 
				TblUsers u = new TblUsers();
				u.setUserName(users[0]);
			    u.setChineseName(users[1]);
			    if(users[2]!= null && !users[2].equals("") && role_name.contains(users[2])) {
			    	u.setRoleId(role_id.get(role_name.indexOf(users[2])));
			    }
				u.setRoleName(users[2]);
				u.setPosition(users[3]);
				u.setInstitution(users[4]);
				u.setMobilePhone(users[5]);
				u.setEmail(users[6]);
				u.setMemo(users[7]);
				u.setPassword(Code.encrypt(propConfig.getProperty("user_password"), propConfig.getProperty("code_key"), propConfig.getProperty("code_ivs")));
				service.createUsers(u);
				service.createUserRole(u);
				//usersList.add(u);
			}
			
			//int a = service.createUsersList(usersList);
			return 1;	
	}
	
	public static void checkFile(MultipartFile file) throws IOException{
	        //判断文件是否存在
	        if(null == file){
	 
	            throw new FileNotFoundException("文件不存在！");
	        }
	        //获得文件名
	        String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
	        //判断文件是否是excel文件
	        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
	            throw new IOException(fileName + "不是excel文件");
	        }
	    }
	 
    public static Workbook getWorkBook(MultipartFile file) {
	        //获得文件名
	        String fileName = file.getOriginalFilename();
	        //创建Workbook工作薄对象，表示整个excel
	        Workbook workbook = null;
	        try {
	            //获取excel文件的io流
	            InputStream is = file.getInputStream();
	            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
	            if(fileName.endsWith("xls")){
	                //2003
	                workbook = new HSSFWorkbook(is);
	            }else if(fileName.endsWith("xlsx")){
	                //2007
	                workbook = new XSSFWorkbook(is);
	            }
	        } catch (IOException e) {
	            logger.info(e.getMessage());
	        }
	        return workbook;
	    }
	    
	    public static String getCellValue(Cell cell){
	        String cellValue = "";
	        if(cell == null){
	            return cellValue;
	        }
	        //把数字当成String来读，避免出现1读成1.0的情况
	        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
	            cell.setCellType(Cell.CELL_TYPE_STRING);
	        }
	        //判断数据的类型
	        switch (cell.getCellType()){
	            case Cell.CELL_TYPE_NUMERIC: //数字
	                cellValue = String.valueOf(cell.getNumericCellValue());
	                break;
	            case Cell.CELL_TYPE_STRING: //字符串
	                cellValue = String.valueOf(cell.getStringCellValue());
	                break;
	            case Cell.CELL_TYPE_BOOLEAN: //Boolean
	                cellValue = String.valueOf(cell.getBooleanCellValue());
	                break;
	            case Cell.CELL_TYPE_FORMULA: //公式
	            	//cellValue = String.valueOf(cell.getCellFormula());
	                cellValue = String.valueOf(cell.getStringCellValue());
	                break;
	            case Cell.CELL_TYPE_BLANK: //空值
	                cellValue = "";
	                break;
	            case Cell.CELL_TYPE_ERROR: //故障
	                cellValue = "非法字符";
	                break;
	            default:
	                cellValue = "未知类型";
	                break;
	        }
	        
	        return cellValue;
	    }

	    
	
		@SuppressWarnings("resource")
	    @ResponseBody
	    @RequestMapping(value="downloadUsers",method = RequestMethod.GET,produces="text/html;charset=UTF-8")//json中文乱码解决produces="text/html;charset=UTF-8"
		public void downloadUsers(HttpServletResponse response) throws Exception  {
			List<String> list = new ArrayList<String>();
			list.add(UsersFieldConstants.USERNAME);
			list.add(UsersFieldConstants.FULLNAME);
			list.add(UsersFieldConstants.ROLENAME);
			list.add(UsersFieldConstants.POSITION);
			list.add(UsersFieldConstants.INSTITUTION);
			list.add(UsersFieldConstants.MOBILEPHONE);
			list.add(UsersFieldConstants.EMAIL);
			list.add(UsersFieldConstants.MEMO);
			String file_path = propConfig.getProperty("download_users_path")+"用户模板"+formatter.format(new Date())+".xlsx";
			boolean isdown =  exportExcel(list, file_path);
			
		 
		   
		   File f = new File(file_path);
	        if (!f.exists() || !isdown) {
	            response.sendError(404, "File not found!");	  
	            return;
	        }
	        String fileName = f.getName();
	        fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

	        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
	        byte[] buf = new byte[1024];
	        int len = 0;
	        response.reset(); // 非常重要
	        if (false) { // 在线打开方式
	            URL u = new URL("file:///" + file_path);
	            response.setContentType(u.openConnection().getContentType());
	            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
	            // 文件名应该编码成UTF-8
	        } else { // 纯下载方式
	            response.setContentType("application/x-msdownload");
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	        }
	        OutputStream out = response.getOutputStream();
	        while ((len = br.read(buf)) > 0)
	            out.write(buf, 0, len);
	        br.close();
	        out.close();
	        f.delete();
		}
		 
 
 
	    
	    public boolean exportExcel( List<String> list, String filePath) {
			try {
				Method method;
				Workbook workbook = new XSSFWorkbook();
				Sheet sheet = workbook.createSheet("Sheet1");
				Row row0 = sheet.createRow(0);
				for (int i = 0; i < list.size(); i++) {
					Cell cell = row0.createCell(i, Cell.CELL_TYPE_STRING);
					CellStyle style = getStyle(workbook);
					cell.setCellStyle(style);
					cell.setCellValue(list.get(i));
					sheet.autoSizeColumn(i);
				}
				 
				FileOutputStream outputStream = new FileOutputStream(filePath);
				workbook.write(outputStream);
				outputStream.flush();
				outputStream.close();
				return	true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return	false;
			}
		 
		}
		
		private static CellStyle getStyle(Workbook workbook) {
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

			Font headerFont = workbook.createFont(); 
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(HSSFColor.RED.index);
			headerFont.setFontName("宋体");
			style.setFont(headerFont);
			style.setWrapText(true);


			style.setBorderBottom((short) 1);
			style.setBorderLeft((short) 1);
			style.setBorderRight((short) 1);
			style.setBorderTop((short) 1);
			style.setWrapText(true);
			return style;
		}   
	
		
		private BackendData mes(int code,String mes) {
			BackendData data = new BackendData();
			data.setMsg(mes);
			data.setCode(code);
			return data; 
		}
}
