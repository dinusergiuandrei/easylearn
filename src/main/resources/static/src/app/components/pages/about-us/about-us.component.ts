import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.scss']
})
export class AboutUsComponent implements OnInit {
  public team;

  constructor() { }

  ngOnInit() {
    this.team = {
      members: [
        {
          photo: "../../../../assets/images/team/andro_bianca.jpg",
          name: "Andro Bianca",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/user_default.png",
          name: "Birsan Ioana",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/borceanu_florin.png",
          name: "Borceanu Florin",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/team/ceicoschi_gabriel.png",
          name: "Ceicoschi Gabriel",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/team/cojocariu_oana.jpg",
          name: "Cojocariu Oana",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/dinu_sergiu.jpg",
          name: "Dinu Sergiu",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/gensthaler_octavian.png",
          name: "Gensthaler Octavian",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/lazar_alexandru.jpg",
          name: "Lazar Alexandru",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/luca_alexandru.jpg",
          name: "Luca Alexandru",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/maftei_robert.png",
          name: "Maftei Robert",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/team/mihai_catalin.jpg",
          name: "Mihai Catalin",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/mirza_bogdan.jpg",
          name: "Mirza Bogdan",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/neagu_oana.jpg",
          name: "Neagu Oana",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/onica_viorel.png",
          name: "Onica Viorel",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/user_default.png",
          name: "Pintilii Rares",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/ripan_vladimir.jpg",
          name: "Ripan Vladimir",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/sonea_adrian.jpg",
          name: "Sonea Adrian",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/sustac_andreea.jpg",
          name: "Sustac Andreea",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/tanasuca_bogdan.png",
          name: "Tanasuca Bogdan",
          role: "Front-end"
        },
        {
          photo: "../../../../assets/images/team/ticlos_olimpia.jpg",
          name: "Ticlos Olimpia",
          role: "Compilator"
        },
        {
          photo: "../../../../assets/images/team/timofti_alexandru.jpg",
          name: "Timofti Alexandru",
          role: "Management"
        },
        {
          photo: "../../../../assets/images/team/zapan_calin.png",
          name: "Zapan Calin",
          role: "Front-end"
        }
      ]
    }
  }
}
