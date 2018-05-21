import { Component } from "@angular/core";

@Component({
    selector: 'app-problem',
    styleUrls: ['./problem.component.scss'],
    templateUrl: './problem.component.html',
  })
  export class ProblemComponent {
      title: string;
      author: string;
      difficulty: string;
      description: string;
      editorOptions = {theme: 'vs-light', language: 'javascript', height: "400"};
      code: string = 'function x() {\nconsole.log("Hello world!");\n}';

      constructor() {
          this.title = "Problem title";
          this.author = "Robert Maftei";
          this.difficulty = "Easy";
      }
  }