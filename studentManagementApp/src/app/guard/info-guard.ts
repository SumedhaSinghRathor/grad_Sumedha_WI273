import { CanActivateFn } from '@angular/router';
import { User } from '../services/user/user';
import { inject } from '@angular/core';

export const infoGuard: CanActivateFn = (route, state) => {
  let us: User = inject(User);

  if (us.getName() == 'Username') {
    return false;
  } else {
    return true;
  }
};
