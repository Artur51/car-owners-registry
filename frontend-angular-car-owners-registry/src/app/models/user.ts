import { Car } from './car';

export class User {
    id: number;
    name: string;
    cars: Car[];

    constructor(init?: Partial<User>) {
        Object.assign(this, init);
    }

}