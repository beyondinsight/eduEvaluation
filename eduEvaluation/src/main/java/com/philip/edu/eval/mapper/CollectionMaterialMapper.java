package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblCollectionMaterial;


public interface CollectionMaterialMapper {
  

	int deleteByPrimaryKey(Integer id);

	List<TblCollectionMaterial> getCollectionMaterial();

	List<TblCollectionMaterial> CollectionMaterial();

	int createMaterial(TblCollectionMaterial material);

	int updateMaterial(TblCollectionMaterial material);

	int deleteMaterial(int id);
}