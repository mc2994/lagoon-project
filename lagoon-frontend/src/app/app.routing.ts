import { ModuleWithProviders }  from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent }  from './components/home.component';
import { Register} from './components/register.component';
import {Login} from './components/login.component';
import {MyAlbum} from './components/my-album.component';
import {AddPhoto} from './components/add-photo.component';
import {ImageDetail} from './components/image-detail.component';
import {UserManagement} from './components/user-management';
import { AuthGuard } from './guards/auth.guard';

const appRoutes: Routes = [
  {
    path: '',
    canActivate: [AuthGuard],
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
  	path: 'register',
  	component: Register
  },
  {
    path: 'login',
    component: Login
  },
  {
    path: 'my-album',
    component: MyAlbum
  },
  {
    path: 'add-photo',
    component: AddPhoto
  },
  {
    path: 'image-detail/:id',
    component: ImageDetail
  },
  {
    path: 'user-management',
    component: UserManagement
  },

  { path: '**', redirectTo:'home' }
  
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);