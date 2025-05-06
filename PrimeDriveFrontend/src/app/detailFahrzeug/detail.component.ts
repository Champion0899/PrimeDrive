import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {FahrzeugService} from '../fahrzeug/fahrzeug.service';
import {Fahrzeug} from '../fahrzeug/fahrzeug.model';
import {CurrencyPipe, NgIf} from '@angular/common';

@Component({
  standalone: true,
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
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.fahrzeugService.getFahrzeugById(id).subscribe(fahrzeug => {
          this.fahrzeug = fahrzeug;
          console.log(this.fahrzeug)
        });
      }
    });
  }
}
