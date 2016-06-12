			<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head> 
	<title>Chatbot Eustachy</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta name="description" content="Chatbot rozmawiający o problemach interpersonalnych i doświadczeniach życiowych" />
	<meta name="keywords" content="chatbot" />
	<link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href='http://fonts.googleapis.com/css?family=Wire+One' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="<c:url value="/assets/css/styles.css"/>">
	<script>
	function scrollBox()
	{
		var objDiv = document.getElementById("textarea");
		objDiv.scrollTop = objDiv.scrollHeight;

	}
	function keepFocus() {
		document.getElementById("answerArea").focus();
	};
	
	</script>
	</head>
	
	<body onload="scrollBox();
					keepFocus();">
	<div class="menu">
		<%--<form:form method="GET" action="/raport" target="_blank">
			<input class="raport" type="submit" name="raport" value="" title="Raport"/>
		</form:form>
--%>
		<form:form method="GET" action="/reload" >
				<input class="reload" type="submit" name="reload" value="" title="Nowa rozmowa"/>
			</form:form>
		</div>

	<br>
	<header>
		<title>Chatbot Eustachy</title>
	</header>
		<section id="main">
			
			<div class="container">
				<div id="textarea" >
					<c:set var="count" value="0" scope="page" />
					<c:forEach items="${answers}" var="answer">  
						<c:choose>
							<c:when test="${(count mod 2) == 0}"><p class="bot"></c:when>
						   	<c:otherwise><p class="user"></c:otherwise>
					  	</c:choose>
						<c:out value="${answer}"/></p>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>

				</div>
		
					<form:form class="submit" modelAttribute="Answer" method="POST" action="/" >
						<form:input class="form-control" path="sentence" id="answerArea"/>
						<input class="btn" type="submit" value="Submit"/>
					</form:form>
			</div>

			<div class="footer">

			</div>
		</section>


	</body>
</html>