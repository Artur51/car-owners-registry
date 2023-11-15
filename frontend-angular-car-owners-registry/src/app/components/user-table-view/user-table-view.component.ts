import { Page } from '../../models/page';
import { User } from '../../models/user';
import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-user-table-view',
    templateUrl: './user-table-view.component.html',
    styleUrls: ['./user-table-view.component.scss']
})
export class UserTableViewComponent {

    @Output() public onFilterChange = new EventEmitter<any>();

    @Input() page: Page<User>;

    displayedColumns: string[] = [
        'id',
        'name',
        'cars'
    ];

}