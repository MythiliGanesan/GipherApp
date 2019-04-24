import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SearchService } from '../services/search.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {

  public HomePageGifCollection: object[] = [];

  constructor(private searchService: SearchService, private router: Router) {}

  public goToDetail(id: string) {
    const link = ['/gif', id];
    this.router.navigate(link);
  }
  
   public ngOnInit() {
    this.searchService.getHomePageGifCollection().subscribe(response => {
      console.log(response);
      this.HomePageGifCollection = response.data.splice(0,9);
    });
  }
}
