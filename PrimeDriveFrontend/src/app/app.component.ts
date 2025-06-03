import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavigationComponent } from './Components/navigation/navigation.component';

/**
 * Root component of the PrimeDrive Angular application.
 * Acts as the entry point and wraps global layout and navigation.
 *
 * Author: Fatlum Epiroti & Jamie Schüpbach
 * Version: 1.0.0
 * Date: 2025-06-03
 */
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'Prime Drive';
}
