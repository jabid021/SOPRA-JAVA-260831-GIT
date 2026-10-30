import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-mon-premier-composant',
  imports: [],
  templateUrl: './mon-premier-composant.html',
  styleUrl: './mon-premier-composant.css',
})
export class MonPremierComposant {

  @Input()
  public titre: string = "";

}
