import {Component, inject, OnInit} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FahrzeugComponent} from '../fahrzeug/fahrzeug.component';
import {NgForOf} from '@angular/common';
import {Fahrzeug} from '../fahrzeug/fahrzeug.model';
import {FahrzeugService} from '../fahrzeug/fahrzeug.service';

@Component({
  selector: 'app-overview',
  imports: [HttpClientModule, FahrzeugComponent, NgForOf],
  templateUrl: './overview.component.html',
  styleUrl: './overview.component.css'
})

export class OverviewComponent implements OnInit {
  private service = inject(FahrzeugService);
  fahrzeuge: Fahrzeug[] = []

  ngOnInit() {
    this.service.getFahrzeuge().subscribe(fahrzeuge => {
      this.fahrzeuge = fahrzeuge;
      // console.log(fahrzeuge)
    })
  }
}
