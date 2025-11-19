import {Component, inject} from '@angular/core';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {AuthService} from '../../../Services/auth/auth.service';
import {FormsModule} from '@angular/forms';

/**
 * Component for rendering a login dialog with username and password inputs.
 * Utilizes Angular Material for styling and layout.
 *
 * When submitted, credentials are sent to the AuthService for authentication.
 * On success, the dialog is closed and emits a success flag.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
@Component({
  selector: 'app-login-dialog',
  standalone: true,
  imports: [
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
  ],
  templateUrl: './login-dialog.component.html',
  styleUrl: './login-dialog.component.scss',
})
export class LoginDialogComponent {
  private authService = inject(AuthService);
  private dialogRef = inject(MatDialogRef<LoginDialogComponent>);

  /** Holds the input username from the user. */
  protected username = '';
  /** Holds the input password from the user. */
  protected password = '';

  /**
   * Handles user login action.
   * Sends username and password to the AuthService and closes the dialog on success.
   * Logs errors to the console on failure.
   */
  protected login() {
    this.authService.login(this.username, this.password).subscribe({
      next: () => {
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Login failed', error);
      },
    });
  }
}
