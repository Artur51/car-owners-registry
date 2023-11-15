import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AppComponent } from '../app/app.component';
import { FilterDataComponent } from '../app/components/filter-data/filter-data.component';
import { CarTableViewComponent } from '../app/components/car-table-view/car-table-view.component';
import { UserTableViewComponent } from '../app/components/user-table-view/user-table-view.component';
import { PaginatorComponent } from '../app/components/paginator/paginator.component';
import { CarInfoComponent } from '../app/components/car-info/car-info.component';

describe('AppComponent', () => {
    beforeEach(() => TestBed.configureTestingModule({
        imports: [
            HttpClientTestingModule,
            MatFormFieldModule,
            MatSelectModule,
            MatPaginatorModule
        ],
        declarations: [
            AppComponent,
            FilterDataComponent,
            CarTableViewComponent,
            UserTableViewComponent,
            PaginatorComponent,
            CarInfoComponent
        ]
    }));

    it('should create the app', () => {
        const fixture = TestBed.createComponent(AppComponent);
        const app = fixture.componentInstance;
        expect(app).toBeTruthy();
    });

});