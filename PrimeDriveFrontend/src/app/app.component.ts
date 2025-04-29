import {Component, inject, OnInit} from '@angular/core';
import {FahrzeugService} from './fahrzeug/fahrzeug.service';
import {Fahrzeug} from './fahrzeug/fahrzeug.model';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-root',
  imports: [HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'PrimeDriveFrontend';
  private service = inject(FahrzeugService);
  cars: Fahrzeug[] = []

  ngOnInit() {
    this.service.getFahrzeuge().subscribe(fahrzeuge => {
      this.cars = fahrzeuge;
      console.log(fahrzeuge)
    })
  }


}
