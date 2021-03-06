package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import jdbc.ConnectorFactory;
import jdbc.MySQLConnectorFactory;
import jdbc.ProductConnector;
import model.AluguelLivro;
import model.Aluno;
import model.Funcionario;


public class AluguelLivroDAO {
	private Connection conexao;
	private static String NOME_TABELA;
	private ConnectorFactory fConnecor = ConnectorFactory.getInstance("mariadb");
	private ProductConnector pConnector = fConnecor.getConnector(
				"jdbc:mysql://localhost/biblioteca", "root", "root"
			);
	
	

	
	private List<Method> metodosComGet = new ArrayList<>();
	private List<String> atributos = new ArrayList<>();
	
	public AluguelLivroDAO(){
		ConnectorFactory connectionFactory = MySQLConnectorFactory.getInstance();
		this.conexao = pConnector.getConnection();
	
		ExtrattorFacade extratorFacede = new ExtrattorFacade(AluguelLivro.class);
		atributos = extratorFacede.getAtributos();
		metodosComGet = extratorFacede.getMetodosComGet();
		NOME_TABELA = extratorFacede.getNomeClasse();
	}
	
public void salvar(AluguelLivro aluguelLivro){
		
		String sql = String.format("insert into %s ",NOME_TABELA );
		for(int i =0; i<atributos.size(); i++) {
			
			//primero
			if(i==0) {
				sql=sql.concat("(");
			}
			
			//antes do ultimo
			if(i<=atributos.size()-1) {
				sql=sql.concat("%s");
				sql = String.format(sql, ","+atributos.get(i) );
			}
			//ultimo atributo
			if(i==atributos.size()-1) {
				sql= sql.replaceFirst(",", " ");
				
				sql=sql.concat(")");
				
			}
			
		}
		
		sql =sql.concat(" values ");
		
		String values="";
		
	for(int i =0; i<atributos.size(); i++) {
			
			//primero
			if(i==0) {
				values=values.concat("(");
			}
			
			//antes do ultimo
			if(i<=atributos.size()-1) {
				values=values.concat(",?");
			}
			//ultimo atributo
			if(i==atributos.size()-1) {
				values= values.replaceFirst(",", " ");
				values=values.concat(")");
			
			}
			
		}
	
	sql =sql.concat(values);
		
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			
		
			for(int i =0; i< metodosComGet.size() ; i++) {
				try {
					Object retornoDoMetodo = metodosComGet.get(i).invoke(aluguelLivro);					
					if(retornoDoMetodo instanceof String) {
						preparedStatement.setString(i+1, (String)retornoDoMetodo);
					}else if(retornoDoMetodo instanceof Long) {
						preparedStatement.setLong(i+1, (Long)retornoDoMetodo);
						
					}else if(retornoDoMetodo instanceof Calendar) {
						preparedStatement.setDate(i+1, new Date(((Calendar)retornoDoMetodo).getTimeInMillis()));
						
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

public void alterar(AluguelLivro aluguelLivro) {
	String sql = "update "
			+NOME_TABELA+" set codigo_livro=?, matricula_aluno=?, data_inicial=?, data_final=?, codigo_funcionario=?"
					+ " where codigo=?";
	
	try {
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setLong(1, aluguelLivro.getCodigo_livro());
		stmt.setLong(2, aluguelLivro.getMatricula_aluno());
		stmt.setDate(3, new Date(aluguelLivro.getData_inicial().getTimeInMillis()));
		stmt.setDate(4, new Date(aluguelLivro.getData_final().getTimeInMillis()));
		stmt.setLong(5, aluguelLivro.getCodigo_funcionario());
		stmt.setLong(6, aluguelLivro.getCodigo());
		stmt.execute();
		stmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void deletar (AluguelLivro aluguelLivro) {
	deletar(aluguelLivro.getCodigo());
	}
	
	public void deletar (Long codigo) {
		String sql = "delete from "+NOME_TABELA+" where codigo=?";
		
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
	
	public List<AluguelLivro> getLista() {
		List<AluguelLivro> aluguelLivros = new ArrayList<>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("select * from "+NOME_TABELA);
			
			ResultSet result = stmt.executeQuery();
			AluguelLivro aluguelLivro = null;
			Calendar calendar = Calendar.getInstance();
			Date dataInicial =null;
			Date dataFinal = null;
			
			while(result.next()) {
				aluguelLivro = new AluguelLivro();
				aluguelLivro.setCodigo(result.getLong("codigo"));
				dataInicial =result.getDate("data_inicial");
				dataFinal =result.getDate("data_final");
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			
				calendar.setTime(dataInicial);

				aluguelLivro.setData_inicial(calendar);
				
				System.out.println(df.format(aluguelLivro.getData_inicial().getTime()));
				
				calendar = Calendar.getInstance();
				calendar.setTime(dataFinal);
				
			
				aluguelLivro.setData_final(calendar);
				System.out.println(df.format(aluguelLivro.getData_final().getTime()));
				aluguelLivro.setMatricula_aluno(result.getLong("matricula_aluno"));
				aluguelLivro.setCodigo_funcionario(result.getLong("codigo_funcionario"));
				aluguelLivro.setCodigo_livro(result.getLong("codigo_livro"));
				
				
				
				
		
				
				aluguelLivros.add(aluguelLivro);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aluguelLivros;
	}
	
	public AluguelLivro getAluguel(Long codigo) throws SQLException {
		String sql = "select * from "+NOME_TABELA+" where codigo = ?";
		Calendar cal = Calendar.getInstance();
		
		PreparedStatement preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setLong(1, codigo);
		
	ResultSet set=preparedStatement.executeQuery();
	set.next();
	
	AluguelLivro aluguelLivro = new AluguelLivro();
	aluguelLivro.setCodigo(codigo);
	aluguelLivro.setCodigo_funcionario(set.getLong("codigo_funcionario"));
	aluguelLivro.setCodigo_livro(set.getLong("codigo_livro"));
	cal.setTime(set.getDate("data_final"));
	aluguelLivro.setData_final(cal);
	
	cal = Calendar.getInstance();
	
	cal.setTime(set.getDate("data_inicial"));
	System.out.println(set.getDate("data_inicial").getTime());
	aluguelLivro.setData_inicial(cal);
	aluguelLivro.setMatricula_aluno(set.getLong("matricula_aluno"));
	
	
		return aluguelLivro;
		
	}
	
	
	
}
