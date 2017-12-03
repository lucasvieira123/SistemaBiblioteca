package br.triadworks.javaweb.servets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AluguelLivroDAO;
import DAO.AlunoDAO;
import DAO.LivroDAO;
import RN.ValidarAluguelLivroRN;
import exceptions.GeralException;
import model.AluguelLivro;
import model.Aluno;
import model.Livro;

@WebServlet("/sistema")
public class ServletPrincipal extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao.equals("adicionarAluno")) {
			adicionarAluno(request, response);
		} else if (acao.equals("removerAluno")) {
			removerAluno(request, response);
		} else if (acao.equals("alterarAluno")) {
			alterarAluno(request, response);
		} else if (acao.equals("adicionarLivro")) {
			adicionarLivro(request, response);
		} else if (acao.equals("removerLivro")) {
			removerLivro(request, response);
		} else if (acao.equals("alterarLivro")) {
			alterarLivro(request, response);
		} else if (acao.equals("alterarAluguel")) {
			try {
				alterarAluguel(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (acao.equals("removerAluguel")) {
			removerAluguel(request, response);
		} else if (acao.equals("adicionarAluguelLivro")) {
			try {
				adicionarAluguelLivro(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void adicionarAluguelLivro(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		LivroDAO livroDAO = new LivroDAO();

		String codigoLivroEmString = "";
		Long codigoLivro;
		String matriculaAlunoEmString = "";
		Long matriculaAluno;
		String dataInicialEmString = "";
		Date dataInicial;
		String dataFinalEmString = "";
		Date dataFinal;
		String codigoFuncionarioEmString = "";
		Long codigoFuncionario;

		codigoLivroEmString = request.getParameter("codigo_livro");
		codigoLivro = Long.valueOf(codigoLivroEmString);

		matriculaAlunoEmString = request.getParameter("matricula_aluno");
		matriculaAluno = Long.valueOf(matriculaAlunoEmString);

		dataInicialEmString = request.getParameter("data_inicial");
		dataInicial = sdf.parse(dataInicialEmString);

		dataFinalEmString = request.getParameter("data_final");
		dataFinal = sdf.parse(dataFinalEmString);

		codigoFuncionarioEmString = request.getParameter("codigo_funcionario");
		codigoFuncionario = Long.valueOf(codigoFuncionarioEmString);

		AluguelLivro aluguelLivro = new AluguelLivro();
		Calendar cal = Calendar.getInstance();
		aluguelLivro.setCodigo_funcionario(codigoFuncionario);
		aluguelLivro.setCodigo_livro(codigoLivro);

		cal.setTime(dataFinal);
		aluguelLivro.setData_final(cal);
		cal = Calendar.getInstance();
		cal.setTime(dataInicial);
		aluguelLivro.setData_inicial(cal);

		aluguelLivro.setMatricula_aluno(matriculaAluno);

		if (ValidarAluguelLivroRN.validarQuantidadeDisponivel(codigoLivro)) {
			//Emite um alerta na tela, mas n�o consegui fazer com que a p�gina recarregasse novamente
			/*PrintWriter out = response.getWriter();
			out.println("<script>alert('N�o � poss�vel locar este livro, pois n�o h� exemplares dispon�veis!');</script>");*/
			request.setAttribute("mensagem", "N�o � poss�vel locar este livro, pois n�o h� exemplares dispon�veis!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/ListarAlugueisLivro.jsp");
			requestDispatcher.forward(request, response);
		} else {
			aluguelLivroDAO.salvar(aluguelLivro);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/ListarAlugueisLivro.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	private void removerAluguel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long codigo = Long.valueOf(request.getParameter("codigo"));

		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		aluguelLivroDAO.deletar(codigo);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/ListarAlugueisLivro.jsp");
		requestDispatcher.forward(request, response);

	}

	private void alterarAluguel(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();

		String codigoEmString = request.getParameter("codigo");
		Long codigo = Long.valueOf(codigoEmString);

		String codigoLivroEmString = "";
		Long codigoLivro;
		String matriculaAlunoEmString = "";
		Long matriculaAluno;
		String dataInicialEmString = "";
		Date dataInicial;
		String dataFinalEmString = "";
		Date dataFinal;
		String codigoFuncionarioEmString = "";
		Long codigoFuncionario;

		codigoLivroEmString = request.getParameter("codigo_livro");
		codigoLivro = Long.valueOf(codigoLivroEmString);

		matriculaAlunoEmString = request.getParameter("matricula_aluno");
		matriculaAluno = Long.valueOf(matriculaAlunoEmString);

		dataInicialEmString = request.getParameter("data_inicial");
		dataInicial = sdf.parse(dataInicialEmString);

		dataFinalEmString = request.getParameter("data_final");
		dataFinal = sdf.parse(dataFinalEmString);

		codigoFuncionarioEmString = request.getParameter("codigo_funcionario");
		codigoFuncionario = Long.valueOf(codigoFuncionarioEmString);

		AluguelLivro aluguelLivro = new AluguelLivro();
		Calendar cal = Calendar.getInstance();
		aluguelLivro.setCodigo(codigo);
		aluguelLivro.setCodigo_funcionario(codigoFuncionario);
		aluguelLivro.setCodigo_livro(codigoLivro);

		cal.setTime(dataFinal);
		aluguelLivro.setData_final(cal);
		cal = Calendar.getInstance();
		cal.setTime(dataInicial);
		aluguelLivro.setData_inicial(cal);

		aluguelLivro.setMatricula_aluno(matriculaAluno);

		aluguelLivroDAO.alterar(aluguelLivro);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/alugueis/ListarAlugueisLivro.jsp");
		requestDispatcher.forward(request, response);

	}

	private void alterarLivro(HttpServletRequest request, HttpServletResponse response)
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

	private void removerLivro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LivroDAO livrodao = new LivroDAO();
		String codigoEmString = request.getParameter("codigo");
		Long codigo = Long.valueOf(codigoEmString);
		livrodao.deletar(codigo);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/livros/ListarLivros.jsp");
		requestDispatcher.forward(request, response);

	}

	private void adicionarLivro(HttpServletRequest request, HttpServletResponse response)
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

	private void alterarAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String dataNascimentoEmString = request.getParameter("data_nascimento");
		Date dataNascimento = null;
		Calendar dataNascimentoEmCalendar = null;
		try {
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoEmString);
			dataNascimentoEmCalendar = Calendar.getInstance();
			dataNascimentoEmCalendar.setTime(dataNascimento);
		} catch (ParseException e) {

			e.printStackTrace();

			throw new GeralException(e.getMessage());
		}

		String endereco = request.getParameter("endereco");

		Aluno aluno = new Aluno();

		aluno.setNome(nome);
		aluno.setData_nascimento(dataNascimentoEmCalendar);
		aluno.setEndereco(endereco);

		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.alterar(aluno);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/aluno/ListarAlunos.jsp");
		requestDispatcher.forward(request, response);

	}

	private void removerAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String matricula = request.getParameter("matricula");

		AlunoDAO alunoDAO = new AlunoDAO();
		Aluno aluno = new Aluno();
		aluno.setMatricula(Long.valueOf(matricula));
		alunoDAO.deletar(aluno);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/aluno/ListarAlunos.jsp");
		requestDispatcher.forward(request, response);

	}

	private void adicionarAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String dataNascimentoEmString = request.getParameter("data_nascimento");
		Date dataNascimento = null;
		Calendar dataNascimentoEmCalendar = null;
		try {
			dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoEmString);
			dataNascimentoEmCalendar = Calendar.getInstance();
			dataNascimentoEmCalendar.setTime(dataNascimento);
		} catch (ParseException e) {

			e.printStackTrace();

			throw new GeralException(e.getMessage());
		}

		String endereco = request.getParameter("endereco");

		Aluno aluno = new Aluno();

		aluno.setNome(nome);
		aluno.setData_nascimento(dataNascimentoEmCalendar);
		aluno.setEndereco(endereco);

		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.salvar(aluno);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("aluno/ListarAlunos.jsp");
		requestDispatcher.forward(request, response);
	}

}
