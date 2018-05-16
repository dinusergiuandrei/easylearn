import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatProgressSpinnerModule,
  MatFormFieldModule,
  MatInputModule,
  MatAutocomplete,
  MatAutocompleteModule,
  MatRadioButton,
  MatRadioModule
} from '@angular/material';

@NgModule({
  imports: [ CommonModule ],
  exports: [
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatRadioModule
  ]
})
export class MaterialModule { }
