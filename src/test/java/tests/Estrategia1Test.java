package tests;

import Base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Condition.text;


public class Estrategia1Test extends BaseTest {

    @BeforeMethod
    public void incializa() throws IOException {
        login.open().with("admin@admin.il", "admin");
        menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));

    }

    @Test(priority = 1)
    public void teste_1_inserir() {
        menu.selecionarMenuConta().selecionarMenuAdicionar();
        estrategia1.incluirConta("Conta estrategia #1").verificarMensagemSucesso().shouldHave(text("Conta adicionada com sucesso!"));
    }

    @Test(priority = 2)
    public void teste_2_consultar() {
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia1.selecionarConta().verificarNome().contains("Conta estrategia #1");
    }

    @Test(priority = 3)
    public void teste_3_alterar() {
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia1.selecionarConta().realizarAlteracao("Conta estrategia #1 alterada").verificarMensagemSucesso().shouldHave(text("Conta alterada com sucesso!"));
    }

    @Test(priority = 4)
    public void teste_4_excluir() {
        menu.selecionarMenuConta().selecionarMenuLista();
        estrategia1.realizarExclusao().verificarMensagemSucesso().shouldHave(text("Conta removida com sucesso!"));
    }


}
