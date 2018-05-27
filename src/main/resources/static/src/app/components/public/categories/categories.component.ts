import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  categories: { name: string; path: string; route: string; }[];

  constructor() {
    this.categories = [
      {
        name: 'Algorithms',
        path: '../../../../assets/images/categories/algorithms.png',
        route: '/problems-category/algorithms'
      },
      {
        name: 'Data Structures',
        path: '../../../../assets/images/categories/data-structures.png',
        route: '/problems-category/data_structures'
      },
      {
        name: 'Mathematics',
        path: '../../../../assets/images/categories/mathematics.png',
        route: '/problems-category/mathematics'
      }
    ];
  }

  ngOnInit() { }

}
