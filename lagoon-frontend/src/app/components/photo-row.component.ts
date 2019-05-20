import {Component } from '@angular/core';
import {PhotoService} from '../services/photo.service';
import {Photo} from '../models/photo';
import { Router } from '@angular/router';

@Component({
  selector: 'photo-row',
  templateUrl: './photo-row.component.html'
})
export class PhotoRow {
  photoList = new Array<Photo>();
  photoListSorted = new Array<Photo>();
  photoListRanked = new Array<Photo>();

  constructor (private photoService: PhotoService, private router: Router) {
    this.photoService.getPhotos().subscribe(
      data=> {
        this.photoList = data;
        this.photoListSorted = this.photoList.sort((a,b) => b.likes-a.likes);
        this.photoListRanked = [];
        for (let index in this.photoListSorted) {
          if (Number(index) < 3) {
            this.photoListRanked.push(this.photoListSorted[index]);
          } else {
            break;
          }
        }
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
  }
}
