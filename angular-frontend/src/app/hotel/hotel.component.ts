import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Hotel, Feature } from './hotel';
import { NgForm } from '@angular/forms';
import { HotelService } from './hotel.service';
import {TemplateRef, ViewChild} from '@angular/core';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  //type of templates
  @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any>;
     
  hotels: Hotel[];
  // newHotel: Hotel = new Hotel();
  editing: boolean = false;
  isNewHotel: boolean = false;
  editingHotel: Hotel = new Hotel();
  features = Feature
  page: number = 1;
  count: number = 4;

  constructor(private cdref: ChangeDetectorRef,
              private hotelService: HotelService,
              private userService: UserService) {}

  ngOnInit(): void {
    this.getHotels();
  }

  ngAfterContentChecked() {
    this.cdref.detectChanges();
     }

  keys() : Array<string> {
    var keys = Object.keys(this.features);
    return keys.slice(keys.length / 2);
}

  getHotels(): void {
    this.hotelService.getHotels()
    .then(hotels => this.hotels = hotels );
  }

  // загружаем один из двух шаблонов
  loadTemplate(hotel: Hotel) {
    if (this.editingHotel && this.editingHotel.id == hotel.id) {
        return this.editTemplate;
    } else {
        return this.readOnlyTemplate;
    }
}

addHotel() {
  this.editingHotel = new Hotel();
  this.hotels.unshift(this.editingHotel);
  this.isNewHotel = true;
}


 createHotel(hotelForm: NgForm): void {
   if (this.isNewHotel){
    // if(this.editingHotel.id == null){
    this.hotelService.createHotel(this.editingHotel)
      .then(createHotel => {        
        // hotelForm.reset();
        // this.newHotel = new Hotel();
        this.hotels.push(createHotel)
      });
      this.clearEditing();
    } else {
      this.updateHotel(this.editingHotel);
    }
    this.getHotels();
  }

  deleteHotel(id: string): void {
    this.hotelService.deleteHotel(id)
    .then(() => {
      this.hotels = this.hotels.filter(hotel => hotel.id != id);
    });
  }

  updateHotel(hotelData: Hotel): void {
    console.log(hotelData);
    this.hotelService.updateHotel(hotelData)
    .then(updatedHotel => {
      let existingHotel = this.hotels.find(hotel => hotel.id === updatedHotel.id);
      Object.assign(existingHotel, updatedHotel);
      this.clearEditing();
    });
  }

  editHotel(hotelData: Hotel): void {
    // if( this.editing = false){
    if(this.editingHotel == null){
      this.editingHotel = new Hotel();
    }
    this.editing = true;
    Object.assign(this.editingHotel, hotelData);
  // }
}

  clearEditing(): void {
    this.editingHotel =  null;
    this.editing = false;
    this.isNewHotel = false;
    this.getHotels();
  }
}
