import { TestBed, inject } from '@angular/core/testing';

import { ClimaService } from './clima.service';

describe('ClimaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClimaService]
    });
  });

  it('should be created', inject([ClimaService], (service: ClimaService) => {
    expect(service).toBeTruthy();
  }));
});
