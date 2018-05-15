import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatProgressSpinnerModule,
  MatFormFieldModule,
  MatInputModule,
  MatAutocomplete,
  MatAutocompleteModule
} from '@angular/material';

@NgModule({
  imports: [ CommonModule ],
  exports: [
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule
  ]
})
export class MaterialModule { }
