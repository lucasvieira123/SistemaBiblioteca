package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AlunoDAO;
import model.Aluno;

public class RemoverAluno implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String matricula = request.getParameter("matricula");

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();
		aluno.setMatricula(Long.valueOf(matricula));
		alunoDAO.deletar(aluno);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/aluno/ListarAlunos.jsp");
		requestDispatcher.forward(request, response);
		

	}

}
