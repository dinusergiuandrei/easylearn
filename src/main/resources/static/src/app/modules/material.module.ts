import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {
  MatProgressSpinnerModule,
  MatFormFieldModule,
  MatInputModule,
  MatAutocompleteModule,
  MatRadioModule,
  MatTabsModule,
  MatButtonModule
} from '@angular/material';

@NgModule({
  imports: [ CommonModule ],
  exports: [
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatInputModule,
    MatAutocompleteModule,
    MatRadioModule,
    MatTabsModule,
    MatButtonModule
  ]
})
export class MaterialModule { }
