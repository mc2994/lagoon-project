import { Component } from '@angular/core';
import { PhotoService } from '../services/photo.service';
import { User } from '../models/user';
import { Photo } from '../models/photo';
import { Router } from '@angular/router';

@Component({
  selector: 'photo-list',
  templateUrl: './photo-list.component.html'
})
export class PhotoList {
  photos = new Array<Photo>();
  selectedPhoto = new Photo();
 
  constructor(private photoService: PhotoService, private router: Router) {
    this.getAllPhotos();
  }

  onSelect(photo: Photo) {
    this.selectedPhoto = photo;
    this.router.navigate(['/image-detail', this.selectedPhoto.photoId]);
  }

  getAllPhotos(){
    this.photoService.getPhotos().subscribe(
      data => {
        this.photos = data;
      },
      error => {
        this.router.navigate(['/login']);
      });
  }
}