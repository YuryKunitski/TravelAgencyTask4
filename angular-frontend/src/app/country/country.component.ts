import { Component, OnInit } from '@angular/core';
import { Country } from './country';
import { NgForm } from '@angular/forms';
import { CountryService } from './country.service';
import { UserService } from '../user/user.service';

@Component({
  selector: 'country',
  templateUrl: './country.component.html'
})

export class CountryComponent implements OnInit {
  countries: Country[];
  newCountry: Country = new Country();
  editing: boolean = false;
  editingCountry: Country = new Country();
  page: number = 1;
  count: number = 4;
  key: string = name;
  reverse: boolean = false;

  constructor(private countryService: CountryService,
              private userService: UserService) {}

  ngOnInit(): void {
    this.getCountries();
  }

  sort(key){
    this.key = key;
    this.reverse = !this.reverse;
  }

  getCountries(): void {
    this.countryService.getCountries()
    .then(countries => this.countries = countries );
  }

  createCountry(countryForm: NgForm): void {
    this.countryService.createCountry(this.newCountry)
      .then(createCountry => {        
        countryForm.reset();
        this.newCountry = new Country();
        this.countries.unshift(createCountry)
      });
  }

  deleteCountry(id: string): void {
    this.countryService.deleteCountry(id)
    .then(() => {
      this.countries = this.countries.filter(country => country.id != id);
    });
  }

  updateCountry(countryData: Country): void {
    console.log(countryData);
    this.countryService.updateCountry(countryData)
    .then(updatedCountry => {
      let existingCountry = this.countries.find(country => country.id === updatedCountry.id);
      Object.assign(existingCountry, updatedCountry);
      this.clearEditing();
    });
  }

  editCountry(countryData: Country): void {
    this.editing = true;
    Object.assign(this.editingCountry, countryData);
  }

  clearEditing(): void {
    this.editingCountry = new Country();
    this.editing = false;
  }
}