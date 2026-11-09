import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcFormField } from './ajc-form-field';

describe('AjcFormField', () => {
  let component: AjcFormField;
  let fixture: ComponentFixture<AjcFormField>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcFormField],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcFormField);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
