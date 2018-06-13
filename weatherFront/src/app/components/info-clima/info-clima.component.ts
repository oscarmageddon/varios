import {ClimaService} from '../../services/clima/clima.service';
import {ICiudad} from '../../interfaces/ciudad.interface';
import {Climadata} from '../../interfaces/climadata.interface';
import {Component, OnInit} from '@angular/core';
import {subscribeOn} from 'rxjs/operators';


@Component({
  selector: 'app-info-clima',
  templateUrl: './info-clima.component.html'
})
export class InfoClimaComponent implements OnInit {

  ciudades = new Array<ICiudad>();
  ciudadSel = '';
  metrica = 'metric';

  climadata = new Climadata();

  constructor(private _climaService: ClimaService) {

    this.getCiudades();
  }

  ngOnInit() {

  }

  getCiudades() {
    this._climaService.getCiudades().subscribe((data: any) => {
      this.ciudades = data;
    });
  }

  getClima() {
    this._climaService.getClima(this.metrica, this.ciudadSel).subscribe(
      (data: any) => {
        this.climadata = data;
        console.log('data:');
        console.log(data);
      });
  }

  buscarData() {
    if (this.ciudadSel !== '') {
      this.getClima();
    }
  }
}
