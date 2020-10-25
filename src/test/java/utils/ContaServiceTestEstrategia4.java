package utils;

import org.junit.Test;

import br.ce.wcaquino.dao.utils.ConnectionFactory;
import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;

import java.sql.Connection;

import org.junit.Assert;

public class ContaServiceTestEstrategia4 {
	
	
	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();
	
	
	
	/*
	@Before
	public void inserirConta() throws Exception {
		//Criando a conxão com o banco da Aplicação. Para realizar a manipulação desses dados
		Connection conn = ConnectionFactory.getConnection();
		//conn.createStatement().executeUpdate("DELETE FROM transacoes");
		conn.createStatement().executeUpdate("DELETE FROM contas");
		conn.createStatement().executeUpdate("DELETE FROM usuarios");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id,nome,email,senha) VALUES (1, 'Usuario de contole', 'usuario@email.com', 'pass')");
	}
		*/
	
	@Test
	public void testeInserir() throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM contas where NOME = 'Conta para inclusão'");
		conn.createStatement().executeUpdate("DELETE FROM usuarios where NOME = 'Usuario de contole'");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id,nome,email,senha) VALUES (1, 'Usuario de contole', 'usuario@email.com', 'pass')");
		//Criando a conta e recuperando o usuario que foi criado
		Usuario usuario = userService.findById(1L);
		Conta conta = new Conta("Conta para inclusão",usuario);
		Conta contaSalva = service.salvar(conta);
		Assert.assertNotNull(contaSalva.getId());
		userService.printAll();
		service.printAll();		
	}
	
	@Test
	public void testeConsulta() throws Exception {
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM contas where id = 2");
		conn.createStatement().executeUpdate("DELETE FROM usuarios where NOME = 'Usuario de controle consulta'");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id,nome,email,senha) VALUES (2, 'Usuario de controle consulta', 'consulta@email.com', 'pass')");
		ConnectionFactory.getConnection().createStatement().executeUpdate(
			"INSERT INTO contas (id, nome, usuario_id) VALUES (2, 'Contas de controle consulta', 2)");
		Conta contaBuscada = service.findById(2L);
		Assert.assertEquals("Usuario de controle consulta", contaBuscada.getNome() );
		service.printAll();	
	}
	@Test
	public void testeAtualiza() throws Exception{
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM contas where id = 3");
		conn.createStatement().executeUpdate("DELETE FROM usuarios where NOME = 'Usuario de controle alteração'");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id,nome,email,senha) VALUES (3, 'Usuario de controle alteração', 'alteração@email.com', 'pass')");
		
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				"INSERT INTO contas (id, nome, usuario_id) VALUES (3, 'Conta para alteração', 3)");
		Conta contaTeste = service.findByName("Conta para alteração");
		contaTeste.setNome("Conta para alteração alterada");
		Conta contaAlterada = service.salvar(contaTeste);
		Assert.assertEquals("Conta para alteração alterada", contaAlterada.getNome() );
		service.printAll();
	}
	@Test
	public void testeExcluir() throws Exception{
		Connection conn = ConnectionFactory.getConnection();
		conn.createStatement().executeUpdate("DELETE FROM contas where id = 4");
		conn.createStatement().executeUpdate("DELETE FROM usuarios where NOME = 'Usuario de controle exclusão'");
		conn.createStatement().executeUpdate("INSERT INTO usuarios (id,nome,email,senha) VALUES (4, 'Usuario de controle exclusão', 'exclusão@email.com', 'pass')");
		
		
		ConnectionFactory.getConnection().createStatement().executeUpdate(
				"INSERT INTO contas (id, nome, usuario_id) VALUES (4, 'Conta para exclusão', 4)");
		Conta contaTeste = service.findByName("Conta para exclusão");
		service.printAll();
		service.delete(contaTeste);		
		Conta contaBuscada = service.findById(contaTeste.getId());
		Assert.assertNull(contaBuscada);
		service.printAll();
	}
	
	
}
