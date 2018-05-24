import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemsCategoryComponent } from './problems-category.component';

describe('ProblemsCategoryComponent', () => {
  let component: ProblemsCategoryComponent;
  let fixture: ComponentFixture<ProblemsCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProblemsCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProblemsCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
