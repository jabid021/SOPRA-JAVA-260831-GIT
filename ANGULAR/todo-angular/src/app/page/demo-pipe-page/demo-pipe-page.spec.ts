import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemoPipePage } from './demo-pipe-page';

describe('DemoPipePage', () => {
  let component: DemoPipePage;
  let fixture: ComponentFixture<DemoPipePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemoPipePage],
    }).compileComponents();

    fixture = TestBed.createComponent(DemoPipePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
