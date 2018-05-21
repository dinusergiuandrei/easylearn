import { Component, OnInit } from '@angular/core';
 

@Component({
  selector: 'app-testimonials',
  templateUrl:'./testimonials.component.html',
  styleUrls: ['./testimonials.component.scss']
})


export class TestimonialsComponent implements OnInit {
  

  items: Array<any> = [
    {
      "photo": "../../../../assets/images/user_default.png",
      "name":"Birsan Ioana",
      "despre":"“You guys are the best! “ ",
      "slidenumber":"1"},

   
    {
      "photo": "../../../../assets/images/user_default.png",
      "name":"Lazar Cosmin-Alexandru",
      "despre":"“Keep up the great work!“ ",
      "slidenumber":"2",
    },

    {
      "photo": "../../../../assets/images/user_default.png",
      "name":"Neagu Oana-Andreea",
      "despre":"“You are doing a great job, and up to date with technology“ ",
      "slidenumber":"3",
    },
  
  ]
    
   

  
  constructor() { }

  slideIndex = 0;


  plusSlides(n)
{ 
  this.showSlides(this.slideIndex+=n);
}

currentSlide(n)
{
  this.slideIndex=n;
  this.showSlides(this.slideIndex);
  
 
}


 showSlides(n)
{
  var i;
  var j;
  var el = document.getElementsByClassName("testimonials-slides-display_off");
  var el2 = document.getElementsByClassName("testimonials-slides-display_on");

  for (i = 0; i < el.length; i++) 
     el[i].className="testimonials-slides-display_off";
  for (j = 0; j < el2.length; j++) 
     el2[j].className="testimonials-slides-display_off";
  
  if(n>el.length-1)
    this.slideIndex = 0;
    
else 
  if(n<0)
    this.slideIndex = el.length-1;
     
  el[this.slideIndex].className="testimonials-slides-display_on";


   

  
}


  ngOnInit() {
   }
   
}

    
  