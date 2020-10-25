package page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import Base.BasePage;
import com.codeborne.selenide.Selenide;

import java.io.IOException;

public class LoginPage extends BasePage{
	
	 public LoginPage open() {
	        Selenide.open("/login");
	        return this;
	    }

	    public LoginPage with(String email, String senha) {
	        //$("input[name:name]")busca por tag(input)+atributo(name)+valor do atributo=(name)
	        $("#email").setValue(email);
	        //$("#id")busca pelo id html
	        $("#senha").setValue(senha);
	        //$(byText()) busca pelo texto no html
	        $(".btn-primary").click();
	        return this;
	    }
	    public void limparSessao(){
	        executeJavaScript("localStorage.clear();");
	    }

		public String datapool(int coluna, int linha)throws IOException {
		return planilha(coluna,linha, "teste.xls");
		}


}
