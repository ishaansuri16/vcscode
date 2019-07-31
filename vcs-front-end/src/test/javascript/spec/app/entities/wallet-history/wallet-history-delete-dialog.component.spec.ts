/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PpFrontEndTestModule } from '../../../test.module';
import { WalletHistoryDeleteDialogComponent } from 'app/entities/wallet-history/wallet-history-delete-dialog.component';
import { WalletHistoryService } from 'app/entities/wallet-history/wallet-history.service';

describe('Component Tests', () => {
    describe('WalletHistory Management Delete Component', () => {
        let comp: WalletHistoryDeleteDialogComponent;
        let fixture: ComponentFixture<WalletHistoryDeleteDialogComponent>;
        let service: WalletHistoryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [WalletHistoryDeleteDialogComponent]
            })
                .overrideTemplate(WalletHistoryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(WalletHistoryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(WalletHistoryService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
