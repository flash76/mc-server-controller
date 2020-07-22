package net.vincentxie.mcservercontroller.configurator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Versions {
    public static String[] getVersions(Server.ServerModType forVersion) {
        Document docVersion;
        ArrayList<String> finalVers = new ArrayList<>();
        try {
            switch (forVersion) {
                case FORGE: {
                    docVersion = Jsoup.connect("https://files.minecraftforge.net").get();
                    Elements selected = docVersion.select("li.li-version-list");
                    for (Element element : selected) {
                        String[] tmp = element.select("a[href]").text().split(" ");
                        for (String i : Arrays.copyOfRange(tmp, 1, tmp.length)) {
                            finalVers.add(i); }
                    }
                    break;
                }
                case PAPER_SPIGOT: {
                    docVersion = Jsoup.connect("https://papermc.io/downloads").get();
                    Elements selected = docVersion.select("li.tab");
                    System.out.println(selected.toString());
                    break;
                }
                case SPIGOT: {
                    System.out.print("hiu");
                    break;
                }
                default: {
                    finalVers.add("No server software specified.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return finalVers.toArray(new String[finalVers.size()]);
    }
}
