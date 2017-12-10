package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LivroDAO;
import model.Livro;

public class AlterarLivro implements FuncionalidadesStrategy{

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codigoEmString = request.getParameter("codigo");
		Long codigo = Long.valueOf(codigoEmString);

		String nome = "";
		String autor = "";
		String edicao = "";
		String editora = "";

		nome = request.getParameter("nome");
		autor = request.getParameter("autor");
		edicao = request.getParameter("edicao");
		editora = request.getParameter("editora");

		Livro livro = new Livro();

		livro.setAutor(autor);
		livro.setEdicao(edicao);
		livro.setEditora(editora);
		livro.setNome(nome);
		livro.setCodigo(codigo);

		LivroDAO livroDAO = new LivroDAO();

		livroDAO.alterar(livro);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/livros/ListarLivros.jsp");
		requestDispatcher.forward(request, response);
		
	}

}
