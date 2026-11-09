import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcSelect } from './ajc-select';

describe('AjcSelect', () => {
  let component: AjcSelect;
  let fixture: ComponentFixture<AjcSelect>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcSelect],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcSelect);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
