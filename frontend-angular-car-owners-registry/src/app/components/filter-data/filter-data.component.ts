import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { DataProviderService } from '../../services/data-provider.service';
import { DataType } from '../../models/data-type';
import { FilterData } from '../../models/filter-data';

@Component({
    selector: 'app-filter-data',
    templateUrl: './filter-data.component.html',
    styleUrls: ['./filter-data.component.scss']
})
export class FilterDataComponent implements OnInit {

    carFields = [undefined, 'make', 'model', 'numberplate'];
    userFields = [undefined, 'name'];

    constructor(
            private readonly dataProviderService: DataProviderService) {
    }

    readonly dataTypes = Object.values(DataType);

    @Input() public data: FilterData = new FilterData();
    @Output() public readonly onFilterSelect = new EventEmitter<FilterData>();

    ngOnInit(): void {
    }

    onSearchButtonClick() {
        this.dataProviderService.downloadData(this.data);
        // this.data.sortFieldName
    }

    sortFieldNameSelectionItems(): any {
        switch(this.data.dataType) {
            case DataType.CAR:
            return this.carFields;
            case DataType.USER:
            return this.userFields;
        }
        return [];
    }

}