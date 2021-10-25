import { TestBed } from '@angular/core/testing';

import { SubmitDealService } from './submit-deal.service';

describe('SubmitDealService', () => {
  let service: SubmitDealService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SubmitDealService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
