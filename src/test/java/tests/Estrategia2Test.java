package tests;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;


public class Estrategia2Test extends BaseTest {

    @BeforeMethod
    public void incializa() throws IOException {
        login.open().with("admin@admin.il", "admin");
        menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));

    }

    @Test
    public void inserir() {
        menu.selecionarMenuConta().selecionarMenuAdicionar();
        estrategia2.incluirConta(fake.harryPotter().character()).verificarMensagemSucesso().shouldHave(text("Conta adicionada com sucesso!"));
    }

    @Test
    public void consultar() {
        String conta = inserirConta();
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia2.selecionarConta(conta).verificarNome().contains(conta);
    }

    @Test
    public void alterar() {
        String conta = inserirConta();
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia2.selecionarConta(conta).realizarAlteracao(conta+" Alterada").verificarMensagemSucesso().shouldHave(text("Conta alterada com sucesso!"));
    }

    @Test
    public void excluir() {
        String conta = inserirConta();
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia2.realizarExclusao(conta).verificarMensagemSucesso().shouldHave(text("Conta removida com sucesso!"));
    }

    public String inserirConta() {

        String registro = fake.harryPotter().character();

        menu.selecionarMenuConta().selecionarMenuAdicionar();
        estrategia2.incluirConta(registro);
        return registro;

    }
}
