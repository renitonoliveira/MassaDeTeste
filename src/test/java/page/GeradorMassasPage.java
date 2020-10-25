package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class GeradorMassasPage {



        public GeradorMassasPage incluirConta(String nome){
            $("#nome").setValue(nome);
            $(".btn-primary").click();
            return this;
        }

}
