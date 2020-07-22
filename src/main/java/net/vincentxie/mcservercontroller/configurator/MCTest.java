package net.vincentxie.mcservercontroller.configurator;

import java.util.Arrays;

public class MCTest {
    public static void main(String[] args) {
//        Server serverConfigurator = new Server("", Server.ServerModType.FORGE, "1.15.2");

//         Tests how the version getting thing works
        Versions.getVersions(Server.ServerModType.PAPER_SPIGOT);
        for (String i : Versions.getVersions(Server.ServerModType.FORGE)) {
            System.out.println(i);
        }
    }
}
