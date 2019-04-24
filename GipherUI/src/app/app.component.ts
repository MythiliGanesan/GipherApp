import { Component, ViewChild } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { SearchService } from "./services/search.service";

@Component({
	selector: "app-root",
	templateUrl: "./app.component.html",
	styleUrls: ["./app.component.css"]
})

export class AppComponent {

	public title = "Gipher App";
	public isUserLoggedIn = false;
    public searchQuery: string;
    
    constructor(private searchService: SearchService, private router: Router) {}
    
	ngOnInit(){
		this.isUserLoggedIn = localStorage.getItem("jwt_token") === null ? false : true;
	}
	public navigateTo() {
		const link = ["/search-results"];
		this.router.navigate(link);
	}
	public search() {
		this.searchService.find(this.searchQuery).then(response => {
			this.searchService.sharedSearchResult = response.data.splice(0,9);
			this.navigateTo();
		});
	}
}
