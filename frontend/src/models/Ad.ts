import {AdCategory} from "./AdCategory";

export interface Ad {
    id: number;
    userName: string;
    category: AdCategory;
    title: string;
    description: string;
    created: string;
    price: number;
}
