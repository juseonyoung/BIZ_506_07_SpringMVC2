<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>BBS 2020</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${rootPath}/static/summernote/summernote-bs4.min.css">
<script src="${rootPath}/static/summernote/summernote-bs4.min.js"></script>
<script src="${rootPath}/static/summernote/lang/summernote-ko-KR.min.js"></script>
   <style>
      * {
        box-sizing: border-box;
        margin: 0;
        padding: 0;
      }
      body,
      html {
        width: 100%;
        height: 100%;
      }
      body {
        display: flex;
        flex-direction: column;
      }

      header {
       position: relative;
  height: 400px;
  color: white;

  background-image: url("${rootPath}/static/images/냥이.jpg");
  background-position: center;
  background-repeat: repeat;
  
  background-attachment: fixed;

  justify-content: center;
  align-items: center;
      }
      
      section#content{
      
      /*
      html, body의 height: 100%로 설정하고, 
      body를 flex-direction: column;,flex로 설정하고 
      header, nav, content, footer 를 각각 배열하고
      content가 있는 박스에만 flex값을 1로 설정하면 height 가 전체 가득 찬 레이아웃이 만들어진다
      overflow 는 내용이 넘치면 자동으로 스크롤바생성*/
      	flex:1;
      	overflow:auto;
      	
      }

      nav#main-nav ul {
        background: linear-gradient(to right, purple, orange);
        list-style: none;
        display: flex;
      }

      nav#main-nav li {
        padding: 8px 16px;
        color: white;
        margin: 0px 5px;
        border-bottom: 3px solid transparent;
        cursor:pointer;
        transition:border-color 0.3s linear;
      }

      nav#main-nav li:nth-child(3) {
        margin-left: auto;
      }

      nav#main-nav li:hover {
        border-bottom: 3px solid black;
        cursor: pointer;
      }

      footer {
        background: linear-gradient(to left, pink, black);
        color: white;
        text-align: center;
        padding: 0.7rem;
      }
    </style>
</head>
<body>
<%
/*
   tiles:insertAttribute
   다른 jsp파일을 부착하는 용도의 설정값
   여기에 name으로 설정된 부분에 tile.xml파일에서 지정한 jsp 파일이 부착된다

*/%>
   <tiles:insertAttribute name="header"/>
   <tiles:insertAttribute name="nav"/>
   <section id="content">
      <tiles:insertAttribute name="content"/>
   </section>
   
   <tiles:insertAttribute name="footer"/>
</body>
</html>