import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { HttpInterceptor, HTTP_INTERCEPTORS} from "@angular/common/http";

import { AuthService } from './services/auth.service';
import { SearchService } from './services/search.service';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchComponent } from './search/search.component';

import { AuthGuard } from './services/auth-guard.service';
import { GifComponent } from './gif/gif.component';
import { FavouritesComponent } from './favourites/favourites.component';
import { SigninComponent } from './signin/signin.component';
import { SignupComponent } from './signup/signup.component';
import { SignOutComponent } from './signout/signout.component';
import { GifService } from './services/gif.service';
import { GifHttpClient } from './services/gif-http-client.service';
import { AlertService } from './services/alert.service';
import { AlertComponent } from './alerts/alerts.component';

  

@NgModule({
  declarations: [
    AppComponent,
  SearchResultsComponent,
  SearchComponent,
  GifComponent,
  FavouritesComponent,
  HomeComponent,
    SigninComponent,
    SignupComponent,
    SignOutComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    RouterModule
  ],
  providers: [GifService, SearchService, AlertService, AuthGuard, AuthService,GifHttpClient],
  bootstrap: [AppComponent]
})
export class AppModule {}