import { Routes } from '@angular/router';
import { MainPageComponent } from './main-page/main-page.component';
import { AddDestinationComponent } from './add-destination/add-destination.component';
import { DeleteDestinationComponent } from './delete-destination/delete-destination.component';
import { ModifyDestinationComponent } from './modify-destination/modify-destination.component';
import { BrowseDestinationComponent } from './browse-destination/browse-destination.component';

export const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'add-destination', component: AddDestinationComponent },
  { path: 'delete-destinations', component: DeleteDestinationComponent },
  { path: 'modify-destinations', component: ModifyDestinationComponent },
  { path: 'browse-destinations', component: BrowseDestinationComponent },
];
