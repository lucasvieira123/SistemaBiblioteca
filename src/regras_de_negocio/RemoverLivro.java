package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LivroDAO;

public class RemoverLivro implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LivroDAO livrodao = new LivroDAO();
		String codigoEmString = request.getParameter("codigo");
		Long codigo = Long.valueOf(codigoEmString);
		livrodao.deletar(codigo);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/livros/ListarLivros.jsp");
		requestDispatcher.forward(request, response);

	}

}
