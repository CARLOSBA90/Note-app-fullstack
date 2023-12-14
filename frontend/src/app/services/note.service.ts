import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import { environment } from '../../environment';
import { Note } from '../models/note';
import { Tag } from '../models/tag';


@Injectable({
  providedIn: 'root'
})
export class NoteService {
  private readonly baseUrl = `${environment.API_URL}/notes`;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': 'http://localhost:4200',
  });


  constructor(private http: HttpClient) { }

  getNotes(): Observable<Note[]> {
    return this.http.get<Note[]>(this.baseUrl + '/list').pipe(
      catchError((error: any) => {
        // T0D0: Handle the error
        console.error(error);
        // Return an empty observable to prevent further processing
        return of([]);
      })
    );
  }

  getNoteById(id:number): Observable<Note> {
    return this.http.get<Note>(this.baseUrl + '/'+id).pipe(
      catchError((error: any) => {
        // Handle the error
        console.error(error);

        if (error.status === 404) {
          // If error is 404, return an empty note
          return of(this.createEmptyNote());
        } else {
          // For other errors, rethrow the error
          return throwError(error);
        }
      })
    );
  }

  createNote(note: Note): Observable<any> {
    return this.http.post<Note>(this.baseUrl + '/create', note, {headers: this.headers, observe: 'response'}).pipe(
      map((response: any) => {
        // Check for successful response codes
        if (response.status === 200 || response.status === 204) {
          // Handle success
          return {success:true};
        }
        // Throw error for other status codes
        return throwError(response);
      }),
      catchError((error: any) => {
        // Handle the error
        console.error(error);

        // Handle specific error codes
        switch (error.status) {
          case 400:
            // Handle bad request error
            // ...
            break;
          case 404:
            // Handle not found error
            // ...
            break;
          default:
            // Rethrow the error for other cases
            return throwError(error);
        }

        // Return an empty observable to prevent further processing
        return of(null);
      })
    );
  }


  updateNote(note:Note): any {
    return this.http.put<Note>(this.baseUrl + '/'+note.id, note, {headers: this.headers, observe: 'response'}).pipe(
      map((response: any) => {
        // Check for successful response codes
        if (response.status === 200 || response.status === 204) {
          // Handle success
          return {success:true};
        }
        // Throw error for other status codes
        return throwError(response);
      }),
      catchError((error: any) => {
        // Handle the error
        console.error(error);

        // Handle specific error codes
        switch (error.status) {
          case 400:
            // Handle bad request error
            // ...
            break;
          case 404:
            // Handle not found error
            // ...
            break;
          default:
            // Rethrow the error for other cases
            return throwError(error);
        }

        // Return an empty observable to prevent further processing
        return of(null);
      })
    );
  }


isNote(data: any): data is Note {
  return (
    typeof data === 'object' &&
    'title' in data &&
    'content' in data &&
    'finished' in data &&
    'tag' in data
  );
}

areNotes(data: any): data is Tag[] {
  return (
    Array.isArray(data) &&
    data.every((item: any) => typeof item === 'object' && 'title' in item && 'content' in item && 'id' in item)
  );
}

  private createEmptyNote(): Note {
    return {
      id: 0,
      title: '',
      content: '',
      finished: false,
      tag: null
    };
  }


}

