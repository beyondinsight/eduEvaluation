package com.philip.edu.eval.collectionmaterial;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
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
import com.philip.edu.eval.bean.TblCollectionMaterial;
import com.philip.edu.eval.util.PropertiesUtil;

@Controller
@RequestMapping(value = "/collectionMaterial")
public class CollectionMaterialController {

	private Properties propConfig = PropertiesUtil.getProperty("config");

	private static final Logger logger = Logger.getLogger(CollectionMaterialController.class);

	@Autowired
	private CollectionMaterialService service;

	@RequestMapping(value = "/collectionMaterial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> collectionMaterial() {

		ArrayList usersList = (ArrayList) service.CollectionMaterial();
		logger.info("successfully get the roles list");

		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0);
		data.setData(usersList);
		data.setCount(usersList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/getCollectionMaterial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<BackendData> getCollectionMaterial(HttpServletRequest request) {

		String form_performance_id = request.getParameter("form_performance_id");
		
		ArrayList materialList = (ArrayList) service.getCollectionMaterial(Integer.parseInt(form_performance_id));
		logger.info("successfully get the materials list");

		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(0);
		data.setData(materialList);
		data.setCount(materialList.size());

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/addCollectionMaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity addCollectionmaterial(HttpServletRequest request) {

		TblCollectionMaterial material = new TblCollectionMaterial();
		String materialname = request.getParameter("materialname");
		String memo = request.getParameter("memo");
		String form_performance_id = request.getParameter("form_performance_id");

		material.setFormPerformanceId(new Integer(form_performance_id));
		material.setMaterialname(materialname);
		material.setDescription(memo);

		int result = service.createMaterial(material);
		 

		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(result);
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadCollectionmaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> uploadCollectionmaterial(HttpServletRequest request, MultipartFile file)
			throws IOException {
		// TODO Auto-generated method stub
		// 检查文件
		TblCollectionMaterial material = new TblCollectionMaterial();

		checkFile(file);
		String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
		material.setFile_name(fileName);
		
		String sname = fileName.substring(fileName.lastIndexOf("."));
		String fileFirstName = fileName.substring(0, fileName.lastIndexOf("."));

		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timeStamp=simpleDateFormat.format(new Date());
		fileName = fileFirstName + timeStamp + sname;
		
		String savePath = propConfig.getProperty("download_path") + fileName;

		file.transferTo(new File(savePath));
   
		Date uploadtime = new Date();
		// 大小
		long size = file.getSize();
		int sizes = (int) (size / 1024);

		String id = request.getParameter("upload_id");
		if (id != null && !id.equals("")) {
			material.setId(Integer.parseInt(id));
		}

		material.setUpdateTime(uploadtime);
		//material.setCreateTime(uploadtime);
		material.setDoc(fileName);
		material.setDocsize(sizes);

		int result = service.updateMaterial(material);
		BackendData data = new BackendData();
		if(result != 0) {
			data.setMsg("成功");
			data.setCode(0);
		}else {
			data.setMsg("失败");
			data.setCode(1);
		}
		
		return new ResponseEntity<BackendData>(data, HttpStatus.OK);

	} 

	@RequestMapping(value = "downloadMaterial", method = RequestMethod.GET, produces = "text/html;charset=UTF-8") // json中文乱码解决produces="text/html;charset=UTF-8"
	public void downloadMaterial(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String fileName = request.getParameter("doc");
		String filePath = propConfig.getProperty("download_path") + fileName;

		File f = new File(filePath);
		
		//如果文件为空，则不继续执行
		if(!f.exists()) {
			return;
		}
		
		fileName = URLEncoder.encode(fileName, "utf-8");
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		if (false) { // 在线打开方式
			URL u = new URL("file:///" + filePath);
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

	}

	private boolean exportExcel(List<String> list, String filePath) {
		// TODO Auto-generated method stub
		return false;
	}

	private long parseInt(long time) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {

			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");

	}

	@RequestMapping(value = "/deleteCollectionMaterial", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<BackendData> deleteCollectionMaterial(HttpServletRequest request) {

		String id = request.getParameter("id");

		int result = service.deleteMaterial(Integer.parseInt(id));

		BackendData data = new BackendData();
		data.setMsg("");
		data.setCode(result);

		return new ResponseEntity<BackendData>(data, HttpStatus.OK);
	}

}
