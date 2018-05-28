import {MatTableDataSource, MatSort, MatPaginator} from '@angular/material';
import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ProblemService} from '../../../services/problem.service';
import {ProblemModel} from '../../../shared';

@Component({
  selector: 'app-latest',
  templateUrl: './latest.component.html',
  styleUrls: ['./latest.component.scss']
})
export class LatestComponent implements OnInit {
  displayedColumns = ['id', 'category', 'description', 'timelimit', 'memorylimit', 'userId'];
  dataSource: MatTableDataSource<UserData>;
  dataSource2: MatTableDataSource<UserData>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  public categoryID = 2;
  public problems;

  constructor(private problemService: ProblemService) {
  }

  ngOnInit() {
    this.problemService.getProblems(this.categoryID).subscribe((res: any) => {
        this.problems = res;
        this.dataSource = new MatTableDataSource(res);
      },
      err => {
        console.log(err);
      }
    );
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}

function createNewUser(id: number): UserData {
  return {
    id: id.toString(),
    problems: PROBLEMS[Math.round(Math.random() * (PROBLEMS.length - 1))],
    description: DESCRIPTION[Math.round(Math.random() * (DESCRIPTION.length - 1))],
    category: CATEGORY[Math.round(Math.random() * (CATEGORY.length - 1))],
    timelimit: Math.round(Math.random()).toFixed(1).toString() + ' seconds',
    memorylimit: Math.round(Math.random() * 32).toString() + ' MB',
    author: AUTHOR[Math.round(Math.random() * (AUTHOR.length - 1))]
  };
}

const DESCRIPTION = ['Să se scrie o funcție C++ care să determine prima și ultima cifră a unui număr natural transmis ca parametru. Funcția va întoarce rezultatele prin intermediul unor parametri de ieşire.',
  'Să se scrie o funcție C++ care primește ca parametri două numere n și k și determină numărul format din primele k cifre ale lui n. Funcția va întoarce rezultatul prin intermediul unui parametru de ieşire.',
  'Să se scrie o funcție C++ care primește ca parametri două numere n și k și determină cel mai mare număr care poate fi scris cu k cifre ale lui n. Funcția va întoarce rezultatul prin intermediul unui parametru de ieşire.',
  'Se dă un şir cu n elemente, numere naturale. Să se afişeze elementele şirului pentru care suma cifrelor este divizibilă cu 3.',
  'Să se scrie o funcție C++ care să returneze suma factorialelor cifrelor unui număr natural transmis ca parametru.',
  'Să se scrie o funcție C++ care să returneze oglinditul unui număr natural transmis ca parametru.'];
const PROBLEMS = ['sumaMinMax', 'Dublare1', 'Element_SA', 'Suma cifrelor', 'FactorialF', 'Mutare1', 'Palindrom_Ciclic', 'Multiplu'];

const CATEGORY = ['Algorithms', 'Data Sturucture', 'Matemathics'];
const AUTHOR = ['Maia', 'Asher', 'Olivia', 'Atticus', 'Amelia', 'Jack',
  'Charlotte', 'Theodore', 'Isla', 'Oliver', 'Isabella', 'Jasper',
  'Cora', 'Levi', 'Violet', 'Arthur', 'Mia', 'Thomas', 'Elizabeth'];

export interface UserData {
  id: string;
  problems: string;
  description: string;
  category: string;
  timelimit: string;
  memorylimit: string;
  author: string;
}
