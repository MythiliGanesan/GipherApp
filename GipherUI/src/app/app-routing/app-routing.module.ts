import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SigninComponent } from "../signin/signin.component";
import { HomeComponent } from "../home/home.component";
import { SearchResultsComponent } from "../search-results/search-results.component";
import { SearchComponent } from "../search/search.component";
import { GifComponent } from "../gif/gif.component";
import { FavouritesComponent } from "../favourites/favourites.component";
import { SignupComponent } from "../signup/signup.component";
import { SignOutComponent } from "../signout/signout.component";
import { AuthGuard } from "../services/auth-guard.service";
 
const routes: Routes = [
	{
		path: "",
		component:
			localStorage.getItem("token") === null ? SigninComponent : HomeComponent,
		canActivate: [AuthGuard]
	},
	{
		path: "search-results",
		component: SearchResultsComponent,
		canActivate: [AuthGuard]
	},
	{ path: "search", component: SearchComponent, canActivate: [AuthGuard] },
	{ path: "gif/:id", component: GifComponent, canActivate: [AuthGuard] },
		{
		path: "favourites",
		component: FavouritesComponent,
		canActivate: [AuthGuard]
	},
	{ path: "home", component: HomeComponent, canActivate: [AuthGuard] },
	{ path: "signin", component: SigninComponent },
	{ path: "signup", component: SignupComponent },
	{ path: "signout", component: SignOutComponent }
];
@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}