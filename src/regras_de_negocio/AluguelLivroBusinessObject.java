package regras_de_negocio;

import java.util.concurrent.TimeUnit;

import DAO.AluguelLivroDAO;
import DAO.LivroDAO;
import model.AluguelLivro;
import model.Livro;

public class AluguelLivroBusinessObject {
	
	public static boolean validarQuantidadeDisponivel(Long codigoLivro) {
		LivroDAO livroDAO = new LivroDAO();
		Livro livro = livroDAO.getLivro(codigoLivro);
		
		if (livro.getQuantidadeExemplares() <= 0.0) {
			return true;
		} else {
			return false;
		}

	}
	
	
	public static long calcular(AluguelLivro aluguelLivroRegistro) {
		 long diff = aluguelLivroRegistro.getData_final().getTime().getTime() - aluguelLivroRegistro.getData_inicial().getTime().getTime();
		 return  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}



	public static AluguelLivro pegarAluguelDoLivro(String codigoLivro, String matriculaAluno) {
		AluguelLivroDAO aluguelLivroDAO = new AluguelLivroDAO();
		for(AluguelLivro aluguelLivro : aluguelLivroDAO.getLista()) {
			if(String.valueOf(aluguelLivro.getCodigo_livro()).equals(codigoLivro)) {
				if(String.valueOf(aluguelLivro.getMatricula_aluno()).equals(matriculaAluno)){
					return aluguelLivro;
					
				}
			}
		}
		return null;
	}
	

}
