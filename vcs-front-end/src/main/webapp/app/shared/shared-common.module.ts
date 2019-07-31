import { NgModule } from '@angular/core';

import { PpFrontEndSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [PpFrontEndSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [PpFrontEndSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class PpFrontEndSharedCommonModule {}
