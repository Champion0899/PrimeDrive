import { Doors } from './doors.interface';
import { Engine } from './engine.interface';
import { Fuel } from './fuel.interface';
import { Seats } from './seats.interface';
import { Specs } from './specs.interface';

export interface SpecsWithDetails extends Specs {
  engine: Engine;
  fuel: Fuel;
  doors: Doors;
  seats: Seats;
}
