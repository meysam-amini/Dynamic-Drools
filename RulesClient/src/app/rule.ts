import {Serializable} from 'selenium-webdriver';

export class Rule {
    id: string;
    when_key: string;
    when_value: string;
    then_key: string;
    then_value: string;
}
