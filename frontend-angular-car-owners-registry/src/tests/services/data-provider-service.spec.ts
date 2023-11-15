import { FilterData } from '../../app/models/filter-data';
import { DataType } from '../../app/models/data-type';
import { CarService } from '../../app/services/backend/car.service';
import { BehaviorSubject } from 'rxjs';
import { UserService } from '../../app/services/backend/user.service';
import { DataProviderService } from '../../app/services/data-provider.service';
import { Page } from '../../app/models/page';
import { Car } from '../../app/models/car';
import { User } from '../../app/models/user';

describe('DataProviderService', () => {

    it('Test downloadData method should download user data when data type is user.', () => {
        const { service, carService, userService } = setupMockedService();

        expect(service).toBeDefined();

        const model = new FilterData();
        model.dataType = DataType.USER;
        service.downloadData(model as FilterData);

        expect(carService.findByValue).not.toHaveBeenCalled();
        expect(userService.findByName).toHaveBeenCalled();

        expect(service.usersData).toBeDefined();
        expect(service.carsData).toBeNull();
    });

    it('Test downloadData method should download cars data when data type is car.', () => {
        const { service, carService, userService } = setupMockedService();

        expect(service).toBeDefined();

        const model = new FilterData();
        model.dataType = DataType.CAR;
        service.downloadData(model as FilterData);

        expect(carService.findByValue).toHaveBeenCalled();
        expect(userService.findByName).not.toHaveBeenCalled();

        expect(service.carsData).toBeDefined();
        expect(service.usersData).toBeNull();
    });

    function setupMockedService() {
        const car = new Car();
        car.id = 0;
        car.make = 'car maker';
        car.model = 'some model';
        car.numberplate = 'some number plate';

        const findByValueExpectedValue: Page<Car> = {
            content: [car],
            empty: false,
            first: false,
            last: false,
            number: 0,
            numberOfElements: 0,
            totalElements: 0,
            totalPages: 0,
            size: 0,
        };

        const user = new User();
        user.id = 1;
        user.name = 'some user name';
        user.cars = [car];

        const findByNameExpectedValue: Page<User> = {
            content: [user],
            empty: false,
            first: false,
            last: false,
            number: 0,
            numberOfElements: 0,
            totalElements: 0,
            totalPages: 0,
            size: 0,
        };

        const carService = new CarService(null);
        const findByValueSub = new BehaviorSubject<Page<Car>>(findByValueExpectedValue);
        spyOn(carService, 'findByValue').and.returnValue(findByValueSub);

        const userService = new UserService(null);
        const findByNameSub = new BehaviorSubject<Page<User>>(findByNameExpectedValue as Page<User>);
        spyOn(userService, 'findByName').and.returnValue(findByNameSub);

        const service = new DataProviderService(carService, userService);
        return {service, carService, userService };
    }

});