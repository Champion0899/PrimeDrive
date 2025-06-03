import { Doors } from './doors.interface';
import { Engine } from './engine.interface';
import { Fuel } from './fuel.interface';
import { Seats } from './seats.interface';
import { Specs } from './specs.interface';

/**
 * Extended interface combining base technical specs with detailed configuration.
 * Includes engine, fuel, door, and seat specifications for a complete technical profile.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface SpecsWithDetails extends Specs {
  engine: Engine;
  fuel: Fuel;
  doors: Doors;
  seats: Seats;
}
