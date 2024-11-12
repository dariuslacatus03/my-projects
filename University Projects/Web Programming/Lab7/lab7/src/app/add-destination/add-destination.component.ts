import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-add-destination',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './add-destination.component.html',
  styleUrl: './add-destination.component.css'
})
export class AddDestinationComponent implements OnInit{
  destinationForm!: FormGroup;
  http: HttpClient = inject(HttpClient);


  constructor(
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.destinationForm = this.fb.group({
      location: ['', Validators.required],
      country: ['', Validators.required],
      description: ['', Validators.required],
      targets: ['', Validators.required],
      costs: ['', [Validators.required, Validators.min(0)]]
    });
  }

  onSubmit(): void {
    if (this.destinationForm.valid) {
      this.http.post("http://localhost/WP_Lab6/backend/add-destination-backend.php", this.destinationForm.value)
        .subscribe(
          (response) => {
            // Handle the successful response here
            console.log('Destination added successfully', response);
            // You can also reset the form or navigate to another page if needed
          },
          (error) => {
            // Handle the error here
            console.error('Error adding destination', error);
          }
        );
    }
  }
}
