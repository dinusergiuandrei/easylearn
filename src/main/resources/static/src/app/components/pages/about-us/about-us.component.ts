import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.scss']
})
export class AboutUsComponent implements OnInit {

  team: { photo: string; name: string; role: string; }[];
  techs: string[];

  constructor() { }

  ngOnInit() {
    this.team = [
      {
        photo: '../../../../assets/images/team/andro_bianca.jpg',
        name: 'Andro Bianca',
        role: 'Front-end'
      },
      {
        photo: null,
        name: 'Bîrsan Ioana',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/borceanu_florin.png',
        name: 'Borceanu Florin',
        role: 'Front-end'
      },
      {
        photo: '../../../../assets/images/team/ceicoschi_gabriel.png',
        name: 'Ceicoschi Gabriel',
        role: 'Front-end'
      },
      {
        photo: '../../../../assets/images/team/cojocariu_oana.jpg',
        name: 'Cojocariu Oana',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/dinu_sergiu.jpg',
        name: 'Dinu Sergiu',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/gensthaler_octavian.png',
        name: 'Gensthaler Octavian',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/lazar_alexandru.jpg',
        name: 'Lazăr Alexandru',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/luca_alexandru.jpg',
        name: 'Luca Alexandru',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/maftei_robert.png',
        name: 'Maftei Robert',
        role: 'Front-end'
      },
      {
        photo: '../../../../assets/images/team/mihai_catalin.jpg',
        name: 'Mihai Cătălin',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/mirza_bogdan.jpg',
        name: 'Mîrza Bogdan',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/neagu_oana.jpg',
        name: 'Neagu Oana',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/onica_viorel.png',
        name: 'Onica Viorel',
        role: 'Front-end'
      },
      {
        photo: null,
        name: 'Pintilii Rareș',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/ripan_vladimir.jpg',
        name: 'Rîpan Vladimir',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/sonea_adrian.jpg',
        name: 'Sonea Adrian',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/sustac_andreea.jpg',
        name: 'Șuștac Andreea',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/tanasuca_bogdan.png',
        name: 'Tănăsucă Bogdan',
        role: 'Front-end'
      },
      {
        photo: '../../../../assets/images/team/ticlos_olimpia.jpg',
        name: 'Țiclos Olimpia',
        role: 'Compiler'
      },
      {
        photo: '../../../../assets/images/team/timofti_alexandru.jpg',
        name: 'Timofti Alexandru',
        role: 'Management'
      },
      {
        photo: '../../../../assets/images/team/zapan_calin.png',
        name: 'Zapan Călin',
        role: 'Front-end'
      }
    ];
    this.techs = ['html', 'css', 'sass', 'js', 'angular', 'java', 'spring'];
  }
}
