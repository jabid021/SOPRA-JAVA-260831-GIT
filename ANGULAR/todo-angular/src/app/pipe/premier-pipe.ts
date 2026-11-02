import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'premier',
})
export class PremierPipe implements PipeTransform {
  transform(value: unknown, ...args: unknown[]): string {
    return (value ? 'YES' : 'NO') + args[0] + args[1];
  }
}
