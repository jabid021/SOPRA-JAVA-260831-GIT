import { KeyValuePipe } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { minLength, required } from '@angular/forms/signals';

@Component({
  selector: 'ajc-form-field',
  imports: [ KeyValuePipe ],
  templateUrl: './ajc-form-field.html',
  styleUrl: './ajc-form-field.css',
})
export class AjcFormField {
  @Input() label!: string;
  @Input() id!: string;
  @Input() control!: FormControl;

  @Input() errorMessages: Record<string, string> = {
    required: "Ce champ est obligatoire",
    minlength: "Ce champ a une taille minimum",
    min: "Ce champ a une valeur minimum",
    max: "Ce champ a une valeur maximum"
  };
}
