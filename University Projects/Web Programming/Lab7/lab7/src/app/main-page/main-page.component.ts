import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
  ],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.css'
})


export class MainPageComponent implements OnInit{
  message: string = '';

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.message = '';
    }
  
}
