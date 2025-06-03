import { Brand } from './brand.interface';
import { User } from './user.interface';
import { Type } from './type.interface';
import { Vehicle } from './vehicle.interface';
import { Color } from './color.interface';
import { SpecsWithDetails } from './specsWithDetails.interface';

/**
 * Extended interface representing a vehicle with full detail associations.
 * Combines basic vehicle data with related brand, type, color, technical specifications,
 * and seller information for a complete representation.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface VehicleWithFullDetails extends Vehicle {
  brand: Brand;
  type: Type;
  color: Color;
  specs: SpecsWithDetails;
  seller: User;
}
