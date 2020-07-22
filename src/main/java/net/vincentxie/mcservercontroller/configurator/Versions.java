package net.vincentxie.mcservercontroller.configurator;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class Versions {
    public static String[] getVersions(Server.ServerModType forVersion) throws IOException, ParseException {
        Document docVersion;
        ArrayList<String> finalVers = new ArrayList<>();
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
                    String paperScriptContents = IOUtils.toString(new URL("https://papermc.io/js/downloads.js?v=b0a5f23316d3d580"), StandardCharsets.UTF_8);
                    String downloads = paperScriptContents.substring(paperScriptContents.indexOf("s = {") + 3, paperScriptContents.indexOf("}\n};") + 3);
                    String[] downloadsArray = downloads.split(System.getProperty("line.separator"));

                    ArrayList tmp;
                    ArrayList<String> paperVersions = new ArrayList<>();
                    for (int i = 0; i < downloadsArray.length; i++) {
                        // if this line ends with comma and next line is a closing curly brace
                        if (downloadsArray[i].endsWith(",") && downloadsArray[i + 1].contains("}")) {
                            tmp = new ArrayList(Arrays.asList(downloadsArray[i].split("")));
                            tmp.remove(tmp.size() - 1);
                            paperVersions.add(String.join("", tmp));
                        } else if (downloadsArray[i].contains("//")) {
                            paperVersions.add(downloadsArray[i].substring(0, downloadsArray[i].length() - downloadsArray[i].indexOf("/")));
                        }
                        else paperVersions.add(downloadsArray[i]);
                    }
                    for (String i : paperVersions) System.out.println(i);



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

        return finalVers.toArray(new String[finalVers.size()]);
    }
}
