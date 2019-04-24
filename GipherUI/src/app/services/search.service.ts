import { Injectable } from "@angular/core";
import { Http } from "@angular/http";
import {GifHttpClient} from './gif-http-client.service';
import "rxjs/add/operator/map";

@Injectable()
export class SearchService {

	public sharedSearchResult: object[] = [];
    private HomePagecollectionGifsUrl = "https://api.giphy.com/v1/gifs/trending?api_key=TAqogQaSGnVqCzp4XGVfRM6R9L5tDlg4";
	private searchUrl = "https://api.giphy.com/v1/gifs/search?api_key=TAqogQaSGnVqCzp4XGVfRM6R9L5tDlg4&q=";
	private gifUrl = "https://api.giphy.com/v1/gifs/";
	
	private apikey = "?api_key=TAqogQaSGnVqCzp4XGVfRM6R9L5tDlg4";
	
	constructor(private http: Http) {}

	public find(term: string) {
		const url = `${this.searchUrl}${term}`;
		return this.http
			.get(url)
			.toPromise()
			.then(response => response.json())
			.catch(this.handleError);
	}

	public getHomePageGifCollection(){
		return this.http.get(this.HomePagecollectionGifsUrl).map(result => result.json());
	}

	
	public getGifById(gif_id: string) {
		const url = this.gifUrl + gif_id + this.apikey;
		return this.http.get(url).map(response => response.json());
	}
	
	private handleError(error: any): Promise<any> {
		return Promise.reject(error.message || error);
	}
}