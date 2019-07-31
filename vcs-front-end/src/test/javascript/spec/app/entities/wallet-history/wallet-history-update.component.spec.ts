/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { PpFrontEndTestModule } from '../../../test.module';
import { WalletHistoryUpdateComponent } from 'app/entities/wallet-history/wallet-history-update.component';
import { WalletHistoryService } from 'app/entities/wallet-history/wallet-history.service';
import { WalletHistory } from 'app/shared/model/wallet-history.model';

describe('Component Tests', () => {
    describe('WalletHistory Management Update Component', () => {
        let comp: WalletHistoryUpdateComponent;
        let fixture: ComponentFixture<WalletHistoryUpdateComponent>;
        let service: WalletHistoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [WalletHistoryUpdateComponent]
            })
                .overrideTemplate(WalletHistoryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(WalletHistoryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WalletHistoryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new WalletHistory(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.walletHistory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new WalletHistory();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.walletHistory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
