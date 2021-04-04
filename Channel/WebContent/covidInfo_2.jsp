<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<%request.setCharacterEncoding("UTF-8");
response.setContentType("text/html; charset=UTF-8");%>
<c:catch var="err">
	<c:set var="covidURL"
		value="http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson?serviceKey=dMj3mB4JRQikFiWG9okvWpFVdKcxxGtkVrJOMFTC7gkX41kdn%2BW59RYtJAdY2qyOYnIDKMtFWPvpDz1bdBGKOQ%3D%3D"/>
	<c:import var="covid" url="${covidURL}" />
	<x:parse var="wrss" xml="${covid}" />
	<rss>
	<x:forEach select="$wrss/response/body/items/item" var="coco">
<gubun><x:out select="$coco/gubun"/></gubun><defCnt><x:out select="$coco/defCnt"/></defCnt><incDec><x:out select="$coco/incDec"/></incDec>
</x:forEach>
</rss>

</c:catch>
<c:if test="${err!=null}">
	${err}
</c:if>