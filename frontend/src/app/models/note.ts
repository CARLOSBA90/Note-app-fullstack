import { Tag } from "./tag";

export class Note {

  id: number = 0;
  title!: string;
  content!: string;
  finished!: boolean;
  tag: Tag | null;

  constructor(id:number = 0 ,title: string = '', content: string = '', finished: boolean = false, tag: Tag | null = null) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.finished = finished;
    this.tag = tag;
  }

}
