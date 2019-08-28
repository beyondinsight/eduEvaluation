package com.philip.edu.eval.collectionmaterial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.philip.edu.eval.bean.BackendData;
import com.philip.edu.eval.bean.TblCollectionMaterial;



@Controller
@RequestMapping(value = "/collectionmaterial")
public class CollectionMaterialController {
	
	private static final Logger logger = Logger.getLogger(CollectionMaterialController.class);
	
	@Autowired
	private CollectionMaterialService service;

	
	@RequestMapping(value = "/selectcollectionMaterial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> collectionmaterial(){
 
		ArrayList usersList = (ArrayList) service.CollectionMaterial();
		logger.info("successfully get the roles list");
		//System.out.println("11111"+usersList);
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());
		//BackendData data = new BackendData();
		System.out.println("assd"+usersList);
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getcollectionmaterial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getcollectionmaterial(){
 
		ArrayList usersList = (ArrayList) service.getCollectionMaterial();
		logger.info("successfully get the roles list");
		//System.out.println("11111"+usersList);
		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0); 
		data.setData(usersList);
		data.setCount(usersList.size());
		//BackendData data = new BackendData();
		System.out.println("22222"+usersList);
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}
	
	

	@RequestMapping(value="/addcollectionmaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addMaterial(HttpServletRequest request) {
		
		TblCollectionMaterial material = new TblCollectionMaterial();

	/*	String formPerformanceId = request.getParameter("formPerformanceId");
		String metricsId = request.getParameter("metricsId");
		String materialId = request.getParameter("materialId");
		String required = request.getParameter("required");
		String doc = request.getParameter("doc");
		
		String createTime = request.getParameter("createTime");
		String updateTime = request.getParameter("updateTime");*/
		
		String description = request.getParameter("description");
		
		material.setDescription(description);
		
		System.out.println("11111"+description);
		//System.out.println("11111"+id);
		
		/*material.setCreateTime(new Date());
		material.setUpdateTime(new Date());		
		Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
		
		
		material.setRequired(required);
		material.setDoc(doc);
		
		
		Matcher isNum2 = pattern.matcher(formPerformanceId);
		if (isNum2.matches()) {
			material.setFormPerformanceId(Integer.parseInt(formPerformanceId));
		}
		
	    Matcher isNum = pattern.matcher(metricsId);
		if (isNum.matches()) {
			material.setMetricsId(Integer.parseInt(metricsId));
		}
		
		Matcher isNum1 = pattern.matcher(materialId);
		if (isNum1.matches()) {
			material.setMaterialId(Integer.parseInt(materialId));
		}*/
		System.out.println("11111"+material);
		int result = service.createMaterial(material);
		
		
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 1);
			object.put("msg", "材料添加成功");
		}else{
			object.put("code", 99);
			object.put("msg", "材料添加失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/uploadcollectionmaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity uploadMaterial(HttpServletRequest request,MultipartFile file) throws IOException {
		 // TODO Auto-generated method stub
		 //检查文件
        checkFile(file);
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
                    int lastCellNum = row.getLastCellNum();//为空列获取
//                    int lastCellNum = row.getPhysicalNumberOfCells();//为空列不获取
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    String[] cells = new String[row.getLastCellNum()];
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
       
		return null;
		
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
//	                cellValue = String.valueOf(cell.getCellFormula());
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
	        System.out.println(cellValue);
	        return cellValue;
	    }   
	

	@RequestMapping(value="/deletecollectionmaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity deleteMaterial(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		
		int result = service.deleteMaterial(Integer.parseInt(id));
		JSONObject object = new JSONObject();
		if(result!=0){
			object.put("code", 0);
			object.put("msg", "用户删除成功");
		}else{
			object.put("code", 99);
			object.put("msg", "用户删除失败");
		}
		
		return new ResponseEntity(object, HttpStatus.OK);
	}
	
}
