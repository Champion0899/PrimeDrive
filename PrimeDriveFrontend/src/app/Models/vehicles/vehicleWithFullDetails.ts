import { Brand } from './brand.interface';
import { User } from './user.interface';
import { Type } from './type.interface';
import { Vehicle } from './vehicle.interface';
import { Color } from './color.interface';
import { SpecsWithDetails } from './specsWithDetails.interface';

export interface VehicleWithFullDetails extends Vehicle {
  brand: Brand;
  type: Type;
  color: Color;
  specs: SpecsWithDetails;
  seller: User;
}
