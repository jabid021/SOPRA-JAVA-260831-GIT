import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-liste-prenoms',
  imports: [
    CommonModule
  ],
  templateUrl: './liste-prenoms.html',
  styleUrl: './liste-prenoms.css',
})
export class ListePrenoms {
  @Input('autrenom')
  public prenoms!: string[];
}
