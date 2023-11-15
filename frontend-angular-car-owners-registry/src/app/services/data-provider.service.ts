
import { Injectable } from '@angular/core';
import { Page } from '../models/page';
import { User } from '../models/user';
import { Car } from '../models/car';
import { CarService } from './backend/car.service';
import { UserService } from './backend/user.service';
import { FilterData } from '../models/filter-data';
import { DataType } from '../models/data-type';
import { PageEvent } from '@angular/material/paginator';

@Injectable({ providedIn: 'root' })
export class DataProviderService {
    public usersData: Page<User>;
    public carsData: Page<Car>;
    private model: FilterData;
    private event: PageEvent;

    constructor(private readonly carService: CarService,
            private readonly userService: UserService) {
    }

    public downloadData(model: FilterData) {
        this.model = model;
        switch(this.model.dataType) {
            case DataType.CAR:
            this.carService.findByValue(this.model.text, this.model.getSort(), this.event?.pageIndex || 0, this.event?.pageSize || 20)
                    .subscribe((it) => {
                this.carsData = it;
                this.usersData = null;
            });
            break;
            case DataType.USER:
            this.userService.findByName(this.model.text, this.model.getSort(), this.event?.pageIndex || 0, this.event?.pageSize || 20)
                    .subscribe((it) => {
                this.usersData = it;
                this.carsData = null;
            });
            break;
        }
    }

    onPaginationChange(event: PageEvent) {
        this.event = Object.assign({}, event);
        if (this.model) {
            this.downloadData(this.model);
        }
    }

}