import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatiereV2Page } from './matiere-v2-page';

describe('MatiereV2Page', () => {
  let component: MatiereV2Page;
  let fixture: ComponentFixture<MatiereV2Page>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatiereV2Page],
    }).compileComponents();

    fixture = TestBed.createComponent(MatiereV2Page);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
