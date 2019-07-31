/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PpFrontEndTestModule } from '../../../test.module';
import { PpUserMasterDeleteDialogComponent } from 'app/entities/pp-user-master/pp-user-master-delete-dialog.component';
import { PpUserMasterService } from 'app/entities/pp-user-master/pp-user-master.service';

describe('Component Tests', () => {
    describe('PpUserMaster Management Delete Component', () => {
        let comp: PpUserMasterDeleteDialogComponent;
        let fixture: ComponentFixture<PpUserMasterDeleteDialogComponent>;
        let service: PpUserMasterService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PpFrontEndTestModule],
                declarations: [PpUserMasterDeleteDialogComponent]
            })
                .overrideTemplate(PpUserMasterDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PpUserMasterDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PpUserMasterService);
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
