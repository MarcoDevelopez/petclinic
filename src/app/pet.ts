import { Type } from "./type";
import { Visit } from "./visit";

export class Pet {
  id: number;
  name: string;
  birthDate: string;
  type: Type = new Type();
  visits: Visit[];
  
}
