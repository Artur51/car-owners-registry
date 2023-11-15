import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatButtonModule } from '@angular/material/button';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatSortModule } from '@angular/material/sort';
import { MatTreeModule } from '@angular/material/tree';
import { MatExpansionModule } from '@angular/material/expansion';
import { BaseUrlInterceptor } from './interceptors/base-url.interceptor';
import { environment } from '../environments/environment';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { FilterDataComponent } from './components/filter-data/filter-data.component';
import { CarTableViewComponent } from './components/car-table-view/car-table-view.component';
import { UserTableViewComponent } from './components/user-table-view/user-table-view.component';
import { CarInfoComponent } from './components/car-info/car-info.component';
import { ErrorInterceptor } from './interceptors/error.interceptor';
import { MatSnackBarModule } from '@angular/material/snack-bar';

@NgModule({
    declarations: [
        AppComponent,
        FilterDataComponent,
        CarTableViewComponent,
        UserTableViewComponent,
        PaginatorComponent,
        CarInfoComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        FormsModule,
        HttpClientModule,
        MatButtonModule,
        ReactiveFormsModule,
        MatPaginatorModule,
        MatCheckboxModule,
        MatInputModule,
        MatRadioModule,
        MatToolbarModule,
        MatTableModule,
        MatIconModule,
        MatFormFieldModule,
        MatDialogModule,
        MatSelectModule,
        MatAutocompleteModule,
        MatProgressBarModule,
        MatStepperModule,
        MatSortModule,
        MatTreeModule,
        MatSnackBarModule,
        MatExpansionModule
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: BaseUrlInterceptor,
            multi: true,
        },
        { provide: 'BASE_API_URL', useValue: environment.backendUrl },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorInterceptor,
            multi: true,
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }