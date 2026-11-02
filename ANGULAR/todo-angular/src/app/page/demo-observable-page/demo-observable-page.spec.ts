import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoObservablePage } from './demo-observable-page';

describe('DemoObservablePage', () => {
  let component: DemoObservablePage;
  let fixture: ComponentFixture<DemoObservablePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemoObservablePage],
    }).compileComponents();

    fixture = TestBed.createComponent(DemoObservablePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
