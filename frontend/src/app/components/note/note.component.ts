import { ChangeDetectorRef, Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import {MatCardModule} from '@angular/material/card';
import { FormsModule } from '@angular/forms';
import { NoteService } from '../../services/note.service';
import { TagService } from '../../services/tag.service';
import { Tag } from '../../models/tag';
import { Note } from '../../models/note';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-note',
  standalone: true,
  imports: [MatCardModule,MatDividerModule,MatButtonModule,FormsModule,CommonModule],
  templateUrl: './note.component.html',
  styleUrl: './note.component.scss'
})
export class NoteComponent {
 note = new Note();
 new : boolean=true;
 tags!: Tag[];
 activeTag: Tag | null = null;

  constructor(private noteService: NoteService,
              private tagService: TagService,
              private snackBar: MatSnackBar,
              public dialogRef: MatDialogRef<NoteComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              private cd:ChangeDetectorRef) { }


  ngOnInit(): void {
    this.getTags();
    if(this.noteService.isNote(this.data.note)){
      this.note=this.data.note;
      this.activeTag = this.note.tag; // Set active tag to note's tag
      this.cd.detectChanges();
      this.new=false;

  }
  }

  getTags(){
    this.tagService.getTags().subscribe((data: any) => {
      if (data && this.tagService.areTags(data)){
        this.tags = data;
     }else{ /*T0D0: error handler*/ }
    });
  }

  setTag(tag:Tag | null){
    this.activeTag = tag;
    this.note.tag = tag;
  }

  saveNote(){
    if(this.new)
      this.createNote();
    else
      this.updateNote();
  }

  createNote(){
    if(this.note.tag==null || this.note.tag==undefined){
         this.showErrorSnackbar("You must select a tag!");
    }else{
      this.noteService.createNote(this.note).subscribe((data: any) => {
        if (data.success){
              this.closeDialog();
          }else{
            //T0D0: error handler
          }
      });
    }
  }

  updateNote(){
    if(this.note.tag==null || this.note.tag==undefined){
      this.showErrorSnackbar("You must select a tag!");
        }else{
            this.noteService.updateNote(this.note).subscribe((data: any) => {
              if (data){
                  this.closeDialog();
                }else{
                  //T0D0: error handler
                }
            });}
  }

  showErrorSnackbar(message: string): void {
    this.snackBar.open(message, 'OK', { duration: 3000 });
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
