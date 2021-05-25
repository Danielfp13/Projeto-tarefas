import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http'
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './service/auth.service';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { FormsModule } from '@angular/forms';

import { LayoutComponent } from './layout/layout.component';
import { TemplateModule } from './template/template.module';
import { TarefaModule } from './tarefa/tarefa.module';
import { TarefaService } from './service/tarefa.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorIntl, MatPaginatorModule } from '@angular/material/paginator';
import { PtBrMatPaginatorIntl } from './tarefa/tarefa/tradução-table';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TemplateModule,
    TarefaModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
  ],
  providers: [
     AuthService,
     TarefaService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
    {
    provide: MatPaginatorIntl,
    useClass: PtBrMatPaginatorIntl
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
