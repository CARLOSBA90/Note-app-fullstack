export class Tag {

  id: number = 0;
  name: string = '';
  priority: number = 0;

  constructor(id: number, name: string, priority: number) {
    this.id = id;
    this.name = name;
    this.priority = priority;
  }

}
