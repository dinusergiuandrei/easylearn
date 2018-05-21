import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YourlatestComponent } from './yourlatest.component';

describe('YourlatestComponent', () => {
  let component: YourlatestComponent;
  let fixture: ComponentFixture<YourlatestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YourlatestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YourlatestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
