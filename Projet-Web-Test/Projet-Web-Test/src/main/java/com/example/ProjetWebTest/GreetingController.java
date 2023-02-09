package com.example.ProjetWebTest;

import com.example.model.NasaBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.model.RequestUtils.*;
import static com.example.model.RequestUtils.correspondingCredits;

@Controller
public class GreetingController {
    ArrayList<NasaBean> test = loadNasa();

    @GetMapping("/form")
    public String form(@RequestParam Map<String,String> allparams, Model model){
        //Récupération des valeurs présentes dans l'url
        String year = allparams.get("yearSelection");
        String go = allparams.get("GO");
        String submit = allparams.get("Submit");

        //Lister les années présentes dans la collection
        List yearsList = Arrays.stream(yearList(test)).sorted().toList();

        //Envoyer la liste d'années à l'élément HTML : Select, des années
        model.addAttribute("years", yearsList);

        //Test de si le bouton "GO" à été cliqué
        if(go != null) {
            //Remettre l'année sélectionnée dans le Select des années
            model.addAttribute("years", year);

            //Lister les titres d'images et les envoyer dans le Select suivant
            List Titles = List.of(correspondingTitles(test, String.valueOf(year)));
            model.addAttribute("names", Titles);
        }

        //Si clic du bouton Submit
        if(submit != null) {
            //Récupération du titre sélectionné depuis la page html
            String title = allparams.get("titleSelection");

            //Récupération de l'url de l'image avec les éléments séléctionnés (année et titre)
            String url = correspondingImage(test, String.valueOf(year), title);
            //Récupération de l'url de l'image avec les éléments séléctionnés (année et titre)
            String credits = correspondingCredits(test, String.valueOf(year), title);

            //Envoyer l'url et les crédits aux élément html pour les afficher
            model.addAttribute("url", url);
            model.addAttribute("credits", credits);
        }

        return "form";
    }

}