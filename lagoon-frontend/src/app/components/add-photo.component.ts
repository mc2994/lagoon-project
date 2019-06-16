import { Component } from '@angular/core';
import { Photo } from '../models/photo';
import { UploadPhotoService } from '../services/upload-photo.service';
import { AddPhotoService } from '../services/add-photo.service';
import { UserService } from '../services/user.service';
import { User } from '../models/user';

@Component({
  selector: 'add-photo',
  templateUrl: './add-photo.component.html'
})
export class AddPhoto {
  newPhoto: Photo = new Photo();
  photoAdded: boolean = false;
  user: User = new User();
  filesToUpload: File;
  formData: FormData = new FormData();

  constructor(private uploadPhotoService: UploadPhotoService,
    private addPhotoService: AddPhotoService,
    private userService: UserService) {

      this.userService.getUserByName(localStorage.getItem("currentUserName")).subscribe(
        user => {
          this.user = user;
        },
        error => {
            console.log("SDADA",error);
        }
      )

  }

  fileChangeEvent(files: FileList) {
    for (var i = 0; i < files.length; i++) {
      this.filesToUpload = files[i];
      this.formData.append("file", this.filesToUpload, files.item(i).name);
    }
  }

  onSubmit() {
    this.newPhoto.user = this.user;
    this.formData.append("photo", new Blob([JSON.stringify(this.newPhoto)],
      {
        type: "application/json"
      }
    ));
    this.addPhotoService.sendPhoto(this.formData)
      .subscribe(
        data => {
          this.photoAdded = true;
          this.newPhoto = new Photo();
        }, error => {
          console.log("sendPhotoError: " + error);
        }
      );
  }
}