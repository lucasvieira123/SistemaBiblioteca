<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.FuncionarioDAO"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de funcionarios</title>
</head>
<body>
<table border="1px">
<p>Lista de funcionarios</p>
<tr>
<td>id</td>
<td>Nome</td>
</tr>
<c:forEach var="funcionario" items="${dao.lista}">
<tr>
<td>${funcionario.id}</td>
<td>${funcionario.nome}</td>
<td>Alterar</td>
<td>Remover</td>
</tr>
</c:forEach>

</table>

<!-- <a href="">Adicionar +</a> -->
</br>
</br>
	
	<a href="/SistemaBiblioteca/sistema?acao=VoltarMenu"> <<<<< voltar</a>
	
</body>
</html>