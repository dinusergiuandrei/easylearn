import { Component } from '@angular/core';
import { ProblemService } from '../../../services/problem.service';

@Component({
    selector: 'app-add-problem',
    styleUrls: ['./add-problem.component.scss'],
    templateUrl: './add-problem.component.html',
})
export class AddProblemComponent {
    author: string;
    description: string;
    input: string;
    output: string;
    inputExample: string;
    outputExample: string;
    restrictions: string;
    category: string;

    constructor(private problemService: ProblemService) {
        this.author = '';
        this.description = '';
        this.input = '';
        this.output = '';
        this.inputExample = '';
        this.outputExample = '';
        this.restrictions = '';
        this.category = '';
    }

    onClickSubmit() {
        // apeleaza serviciu.post aici
    }
}
