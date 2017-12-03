import DAO.LivroDAO;

public class ValidarAluguelLivroRN {


	public static boolean validarQuantidadeDisponivel(Long codigoLivro) {
		LivroDAO livroDAO = new LivroDAO();

		if (livroDAO.pegaQuantidadeAtualDoLivro(codigoLivro) <= 0) {
			return true;
		} else {
			return false;
		}

	}

}
