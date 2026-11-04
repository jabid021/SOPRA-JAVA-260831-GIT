import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilierePage } from './filiere-page';

describe('FilierePage', () => {
  let component: FilierePage;
  let fixture: ComponentFixture<FilierePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilierePage],
    }).compileComponents();

    fixture = TestBed.createComponent(FilierePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
