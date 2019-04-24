import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import {GifService} from '../services/gif.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  public favourites: any[];

  constructor(private gifService : GifService) { }

     ngOnInit() {
       this.gifService.getFavourites().then(res => {
         if (res) {this.favourites = res;	 }
         console.log(res);
      });
  }

      public removeFromFavourites(id: string) {
      this.gifService.removeFromFavourites(id) .then(() => this.ngOnInit());
    }

}