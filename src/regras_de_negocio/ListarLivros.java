package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarLivros implements FuncionalidadesStrategy{

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("livros/ListarLivros.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
