import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Page } from '../../models/page';
import { User } from '../../models/user';
import { Car } from '../../models/car';

@Injectable({
    providedIn: 'root',
})
export class UserService {
    constructor(private http: HttpClient) {}

    public findByName(find?: string, sort?: string, page?: number, size?: number) {
        const url = '/users';
        let params = new HttpParams();
        if (find !== undefined) { params = params.set('find', find); }
        if (sort !== undefined) { params = params.set('sort', sort); }
        if (page !== undefined) { params = params.set('page', page); }
        if (size !== undefined) { params = params.set('size', size); }

        return this.http.get<Page<User>>(url, { params });
    }

    public read(id: number) {
        return this.http.get<User>(`/users/${id}`);
    }

    public readCars(id: number) {
        return this.http.get<Array<Car>>(`/users/${id}/cars`);
    }
}