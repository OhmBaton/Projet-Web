package com.example.model;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;
import java.util.ArrayList;

public class RequestUtils {
    public static String sendGet(String url) throws Exception {
        System.out.println("url : " + url);
        OkHttpClient client = new OkHttpClient();

        //Création de la requête
        Request request = new Request.Builder().url(url).build();

        //Le try-with ressource doc ici
        //Nous permet de fermer la réponse en cas de succès ou d'échec (dans le finally)
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public static MoonBean loadMoon() throws Exception { //loadmoon() Sert à récupérer les fichiers de l'API et à les remplir dans MoonBean (qui remplira les Beans sous-jacents)
        String json = sendGet("https://images-api.nasa.gov/search?q=moon&media_type=image");
        MoonBean data = new Gson().fromJson(json, MoonBean.class);
        return data;
    }

    public static ArrayList<NasaBean> loadNasa() {  // Sert à reunir tous les attributs dans les Nasabean (ArrayList de Nasabean)
        MoonBean moons;
        try {
            moons = loadMoon();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<NasaBean> nasas = new ArrayList<NasaBean>();
        NasaBean nasa = new NasaBean();
        for (ItemBean item : moons.getCollection().getItems()) {
            for (DataBean data : item.getData()) {
                nasa = new NasaBean();
                nasa.setYear(data.getDate_created());
                nasa.setTitle(data.getTitle());
                nasa.setCredits(data.getSecondary_creator());
            }
            for (LinksBean links : item.getLinks()) {
                nasa.setUrlPic(links.getHref());
            }
            nasas.add(nasa);
        }
        return nasas;
    }

    public static void main(String[] args) { //tests
        ArrayList<NasaBean> test = loadNasa();
        //System.out.println(test.get(0).getTitle() + test.get(0).getUrlPic() + test.get(0).getYear());
      /*  //System.out.println(test.get(50).getTitle() + test.get(50).getUrlPic() + test.get(50).getYear());
        //System.out.println(correspondingTitles(test,"1999")[0] + correspondingTitles(test,"1999")[1] + correspondingTitles(test,"2010").length);;
        System.out.println(correspondingImage(test, "2010" , "The Moon Largest Impact Basin"));
        System.out.println(correspondingDescription(test, "2010" , "The Moon Largest Impact Basin"));*/
        int yeartest = 1996;
        for (int i = 0; i < correspondingTitles(test, String.valueOf(yeartest)).length; i++) {
            System.out.println(correspondingTitles(test, String.valueOf(yeartest))[i]);
            System.out.println(correspondingImage(test, String.valueOf(yeartest), correspondingTitles(test, String.valueOf(yeartest))[i]));
        }


    }

    public static String[] yearList(ArrayList<NasaBean> nasas) { // créé une liste de string (pas une arrayList) des dates de prise de photos (années).
        ArrayList<String> yearArrayList = new ArrayList<>();
        for (NasaBean nasa : nasas) {
            if (containsYear(yearArrayList, nasa.getYear().substring(0, 4)) == false) {
                yearArrayList.add(nasa.getYear().toString().substring(0, 4));


            }
            ;
        }
        return toStringList(yearArrayList);
    }

    public static boolean containsYear(ArrayList<String> years, String name) {
        for (String year : years) {
            if (year.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static String[] toStringList(ArrayList<String> AL) { // Change arraylists en arrays
        String[] SL = new String[AL.size()];
        for (int i = 0; i < AL.size(); i++) {
            SL[i] = AL.get(i);
        }
        //String[] SL = new String[AL.size()];
        //SL = AL.toArray(SL);

        return SL;
    }

    public static String[] correspondingTitles(ArrayList<NasaBean> nasas, String year) {// créé une liste de string (pas une arrayList) des photos correspondantes aux années .
        ArrayList<String> corTitles = new ArrayList<>();
        for (NasaBean nasa : nasas) {
            if (nasa.getYear().substring(0, 4).equals(year))
                corTitles.add(nasa.getTitle());
        }
        return toStringList(corTitles);
    }

    public static String correspondingImage(ArrayList<NasaBean> nasas, String year, String title) {
        String corImage = "";
        for (NasaBean nasa : nasas) {
            if (nasa.getYear().substring(0, 4).equals(year) && nasa.getTitle().equals(title))
                corImage = nasa.getUrlPic();
        }
        return corImage;
    }

    public static String correspondingCredits(ArrayList<NasaBean> nasas, String year, String title) {// créé une liste de string (pas une arrayList) des descriptions correspondantes aux années et photos.
        String corDesc = "";
        for (NasaBean nasa : nasas) {
            if (nasa.getYear().substring(0, 4).equals(year) && nasa.getTitle().equals(title))
                corDesc = nasa.getCredits();
        }
        return corDesc;
    }
}



