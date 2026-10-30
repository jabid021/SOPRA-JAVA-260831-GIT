import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonPremierComposant } from './mon-premier-composant';

describe('MonPremierComposant', () => {
  let component: MonPremierComposant;
  let fixture: ComponentFixture<MonPremierComposant>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MonPremierComposant],
    }).compileComponents();

    fixture = TestBed.createComponent(MonPremierComposant);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
