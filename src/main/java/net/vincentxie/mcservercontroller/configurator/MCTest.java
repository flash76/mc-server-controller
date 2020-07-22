package net.vincentxie.mcservercontroller.configurator;

public class MCTest {
    public static void main(String[] args) {
        Server serverConfigurator = new Server("", Server.ServerModType.FORGE, "1.15.2");

//         Tests how the version getting thing works
        for (String i : Versions.getVersions(Server.ServerModType.PAPER_SPIGOT)) {
            System.out.println(i);
        }
    }
}
