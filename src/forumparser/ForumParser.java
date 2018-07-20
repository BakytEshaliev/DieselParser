/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forumparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ForumParser {

    private String url;
    private KeyWords kw;

    public void unit(String url, String[] kwMassive) {
        this.url = url;
        kw = new KeyWords(kwMassive);
    }

    public void run() throws IOException{
        ArrayList<Result> uncheckedResults = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements h4Elements = doc.select("h4");
        for (Element element : h4Elements) {
            Elements aElements = element.select("a");
            for (Element aElement : aElements) {
                String title = aElement.attr("title").toLowerCase();
                String resultUrl = aElement.attr("abs:href");
                Result result = new Result(title, resultUrl);
                uncheckedResults.add(result);
                //System.out.println(title+"-"+url);
            }
        }

        ArrayList<Result> finallyResults = kw.selectResults(uncheckedResults);
        if (finallyResults == null) {
            System.out.println("Пока нет результатов по данным ключевым словам");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        } else {
            for (Result result : finallyResults) {
                String title = result.getTitle();
                String resultUrl = result.getUrl();
                System.out.println(title + "---" + resultUrl);
            }
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Введите url наблдюдаемой ветки:");
        Scanner sc = new Scanner(System.in);
        String url = sc.nextLine();
        System.out.println("Введите ключевые слова через запятую:");
        Scanner sc1 = new Scanner(System.in);
        String kw = sc1.nextLine();
        kw=kw.toLowerCase();
        String[] kwMassive = kw.split(",");
        System.out.println("Введите время обновления в минутах:");
        Scanner sc2 = new Scanner(System.in);
        int time = sc2.nextInt();
        ForumParser fp = new ForumParser();
        fp.unit(url, kwMassive);
        for(int i=0;i<2;i++){
            i=0;
            fp.run();
            TimeUnit.MINUTES.sleep(time);
        }
    }
}
