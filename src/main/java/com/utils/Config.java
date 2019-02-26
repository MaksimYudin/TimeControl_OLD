package com.utils;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Config {

    public static int MAIN_FORM_WIDTH = 1200;
    public static int MAIN_FORM_HEIGHT = 1000;
    public static int TOP_BOTTOM_PANE_HEIGHT = 80;
    public static int BTN_MENU_HEIGHT = 80;
    public static int BTN_MENU_WIDTH = 200;
    public static int BTN_MENU_FONT_SIZE = 20;
    public static int BTN_MENU_IMAGE_SIZE = 20;
    public final static int SEPARATOR_BTN_WIDTH = 15;
    public static int WORK_PLACE_LEFT_ANCOR_WIDTH = (BTN_MENU_WIDTH + 20);
    public static int MAIN_WP_IMG_WIDTH = 130;
    public static int MAIN_WP_IMG_HEIGHT = 100;
    public static int MAIN_WP_GRIDPANE_WIDTH = 100;
    public static int MAIN_WP_GRIDPANE_HEIGHT = 100;
    public static int PROGRAM_CAPTION_FONT_SIZE = 20;
    public static int PROGRAM_CAPTION_HEIGHT = 20;
    public static int MAIN_WP_GRIDPANE_LABEL_HEIGHT = 100;

    public static final String GROUP_NAME = "ДОО 1";
    public static final String PATH_TO_ICONS = "/org/russianfeature/assests/images/icons/";
    public static final int minHeightWorkPlaceMenu = 200;

    public static final String DB_FULL_PATH = "src/sample/db/TimeControl.sqlite";
    public final static int DICTIONARY_MENU_WIDTH = 200;
    public final static int DICTIONARY_MENU_HEIGHT = 200;

    public static void setScreenProperty() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        if (width <= 1366) {
            MAIN_FORM_WIDTH = 800;
            MAIN_FORM_HEIGHT = 600;
            BTN_MENU_WIDTH = 120;
            BTN_MENU_HEIGHT = 50;
            BTN_MENU_FONT_SIZE = 12;
            BTN_MENU_IMAGE_SIZE = 20;
            MAIN_WP_IMG_WIDTH = 70;
            MAIN_WP_IMG_HEIGHT = 50;
            MAIN_WP_GRIDPANE_WIDTH = 120;
            MAIN_WP_GRIDPANE_HEIGHT = 164;
            TOP_BOTTOM_PANE_HEIGHT = 40;
            PROGRAM_CAPTION_FONT_SIZE = 20;
            PROGRAM_CAPTION_HEIGHT = 20;
            MAIN_WP_GRIDPANE_LABEL_HEIGHT = 35;
        } else {
            MAIN_FORM_WIDTH = 1200;
            MAIN_FORM_HEIGHT = 1000;
            BTN_MENU_WIDTH = 200;
            BTN_MENU_HEIGHT = 80;
            BTN_MENU_FONT_SIZE = 20;
            BTN_MENU_IMAGE_SIZE = 30;
            MAIN_WP_IMG_WIDTH = 130;
            MAIN_WP_IMG_HEIGHT = 100;
            MAIN_WP_GRIDPANE_WIDTH = 140;
            MAIN_WP_GRIDPANE_HEIGHT = 200;
            TOP_BOTTOM_PANE_HEIGHT = 80;
            PROGRAM_CAPTION_FONT_SIZE = 30;
            PROGRAM_CAPTION_HEIGHT = 30;
            MAIN_WP_GRIDPANE_LABEL_HEIGHT = 20;
        }
        WORK_PLACE_LEFT_ANCOR_WIDTH = (BTN_MENU_WIDTH + 20);
    }

    public static TreeMap<String, String> getMenuBtnMap() {
        TreeMap<String, String> menuBtns = new TreeMap<String, String>();

        menuBtns.put("1.main", "leftMenuMain");
        menuBtns.put("2.settings", "leftMenuSettings");
        menuBtns.put("3.dictionary", "leftMenuDictionary");
        menuBtns.put("4.group", "leftMenuGroup");
        menuBtns.put("5.shedule", "leftMenuShedule");
        menuBtns.put("6.manual", "leftMenuManual");
        menuBtns.put("7.reports", "leftMenuReports");

        return menuBtns;
    }

    public static TreeMap<String, String> getDictionaryMap() {

        TreeMap<String, String> dic = new TreeMap<String, String>();

        dic.put("1.student", "Воспитанники");
        dic.put("2.teacher", "Педагоги");
        dic.put("3.lesson", "Занятия");
        dic.put("4.group", "Группы");

        return dic;
    }

    public static TreeMap<String, String> getSettingsMap() {

        TreeMap<String, String> dic = new TreeMap<String, String>();

        dic.put("1.sanpin", "СанПин");
        dic.put("2.crosslessons", "Пересечения занятий");

        return dic;
    }

    public static TreeMap<String, String> getGroupsMap() {

        TreeMap<String, String> dic = new TreeMap<String, String>();

        dic.put("1.group", "Группы");
        dic.put("2.groupLessons", "Групповые занятия");
        dic.put("3.personalLessons", "Индивидуальные занятия");

        return dic;
    }

    public static TreeMap<String, String> getReportsMap() {

        TreeMap<String, String> dic = new TreeMap<String, String>();

        dic.put("1.report1", "Отчет по группам");
        dic.put("2.report2", "Отчет по воспитанникам");

        return dic;
    }

    public static TreeMap<String, String> getSheduleMap() {

        TreeMap<String, String> dic = new TreeMap<String, String>();

        dic.put("1.shedule", "Рассписание занятий");

        return dic;
    }

    public static List<String> getMainWorkPlaceImages() {
        List<String> imgList = new ArrayList<>();

        imgList.add("imgMenuReports");
        imgList.add("imgMenuShedule");
        imgList.add("imgMenuGroup");
        imgList.add("imgMenuDictionary");
        imgList.add("imgMenuSettings");

        return imgList;
    }

    public static List<String> getMainWorkPlaceBlocks() {
        List<String> imgList = new ArrayList<>();

        imgList.add("reportsBlock");
        imgList.add("sheduleBlock");
        imgList.add("groupsBlock");
        imgList.add("dictionaryBlock");
        imgList.add("settingsBlock");

        return imgList;
    }

}
