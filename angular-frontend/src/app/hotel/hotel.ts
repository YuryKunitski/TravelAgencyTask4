export class Hotel {
 id: string;
 name: string;
 stars: number;
 website: string;
 latitude: number;
 longitude: number;
 features: string
  }

  export enum Feature {
    SWIMMING_POOL,
    FREE_WIFI,
    PARKING,
    CHILDREN_AREA,
    RESTAURANT
}