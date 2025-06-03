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
  lengthMillimeters: number;
  widthMillimeters: number;
  heightMillimeters: number;
  trunkInLiterMin: number;
  trunkInLiterMax: number;
  zeroToHundredInSeconds: number;
  topSpeedInKmH: number;
  consumptionHundredInX: number;
  coTwoEmissionsInGPerKm: number;
  cubicCapacity: number;
  doorsId: string;
  seatsId: string;
  engineId: string;
  fuelsId: string;
}
