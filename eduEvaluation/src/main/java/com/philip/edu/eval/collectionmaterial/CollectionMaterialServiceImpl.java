package com.philip.edu.eval.collectionmaterial;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.philip.edu.eval.bean.TblCollectionMaterial;
import com.philip.edu.eval.mapper.CollectionMaterialMapper;

@org.springframework.stereotype.Service("collection_material_service")
public  class CollectionMaterialServiceImpl implements CollectionMaterialService {

	//必須要加注解
	@Autowired
	private CollectionMaterialMapper dao; 

	public List<TblCollectionMaterial> getCollectionMaterial() {
		// TODO Auto-generated method stub
		return dao.getCollectionMaterial();
	}


	public int createMaterial(TblCollectionMaterial material) {
		// TODO Auto-generated method stub
		return dao.createMaterial(material);
	}

	
	public int updateMaterial(TblCollectionMaterial material) {
		// TODO Auto-generated method stub
		return dao.updateMaterial(material);
	}


	public int deleteMaterial(int id) {
		// TODO Auto-generated method stub
		return dao.deleteMaterial(id);
	}



	public List<TblCollectionMaterial> CollectionMaterial() {
		// TODO Auto-generated method stub
		return dao.CollectionMaterial();
	}




	
}