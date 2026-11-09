import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AjcModal } from './ajc-modal';

describe('AjcModal', () => {
  let component: AjcModal;
  let fixture: ComponentFixture<AjcModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AjcModal],
    }).compileComponents();

    fixture = TestBed.createComponent(AjcModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
