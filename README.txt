Após efetuar a configuração conforme orientações abaixo utilize 
a seguinte conta para autenticação na aplicação:

Usuário: administrador
Senha: 123456

OBS: para inclusão de usuários do sistema, suas senhas devem ser armazenadas digeridas 
     pelo padrão SHA-512

Link de acesso ao front-end Primefaces: Http://localhost:8080/supero/index.faces
Link de acesso ao front-end AngularJS: Http://localhost:8080/supero/index.html


Orientações de configuração:

1 - Crie uma base de dados MySQL com o nome 'supero' ou o nome que desejar. Caso escolha
    um nome diferente de 'supero', fique atento(a) para replicar a escolha no datasource.
    
2 - Execute os scripts de criação das tables e insersão de 
    dados básicos do arquivo '/sql/dump.sql'

3 - Crie um usuário do SGBD MySQL com nome igual a 'supero' e senha igual a 'supero' ou o que desejar.
    Caso escolha diferente de 'supero' fique atrnto(a) para replicar a escolha no datasource.
    Conceda grant mínimo de 'CRUD' para o usuário criado, na base citada no passo 1 

4 - Faça deploy do driver do conector MySQL na sua instância do JBoss com o arquivo .jar 
    de acordo com a versão do Wildfly e do MySQL.
   
5 - Crie o datasource para o banco/usuário recém criado:
