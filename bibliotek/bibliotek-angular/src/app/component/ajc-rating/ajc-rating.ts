import { CommonModule } from '@angular/common';
import { Component, EventEmitter, HostBinding, Input, OnInit, Output } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'ajc-rating',
  imports: [ ],
  templateUrl: './ajc-rating.html',
  styleUrl: './ajc-rating.css',
})
export class AjcRating implements OnInit {
  @Input() set note(val: number | undefined) {
    this._note = val ?? 0;
    this.value = this._note;
  }
  @Input() control?: FormControl;
  @Input() readonly: boolean = false;

  @Output() valueChange = new EventEmitter<number>();

  private _note?: number;
  private noteMax: number = 5;
  protected hoverNote: number = 0;
  protected value: number = 0;
  protected stars: number[] = Array.from({ length: this.noteMax }, (_, i) => i);

  ngOnInit(): void {
    if (this._note !== undefined && this.control) {
      throw new Error("AjcRating: utiliser soit [note] soit [control]");
    }

    this.value = this._note ?? this.control?.value;

    if (this.control) {
      this.control.valueChanges.subscribe(val => {
        this.value = val ?? 0;
      });
    }
  }

  @HostBinding('class.is-invalid')
  public get isInvalid(): boolean {
    return !!this.control?.invalid;
  }

  public setValue(val: number) {
    if (this.readonly) {
      return;
    }

    if (this.control) {
      this.control.setValue(val);
    }

    this.value = val;
    this.valueChange.emit(val);
  }

  public onMouseEnter(i: number) {
    if (this.readonly) {
      return;
    }

    this.hoverNote = i + 1
  }

  public onMouseLeave() {
    if (this.readonly) {
      return;
    }

    this.hoverNote = 0;
  }
}
