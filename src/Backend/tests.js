const assert = require("assert");
const request = require("supertest");
const app = require("./index"); // Substitua 'seu_arquivo_servidor' pelo nome do seu arquivo de servidor

describe("Testes da API", function () {
  // Teste para verificar se o servidor está funcionando
  it('Deve retornar "Servidor funcionando!"', function (done) {
    request(app).get("/").expect(200).expect("Servidor funcionando!", done);
  });

  // Teste para verificar se a rota de obtenção do nome do usuário funciona corretamente
  it("Deve obter o nome do usuário pelo email", function (done) {
    request(app)
      .post("/obterNomeUsuario")
      .send({ email: "leocatedral1000@gmail.com" })
      .expect(200)
      .expect("Leonardo", done);
  });

  // Teste para verificar se a rota "/cadastrarUsuario" cadastra um novo usuário corretamente
  it("POST /cadastrarUsuario cadastra um novo usuário", async () => {
    const response = await request(app).post("/cadastrarUsuario").send({
      nome: "Novo Usuário",
      email: "novo@example.com",
      senha: "senha123",
    });
    expect(response.statusCode).toBe(201);
    expect(response.text).toBe("Usuário registrado com sucesso");
  });

  // Teste para verificar se a rota de exclusão de usuário funciona corretamente
  it("Deve excluir um usuário", function (done) {
    request(app)
      .delete("/deletarUsuario")
      .send({ email: "novo@example.com", senha: "senha123" })
      .expect(200, done);
  });
});
