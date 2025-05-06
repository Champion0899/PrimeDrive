import {Component, inject, OnInit} from '@angular/core';
import {FahrzeugService} from './fahrzeug/fahrzeug.service';
import {Fahrzeug} from './fahrzeug/fahrzeug.model';
import {HttpClientModule} from '@angular/common/http';
import {FahrzeugComponent} from './fahrzeug/fahrzeug.component';
import {NgForOf} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [HttpClientModule, FahrzeugComponent, NgForOf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'PrimeDriveFrontend';
  private service = inject(FahrzeugService);
  fahrzeuge: Fahrzeug[] = []

  ngOnInit() {
    this.service.getFahrzeuge().subscribe(fahrzeuge => {
      this.fahrzeuge = fahrzeuge;
      console.log(fahrzeuge)
    })
  }


  protected readonly FahrzeugService = FahrzeugService;
}
