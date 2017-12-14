<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.FuncionarioDAO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fmt:parseNumber var="id" scope = "session" type="number" value="${param.id}" />
<c:set var = "funcionario" scope = "session" value = "${dao.getFuncionario(id)}"/>
<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="AlterarFuncionario">
<input type="hidden" name="id" value="${funcionario.id}">
	Nome: <input type="text" name="nome" value="${funcionario.nome} " required> </br>
	<input type="submit" value="Salvar" >
	
</form>

</body>
</html>