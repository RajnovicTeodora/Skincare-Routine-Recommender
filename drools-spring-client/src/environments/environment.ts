// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  baseUrl: 'http://localhost:8080/api',
  login: 'auth/login',
  logout: 'auth/logout',
  getPatientsUrl: 'patients/getAll',
  getPatientByUsernameUrl: 'patients/findByUsername',
  registerPatientUrl: 'patients/register',
  registerDermatologistUrl: 'dermatologists/register',
  getRoutinePerscriptionUrl: 'routine/getRoutineRecommendation',
  getRoutinesWithReactionUrl: 'routine/getPatientRoutinesWithReaction',
  hasReactionUrl: 'reaction/hasReaction',
  checkProductReactionUrl: 'reaction/checkProductReaction',
  getProductsUrl: 'products/getAll',
  addProductUrl: 'products/addProduct',
  getIngredientsUrl: 'ingredients/getAll',
  getUsersUrl: 'users/findAllExcludingCurrent',
  editUserUrl: 'users/edit',
  suspiciousPatientsReportUrl: 'report/getFrequentRoutineChangeReport',
  productReportUrl: 'report/getProductReport',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
