import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivationSuccessfullPageComponent } from './activation-successfull-page.component';

describe('ActivationSuccessfullPageComponent', () => {
  let component: ActivationSuccessfullPageComponent;
  let fixture: ComponentFixture<ActivationSuccessfullPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActivationSuccessfullPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivationSuccessfullPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
