import { Component, OnInit } from '@angular/core';
import { Country } from './country';
import { NgForm } from '@angular/forms';
import { CountryService } from './country.service';

@Component({
  selector: 'country',
  templateUrl: './country.component.html'
})

export class CountryComponent implements OnInit {
  countries: Country[];
  newCountry: Country = new Country();
  editing: boolean = false;
  editingCountry: Country = new Country();

  constructor(
    private countryService: CountryService,
  ) {}

  ngOnInit(): void {
    this.getCountries();
  }

  getCountries(): void {
    this.countryService.getCountries()
    .then(countries => this.countries = countries );
  }

  createCountry(countryForm: NgForm): void {
    this.countryService.createCountry(this.newCountry)
      .then(createCountry => {        
        countryForm.reset();
        // this.newCountry = new Country();
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
