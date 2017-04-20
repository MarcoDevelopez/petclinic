import { TestBed, inject } from '@angular/core/testing';

import { PetclinicService } from './petclinic.service';

describe('PetclinicService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PetclinicService]
    });
  });

  it('should ...', inject([PetclinicService], (service: PetclinicService) => {
    expect(service).toBeTruthy();
  }));
});
