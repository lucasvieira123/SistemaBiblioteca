<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.AluguelLivroDAO"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de alugueis livro</title>
</head>
<body>
<table border="1px">
<p>Lista de alugueis livro</p>
<tr>
<td>Código</td>
<td>Código livro</td>
<td>Matricula aluno</td>
<td>Data inicial</td>
<td>Data final</td>
<td>Codigo funcionario</td>
</tr>
<c:forEach var="alguel" items="${dao.lista}">
<tr>
<td>${alguel.codigo}</td>
<td>${alguel.codigo_livro}</td>
<td>${alguel.matricula_aluno}</td>
<td><fmt:formatDate value="${alguel.data_inicial.time}" type="date" dateStyle="short"/></td>
<td><fmt:formatDate value="${alguel.data_final.time}" type="date" dateStyle="short"/></td>
<td>${alguel.codigo_funcionario}</td>
<td>Alterar</td>
<td>Remover</td>
</tr>
</c:forEach>

</table>

<a href="">Adicionar +</a>

	
	
</body>
</html>