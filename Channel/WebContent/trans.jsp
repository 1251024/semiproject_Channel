<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta charset="UTF-8">
<link rel="icon" type="image/png" sizes="16x16"  href="resources/image/channel_favicon.png">
<title>CHANNEL / Translation / ${loginDto.member_name }</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<style type="text/css">

#send_text, #result_text{
	font-size: 15pt;
	font-family:sans-serif;
	padding: 13px 13px 13px 13px;
}


</style>
</head>
<body>

<%@ include file="common.jsp" %>

<div style="width: 1200px; margin: 0px auto; margin-top: 40px; text-align: center">
		<div style="width: 500px; height: 400px; float: left">
		<!--  
			<div style="padding-bottom: 10px">
				<select style="float: right" name="select01">
					<option value="html">한국어</option>
					<option value="css">영어</option>
				</select>
			</div>
		-->	
			<div>
				<textarea id="send_text" style="width: 100%; height: 370px" placeholder="번역할 내용을 입력하세요."></textarea>
			</div>
		</div>
		<div style="width: 200px; height: 400px; float: left; padding-top: 170px">
			<button id="trans" type="button" style="font-size: 18px; font-family:fantasy; margin-left: 25px; padding: 3px 8px 3px 8px; font-style: inherit; font-weight: bold; 			box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
			" >
				번역하기
			</button>
		</div>
		<div style="width: 500px; height: 400px; float: right">	
		<!-- 
			<div style="padding-bottom: 10px">
				<select style="float: right" name="select02">
					<option value="html">영어</option>
					<option value="css">한국어</option>
				</select>
			</div>
		-->	
			<div>
				<textarea id="result_text" style="width: 100%; height: 370px" readonly="readonly"></textarea>
			</div>
		</div>
	</div>
	

<script>

	//번역을 위해서 button 이벤트를 사용
	$('#trans').click(function() {
		//번역할 object를 생성
		var test = {
			"original_str" : $("#send_text").val()
		};
		jsonSend(test);
	});
	function jsonSend(test) {
		$.ajax({
			type : "POST",
			url : "transController",
			data : test, //json을 보내는 방법
			success : function(data) { //서블렛을 통한 결과 값을 받을 수 있음
				console.log(data);
				//alert(data);

				//string의 값을 object 형식으로 변환
				var result_obj = JSON.parse(data);
				//결과값을 textarea에 넣기 위해서
				$("#result_text")
						.val(result_obj.message.result.translatedText);
			},
			error : function(e) {
				console.log(e);
				alert('실패했습니다.');
			}
		});
	}
	
	
	//번역을 위해서 button 이벤트를 사용
	$('#trans').click(function() {
		//번역할 object를 생성
		var test2 = {
			"original_str2" : $("#send_text").val()
		};
		jsonSend(test2);
	});
	/*
	function jsonSend(test2) {
		$.ajax({
			type : "POST",
			url : "transController",
			data : test2, //json을 보내는 방법
			success : function(data) { //서블렛을 통한 결과 값을 받을 수 있음
				console.log(data);
				//alert(data);

				//string의 값을 object 형식으로 변환
				var result_obj2 = JSON.parse(data);
			
				//결과값을 textarea에 넣기 위해서
				$("#result_text")
						.val(result_obj2.message.result.translatedText);
			},
			error : function(e) {
				console.log(e);
				alert('실패했습니다.');
			}
		});
	}
	*/
	
</script>


</body>
</html>