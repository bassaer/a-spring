package com.github.bassaer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Main controller
 * Created by nakayama on 2017/07/15.
 */
@Controller
public class MainController {
    public static final String LOADING_ERROR = "Not found";

    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping("/")
    public String main(Model model) {
        String setting = loadConf();
        System.out.println("setting -> " + setting);
        model.addAttribute("setting", setting);
        return "main";
    }


    private String loadConf() {
        StringBuilder confFile = new StringBuilder();
        try{
            Resource resource = resourceLoader.getResource("classpath:/static/config/setting.conf");
            BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

            String line = br.readLine();
            while(line != null){
                confFile.append(line);
                line = br.readLine();
            }

            br.close();
        }catch(Exception e) {
            return LOADING_ERROR;
        }
        return confFile.toString();
    }
}
