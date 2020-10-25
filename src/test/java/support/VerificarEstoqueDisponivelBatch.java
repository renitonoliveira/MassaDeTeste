package support;



import java.sql.SQLException;

public class VerificarEstoqueDisponivelBatch {

    public static final String CHAVE_CONTA = "CONTA";

public static void main(String[] args) throws ClassNotFoundException, SQLException {

    //Para testes manuais, pode-se utlizar o trecho abaixo para obter as massas que não foram utilizadas e realizar o teste no sistema.
    String massa = new MassaDAOImpl().obterMassa(CHAVE_CONTA);
    System.out.println(massa);
    }

}
