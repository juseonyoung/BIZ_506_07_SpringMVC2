package com.biz.iolist.mapper;

import com.biz.iolist.model.ProductVO;

/*
 * GenericDao를 extends 하면 기본 CRUD 메서드를 별도의 정의하지 않아도된다. 
 *  
 * 
 */
public interface ProductDao extends GenericDao<ProductVO, Long>{

}
