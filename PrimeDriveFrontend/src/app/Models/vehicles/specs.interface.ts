/**
 * Interface representing the technical specifications of a vehicle.
 * Includes performance data, dimensions, consumption, emissions,
 * and foreign keys linking to other specification models.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.0
 * Date: 2025-06-03
 */
export interface Specs {
  id: string;
  powerKw: number;
  powerPs: number;
  lengthMillimeter: number;
  widthMillimeter: number;
  heightMillimeter: number;
  trunkInLiterMin: number;
  trunkInLiterMax: number;
  zeroToHundredInSeconds: number;
  topSpeedInKmh: number;
  consumptionHundredInX: number;
  coTwoEmissionInGPerKm: number;
  cubicCapacity: number;
  doorsId: string;
  seatsId: string;
  engineId: string;
  fuelsId: string;
}
