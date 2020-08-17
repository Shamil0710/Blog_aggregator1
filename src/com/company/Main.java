package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	// write your code here

//
//        ArrayList<String> linksList = new ArrayList<>();
//        ArrayList<String> timeList = new ArrayList<>();
//        ArrayList<String> headingList = new ArrayList<>();
//
        //Пытаемся подконектится к ленте и получить документ

        Document document = null;

        try {
            document = Jsoup.connect("https://lenta.ru/rss/news").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        //** Получаем ссылки **//
//
//
//
//        //Получаем эелементы относящиеся к тегу ссылки
//        Elements links = document.getElementsByTag("link");
//
//
//        //Делим строку на токены
//
//        StringTokenizer stringTokenizerLinks = new StringTokenizer(links.toString());
//
//
//        //Складываем токены в список
//
//        while (stringTokenizerLinks.hasMoreTokens()) linksList.add(stringTokenizerLinks.nextToken());
//
//        //Чистим наш массив с сылками от тегов и не релевантных ссылок
//        Iterator<String> linksIterator = linksList.iterator(); //Создаем итератор
//        while (linksIterator.hasNext()){
//           String i = linksIterator.next();
//            if (i.equals("</link>") || i.equals("<link>") || i.equals("https://lenta.ru")) linksIterator.remove();
//        }
////
////        for (String i: linksList) {
////            System.out.println(i);
////        }
//
//        //** Получаем даты **//
//
//        Elements time = document.getElementsByTag("pubDate");
//
//
//
//        StringTokenizer stringTokenizerTime = new StringTokenizer(time.toString(), "" + "<>");
//
//        while (stringTokenizerTime.hasMoreTokens()) timeList.add(stringTokenizerTime.nextToken());
//
////        timeList.removeAll(Collections.singleton(null));
////        timeList.removeAll(Collections.singleton(""));
//
//        Iterator <String> timeListIterator = timeList.iterator();
//
//
//
//        while (timeListIterator.hasNext()) {
//            String i = timeListIterator.next();
//            if (i.equals("/pubDate") || i.equals("pubDate")) timeListIterator.remove();
//        }
//
//        //** Получение заголовка **//
//
//        Elements heading = document.getElementsByTag("title");
//
//        StringTokenizer stringTokenizerHeading = new StringTokenizer(heading.toString(), "><");
//
//        while (stringTokenizerHeading.hasMoreTokens()) headingList.add(stringTokenizerHeading.nextToken());
//
//        Iterator <String> headingListIterator = headingList.iterator();
//
//        while (headingListIterator.hasNext()) {
//            String i = headingListIterator.next();
//            if (i.equals("title") || i.equals("/title") || i.equals("Lenta.ru") || i.equals("Lenta.ru : Новости")) headingListIterator.remove();
//        }
//
////        for (String i: headingList) {
////
////            System.out.println(i);
////
////        }
////
////        System.out.println( "Заголовки " + headingList.size());
////        System.out.println("Links " + linksList.size());
////        System.out.println("Time " + timeList.size());
////
////
//
////        for (int i = 0; i<timeList.size(); i++) {
////
////
////            System.out.println(i + " " + timeList.get(i)); //todo доделать очистку списка с датами и сравнить получаемое количество
////
////
////        }
//
////        System.out.println("Time List " + timeList.size());
////        System.out.println("Link List " + linksList.size());

        //** Получаем ссылки **//

        ArrayList<String> linksList = new ArrayList<>();

        String links = document.getElementsByTag("link").text();

       StringTokenizer linksTokenaizer = new StringTokenizer(links, " ");

       while (linksTokenaizer.hasMoreTokens()) linksList.add(linksTokenaizer.nextToken());

       linksList.removeAll(Collections.singleton("https://lenta.ru"));


       //Складируем результат в список

       while (linksTokenaizer.hasMoreTokens()) linksList.add(linksTokenaizer.nextToken());
//
//        for (String i : linksList) {
//
//            System.out.println(i);
//
//        }
//
//        System.out.println(linksList.size());

       //** Получаем время **//

       ArrayList<LocalDateTime> timeList = new ArrayList<>();


        String pubDate = document.getElementsByTag("pubDate").text();

        Pattern pattern = Pattern.compile("..., .. ... .... ..:..:.. +.....");

        Matcher matcher = pattern.matcher(pubDate);

        while (matcher.find()) {
            timeList.add(Parser.getTimeLenta(matcher.group()));
        }
//
//        for (LocalDateTime i:
//             timeList) {
//            System.out.println(i);
//        }

        //** Получение заголовка **// todo надо решить проблему с заголовком

        ArrayList<String> headingList = new ArrayList<>();

        Elements heading = document.getElementsByTag("title");

        StringTokenizer stringTokenizerHeading = new StringTokenizer(heading.toString(), "><");

        while (stringTokenizerHeading.hasMoreTokens()) headingList.add(stringTokenizerHeading.nextToken());

        Iterator <String> headingListIterator = headingList.iterator();

        while (headingListIterator.hasNext()) {
            String i = headingListIterator.next();
            if (i.equals("title") || i.equals("/title") || i.equals("Lenta.ru") || i.equals("Lenta.ru : Новости")) headingListIterator.remove();
        }

        for (int i = 0; i<headingList.size(); i++) {
            System.out.println(i + " " + headingList.get(i));
        }






//
//        StringTokenizer dataTimeTokenizer = new StringTokenizer(pubDate, "..., .. ... .... ..:..:.. +.....", true); //todo Кастыль, переделать с регулярными выражениями, когда разберусь в них(
//



















    }
}
