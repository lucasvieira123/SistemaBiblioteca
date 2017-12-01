package model;

import java.util.Calendar;

public class Aluno {
private long matricula;
private String nome;
private Calendar data_nascimento;
private String endereco;

public long getMatricula() {
	return matricula;
}
public void setMatricula(long matricula) {
	this.matricula = matricula;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Calendar getData_nascimento() {
	return data_nascimento;
}
public void setData_nascimento(Calendar data_nascimento) {
	this.data_nascimento = data_nascimento;
}
public String getEndereco() {
	return endereco;
}
public void setEndereco(String endereco) {
	this.endereco = endereco;
}


}
