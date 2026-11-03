import { HttpInterceptorFn } from '@angular/common/http';

export const demoInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("INTERCEPTION !!!");

  const request = req.clone({
    setHeaders: {
      'X-DEMO': 'La valeur de lentetee'
    }
  });

  return next(request);
};
