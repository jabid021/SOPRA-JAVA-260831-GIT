import { Pipe, PipeTransform } from '@angular/core';
import { NATIONALITE_LABEL } from '../enumerator/nationalite-label';

@Pipe({
  name: 'nationalite'
})
export class NationalitePipe implements PipeTransform {
  transform(value: string, type: 'P' | 'A' = 'A'): string {
    const key = value as keyof typeof NATIONALITE_LABEL;
    const nat: any = NATIONALITE_LABEL[key];

    if (!nat) {
      return value;
    }

    return type === 'P' ? nat.pays : nat.nationalite;
  }
}
