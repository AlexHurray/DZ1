package ru.ermolaenkoalex.dz1;

import com.google.gson.*;

import java.lang.reflect.Type;

class PeopleRecordSerializer implements JsonDeserializer<PeopleRecord> {
    private static final String JSON_NAME_FIELD = "fname";
    private static final String JSON_SURNAME_FIELD = "lname";
    private static final String JSON_PATRONYMIC_FIELD = "patronymic";
    private static final String JSON_GENDER_FIELD = "gender";
    private static final String JSON_POSTCODE_FIELD = "postcode";
    private static final String JSON_TOWN_FIELD = "city";
    private static final String JSON_STREET_FIELD = "street";
    private static final String JSON_HOUSE_FIELD = "house";
    private static final String JSON_APARTMENT_FIELD = "apartment";
    private static final String JSON_GENDER_MALE_VAL = "m";

    @Override
    public PeopleRecord deserialize(JsonElement json,
                                    Type typeOfT,
                                    JsonDeserializationContext context)
            throws JsonParseException {
        PeopleRecord record = null;
        if (json.isJsonObject()) {
            JsonObject jobject = json.getAsJsonObject();

            record = new PeopleRecord(jobject.get(JSON_NAME_FIELD).getAsString(),
                    jobject.get(JSON_SURNAME_FIELD).getAsString(),
                    jobject.get(JSON_PATRONYMIC_FIELD).getAsString(),
                    jobject.get(JSON_GENDER_FIELD).getAsString().equals(JSON_GENDER_MALE_VAL),
                    jobject.get(JSON_POSTCODE_FIELD).getAsInt(),
                    jobject.get(JSON_TOWN_FIELD).getAsString(),
                    jobject.get(JSON_STREET_FIELD).getAsString(),
                    jobject.get(JSON_HOUSE_FIELD).getAsInt(),
                    jobject.get(JSON_APARTMENT_FIELD).getAsInt());
        }
        return record;
    }
}
