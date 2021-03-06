import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {routing} from './app.routing';
import { HttpClientModule,HTTP_INTERCEPTORS  } from '@angular/common/http'; 
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home.component';
import {PhotoList} from './components/photo-list.component';
import {SidePanel} from './components/side-panel.component';
import {NavBar} from './components/nav-bar.component';
import {Register} from './components/register.component';
import {Login} from './components/login.component';
import {MyAlbum} from './components/my-album.component';
import {AddPhoto} from './components/add-photo.component';
import {ImageComments} from './components/image-comments.component';
import {ImageDetail} from './components/image-detail.component';
import {PhotoRow} from './components/photo-row.component';
import {UserManagement} from './components/user-management';
// import { Interceptor } from './guards/app.interceptor';

import {PhotoService} from './services/photo.service';
import {RegisterService} from './services/register.service';
import {LoginService} from './services/login.service';
import {UserService} from './services/user.service';
import {UploadPhotoService} from './services/upload-photo.service';
import {AddPhotoService} from './services/add-photo.service';
import {CommentService} from './services/comment.service';
import { Interceptor } from './guards/app.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PhotoList,
    SidePanel,
    NavBar,
    Register,
    Login,
    MyAlbum,
    AddPhoto,
    ImageComments,
    ImageDetail,
    PhotoRow,
    UserManagement
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing
  ],
  providers: [
    PhotoService,
    RegisterService,
    LoginService,
    UserService,
    UploadPhotoService,
    AddPhotoService,
    CommentService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
