package com.philip.edu.eval.collectionmaterial;


import java.util.ArrayList;
import java.util.List;

import com.philip.edu.eval.bean.TblCollectionMaterial;
import com.philip.edu.eval.bean.TblRoles;
import com.philip.edu.eval.bean.TblSchoolMajor;
import com.philip.edu.eval.bean.TblUsers;



public interface CollectionMaterialService {
	
 public List<TblCollectionMaterial> getCollectionMaterial();
 
 public List<TblCollectionMaterial> CollectionMaterial();
 
 public int createMaterial(TblCollectionMaterial material);
	public int updateMaterial(TblCollectionMaterial material);
	public int deleteMaterial(int id);
	
 
 







	
}
