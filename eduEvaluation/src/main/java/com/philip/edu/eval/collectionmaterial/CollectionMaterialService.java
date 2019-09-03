package com.philip.edu.eval.collectionmaterial;

import java.util.List;

import com.philip.edu.eval.bean.TblCollectionMaterial;


public interface CollectionMaterialService {
	
	List<TblCollectionMaterial> getCollectionMaterial(int form_performance_id);

	List<TblCollectionMaterial> CollectionMaterial();

	int createMaterial(TblCollectionMaterial material);

	int updateMaterial(TblCollectionMaterial material);

	int deleteMaterial(int id);
 
	
}
