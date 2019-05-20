import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Photo } from '../models/photo';
import { User } from '../models/user';
import { UserService } from '../services/user.service';
import { Comment } from '../models/comment';
import { CommentService } from '../services/comment.service';
import { PhotoService } from '../services/photo.service';

@Component({
  selector: 'image-comments',
  templateUrl: './image-comments.component.html'
})
export class ImageComments {
  @Input('photo') photo: Photo;
  myLocalStorage = localStorage;
  user: User = new User();
  newComment = new Comment();

  constructor(private userService: UserService, 
              private commentService: CommentService, 
              private photoService: PhotoService,
              private router: Router) {
    this.userService.getUserByName(localStorage.getItem("currentUserName")).subscribe(
      user => {
        this.user = user;
      },
      error => {
          this.router.navigate(['/login']);
      }
    )
  }

  onInit() {

  }

  onSubmit() {
    this.newComment.photo = this.photo;
    this.newComment.userName = this.user.userName;
    this.newComment.photoId = this.photo.photoId;
    this.commentService.addComment(this.newComment).subscribe(
      photo => this.photoService.getPhotoById(this.photo.photoId).subscribe(
        photo => {
          this.photo = photo;
        },
        error => console.log(error)
      )
      // error => console.log(error)
    );
    // this.photo.commentList.push(this.newComment);


    this.newComment = new Comment();
  }
}
