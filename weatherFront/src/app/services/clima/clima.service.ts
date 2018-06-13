import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClimaService {

  getCiudadesURL = 'http://localhost:8080/clima/ciudades';
  getClimaURLBase = 'http://localhost:8080/clima/climadata';

  constructor(private http: HttpClient) {
  }

  getCiudades() {
    return this.http.get(this.getCiudadesURL);
  }

  getClima(metrica, ciudad) {
    const url = this.getClimaURLBase + '?metrica=' + metrica + '&ciudad=' + ciudad;
    return this.http.get(url);
  }
}
