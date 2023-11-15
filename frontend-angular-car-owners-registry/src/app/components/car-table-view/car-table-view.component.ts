import { Page } from '../../models/page';
import { Car } from '../../models/car';
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-car-table-view',
    templateUrl: './car-table-view.component.html',
    styleUrls: ['./car-table-view.component.scss']
})
export class CarTableViewComponent {

    @Output() public onFilterChange = new EventEmitter<any>();

    @Input() page: Page<Car>;

    displayedColumns: string[] = [
        'id',
        'make',
        'model',
        'numberplate'
    ];

}