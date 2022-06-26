import { User } from './user';

export interface Patient extends User {
  birthday: String;
  gender: String;
}
