import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListPage } from './todo-list-page';

describe('TodoListPage', () => {
  let component: TodoListPage;
  let fixture: ComponentFixture<TodoListPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoListPage],
    }).compileComponents();

    fixture = TestBed.createComponent(TodoListPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
