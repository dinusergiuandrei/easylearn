import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SubmitSolutionService, ProblemService } from '../../../services';
import { SubmissionRequestModel, ProblemModel, SourceModel} from '../../../shared';

@Component({
    selector: 'app-problem',
    styleUrls: ['./problem.component.scss'],
    templateUrl: './problem.component.html',
})
export class ProblemComponent implements OnInit {

    public title: string;
    public author: string;
    public difficulty: string;
    public description: string;
    public editorOptions = { theme: 'vs-light', language: 'javascript', height: '400' };
    public code = 'function x() {\nconsole.log("Hello world!");\n}';

    public submitRequestModel: SubmissionRequestModel;
    public problemModel: ProblemModel;
    public activatedRoute: ActivatedRoute;
    public problemId: number;

    constructor(private submitService: SubmitSolutionService, private problemService: ProblemService, private route: ActivatedRoute) {
        this.title = 'Problem title';
        this.author = 'Robert Maftei';
        this.difficulty = 'Easy';

        this.route.queryParams.subscribe(params => {this.problemId = params['problemId']});

        this.submitRequestModel = new SubmissionRequestModel();
        this.submitRequestModel.mainSource = 'function x() {\nconsole.log("Hello world!");\n}';
        this.submitRequestModel.problemId = this.problemId;
        this.submitRequestModel.language = 'Java';
        this.submitRequestModel.userId = 1;
        this.submitRequestModel.sources = new Array<SourceModel>();
    }

    public ngOnInit(): void {
        this.problemService.getProblem(this.problemId).subscribe((result: ProblemModel) => {
            this.problemModel = result;
        });
    }

    public submitSolution(): void{
        this.submitService.submitSolution(this.submitRequestModel).subscribe(res => {
            console.log(res);
        });
    }
}
