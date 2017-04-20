import { Pet } from "app/pet";

export class Owner {

  id: number;
  firstName: string;
  lastName: string;
  description: string;
  address: string;
  city: string;
  telephone: string;
  pets: Pet[] = [];
  
}
