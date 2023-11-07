package org.example.utilits;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import io.qameta.allure.model.TestResultContainer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

public class ExcelConverter {

    public static void parseExcelDataToAllureReport() {

        String filePath = "src/main/resources/asit-testFile.xlsx";
        try {
            FileInputStream file = new FileInputStream(new File(filePath));

            // Создание рабочей книги
            Workbook workbook = new XSSFWorkbook(file);

            // Получение первого листа из файла xlsx
            Sheet sheet = workbook.getSheetAt(0);
            TestResultContainer testResultContainer = new TestResultContainer();
            testResultContainer.setName("Test Cases from Excel");

            // Перебор строк в листе
            for (Row row : sheet) {

                Cell testCaseNameCell = row.getCell(0);
                Cell testCaseDescriptionCell = row.getCell(9);

                if (testCaseNameCell != null && testCaseDescriptionCell != null) {
                    String testCaseName = testCaseNameCell.getStringCellValue();
                    String testCaseDescription = testCaseDescriptionCell.getStringCellValue();
                    setStatusForTestCase(testCaseDescription);
                }
            }

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setStatusForTestCase(String description) {
        String uuid = UUID.randomUUID().toString();
        AllureLifecycle lifecycle = Allure.getLifecycle();
        TestResult testResult = new TestResult();
        testResult.setUuid(uuid);
        testResult.setHistoryId(UUID.randomUUID().toString());
        testResult.setName(uuid);
        testResult.setDescription(description);
        lifecycle.scheduleTestCase(testResult);
        lifecycle.startTestCase(uuid);
        lifecycle.updateTestCase(result -> result.setStatus(description.equals("PASS") ? Status.PASSED : Status.FAILED));
        lifecycle.stopTestCase(uuid);
        lifecycle.writeTestCase(uuid);
    }
}
