import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root',
})
export class HelperService {
  constructor(private domSanitizer: DomSanitizer) {}

  bypassUrlSecurity(imageUlr: String) {
    return this.domSanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpg;base64,' + imageUlr
    );
  }
}
