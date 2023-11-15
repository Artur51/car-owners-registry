import { Component } from '@angular/core';
import { DataProviderService } from './services/data-provider.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {

    constructor(
            public dataProviderService: DataProviderService) {
    }

}