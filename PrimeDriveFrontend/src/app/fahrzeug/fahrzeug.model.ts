export interface Fahrzeug {
  id: number;
  image: string;
  marke: string;
  modell: string;
  baujahr: number;
  kilometerstand: number;
  preis: number;
  zustand?: string;
  fahrzeughistorie?: string;
}
