package com.biz.book.mapper;

import com.biz.book.model.BookVO;

/*
 * GenericDao를 extends 하면 기본 CRUD 메서드를 별도의 정의하지 않아도된다. 
 *  
 * 
 */
public interface BookDao extends GenericDao<BookVO, Integer>{

}
