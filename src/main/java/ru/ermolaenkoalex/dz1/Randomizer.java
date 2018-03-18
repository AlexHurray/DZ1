package ru.ermolaenkoalex.dz1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

class Randomizer {
    private static final String GET_METHOD = "GET";
    private static final int YEAR_MIN = 1950;
    private static final int YEAR_MAX = 1995;
    private static final int DAY_MIN = 1;
    private static final int DAY_MAX = 355;
    private static final String URL_PATH = "https://randus.org/api.php";
    private static final Gson gson = new GsonBuilder().registerTypeAdapter(PeopleRecord.class,
            new PeopleRecordSerializer()).create();

    public static int getRandomInteger(int start, int end){
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate getRandomDate(){
        return LocalDate.ofYearDay(getRandomInteger(YEAR_MIN, YEAR_MAX), getRandomInteger(DAY_MIN, DAY_MAX));
    }

    public static PeopleRecord getRandomPeopleRecord() throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(URL_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET_METHOD);

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            return gson.fromJson(rd, PeopleRecord.class);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}