import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RunService } from '../../../services';
import { RunModel } from '../../../shared';
import { FormGroup, FormBuilder } from '@angular/forms';


@Component({
    selector: 'app-runs',
    styleUrls: ['./runs.component.scss'],
    templateUrl: './runs.component.html',
})

export class RunsComponent implements OnInit {
    constructor(private route: ActivatedRoute, private router: Router, private runService: RunService) { }

    public submissionId;
    public runs;

    ngOnInit() {
        this.route.queryParams.subscribe(params => { this.submissionId = params['submissionId']; });

        this.runService.getRunsBySubmission(this.submissionId).subscribe((result: RunModel) => {
            this.runs = result;
            console.log(this.runs);
        },
            err => {
                console.log(err);
                this.router.navigate(['/403']);
            });
    }
}
