package Base;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BasePage {


    public String planilha (int coluna, int linha, String arquivo) throws IOException {
        //Pega o caminho do diretorio ate no projeto.
        String executionPath = System.getProperty("user.dir");
        String target = executionPath + "\\src\\main\\resources\\Datapool\\"+ arquivo;



        FileInputStream file = new FileInputStream(new File(target));
        HSSFWorkbook workbook = new HSSFWorkbook(file);

        DataFormatter formatter = new DataFormatter();

        //recupera primeira planilha (aba)
        HSSFSheet sheet = workbook.getSheetAt(0);
        //pega o valor da primeira célula (A1)
        String campo = formatter.formatCellValue(sheet.getRow(linha).getCell(coluna));
        //fecha planilha e arquivo (não precisa fechar os dois)
        workbook.close();

        return campo;
    }


}
