<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="/SistemaBiblioteca/Sistema"  method="get"></form>
<input type="hidden" name="acao" value="fazerLogin">
Login <input type="text" name="identificador" value="">
Senha <input type="password" name="senha" value="">
<input type="submit" name="" value="acessar">

<a href="/SistemaBiblioteca/Sistema?acao=criarConta"></a>

</body>
</html>