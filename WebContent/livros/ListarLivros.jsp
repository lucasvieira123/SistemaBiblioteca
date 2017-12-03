<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.LivroDAO"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista livros</title>
</head>
<body>
<table border="1px">
<p>Lista livros</p>
<tr>
<td>Código</td>
<td>Nome</td>
<td>Autor</td>
<td>edição</td>
<td>editora</td>
</tr>
<c:forEach var="livro" items="${dao.lista}">
<tr>
<td>${livro.codigo}</td>
<td>${livro.nome}</td>
<td>${livro.autor}</td>
<td>${livro.edicao}</td>
<td>${livro.editora}</td>
<td>
<form action="/SistemaBiblioteca/livros/atualizarLivro.jsp" method="get">
<input type="hidden" name="codigo" value="${livro.codigo}"/>
<input type="submit" value="Alterar*"></input>
</form>
</td>

<td>
<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="removerLivro">
<input type="hidden" name="codigo" value="${livro.codigo}"/>
<input type="submit" value="Remover-"></input>
</form>
</td>
</tr>
</c:forEach>
</table>

<!-- <a href="/SistemaBiblioteca/livros/adicionarLivro.html">Adicionar +</a> -->


</br>
</br>
	
	<a href="/SistemaBiblioteca/menu.jsp"> <<<<< voltar</a>
	
	
</body>
</html>