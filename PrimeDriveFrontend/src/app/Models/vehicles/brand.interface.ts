/**
 * Interface representing a vehicle brand.
 * Contains brand metadata including founding year, logo URL, and association with a holding.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface Brand {
  id: string;
  holdingId: string;
  founding: number;
  logo: string;
  name: string;
}
