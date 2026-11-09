import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcTextField } from './ajc-text-field';

describe('AjcTextField', () => {
  let component: AjcTextField;
  let fixture: ComponentFixture<AjcTextField>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcTextField],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcTextField);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
