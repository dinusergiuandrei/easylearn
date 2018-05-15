import { Component, OnInit } from '@angular/core';
import {
  Router,
  Event,
  RouteConfigLoadStart,
  RouteConfigLoadEnd
} from '@angular/router';
import { LoadingScreenService } from './services/loading-screen.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private router: Router, private screen: LoadingScreenService) {}

  ngOnInit() {
    this.router.events.subscribe((event: Event) => {
      if (event instanceof RouteConfigLoadStart) {
        this.screen.start();
      }
      if (event instanceof RouteConfigLoadEnd) {
        this.screen.stop();
      }
    });
  }
}
