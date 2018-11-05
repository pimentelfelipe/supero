Ap�s efetuar a configura��o conforme orienta��es abaixo utilize 
a seguinte conta para autentica��o na aplica��o:

Usu�rio: administrador
Senha: 123456

OBS: para inclus�o de usu�rios do sistema, suas senhas devem ser armazenadas digeridas 
     pelo padr�o SHA-512

Link de acesso ao front-end Primefaces: Http://localhost:8080/supero/index.faces
Link de acesso ao front-end AngularJS: Http://localhost:8080/supero/index.html


Orienta��es de configura��o:

1 - Crie uma base de dados MySQL com o nome 'supero' ou o nome que desejar. Caso escolha
    um nome diferente de 'supero', fique atento(a) para replicar a escolha no datasource.
    
2 - Execute os scripts de cria��o das tables e insers�o de 
    dados b�sicos do arquivo '/sql/dump.sql'

3 - Crie um usu�rio do SGBD MySQL com nome igual a 'supero' e senha igual a 'supero' ou o que desejar.
    Caso escolha diferente de 'supero' fique atrnto(a) para replicar a escolha no datasource.
    Conceda grant m�nimo de 'CRUD' para o usu�rio criado, na base citada no passo 1 

4 - Fa�a deploy do driver do conector MySQL na sua inst�ncia do JBoss com o arquivo .jar 
    de acordo com a vers�o do Wildfly e do MySQL.
   
5 - Crie o datasource para o banco/usu�rio rec�m criado:
