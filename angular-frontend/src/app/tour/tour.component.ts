import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Tour, TourType } from './tour';
import { NgForm } from '@angular/forms';
import { TourService } from './tour.service';
import {TemplateRef, ViewChild} from '@angular/core';
import { Country } from '../country/country';
import { CountryService } from '../country/country.service';
import { Hotel } from '../hotel/hotel';
import { HotelService } from '../hotel/hotel.service';
import { TourForm } from './tour-form';
import { UserService } from '../user/user.service';


@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.css']
})
export class TourComponent implements OnInit {

   //type of templates
   @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;
   @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any>;

  tours: Tour[];
  editing: boolean = false;
  isNewTour: boolean = false;
  editingTour: Tour = new Tour();
  tourType = TourType;
  countries: Country[];
  hotels: Hotel[];
  isSearching: boolean = false;
  tourForm: TourForm = new TourForm();
  page: number = 1;
  count: number = 4;
  key: string;
  reverse: boolean = false;

  constructor(private cdref: ChangeDetectorRef,
              private tourService: TourService,
              private countryService: CountryService,
              private hotelService: HotelService,
              private userService: UserService) { }

  ngOnInit() {
    this.getTours()
    this.getCountries()
    this.getHotels()
  }

  sort(key){
    this.key = key;
    this.reverse = !this.reverse;
    console.log("reverse - " + this.reverse)
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
     }

   // загружаем один из двух шаблонов
   loadTemplate(tour: Tour) {
    if (this.editingTour && this.editingTour.id == tour.id) {
        return this.editTemplate;
    } else {
        return this.readOnlyTemplate;
    }
}

compareFn(val1, val2): boolean {
  return val1 && val2 ? val1.id === val2.id : val1 === val2;
}

getToursBySearch(tourFormData: TourForm): void {
  this.tourService.getToursByCriteria(tourFormData)
  .then(tours => {
    this.tours = tours });

}

getCountries() : void {
 this.countryService.getCountries().then(countries => this.countries = countries);
}

getHotels() : void {
  this.hotelService.getHotels().then(hotels => this.hotels = hotels);
 }

  keys() : Array<string> {
    var keys = Object.keys(this.tourType);
    return keys.slice(keys.length / 2);
}

editSearhing() {
  this.isSearching = !this.isSearching;
   }

  getTours(): void {
    this.tourService.getTours()
    .then(tours => this.tours = tours );
  }

  addTour() {
    this.editingTour = new Tour();
    this.tours.unshift(this.editingTour);
    this.isNewTour = true;
  }

  createTour(tourForm: NgForm): void {
    if (this.isNewTour){
    this.tourService.createTour(this.editingTour)
      .then(createdTour => {        
       
        this.tours.push(createdTour)
      });
      this.clearEditing();
    } else {
      this.updateTour(this.editingTour);
    }
    this.getTours();
  }

  deleteTour(id: string): void {
    this.tourService.deleteTour(id)
    .then(() => {
      this.tours = this.tours.filter(tour => tour.id != id);
    });
  }

  updateTour(tourData: Tour): void {
    console.log(tourData);
    this.tourService.updateTour(tourData)
    .then(updatedTour => {
      let existingTour = this.tours.find(tour => tour.id === updatedTour.id);
      Object.assign(existingTour, updatedTour);
      this.clearEditing();
    });
  }

  editTour(tourData: Tour): void {
    if(this.editingTour == null){
      this.editingTour = new Tour();
    }
    this.editing = true;
    Object.assign(this.editingTour, tourData);
  }

  clearEditing(): void {
    this.editingTour = null;
    this.editing = false;
    this.isNewTour = false;
    this.getTours();
  }

}
