package tests;

import Base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import support.MassaDAOImpl;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.text;

public class GeradorMassasTest extends BaseTest {

    public static final String CHAVE_CONTA = "CONTA";
    private static final Integer ESTOQUE_MINIMO = 5;
    private static String TIPO_MONITORADO = GeradorMassasTest.CHAVE_CONTA;

   // public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Executa o teste acima varias vezes. Utilizado para gerar massas de testes. Ele executa a automação e os dados incluido no sistema, guarda no banco local.
		/*
			GeradorMassasTest gerador = new GeradorMassasTest();
			for(int i = 0; i < 5; i++) {
				gerador.gerarConta();
			}
		*/
        //Para testes manuais, pode-se utlizar o trecho abaixo para obter as massas que não foram utilizadas e realizar o teste no sistema.
       // String massa = new MassaDAOImpl().obterMassa(CHAVE_CONTA);
        //System.out.println(massa);
    //}

    @Test
    public static void verificarEstoque() throws ClassNotFoundException, SQLException, InterruptedException {

        MassaDAOImpl dao = new MassaDAOImpl();


        while (true) {
            int estoqueAtual = dao.obterEstoque(TIPO_MONITORADO);
            System.out.println("-- Estoque Atual: "+estoqueAtual+" --");
            if (estoqueAtual < ESTOQUE_MINIMO) {
                System.out.println("-- Estoque abaixo do valor esperado: 5 -- Criando estoque..");


                login.open().with("admin@admin.il", "admin");
                menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));
                menu.selecionarMenuConta().selecionarMenuAdicionar();
                Faker fake = new Faker();
                String conta = fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon();
                gerador.incluirConta(fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon());

                new MassaDAOImpl().inserirMassa(CHAVE_CONTA, conta);
            } else {
                Thread.sleep(10000);
            }
        }

    }

        @Test
        public static void criarVariasMassas() throws ClassNotFoundException, SQLException{
            for(int i = 0; i < 5; i++) {
                login.open().with("admin@admin.il", "admin");
                menu.verificarUsuarioLogado().shouldHave(text("Bem vindo, admin!"));
                menu.selecionarMenuConta().selecionarMenuAdicionar();
                Faker fake = new Faker();
                String conta = fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon();
                gerador.incluirConta(fake.gameOfThrones().character() + " " + fake.gameOfThrones().dragon());
                new MassaDAOImpl().inserirMassa(CHAVE_CONTA, conta);
            }
        }

}
