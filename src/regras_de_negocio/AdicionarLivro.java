package regras_de_negocio;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LivroDAO;
import model.Livro;

public class AdicionarLivro implements FuncionalidadesStrategy{

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = "";
		String autor = "";
		String edicao = "";
		String editora = "";
		String quantidadeExemplares;

		nome = request.getParameter("nome");
		autor = request.getParameter("autor");
		edicao = request.getParameter("edicao");
		editora = request.getParameter("editora");
		quantidadeExemplares = request.getParameter("quantidadeExemplares");

		Livro livro = new Livro();

		livro.setAutor(autor);
		livro.setEdicao(edicao);
		livro.setEditora(editora);
		livro.setNome(nome);
		livro.setQuantidadeExemplares(Integer.parseInt(quantidadeExemplares));

		LivroDAO livroDAO = new LivroDAO();
		livroDAO.salvar(livro);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/livros/ListarLivros.jsp");
		requestDispatcher.forward(request, response);
	}

}
