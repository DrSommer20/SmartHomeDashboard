import { Component, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';
import { Apollo, gql } from 'apollo-angular';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../modal/modal.component';

@Component({
  selector: 'an-user-profile',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css',
})
export class UserProfileComponent implements OnInit {
  userForm: FormGroup;
  successMessage: string | null = null;
  @ViewChild('modalContainer', { read: ViewContainerRef })
  modalContainer!: ViewContainerRef;
  private modalRef!: ComponentRef<ModalComponent>;

  constructor(
    private apollo: Apollo,
    private fb: FormBuilder,
    private router: Router
  ) {
    this.userForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      password: [''],
      pat: [''],
    });
  }

  ngOnInit(): void {
    this.getUserData();
  }

  getUserData(): void {
    this.apollo
      .watchQuery<any>({
        query: GET_USER_INFO,
      })
      .valueChanges.subscribe(
        ({ data }) => {
          const user = data.userInfo;
          this.userForm.patchValue({
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            pat: user.pat,
            isVerified: user.isVerified,
          });
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
  }

  updateField(field: string, newValue: string): void {
    const variables: any = {};
    variables[field] = newValue;

    this.apollo
      .mutate({
        mutation: UPDATE_USER,
        variables: variables,
      })
      .subscribe(
        (response) => {},
        (error) => {
          console.error(`Error updating ${field}:`, error);
        }
      );
  }

  onEditButtonClick(): void {
    const currentData = this.userForm.value;

    this.apollo
      .watchQuery<any>({
        query: GET_USER_INFO,
      })
      .valueChanges.subscribe(
        ({ data }) => {
          const user = data.userInfo;
          if (user.firstName !== currentData.firstName) {
            this.updateField('firstName', currentData.firstName);
          }
          if (user.lastName !== currentData.lastName) {
            this.updateField('lastName', currentData.lastName);
          }
          if (user.email !== currentData.email) {
            this.updateField('email', currentData.email);
          }
          if (user.pat !== currentData.pat) {
            this.updateField('pat', currentData.pat);
          }
          if (user.isVerified !== currentData.isVerified) {
            this.updateField('isVerified', currentData.isVerified);
          }
          this.successMessage = 'Profile updated successfully!';
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
  }

  deleteProfile(): void {
     this.modalRef = this.modalContainer.createComponent(ModalComponent);
    this.modalRef.instance.type = 'ConfirmDelete';

    this.modalRef.instance.onConfirm.subscribe(() => {
      this.modalRef.destroy();
      this.apollo
        .watchQuery<any>({
          query: DELETE_USER,
        })
        .valueChanges.subscribe(
          (response) => {
            this.router.navigate(['/auth/login']);
          },
          (error) => {
            console.error('Error deleting profile:', error);
          }
        );
    });
    this.modalRef.instance.onCancel.subscribe(() => {
      // Simply destroy the modal if cancelled
      this.modalRef.destroy();
    });
  }
}


const UPDATE_USER = gql`
  mutation UpdateUser(
    $firstName: String
    $lastName: String
    $email: String
    $pat: String
    $isVerified: Boolean
  ) {
    updateUser(
      firstName: $firstName
      lastName: $lastName
      email: $email
      pat: $pat
      isVerified: $isVerified
    ) {
      id
      firstName
      lastName
      email
      pat
      isVerified
    }
  }
`;

const GET_USER_INFO = gql`
  query GetUserInfo {
    userInfo {
      id
      firstName
      lastName
      email
      pat
      isVerified
    }
  }
`;

const DELETE_USER = gql`
  mutation DeleteUser {
    deleteUser
  }
`;
