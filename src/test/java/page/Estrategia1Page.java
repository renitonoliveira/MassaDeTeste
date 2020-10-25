package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Estrategia1Page {

        public Estrategia1Page incluirConta(String nome){
            $("#nome").setValue(nome);
            $(".btn-primary").click();
            return this;
        }
        public SelenideElement verificarMensagemSucesso() {
            return $(".alert-success");
        }
        public Estrategia1Page selecionarConta(){
            $(By.xpath("//td[contains(text(),'Conta estrategia #1')]/..//a")).click();
            return this;
        }
        public String verificarNome(){
            return $("#nome").getAttribute("value");
        }
        public Estrategia1Page realizarAlteracao(String conta){
            $("#nome").clear();
            $("#nome").setValue(conta);
            $(".btn-primary").click();
            return this;
        }
        public Estrategia1Page realizarExclusao(){
            $(By.xpath("//td[contains(text(),'Conta estrategia #1')]/..//a[2]")).click();
            return this;
        }
}
