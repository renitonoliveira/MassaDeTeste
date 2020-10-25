package Base;

import static com.codeborne.selenide.Selenide.screenshot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.codeborne.selenide.Configuration;

import page.*;
import tests.GeradorMassasTest;


public class BaseTest {
	protected static LoginPage login = new LoginPage();
    protected static MenuPage menu = new MenuPage();
    protected static Estrategia1Page estrategia1 = new Estrategia1Page();
    protected static Estrategia2Page estrategia2 = new Estrategia2Page();
    protected static Estrategia3Page estrategia3 = new Estrategia3Page();
    protected static GeradorMassasPage gerador = new GeradorMassasPage();
    //protected static GeradorMassasTest geradorteste = new GeradorMassasTest();

    protected static Faker fake = new Faker();

    @BeforeMethod
    public void start() {

        Properties prop = new Properties();
        InputStream inputFile =getClass().getClassLoader().getResourceAsStream("config.properties");

        try{
            prop.load(inputFile);
        }catch (Exception ex){
            System.out.println("Deu ruim ao carregar o config.properties. Trace -> " + ex.getMessage());

        }

        Configuration.browser = prop.getProperty("browser");
        Configuration.baseUrl = prop.getProperty("url");
        Configuration.timeout = Long.parseLong(prop.getProperty("timeout"));
    }

    @AfterMethod
    public void finish() {
        //Tira print pelo selenide
        String print = screenshot("print");
        //Transforma a imagem em binÃ¡rio para anexar ao report  do Allure
        try {
            //Obter a imagem em mÃ©moria
            BufferedImage bimage = ImageIO.read(new File(print));
            // Saida da imagem em binÃ¡rio.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Preparar a saida da imagem em um array de byte, definindo o tipo para a imagem
            ImageIO.write(bimage, "png", baos);
            //Pega a imagem em binÃ¡rio e transforma em array de byte
            byte[] printFinal = baos.toByteArray();
            //anexa a imagem ao Allure para apresentar no relatÃ³rio.
            io.qameta.allure.Allure.addAttachment("Evidência", new ByteArrayInputStream(printFinal));

        } catch (Exception ex) {
            System.out.println("Deu erro ao anexar o print ao Allure : (. Trace =>" + ex.getMessage());
        }
    }
}
