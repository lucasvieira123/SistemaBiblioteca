J� cont�m todos os padr�es necess�rios para Java EE{
Camada de apresenta��o ->Front Controller, a classe que controla todas as requisi��es iniciais (servetController.java)
Camada de neg�cios -> BussinessObjet, tira as regras de neg�cios das entidades (AluguelLivroBussinessObjet.java)
Camada de integra��o -> DAO (Todas as classes que contem o sufixo DAO)

}


Padr�o estrutural{
MVC, model: classes que representam as entidades (est�o da pasta model), view (est�o na pasta WebContent) e
controller, encaminha das tarefas para as classes respons�veis (ServetController) }

Padr�es de projeto{
Strategy ->V�rias classes que est�o da pasta regras_de_negocio (FuncionalidadesStrategy.java e todas que implementam essa)
Sington -> a conexao � um Sington pois s� precisa ser �nica
Factory -> a ConnectionFactory.java e ConnectionMySql.java, mas acho que n est�o correta ainda, vou arrumar 
}

FALTA 1 PADR�O DE PROJETO, MELHORAR A INTERFACE, E COLOCAR MAIS FUNCIONALIDADES, COMPLETAR CRUD DO ALUNO,
COMPLETAR O CRUD DO FUNCIONARIO COM SEUS RESPECTIVOS FUNCIONAMENTOS; APLICAR MAIS ALGUMAS REGRAS DE NEG�CIOS.