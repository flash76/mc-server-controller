package net.vincentxie.mcservercontroller.configurator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Versions {
    public static String[] getVersions(Server.ServerModType forVersion) {
        Document docVersion;
        String[] finalVer = null;
        try {
            switch (forVersion) {
                case FORGE: {
                    docVersion = Jsoup.connect("https://files.minecraftforge.net").get();
                    Elements selected = docVersion.select("li.li-version-list");
                    StringBuilder versionString = new StringBuilder();
                    for (Element element : selected) {
                        versionString.append(element.select("a[href]").text()).append(" ");
                    }

                    finalVer = versionString.toString().split(" ");
                }
                case PAPER_SPIGOT: {
                    docVersion = Jsoup.connect("https://papermc.io/downloads").get();
                }
                case SPIGOT: {

                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return finalVer;
    }
}
