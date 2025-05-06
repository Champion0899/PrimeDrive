import {Component, Input} from '@angular/core';
import {CurrencyPipe, DecimalPipe} from '@angular/common';
import {Fahrzeug} from './fahrzeug.model';
import {RouterLink} from '@angular/router';


@Component({
  standalone: true,
  selector: 'app-fahrzeug',
  templateUrl: './fahrzeug.component.html',
  imports: [
    DecimalPipe,
    CurrencyPipe,
    RouterLink
  ],
  styleUrls: ['./fahrzeug.component.css']
})
export class FahrzeugComponent {
  @Input() fahrzeug!: Fahrzeug;
}
