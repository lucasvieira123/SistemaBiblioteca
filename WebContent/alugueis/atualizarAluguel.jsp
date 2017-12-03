<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.AluguelLivroDAO"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atualizar aluguel</title>
</head>
<body>

<fmt:parseNumber var="codigo" scope = "session" type="number" value="${param.codigo}" />
<c:set var = "aluguel" scope = "session" value = "${dao.getAluguel(codigo)}"/>

<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="alterarAluguel">
<input type="hidden" name="codigo" value="${aluguel.codigo}">
	Código Livro: <input type="text" name="codigo_livro" value="${aluguel.codigo_livro}"> </br>
	Matricula Aluno: <input type="text" name="matricula_aluno" value="${aluguel.matricula_aluno}"> </br>
	Data Inicial: <input type="text" name="data_inicial" value="<fmt:formatDate value="${aluguel.data_inicial.time}" type="date" dateStyle="short"/>"> </br>
	Data Final: <input type="text" name="data_final" value="<fmt:formatDate value="${aluguel.data_final.time}" type="date" dateStyle="short"/>"> </br>
	Código funcionario: <input type="text" name="codigo_funcionario" value="${aluguel.codigo_funcionario}"> </br>
	
	
	
	Salvar <input type="submit" value="Salvar" >
</form>



</body>
</html>