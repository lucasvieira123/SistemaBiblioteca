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
<div class="alert alert-danger">
	<!-- Fiz com que a mensagem aparecesse na tela de listar livros -->
	<font color="red" size = "05"><c:out value="${mensagem}"/></font>
</div>

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
<c:forEach var="aluguel" items="${dao.lista}">
<tr>
<td>${aluguel.codigo}</td>
<td>${aluguel.codigo_livro}</td>
<td>${aluguel.matricula_aluno}</td>
<td><fmt:formatDate value="${aluguel.data_inicial.time}" type="date" dateStyle="short"/></td>
<td><fmt:formatDate value="${aluguel.data_final.time}" type="date" dateStyle="short"/></td>
<td>${aluguel.codigo_funcionario}</td>


<td>
	<form action="/SistemaBiblioteca/alugueis/atualizarAluguel.jsp" method="get">
<input type="hidden" name="codigo" value="${aluguel.codigo}"/>
<input type="submit" value="Alterar*"></input>
</form>
</td>

<td>
	<form action="/SistemaBiblioteca/sistema" method="get">
	<input type="hidden" name="acao" value="removerAluguel"/>
	<input type="hidden" name="codigo" value="${aluguel.codigo}"/>
	<input type="submit" value="remover -"></input>
	</form>


</tr>
</c:forEach>

</table>

<a href="/SistemaBiblioteca/alugueis/adicionarAlugueisLivro.html">Adicionar +</a> </br></br>

<a href="/SistemaBiblioteca/sistema?acao=VoltarMenu"> <<<<< voltar</a>
	
</body>
</html>