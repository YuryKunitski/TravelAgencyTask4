import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelComponent } from './hotel.component';
require('zone.js');
import 'reflect-metadata';
import { Component, ChangeDetectorRef } from  "@angular/core";
import {inject} from  "@angular/core/testing";
var chai = require('chai');
var spies = require('chai-spies');
chai.use(spies);
var assert = chai.assert;
var expect = chai.expect;
import { HotelService } from '../hotel/hotel.service';
import { UserService } from '../user/user.service';

describe('HotelComponent', () => {
  let component: HotelComponent;
  let fixture: ComponentFixture<HotelComponent>;

  // beforeEach(inject([ChangeDetectorRef, HotelService,UserService],(HotelService)=>{
  //   component = new HotelComponent(ChangeDetectorRef, HotelService, UserService );
  // }));

  // beforeEach(async(() => {
  //   TestBed.configureTestingModule({
  //     declarations: [ HotelComponent ]
  //   })
  //   .compileComponents();
  // }));

  // beforeEach(() => {
  //   fixture = TestBed.createComponent(HotelComponent);
  //   component = fixture.componentInstance;
  //   fixture.detectChanges();
  // });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
