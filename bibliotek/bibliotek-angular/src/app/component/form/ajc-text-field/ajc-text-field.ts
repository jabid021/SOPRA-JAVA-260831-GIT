import { Component, Input } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { AjcFormField } from '../ajc-form-field/ajc-form-field';

@Component({
  selector: 'ajc-text-field',
  imports: [ AjcFormField, ReactiveFormsModule ],
  templateUrl: './ajc-text-field.html',
  styleUrl: './ajc-text-field.css',
})
export class AjcTextField extends AjcFormField {
  @Input() type: string = "text";
}
