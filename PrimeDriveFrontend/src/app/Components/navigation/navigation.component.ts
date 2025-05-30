import { Component, inject, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginDialogComponent } from '../auth/login-dialog/login-dialog.component';
import { RegisterDialogComponent } from '../auth/register-dialog/register-dialog.component';
import { AuthService } from '../../Services/auth/auth.service';

@Component({
  selector: 'app-navigation',
  standalone: true,
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss',
})
export class NavigationComponent implements OnInit {
  protected isLoggedIn: boolean = false;
  private dialog = inject(MatDialog);
  private authService = inject(AuthService);

  public ngOnInit(): void {
    this.checkLoginStatus();
  }

  public openLoginDialog() {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '400px',
      data: { message: 'Hello from NavigationComponent!' },
    });

    dialogRef.afterClosed().subscribe((result: boolean) => {
      if (result === true) {
        this.checkLoginStatus();
      }
    });
  }

  public openRegisterDialog() {
    this.dialog.open(RegisterDialogComponent, {
      width: '400px',
      data: { message: 'Hello from NavigationComponent!' },
    });
  }

  public logout() {
    this.authService.logout();
    this.isLoggedIn = false;
  }

  private checkLoginStatus(): void {
    this.isLoggedIn = this.authService.isAuthenticated();
  }
}
