import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcSubmit } from './ajc-submit';

describe('AjcSubmit', () => {
  let component: AjcSubmit;
  let fixture: ComponentFixture<AjcSubmit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcSubmit],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcSubmit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
