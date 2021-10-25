import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubmitDealComponent } from './submit-deal.component';

describe('SubmitDealComponent', () => {
  let component: SubmitDealComponent;
  let fixture: ComponentFixture<SubmitDealComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubmitDealComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubmitDealComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
