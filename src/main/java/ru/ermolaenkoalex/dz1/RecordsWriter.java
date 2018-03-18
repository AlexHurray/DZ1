package ru.ermolaenkoalex.dz1;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

class RecordsWriter {
    private static final int[] COLUMN_WIDTHS = {70, 70, 70, 20, 20, 70, 100,
            70, 70, 70, 70, 100, 20, 20};

    private static final int FONT_SIZE = 8;
    private static final String FONT_PATH = "arial.ttf";

    private static final String COL_SURNAME          = "Фамилия";
    private static final String COL_NAME             = "Имя";
    private static final String COL_PATRONYMIC       = "Отчество";
    private static final String COL_AGE              = "Возраст";
    private static final String COL_GENDER           = "Пол";
    private static final String COL_DATE_OF_BIRTH    = "Дата рождения";
    private static final String COL_PLACE_OF_BIRTH   = "Место рождения";
    private static final String COL_POSTCODE         = "Почтовый индекс";
    private static final String COL_COUNTRY          = "Страна";
    private static final String COL_REGION           = "Область";
    private static final String COL_TOWN             = "Город";
    private static final String COL_STREET           = "Улица";
    private static final String COL_HOUSE_NUMBER     = "Дом";
    private static final String COL_APARTMENT_NUMBER = "Квартира";
    private static final String FILE_CREATED         = "Файл создан. Путь: ";
    private static final String FILE_NOT_CREATED     = "Файл не создан. Путь: ";
    private static final String FIRST_SHEET          = "FirstSheet";

    private static final String[] tableHeaderString = { COL_SURNAME,
            COL_NAME, COL_PATRONYMIC, COL_AGE, COL_GENDER,
            COL_DATE_OF_BIRTH, COL_PLACE_OF_BIRTH, COL_POSTCODE,
            COL_COUNTRY, COL_REGION, COL_TOWN, COL_STREET,
            COL_HOUSE_NUMBER, COL_APARTMENT_NUMBER };


    private ArrayList<PeopleRecord> records;

    RecordsWriter(ArrayList<PeopleRecord> records){
        this.records = records;
    }

    private void writeRecord2Xls(HSSFRow row, String[] tableString){
        for (int i = 0; i < tableString.length; i++ ) {
            row.createCell(i).setCellValue(tableString[i]);
        }
    }

    void write2Xls(String filepath){
        File newFile = new File(filepath);

        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(FIRST_SHEET);
            FileOutputStream fileOut = new FileOutputStream(newFile);
            HSSFRow rowhead = sheet.createRow((short)0);

            writeRecord2Xls(rowhead, tableHeaderString);

            for (int i = 0; i < records.size(); i++) {
                HSSFRow row = sheet.createRow((short) i + 1);
                writeRecord2Xls(row, records.get(i).getTableString());
            }

            workbook.write(fileOut);
            fileOut.close();
            System.out.println(FILE_CREATED + newFile.getAbsolutePath());

        } catch ( Exception ex ) {
            System.out.println(ex.getMessage());
            System.out.println(FILE_NOT_CREATED + newFile.getAbsolutePath());
        }
    }

    private void writeRecord2Pdf(PdfPTable table, Font font, String[] tableString){
        for (int i = 0; i < tableString.length; i++ ) {
            table.addCell(new Phrase(tableString[i], font));
        }
    }

    void write2Pdf(String filepath){
        Document document = null;
        File newFile = new File(filepath);
        try {
            document = new Document(PageSize.A3.rotate());
            Font font = FontFactory.getFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, FONT_SIZE);
            PdfPTable table;

            PdfWriter.getInstance(document, new FileOutputStream(newFile));
            document.open();
            table = new PdfPTable(tableHeaderString.length);
            table.setWidths(COLUMN_WIDTHS);

            writeRecord2Pdf(table, font, tableHeaderString);

            for(int i = 0; i < records.size(); i++){
                writeRecord2Pdf(table, font, records.get(i).getTableString());
            }

            document.add(table);
            System.out.println(FILE_CREATED + newFile.getAbsolutePath());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(FILE_NOT_CREATED + newFile.getAbsolutePath());
        }
        finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
