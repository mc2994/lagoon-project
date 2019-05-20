import {Component, Input} from '@angular/core';
import {PhotoService} from '../services/photo.service';
import {UserService} from '../services/user.service';
import {User} from '../models/user';
import {Photo} from '../models/photo';
import {Router} from '@angular/router';


@Component({
  selector: 'my-album',
  templateUrl: './my-album.component.html'
})
export class MyAlbum {

  private photos = new Array<Photo>();
  private user = new User();
  private selectedPhoto = new Photo();

  constructor (private photoService: PhotoService, private router: Router, private userService: UserService) {
    this.userService.getUserByName(localStorage.getItem("currentUserName")).subscribe(
      user => {
        this.user = user;
        console.log(this.user);
        this.photoService.getPhotosByUser(this.user).subscribe(
          photo => {
            this.photos = photo;
          },
          error => {
            this.router.navigate(['/login']);
          }
        );
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

  onSelect(photo:Photo) {
    this.selectedPhoto = photo;
    this.router.navigate(['/image-detail', this.selectedPhoto.photoId]);
    
  }
}
