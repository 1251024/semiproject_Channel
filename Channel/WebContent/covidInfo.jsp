<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>


<%request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");%>

<c:catch var="err">
	<c:set var="covidURL"
		value="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?serviceKey=dMj3mB4JRQikFiWG9okvWpFVdKcxxGtkVrJOMFTC7gkX41kdn%2BW59RYtJAdY2qyOYnIDKMtFWPvpDz1bdBGKOQ%3D%3D"/>
	<c:import var="covid" url="${covidURL}" />
	<x:parse var="wrss" xml="${covid}" />
{"stateDt":"<x:out select="$wrss/response/body/items/item/stateDt" />",
"decideCnt":"<x:out
		select="$wrss/response/body/items/item/decideCnt" />",
"deathCnt":"<x:out select="$wrss/response/body/items/item/deathCnt" />",
"careCnt":"<x:out select="$wrss/response/body/items/item/careCnt" />",
"accExamCnt":"<x:out select="$wrss/response/body/items/item/accExamCnt" />",
"examCnt":"<x:out select="$wrss/response/body/items/item/examCnt" />",
"resultNegCnt":"<x:out select="$wrss/response/body/items/item/resutlNegCnt" />",
"accDefRate":"<x:out
		select="$wrss/response/body/items/item/accDefRate" />"}
</c:catch>
<c:if test="${err!=null}">
	${err}
</c:if>
