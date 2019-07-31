/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserHistoryDeleteDialogComponent } from 'app/entities/pp-user-history/pp-user-history-delete-dialog.component';
import { PpUserHistoryService } from 'app/entities/pp-user-history/pp-user-history.service';

describe('Component Tests', () => {
    describe('PpUserHistory Management Delete Component', () => {
        let comp: PpUserHistoryDeleteDialogComponent;
        let fixture: ComponentFixture<PpUserHistoryDeleteDialogComponent>;
        let service: PpUserHistoryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserHistoryDeleteDialogComponent]
            })
                .overrideTemplate(PpUserHistoryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PpUserHistoryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PpUserHistoryService);
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
