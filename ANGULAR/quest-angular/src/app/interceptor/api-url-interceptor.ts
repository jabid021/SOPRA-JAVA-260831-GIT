import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  const request = req.clone({
    url: `/api${ req.url }`
  });

  return next(request);
};
