<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.LivroDAO"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar Livro</title>
</head>
<body>
<fmt:parseNumber var="codigo" scope = "session" type="number" value="${param.codigo}" />
<c:set var = "livro" scope = "session" value = "${dao.getLivro(codigo)}"/>

<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="alterarLivro">
<input type="hidden" name="codigo" value="${livro.codigo}">
	Nome: <input type="text" name="nome" value="${livro.nome}"> </br>
	autor: <input type="text" name="autor" value="${livro.autor}"> </br>
	edicao: <input type="text" name="edicao" value="${livro.edicao} "> </br>
	editora: <input type="text" name="editora" value="${livro.editora} "> </br>
	Quantidade Exemplares <input type="text" name="quantidadeExemplares" value="${livro.quantidadeExemplares}"></br>
	
	Salvar <input type="submit" value="Salvar" >
</form>
</body>
</html>

</body>
</html>