package com.weatherBack.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.weatherBack.entity.Ciudad;

public class CiudadService {

	public final List<Ciudad> getCiudades(int cantidadCiudades) {

		List<Ciudad> ciudades = new ArrayList<Ciudad>();

		try {
			URL url = new URL("http://openweathermap.org/help/city_list.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine = "";

			int num = 0;
			inputLine = in.readLine();
			while (num++ < cantidadCiudades + 1) {
				inputLine = in.readLine();
				String[] linea = inputLine.split("\t");
				Ciudad city = new Ciudad();
				city.setId(linea[0]);
				city.setName(linea[1]);
				city.setLat(linea[2]);
				city.setLon(linea[3]);
				city.setCode(linea[4]);
				ciudades.add(city);
			}
			in.close();
		} catch (Exception e) {
		}
		return ciudades;
	}

	public final JSONObject getClimaData(String metrica, String ciudad) {

		JSONParser parser = new JSONParser();
		JSONObject datos = new JSONObject();

		try {

			URL api = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + ciudad + "&units=" + metrica
					+ "&mode=json&APPID=cb5f25b36e9740eb8fd2d19fedf3b558");
			URLConnection yc = api.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

			String inputLine;
			inputLine = in.readLine();
			if (inputLine != null) {
				datos = (JSONObject) parser.parse(inputLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datos;
	}
}
