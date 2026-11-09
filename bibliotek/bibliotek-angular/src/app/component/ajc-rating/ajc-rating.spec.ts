import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcRating } from './ajc-rating';

describe('AjcRating', () => {
  let component: AjcRating;
  let fixture: ComponentFixture<AjcRating>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcRating],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcRating);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
