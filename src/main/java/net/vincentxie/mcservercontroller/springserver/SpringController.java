package net.vincentxie.mcservercontroller.springserver;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.vincentxie.mcservercontroller.configurator.*;

import java.io.IOException;

@Controller
public class SpringController {

    Server.ServerModType serverType;
    String serverVersion;
    int maxHeap;
    int minHeap;

    @GetMapping("/servercreate/type")
    public String setServerType(@RequestParam(name="serverType", required = false) String inServerType, Model model) {
        serverType = Server.ServerModType.valueOf(inServerType);
        return "/servercreate/type";
    }

    @GetMapping("/servercreate/version")
    public String setServerVersion(@RequestParam(name = "serverVersion", required = false) String inServerVersion, Model model) throws IOException, ParseException {
        model.addAttribute("possibleVersions", Versions.getVersions(serverType));
        serverVersion=inServerVersion;

        return "/servercreate/version";
    }

    @GetMapping("/servercreate/memory")
    public String setHeap(@RequestParam(name = "maxHeap", required = false) String inMaxHeap,
                          @RequestParam(name = "minHeap", required = false) String inMinHeap,
                          Model model) {
        maxHeap = Integer.parseInt(inMaxHeap);
        minHeap = Integer.parseInt(inMinHeap);

        return "/servercreate/memory";
    }


}
