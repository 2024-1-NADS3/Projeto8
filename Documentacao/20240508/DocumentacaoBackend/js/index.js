/**
 * Importação das bibliotecas e configuração do aplicativo Express.
 */
var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var port = process.env.PORT || 3000;
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

/**
 * Configuração do banco de dados SQLite.
 */
var sqlite3 = require("sqlite3").verbose();
var DBPATH = "appBD.db";
var db = new sqlite3.Database(DBPATH);

/**
 * Rota GET para retornar todos os registros da tabela 'intimalearn'.
 */
app.get("/tudo", function (req, res) {
  db.all(`SELECT * FROM intimalearn`, [], (err, rows) => {
    if (err) {
      res.send(err);
    }
    res.send(rows);
  });
});

/**
 * Criação da tabela 'intimalearn', se ainda não existir.
 */
db.serialize(function () {
  db.run(
    `CREATE TABLE IF NOT EXISTS intimalearn (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      nome TEXT,
      email TEXT UNIQUE,
      senha TEXT,
      pontuacao INTEGER DEFAULT 0,
      premiacoes TEXT DEFAULT ''
    )`,
    function (err) {
      if (err) {
        console.error("Erro ao criar tabela:", err.message);
      }
    },
  );
});

/**
 * Rota GET para verificar se o servidor está funcionando.
 */
app.get("/", function (req, res) {
  res.send("Servidor funcionando!");
});

/**
 * Rota POST para obter o nome do usuário com base no email.
 */
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
          res.status(200).send(row.nome);
        } else {
          res.status(404).send("Usuário não encontrado");
        }
      }
    },
  );
});

/**
 * Rota POST para obter a pontuação do usuário com base no email.
 */
app.post("/obterPontuacaoUsuario", function (req, res) {
  var email = req.body.email;
  db.get(
    "SELECT pontuacao FROM intimalearn WHERE email = ?",
    [email],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao obter a pontuação do usuário");
      } else {
        if (row) {
          res.status(200).send(row.pontuacao.toString());
        } else {
          res.status(404).send("Usuário não encontrado");
        }
      }
    },
  );
});

/**
 * Rota POST para cadastrar um novo usuário.
 */
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

/**
 * Rota POST para autenticar um usuário.
 */
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

/**
 * Rota DELETE para deletar a conta de um usuário.
 */
app.delete("/deletarUsuario", function (req, res) {
  var email = req.body.email;
  var senha = req.body.senha;

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
          return res.status(401).send("Email ou senha incorretos.");
        } else {
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

/**
 * Rota POST para registrar a pontuação do usuário.
 */
app.post("/enviarPontos", (req, res) => {
  const { email, senha, pontuacao } = req.body;

  db.get(
    "SELECT * FROM intimalearn WHERE email = ? AND senha = ?",
    [email, senha],
    function (err, row) {
      if (err) {
        console.error("Erro ao verificar usuário:", err.message);
        res.status(500).send("Erro ao verificar usuário");
      } else {
        if (row) {
          db.run(
            "UPDATE intimalearn SET pontuacao = pontuacao + ? WHERE email = ? AND senha = ?",
            [pontuacao, email, senha],
            function (err) {
              if (err) {
                console.error(
                  "Erro ao atualizar a pontuação do usuário:",
                  err.message,
                );
                res
                  .status(500)
                  .send("Erro ao atualizar a pontuação do usuário");
              } else {
                console.log(
                  `Pontuação do usuário (${email}) atualizada para ${row.pontuacao + pontuacao}`,
                );
                res
                  .status(200)
                  .send("Pontuação do usuário atualizada com sucesso");
              }
            },
          );
        } else {
          res.status(404).send("Usuário não encontrado");
        }
      }
    },
  );
});

// Iniciar o servidor na porta especificada
app.listen(port);
