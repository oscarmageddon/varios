package com.weatherBack.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weatherBack.entity.Ciudad;
import com.weatherBack.entity.ClimaData;
import com.weatherBack.service.CiudadService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = "clima")
public class ClimaController {

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/ciudades", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Ciudad>> getCities() {
		CiudadService ciudadServ = new CiudadService();
		List<Ciudad> ciudades = ciudadServ.getCiudades(10);
		if (!ciudades.isEmpty()) {
			return new ResponseEntity<List<Ciudad>>(ciudades, HttpStatus.OK);
		}
		return new ResponseEntity<List<Ciudad>>(HttpStatus.NOT_FOUND);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/climadata", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ClimaData> getClima(@RequestParam String metrica, @RequestParam String ciudad) {
		CiudadService ciudadServ = new CiudadService();
		JSONObject cData = ciudadServ.getClimaData(metrica, ciudad);
		if (cData != null) {
			return new ResponseEntity<ClimaData>(filtraData(cData), HttpStatus.OK);
		}
		return new ResponseEntity<ClimaData>(HttpStatus.NOT_FOUND);
	}

	private ClimaData filtraData(JSONObject data) {

		ClimaData clima = new ClimaData();
		JSONObject coordenadas = (JSONObject) data.get("coord");
		clima.setLatitude(coordenadas.get("lat").toString());
		clima.setLongitude(coordenadas.get("lon").toString());

		JSONObject pais = (JSONObject) data.get("sys");
		clima.setCountry(pais.get("country").toString());

		clima.setCity(data.get("name").toString());

		JSONObject temperatura = (JSONObject) data.get("main");
		clima.setCurrentWeather(temperatura.get("temp").toString());

		JSONObject viento = (JSONObject) data.get("wind");
		clima.setWindSpeed(viento.get("speed").toString());

		return clima;
	}
}
