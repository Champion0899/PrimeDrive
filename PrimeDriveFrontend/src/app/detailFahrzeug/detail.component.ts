import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FahrzeugService } from '../fahrzeug/fahrzeug.service';
import { Fahrzeug } from '../fahrzeug/fahrzeug.model';
import {CurrencyPipe, NgIf} from '@angular/common';

@Component({
  selector: 'app-detail',
  templateUrl: 'detail.component.html',
  imports: [
    CurrencyPipe,
    NgIf
  ],
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  fahrzeug: Fahrzeug | undefined;

  constructor(
    private route: ActivatedRoute,
    private fahrzeugService: FahrzeugService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.fahrzeugService.getFahrzeugById(+id).subscribe(fahrzeug => {
        console.log(fahrzeug);
        this.fahrzeug = fahrzeug;
      });
    }
  }
}
