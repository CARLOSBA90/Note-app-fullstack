import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, map, of, throwError } from 'rxjs';
import { environment } from '../../environment';
import { Note } from '../models/note';
import { Tag } from '../models/tag';


@Injectable({
  providedIn: 'root'
})
export class TagService {
  private readonly baseUrl = `${environment.API_URL}/tags`;
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': 'http://localhost:4200',
  });


  constructor(private http: HttpClient) { }


  getTags(): Observable<Tag[]> {
    return this.http.get<Tag[]>(this.baseUrl + '/list').pipe(
      catchError((error: any) => {
        // T0D0: Handle the error
        console.error(error);
        // Return an empty observable to prevent further processing
        return of([]);
      })
    );
  }


  areTags(data: any): data is Tag[] {
    return (
      Array.isArray(data) &&
      data.every((item: any) => typeof item === 'object' && 'name' in item && 'priority' in item && 'id' in item)
    );
  }
}
