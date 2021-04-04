<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="covidAjax.js"></script>

<link rel="shortcut icon" href="#">
<style type="text/css">


table {
	width: 450px;
	height: 330px;
	position: absolute;
	top: 170px;
	left: 950px;
	margin: 10px;
	border-spacing: 3 5px;
}

#map_img {
	position: absolute;
	top: 100px;
	left: 230px;
	margin: 0px;
}

input{
	text-align: center;
	border: none;
	background-color: transparent;
	font-weight: bold;

}

#input2{
	font-size: 8pt;
	text-align: center;
}

#seoul{
	width: 55px;
	height: 50px;
	position: absolute;
	top: 220px;
	left:485px;
	text-align: center;
	font-weight: bold;
	margin: 0px;
	border: 1px solid;
	border-spacing: 0px;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	
}

#gangwon{
	width: 50px;
	height: 50px;
	position: absolute;
	top: 190px;
	left:580px;
	text-align: center;
	font-weight: bold;
	margin: 0px;
	border: 1px solid;
	border-spacing: 0px;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	
}

#gyeonggi{
	width: 50px;
	height: 50px;
	position: absolute;
	top: 160px;
	left:420px;
	text-align: center;
	font-weight: bold;
	margin: 0px;
	border: 1px solid;
	border-spacing: 0px;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	
}

#incheon{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 220px;
	left:360px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#sejong{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 300px;
	left:445px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#chungnam{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 330px;
	left:380px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#chungbuk{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 250px;
	left:540px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#daejeon{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 320px;
	left:515px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#jeonbuk{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 410px;
	left:455px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#jeonnam{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 490px;
	left:370px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#gwangju{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 480px;
	left:460px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#gyeongbuk{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 290px;
	left:640px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#gyeongnam{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 450px;
	left:560px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#daegu{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 365px;
	left:630px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#ulsan{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 445px;
	left:700px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#busan{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 500px;
	left:645px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}

#jeju{
	width: 55px;
	height: 55px;
	position: absolute;
	top: 640px;
	left:370px;
	text-align: center;
	font-weight: bold;
	margin: 2px;
	border: 1px solid;
	font-size: 8pt;
	background-color: #FFFFF0;
	cursor: pointer;
	padding: 2px 4px;
	box-shadow: 2px 2px 4px rgb(100 100 100 / 20%);
	border-radius: 2px;
	line-height: 3ex;
}


</style>
</head>
<body>

<%@ include file="common.jsp" %>

	<!-- 
		<a href="https://api.corona-19.kr/korea/?serviceKey=y95ANMI7Wqm8rhOXSoF3auBHVl2UKjndY">
			굿바이 코로나
		</a>
		<br/><br/>
		<a href="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=dMj3mB4JRQikFiWG9okvWpFVdKcxxGtkVrJOMFTC7gkX41kdn%2BW59RYtJAdY2qyOYnIDKMtFWPvpDz1bdBGKOQ%3D%3D">
			보건복지부
		</a>
		<br/><br/>
		<a href="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=dMj3mB4JRQikFiWG9okvWpFVdKcxxGtkVrJOMFTC7gkX41kdn%2BW59RYtJAdY2qyOYnIDKMtFWPvpDz1bdBGKOQ%3D%3D">
			시/도 발생현황
		</a>
		<br/><br/><br/>
	-->
	<!-- 
	
			<accDefRate>1.3660181291</accDefRate>
			 누적 확진률
			<accExamCnt>7355964</accExamCnt>
			 누적 검사수
			<accExamCompCnt>7252832</accExamCompCnt>
			 누적 검사 완료수
			<careCnt>6767</careCnt>
			 치료중 환자 수 
			<clearCnt>90611</clearCnt>
			 격리해제 수
			<createDt>2021-03-22 10:04:58.004</createDt>
			 등록일시분초
			<deathCnt>1697</deathCnt>
			 사망자 수
			<decideCnt>99075</decideCnt>
			 확진자 수
			<examCnt>103132</examCnt>
			 검사진행 수 
			<resutlNegCnt>7153757</resutlNegCnt>
			 결과 음성 수 
			<seq>456</seq>
			 게시글 번호수 (감염현환 고유값)
			<stateDt>20210322</stateDt>
			 기준일
			<stateTime>00:00</stateTime>
			 기준시간
			<updateDt>null</updateDt>
			 수정일시분초
			 
			 ---------------------------------------------------------------------
			 
			<createDt>등록일시분초 </createDt>
			<deathCnt>사망자 수</deathCnt>
			<defCnt>확진자 수</defCnt>
			<gubun>시도명(한글)</gubun>
			<gubunCn>시도명(중국어)隔離區</gubunCn>
			<gubunEn>시도명(영어)Lazaretto</gubunEn>
			<incDec>전일대비 증감수</incDec>
			<isolClearCnt>격리 해제 수</isolClearCnt>
			<isolIngCnt>격리중 확자수</isolIngCnt>
			<localOccCnt>지역발생 수</localOccCnt>
			<overFlowCnt>해외유입 수</overFlowCnt>
			<qurRate>10만명당 발생률</qurRate>
			<seq>게시글번호(국내 시도별 발생현황 고유값)</seq>
			<stdDay>기준일시</stdDay>
			<updateDt>수정일시분초</updateDt>

	 -->
	 	<img src="resources/image/covidmap.png" id="map_img" width="650" height="600">
	
		<table>
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>등록일자</b></td>
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="stateDt" style="text-align: right;" readonly>
			</tr>
			
		
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>국내 확진자</b></td>
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="decideCnt" style="text-align: right;" readonly>
			</tr>
						
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>국내 사망자</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="deathCnt" style="text-align: right;" readonly>
			</tr>
			
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>국내 치료중</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="careCnt" style="text-align: right;" readonly>
			</tr>
			
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>누적 검사수</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="accExamCnt" style="text-align: right;" readonly>
			</tr>
			
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>검사진행 수</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="examCnt"  style="text-align: right;" readonly>
			</tr>
			
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>결과 음성 수</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="resultNegCnt" style="text-align: right;" readonly>
			</tr>
			
			<tr>
				<td colspan="5" bgcolor="FDF5E6" align="left" style="padding-left: 20px;"><b>누적 확진률</b></td>
			
				<td colspan="6" bgcolor="FFFAFA" align="right" style="padding-right: 15px;">  <input type="text" id="accDefRate" style="text-align: right;" readonly>
			</tr>
			
		
			
		</table>
	
		<table id="seoul">
			<tr>
				<td>서울</td>
				<!-- <input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="gubun" readonly> -->
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt17" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec17" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="gangwon">
			<tr>
				<td>강원</td>
			</tr>
			
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt8" disabled="disabled"></td>
			</tr>
			
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec8" disabled="disabled"></td>
			</tr>
		</table>

		<table id="gyeonggi">
			<tr>
				<td>경기</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt9" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec9" disabled="disabled"></td>
			</tr>
		</table>
		
		
		<table id="incheon">
			<tr>
				<td>인천</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt14" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec14" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="sejong">
			<tr>
				<td>세종</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt10" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec10" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="chungnam">
			<tr>
				<td>충남</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt6" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec6" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="chungbuk">
			<tr>
				<td>충북</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt7" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec7" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="daejeon">
			<tr>
				<td>대전</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt12" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec12" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="jeonbuk">
			<tr>
				<td>전북</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt5" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec5" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="jeonnam">
			<tr>
				<td>전남</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt4" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec4" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="gwangju">
			<tr>
				<td>광주</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt13" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec13" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="gyeongbuk">
			<tr>
				<td>경북</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt3" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec3" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="gyeongnam">
			<tr>
				<td>경남</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt2" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec2" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="daegu">
			<tr>
				<td>대구</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt15" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec15" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="ulsan">
			<tr>
				<td>울산</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt11" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec11" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="busan">
			<tr>
				<td>부산</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt16" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec16" disabled="disabled"></td>
			</tr>
		</table>
		
		<table id="jeju">
			<tr>
				<td>제주</td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="defCnt1" disabled="disabled"></td>
			</tr>
			
			<tr>
				<td><input style="width: 38px; height: 13px; font-size: 8pt;" type="text" id="incDec1" disabled="disabled"></td>
			</tr>
		</table>
		
		
</body>
</html>



















