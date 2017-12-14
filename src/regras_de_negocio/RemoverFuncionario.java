package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FuncionarioDAO;
import model.Funcionario;

public class RemoverFuncionario implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		String idEmString = request.getParameter("id");
		Long id = Long.valueOf(idEmString);
		dao.deletar(id);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("sistema?acao=ListarFuncionarios");
		requestDispatcher.forward(request, response);
		
		
	}

}
