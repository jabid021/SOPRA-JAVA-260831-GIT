import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePrenoms } from './liste-prenoms';

describe('ListePrenoms', () => {
  let component: ListePrenoms;
  let fixture: ComponentFixture<ListePrenoms>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListePrenoms],
    }).compileComponents();

    fixture = TestBed.createComponent(ListePrenoms);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
