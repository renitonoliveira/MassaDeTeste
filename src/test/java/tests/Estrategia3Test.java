package tests;

import Base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import support.MassaDAOImpl;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Condition.text;


public class Estrategia3Test extends BaseTest {

    @BeforeMethod
    public void incializa() {
        login.open().with("admin@admin.il", "admin");
        menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));

    }

    @Test
    public void inserir() throws SQLException, ClassNotFoundException {
        String conta = fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon();
        menu.selecionarMenuConta().selecionarMenuAdicionar();
        estrategia3.incluirConta(fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon())
                .verificarMensagemSucesso().shouldHave(text("Conta adicionada com sucesso!"));
        new MassaDAOImpl().inserirMassa(GeradorMassasTest.CHAVE_CONTA, conta);
    }

    @Test
    public void consultar() throws SQLException, ClassNotFoundException{
        String conta = new MassaDAOImpl().obterMassa(GeradorMassasTest.CHAVE_CONTA);
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia3.selecionarConta(conta).verificarNome().contains(conta);
        new MassaDAOImpl().inserirMassa(GeradorMassasTest.CHAVE_CONTA, conta);
    }

    @Test
    public void alterar() throws SQLException, ClassNotFoundException{
        String conta = new MassaDAOImpl().obterMassa(GeradorMassasTest.CHAVE_CONTA);
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia2.selecionarConta(conta).realizarAlteracao(conta+" Alterada").verificarMensagemSucesso().shouldHave(text("Conta alterada com sucesso!"));
    }

    @Test
    public void excluir() throws SQLException, ClassNotFoundException{
        String conta = new MassaDAOImpl().obterMassa(GeradorMassasTest.CHAVE_CONTA);
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia3.realizarExclusao(conta).verificarMensagemSucesso().shouldHave(text("Conta removida com sucesso!"));
    }
}
