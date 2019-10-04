import { Hotel } from '../hotel/hotel';
import { Country } from '../country/country';


export class Tour {
    id: string;
    photo: string;
    date: Date;
    duration: number;
    description: string;
    cost: number;
    hotel: Hotel;
    country: Country;
    tour_type: TourType
}

export enum TourType {
    ECONOM,
    ALL_INCLUSIVE,
    ONLY_BREAKFAST
}