import { Component } from '@angular/core';
import { PremierPipe } from '../../pipe/premier-pipe';
import { UpperCasePipe } from '@angular/common';

@Component({
  selector: 'app-demo-pipe-page',
  imports: [
    PremierPipe,
    UpperCasePipe
  ],
  templateUrl: './demo-pipe-page.html',
  styleUrl: './demo-pipe-page.css',
})
export class DemoPipePage {
  protected varBoolean: boolean = false;
}
