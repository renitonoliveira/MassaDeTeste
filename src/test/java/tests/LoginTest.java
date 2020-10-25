package tests;

import static com.codeborne.selenide.Condition.text;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Base.BaseTest;

import java.io.IOException;

public class LoginTest extends BaseTest {
	
	 	@Test
	    public void deveLogarComSucesso(){
	            login.open().with("admin@admin.il","admin");
	            menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));
	    }

	    @AfterMethod
	    public void limparSessao() {
	        login.limparSessao();
	    }

}
