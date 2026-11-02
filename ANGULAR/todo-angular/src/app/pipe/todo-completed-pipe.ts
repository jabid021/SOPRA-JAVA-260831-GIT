import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'todoCompleted',
})
export class TodoCompletedPipe implements PipeTransform {
  transform(value: boolean): string {
    if (value) {
      return "Terminé !";
    }

    return "Pas terminé";
  }
}
