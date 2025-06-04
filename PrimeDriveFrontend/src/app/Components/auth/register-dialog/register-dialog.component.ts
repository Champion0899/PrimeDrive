import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../../Services/auth/auth.service';
import { HttpErrorResponse } from '@angular/common/http';

/**
 * Component for the registration dialog.
 * Collects user data and sends it to the AuthService for registration.
 */
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
  /** @public Username for registration */
  public username: string = '';
  /** @public Password for registration */
  public password: string = '';
  /** @public Confirmation of the password */
  public confirmPassword: string = '';
  /** @public First name of the user */
  public firstName: string = '';
  /** @public Last name of the user */
  public lastName: string = '';
  /** @public Email address of the user */
  public email: string = '';
  /** @public Birthdate of the user */
  public birthdate: string = '';
  /** @public Address of the user */
  public address: string = '';
  /** @public Zip code of the user's address */
  public zipCode: string = '';
  /** @public City of the user's address */
  public city: string = '';
  /** @public Country of the user's address */
  public country: string = '';
  /** @public Phone number of the user */
  public phoneNumber: string = '';

  private authService = inject(AuthService);

  /** Converts yyyy-MM-dd to dd.MM.yyyy */
  private convertToAppFormat(dateString: string): string {
    const [year, month, day] = dateString.split('-');
    return `${day}.${month}.${year}`;
  }

  /** @public Registers the user with the provided data */
  public register() {
    const registerDto = {
      username: this.username,
      password: this.password,
      confirmPassword: this.confirmPassword,
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      birthdate: this.convertToAppFormat(this.birthdate),
      address: this.address,
      zipCode: this.zipCode,
      city: this.city,
      country: this.country,
      phoneNumber: this.phoneNumber,
    };

    this.authService.register(registerDto).subscribe({
      error: (err: HttpErrorResponse) => {
        if (err.error instanceof ErrorEvent) {
          console.error(
            'Client-side error during registration:',
            err.error.message
          );
        } else {
          console.error(
            `Server returned code ${err.status}, body was:`,
            err.error
          );
        }
      },
    });
  }
}
