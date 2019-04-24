import { Location } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import "rxjs/add/operator/switchMap";
import { GifService } from "../services/gif.service";
import { SearchService } from "../services/search.service";
import { Giphy } from "./../Giphy";

@Component({
  selector: 'app-gif',
  templateUrl: './gif.component.html',
  styleUrls: ['./gif.component.css']
})
export class GifComponent implements OnInit {

  public gif: Giphy
	public inFavourites = false;
	id: string;
	
  
 constructor(
	 private router: Router,private gifService: GifService,
	 private searchService: SearchService,private route: ActivatedRoute,
	 private location: Location)  {}

	public addToFavourites(id: string,title: string,url: string,username: string,rating : string,posterPath:string) {
		this.gifService.addToFavourites(id,title,url,username,rating,posterPath);
		this.inFavourites = true;
  }
  
	public ngOnInit() {
		this.id = this.route.snapshot.params.id;
		this.searchService.getGifById(this.id).subscribe(gif=> {
			this.gif = gif.data;
			console.log('gif value is ...... ',this.gif);
			
		});
		
		
	}

	public removeFromFavourites(id: string) {
		this.gifService.removeFromFavourites(id).then(() => (this.inFavourites = false));
	}
}


	