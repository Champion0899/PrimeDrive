/**
 * Interface representing a purchase transaction.
 * Contains identifiers for the purchase itself, the seller, the buyer, and the associated vehicle.
 *
 * Author: Fatlum Epiroti
 * Version: 1.0.1
 * Date: 2025-06-06
 */
export interface Purchases {
  id: string;
  sellerId: string;
  buyerId: string;
  vehicleId: string;
}
