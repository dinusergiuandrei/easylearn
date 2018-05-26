import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  notification = null;
  constructor() {}

  push(notification: any) {
    notification.interval = setTimeout(() => {
      this.remove();
    }, 5000);
    this.notification = notification;
  }

  remove() {
    if (this.notification) {
      clearInterval(this.notification.interval);
      this.notification = false;
    }
  }
}