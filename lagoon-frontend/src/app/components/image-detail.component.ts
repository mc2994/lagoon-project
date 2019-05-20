import {Component} from '@angular/core';
import {Photo} from '../models/photo';
import {PhotoService} from '../services/photo.service';
import {UserService} from '../services/user.service';
import {User} from '../models/user';
import { ActivatedRoute, Params } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'image-detail',
  templateUrl: './image-detail.component.html'
})
export class ImageDetail {
  photo: Photo = new Photo();
  like: string;
  user: User;
  photoId: number;

  constructor ( private photoService: PhotoService,
                private userService: UserService, 
                private route: ActivatedRoute,
                private router: Router){
     this.route.params.forEach((params: Params) => {
     this.photoId = Number.parseInt(params['id']);
    });

    
    this.photoService.getPhotoById(this.photoId).subscribe(
      photo => {
        this.photo = photo;
        this.userService.getUserByName(localStorage.getItem("currentUserName")).subscribe(
          user => {
            this.user = user;
            if (this.user.likedPhotoList.filter(photo => photo.photoId == this.photo.photoId)[0]) {
              this.like="Unlike";
            } else {
              this.like="Like";
            }
          },
          error => {
            this.router.navigate(['/login']);
          }
        )
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }

  goBack() {
    window.history.back();
  }

  ngOnInit() {

  }

  likeDisplay() {
    if (this.like =="Like") {
      this.like="Unlike";
      this.user.likedPhotoList.push(this.photo);
      this.photo.likes+=1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    } else {
      this.like="Like";
      // var index = this.user.likedPhotoList.indexOf(this.photo, 0);
      for (let i=0; i<this.user.likedPhotoList.length; i++) {
        if (this.user.likedPhotoList[i].photoId == this.photo.photoId) {
            this.user.likedPhotoList.splice(i, 1);
        }
      }
      // console.log(index);
      // if (index > -1) {
      //   this.user.likedPhotoList.splice(index, 1);
      // }
      this.photo.likes-=1;
      this.userService.updateUser(this.user).subscribe();
      this.photoService.updatePhoto(this.photo).subscribe();
    }
  }
}
