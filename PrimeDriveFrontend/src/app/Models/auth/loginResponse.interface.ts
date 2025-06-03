/**
 * Interface representing the response returned after a successful login.
 * Contains the authenticated user's ID and the JWT token for session handling.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface LoginResponse {
  userId: string;
  token: string;
}
