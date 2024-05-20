/**
 * Importação das bibliotecas e configuração do aplicativo Express.
 */
var nodemailer = require("nodemailer");
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
            premiacoes TEXT DEFAULT '',
            resetToken TEXT,
            resetTokenExpires INTEGER
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
var crypto = require("crypto");

const AES_KEY = "MySecretKey12345"; // 16 characters for AES-128

function decrypt(text) {
  let decipher = crypto.createDecipheriv("aes-128-ecb", AES_KEY, null);
  let decrypted = decipher.update(text, "base64", "utf8");
  decrypted += decipher.final("utf8");
  return decrypted;
}

app.post("/cadastrarUsuario", function (req, res) {
  var nome = req.body.nome;
  var email = req.body.email;
  var encryptedSenha = req.body.senha;

  try {
    var senha = decrypt(encryptedSenha);
  } catch (error) {
    return res.status(500).send("Erro ao descriptografar a senha");
  }

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

function generateResetToken() {
  return crypto.randomBytes(20).toString("hex");
}

app.post("/esqueceuSenha", function (req, res) {
  var email = req.body.email;

  db.get(
    "SELECT * FROM intimalearn WHERE email = ?",
    [email],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao verificar email");
      } else if (!row) {
        res.status(404).send("Usuário não encontrado");
      } else {
        var resetToken = generateResetToken();
        var resetTokenExpires = Date.now() + 3600000; // 1 hour from now

        db.run(
          "UPDATE intimalearn SET resetToken = ?, resetTokenExpires = ? WHERE email = ?",
          [resetToken, resetTokenExpires, email],
          function (err) {
            if (err) {
              res.status(500).send("Erro ao gerar token de redefinição");
            } else {
              // Configure the email transport using the default SMTP transport and a GMail account
              var transporter = nodemailer.createTransport({
                host: "smtp.gmail.com",
                port: 465,
                secure: true,
                auth: {
                  user: "user",
                  pass: "password",
                },
              });

              var emailDestinatario = row.email; // Obtém o email do destinatário da linha do banco de dados
              var mailOptions = {
                from: "user",
                to: emailDestinatario,
                subject: "Redefinição de senha do Íntimalearn",
                text:
                  "Você está recebendo isto porque você (ou alguém) solicitou a redefinição da senha para sua conta.\n\n" +
                  "Por favor, cole este token abaixo em seu aplicativo na tela de alteração de aleração de senha para alterar a senha\n\n" +
                  resetToken +
                  "\n\n" +
                  "Se você não solicitou isto, por favor, ignore este email e sua senha permanecerá inalterada.\n",
              };

              transporter.sendMail(mailOptions, function (err) {
                if (err) {
                  res
                    .status(500)
                    .send("Erro ao enviar email de redefinição de senha");
                } else {
                  res
                    .status(200)
                    .send("Email de redefinição de senha enviado com sucesso");
                }
              });
            }
          },
        );
      }
    },
  );
});

app.post("/redefinirSenha", function (req, res) {
  var resetToken = req.body.token;
  var newPassword = req.body.senha;

  try {
    var encryptedSenha = decrypt(newPassword);
  } catch (error) {
    return res.status(500).send("Erro ao descriptografar a senha");
  }

  db.get(
    "SELECT * FROM intimalearn WHERE resetToken = ? AND resetTokenExpires > ?",
    [resetToken, Date.now()],
    function (err, row) {
      if (err) {
        res.status(500).send("Erro ao verificar token");
      } else if (!row) {
        res.status(400).send("Token inválido ou expirado");
      } else {
        db.run(
          "UPDATE intimalearn SET senha = ?, resetToken = NULL, resetTokenExpires = NULL WHERE resetToken = ?",
          [encryptedSenha, resetToken],
          function (err) {
            if (err) {
              res.status(500).send("Erro ao redefinir a senha");
            } else {
              res.status(200).send("Senha redefinida com sucesso");
            }
          },
        );
      }
    },
  );
});

// Iniciar o servidor na porta especificada
app.listen(port);
