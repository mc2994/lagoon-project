import { User } from "../models/user";

export class PaginatedObject<T> {
    public content = new Array<T>();
    public first: boolean;
    public last: boolean
    public number: number
    public numberOfElements: number
    public size: number
    public sort: null
    public totalElements: number
    public totalPages: number
}