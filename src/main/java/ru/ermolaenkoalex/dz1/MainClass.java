package ru.ermolaenkoalex.dz1;

import java.util.ArrayList;

public class MainClass {
    private static final int MIN_RECORDS = 10;
    private static final int MAX_RECORDS = 30;
    private static final String FILENAME_XLS = "Xls.xls";
    private static final String FILENAME_PDF = "Pdf.pdf";

    public static void main (String[] args) {
        int sizeOfArray = Randomizer.getRandomInteger(MIN_RECORDS, MAX_RECORDS);
        ArrayList<PeopleRecord> records = new ArrayList<PeopleRecord>();
        RecordsWriter writer;

        for (int i = 0; i < sizeOfArray; i++) {
            try {
                records.add(Randomizer.getRandomPeopleRecord());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        writer = new RecordsWriter(records);
        writer.write2Xls(FILENAME_XLS);
        writer.write2Pdf(FILENAME_PDF);
    }
}
