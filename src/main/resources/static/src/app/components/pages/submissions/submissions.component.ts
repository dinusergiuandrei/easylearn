import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SubmitSolutionService, ProblemService } from '../../../services';
import { SubmissionRequestModel, ProblemModel, SourceModel, SubmissionModel } from '../../../shared';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
    selector: 'app-submissions',
    styleUrls: ['./submissions.component.scss'],
    templateUrl: './submissions.component.html',
})
export class SubmissionsComponent{

    constructor(private route: ActivatedRoute, private router: Router, private submissionService: SubmitSolutionService) { }
  
    public problemId;
    public submissions;// = new Array<SubmissionModel>();

    ngOnInit() {
        this.route.queryParams.subscribe(params => { this.problemId = params['problemId'] });

        this.submissionService.getSubmissionsByProblem(this.problemId).subscribe((result: ProblemModel) => {
            this.submissions = result;
            console.log(this.submissions);
        },
            err => {
                this.router.navigate(['/notallowed'])
            });
    }
  
  }