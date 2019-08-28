package com.philip.edu.eval.mapper;

import java.util.List;

import com.philip.edu.eval.bean.TblCollectionMaterial;


public interface TblCollectionMaterialMapper {
  

    int deleteByPrimaryKey(Integer id);

    List<TblCollectionMaterial> getCollectionMaterial();
    List<TblCollectionMaterial> CollectionMaterial();
    public int createMaterial(TblCollectionMaterial material);
	public int updateMaterial(TblCollectionMaterial material);
	public int deleteMaterial(int id);
}