package ru.ermolaenkoalex.dz1;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class PeopleRecord {
    private static final String COUNTRY_VAL = "Россия";
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String MALE_VAL = "м";
    private static final String FEMALE_VAL = "ж";

    private String name;
    private String surname;
    private String patronymic;
    private LocalDate dateOfBirth;
    private boolean isMale;
    private String placeOfBirth;
    private int postcode;
    private String country;
    private String region;
    private String town;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    PeopleRecord(String name, String surname, String patronymic,
                  boolean isMale, int postcode, String town,
                  String street, int houseNumber,
                  int apartmentNumber){
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.isMale = isMale;
        this.placeOfBirth = town;
        this.postcode = postcode;
        this.country = COUNTRY_VAL;
        this.region = town;
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.dateOfBirth = Randomizer.getRandomDate();
    }

    private String getDateOfBirth(){
        return dateOfBirth.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    private String getGender(){
        return isMale ? MALE_VAL : FEMALE_VAL;
    }

    private String getAge(){
        return String.valueOf(Period.between(dateOfBirth, LocalDate.now()).getYears());
    }

    private String getPostcode(){
        return String.valueOf(postcode);
    }

    private String getHouseNumber(){
        return String.valueOf(houseNumber);
    }

    private String getApartmentNumber(){
        return String.valueOf(apartmentNumber);
    }

    public String[] getTableString(){
        return new String[] {surname, name, patronymic, getAge(),
        getGender(), getDateOfBirth(), placeOfBirth, getPostcode(),
        country, region, town, street, getHouseNumber(),
        getApartmentNumber()};
    }
}
