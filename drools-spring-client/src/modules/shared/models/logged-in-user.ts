export class LoggedInUser {
  public username: String;
  public role: String;

  constructor(username: String, role: String) {
    this.username = username;
    this.role = role;
  }
}
