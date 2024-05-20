# Documentação

## Introdução

O **IntimaLearn** é um aplicativo desenvolvido para auxiliar na gestão de rotinas, treinos e dieta. Ele foi projetado para fornecer uma experiência personalizada aos usuários, permitindo que eles criem e acompanhem suas rotinas de exercícios e planos alimentares de forma eficiente.

### Tecnologias Utilizadas

O aplicativo **IntimaLearn** foi desenvolvido utilizando as seguintes tecnologias:

* Android Studio: A plataforma de desenvolvimento integrado (IDE) Android Studio foi utilizada para criar a interface do usuário do aplicativo. Ela oferece uma ampla gama de recursos e ferramentas para o desenvolvimento de aplicativos Android, incluindo suporte completo ao desenvolvimento em Java.

* Java: A linguagem de programação Java foi escolhida para o desenvolvimento do aplicativo Android. Ela é amplamente utilizada na comunidade de desenvolvimento Android e oferece uma sintaxe robusta e poderosa, além de uma vasta biblioteca de classes e recursos.

* Node.js: O backend do **IntimaLearn** foi desenvolvido utilizando o Node.js, um ambiente de tempo de execução JavaScript baseado no motor V8 do Google Chrome. O Node.js permite a construção de servidores web escaláveis e de alto desempenho, tornando-o uma escolha ideal para o desenvolvimento do backend do aplicativo.

* SQLite: O banco de dados SQLite foi escolhido como o sistema de gerenciamento de banco de dados para o **IntimaLearn**. O SQLite é uma biblioteca de software que fornece um banco de dados relacional embutido, permitindo o armazenamento e a recuperação eficientes de dados. Ele é amplamente utilizado em aplicativos móveis devido à sua simplicidade e eficiência.

Com essa combinação de tecnologias, o **IntimaLearn** oferece uma solução completa e robusta para a gestão de rotinas, treinos e dieta, proporcionando uma experiência fluida e personalizada aos usuários.

---

## Backend do IntimaLearn

Esse pedaço da documentação é dedicado a explicar o backend do **IntimaLearn**. O backend é responsável por gerenciar a lógica de negócios do aplicativo, incluindo a manipulação de dados, autenticação de usuários e comunicação com o banco de dados. Ele fornece uma interface de programação de aplicativos (API) para que o frontend do aplicativo possa interagir com o backend e acessar os recursos e funcionalidades oferecidos pelo aplicativo.

---

```js
var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var port = process.env.PORT || 3000;
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

//Banco de Dados
var sqlite3 = require("sqlite3").verbose();
var DBPATH = "appBD.db";
var db = new sqlite3.Database(DBPATH);
app.get("/tudo", function (req, res) {
  db.all(`SELECT * FROM intimalearn`, [], (err, rows) => {
    if (err) {
      res.send(err);
    }
    res.send(rows);
  });
});
```

Essas são as dependêcias do nosso projeto:

* **Express.js**: Uma framework web rápida, flexível e minimalista para Node.js. Permite criar APIs e aplicativos web de forma simplificada, oferecendo uma variedade de recursos para lidar com solicitações HTTP, roteamento, middleware e muito mais.
* **SQLite3**: Um módulo Node.js que fornece uma interface para interagir com bancos de dados SQLite. O SQLite é um banco de dados SQL embutido e amplamente utilizado, conhecido por sua facilidade de uso, eficiência e suporte à maioria dos recursos do SQL padrão.
  
---

```js
app.post("/cadastrarUsuario", function (req, res) {
  var nome = req.body.nome;
  var email = req.body.email;
  var senha = req.body.senha;

  db.get(
    "SELECT * FROM intimalearn WHERE email = ?",
    [email],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao verificar email");
      } else {
        if (row) {
          res.status(400).send("Este email já está registrado");
        } else {
          var sql =
            "INSERT INTO intimalearn (nome, email, senha) VALUES (?, ?, ?)";
          db.run(sql, [nome, email, senha], function (err) {
            if (err) {
              res.status(500).send("Erro ao criar usuário");
            } else {
              res.status(201).send("Usuário registrado com sucesso");
            }
          });
        }
      }
    },
  );
});
```

Este trecho de código adiciona uma nova rota POST em `/cadastrarUsuario` para lidar com o registro de novos usuários. Aqui está uma descrição do que o código faz:
**
* **Rota POST `/cadastrarUsuario`**: Esta rota é usada para registrar novos usuários no sistema.

* **Extração de dados do corpo da solicitação**: O código utiliza destructuring para extrair os campos `nome`, `email` e `senha` do corpo da solicitação (`req.body`).

* **Validação dos campos obrigatórios**: Verifica se os campos `nome`, `email` e `senha` foram fornecidos no corpo da solicitação. Se algum deles estiver faltando, retorna um status no aplicativo indicando que o campo está pendente de preenchimento.

* **Verificação de duplicidade de email**: Realiza uma consulta ao banco de dados para verificar se já existe um usuário com o mesmo email fornecido. Se encontrar um usuário com o mesmo email, retorna um status de erro 400 indicando que o email já está cadastrado.

* **Inserção do novo usuário no banco de dados**: Se o email não estiver duplicado, o código executa uma instrução SQL para inserir o novo usuário na tabela `intimalearn` do banco de dados, incluindo o `nome`, `email` e `senha` e outros campos que ja são gerados com o valor 0 como, pontuação e premiações. Se ocorrer algum erro durante a inserção, retorna um status de erro 500 com uma mensagem indicando o erro. Caso contrário, retorna um status de sucesso 201 com uma mensagem de sucesso.

Este trecho de código é responsável por adicionar a funcionalidade de registro de novos usuários ao seu aplicativo.

---

```js
app.post("/login", function (req, res) {
  var email = req.body.email;
  var senha = req.body.senha;

  db.get(
    "SELECT * FROM intimalearn WHERE email = ? AND senha = ?",
    [email, senha],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao autenticar usuário");
      } else {
        if (row) {
          res.status(200).send("Login bem-sucedido");
        } else {
          res.status(401).send("Usuário ou senha incorretos");
        }
      }
    },
  );
});
```

Este trecho de código adiciona uma nova rota POST em `/login` para lidar com o processo de autenticação dos usuários. Aqui está uma descrição do que o código faz:

* **Rota POST `/login`**: Esta rota é usada para autenticar os usuários no sistema.

* **Extração de dados do corpo da solicitação**: O código utiliza destructuring para extrair os campos `email` e `senha` do corpo da solicitação (`req.body`).

**Validação dos campos obrigatórios**: Verifica se os campos `nome`, `email` e `senha` foram fornecidos no corpo da solicitação. Se algum deles estiver faltando, retorna um status no aplicativo indicando que o campo está pendente de preenchimento.

* **Verificação das credenciais de login**: Realiza uma consulta ao banco de dados para verificar se existe um usuário com o email e senha fornecidos. Se não encontrar nenhum usuário correspondente, retorna um status de erro 500 indicando credenciais inválidas. Caso tenha mas seja inserido indevidamente retorna uma mensagem de usuário ou senha incorretos com o código 401.

* **Resposta de sucesso**: Se as credenciais estiverem corretas e corresponderem a um usuário existente, retorna um status de sucesso 200 com uma mensagem indicando que o login foi bem-sucedido.

Este trecho de código é responsável por adicionar a funcionalidade de autenticação de usuários ao seu aplicativo, permitindo que eles façam login fornecendo seu email e senha.

---

```js
app.delete("/deletarUsuario", function (req, res) {
  var email = req.body.email;
  var senha = req.body.senha;

  // Verificar se o email e a senha foram fornecidos
  if (!email || !senha) {
    return res
      .status(400)
      .send("Email e senha são obrigatórios para deletar a conta.");
  }
  db.get(
    "SELECT * FROM intimalearn WHERE email = ? AND senha = ?",
    [email, senha],
    function (err, row) {
      if (err) {
        return res.status(500).send("Erro ao consultar o banco de dados.");
      } else {
        if (!row) {
          // Usuário não encontrado ou credenciais incorretas
          return res.status(401).send("Email ou senha incorretos.");
        } else {
          // Deletar o usuário do banco de dados
          var sql = "DELETE FROM intimalearn WHERE email = ?";
          db.run(sql, [email], function (err) {
            if (err) {
              return res.status(500).send("Erro ao deletar a conta.");
            } else {
              return res.status(200).send("Conta deletada com sucesso.");
            }
          });
        }
      }
    },
  );
});

```
Este trecho de código adiciona uma nova rota DELETE em `/deletarUsuario` para permitir a exclusão de usuários do sistema. Aqui está uma descrição do que o código faz:

* **Rota DELETE `/deletarUsuario`**: Esta rota é usada para excluir um usuário do sistema.

* **Extração de dados do corpo da solicitação**: O código utiliza destructuring para extrair os campos `email` e `senha` do corpo da solicitação (`req.body`).

* **Validação dos campos obrigatórios**: Verifica se os campos `email` e `senha` foram fornecidos no corpo da solicitação. Se algum deles estiver faltando, retorna um status de erro 500 com uma mensagem no aplicativo dizendo que os campos são obrigatórios.

* **Exclusão do usuário do banco de dados**: Executa uma instrução SQL para excluir o usuário da tabela `intimalearn` onde o email e senha correspondem aos fornecidos. Se ocorrer algum erro durante a exclusão, retorna um status de erro 500 com uma mensagem JSON indicando o erro.

* **Verificação do resultado da exclusão**: Verifica se algum usuário foi afetado pela operação de exclusão. Se nenhum usuário for encontrado com as credenciais fornecidas, retorna um status de erro 401 indicando que o usuário não foi encontrado ou a senha está incorreta.

* **Resposta de sucesso**: Se a exclusão for bem-sucedida e pelo menos um usuário for afetado, retorna um status de sucesso 200 com uma mensagem indicando que o usuário foi deletado com sucesso.

Este trecho de código é responsável por adicionar a funcionalidade de exclusão de usuários ao seu aplicativo, permitindo que eles sejam removidos do sistema fornecendo seu email e senha.

---

```js
// Rota para obter o nome do usuário com base no email
app.post("/obterNomeUsuario", function (req, res) {
  var email = req.body.email;
  db.get(
    "SELECT nome FROM intimalearn WHERE email = ?",
    [email],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao obter o nome do usuário");
      } else {
        if (row) {
          // Usuário encontrado, enviar o nome do usuário como resposta
          res.status(200).send(row.nome);
        } else {
          // Usuário não encontrado
          res.status(404).send("Usuário não encontrado");
        }
      }
    },
  );
});
```

Este trecho de código adiciona uma nova rota POST em `/obterNomeUsuario` para buscar informações de um usuário com base no seu email. Aqui está uma descrição do que o código faz:

* **Rota POST `/obterNomeUsuario`**: Esta rota é utilizada para buscar informações de um usuário com base no email fornecido.

* **Extração de dados do corpo da solicitação**: O código extrai o campo `email` do corpo da solicitação (`req.body`).

* **Validação do campo obrigatório**: Verifica se o campo `email` foi fornecido no corpo da solicitação. Se não for fornecido, retorna um status de erro 500 com uma indicando que o email é obrigatório para buscar o usuário.

* **Busca das informações do usuário no banco de dados**: Executa uma consulta SQL para selecionar o nome da tabela `intimalearn` onde o email corresponde ao fornecido, pois, será usado para exibir o nome no aplicativo na aba de perfil. 

* **Verificação do resultado da consulta**: Verifica se a consulta retornou algum resultado. Se não retornar nenhum resultado, significa que o usuário não foi encontrado e retorna um status de erro 404 com uma mensagem indicando que o usuário não foi encontrado.

* **Resposta com as informações do usuário**: Se a consulta retornar resultados, envia esses resultados como uma resposta que contém o nome.

Este trecho de código é responsável por adicionar a funcionalidade de buscar informações de um usuário com base no seu email ao seu aplicativo, para que seja exibido na aba perfil.

```js
app.post("/registrarPontuacao", (req, res) => {
  const { email, senha, pontuacao } = req.body;

  // Atualiza a pontuação do usuário no banco de dados ou insere um novo registro se o usuário não existir
  db.run(
    "INSERT OR REPLACE INTO intimalearn (email, senha, pontuacao) VALUES (?, ?, ?)",
    [email, senha, pontuacao],
    function (err) {
      if (err) {
        console.error("Erro ao registrar a pontuação do usuário:", err.message);
        res.status(500).send("Erro ao registrar a pontuação do usuário");
      } else {
        console.log(
          `Pontuação do usuário (${email}) registrada como ${pontuacao}`,
        );
        res.status(200).send("Pontuação do usuário registrada com sucesso");
      }
    },
  );
});
```

Este trecho de código adiciona uma nova rota POST em `/registrarPontuacao` para salvar informações relacionadas a pontuação do úsuario em relação aos quizzes etc.

* **Extração de dados do corpo da solicitação**: O código extrai o campo `email`,`senha``pontuacao` do corpo da solicitação (`req.body`).

* **Busca das informações do usuário no banco de dados**: Executa uma consulta SQL para selecionar o nome da tabela `intimalearn` onde o email, senha e pontuacao corresponde ao fornecido, pois, será usado para incrementar com a nova pontuação.

* **Resposta com as informações do usuário**: Se a consulta retornar resultados, envia esses resultados substituindo os já existentes.

* **Verificação do resultado da consulta**: Verifica se a consulta retornou algum resultado. Se não retornar nenhum resultado, significa que o usuário não foi encontrado e retorna um status de erro 500 com uma mensagem de erro.


Este trecho de código é responsável por adicionar a funcionalidade de inserir a pontuação do úsuario.
```

```
---

## Frontend do IntimaLearn

Este pedaço da documentação é dedicado a explicar o frontend do **IntimaLearn**. O frontend é responsável por fornecer uma interface de usuário interativa e amigável para os usuários do aplicativo, permitindo que eles visualizem, interajam e gerenciem suas rotinas de exercícios e planos alimentares de forma eficiente.

Atualmente, temos seis telas principais, sendo, tela inicial com animação, registro/login, menu principal, trilha com quizzes e perguntas e perfil.

---

## Tela Splash
Essa é a primeira tela que o usuário vê ao abrir o aplicativo. Nela, o usuário verá uma animação da logo que foi realizada, junto de alguns pontos importantes para a nossa essência.
<div style="display: flex; justify-content: center; align-items: center; height:60vw;">
    <img src="./Telas do Aplicativo/tela_0_splash.png" alt="Descrição da imagem">
</div>  

### Login e Registro

Essa é a segunda tela que o usuário vê ao abrir o aplicativo. Nela, o usuário pode fazer login com seu email e senha, ou se registrar como um novo usuário. Se o usuário ainda não tiver uma conta, ele pode clicar no botão "Cadastrar-se" para ser redirecionado para a tela de registro.

E essas são as duas telas de registro e login:

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_1_login.png" alt="Descrição da imagem">
    <img src="./Telas do Aplicativo/tela_2_registro.png" alt="Descrição da imagem">
</div>


Ao inserir os dados, o aplicativo faz uma requisição POST para o backend, utilizando uma função `Asyncrona`, temos o seguinte código para acessar as rotas de login e registro:

```java
    public void fazerLogin(View view) {
        // Obtenha os valores dos EditTexts
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        // Verifique se os campos não estão vazios
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }
        fazerLoginNoServidor(email, senha);
    }

    // Método para fazer login no servidor
    @SuppressLint("StaticFieldLeak")
    private void fazerLoginNoServidor(String email, String senha) {
        String url = "https://l9sc8q-3000.csb.app/login";
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", email);
            jsonParams.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Void, String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonParams.toString().getBytes());

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();
                        Log.d("Login", "Response: " + response.toString());

                        if (response.toString().contains("Login bem-sucedido")) {
                            String nomeUsuario = obterNomeUsuario(email);
                            salvarNomeUsuario(nomeUsuario);

                            return nomeUsuario;
                        } else {
                            return null;
                        }
                    } else {
                        Log.e("Login", "Erro na solicitação: Código " + responseCode);
                        return null;
                    }
                } catch (@SuppressLint("StaticFieldLeak") IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String nomeUsuario) {
                if (nomeUsuario != null) {
                    Toast.makeText(LoginActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

```
```java
    private void registrarUsuarioNoServidor(String nome, String email, String senha) {
        String url = "https://l9sc8q-3000.csb.app/cadastrarUsuario"; // Atualize com o endereço do seu servidor
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("nome", nome);
            jsonParams.put("email", email);
            jsonParams.put("senha", senha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(jsonParams.toString().getBytes());

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_CREATED) {
                        runOnUiThread(() -> {
                            Toast.makeText(RegisterActivity.this, "Usuário registrado com sucesso!", Toast.LENGTH_SHORT).show();
                            editTextNome.setText("");
                            editTextEmail.setText("");
                            editTextSenha.setText("");
                        });
                    } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                        runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Erro: Este email já está registrado.", Toast.LENGTH_SHORT).show());
                    } else {
                        runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Erro ao registrar usuário", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "Erro na solicitação: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                }
                return null;
            }
        }.execute();
    }
```

Para fazer a requisição, utilizamos o seguinte código, que basicamente cria um `HashMap` com os dados capturados no corpo da tela, e faz uma requisição com os dados:

```java
        public void fazerLogin(View view) {
            String email = editTextEmail.getText().toString();
            String senha = editTextSenha.getText().toString();
            fazerLoginNoServidor(email, senha);
        }
```

O servidor retorna uma resposta, que é tratada no seguinte código:

```java
        if (email.isEmpty() || senha.isEmpty()) {
              Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
              return;
          }
```

O tratamento dessas informações é feito no BackEnd, que já está explicado acima. Caso o servidor retorne que deu tudo certo, executa:

```java
            new AsyncTask<Void, Void, String>() {
                @SuppressLint("StaticFieldLeak")
                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Content-Type", "application/json");
                        connection.setDoOutput(true);
                        connection.getOutputStream().write(jsonParams.toString().getBytes());

                        int responseCode = connection.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            StringBuilder response = new StringBuilder();
                            String line;
                            while ((line = reader.readLine()) != null) {
                                response.append(line);
                            }
                            reader.close();
                            Log.d("Login", "Response: " + response.toString());

                            if (response.toString().contains("Login bem-sucedido")) {
                                String nomeUsuario = obterNomeUsuario(email);
                                salvarNomeUsuario(nomeUsuario);

                                return nomeUsuario;
                            } else {
                                return null;
                            }
                        } else {
                            Log.e("Login", "Erro na solicitação: Código " + responseCode);
                            return null;
                        }
                    } catch (@SuppressLint("StaticFieldLeak") IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(String nomeUsuario) {
                    if (nomeUsuario != null) {
                        Toast.makeText(LoginActivity.this, "Login bem-sucedido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
                    }
                }
            }.execute();
```

Esse código acima resolve o login, porém o registro é bem parecido.

---

### Menu Principal

Ao entrar nessa tela, o usuário encontra com a tela principal, onde poderá acessar a trilha e demais informações

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_3_principal.png" alt="Descrição da imagem">
</div>

---

### Trilha

Ao clicar no icone de um circulo, você é direcionado para uma trilha que haverá quizzes de forma intuitiva com o úsuario para que ele possa testar seus conhecimentos quando se trata de saúde sexual.

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_04_quiz_01.png" alt="Descrição da imagem">
</div>

Ao úsuario selecionar uma resposta, caso esteja correto ele ganhara 5 pontos que são enviados para o banco de dados através do serviço `registrarPontuacao` caso esteja incorreto, não pode selecionar a resposta correta, somente caso tente novamente.

O úsuario pode clicar no botão de próxima pergunta para ser direcionado a próxima pergunta.

Chegando ao frim da trilha aparecerá uma tela que mostra que o úsuario conclui a ideia é que apareça os pontos que o úsuario fez nesta mesma tela, mas está em desenvolvimento,

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_5_quiz_01_final.png" alt="Descrição da imagem">
</div>


---

### Perfil

Essa é a última tela que temos até o momento. Nela, o usuário poderá verificar seu nome, pontuação, prêmios e deletar a sua conta.

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_6_perfil.png" alt="Descrição da imagem">
</div>

Ao inserir o e-mail e senha e clicar no botão `Deletar conta`, abre este AlertDialog:

<div style="display: flex; justify-content: center; align-items: center; height: 60vw;">
    <img src="./Telas do Aplicativo/tela_6_perfil_01.png" alt="Descrição da imagem">
</div>

Após inserir os dados, o app faz uma requisição para o backend, que deleta o usuário do banco de dados, e o redireciona para a tela de login.