package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AluguelLivroDAO;

public class RemoverAluguel implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long codigo = Long.valueOf(request.getParameter("codigo"));

		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		aluguelLivroDAO.deletar(codigo);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/ListarAlugueisLivro.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
