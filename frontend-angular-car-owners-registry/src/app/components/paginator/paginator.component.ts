import { Component, OnInit, ViewChild, Input, EventEmitter, Output, AfterViewInit } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { Page } from '../../models/page';

@Component({
    selector: 'app-paginator',
    templateUrl: './paginator.component.html',
    styleUrls: ['./paginator.component.scss'],
})
export class PaginatorComponent implements AfterViewInit {

    @ViewChild(MatPaginator)
    public paginator: MatPaginator;

    @Input() public pageSizeOptions = [5, 20, 50, 100];

    @Input() public page: Page<any>;

    @Output() public readonly onPaginationChange = new EventEmitter<PageEvent>();

    ngAfterViewInit() {
        this.onPaginationChange.emit({
            pageSize: this.paginator.pageSize,
            pageIndex: this.paginator.pageIndex
        } as PageEvent);
    }

    public handlePageEvent(event: PageEvent) {
        this.paginator.pageIndex = event.pageIndex;
        this.paginator.pageSize = event.pageSize;
        this.onPaginationChange.emit(event);
    }
}