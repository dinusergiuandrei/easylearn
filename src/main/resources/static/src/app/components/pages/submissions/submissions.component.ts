import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {SubmitSolutionService} from '../../../services';
import {MatTableDataSource, MatSort, MatPaginator} from '@angular/material';
import {ProblemModel} from '../../../shared';
import {UserData} from '../top/top.component';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-submissions',
  styleUrls: ['./submissions.component.scss'],
  templateUrl: './submissions.component.html',
})
export class SubmissionsComponent implements OnInit {
  displayedColumns = ['id', 'problem', 'user', 'date', 'language', 'state', 'link'];
  dataSource: MatTableDataSource<ProblemModel>;

  constructor(private route: ActivatedRoute, private router: Router, private submissionService: SubmitSolutionService) {
  }

  public problemId;
  public submissions;
  public sub;

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.problemId = params['problemId'];
    });
    this.submissionService.getSubmissionsByProblem(this.problemId).subscribe((result: ProblemModel[]) => {
        this.submissions = result;
        this.dataSource = new MatTableDataSource(result);
        console.log(this.dataSource);
      },
      err => {
        this.router.navigate(['/403']);
      });
  }
}

export interface Submissions {
  id: number;
  problem: string;
  user: string;
  language: string;
  date: string;
  state: string;
  link: string;
}
