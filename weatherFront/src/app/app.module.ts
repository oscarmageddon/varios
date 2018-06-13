import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { InfoClimaComponent } from './components/info-clima/info-clima.component';

import { KeysPipe } from './pipes/keys.pipe';

import { ClimaService } from '../app/services/clima/clima.service';

@NgModule({
  declarations: [
    AppComponent,
    InfoClimaComponent,
    KeysPipe
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    ClimaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
