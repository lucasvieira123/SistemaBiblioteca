package DAO;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class ExtrattorFacade {
	private Class classe;
	private Method[] metodos;
	private List<Method> metodosComGet = new ArrayList<>();
	private List<String> atributos = new ArrayList<>();
	private String nomeClasse;
	
	public ExtrattorFacade(Class classe) {
		classe = classe;
		metodos = classe.getDeclaredMethods();
		nomeClasse = classe.getSimpleName().toLowerCase();
		extrate();	
	}
	
	
	private void extrate() {
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


	public List<Method> getMetodosComGet() {
		return metodosComGet;
	}


	public List<String> getAtributos() {
		return atributos;
	}


	public String getNomeClasse() {
		return nomeClasse;
	}

	
	



	
	
	
	
	


}
