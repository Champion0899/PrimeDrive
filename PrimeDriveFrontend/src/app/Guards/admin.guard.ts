import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { UsersService } from '../Services/users/users.service';

/**
 * Guard that prevents access to routes unless the user has an 'ADMIN' role.
 * It checks the current user's role using the UsersService and redirects non-admins.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-06
 */
@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate {
  constructor(private usersService: UsersService, private router: Router) {}
  /**
   * Determines whether the current user has permission to activate a route.
   * Grants access only if the user has an 'ADMIN' role; otherwise, redirects to '/vehicles'.
   *
   * @returns Promise<boolean> Resolves to true if user is admin, false otherwise.
   */
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
