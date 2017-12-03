package regras;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import DAO.AluguelLivroDAO;
import model.AluguelLivro;

public class CalculadorMulta {
public AluguelLivro aluguelLivroRegistro = null;
private static final Double VALOR_MULTA_DIARIA = 1.0;

	public CalculadorMulta(String codigoLivro, String matriculaAluno) {
		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		for(AluguelLivro aluguelLivro : aluguelLivroDAO.getLista()) {
			if(String.valueOf(aluguelLivro.getCodigo_livro()).equals(codigoLivro)) {
				if(String.valueOf(aluguelLivro.getMatricula_aluno()).equals(matriculaAluno)){
					aluguelLivroRegistro= aluguelLivro;
					break;
				}
			}
		}
		
	}
	
	public long calcular() {
		 long diff = this.aluguelLivroRegistro.getData_final().getTime().getTime() - aluguelLivroRegistro.getData_inicial().getTime().getTime();
		 return  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

}
