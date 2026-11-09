import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'ajc-submit',
  imports: [ ],
  templateUrl: './ajc-submit.html',
  styleUrl: './ajc-submit.css',
})
export class AjcSubmit {
  @Input() editing: boolean = false;
  @Input() form!: FormGroup;
  @Output() cancel: EventEmitter<void> = new EventEmitter<void>();

  public onCancelClick() {
    this.cancel.emit();
  }
}
