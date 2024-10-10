import { Component, Input} from '@angular/core';
import { ContentHeaderComponent } from '../content-header/content-header.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'an-main-content',
  standalone: true,
  imports: [ContentHeaderComponent, DashboardComponent, RouterOutlet],
  templateUrl: './main-content.component.html',
  styleUrl: './main-content.component.css'
})
export class MainContentComponent {
  @Input() marginLeft: string = '90px';
}
