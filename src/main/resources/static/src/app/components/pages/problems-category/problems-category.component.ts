import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ProblemService} from '../../../services/problem.service'
@Component({
  selector: 'app-problems-category',
  templateUrl: './problems-category.component.html',
  styleUrls: ['./problems-category.component.scss']
})
export class ProblemsCategoryComponent implements OnInit {

  public p;
  public title;
  public content;

  constructor(private route: ActivatedRoute, private problem:ProblemService) { 
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.title = params.get('id');
  
    });
    if (this.title != "algorithms" && this.title != "data_structures" && this.title != "mathematics") {
      this.title = "Wrong";
    }
    else {
      this.title = this.title.charAt(0).toUpperCase() + this.title.slice(1);
    }
    this.problem.request(2) .subscribe(
      res => {
        console.log(res);
      },
      err => {
        console.log(err);
      }
    )
    this.content = {
      problems: [
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.2 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "312321312mb",
          time: "0.2231 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.221321432 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.123122 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.212312 seconds",
          solved: "Solved"
        },
        {
          title: "Easy C++",
          statement: "Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.",
          memory: "32mb",
          time: "0.21231 seconds",
          solved: "Solved"
        }
      ]
    }
  }
}
