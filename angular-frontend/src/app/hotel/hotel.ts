export class Hotel {
 id: string;
 name: string;
 stars: number;
 website: string;
 latitude: number;
 longitude: number;
 features: Feature
  }

  export enum Feature {
    SWIMMING_POOL,
    FREE_WIFI,
    PARKING,
    CHILDREN_AREA,
    RESTAURANT
}