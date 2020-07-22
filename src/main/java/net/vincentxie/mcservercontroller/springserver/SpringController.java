package net.vincentxie.mcservercontroller.springserver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.vincentxie.mcservercontroller.configurator.*;

@Controller
public class SpringController {

    Server.ServerModType serverType;
    String serverVersion;

    @GetMapping("/servercreate/type")
    public String setServerType(@RequestParam(name="serverType") String inServerType, Model model) {
        switch (inServerType) {
            case "Forge":
                serverType = Server.ServerModType.FORGE;
            case "Paper_Spigot":
                serverType = Server.ServerModType.PAPER_SPIGOT;
            case "Spigot":
                serverType = Server.ServerModType.SPIGOT;
            default:

        }
        return "/servercreate/type";
    }

    @GetMapping("/servercreate/version")
    public String setServerVersion(@RequestParam(name="serverVersion") String inServerVersion, Model model) {
        if (serverType == null) {
            model.addAttribute("exception", "You did not set the server type first!");

            return "/servercreate/version";
        }


        return "/servercreate/version";
    }

}
