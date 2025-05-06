import {Component} from '@angular/core';
import {FahrzeugService} from './fahrzeug/fahrzeug.service';
import {HttpClientModule} from '@angular/common/http';
import {FahrzeugComponent} from './fahrzeug/fahrzeug.component';
import {NgForOf} from '@angular/common';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [HttpClientModule, FahrzeugComponent, NgForOf, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'PrimeDriveFrontend';


  protected readonly FahrzeugService = FahrzeugService;
}
