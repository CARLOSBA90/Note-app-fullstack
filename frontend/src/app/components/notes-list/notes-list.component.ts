import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { NoteService } from '../../services/note.service';
import { Note } from '../../models/note';
import { NoteComponent } from '../note/note.component';

@Component({
  selector: 'app-notes-list',
  standalone: true,
  imports: [MatCardModule,MatDividerModule,MatButtonModule,CommonModule, MatDialogModule],
  templateUrl: './notes-list.component.html',
  styleUrl: './notes-list.component.scss'
})
export class NotesListComponent {

   notes: Note[] = [];

  constructor(private noteService: NoteService,
              private dialog: MatDialog){}

  ngOnInit(): void {
    this.listNotes();
  }

  newNote(){
    this.openDialog(null);
  }

  openDialog(note:any){
    const dialogRef = this.dialog.open(NoteComponent, {
      width: '600px',
      height: '400px',
      data: { note },
    });

    dialogRef.afterClosed().subscribe((result) => { this.listNotes();});
  }

  listNotes(){
    this.noteService.getNotes().subscribe((data: Note[]) => {
      if (data && this.noteService.areNotes(data)){
        this.notes = data;
     }else{ /*T0D0: error handler*/ }
    });
  }

}
