import { Component, OnInit, Input } from '@angular/core';
import { Car } from '../../models/car';

@Component({
    selector: 'app-car-info',
    templateUrl: './car-info.component.html',
    styleUrls: ['./car-info.component.scss'],
})
export class CarInfoComponent implements OnInit {

    @Input() public index: number;

    @Input() public model: Car;

    constructor() {}

    ngOnInit(): void {

    }
}