import { trigger, transition, style, animate } from '@angular/animations';

export const boxResizeAnimation = trigger('boxResizeAnimation', [
  transition(':enter', [
    style({ transform: 'scale(0.8)', opacity: 0 }),
    animate('0.5s ease-out', style({ transform: 'scale(1)', opacity: 1 })),
  ]),
  transition(':leave', [
    style({ transform: 'scale(1)', opacity: 1 }),
    animate('0.5s ease-in', style({ transform: 'scale(0.8)', opacity: 0 })),
  ]),
]);
