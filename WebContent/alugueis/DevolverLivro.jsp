<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="LivroDevolvido">
Matricula: <input type="text" name="matricula_aluno" value="">
C�digo livro: <input type="text" name="codigo_livro" value="">
<input type="submit" value="Devolver">
</form>
<a href="/SistemaBiblioteca/sistema?acao=VoltarMenu"> <<<<< voltar</a>
</body>
</html>