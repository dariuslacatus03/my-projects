import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseDestinationComponent } from './browse-destination.component';

describe('BrowseDestinationComponent', () => {
  let component: BrowseDestinationComponent;
  let fixture: ComponentFixture<BrowseDestinationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowseDestinationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BrowseDestinationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
