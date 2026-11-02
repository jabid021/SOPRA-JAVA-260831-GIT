import { TodoCompletedPipe } from './todo-completed-pipe';

describe('TodoCompletedPipe', () => {
  it('create an instance', () => {
    const pipe = new TodoCompletedPipe();
    expect(pipe).toBeTruthy();
  });
});
