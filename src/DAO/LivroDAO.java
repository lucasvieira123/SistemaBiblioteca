package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import jdbc.ConnectorFactory;
import jdbc.MySQLConnectorFactory;
import jdbc.ProductConnector;
import model.Aluno;
import model.Funcionario;
import model.Livro;

public class LivroDAO {
	private Connection conexao;
	private static String NOME_TABELA;
	private ConnectorFactory fConnecor = ConnectorFactory.getInstance("mariadb");
	private ProductConnector pConnector = fConnecor.getConnector(
				"jdbc:mysql://localhost/biblioteca", "root", "root"
			);


	private List<Method> metodosComGet = new ArrayList<>();
	private List<String> atributos = new ArrayList<>();

	public LivroDAO() {
		ConnectorFactory connectionFactory = MySQLConnectorFactory.getInstance();
		this.conexao = pConnector.getConnection();
		
		ExtrattorFacade extratorFacede = new ExtrattorFacade(Livro.class);
		atributos = extratorFacede.getAtributos();
		metodosComGet = extratorFacede.getMetodosComGet();
		NOME_TABELA = extratorFacede.getNomeClasse();

	}

	public void salvar(Livro livro) {

		String sql = String.format("insert into %s ", NOME_TABELA);
		for (int i = 0; i < atributos.size(); i++) {

			// primero
			if (i == 0) {
				sql = sql.concat("(");
			}

			// antes do ultimo
			if (i <= atributos.size() - 1) {
				sql = sql.concat("%s");
				sql = String.format(sql, "," + atributos.get(i));
			}
			// ultimo atributo
			if (i == atributos.size() - 1) {
				sql = sql.replaceFirst(",", " ");

				sql = sql.concat(")");

			}

		}

		sql = sql.concat(" values ");

		String values = "";

		for (int i = 0; i < atributos.size(); i++) {

			// primero
			if (i == 0) {
				values = values.concat("(");
			}

			// antes do ultimo
			if (i <= atributos.size() - 1) {
				values = values.concat(",?");
			}
			// ultimo atributo
			if (i == atributos.size() - 1) {
				values = values.replaceFirst(",", " ");
				values = values.concat(")");

			}

		}

		sql = sql.concat(values);

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);

			for (int i = 0; i < metodosComGet.size(); i++) {
				try {
					Object retornoDoMetodo = metodosComGet.get(i).invoke(livro);
					if (retornoDoMetodo instanceof String) {
						preparedStatement.setString(i + 1, (String) retornoDoMetodo);
					} else if (retornoDoMetodo instanceof Long) {
						preparedStatement.setLong(i + 1, (Long) retornoDoMetodo);

					} else if (retornoDoMetodo instanceof Calendar) {
						preparedStatement.setDate(i + 1, new Date(((Calendar) retornoDoMetodo).getTimeInMillis()));
					} else if (retornoDoMetodo instanceof Integer) {
						preparedStatement.setInt(i + 1, (Integer) retornoDoMetodo);
					}

				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			preparedStatement.execute();
			preparedStatement.close();
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();

		}
	}

	public List<Livro> getLista() {
		List<Livro> livros = new ArrayList<>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("select * from " + NOME_TABELA);

			ResultSet result = stmt.executeQuery();
			Livro livro = null;
			Long codigo;
			String nome;
			String autor;
			String edicao;
			String editora;
			Integer quantidadeExemplares;

			while (result.next()) {
				livro = new Livro();

				codigo = result.getLong("codigo");
				nome = result.getString("nome");
				autor = result.getString("autor");
				edicao = result.getString("edicao");
				editora = result.getString("editora");
				quantidadeExemplares = result.getInt("quantidadeexemplares");

				livro.setAutor(autor);
				livro.setCodigo(codigo);
				livro.setEdicao(edicao);
				livro.setEditora(editora);
				livro.setNome(nome);
				livro.setQuantidadeExemplares(quantidadeExemplares);

				livros.add(livro);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return livros;
	}

	
	public void alterar(Livro livro) {
		String sql = "update " + NOME_TABELA + " set nome=?, autor=?,edicao=?,editora=?,quantidadeexemplares=? "
				+ "where codigo =?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setString(1, livro.getNome());
			preparedStatement.setString(2, livro.getAutor());
			preparedStatement.setString(3, livro.getEdicao());
			preparedStatement.setString(4, livro.getEditora());
			preparedStatement.setInt(5, livro.getQuantidadeExemplares());
			preparedStatement.setLong(6, livro.getCodigo());

			preparedStatement.execute();
			preparedStatement.close();
			conexao.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletar(Livro livro) {
		deletar(livro.getCodigo());
	}

	public void deletar(Long codigo) {
		String sql = "delete from " + NOME_TABELA + " where codigo=?";

		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);

			preparedStatement.setLong(1, codigo);

			preparedStatement.execute();
			preparedStatement.close();
			conexao.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Livro getLivro(Long codigo) {
		String sql = "select * from " + NOME_TABELA + " where codigo = ?";

		Livro livro = new Livro();
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			preparedStatement.setLong(1, codigo);
			ResultSet set = preparedStatement.executeQuery();

			set.next();

			livro.setCodigo(set.getLong("codigo"));
			livro.setNome(set.getString("nome"));
			livro.setAutor(set.getString("autor"));
			livro.setEdicao(set.getString("edicao"));
			livro.setEditora(set.getString("editora"));
			livro.setQuantidadeExemplares(set.getInt("quantidadeexemplares"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return livro;

	}

}
