import {DataType} from'./data-type';

export class FilterData {
    text:string;
    sortFieldName:string;
    sortDirectionAsc = true;
    dataType = DataType.USER;

    public getSort() {
        if (this.sortFieldName) {
            return this.sortFieldName + ':' + (this.sortDirectionAsc ? 'asc' : 'desc');
        }
        return undefined;
    }
}