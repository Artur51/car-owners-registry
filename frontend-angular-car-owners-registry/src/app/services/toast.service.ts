import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({ providedIn: 'root' })
export class ToastService {

    constructor(private snackBar: MatSnackBar) {
    }

    public showErrorResponseToast(errorResp: any) {
        if (errorResp.message) {
            this.showErrorMessageToast(errorResp.statusText, errorResp.message);
            return;
        }
        this.showErrorMessageToast(errorResp.name, errorResp.statusText);
    }

    public showErrorMessageToast(message: string, secondText = '') {
        this.snackBar.open(message, secondText, {
            verticalPosition: 'top',
            panelClass: 'snack-bar-error',
            duration: 3000
        });
    }

    public showSuccessMessageToast(message: string) {
        this.snackBar.open(message, '', {
            verticalPosition: 'top',
            panelClass: 'snack-bar-success',
            duration: 3000
        });
    }

}