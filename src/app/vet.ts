import { Specialty } from "./specialty";

export class Vet {

  id: number;
  firstName: string;
  lastName: string;
  description: string;
  nrOfSpecialties: number;
  specialties: Specialty[];
  
}
