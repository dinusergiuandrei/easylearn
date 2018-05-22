import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemtestComponent } from './problemtest.component';

describe('ProblemtestComponent', () => {
  let component: ProblemtestComponent;
  let fixture: ComponentFixture<ProblemtestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemtestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemtestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
