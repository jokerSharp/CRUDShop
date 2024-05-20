package com.shop.PetProject.testUtils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class InputUtils {

    private static final SecureRandom random = new SecureRandom();

    private static final String latChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int defaultLength = 20;

    public static String getStringWithoutSpaces(int count) {
        return RandomStringUtils.random(count, latChars);
    }

    public static String getStringWithoutSpaces() {
        return getStringWithoutSpaces(defaultLength);
    }

    public static String getRandomShortString() {
        return getStringWithoutSpaces(6);
    }

    public static <T extends Enum<?>> T getRandomValueFromEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static int getRandomPositiveInt() {
        return random.nextInt(1, Integer.MAX_VALUE);
    }

    public static int getRandomPositiveInt(int min, int max) {
        return random.nextInt(min, max);
    }

    public static String getRandomPriceWithCents() {
        DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
        decimalFormatSymbols.setDecimalSeparator('.');
        DecimalFormat decFormat = new DecimalFormat("#.##", decimalFormatSymbols);
        return decFormat.format(random.nextDouble() * 1000);
    }
}
