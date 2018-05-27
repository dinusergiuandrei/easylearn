import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { SubmitSolutionService, ProblemService } from '../../../services';
import { SubmissionRequestModel, ProblemModel, SourceModel } from '../../../shared';

import { FormGroup, FormBuilder } from '@angular/forms';

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

    public responseText;
    public submisionText;

    constructor(
        private submitService: SubmitSolutionService,
        private problemService: ProblemService,
        private route: ActivatedRoute,
        private router: Router) {
        this.title = 'Problem title';
        this.author = 'Robert Maftei';
        this.difficulty = 'Easy';

        this.route.queryParams.subscribe(params => { this.problemId = params['problemId']; });

        this.submitRequestModel = new SubmissionRequestModel();
        this.submitRequestModel.mainSource = 'main';
        this.submitRequestModel.problemId = this.problemId;
        this.submitRequestModel.userId = 1;
        this.submitRequestModel.sources = new Array<SourceModel>();
        this.sourceFile = new SourceModel;
        console.log(this.submitRequestModel);
    }
    public e;
    public sourceFile: SourceModel;
    public strUser;
    public ngOnInit(): void {
        this.problemService.getProblem(this.problemId).subscribe((result: ProblemModel) => {
            this.problemModel = result;
        },
            err => {
                this.router.navigate(['/error404']);
            });


        this.responseText = 'If you choose to code in java, you must have the main class.';
    }

    public submitSolution(): void {

        this.e = document.getElementById('languageSelector');
        this.strUser = this.e.options[this.e.selectedIndex].value;
        console.log(this.e);
        this.submitRequestModel.language = this.strUser;
        if (this.sourceFile.content == null) {
            this.sourceFile.content = '';
        }

        if (this.strUser === 'Java') {
            this.submitRequestModel.mainSource = 'main.java';
            this.sourceFile.fileName = this.submitRequestModel.mainSource;
        } else if (this.strUser === 'Cpp') {
            this.submitRequestModel.mainSource = 'main.cpp';
            this.sourceFile.fileName = this.submitRequestModel.mainSource;
        } else if (this.strUser === 'Python') {
            this.submitRequestModel.mainSource = 'main.py';
            this.sourceFile.fileName = this.submitRequestModel.mainSource;
        }

        let submissionID;

        console.log(this.sourceFile);
        this.submitRequestModel.sources.push(this.sourceFile);

        this.submitService.submitSolution(this.submitRequestModel).subscribe(res => {
            console.log(res);
        });

        this.submitService.getSubmision(submissionID).subscribe(res => {
            console.log(res);
        });

    }
}
