import { TestBed } from '@angular/core/testing';

import { BasicAuthInterceptService } from './basic-auth-intercept.service';

describe('BasicAuthInterceptService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BasicAuthInterceptService = TestBed.get(BasicAuthInterceptService);
    expect(service).toBeTruthy();
  });
});
