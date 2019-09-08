package com.philip.edu.eval.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

	public ArrayList   batchList(ArrayList sourceList, int page , int pagesize) {
        //List<List<T>> returnList  = new ArrayList<List<T>>();
        ArrayList majorList = new ArrayList();
        int startIndex = (page-1)*pagesize; // 从第0个下标开始
        int endIndex = 0;
       
        if (page*pagesize-1 < sourceList.size()) {
        	endIndex=page*pagesize ;
        }else {
        	endIndex=sourceList.size();
        }
        List aa = sourceList.subList(startIndex, endIndex);
        for(int c=0;c<aa.size();c++) {
        	majorList.add(aa.get(c));
        }
       // returnList.add();
        return majorList;
    }

}
/* int listSize = sourceList.size();
for (int i=0;i<page;i++) {
	List<T> subList = new ArrayList<T>();
	for (int j = 0; j < listSize; j++) {
		int pageIndex = ((j + 1) + (pagesize - 1)) / pagesize;
		if (pageIndex == (i+1)) {
			subList.add(sourceList.get(j));
		}
		if ((j+1) == (j+1)*pagesize) {
			break;
		}
	}
	listArray.add(subList);
}
return listArray;

}*/