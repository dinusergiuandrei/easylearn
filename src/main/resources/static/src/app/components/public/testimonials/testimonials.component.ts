import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-testimonials',
  templateUrl: './testimonials.component.html',
  styleUrls: ['./testimonials.component.scss']
})

export class TestimonialsComponent implements OnInit {

  slideIndex = 0;

  items: Array<any> = [
    {
      'photo': null,
      'name': 'Birsan Ioana',
      'despre': '“You guys are the best! “ ',
      'slidenumber': 1
    },
    {
      'photo': null,
      'name': 'Lazar Cosmin-Alexandru',
      'despre': '“Keep up the great work!“ ',
      'slidenumber': 2
    },
    {
      'photo': null,
      'name': 'Neagu Oana-Andreea',
      'despre': '“You are doing a great job, and up to date with technology“ ',
      'slidenumber': 3
    },
  ];

  constructor() { }

  plusSlides(n) {
    this.showSlides(this.slideIndex += n);
  }

  currentSlide(n) {
    this.slideIndex = n;
    this.showSlides(this.slideIndex);
  }

  showSlides(n) {
    let i;
    let j;
    const el = document.getElementsByClassName('testimonials-slides-display_off');
    const el2 = document.getElementsByClassName('testimonials-slides-display_on');

    for (i = 0; i < el.length; i++) {
      el[i].className = 'testimonials-slides-display_off';
    }
    for (j = 0; j < el2.length; j++) {
      el2[j].className = 'testimonials-slides-display_off';
    }

    if (n > el.length - 1) {
      this.slideIndex = 0;
    } else if (n < 0) {
      this.slideIndex = el.length - 1;
    }

    el[this.slideIndex].className = 'testimonials-slides-display_on';
  }

  ngOnInit() { }

}
