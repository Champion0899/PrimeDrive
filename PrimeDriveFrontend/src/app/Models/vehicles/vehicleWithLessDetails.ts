import { Brand } from './brand.interface';
import { Type } from './type.interface';
import { Vehicle } from './vehicle.interface';

export interface VehicleWithLessDetails extends Vehicle {
  brand: Brand;
  type: Type;
}
