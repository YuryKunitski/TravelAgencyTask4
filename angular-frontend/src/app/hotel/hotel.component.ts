import { Component, OnInit } from '@angular/core';
import { Hotel } from './hotel';
import { NgForm } from '@angular/forms';
import { HotelService } from './hotel.service';
import {TemplateRef, ViewChild} from '@angular/core';

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
  newHotel: Hotel = new Hotel();
  editing: boolean = false;
  editingHotel: Hotel = new Hotel();

  constructor(private hotelService: HotelService) {}

  ngOnInit(): void {
    this.getHotels();
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

// добавление пользователя
addHotel() {
  this.editingHotel = new Hotel();
  this.hotels.push(this.editingHotel);
  this.editing = true;
}


 createHotel(hotelForm: Hotel): void {
   if (this.editing){
    this.hotelService.createHotel(this.editingHotel)

      .then(createHotel => {        
        // hotelForm.reset();
        // this.newHotel = new Hotel();
        this.hotels.unshift(createHotel)
      });
      this.editing = false;
      this.editingHotel = null;
    } else {
      this.updateHotel(this.editingHotel);
    }
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
    this.editing = true;
    Object.assign(this.editingHotel, hotelData);
  }

  clearEditing(): void {
    this.editingHotel =  new Hotel();
    this.editing = false;
  }
}
