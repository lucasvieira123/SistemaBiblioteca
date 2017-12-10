package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AluguelLivroDAO;
import model.AluguelLivro;

public class LivroDevolvido implements FuncionalidadesStrategy{

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codigoLivro = String.valueOf(request.getParameter("codigo_livro"));
		String matriculaAluno = String.valueOf(request.getParameter("matricula_aluno"));
		
		AluguelLivro aluguelLivro = AluguelLivroBusinessObject.pegarAluguelDoLivro(codigoLivro, matriculaAluno);
		Long valordaMulta = AluguelLivroBusinessObject.calcular(aluguelLivro);
		request.setAttribute("valor", valordaMulta);
		request.setAttribute("codigo", aluguelLivro.getCodigo());
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/comprovante.jsp");
		requestDispatcher.forward(request, response);
		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		
		aluguelLivroDAO.deletar(aluguelLivro);
		
	}

}
