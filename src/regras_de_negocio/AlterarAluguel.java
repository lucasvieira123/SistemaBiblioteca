package regras_de_negocio;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AluguelLivroDAO;
import model.AluguelLivro;

public class AlterarAluguel implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();

		String codigoEmString = request.getParameter("codigo");
		Long codigo = Long.valueOf(codigoEmString);

		String codigoLivroEmString = "";
		Long codigoLivro;
		String matriculaAlunoEmString = "";
		Long matriculaAluno;
		String dataInicialEmString = "";
		Date dataInicial = null;
		String dataFinalEmString = "";
		Date dataFinal = null;
		String codigoFuncionarioEmString = "";
		Long codigoFuncionario;

		codigoLivroEmString = request.getParameter("codigo_livro");
		codigoLivro = Long.valueOf(codigoLivroEmString);

		matriculaAlunoEmString = request.getParameter("matricula_aluno");
		matriculaAluno = Long.valueOf(matriculaAlunoEmString);

		dataInicialEmString = request.getParameter("data_inicial");
		try {
			dataInicial = sdf.parse(dataInicialEmString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dataFinalEmString = request.getParameter("data_final");
		try {
			dataFinal = sdf.parse(dataFinalEmString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

}
