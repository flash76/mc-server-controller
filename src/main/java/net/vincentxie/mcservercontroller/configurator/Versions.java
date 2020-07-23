package net.vincentxie.mcservercontroller.configurator;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class Versions {
    public static String[] getVersions(Server.ServerModType forVersion) throws IOException {
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
                    ArrayList<String> versionJsonArray = new ArrayList<>();
                    for (int i = 0; i < downloadsArray.length; i++) {
                        // if this line ends with comma and next line is a closing curly brace
                        if (downloadsArray[i].endsWith(",") && downloadsArray[i + 1].contains("}")) {
                            tmp = new ArrayList(Arrays.asList(downloadsArray[i].split("")));
                            tmp.remove(tmp.size() - 1);
                            versionJsonArray.add(String.join("", tmp));
                        } else if (downloadsArray[i].contains("//")) {
                            versionJsonArray.add(downloadsArray[i].substring(0, downloadsArray[i].length() - downloadsArray[i].indexOf("//") + 7));
                        }
                        else versionJsonArray.add(downloadsArray[i]);
                    }
                    for (String i : versionJsonArray) System.out.println(i);
                    
                    String versionJson = String.join("", versionJsonArray);

                    JSONObject jsonObject = new JSONObject(versionJson);
//                    String pageName = jsonObject.getJSONObject("pageInfo").getString("pageName");
//                    JSONArray arr = jsonObject.getJSONArray("posts");

                    Set keySet = jsonObject.keySet();
                    Iterator<?> i = keySet.iterator();
                    do finalVers.add(jsonObject.getJSONObject(i.next().toString()).getString("title")); while(i.hasNext());

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
