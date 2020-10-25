package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MenuPage {

    public SelenideElement verificarUsuarioLogado() {

        return $(".alert-success");
    }

    public MenuPage selecionarMenuConta() {
        $(".dropdown-toggle").click();
        return this;
    }

    public MenuPage selecionarMenuAdicionar() {
        $(byLinkText("Adicionar")).click();
        return this;
    }
    public MenuPage selecionarMenuLista() {
        $(byLinkText("Listar")).click();
        return this;
    }


}
