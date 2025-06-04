import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UsersService } from '../Services/users/users.service';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private usersService: UsersService, private router: Router) {}
  canActivate(): Promise<boolean> {
    return new Promise((resolve) => {
      this.usersService.getCurrentUser().subscribe({
        next: (user) => {
          if (user?.role?.includes('ADMIN')) {
            resolve(true);
          } else {
            this.router.navigate(['/vehicles']);
            resolve(false);
          }
        },
        error: () => {
          this.router.navigate(['/vehicles']);
          resolve(false);
        },
      });
    });
  }
}
