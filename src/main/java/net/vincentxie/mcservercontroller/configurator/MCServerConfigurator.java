package net.vincentxie.mcservercontroller.configurator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MCServerConfigurator {

    public enum ServerModType {
        FORGE,
        PAPER_SPIGOT,
        SPIGOT;
    }

    private String serverDirectory;
    private int minHeap;
    private int maxHeap;
    private ServerModType serverModChoice;

    public MCServerConfigurator(String serverDirectory, ServerModType serverModChoice) {
        this.serverDirectory = serverDirectory;

        Document docVersion;
        try {
            switch (serverModChoice) {
                case FORGE: {
                    docVersion = Jsoup.connect("https://files.minecraftforge.net").get();
                    Elements versions = docVersion.select("li.li-version-list");
                    System.out.println(versions);
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

        this.serverModChoice = serverModChoice;
    }

    public void setMinHeap(int minHeap) {
        this.minHeap = minHeap;
    }

    public int getMinHeap() {
        return minHeap;
    }

    public void setMaxHeap(int maxHeap) {
        this.maxHeap = maxHeap;
    }

    public int getMaxHeap() {
        return maxHeap;
    }

    public boolean start() {
        // TODO: Make start function
        return true;
    }

    public boolean kill() {
        // TODO: Make kill function
        return true;
    }
}
