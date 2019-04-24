import { Injectable } from "@angular/core";
import { Headers, Http, Jsonp } from "@angular/http";
import "rxjs/Rx";
import { GifHttpClient } from "./gif-http-client.service";


@Injectable()
export class GifService {

  private serviceUrl = "http://localhost:8080/api/v1/giphyService/";
  
  constructor(private http: GifHttpClient) { }
  
	addToFavourites(id: string,title: string,source: string,username: string,rating : string,posterPath:string) {
    const Url = this.serviceUrl + 'gif';
    
		const json = {
			gifId:id,
			title: title,
			url: source,
			username: username,
			rating: rating,
			posterPath: posterPath
		};
		return this.http.post(Url, json).toPromise().catch(this.handleError);
  }
  
	getFavourites() {
		const url = this.serviceUrl + 'gifs';
		return this.http.get(url).toPromise().then(res => res.json(),
			err => err.json());
  }
  
	checkFavourites(id: string) {
		const url = this.serviceUrl + 'gif/' + id;
		return this.http.get(url).toPromise().then(res => (res ? res.json() : null))
			.catch(this.handleError);
	}
	
	removeFromFavourites(id: string) {

		const url = this.serviceUrl + 'gif/';
		const uri = `${url}${id}`;
	  console.log(id);
		console.log('Remove gif', uri);
		return this.http.delete(uri).toPromise().catch(this.handleError);
	}
	
	private handleError(error: any) {
		console.error('An error occurred', error); // for demo purposes only
	}
}