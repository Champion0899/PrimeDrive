import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-register-dialog',
  standalone: true,
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
  ],
  templateUrl: './register-dialog.component.html',
  styleUrl: './register-dialog.component.scss',
})
export class RegisterDialogComponent {
  public username: string = '';
  public password: string = '';
  public confirmPassword: string = '';
  public firstName: string = '';
  public lastName: string = '';
  public email: string = '';
  public birthdate: string = '';
  public address: string = '';
  public zipCode: string = '';
  public city: string = '';
  public country: string = '';
  public phoneNumber: string = '';

  public register() {}
}
