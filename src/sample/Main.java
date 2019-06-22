package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Workbook> wbList = new ArrayList();
    public static ArrayList<Row> rowsList = new ArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static String readFileName() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }

    public static void addWorkbooks(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            try (FileInputStream fis = new FileInputStream(readFileName())) {
                wbList.add(new XSSFWorkbook(fis));
            }
        }
    }

    public static void fillRowsList(String search) {
        if (!wbList.isEmpty()) {
            for (Workbook workbook : wbList) {
                for (Sheet sheet : workbook) {
                    for (Row row : sheet) {
                        for (Cell cell : row) {
                            if(cell != null){
                                if(cell.getCellType() == CellType.STRING){
                                    if(cell.toString().toLowerCase().contains(search.toLowerCase())){
                                        rowsList.add(row);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
