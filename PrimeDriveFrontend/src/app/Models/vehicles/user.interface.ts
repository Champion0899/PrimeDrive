/**
 * Interface representing a user in the system.
 * Contains personal and contact information along with the user's role and identity.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface User {
  id: string;
  username: string;
  role: string;
  eMail: string;
  birthDate: string;
  address: string;
  zipCode: string;
  city: string;
  country: string;
  phoneNumber: string;
}
