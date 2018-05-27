import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProblemService } from '../../../services/problem.service'
import { ProblemModel } from '../../../shared';

@Component({
  selector: 'app-problems-category',
  templateUrl: './problems-category.component.html',
  styleUrls: ['./problems-category.component.scss']
})
export class ProblemsCategoryComponent implements OnInit {

  public p;
  public title;
  public content;
  public categoryID;
  public problems: Array<ProblemModel>;

  constructor(
    private route: ActivatedRoute,
    private problemService: ProblemService,
    private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.title = params.get('id');

    });
    if (this.title !== 'algorithms' && this.title !== 'data_structures' && this.title !== 'mathematics') {
      this.router.navigate(['/error404']);
    } else {
      if (this.title === 'algorithms') {
        this.categoryID = '2';
      }
      if (this.title === 'data_structures') {
        this.categoryID = '3';
      }
      if (this.title === 'mathematics') {
        this.categoryID = '1';
      }
      this.title = this.title.charAt(0).toUpperCase() + this.title.slice(1);
    }
    this.problemService.getProblems(this.categoryID).subscribe((res: any) => {
      this.problems = res;
    },
      err => {
        console.log(err);
      }
    );
  }

  public getProblemPage(problemId: number): void {
    this.router.navigate(['/problem/' + problemId]);
  }

}
