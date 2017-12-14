package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FuncionarioDAO;
import model.Funcionario;

public class AdicionarFuncionario implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		String nome = (String) request.getParameter("nome");

		funcionario.setNome(nome);
		
		dao.salvar(funcionario);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("sistema?acao=ListarFuncionarios");
		requestDispatcher.forward(request, response);
		
	}

}
