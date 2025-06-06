/****
 * Interface representing a vehicle holding (corporate group).
 * Describes identifying information such as ID, name, founding year, and logo.
 *
 * Author: Fatlum Epiroti
 * Version: 1.1.0
 * Date: 2025-06-06
 */
export interface Holding {
  id: string;
  founding: number;
  logo: string;
  name: string;
}
