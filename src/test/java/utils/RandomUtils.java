package utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String[] genders = {"Male", "Female", "Other"};
    public static String[] months = {"January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December"};
    public static String[] subjects = {"Maths", "Arts", "Biology", "Chemistry"};
    public static String[] hobbies = {"Sports", "Reading", "Music"};
    public static String[] states = {"NCR"};
    public static String[] cities = {"Delhi", "Gurgaon", "Noida"};

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomItemFromArray(String[] values) {
        int index = getRandomInt(0, values.length - 1);
        return values[index];
    }
}
