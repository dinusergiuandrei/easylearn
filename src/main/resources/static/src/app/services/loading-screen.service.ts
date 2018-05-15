import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingScreenService {
  public loading = false;

  constructor() { }

  start() { this.loading = true; }

  stop() { this.loading = false; }

  toggle() { this.loading = !this.loading; }
}
