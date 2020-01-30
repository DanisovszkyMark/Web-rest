import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivationFailedPageComponent } from './activation-failed-page.component';

describe('ActivationFailedPageComponent', () => {
  let component: ActivationFailedPageComponent;
  let fixture: ComponentFixture<ActivationFailedPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivationFailedPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivationFailedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
