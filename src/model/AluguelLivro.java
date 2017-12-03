package model;

import java.util.Calendar;

public class AluguelLivro {
private long codigo;
private long codigo_livro;
private long matricula_aluno;
private long codigo_funcionario;
private Calendar data_inicial;
private Calendar data_final;

public long getCodigo() {
	return codigo;
}
public void setCodigo(long codigo) {
	this.codigo = codigo;
}
public long getCodigo_livro() {
	return codigo_livro;
}
public void setCodigo_livro(long codigo_livro) {
	this.codigo_livro = codigo_livro;
}
public long getMatricula_aluno() {
	return matricula_aluno;
}
public void setMatricula_aluno(long matricula_aluno) {
	this.matricula_aluno = matricula_aluno;
}
public long getCodigo_funcionario() {
	return codigo_funcionario;
}
public void setCodigo_funcionario(long codigo_funcionario) {
	this.codigo_funcionario = codigo_funcionario;
}
public Calendar getData_inicial() {
	return data_inicial;
}
public void setData_inicial(Calendar data_inical) {
	this.data_inicial = data_inical;
}
public Calendar getData_final() {
	return data_final;
}
public void setData_final(Calendar data_final) {
	this.data_final = data_final;
}


}
