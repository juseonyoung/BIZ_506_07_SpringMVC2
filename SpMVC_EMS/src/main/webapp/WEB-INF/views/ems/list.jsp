<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />    
<script>
	document.addEventListener("DOMContentLoaded", function(){
		document.querySelector("#ems-write").addEventListener("click",
				function(){
			document.location.href="${rootPath}/ems/write"
		})
		
		
		
		document.querySelector("table").addEventListener("click", function(event){
			let tag_name = event.target.tagName;
			if(tag_name === "TD"){
				
				let seq = event.target.closest("TR").dataset.seq
				if(seq){
					document.location.href="${rootPath}/ems/detail/"+seq
				}
			}
		});
		
	})
	
	$(function(){
		
		$(".ems-tr").click(function(){
			let seq = $(this).data("seq")
			document.locationj.href="${rootPath}/ems/detail/" + seq;
		})
	})

</script>
<style>
</style>


<table class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th></th>
		</tr>
	</thead>
</table>























