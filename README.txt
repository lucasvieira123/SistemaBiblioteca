Já contém todos os padrões necessários para Java EE{
Camada de apresentação ->Front Controller, a classe que controla todas as requisições iniciais (servetController.java)
Camada de negócios -> BussinessObjet, tira as regras de negócios das entidades (AluguelLivroBussinessObjet.java)
Camada de integração -> DAO (Todas as classes que contem o sufixo DAO)

}


Padrão estrutural{
MVC, model: classes que representam as entidades (estão da pasta model), view (estão na pasta WebContent) e
controller, encaminha das tarefas para as classes responsáveis (ServetController) }

Padrões de projeto{
Strategy ->Várias classes que estão da pasta regras_de_negocio (FuncionalidadesStrategy.java e todas que implementam essa)
Sington -> a conexao é um Sington pois só precisa ser única
Factory -> a ConnectionFactory.java e ConnectionMySql.java, mas acho que n estão correta ainda, vou arrumar 
}

FALTA 1 PADRÃO DE PROJETO, MELHORAR A INTERFACE, E COLOCAR MAIS FUNCIONALIDADES, COMPLETAR CRUD DO ALUNO,
COMPLETAR O CRUD DO FUNCIONARIO COM SEUS RESPECTIVOS FUNCIONAMENTOS; APLICAR MAIS ALGUMAS REGRAS DE NEGÓCIOS.