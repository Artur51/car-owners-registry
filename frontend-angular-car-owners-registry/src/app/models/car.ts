

export class Car {
    id: number;
    make: string;
    model: string;
    numberplate: string;

    constructor(init?: Partial<Car>) {
        Object.assign(this, init);
    }
}