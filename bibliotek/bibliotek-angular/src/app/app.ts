import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Navigation } from './component/navigation/navigation';
import { AuthService } from './service/auth-service';

@Component({
  selector: 'app-root',
  imports: [ RouterOutlet, Navigation ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected authService: AuthService = inject(AuthService);
}
