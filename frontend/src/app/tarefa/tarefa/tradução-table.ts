import { Injectable } from '@angular/core';
import { MatPaginatorIntl } from '@angular/material/paginator';


@Injectable()
export class PtBrMatPaginatorIntl extends MatPaginatorIntl {

   itemsPerPageLabel: string = 'Itens por página:';
   nextPageLabel: string = 'Página seguinte';
   previousPageLabel: string = 'Página anterior';

   getRangeLabel = function (page: any, pageSize: any,
      length: any): string {
      if (length === 0 || pageSize === 0) {
         return '0 de ' + length;
      }
      length = Math.max(length, 0);
      const startIndex = page * pageSize;
      const endIndex = startIndex < length ?
         Math.min(startIndex + pageSize, length) :
         startIndex + pageSize;
      return startIndex + 1 + ' - ' + endIndex + ' de ' + length;
   };
}