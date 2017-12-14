<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Funcionario</title>
</head>
<body>

<form action="/SistemaBiblioteca/sistema" method="get">
<input type="hidden" name="acao" value="AdicionarFuncionario">
	Nome: <input type="text" name="nome" value="" required> </br>
	<input type="submit" value="Salvar" >
	
</form>

</body>
</html>