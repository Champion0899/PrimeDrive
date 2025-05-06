import {Component, Input} from '@angular/core';
import {CurrencyPipe, DecimalPipe} from '@angular/common';
import {Fahrzeug} from './fahrzeug.model';


@Component({
  selector: 'app-fahrzeug',
  templateUrl: './fahrzeug.component.html',
  imports: [
    DecimalPipe,
    CurrencyPipe
  ],
  styleUrls: ['./fahrzeug.component.css']
})
export class FahrzeugComponent {
  @Input() fahrzeug!: Fahrzeug;
}
