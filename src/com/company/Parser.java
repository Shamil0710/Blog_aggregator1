package com.company;

/*
Создать карту вида, ключь, дата полученная из блока ленты. Значение ссылка на новость. отсторитировать даты по убыванию вывести первые 50;
*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


public class Parser {

    private String URL;



    public static Document getLenta () {
        
        Document document = null;
        
        try {
            document = Jsoup.connect("https://lenta.ru/rss/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements inks = document.select("<link></link>");


        return document;
        
    }

    public static void getLinks () {

        Document document = null;
        ArrayList<String> linksList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();

        //Пытаемся подконектится к ленте и получить документ

        try {
            document = Jsoup.connect("https://lenta.ru/rss/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //** Получаем ссылки **//



        //Получаем эелементы относящиеся к тегу ссылки
        Elements links = document.getElementsByTag("link");


        //Делим строку на токены

        StringTokenizer stringTokenizerLinks = new StringTokenizer(links.toString());


        //Складываем токены в список

        while (stringTokenizerLinks.hasMoreTokens()) linksList.add(stringTokenizerLinks.nextToken());

        //Чистим наш массив с сылками от тегов и не релевантных ссылок
        Iterator<String> linksIterator = linksList.iterator(); //Создаем итератор
        while (linksIterator.hasNext()){
            String i = linksIterator.next();
            if (i.equals("</link>") || i.equals("<link>") || i.equals("https://lenta.ru")) linksIterator.remove();
        }


        Elements time = document.getElementsByTag("pubDate");



        StringTokenizer stringTokenizerTime = new StringTokenizer(time.toString(), "" + "<>");

        while (stringTokenizerTime.hasMoreTokens()) timeList.add(stringTokenizerTime.nextToken());


        Iterator <String> timeListIterator = timeList.iterator();



        while (timeListIterator.hasNext()) {
            String i = timeListIterator.next();
            if (i.equals("/pubDate") || i.equals("pubDate")) timeListIterator.remove(); //todo Разобратся с очитской массива от пустых строк
        }



    }

    //Метод приобразования даты и времени из строки в объект DateTime для Lenta.ru

    public static LocalDateTime getTimeLenta (String time) {


        LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.RFC_1123_DATE_TIME);


        return  dateTime;

    }



    }








