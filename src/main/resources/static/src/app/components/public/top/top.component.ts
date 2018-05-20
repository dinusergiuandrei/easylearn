import {MatTableDataSource, MatSort, MatPaginator} from '@angular/material';
import { Component, OnInit, ViewChild} from '@angular/core';

/**
 * @title Table with sorting
 */
@Component({
  selector: 'app-top',
  styleUrls: ['./top.component.scss'],
  templateUrl: './top.component.html',
})
export class TopComponent implements OnInit {
  displayedColumns = ['id', 'name', 'languages', 'solutions', 'solved', 'source'];
  dataSource: MatTableDataSource<UserData>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor() {
    // Create 100 users
    const users: UserData[] = [];
    for (let i = 1; i <= 100; i++) { users.push(createNewUser(i)); }

    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(users);
    console.log(this.dataSource);
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }
}

function createNewUser(id: number): UserData {
  const name =
    NAMES[Math.round(Math.random() * (NAMES.length - 1))] + ' ' +
    NAMES[Math.round(Math.random() * (NAMES.length - 1))].charAt(0) + '.';

  return {
    id: id.toString(),
    name: name,
    languages: LANGUAGES[Math.round(Math.random() * (LANGUAGES.length - 1))],
    solutions: Math.round(Math.random() * 100).toString(),
    solved: Math.round(Math.random() * 100).toString(),
    source: Math.round(Math.random() * 100).toString(),
  };
}

/** Constants used to fill up our data base. */
const LANGUAGES = ['C', 'C++', 'Java', 'C#', 'Pascal', 'Python', 'JavaScript'];
const NAMES = ['Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack',
  'Charlotte', 'Theodore', 'Isla', 'Oliver', 'Isabella', 'Jasper',
  'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'];

export interface UserData {
  id: string;
  name: string;
  languages: string;
  solutions: string;
  solved: string;
  source: string;
}
