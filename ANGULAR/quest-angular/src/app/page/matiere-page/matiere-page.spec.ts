import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatierePage } from './matiere-page';

describe('MatierePage', () => {
  let component: MatierePage;
  let fixture: ComponentFixture<MatierePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatierePage],
    }).compileComponents();

    fixture = TestBed.createComponent(MatierePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
