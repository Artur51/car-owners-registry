import { FilterData } from '../../app/models/filter-data';

describe('FilterData', () => {

    it('Test getSort method should return undefined in case no sort field in filter data', () => {
        const service = new FilterData();
        const result = service.getSort();
        expect(result).toBeUndefined();
    });

    it('Test getSort method should return name:direction result in case data is provided', () => {
        const service = new FilterData();
        service.sortFieldName = 'name';
        service.sortDirectionAsc = true;

        let result = service.getSort();
        expect(result).toBe('name:asc');

        service.sortFieldName = 'name2';
        service.sortDirectionAsc = false;

        result = service.getSort();
        expect(result).toBe('name2:desc');
    });

});