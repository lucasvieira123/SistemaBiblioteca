package DAO;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.triadworks.jdbc.ConnectionFactory;
import model.Aluno;
import model.Funcionario;

public class FuncionarioDAO {
	private Connection conexao;
	private static String NOME_TABELA;
	
	
	
	private Class classe = Funcionario.class;
	private Method[] metodos =  classe.getDeclaredMethods();
	
	private List<Method> metodosComGet = new ArrayList<>();
	private List<String> atributos = new ArrayList<>();
	
	public FuncionarioDAO(){
		this.conexao = new ConnectionFactory().getConnection();
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
	
public void salvar(Funcionario funcionario) {
		
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
					Object retornoDoMetodo = metodosComGet.get(i).invoke(funcionario);
					
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

public void alterar(Funcionario funcionario) {
	String sql = "update "+ NOME_TABELA+" set nome=? "
			+ "where id = ?";
	
	try {
		PreparedStatement preparedStatement = conexao.prepareStatement(sql);
		
		preparedStatement.setString(1, funcionario.getNome());
		preparedStatement.setLong(2, funcionario.getId());
		
		preparedStatement.execute();
		preparedStatement.close();
		conexao.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void deletar (Funcionario funcionario) {
	deletar(funcionario.getId());
	}
	
	public void deletar (Long id) {
		String sql = "delete from "+NOME_TABELA+" where id=?";
		
		try {
			PreparedStatement preparedStatement = conexao.prepareStatement(sql);
			
			preparedStatement.setLong(0, id);
			
			preparedStatement.execute();
			preparedStatement.close();
			conexao.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
