<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.AlunoDAO"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<fmt:parseNumber var="matricula" scope = "session" type="number" value="${param.matricula}" />
<c:set var = "aluno" scope = "session" value = "${dao.getAluno(matricula)}"/>

<form action="sistema" method="get">
<input type="hidden" name="acao" value="alterarAluno">
<input type="hidden" name="matricula" value="${aluno.matricula}">
	Nome: <input type="text" name="nome" value="${aluno.nome}"> </br>
	Data: <input type="text" name="data_nascimento" value="<fmt:formatDate value="${aluno.data_nascimento.time}" type="date" dateStyle="short"/>" > </br>
	Endereço: <input type="text" name="endereco" value="${aluno.endereco} "> </br>
	
	Salvar <input type="submit" value="Salvar" >
</body>
</html>

</body>
</html>