<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<tr class="pro_item" data-pcode=${LIST.io_pname}>
	 <td>${i.count}</td>
          <td>${LIST.io_date}</td>
          <td>${LIST.io_time}</td>
          <td>${LIST.io_pname}</td>
          <td>${LIST.io_input}</td>
          <td>${LIST.io_price}</td>
          <td>${LIST.io_quan}</td>
          <td>${LIST.io_total}</td>

</tr>