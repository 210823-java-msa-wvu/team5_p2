import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MysellComponent } from './mysell.component';

describe('MysellComponent', () => {
  let component: MysellComponent;
  let fixture: ComponentFixture<MysellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MysellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MysellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
