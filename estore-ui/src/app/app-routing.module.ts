import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CatalogComponent } from './catalog/catalog.component';
import { AdminComponent } from './admin/admin.component';
import { CartComponent } from './cart/cart.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AdminProductDetailComponent } from './admin-product-detail/admin-product-detail.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'catalog', component: CatalogComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'cart', component: CartComponent },
  { path: 'admin/:id', component: AdminProductDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
