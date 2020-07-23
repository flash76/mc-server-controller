package net.vincentxie.mcservercontroller.configurator;

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
    private String serverModVersion;

    public Server(String serverDirectory, ServerModType serverModChoice, String serverModVersion) {
        this.serverDirectory = serverDirectory;
        this.serverModVersion = serverModVersion;
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
        if (serverModVersion == null || serverModVersion.isEmpty()) {
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
