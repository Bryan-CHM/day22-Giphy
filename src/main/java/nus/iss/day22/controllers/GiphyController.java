package nus.iss.day22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.day22.services.GiphyService;

@Controller
@RequestMapping(path="/giphy")
public class GiphyController {

    @Autowired
    private GiphyService giphySvc;
    
    @GetMapping("")
    public String getGiphy(@RequestParam String phrase, @RequestParam String limit, @RequestParam String rating, Model model){
        List<String> gifUrls = giphySvc.getGifs(phrase, limit, rating);

        model.addAttribute("gifUrls",gifUrls);
        return "DisplayGiphy";
    }
}
