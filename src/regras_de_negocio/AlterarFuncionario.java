package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FuncionarioDAO;
import model.Funcionario;

public class AlterarFuncionario implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = new Funcionario();
		String nome = (String) request.getParameter("nome");
		String idEmString = (String) request.getParameter("id");
		Long id = Long.valueOf(idEmString);
		funcionario.setId(id);
		funcionario.setNome(nome);
		
		dao.alterar(funcionario);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("sistema?acao=ListarFuncionarios");
		requestDispatcher.forward(request, response);
		
	}

}
