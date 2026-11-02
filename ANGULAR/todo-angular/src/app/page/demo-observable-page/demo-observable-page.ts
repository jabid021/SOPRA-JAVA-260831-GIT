import { Component } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-demo-observable-page',
  imports: [],
  templateUrl: './demo-observable-page.html',
  styleUrl: './demo-observable-page.css',
})
export class DemoObservablePage {
  // constructor() {
  //   console.log("#1");

  //   setTimeout(() => {
  //     console.log("#2");
  //   }, 5);

  //   console.log("#3");
  // }

  // constructor() {
  //   console.log("#1");

  //   let prom = new Promise((resolve, error) => {
  //     setTimeout(() => {
  //       resolve("RESULTAT ICI #2");
  //     }, 1_000);
  //   });

  //   prom.then((value) => {
  //     console.log("#2", value);
  //   });

  //   console.log("#3");
  // }

  constructor() {
    console.log("#1");

    let obs = new Observable<number>(observer => {
      setTimeout(() => observer.next(1), 1_000);
      setTimeout(() => observer.next(2), 2_000);
      setTimeout(() => observer.next(3), 3_000);
      setTimeout(() => observer.next(4), 4_000);
      setTimeout(() => observer.complete(), 5_000);
    });

    obs.subscribe(value => {
      console.log("#2", value);
    });

    console.log("#3");
  }
}
