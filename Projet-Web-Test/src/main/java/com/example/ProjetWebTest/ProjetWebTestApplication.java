package com.example.ProjetWebTest;

import com.example.model.DataBean;
import com.example.model.NasaBean;
import com.example.model.RequestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.model.RequestUtils.*;

@SpringBootApplication
public class ProjetWebTestApplication {

	public static void main(String[] args) {
//		ArrayList<NasaBean> test = loadNasa();
//
//		int yeartest = 1996;
//
//		for(int i = 0; i< correspondingTitles(test, String.valueOf(yeartest)).length ; i++) {
//			System.out.println(correspondingTitles(test, String.valueOf(yeartest))[i]);
//			System.out.println(correspondingImage(test, String.valueOf(yeartest), correspondingTitles(test, String.valueOf(yeartest))[i]));
//			System.out.println(correspondingCredits(test, String.valueOf(yeartest), correspondingTitles(test, String.valueOf(yeartest))[i]));
//		}
//
//		System.out.println(Arrays.stream(yearList(test)).sorted().toList());

		SpringApplication.run(ProjetWebTestApplication.class, args);
	}
}

