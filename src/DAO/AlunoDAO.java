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

import jdbc.ConnectionMySql;
import model.AluguelLivro;
import model.Aluno;
import model.Funcionario;

public class AlunoDAO {
	private Connection conexao;
	private static String NOME_TABELA;
	
	
	
	private Class classe = Aluno.class;
	private Method[] metodos =  classe.getDeclaredMethods();
	
	private List<Method> metodosComGet = new ArrayList<>();
	private List<String> atributos = new ArrayList<>();
	
	public AlunoDAO(){
		this.conexao =  ConnectionMySql.getInstance().getConnection();
		this.NOME_TABELA = classe.getSimpleName();

		String nomeDoMetodoAtual;
		for(int j =0; j<metodos.length; j++ ) {
			 nomeDoMetodoAtual = metodos[j].getName();
			 
			 if(nomeDoMetodoAtual.contains("get")) {
				 metodosComGet.add(metodos[j]);
			 }
		}
		
		String nomeAtributo ="";
		for(int i =0 ; i< metodosComGet.size(); i++) {
		 nomeAtributo = metodosComGet.get(i).getName();
			nomeAtributo = nomeAtributo.replace("get", "");
			nomeAtributo = nomeAtributo.toLowerCase();
			atributos.add(nomeAtributo);
		}
		
	}
	
public void salvar(Aluno aluno) {
		
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
					Object retornoDoMetodo = metodosComGet.get(i).invoke(aluno);
					
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

	public List<Aluno> getLista() {
		List<Aluno> alunos = new ArrayList<>();
		try {
			PreparedStatement stmt = conexao.prepareStatement("select * from "+NOME_TABELA);
			
			ResultSet result = stmt.executeQuery();
			Aluno aluno = null;
			Calendar dataNascimentoCalendar = Calendar.getInstance();
			while(result.next()) {
				aluno = new Aluno();
				aluno.setNome(result.getString("nome"));
				
				dataNascimentoCalendar.setTime(result.getDate("data_nascimento"));
				aluno.setData_nascimento(dataNascimentoCalendar);
				
				aluno.setEndereco(result.getString("endereco"));
				
				aluno.setMatricula(result.getLong("matricula"));
				
				alunos.add(aluno);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alunos;
	}
	
	public void alterar (Aluno aluno) {
		String sql = "update "+ NOME_TABELA+" set nome=?, data_nascimento=?, endereco=? "
				+ "where matricula=?";
		
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			
			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setDate(2, new Date(aluno.getData_nascimento().getTimeInMillis()));
			preparedStatement.setString(3, aluno.getEndereco());
			preparedStatement.setLong(4, aluno.getMatricula());
			
			preparedStatement.execute();
			
			preparedStatement.close();
			
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void deletar (Aluno aluno) {
		deletar(aluno.getMatricula());
		}
		
		public void deletar (Long matricula) {
			String sql = "delete from "+NOME_TABELA+" where matricula=?";
			
			try {
				PreparedStatement preparedStatement = conexao.prepareStatement(sql);
				
				preparedStatement.setLong(1, matricula);
				
				preparedStatement.execute();
				preparedStatement.close();
				conexao.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public Aluno getAluno (Long matricula) {
			
			String sql = "select * from "+NOME_TABELA+" where matricula=?";
			Aluno aluno = new Aluno();
			
			try {
				PreparedStatement preparedStatement = conexao.prepareStatement(sql);
				
				preparedStatement.setLong(1, matricula);
				
				ResultSet result = preparedStatement.executeQuery();
				
				result.next();
				
				Calendar dataNascimentoCalendar = Calendar.getInstance();
				
				aluno.setNome(result.getString("nome"));
				
				dataNascimentoCalendar.setTime(result.getDate("data_nascimento"));
				aluno.setData_nascimento(dataNascimentoCalendar);
				
				aluno.setEndereco(result.getString("endereco"));
				
				aluno.setMatricula(result.getLong("matricula"));
				
			
				
				preparedStatement.close();
				conexao.close();
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return aluno;
		}
	
	
	
}
