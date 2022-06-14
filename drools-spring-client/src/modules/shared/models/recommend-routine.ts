export interface ReccomendRoutine {
  patientUsername: String;
  skinCharacteristics: Array<String>;
  wantedGoals: Array<String>;
  acneType: String;
  allergies: Array<String>;
  manufacturer: String;

  /* constructor(
    patientUsername: String,
    skinCharacteristics: Array<String>,
    wantedGoals: Array<String>,
    acneType: String,
    allergies: Array<String>,
    manufacturer: String
  ) {
    this.patientUsername = patientUsername;
    this.skinCharacteristics = skinCharacteristics;
    this.wantedGoals = wantedGoals;
    this.acneType = acneType;
    this.allergies = allergies;
    this.manufacturer = manufacturer;
  }
  */
}
