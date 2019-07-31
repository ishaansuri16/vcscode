import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';
import { PpUserMasterService } from './pp-user-master.service';

@Component({
    selector: 'jhi-pp-user-master-delete-dialog',
    templateUrl: './pp-user-master-delete-dialog.component.html'
})
export class PpUserMasterDeleteDialogComponent {
    ppUserMaster: IPpUserMaster;

    constructor(
        private ppUserMasterService: PpUserMasterService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ppUserMasterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ppUserMasterListModification',
                content: 'Deleted an ppUserMaster'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pp-user-master-delete-popup',
    template: ''
})
export class PpUserMasterDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ppUserMaster }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PpUserMasterDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ppUserMaster = ppUserMaster;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
