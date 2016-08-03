package io.advant.orm.test.testcase;

/**
 * Created by Marco on 03/08/2016.
 */
public class PrintUtil {

    private static final String sep = " -> ";
    private static String suite;
    private static String test;
    private static String action;
    private static String result;

    public static void suite(String value) {
        suite = "Suite: " + value;
        System.out.println("\n" + suite);
    }

    public static void test(String value) {
        test = "Test: " + value;
        System.out.println(suite + sep + test);
    }

    public static void action(String value) {
        action = value;
        System.out.println(suite + sep + test + sep + action);
    }

    public static void result(String value) {
        result = value;
        System.out.println(suite + sep + test + sep + action + sep + result);
    }
}
