import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home-page',
  imports: [],
  templateUrl: './home-page.html',
  styleUrl: './home-page.css',
})
export class HomePage implements OnInit {
  constructor(private title: Title) {

  }

  ngOnInit(): void {
    this.title.setTitle("Page d'accueil");
  }
}
