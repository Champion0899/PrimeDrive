import {Component, inject, OnInit} from '@angular/core';
import {FahrzeugComponent} from '../fahrzeug/fahrzeug.component';
import {NgForOf} from '@angular/common';
import {Fahrzeug} from '../fahrzeug/fahrzeug.model';
import {FahrzeugService} from '../fahrzeug/fahrzeug.service';
import {RouterModule} from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-overview',
  imports: [FahrzeugComponent, NgForOf, RouterModule],
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
