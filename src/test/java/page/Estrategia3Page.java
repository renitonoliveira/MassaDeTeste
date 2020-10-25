package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Estrategia3Page {



        public Estrategia3Page incluirConta(String nome){
            $("#nome").setValue(nome);
            $(".btn-primary").click();
            return this;
        }
        public SelenideElement verificarMensagemSucesso() {

            return $(".alert-success");
        }
        public Estrategia3Page selecionarConta(String conta){
            $(By.xpath("//td[contains(text(),'"+conta+"')]/..//a")).click();
            return this;
        }
        public String verificarNome(){

            return $("#nome").getAttribute("value");
        }
        public Estrategia3Page realizarAlteracao(String conta){
            $("#nome").setValue(conta);
            $(".btn-primary").click();
            return this;
        }
        public Estrategia3Page realizarExclusao(String conta){
            $(By.xpath("//td[contains(text(),'"+conta+"')]/..//a[2]")).click();
            return this;
        }
}
