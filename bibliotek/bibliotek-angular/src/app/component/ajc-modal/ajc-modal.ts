import { CommonModule } from '@angular/common';
import { Component, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'ajc-modal',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './ajc-modal.html',
  styleUrl: './ajc-modal.css',
})
export class AjcModal {
  @Input() title: string = "";
  @Input() form!: FormGroup;

  @Output() closed: EventEmitter<void> = new EventEmitter<void>();

  @HostListener('document:keydown.escape', ['$event'])
  protected onEscape(event: Event) {
    event.preventDefault();
    this.onClose();
  }

  public onClose() {
    this.closed.emit();
  }
}
