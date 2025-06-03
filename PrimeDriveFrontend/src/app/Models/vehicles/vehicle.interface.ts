/**
 * Interface representing a vehicle entity.
 * Contains general information about a vehicle including price, condition,
 * mileage, year, associated brand, type, specs, color, and seller reference.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface Vehicle {
  id: string;
  name: string;
  price: number;
  year: number;
  image: string;
  mileage: number;
  condition: string;
  vehicleHistory: string;
  brandsId: string;
  specsId: string;
  typesId: string;
  colorsId: string;
  sellerId: string;
}
