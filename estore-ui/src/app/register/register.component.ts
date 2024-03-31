import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { animate, transition, trigger, style,state} from '@angular/animations';

import { User } from '../user';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  animations: [
    trigger('slideIn',[
      transition(':enter',[
        style({left: '50%'}),
        animate('1000ms ease-in',style({left:'150%'}))
      ]),
      state('final',style({left:"150%"}))
    ])
  ]  
})
export class RegisterComponent{
  
  username: string = '';
  password: string = '';
  passwordConfirm: string = '';
  users: User[] = [];
  buttonClicked = false;
  showErrorMessage = false;
  passwordsMatch = true;
  notStrongPassword = false
  

  constructor(private userService: UserService, private router: Router) {}

  

  fieldsFull(): boolean {
    return this.password != '' && this.username != '' && this.passwordConfirm != '';
  }

  showPasswordsDontMatch(): boolean {
    return !this.passwordsMatch && this.buttonClicked;
  }

  isStrongPassword(){
    let match = this.password.match(/^(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/);
    return match != null
  }

  registerOnClick(username: string, password: string): void {
    this.buttonClicked = true;

    if(this.password != this.passwordConfirm) {
      this.passwordsMatch = false;
      return;
    }

    if(this.fieldsFull() && this.isStrongPassword()) {
      this.userService.addUser({username,password} as User).subscribe(
        (response) => {
          if(response.status == 201) {
            this.router.navigate(['/login']);
          }
        },
        (error) => {
          if(error.status == 409) {
            this.showErrorMessage = true;
          }
        }
      );
    }
  }
}

