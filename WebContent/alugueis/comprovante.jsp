<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.AluguelLivroDAO"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comprovante</title>
</head>
<body>

<fmt:parseNumber var="codigo" scope = "session" type="number" value="${param.codigo}" />
<fmt:parseNumber var="valor" scope = "session" type="number" value="${param.valor}" />
<c:set var = "aluguel" scope = "session" value = "${dao.getAluguel(codigo)}"/>

	Código Livro: ${aluguel.codigo_livro} </br>
	Matricula Aluno: ${aluguel.matricula_aluno} </br>
	Data Inicial: <fmt:formatDate value="${aluguel.data_inicial.time}" type="date" dateStyle="short"/> </br>
	Data prevista: <fmt:formatDate value="${aluguel.data_final.time}" type="date" dateStyle="short"/> </br>
	Data final: <fmt:formatDate value="${now}" type="date" dateStyle="short"/> </br>
	Código funcionario: ${aluguel.codigo_funcionario}" </br>
	Valor para pagar:RS: ${valor}"
	</br></br>
	<a href="/SistemaBiblioteca/sistema?acao=VoltarMenu"> <<<<< voltar</a>

</body>
</html>