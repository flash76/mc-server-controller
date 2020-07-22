package net.vincentxie.mcservercontroller.configurator;

import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Server {

    public enum ServerModType {
        FORGE("Forge"),
        PAPER_SPIGOT("Paper_Spigot"),
        SPIGOT("Spigot"),
        VANILLA("Vanilla");

        private final String modType;
        ServerModType(String modType) {
            this.modType = modType;
        }

        public String getModType() {
            return modType;
        }
    }

    private String serverDirectory;
    private int minHeap;
    private int maxHeap;

    private ServerModType serverModChoice;
    private String[] availableModVersions;
    private String serverModVersion;

    public Server(String serverDirectory, ServerModType serverModChoice, String serverModVersion) throws IOException, ParseException {
        this.serverDirectory = serverDirectory;
        this.availableModVersions = Versions.getVersions(serverModChoice);
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

    public boolean start() throws ServerNoVersionException {
        // TODO: Make start function
        if (serverModVersion == null || serverModVersion.isEmpty()) {
            throw new ServerNoVersionException();
        } else {
            // use server dir, check if files are downloaded, if not download them
        }

        return true;
    }

    public boolean kill() {
        // TODO: Make kill function
        return true;
    }
}

class ServerNoVersionException extends Exception {
    ServerNoVersionException() {
        super("No Minecraft server version specified!\nCall the Server.setVersion method ");
    }
}