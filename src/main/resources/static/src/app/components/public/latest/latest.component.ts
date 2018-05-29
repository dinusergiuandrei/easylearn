import { MatTableDataSource, MatSort, MatPaginator } from '@angular/material';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ProblemService } from '../../../services/problem.service';
import { ProblemModel } from '../../../shared';

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


@Component({
  selector: 'app-latest',
  templateUrl: './latest.component.html',
  styleUrls: ['./latest.component.scss']
})
export class LatestComponent implements OnInit {
  displayedColumns = ['id', 'title', 'requirement', 'categoryId', 'timeLimit', 'memoryLimit', 'userId'];
  dataSource: MatTableDataSource<ProblemModel>;

  constructor(private problemService:ProblemService) {}

  ngOnInit() {
    this.problemService.getLatest().subscribe((res: any) => {
            this.dataSource = new MatTableDataSource(res.splice(res.length-5));
            
    },
      err => {
        console.log(err);
      }
    );
  }

}