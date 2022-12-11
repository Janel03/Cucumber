package utils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
public class ExcelReader {
    static Workbook book;
    static Sheet sheet;
    public static void openExcel(String filePath) throws IOException {
        try {
            FileInputStream fis=new FileInputStream(filePath);
            book=new XSSFWorkbook(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //this method will open the Excel worksheet
    public static void getSheet(String sheetName){
        sheet=book.getSheet(sheetName);
    }
    //opens the rows
    public static int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    //column count
    public static int getColsCount(int rowIndex){
        return sheet.getRow(rowIndex).getPhysicalNumberOfCells();
}
//cell data in string format
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }
    //this method will return list of maps with all the data from excel
    public static List<Map<String,String>> excelListIntoMap(String filePath,String sheetName) throws IOException {
        openExcel(filePath);
        getSheet(sheetName);

        List<Map<String,String>>listData=new ArrayList<>();
        //loops - outer loop always takes care of rows
        for (int row=1; row<getRowCount(); row++){
            //creating a map for every row
            Map<String,String>map=new LinkedHashMap<>();

            for (int col=0; col<getColsCount(row); col++){
                map.put(getCellData(0,col),getCellData(row,col));
            }
            listData.add(map);
        }
    return listData;
    }
}
