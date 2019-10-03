export class Tour {
    id: string;
    photo: string;
    date: Date;
    duration: number;
    description: string;
    cost: number;
    hotel: string;
    country: string;
    tour_type: TourType
}

export enum TourType {
    ECONOM,
    ALL_INCLUSIVE,
    ONLY_BREAKFAST
}