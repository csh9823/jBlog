<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

<!-- 자바스크립트 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/include/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="get" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr> 
		      		<tr>
		      			<td id="tdMsg" colspan="2"><div id="false"></div></td>
						<td id="tdMsg" colspan="2"><div id="true"></div></td>
					</tr>
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/include/main-footer.jsp"></c:import>
	</div>

</body>


<script type="text/javascript">
	//중복체크를 클릭할때
	$("#btnIdCheck").on("click", function() {
		console.log("클릭");
		var id = $("#txtId").val();
		var userVo = {
			id : id
		}
		$.ajax({
			url : "${pageContext.request.contextPath}/user/Check",
			type : "post",
			data : userVo,
			//응답받을때
			success : function(result) {
				if (result == 'success') {//중복 없음
					var str = "";
					str = '사용할 수 있는 아이디 입니다.';
					$("#false").html(str);
				} else {//중복 있음
					var str = "";
					str = ' 중복된 아이디 입니다..';
					$("#true").html(str);
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
</script>


</html>