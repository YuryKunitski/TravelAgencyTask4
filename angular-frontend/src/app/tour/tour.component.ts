import { Component, OnInit } from '@angular/core';
import { Tour, TourType } from './tour';
import { NgForm } from '@angular/forms';
import { TourService } from './tour.service';
import {TemplateRef, ViewChild} from '@angular/core';

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
  newTour: Tour = new Tour();
  editing: boolean = false;
  // isNewTour: boolean = false;
  editingTour: Tour = new Tour();
  toursType = TourType;

  constructor(private tourService: TourService) { }

  ngOnInit() {
    this.getTours()
  }

   // загружаем один из двух шаблонов
   loadTemplate(tour: Tour) {
    if (this.editingTour && this.editingTour.id == tour.id) {
        return this.editTemplate;
    } else {
        return this.readOnlyTemplate;
    }
}

  keys() : Array<string> {
    var keys = Object.keys(this.toursType);
    return keys.slice(keys.length / 2);
}

  getTours(): void {
    this.tourService.getTours()
    .then(tours => this.tours = tours );
  }

  // createTour(tourForm: NgForm): void {
  //   this.tourService.createTour(this.newTour)
  //     .then(createdTour => {        
  //       tourForm.reset();
  //       this.newTour = new Tour();
  //       this.tours.unshift(createdTour)
  //     });
  // }

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
    this.editing = true;
    Object.assign(this.editingTour, tourData);
  }

  clearEditing(): void {
    this.editingTour = new Tour();
    this.editing = false;
  }

}
