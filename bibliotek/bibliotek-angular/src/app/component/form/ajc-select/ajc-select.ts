import { Component, ContentChild, Input, TemplateRef } from '@angular/core';
import { AjcFormField } from '../ajc-form-field/ajc-form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'ajc-select',
  imports: [ AjcFormField, CommonModule, ReactiveFormsModule ],
  templateUrl: './ajc-select.html',
  styleUrl: './ajc-select.css',
})
export class AjcSelect extends AjcFormField {
  @Input() items: any[] = [];
  @Input('item-value') itemValue: string = "";
  @Input() nullable = false;
  @Input('null-label') nullLabel = "-- Aucune sélection --";

  @ContentChild(TemplateRef) itemTemplate?: TemplateRef<unknown>;

  public resolveValue(item: any): any {
    if (!item || !this.itemValue) {
      return item;
    }

    return item[this.itemValue];
  }
}
