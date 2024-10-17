import { Component } from '@angular/core';
import { SidebarComponent } from '../sidebar/sidebar.component';
import { MainContentComponent } from '../main-content/main-content.component';

@Component({
  selector: 'an-home',
  standalone: true,
  imports: [SidebarComponent, MainContentComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  marginLeft: string = '90px';

  onHoverChange(newMargin: string) {
    this.marginLeft = newMargin;
  }
}
