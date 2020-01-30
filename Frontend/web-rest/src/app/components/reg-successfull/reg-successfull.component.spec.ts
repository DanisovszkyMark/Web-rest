import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegSuccessfullComponent } from './reg-successfull.component';

describe('RegSuccessfullComponent', () => {
  let component: RegSuccessfullComponent;
  let fixture: ComponentFixture<RegSuccessfullComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegSuccessfullComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegSuccessfullComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
