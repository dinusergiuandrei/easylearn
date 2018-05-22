import {MatTableDataSource, MatSort, MatPaginator} from '@angular/material';
import { Component, OnInit, ViewChild} from '@angular/core';


@Component({
  selector: 'app-problemtest',
  templateUrl: './problemtest.component.html',
  styleUrls: ['./problemtest.component.scss']
})
export class ProblemtestComponent implements OnInit {

  displayedColumns = ['id', 'language', 'timelimit', 'memorylimit','score'];
  displayedColumns_table2 = ['nr','time','message','score'];
  dataSource: MatTableDataSource<UserData>;
  dataSource_table2:MatTableDataSource<Tests>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
      

  constructor() {

    const users: UserData[] = [];
    for (let i = 1; i <= 100; i++) { users.push(createNewUser(i));
    
    }
    const tests: Tests[] = [];
    for (let j = 1; j<= 10; j++) { tests.push(createtests(j))};
    
    
   this.dataSource_table2=new MatTableDataSource(tests);
    
    this.dataSource = new MatTableDataSource(users);
    console.log(this.dataSource);
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }}

function createNewUser(id: number): UserData {

return {
    id: id.toString(),
    language: LANGUAGE[Math.round(Math.random() * (LANGUAGE.length - 1))],
    timelimit: Math.round(Math.random()).toFixed(1).toString() + ' seconds',
    memorylimit: Math.round(Math.random() * 32).toString() + ' MB',
    score:Math.round(Math.random() * 100).toString()
  };
}
const LANGUAGE=['C++','C','Java','Python']

export interface UserData {

 
id:string;
language: string;
timelimit: string;
 memorylimit: string;
 score:string;
}

export interface Tests {

 
  nr:string;
 time: string;
  message: string;
   score:string;
  }
const MESSAGE=['OK','NOT OK'] 
  function createtests(nr: number): Tests {

    return {
        nr: nr.toString(),
        message: MESSAGE[Math.round(Math.random() * (MESSAGE.length - 1))],
        time: Math.round(Math.random()).toFixed(1).toString() + ' seconds',
        score:Math.round(Math.random() * 10).toString()+'/' + Math.round(Math.random() * 10).toString()
      };
    }