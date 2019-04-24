import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { SearchService } from "../services/search.service";

@Component({
	selector: "app-search",
	templateUrl: "./search.component.html",
	styleUrls: ["./search.component.css"]
})

export class SearchComponent {

  public searchQuery: string;
  
  constructor(private searchService: SearchService, private router: Router) {}
  
	public navigateToSearchResults() {
		this.router.navigate(["/search-results"]);
  }
  
	public search() {
		this.searchService.find("searchQuery").then(response => {
			this.searchService.sharedSearchResult = response.data;
			this.navigateToSearchResults();
		});
	}
}