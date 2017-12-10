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

import DAO.AlunoDAO;
import exceptions.GeralException;
import model.Aluno;

public class AdicionarAluno implements FuncionalidadesStrategy {

	@Override
	public void executaFuncionalida(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

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
