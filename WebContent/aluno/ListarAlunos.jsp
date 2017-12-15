<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="dao" class="DAO.AlunoDAO"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de alunos</title>
</head>
<body>
<table border="1px">
<p>Lista de alunos</p>
<tr>
<td>Matricula</td>
<td>Nome</td>
<td>Data Nasc.</td>
<td>Endereço</td>
</tr>
<c:forEach var="aluno" items="${dao.lista}">
<tr>
<td>${aluno.matricula}</td>
<td>${aluno.nome}</td>
<td><fmt:formatDate value="${aluno.data_nascimento.time}" type="date" dateStyle="short"/></td>
<td>${aluno.endereco} </td>

<form action="sistema" method="get">
	 <input type="hidden" name="acao" value="RemoverAluno"/>
	 <input type="hidden" name="matricula" value="${aluno.matricula}"/>
	<td> <button type="submit" value="">Remover -</button> </td>
</form>

<form action="sistema" method="get">
 <input type="hidden" name="acao" value="ChamarTelaAlterarAluno"/>
<input type="hidden" name="matricula" value="${aluno.matricula}"/>
	<td> <button type="submit">Alterar *</button></td> 
</form>
</tr>
</c:forEach>


</table>

<a href="/SistemaBiblioteca/aluno/AdicionarAluno.html">Adicionar +</a></br></br>



<a href="/SistemaBiblioteca/sistema?acao=VoltarMenu"> <<<<< voltar</a>
	
	
</body>
</html>